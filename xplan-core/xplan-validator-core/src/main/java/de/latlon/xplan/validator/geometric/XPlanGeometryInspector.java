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
package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.commons.jts.JtsParser;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.GeometryFactory;
import org.deegree.geometry.GeometryInspectionException;
import org.deegree.geometry.GeometryInspector;
import org.deegree.geometry.composite.CompositeGeometry;
import org.deegree.geometry.io.WKTWriter;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.points.Points;
import org.deegree.geometry.primitive.Curve;
import org.deegree.geometry.primitive.GeometricPrimitive;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.Surface;
import org.deegree.geometry.primitive.patches.PolygonPatch;
import org.deegree.geometry.primitive.patches.SurfacePatch;
import org.deegree.geometry.primitive.segments.CurveSegment;
import org.deegree.geometry.primitive.segments.LineStringSegment;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.geometry.standard.primitive.DefaultPoint;
import org.locationtech.jts.algorithm.LineIntersector;
import org.locationtech.jts.algorithm.Orientation;
import org.locationtech.jts.algorithm.RobustLineIntersector;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.IntersectionMatrix;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.TopologyException;
import org.locationtech.jts.geomgraph.Edge;
import org.locationtech.jts.geomgraph.EdgeIntersection;
import org.locationtech.jts.geomgraph.GeometryGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.i18n.ValidationMessages.format;
import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;
import static org.deegree.geometry.primitive.segments.CurveSegment.CurveSegmentType.LINE_STRING_SEGMENT;

/**
 * Validiert die aus XPlan-Dokumenten geparsten Geometrien und prüft sie auf topologische
 * Korrektheit / Randbedingungen.
 * <p>
 * <li>Punkte/Punktelisten: Prüfung auf 2D-Koordinaten (Fehler, wird nicht
 * korrigiert)</li>
 * <li>Kurven: Segment-Kontinuität (Fehler, wird nicht korrigiert)</li>
 * <li>Generische Kurve/LineString: falls geschlossen, Laufrichtung CCW (Warnung, wird
 * korrigiert)</li>
 * <li>Ring: Geschlossenheit (Warnung/Fehler, wird korrigiert wenn Abstand Startpunkt zu
 * Endpunkt kleiner als Epsilon)</li>
 * <li>Ring: Keine Selbstüberschneidung (Fehler)</li>
 * <li>Ring: Keine doppelten Stützpunkte (Fehler)</li>
 * <li>Polygon/PolygonPatch: Äußerer Ring, Orientierung CCW (Warnung, wird
 * korrigiert).</li>
 * <li>Polygon/PolygonPatch: Innere Ringe, Orientierung CW (Warnung, wird
 * korrigiert).</li>
 * <li>Polygon/PolygonPatch: Kein Schnitt äußerer Ring / innere Ringe (Fehler)</li>
 * <li>Polygon/PolygonPatch: Innere Ringe liegen innerhalb der vom äußeren Ring
 * umschlossenen Fläche (Fehler)</li>
 * <li>Polygon/PolygonPatch: Schnittmenge der von zwei inneren Ringen gebildeten Flächen
 * ist leer (Fehler)</li>
 * <li>MultiPolygon: Kein Schnitt zwischen einzelnen Polygonen (Fehler)</li>
 * </p>
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @version $Revision: 1154 $, $Date: 2010-03-02 13:23:14 +0100 (Di, 02 Mrz 2010) $
 */
