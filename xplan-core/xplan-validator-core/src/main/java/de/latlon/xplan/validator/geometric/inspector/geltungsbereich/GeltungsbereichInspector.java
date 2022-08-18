/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
import de.latlon.xplan.validator.geometric.inspector.model.FeatureUnderTest;
import de.latlon.xplan.validator.geometric.inspector.model.GeltungsbereichFeature;
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
import org.locationtech.jts.geom.GeometryFactory;
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

	private GeltungsbereichInspectorContext geltungsbereichInspectorContext = new GeltungsbereichInspectorContext();

	private List<BadGeometry> badGeometries = new ArrayList<>();

	private List<String> errors = new ArrayList<>();

	@Override
	public Feature inspect(Feature feature) throws FeatureInspectionException {
		geltungsbereichInspectorContext.addToContext(feature, 0.002);
		return feature;
	}

	@Override
	public boolean checkGeometricRule() {
		List<String> featureIdOfInvalidFeatures = new ArrayList<>();
		for (FeatureUnderTest featureUnderTest : geltungsbereichInspectorContext.getFeaturesUnderTest()) {
			GeltungsbereichFeature geltungsbereichFeature = featureUnderTest.getGeltungsbereichFeature();
			if (geltungsbereichFeature == null) {
				LOG.debug("Feature with ID {} has no Plan or Bereich", featureUnderTest.getFeatureId());
			}
			else {
				if (!geltungsbereichFeature.isGeometryValid()) {
					String error = format("GeltungsbereichInspector_invalid_geltungsbereich",
							geltungsbereichFeature.getFeatureId());
					addErrorAndBadGeometry(error, geltungsbereichFeature.getOriginalGeometry());
					return false;
				}
				Geometry geltungsbereichWithBuffer = geltungsbereichFeature.getBufferedGeometry();
				try {
					if (!isInsideGeom(featureUnderTest, geltungsbereichWithBuffer)) {
						featureIdOfInvalidFeatures.add(featureUnderTest.getFeatureId());
						addInvalidGeometry(geltungsbereichFeature, geltungsbereichWithBuffer, featureUnderTest);
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
		return Collections.emptyList();
	}

	@Override
	public List<BadGeometry> getBadGeometries() {
		return badGeometries;
	}

	@Override
	public boolean applicableForVersion(XPlanVersion version) {
		return true;
	}

	private void addInvalidGeometry(GeltungsbereichFeature geltungsbereichFeature, Geometry geltungsbereichWithBuffer,
			FeatureUnderTest inGeltungsbereichFeature) throws InvalidGeometryException {
		AbstractDefaultGeometry featureGeom = inGeltungsbereichFeature.getOriginalGeometry();
		AbstractDefaultGeometry geltungsbereichGeom = geltungsbereichFeature.getOriginalGeometry();
		try {
			if (geltungsbereichGeom.isDisjoint(featureGeom)) {
				String error = format("GeltungsbereichInspector_error_outOfGeltungsbereich",
						inGeltungsbereichFeature.getFeatureId());
				addErrorAndBadGeometry(error, inGeltungsbereichFeature.getOriginalGeometry());
			}
			else {
				createAndAddErrorWithIntersectionPoints(geltungsbereichGeom, geltungsbereichWithBuffer,
						inGeltungsbereichFeature);
			}
		}
		catch (TopologyException | IllegalArgumentException e) {
			throw new InvalidGeometryException(e);
		}
	}

	private void createAndAddErrorWithIntersectionPoints(AbstractDefaultGeometry geltungsbereichGeom,
			Geometry geltungsbereichWithBuffer, FeatureUnderTest inGeltungsbereichFeature) {
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

		String error = crateErrorMessage(inGeltungsbereichFeature, points);
		BadGeometry badGeometry = addErrorAndBadGeometry(error, inGeltungsbereichFeature.getOriginalGeometry());
		addGeometryOutsideGeltungsbereich(inGeltungsbereichFeature.getFeatureId(), differencesOutsideGeltungsbereich,
				badGeometry);
	}

	private String crateErrorMessage(FeatureUnderTest inGeltungsbereichFeature, List<String> points) {
		if (points.isEmpty()) {
			return format("GeltungsbereichInspector_error_nogeom", inGeltungsbereichFeature.getFeatureId());
		}
		String pointList = points.stream().collect(Collectors.joining(","));
		return format("GeltungsbereichInspector_error_withgeom", inGeltungsbereichFeature.getFeatureId(), pointList);
	}

	private List<AbstractDefaultGeometry> findRelevantDifferenceOutsideGeltungsbereich(
			AbstractDefaultGeometry geltungsbereichGeom, Geometry geltungsbereichWithBuffer,
			org.deegree.geometry.Geometry currentFeatureGeom) {
		AbstractDefaultGeometry difference = (AbstractDefaultGeometry) currentFeatureGeom
				.getDifference(geltungsbereichGeom);
		List<AbstractDefaultGeometry> relevantDifferences = new ArrayList<>();
		if (difference instanceof MultiGeometry) {
			((MultiGeometry<?>) difference).forEach(geom -> relevantDifferences.add((AbstractDefaultGeometry) geom));
		}
		else {
			relevantDifferences.add(difference);
		}
		return relevantDifferences.stream().filter(diff -> !geltungsbereichWithBuffer.covers(diff.getJTSGeometry()))
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
			for (Object geom : (DefaultMultiGeometry) intersection) {
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
			return extractExteriorRingOrPrimitive((MultiGeometry) geometry);
		}
		return Collections.singletonList(geometry);
	}

	private org.deegree.geometry.Geometry extractExteriorRingOrPrimitive(GeometricPrimitive geometry) {
		switch (geometry.getPrimitiveType()) {
		case Surface:
			Surface surface = (Surface) geometry;
			switch (surface.getSurfaceType()) {
			case Polygon:
				Polygon polygon = (Polygon) surface;
				return polygon.getExteriorRing();
			}
		}
		return geometry;
	}

	private List<Ring> extractExteriorRingOrPrimitive(MultiGeometry geometry) {
		switch (geometry.getMultiGeometryType()) {
		case MULTI_CURVE:
			return geometry;
		case MULTI_POLYGON:
			MultiPolygon multiPolygon = (MultiPolygon) geometry;
			return multiPolygon.stream().map(p -> p.getExteriorRing()).collect(Collectors.toList());
		case MULTI_SURFACE:
			MultiSurface multiSurface = (MultiSurface) geometry;
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
		}
		LOG.warn("Intersection with geometries of typ {} are currently not supported.",
				geometry.getMultiGeometryType());
		return Collections.emptyList();
	}

	private boolean isInsideGeom(FeatureUnderTest inGeltungsbereichFeature, Geometry geltungsbereich)
			throws InvalidGeometryException {
		try {
			Geometry geometry = inGeltungsbereichFeature.getJtsGeometry();
			if (geometry == null) {
				throw new InvalidGeometryException("Geometry of feature with ID "
						+ inGeltungsbereichFeature.getFeatureId() + " could not be parsed (or is empty)");
			}
			IntersectionMatrix relate = geltungsbereich.relate(geometry);
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
		return Arrays.stream(p.getAsArray()).filter(value -> !Double.isNaN(value)).mapToObj(Double::toString)
				.collect(Collectors.joining(",", "(", ")"));
	}

	private org.locationtech.jts.geom.Point createJtsPoint(Point point) {
		Coordinate coordinate = new Coordinate(point.get0(), point.get1(), point.get2());
		org.locationtech.jts.geom.Point jtsPoint = new GeometryFactory().createPoint(coordinate);
		return jtsPoint;
	}

}
