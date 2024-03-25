/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.validator.geometric.inspector.geltungsbereich;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.inspector.GeometricFeatureInspector;
import de.latlon.xplan.validator.geometric.inspector.InvalidGeometryException;
import de.latlon.xplan.validator.geometric.inspector.model.BereichFeature;
import de.latlon.xplan.validator.geometric.inspector.model.FeatureUnderTest;
import de.latlon.xplan.validator.geometric.inspector.model.GeltungsbereichFeature;
import de.latlon.xplan.validator.geometric.inspector.model.PlanFeature;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.uom.Measure;
import org.deegree.feature.Feature;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.multi.MultiPolygon;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.primitive.GeometricPrimitive;
import org.deegree.geometry.primitive.LineString;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Polygon;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.Surface;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.geometry.standard.multi.DefaultMultiGeometry;
import org.deegree.gml.feature.FeatureInspectionException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.IntersectionMatrix;
import org.locationtech.jts.geom.TopologyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.i18n.ValidationMessages.format;

/**
 * Inspector for 2.2.3.1 Raumbezogene Objekte im Innern des Geltungsbereichs:
 * <p>
 * Bei allen raumbezogenen Objekten, die zu einem Bereich gehören, muss die
 * Objektgeometrie innerhalb des Geltungsbereichs des Bereichs liegen, bzw. im Innern des
 * Geltungsbereichs des Plans, wenn der Bereich keinen eigenen Gel- tungsbereich hat.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichInspector implements GeometricFeatureInspector {

	private static final Logger LOG = LoggerFactory.getLogger(GeltungsbereichInspector.class);

	private final GeltungsbereichInspectorContext geltungsbereichInspectorContext;

	private final List<BadGeometry> badGeometries = new ArrayList<>();

	private final List<String> errors = new ArrayList<>();

	private final List<String> warnings = new ArrayList<>();

	public GeltungsbereichInspector(XPlanVersion xPlanVersion) {
		this.geltungsbereichInspectorContext = new GeltungsbereichInspectorContext(xPlanVersion);
	}

	@Override
	public Feature inspect(Feature feature) throws FeatureInspectionException {
		geltungsbereichInspectorContext.addToContext(feature, 0.002);
		return feature;
	}

	@Override
	public boolean checkGeometricRule() {
		List<String> featureIdOfInvalidFeatures = new ArrayList<>();
		for (FeatureUnderTest featureUnderTest : geltungsbereichInspectorContext.getFeaturesUnderTest()) {
			BereichFeature bereichFeature = featureUnderTest.getBereichFeature();
			PlanFeature planFeature = featureUnderTest.getPlanFeature();
			boolean isAllowedToBeOutsidePlan = bereichFeature != null
					&& bereichFeature.isKompensationsbereichOrOutsideGeltungsbereich();
			if (isAllowedToBeOutsidePlan) {
				LOG.debug("Feature with id {} is allowed to be outside of the Geltungsbereich of the plan");
			}
			if (bereichFeature == null && planFeature == null) {
				warnings.add("Das Objekt mit der ID " + featureUnderTest.getFeatureId()
						+ " kann keinem Plan oder Bereich zugeordnet werden, die Geltungsbereichspruefung fuer dieses Objekt kann nicht durchgeführt werden.");
			}
			else {
				Geometry bereichGeltungsbereichWithBuffer = retrieveGeltungsbereichWithBuffer(bereichFeature);
				Geometry planGeltungsbereichWithBuffer = retrieveGeltungsbereichWithBuffer(planFeature);
				try {
					boolean isInsideBereichGeltungsbereich = isInsideGeom(featureUnderTest,
							bereichGeltungsbereichWithBuffer);
					boolean isInsidePlanGeltungsbereich = isAllowedToBeOutsidePlan
							|| isInsideGeom(featureUnderTest, planGeltungsbereichWithBuffer);
					if (!isInsideBereichGeltungsbereich || !isInsidePlanGeltungsbereich) {
						featureIdOfInvalidFeatures.add(featureUnderTest.getFeatureId());
						addInvalidGeometry(bereichFeature, planFeature, bereichGeltungsbereichWithBuffer,
								planGeltungsbereichWithBuffer, featureUnderTest, isInsideBereichGeltungsbereich,
								isInsidePlanGeltungsbereich);
					}
				}
				catch (InvalidGeometryException e) {
					String error = format("GeltungsbereichInspector_invalid_geom", featureUnderTest.getFeatureId());
					addErrorAndBadGeometry(error, featureUnderTest.getOriginalGeometry());
				}
			}
		}
		if (featureIdOfInvalidFeatures.isEmpty()) {
			LOG.info("No features outside geltungsbereich");
		}
		else {
			LOG.info("Features outside geltungsbereich:\n {}",
					featureIdOfInvalidFeatures.stream().collect(Collectors.joining("\n")));
		}
		return errors.isEmpty();
	}

	@Override
	public List<String> getErrors() {
		return errors;
	}

	@Override
	public List<String> getWarnings() {
		return warnings;
	}

	@Override
	public List<BadGeometry> getBadGeometries() {
		return badGeometries;
	}

	@Override
	public boolean applicableForVersion(XPlanVersion version) {
		return true;
	}

	private Geometry retrieveGeltungsbereichWithBuffer(GeltungsbereichFeature geltungsbereichFeature) {
		if (geltungsbereichFeature != null && geltungsbereichFeature.hasGeometry()) {
			if (geltungsbereichFeature.isGeometryValid()) {
				return geltungsbereichFeature.getBufferedGeometry();
			}
			else {
				String error = format("GeltungsbereichInspector_invalid_geltungsbereich",
						geltungsbereichFeature.getFeatureId());
				addErrorAndBadGeometry(error, geltungsbereichFeature.getOriginalGeometry());
			}
		}
		return null;
	}

	private void addInvalidGeometry(BereichFeature bereichFeature, PlanFeature planFeature,
			Geometry bufferedBereichGeltungsbereich, Geometry bufferedPlanGeltungsbereich,
			FeatureUnderTest featureUnderTest, boolean isInsideBereichGeltungsbereich,
			boolean isInsidePlanGeltungsbereich) throws InvalidGeometryException {
		AbstractDefaultGeometry featureGeom = featureUnderTest.getOriginalGeometry();
		try {
			if (!isInsidePlanGeltungsbereich && !isInsideBereichGeltungsbereich) {
				AbstractDefaultGeometry planGeom = planFeature.getOriginalGeometry();
				AbstractDefaultGeometry bereichGeom = bereichFeature.getOriginalGeometry();
				boolean isDisjointPlanGeom = planGeom.isDisjoint(featureGeom);
				boolean isDisjointBereichGeom = bereichGeom.isDisjoint(featureGeom);
				if (isDisjointPlanGeom && isDisjointBereichGeom) {
					String pointOutside = getPointOutside(featureUnderTest);
					String error = format("GeltungsbereichInspector_error_outOfPlanAndBereichGeltungsbereich",
							featureUnderTest.getFeatureId(), pointOutside);
					addErrorAndBadGeometry(error, featureUnderTest.getOriginalGeometry());
				}
				else if (isDisjointPlanGeom) {
					String pointOutside = getPointOutside(featureUnderTest);
					String error = format("GeltungsbereichInspector_error_outOfPlanGeltungsbereich",
							featureUnderTest.getFeatureId(), pointOutside);
					addErrorAndBadGeometry(error, featureUnderTest.getOriginalGeometry());
					createAndAddErrorWithIntersectionPoints(null, bereichFeature, bereichGeom,
							bufferedBereichGeltungsbereich, featureUnderTest);
				}
				else if (isDisjointBereichGeom) {
					String pointOutside = getPointOutside(featureUnderTest);
					String error = format("GeltungsbereichInspector_error_outOfBereichGeltungsbereich",
							featureUnderTest.getFeatureId(), pointOutside);
					addErrorAndBadGeometry(error, featureUnderTest.getOriginalGeometry());
					createAndAddErrorWithIntersectionPoints(planFeature, null, planGeom, bufferedPlanGeltungsbereich,
							featureUnderTest);
				}
				else {
					if (planGeom.equals(bereichGeom)) {
						createAndAddErrorWithIntersectionPoints(planFeature, bereichFeature, planGeom,
								bufferedPlanGeltungsbereich, featureUnderTest);
					}
					else {
						createAndAddErrorWithIntersectionPoints(planFeature, null, planGeom,
								bufferedPlanGeltungsbereich, featureUnderTest);
						createAndAddErrorWithIntersectionPoints(null, bereichFeature, bereichGeom,
								bufferedBereichGeltungsbereich, featureUnderTest);
					}
				}
			}
			else if (!isInsidePlanGeltungsbereich) {
				AbstractDefaultGeometry planGeom = planFeature.getOriginalGeometry();
				boolean isDisjointPlanGeom = planGeom.isDisjoint(featureGeom);
				if (isDisjointPlanGeom) {
					String pointOutside = getPointOutside(featureUnderTest);
					String error = format("GeltungsbereichInspector_error_outOfPlanGeltungsbereich",
							featureUnderTest.getFeatureId(), pointOutside);
					addErrorAndBadGeometry(error, featureUnderTest.getOriginalGeometry());
				}
				else {
					createAndAddErrorWithIntersectionPoints(planFeature, null, planGeom, bufferedPlanGeltungsbereich,
							featureUnderTest);
				}
			}
			else if (!isInsideBereichGeltungsbereich) {
				AbstractDefaultGeometry bereichGeom = bereichFeature.getOriginalGeometry();
				boolean isDisjointBereichGeom = bereichGeom.isDisjoint(featureGeom);
				if (isDisjointBereichGeom) {
					String pointOutside = getPointOutside(featureUnderTest);
					String error = format("GeltungsbereichInspector_error_outOfBereichGeltungsbereich",
							featureUnderTest.getFeatureId(), pointOutside);
					addErrorAndBadGeometry(error, featureUnderTest.getOriginalGeometry());
				}
				else {
					createAndAddErrorWithIntersectionPoints(null, bereichFeature, bereichGeom,
							bufferedBereichGeltungsbereich, featureUnderTest);
				}
			}
		}
		catch (TopologyException | IllegalArgumentException e) {
			throw new InvalidGeometryException(e);
		}
	}

	private static String getPointOutside(FeatureUnderTest featureUnderTest) {
		Geometry jtsGeometry = featureUnderTest.getJtsGeometry();
		Coordinate coordinate = jtsGeometry.getCoordinate();
		return "(" + coordinate.x + ", " + coordinate.y + ")";
	}

	private void createAndAddErrorWithIntersectionPoints(PlanFeature planFeature, BereichFeature bereichFeature,
			AbstractDefaultGeometry geltungsbereichGeom, Geometry geltungsbereichWithBuffer,
			FeatureUnderTest inGeltungsbereichFeature) {
		AbstractDefaultGeometry featureGeom = inGeltungsbereichFeature.getOriginalGeometry();
		List<? extends org.deegree.geometry.Geometry> featureGeoms = extractExteriorRingOrPrimitive(featureGeom);
		List<? extends org.deegree.geometry.Geometry> geltungsbereichGeoms = extractExteriorRingOrPrimitive(
				geltungsbereichGeom);
		List<String> points = new ArrayList<>();
		List<AbstractDefaultGeometry> differencesOutsideGeltungsbereich = new ArrayList<>();
		geltungsbereichGeoms.forEach(currentGeltungsbereichGeom -> {
			featureGeoms.forEach(currentFeatureGeom -> {
				List<AbstractDefaultGeometry> differenceOutsideGeltungsbereich = findRelevantDifferenceOutsideGeltungsbereich(
						(AbstractDefaultGeometry) currentGeltungsbereichGeom, geltungsbereichWithBuffer,
						currentFeatureGeom);
				differencesOutsideGeltungsbereich.addAll(differenceOutsideGeltungsbereich);
				org.deegree.geometry.Geometry intersection = currentFeatureGeom
					.getIntersection(currentGeltungsbereichGeom);
				addIntersectionPoints(points, differenceOutsideGeltungsbereich, intersection);
			});
		});

		String error = crateErrorMessage(planFeature, bereichFeature, inGeltungsbereichFeature.getFeatureId(), points);
		BadGeometry badGeometry = addErrorAndBadGeometry(error, inGeltungsbereichFeature.getOriginalGeometry());
		addGeometryOutsideGeltungsbereich(inGeltungsbereichFeature.getFeatureId(), differencesOutsideGeltungsbereich,
				badGeometry);
	}

	private String crateErrorMessage(PlanFeature planFeature, BereichFeature bereichFeature, String featureId,
			List<String> points) {
		if (planFeature != null && bereichFeature != null) {
			if (points.isEmpty()) {
				return format("GeltungsbereichInspector_error_nogeom_bereichAndPlan", featureId,
						planFeature.getFeatureId(), bereichFeature.getPlanId());
			}
			String pointList = points.stream().collect(Collectors.joining(","));
			return format("GeltungsbereichInspector_error_withgeom_bereichAndPlan", featureId,
					planFeature.getFeatureId(), bereichFeature.getPlanId(), pointList);
		}
		if (bereichFeature != null) {
			if (points.isEmpty()) {
				return format("GeltungsbereichInspector_error_nogeom_bereich", featureId,
						bereichFeature.getFeatureId());
			}
			String pointList = points.stream().collect(Collectors.joining(","));
			return format("GeltungsbereichInspector_error_withgeom_bereich", featureId, bereichFeature.getFeatureId(),
					pointList);
		}
		if (points.isEmpty()) {
			return format("GeltungsbereichInspector_error_nogeom_plan", featureId, planFeature.getFeatureId());
		}
		String pointList = points.stream().collect(Collectors.joining(","));
		return format("GeltungsbereichInspector_error_withgeom_plan", featureId, planFeature.getFeatureId(), pointList);

	}

	private List<AbstractDefaultGeometry> findRelevantDifferenceOutsideGeltungsbereich(
			AbstractDefaultGeometry geltungsbereichGeom, Geometry geltungsbereichWithBuffer,
			org.deegree.geometry.Geometry currentFeatureGeom) {
		AbstractDefaultGeometry difference = (AbstractDefaultGeometry) currentFeatureGeom
			.getDifference(geltungsbereichGeom);
		List<AbstractDefaultGeometry> relevantDifferences = new ArrayList<>();
		if (difference == null) {
			return Collections.emptyList();
		}
		if (difference instanceof MultiGeometry) {
			((MultiGeometry<?>) difference).forEach(geom -> relevantDifferences.add((AbstractDefaultGeometry) geom));
		}
		else {
			relevantDifferences.add(difference);
		}
		return relevantDifferences.stream()
			.filter(diff -> !geltungsbereichWithBuffer.covers(diff.getJTSGeometry()))
			.collect(Collectors.toList());
	}

	private void addIntersectionPoints(List<String> points, List<AbstractDefaultGeometry> difference,
			org.deegree.geometry.Geometry intersection) {
		if (intersection instanceof Point) {
			boolean isInDiff = difference.stream().anyMatch(diff -> {
				// buffer with tolerance is used to ensure that control points and
				// intersection points are identified!
				org.deegree.geometry.Geometry buffer = diff.getBuffer(new Measure(new BigDecimal(0.001), "m"));
				return buffer.contains(intersection);
			});
			if (isInDiff)
				points.add(pointAsReadableString((Point) intersection));
		}
		else if (intersection instanceof LineString) {
			((LineString) intersection).getControlPoints().forEach(cp -> addIntersectionPoints(points, difference, cp));
		}
		else if (intersection instanceof DefaultMultiGeometry) {
			for (Object geom : (DefaultMultiGeometry<?>) intersection) {
				addIntersectionPoints(points, difference, (org.deegree.geometry.Geometry) geom);
			}
		}
	}

	private void addGeometryOutsideGeltungsbereich(String featureId,
			List<AbstractDefaultGeometry> geometriesOutsideGeltungsbereich, BadGeometry badGeometry) {
		AtomicInteger index = new AtomicInteger();
		geometriesOutsideGeltungsbereich.forEach(geomOutside -> {
			String featureIdWithIndex = featureId + "_" + index.getAndIncrement();
			geomOutside.setId(featureIdWithIndex + "_OutsideGeltungsbereich");
			String error = format("GeltungsbereichInspector_geomOutside", featureIdWithIndex);
			badGeometry.addMarkerGeometry(error, geomOutside);
		});
	}

	private List<? extends org.deegree.geometry.Geometry> extractExteriorRingOrPrimitive(
			AbstractDefaultGeometry geometry) {
		switch (geometry.getGeometryType()) {
			case PRIMITIVE_GEOMETRY:
				org.deegree.geometry.Geometry primitive = extractExteriorRingOrPrimitive((GeometricPrimitive) geometry);
				return Collections.singletonList(primitive);
			case MULTI_GEOMETRY:
				return extractExteriorRingOrPrimitive((MultiGeometry<?>) geometry);
			default:
				return Collections.singletonList(geometry);
		}
	}

	private org.deegree.geometry.Geometry extractExteriorRingOrPrimitive(GeometricPrimitive geometry) {
		switch (geometry.getPrimitiveType()) {
			case Surface:
				Surface surface = (Surface) geometry;
				switch (surface.getSurfaceType()) {
					case Polygon:
						Polygon polygon = (Polygon) surface;
						return polygon.getExteriorRing();
					default:
				}
			default:
		}
		return geometry;
	}

	@SuppressWarnings("unchecked")
	private List<Ring> extractExteriorRingOrPrimitive(MultiGeometry<?> geometry) {
		switch (geometry.getMultiGeometryType()) {
			case MULTI_CURVE:
				return (List<Ring>) geometry;
			case MULTI_POLYGON:
				MultiPolygon multiPolygon = (MultiPolygon) geometry;
				return multiPolygon.stream().map(p -> p.getExteriorRing()).collect(Collectors.toList());
			case MULTI_SURFACE:
				MultiSurface<?> multiSurface = (MultiSurface<?>) geometry;
				List<Ring> exteriorRingsSurface = new ArrayList<>();
				for (Object surfaceObject : multiSurface) {
					Surface surface = (Surface) surfaceObject;
					if (Surface.SurfaceType.Polygon == surface.getSurfaceType()) {
						exteriorRingsSurface.add(((Polygon) surface).getExteriorRing());
					}
					else {
						LOG.warn(
								"Intersection with other geometry types than Polygons in a MultiSurface are currently not supported.");
					}
				}
				return exteriorRingsSurface;
			default:
		}
		LOG.warn("Intersection with geometries of typ {} are currently not supported.",
				geometry.getMultiGeometryType());
		return Collections.emptyList();
	}

	private boolean isInsideGeom(FeatureUnderTest inGeltungsbereichFeature, Geometry geltungsbereichWithBuffer)
			throws InvalidGeometryException {
		try {
			if (geltungsbereichWithBuffer == null)
				return true;
			Geometry featureGeometry = inGeltungsbereichFeature.getJtsGeometry();
			if (featureGeometry == null) {
				throw new InvalidGeometryException("Geometry of feature with ID "
						+ inGeltungsbereichFeature.getFeatureId() + " could not be parsed (or is empty)");
			}
			IntersectionMatrix relate = geltungsbereichWithBuffer.relate(featureGeometry);
			return relate.matches("***F**FFT");
		}
		catch (TopologyException | IllegalArgumentException e) {
			throw new InvalidGeometryException(e);
		}
	}

	private BadGeometry addErrorAndBadGeometry(String error, AbstractDefaultGeometry geometry) {
		errors.add(error);
		BadGeometry badGeometry = new BadGeometry(geometry, error);
		badGeometries.add(badGeometry);
		return badGeometry;
	}

	private String pointAsReadableString(Point p) {
		return Arrays.stream(p.getAsArray())
			.filter(value -> !Double.isNaN(value))
			.mapToObj(Double::toString)
			.collect(Collectors.joining(",", "(", ")"));
	}

}