class XPlanGeometryInspector implements GeometryInspector {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanGeometryInspector.class);

	private static final AbstractDefaultGeometry DEFAULT_GEOM = new DefaultPoint(null, null, null,
			new double[] { 0.0, 0.0 });

	private final XMLStreamReaderWrapper xmlStream;

	private final boolean skipOrientation;

	private final List<String> errors = new ArrayList<>();

	private final List<String> warnings = new ArrayList<>();

	private final List<BadGeometry> badGeometries = new ArrayList<>();

	private final JtsParser jtsParser = new JtsParser();

	private BadGeometry lastBadGeometry;

	public XPlanGeometryInspector(XMLStreamReaderWrapper xmlStream, boolean skipOrientation) {
		this.xmlStream = xmlStream;
		this.skipOrientation = skipOrientation;
	}

	public List<String> getErrors() {
		return errors;
	}

	public List<String> getWarnings() {
		return warnings;
	}

	public List<BadGeometry> getBadGeometries() {
		return badGeometries;
	}

	@Override
	public Geometry inspect(Geometry geom) throws GeometryInspectionException {
		try {
			switch (geom.getGeometryType()) {
				case PRIMITIVE_GEOMETRY: {
					inspect((GeometricPrimitive) geom);
					break;
				}
				case MULTI_GEOMETRY: {
					inspect((MultiGeometry<?>) geom);
					break;
				}
				case COMPOSITE_GEOMETRY: {
					String msg = createMessage(
							format("XPlanGeometryInspector_invalid_unsupportedGeometry", "Composites"));
					createError(msg);
				}
				default:
					LOG.warn("Unsupported geometry type (will be ignored) {}", geom.getGeometryType());
			}
		}
		catch (Exception e) {
			LOG.trace("Fehler bei der geometrischen Validierung!", e);
			String message = createMessage(e.getMessage());
			createError(message);
		}
		addLastBadGeometry(geom);
		return geom;
	}

	@Override
	public CurveSegment inspect(CurveSegment segment) throws GeometryInspectionException {
		return segment;
	}

	@Override
	public SurfacePatch inspect(SurfacePatch patch) throws GeometryInspectionException {
		if (patch instanceof PolygonPatch) {
			inspect((PolygonPatch) patch);
		}
		else {
			String msg = createMessage(
					format("XPlanGeometryInspector_invalid_unsupportedGeometry", "Nicht-planare Patches"));
			createError(msg);
		}
		return patch;
	}

	@Override
	public Points inspect(Points points) throws GeometryInspectionException {
		if (points.getDimension() != 2) {
			String msg = createMessage(
					format("XPlanGeometryInspector_invalid_punktliste_dimension", points.getDimension()));
			createError(msg);
		}
		return points;
	}

	private void inspect(MultiGeometry<?> geom) throws GeometryInspectionException {
		switch (geom.getMultiGeometryType()) {
			case MULTI_POINT:
			case MULTI_CURVE:
				break;
			case MULTI_SURFACE:
				inspect((MultiSurface<?>) geom);
			default:
		}
	}

	private void inspect(MultiSurface<?> geom) throws GeometryInspectionException {
		List<Object> surfaces = new ArrayList<>(geom);
		AtomicInteger intersectionIndex = new AtomicInteger(1);
		for (Object geom1 : geom) {
			surfaces.remove(geom1);
			Surface surface1 = (Surface) geom1;
			for (Object curve2 : surfaces) {
				Surface surface2 = (Surface) curve2;
				inspect(surface2);
				inspect(geom.getId(), intersectionIndex, surface1, surface2);
			}
		}
	}

	private void inspect(GeometricPrimitive geom) throws GeometryInspectionException {
		switch (geom.getPrimitiveType()) {
			case Point: {
				inspect((Point) geom);
				break;
			}
			case Curve: {
				inspect((Curve) geom);
				break;
			}
			case Surface: {
				inspect((Surface) geom);
				break;
			}
			default:
				String msg = createMessage(
						format("XPlanGeometryInspector_invalid_unsupportedGeometry", geom.getPrimitiveType().name()));
				createError(msg);
		}
	}

	private void inspect(Point point) {
		if (point.getCoordinateDimension() != 2) {
			String msg = createMessage(
					format("XPlanGeometryInspector_invalid_punkt_dimension", point, point.getCoordinateDimension()));
			createError(msg);
		}
	}

	private void inspect(Curve geom) throws GeometryInspectionException {
		checkSegmentContinuity(geom);
		switch (geom.getCurveType()) {
			case Curve:
			case LineString: {
				break;
			}
			case Ring: {
				checkClosed((Ring) geom);
				checkSelfIntersection((Ring) geom);
				checkDuplicateControlPoints((Ring) geom);
				break;
			}
			default:
				String msg = createMessage(
						format("XPlanGeometryInspector_invalid_unsupportedGeometry", geom.getCurveType().name()));
				createError(msg);
		}
	}

	private void inspect(Surface geom) throws GeometryInspectionException {
		switch (geom.getSurfaceType()) {
			case Surface: {
				// nothing to do (all patches have been checked already)
				break;
			}
			case Polygon: {
				org.deegree.geometry.primitive.Polygon polygon = (org.deegree.geometry.primitive.Polygon) geom;
				PolygonPatch firstOriginalPatch = polygon.getPatches().get(0);
				inspect(firstOriginalPatch);
				break;
			}
			default:
				String msg = createMessage(
						format("XPlanGeometryInspector_invalid_unsupportedGeometry", geom.getSurfaceType().name()));
				createError(msg);
		}
	}

	private void inspect(PolygonPatch patch) {
		checkRingOrientations(patch);
		checkSelfIntersection(patch);
	}

	private void checkSegmentContinuity(Curve geom) {
		Point lastSegmentEndPoint = null;
		int segmentIdx = 0;
		for (CurveSegment segment : geom.getCurveSegments()) {
			Point startPoint = segment.getStartPoint();
			if (lastSegmentEndPoint != null) {
				if (startPoint.get0() != lastSegmentEndPoint.get0()
						|| startPoint.get1() != lastSegmentEndPoint.get1()) {
					String msg = createMessage(format("XPlanGeometryInspector_invalid_linie_luecke", segmentIdx,
							segmentIdx++, lastSegmentEndPoint, startPoint));
					createError(msg);
				}
			}
			segmentIdx++;
			lastSegmentEndPoint = segment.getEndPoint();
		}
	}

	void checkClosed(Ring ring) {
		Point startPoint = ring.getStartPoint();
		Point endPoint = ring.getEndPoint();
		if (!startPoint.equals(endPoint)) {
			double dist = startPoint.getDistance(endPoint, null).getValueAsDouble();
			String msg = createMessage(
					format("XPlanGeometryInspector_invalid_polygon_notClosed", startPoint, endPoint, dist));
			createError(msg);
		}
	}

	private void inspect(String multSurfaceId, AtomicInteger intersectionIndex, Surface surface1, Surface surface2) {
		org.locationtech.jts.geom.Geometry jtsGeometry1 = getJTSGeometry(surface1);
		org.locationtech.jts.geom.Geometry jtsGeometry2 = getJTSGeometry(surface2);
		if (jtsGeometry1 != null && jtsGeometry2 != null) {
			try {
				IntersectionMatrix relate = jtsGeometry1.relate(jtsGeometry2);
				if (relate.isContains()) {
					String error = createMessage(format("XPlanGeometryInspector_invalid_ueberdeckung_multipolygon",
							multSurfaceId, WKTWriter.write(surface2)));
					errors.add(error);
					badGeometries.add(new BadGeometry(surface2, error));
				}
				else if (relate.isCoveredBy()) {
					String error = createMessage(format("XPlanGeometryInspector_invalid_ueberdeckung_multipolygon",
							multSurfaceId, WKTWriter.write(surface1)));
					errors.add(error);
					badGeometries.add(new BadGeometry(surface1, error));
				}
				else if (relate.isIntersects()) {
					Geometry intersection = calculateSelfIntersectionOfExterior(surface1, surface2);
					if (hasIntersection(intersection)) {
						intersection.setId(multSurfaceId + "_intersection_" + intersectionIndex.getAndAdd(1));
						String error = createMessage(
								format("XPlanGeometryInspector_invalid_selbstueberschneidung_mulitpolygone",
										multSurfaceId, geometryAsReadableString(intersection)));
						errors.add(error);
						badGeometries.add(new BadGeometry(intersection, error));
					}
				}
			}
			catch (TopologyException e) {
				String error = format("XPlanGeometryInspector_error_multipolygon_invalidGeom", multSurfaceId);
				errors.add(error);
			}
		}
	}

	private Geometry calculateSelfIntersectionOfExterior(Surface surface1, Surface surface2) {
		if (Surface.SurfaceType.Polygon.equals(surface1.getSurfaceType())
				&& Surface.SurfaceType.Polygon.equals(surface2.getSurfaceType())) {
			org.deegree.geometry.primitive.Polygon polygon1 = (org.deegree.geometry.primitive.Polygon) surface1;
			org.deegree.geometry.primitive.Polygon polygon2 = (org.deegree.geometry.primitive.Polygon) surface2;
			return polygon1.getExteriorRing().getIntersection(polygon2.getExteriorRing());

		}
		return surface1.getIntersection(surface2);
	}

	private boolean hasIntersection(Geometry intersection) {
		// touching multi polygons (with a single point intersection) are allowed
		return intersection != null && !(intersection instanceof DefaultPoint);
	}

	void checkSelfIntersection(Ring ring) {
		LineString jtsLineString = jtsParser.getJTSLineString(ring);
		boolean selfIntersection = !jtsLineString.isSimple();
		if (selfIntersection) {
			List<Point> points = calculateIntersectionsAndAddError(ring, jtsLineString);
			String msg = createMessage(format("XPlanGeometryInspector_invalid_selbstueberschneidung",
					points.stream().map(this::pointAsReadableString).collect(Collectors.joining(","))));
			createError(msg);
		}
	}

	void checkDuplicateControlPoints(Ring ring) {
		List<Point> duplicateControlPoints = calculateDuplicateControlPointsAndAddErrors(ring);
		if (!duplicateControlPoints.isEmpty()) {
			String msg = createMessage(format("XPlanGeometryInspector_invalid_identischeStuetzpunkte",
					duplicateControlPoints.stream().map(this::pointAsReadableString).collect(Collectors.joining(","))));
			createError(msg);
		}
	}

	void checkSelfIntersection(PolygonPatch inspected) {
		try {
			Ring exteriorRing = inspected.getExteriorRing();
			LinearRing exteriorJTSRing = jtsParser.getJTSRing(exteriorRing);
			Polygon exteriorJTSRingAsPolygon = jtsParser.getJTSPolygon(exteriorJTSRing);

			List<Ring> interiorRings = inspected.getInteriorRings();
			List<LinearRing> interiorJTSRings = new ArrayList<>(interiorRings.size());
			List<Polygon> interiorJTSRingsAsPolygons = new ArrayList<>(interiorRings.size());
			for (Ring interiorRing : interiorRings) {
				LinearRing interiorJTSRing = jtsParser.getJTSRing(interiorRing);
				interiorJTSRings.add(interiorJTSRing);
				interiorJTSRingsAsPolygons.add(jtsParser.getJTSPolygon(interiorJTSRing));
			}

			LOG.debug("Validating spatial relations between exterior ring and interior rings.");
			for (int ringIdx = 0; ringIdx < interiorJTSRings.size(); ringIdx++) {
				LinearRing interiorJTSRing = interiorJTSRings.get(ringIdx);
				Polygon interiorJTSRingAsPolygon = interiorJTSRingsAsPolygons.get(ringIdx);
				checkSelfIntersectionOfExteriorAndInteriorRing(exteriorRing, exteriorJTSRing, exteriorJTSRingAsPolygon,
						interiorJTSRing, interiorJTSRingAsPolygon, ringIdx);
				ringIdx++;
			}

			LOG.debug("Validating spatial relations between pairs of interior rings.");
			for (int ring1Idx = 0; ring1Idx < interiorJTSRings.size(); ring1Idx++) {
				for (int ring2Idx = ring1Idx; ring2Idx < interiorJTSRings.size(); ring2Idx++) {
					if (ring1Idx == ring2Idx) {
						continue;
					}
					LinearRing interior1JTSRing = interiorJTSRings.get(ring1Idx);
					Polygon interior1JTSRingAsPolygon = interiorJTSRingsAsPolygons.get(ring1Idx);
					LinearRing interior2JTSRing = interiorJTSRings.get(ring2Idx);
					Polygon interior2JTSRingAsPolygon = interiorJTSRingsAsPolygons.get(ring2Idx);
					checkSelfIntersectionOfInteriorRings(interior1JTSRing, interior1JTSRingAsPolygon, interior2JTSRing,
							interior2JTSRingAsPolygon, exteriorRing.getCoordinateSystem(), ring1Idx, ring2Idx);
				}
			}
		}
		catch (Exception e) {
			String msg = createMessage(getMessage("XPlanGeometryInspector_error"));
			getErrors().add(msg); // don't use cm errors - mocking!
		}
	}

	private void checkSelfIntersectionOfExteriorAndInteriorRing(Ring exteriorRing, LinearRing exteriorJTSRing,
			Polygon exteriorJTSRingAsPolygon, LinearRing interiorJTSRing, Polygon interiorJTSRingAsPolygon,
			int ringIdx) {
		if (!interiorJTSRingAsPolygon.within(exteriorJTSRingAsPolygon)) {
			String msg = createMessage(format("XPlanGeometryInspector_invalid_poylgon_innererRingOuside", ringIdx));
			createError(msg);
			return;
		}
		if (exteriorJTSRing.intersects(interiorJTSRing)) {
			org.locationtech.jts.geom.Geometry intersection = exteriorJTSRing.intersection(interiorJTSRing);
			@SuppressWarnings("deprecation")
			AbstractDefaultGeometry intersectionGeom = DEFAULT_GEOM.createFromJTS(intersection,
					exteriorRing.getCoordinateSystem());
			if (hasIntersection(intersectionGeom)) {
				String msg = createMessage(
						format("XPlanGeometryInspector_invalid_polygon_intersection_interiorAndExterior", ringIdx));
				createError(msg);
				String intersectionMsg = format(
						"XPlanGeometryInspector_invalid_polygon_intersection_interiorAndExterior_schnittpunkte",
						ringIdx);
				badGeometries.add(new BadGeometry(intersectionGeom, intersectionMsg));
			}
		}
	}

	private void checkSelfIntersectionOfInteriorRings(LinearRing interior1JTSRing, Polygon interior1JTSRingAsPolygon,
			LinearRing interior2JTSRing, Polygon interior2JTSRingAsPolygon, ICRS crs, int ring1Idx, int ring2Idx) {
		if (interior1JTSRing.within(interior2JTSRingAsPolygon)) {
			String msg = createMessage(
					format("XPlanGeometryInspector_invalid_interiorRingInInteriorRing", ring1Idx, ring2Idx));
			createError(msg);
			return;
		}
		if (interior2JTSRing.within(interior1JTSRingAsPolygon)) {
			String msg = createMessage(
					format("XPlanGeometryInspector_invalid_interiorRingInInteriorRing", ring2Idx, ring1Idx));
			createError(msg);
			return;
		}
		if (interior1JTSRing.intersects(interior2JTSRing)) {
			org.locationtech.jts.geom.Geometry intersection = interior1JTSRing.intersection(interior2JTSRing);
			@SuppressWarnings("deprecation")
			AbstractDefaultGeometry intersectionGeom = DEFAULT_GEOM.createFromJTS(intersection, crs);
			if (hasIntersection(intersectionGeom)) {
				String msg = createMessage(
						format("XPlanGeometryInspector_invalid_intersection_interiorRings", ring1Idx, ring2Idx));
				createError(msg);
				String intersectionMsg = format(
						"XPlanGeometryInspector_invalid_intersection_interiorRings_schnittpunkte", ring1Idx, ring2Idx);
				badGeometries.add(new BadGeometry(intersectionGeom, intersectionMsg));
			}
		}
	}

	private List<Point> calculateIntersectionsAndAddError(Ring ring, LineString jtsLineString) {
		List<Point> selfInterSectionPoints = new ArrayList<>();
		GeometryGraph graph = new GeometryGraph(0, jtsLineString);
		LineIntersector lineIntersector = new RobustLineIntersector();
		graph.computeSelfNodes(lineIntersector, true);
		List<Coordinate> selfInterSectionCoords = new ArrayList<>();
		Iterator<?> edgeIterator = graph.getEdgeIterator();
		while (edgeIterator.hasNext()) {
			Edge edge = (Edge) edgeIterator.next();
			int maxSegmentIndex = edge.getMaximumSegmentIndex();
			Iterator<?> edgeIntersections = edge.getEdgeIntersectionList().iterator();
			while (edgeIntersections.hasNext()) {
				EdgeIntersection intersection = (EdgeIntersection) edgeIntersections.next();
				if (!intersection.isEndPoint(maxSegmentIndex)) {
					Coordinate coordinate = intersection.getCoordinate();
					if (!selfInterSectionCoords.contains(coordinate)) {
						selfInterSectionCoords.add(coordinate);
						String intersectionId = ring.getId() + "_intersection_" + selfInterSectionCoords.size();
						Point intersectionGeom = new GeometryFactory().createPoint(intersectionId, coordinate.x,
								coordinate.y, coordinate.z, ring.getCoordinateSystem());
						String intersectionMsg = getMessage("XPlanGeometryInspector_invalid_intersection_geom");
						badGeometries.add(new BadGeometry(intersectionGeom, intersectionMsg));
						selfInterSectionPoints.add(intersectionGeom);
					}
				}
			}
		}
		return selfInterSectionPoints;
	}

	private List<Point> calculateDuplicateControlPointsAndAddErrors(Ring ring) {
		List<Point> duplicateControlPoints = new ArrayList<>();
		AtomicInteger duplicateControlPointIndex = new AtomicInteger(1);

		List<CurveSegment> curveSegments = ring.getCurveSegments();
		curveSegments.stream()
			.filter(curveSegment -> LINE_STRING_SEGMENT.equals(curveSegment.getSegmentType()))
			.forEach(curveSegment -> {
				Points controlPoints = ((LineStringSegment) curveSegment).getControlPoints();
				AtomicReference<Point> previous = new AtomicReference<>(null);
				controlPoints.forEach(cp -> {
					if (previous.get() == null) {
						previous.set(cp);
					}
					else {
						if (cp.equals(previous.get())) {
							String duplicateControlPointId = ring.getId() + "_doppelterStuetzpunkt_"
									+ duplicateControlPointIndex.getAndIncrement();
							Point duplicateControlPointGeom = new GeometryFactory().createPoint(duplicateControlPointId,
									cp.get0(), cp.get1(), cp.get2(), ring.getCoordinateSystem());
							String duplicateControlPointMsg = getMessage(
									"XPlanGeometryInspector_invalid_duplicateStuetzpunkt_geom");
							badGeometries.add(new BadGeometry(duplicateControlPointGeom, duplicateControlPointMsg));
							duplicateControlPoints.add(duplicateControlPointGeom);
						}
						previous.set(cp);
					}
				});
			});
		return duplicateControlPoints;
	}

	private String geometryAsReadableString(Geometry geom) {
		switch (geom.getGeometryType()) {
			case PRIMITIVE_GEOMETRY:
				return primitiveAsReadableString((GeometricPrimitive) geom);
			case MULTI_GEOMETRY:
				return multipleAsReadableString((MultiGeometry<?>) geom);
			case COMPOSITE_GEOMETRY:
				return compositeAsReadableString((CompositeGeometry<?>) geom);
			default:
				return getMessage("XPlanGeometryInspector_exportGeomInvalid");
		}
	}

	private String primitiveAsReadableString(GeometricPrimitive geom) {
		switch (geom.getPrimitiveType()) {
			case Point:
				return pointAsReadableString((Point) geom);
			case Curve:
				return format("XPlanGeometryInspector_geomAsString_curve", ((Curve) geom).getStartPoint(),
						((Curve) geom).getEndPoint());
			default:
				return getMessage("XPlanGeometryInspector_exportGeomInvalid");
		}
	}

	private String pointAsReadableString(Point geom) {
		return Arrays.stream(geom.getAsArray())
			.filter(value -> !Double.isNaN(value))
			.mapToObj(Double::toString)
			.collect(Collectors.joining(",", "(", ")"));
	}

	private String multipleAsReadableString(MultiGeometry<?> geom) {
		StringBuilder sb = new StringBuilder();
		geom.stream().forEach(g -> {
			if (sb.length() > 0)
				sb.append(", ");
			sb.append(geometryAsReadableString((Geometry) g));
		});
		return sb.toString();
	}

	private String compositeAsReadableString(CompositeGeometry<?> geom) {
		StringBuilder sb = new StringBuilder();
		geom.stream().forEach(g -> {
			if (sb.length() > 0)
				sb.append(", ");
			sb.append(geometryAsReadableString((Geometry) g));
		});
		return sb.toString();
	}

	String createMessage(String msg) {
		String elementName = xmlStream.getName().getLocalPart();
		int lineNumber = xmlStream.getLocation().getLineNumber();
		int columnNumber = xmlStream.getLocation().getColumnNumber();
		return format("XPlanGeometryInspector_location", elementName, lineNumber, columnNumber, msg);
	}

	void createError(String msg) {
		errors.add(msg);
		if (lastBadGeometry == null) {
			lastBadGeometry = new BadGeometry();
		}
		lastBadGeometry.addError(msg);
	}

	void checkRingOrientations(PolygonPatch patch) {
		if (skipOrientation) {
			return;
		}
		checkOuterRing(patch.getExteriorRing());
		for (Ring interiorRing : patch.getInteriorRings()) {
			checkInnerRing(interiorRing);
		}
	}

	private void checkOuterRing(Ring ring) {
		LinearRing jTSRing = jtsParser.getJTSRing(ring);
		if (!Orientation.isCCW(jTSRing.getCoordinates())) {
			String msg = createMessage(getMessage("XPlanGeometryInspector_invalid_falscheLaufrichtung_aeussererRing"));
			errors.add(msg);
			badGeometries.add(new BadGeometry(ring, msg));
		}
	}

	private void checkInnerRing(Ring ring) {
		LinearRing jTSRing = jtsParser.getJTSRing(ring);
		if (Orientation.isCCW(jTSRing.getCoordinates())) {
			String msg = createMessage(getMessage("XPlanGeometryInspector_invalid_falscheLaufrichtung_innererRing"));
			errors.add(msg);
			badGeometries.add(new BadGeometry(ring, msg));
		}
	}

	private org.locationtech.jts.geom.Geometry getJTSGeometry(Surface surface) {
		if (surface instanceof AbstractDefaultGeometry)
			return ((AbstractDefaultGeometry) surface).getJTSGeometry();
		String msg = createMessage(
				format("XPlanGeometryInspector_error_unsupportedGeom", surface.getClass().getSimpleName()));
		createError(msg);
		return null;
	}

	private void addLastBadGeometry(Geometry inspected) {
		if (lastBadGeometry != null) {
			lastBadGeometry.setOriginalGeometry(inspected);
			badGeometries.add(lastBadGeometry);
			lastBadGeometry = null;
		}
	}

}
