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

import de.latlon.xplan.validator.geometric.inspector.GeometricFeatureInspector;
import de.latlon.xplan.validator.geometric.inspector.InvalidGeometryException;
import de.latlon.xplan.validator.geometric.inspector.model.GeltungsbereichFeature;
import de.latlon.xplan.validator.geometric.inspector.model.FeatureUnderTest;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.feature.Feature;
import org.deegree.geometry.composite.CompositeGeometry;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.multi.MultiPoint;
import org.deegree.geometry.multi.MultiPolygon;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.primitive.GeometricPrimitive;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Polygon;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.Surface;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.gml.feature.FeatureInspectionException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.TopologyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

	private static final String ERROR_MSG = "2.2.3.1: Das Objekt mit der gml id %s liegt nicht vollstaendig im Geltungsbereich des Bereichs/Plans. Schnittpunkte mit dem Umring des Geltungsbereich: %s";

	private static final String SCHNITTPUNKT_MSG = "2.2.3.1: Geometrie des Objekts mit der gml id %s, die ausserhalb des Geltungsbereichs des Bereichs/Plans liegt.";

	private static final String OUTOFGELTUNGSBEREICH_MSG = "2.2.3.1: Das Objekt mit der gml id %s liegt vollstaendig ausserhalb des Geltungsbereichs des Bereichs/Plans.";

	private static final String ERROR_MSG_INVALID_CHECK = "2.2.3.1: Der Geltungsbereich des Objekts mit der gml id %s konnte nicht überprüft werden. Wahrscheinlich liegt eine invalide Geometrie vor.";

	private static final String ERROR_MSG_INVALID_GELTUNGSBEREICH = "2.2.3.1: Der Geltungsbereich des Plans ist geometrisch nicht valide. Betroffen ist das Feature mit der gml id %s. Die Pruefung des Geltungsbereichs kann nicht durchgeführt werden.";

	private GeltungsbereichInspectorContext geltungsbereichInspectorContext = new GeltungsbereichInspectorContext();

	private List<BadGeometry> badGeometries = new ArrayList<>();

	private List<String> errors = new ArrayList<>();

	@Override
	public Feature inspect(Feature feature) throws FeatureInspectionException {
		geltungsbereichInspectorContext.addToContext(feature);
		return feature;
	}

	@Override
	public boolean checkGeometricRule() {
		List<String> featureIdOfInvalidFeatures = new ArrayList<>();
		for (FeatureUnderTest featureUnderTest : geltungsbereichInspectorContext.getFeaturesUnderTest()) {
			GeltungsbereichFeature geltungsbereichFeature = featureUnderTest.retrieveGeltungsbereichFeature();
			if (geltungsbereichFeature == null) {
				LOG.debug("Feature with ID {} has no Plan or Bereich", featureUnderTest.getFeatureId());
			}
			else {
				if (!geltungsbereichFeature.isGeometryValid()) {
					String error = String.format(ERROR_MSG_INVALID_GELTUNGSBEREICH,
							geltungsbereichFeature.getFeatureId());
					addErrorAndBadGeometry(error, geltungsbereichFeature.getOriginalGeometry());
					return false;
				}
				Geometry geltungsbereichWithBuffer = geltungsbereichFeature.getBufferedGeometry();
				try {
					if (!isInsideGeom(featureUnderTest, geltungsbereichWithBuffer)) {
						featureIdOfInvalidFeatures.add(featureUnderTest.getFeatureId());
						addInvalidGeometry(geltungsbereichFeature, featureUnderTest);
					}
				}
				catch (InvalidGeometryException e) {
					String error = String.format(ERROR_MSG_INVALID_CHECK, featureUnderTest.getFeatureId());
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

	private void addPointOutsideGeltungsbereich(List<String> points, org.deegree.geometry.Geometry featureGeom) {
		if (featureGeom instanceof Point) {
			points.add(pointAsReadableString((Point) featureGeom));
		}
		else if (featureGeom instanceof MultiPoint) {
			points.addAll(
					((MultiPoint) featureGeom).stream().map(this::pointAsReadableString).collect(Collectors.toList()));
		}
		else {
			points.add("Ausgabe nicht moeglich.");
		}
	}

	private void addIntersectionPoints(List<String> points, org.deegree.geometry.Geometry intersection) {
		if (intersection instanceof Point) {
			points.add(pointAsReadableString((Point) intersection));
		}
		else if (intersection instanceof MultiPoint) {
			points.addAll(
					((MultiPoint) intersection).stream().map(this::pointAsReadableString).collect(Collectors.toList()));
		}
		else if (intersection instanceof CompositeGeometry) {
			for (Object geom : (CompositeGeometry) intersection) {
				addIntersectionPoints(points, (org.deegree.geometry.Geometry) geom);
			}
		}
		else {
			points.add("Ausgabe nicht moeglich.");
		}
	}

	private void addInvalidGeometry(GeltungsbereichFeature geltungsbereichFeature,
			FeatureUnderTest inGeltungsbereichFeature) throws InvalidGeometryException {
		AbstractDefaultGeometry featureGeom = inGeltungsbereichFeature.getOriginalGeometry();
		AbstractDefaultGeometry geltungsbereichGeom = geltungsbereichFeature.getOriginalGeometry();
		try {
			String points = calculateIntersectionPoints(geltungsbereichGeom, inGeltungsbereichFeature);
			if (geltungsbereichGeom.isDisjoint(featureGeom)) {
				String error = String.format(OUTOFGELTUNGSBEREICH_MSG, inGeltungsbereichFeature.getFeatureId(), points);
				addErrorAndBadGeometry(error, inGeltungsbereichFeature.getOriginalGeometry());
			}
			else {
				String error = String.format(ERROR_MSG, inGeltungsbereichFeature.getFeatureId(), points);
				BadGeometry badGeometry = addErrorAndBadGeometry(error, inGeltungsbereichFeature.getOriginalGeometry());
				addGeometryOutsideGeltungsbereich(inGeltungsbereichFeature.getFeatureId(), featureGeom,
						geltungsbereichGeom, badGeometry);
			}
		}
		catch (TopologyException | IllegalArgumentException e) {
			throw new InvalidGeometryException(e);
		}
	}

	private String calculateIntersectionPoints(AbstractDefaultGeometry geltungsbereichGeom,
			FeatureUnderTest inGeltungsbereichFeature) {
		List<String> points = new ArrayList<>();
		AbstractDefaultGeometry featureGeom = inGeltungsbereichFeature.getOriginalGeometry();
		if (geltungsbereichGeom.isDisjoint(featureGeom)) {
			addPointOutsideGeltungsbereich(points, featureGeom);
		}
		else {
			List<? extends org.deegree.geometry.Geometry> featureGeoms = extractExteriorRingOrPrimitive(featureGeom);
			List<? extends org.deegree.geometry.Geometry> geltungsbereichGeoms = extractExteriorRingOrPrimitive(
					geltungsbereichGeom);
			geltungsbereichGeoms.forEach(currentGeltungsbereichGeom -> {
				featureGeoms.forEach(currentFeatureGeom -> {
					org.deegree.geometry.Geometry intersection = currentFeatureGeom
							.getIntersection(currentGeltungsbereichGeom);
					addIntersectionPoints(points, intersection);
				});
			});
		}
		return points.stream().collect(Collectors.joining(","));
	}

	private void addGeometryOutsideGeltungsbereich(String featureId, AbstractDefaultGeometry featureGeom,
			AbstractDefaultGeometry geltungsbereichGeom, BadGeometry badGeometry) {
		List<? extends org.deegree.geometry.Geometry> featureGeoms = extractExteriorRingOrPrimitive(featureGeom);
		featureGeoms.forEach(currentFeatureGeom -> {
			org.deegree.geometry.Geometry geomOutsideGeltungsbereich = currentFeatureGeom
					.getDifference(geltungsbereichGeom);
			if (geomOutsideGeltungsbereich != null) {
				geomOutsideGeltungsbereich.setId(featureId + "_OutsideGeltungsbereich");
				String error = String.format(SCHNITTPUNKT_MSG, featureId);
				badGeometry.addMarkerGeometry(error, geomOutsideGeltungsbereich);
			}
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
			return geltungsbereich.covers(geometry);
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

}
