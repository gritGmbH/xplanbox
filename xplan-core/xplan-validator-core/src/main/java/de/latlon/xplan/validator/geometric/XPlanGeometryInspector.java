/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.validator.geometric;

import com.vividsolutions.jts.algorithm.CGAlgorithms;
import com.vividsolutions.jts.algorithm.LineIntersector;
import com.vividsolutions.jts.algorithm.RobustLineIntersector;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geomgraph.Edge;
import com.vividsolutions.jts.geomgraph.EdgeIntersection;
import com.vividsolutions.jts.geomgraph.EdgeIntersectionList;
import com.vividsolutions.jts.geomgraph.GeometryGraph;
import com.vividsolutions.jts.geomgraph.Node;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.GeometryFactory;
import org.deegree.geometry.GeometryInspectionException;
import org.deegree.geometry.GeometryInspector;
import org.deegree.geometry.composite.CompositeGeometry;
import org.deegree.geometry.linearization.CurveLinearizer;
import org.deegree.geometry.linearization.LinearizationCriterion;
import org.deegree.geometry.linearization.MaxErrorCriterion;
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
import org.deegree.geometry.primitive.segments.Arc;
import org.deegree.geometry.primitive.segments.Circle;
import org.deegree.geometry.primitive.segments.CurveSegment;
import org.deegree.geometry.primitive.segments.LineStringSegment;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.geometry.standard.primitive.DefaultPoint;
import org.deegree.geometry.standard.primitive.DefaultPolygon;
import org.deegree.geometry.standard.surfacepatches.DefaultPolygonPatch;
import org.deegree.geometry.validation.GeometryFixer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
 * <li>Polygon/PolygonPatch: Keine Berührung äußerer Ring / innere Ringe (Fehler)</li>
 * <li>Polygon/PolygonPatch: Kein Schnitt äußerer Ring / innere Ringe (Fehler)</li>
 * <li>Polygon/PolygonPatch: Innere Ringe liegen innerhalb der vom äußeren Ring
 * umschlossenen Fläche (Fehler)</li>
 * <li>Polygon/PolygonPatch: Keine Berührung innere Ringe / innere Ringe (Fehler)</li>
 * <li>Polygon/PolygonPatch: Schnittmenge der von zwei inneren Ringen gebildeten Flächen
 * ist leer (Fehler)</li>
 * <li>MultiPolygon: Keine Berührung zwischen einzelnen Polygonen (Fehler)</li>
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

	private static final String UNSUPPORTED_GEOMETRY_TYPE = "%s sind laut XPlanGML-Schema nicht erlaubt.";

	private static final String INVALID_MSG_IDENTISCHESTUETZPUNKTE = "2.2.2.1: Identische St\u00fctzpunkte: %s.";

	private static final String INVALID_MSG_SELBSTUEBERSCHNEIDUNG = "2.2.2.1: Selbst\u00fcberschneidung an folgenden Punkten: %s.";

	private static final String INVALID_MSG_SELBSTUEBERSCHNEIDUNG_MULTIPOLYGON = "2.2.2.1: Selbst\u00fcberschneidung zwischen MulitPolygonen an folgenden Punkten: %s";

	private final com.vividsolutions.jts.geom.GeometryFactory jtsFactory;

	private final LinearizationCriterion crit;

	private final CurveLinearizer linearizer;

	private final XMLStreamReaderWrapper xmlStream;

	private final List<String> errors = new ArrayList<>();

	private final List<String> warnings = new ArrayList<>();

	private final List<BadGeometry> badGeometries = new ArrayList<>();

	private BadGeometry lastBadGeometry;

	public XPlanGeometryInspector(XMLStreamReaderWrapper xmlStream) {
		this.xmlStream = xmlStream;
		this.crit = new MaxErrorCriterion(1.0, 500);
		this.linearizer = new CurveLinearizer(new GeometryFactory());
		this.jtsFactory = new com.vividsolutions.jts.geom.GeometryFactory();
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
		Geometry inspected = geom;
		try {
			switch (inspected.getGeometryType()) {
			case PRIMITIVE_GEOMETRY: {
				inspected = inspect((GeometricPrimitive) inspected);
				break;
			}
			case MULTI_GEOMETRY: {
				inspected = inspect((MultiGeometry) inspected);
				break;
			}
			case COMPOSITE_GEOMETRY: {
				String msg = createMessage(String.format(UNSUPPORTED_GEOMETRY_TYPE, "Composites"));
				createError(msg);
			}
			default:
				LOG.warn("Unsupported geometry type (will be ignored) {}", inspected.getGeometryType());
			}
		}
		catch (Exception e) {
			LOG.trace("Fehler bei der geometrischen Validierung!", e);
			String message = createMessage(e.getMessage());
			createError(message);
		}
		addLastBadGeometry(inspected);

		return inspected;
	}

	@Override
	public CurveSegment inspect(CurveSegment segment) throws GeometryInspectionException {
		return segment;
	}

	@Override
	public SurfacePatch inspect(SurfacePatch patch) throws GeometryInspectionException {
		if (patch instanceof PolygonPatch) {
			return inspect((PolygonPatch) patch);
		}
		String msg = createMessage(String.format(UNSUPPORTED_GEOMETRY_TYPE, "Nicht-planare Patches"));
		createError(msg);
		return patch;
	}

	@Override
	public Points inspect(Points points) throws GeometryInspectionException {
		if (points.getDimension() != 2) {
			String msg = createMessage(String.format(
					"Punkteliste mit ung\u00fcltiger Dimensionalit\u00e4t (%d). Nur 2D Koordinaten sind erlaubt.",
					points.getDimension()));
			createError(msg);
		}
		return points;
	}

	private MultiGeometry inspect(MultiGeometry geom) throws GeometryInspectionException {
		switch (geom.getMultiGeometryType()) {
		case MULTI_POINT:
		case MULTI_CURVE:
			break;
		case MULTI_SURFACE:
			inspect((MultiSurface) geom);
		}
		return geom;
	}

	private void inspect(MultiSurface geom) throws GeometryInspectionException {
		List<Geometry> selfIntersections = calulateSelfIntersection(geom);
		if (!selfIntersections.isEmpty()) {
			String msg = createMessage(String.format(INVALID_MSG_SELBSTUEBERSCHNEIDUNG_MULTIPOLYGON,
					geometriesAsReadableString(selfIntersections)));
			createError(msg);
		}
	}

	private GeometricPrimitive inspect(GeometricPrimitive geom) throws GeometryInspectionException {
		switch (geom.getPrimitiveType()) {
		case Point: {
			return inspect((Point) geom);
		}
		case Curve: {
			return inspect((Curve) geom);
		}
		case Surface: {
			return inspect((Surface) geom);
		}
		default:
			String msg = createMessage(String.format(UNSUPPORTED_GEOMETRY_TYPE, geom.getPrimitiveType().name()));
			createError(msg);
		}
		return geom;
	}

	private Point inspect(Point point) {
		if (point.getCoordinateDimension() != 2) {
			String msg = createMessage(String.format(
					"Punkt (=%s) mit ung\u00fcltiger Dimensionalit\u00e4t (%d). Nur 2D Koordinaten sind erlaubt.",
					point, point.getCoordinateDimension()));
			createError(msg);
		}
		return point;
	}

	private Curve inspect(Curve geom) throws GeometryInspectionException {
		Curve inspected = checkSegmentContinuity(geom);
		switch (inspected.getCurveType()) {
		case Curve:
		case LineString: {
			break;
		}
		case Ring: {
			inspected = checkClosed((Ring) inspected);
			checkSelfIntersection((Ring) inspected);
			checkDuplicateControlPoints((Ring) inspected);
			break;
		}
		default:
			String msg = createMessage(String.format(UNSUPPORTED_GEOMETRY_TYPE, inspected.getCurveType().name()));
			createError(msg);
		}
		return inspected;
	}

	private Surface inspect(Surface geom) throws GeometryInspectionException {
		switch (geom.getSurfaceType()) {
		case Surface: {
			// nothing to do (all patches have been checked already)
			break;
		}
		case Polygon: {
			org.deegree.geometry.primitive.Polygon polygon = (org.deegree.geometry.primitive.Polygon) geom;
			PolygonPatch firstOriginalPatch = polygon.getPatches().get(0);
			PolygonPatch inspectedPatch = inspect(firstOriginalPatch);
			if (inspectedPatch != firstOriginalPatch) {
				return new DefaultPolygon(geom.getId(), geom.getCoordinateSystem(), geom.getPrecision(),
						inspectedPatch.getExteriorRing(), inspectedPatch.getInteriorRings());
			}
			break;
		}
		default:
			String msg = createMessage(String.format(UNSUPPORTED_GEOMETRY_TYPE, geom.getSurfaceType().name()));
			createError(msg);
		}
		return geom;
	}

	private PolygonPatch inspect(PolygonPatch patch) {
		PolygonPatch inspected = checkRingOrientations(patch);
		checkSelfIntersection(inspected);
		return inspected;
	}

	private Curve checkSegmentContinuity(Curve geom) {
		Point lastSegmentEndPoint = null;
		int segmentIdx = 0;
		for (CurveSegment segment : geom.getCurveSegments()) {
			Point startPoint = segment.getStartPoint();
			if (lastSegmentEndPoint != null) {
				if (startPoint.get0() != lastSegmentEndPoint.get0()
						|| startPoint.get1() != lastSegmentEndPoint.get1()) {
					String msg = createMessage(String.format("L\u00fccke zwischen Kurvensegment %d und %d: %s != %s",
							segmentIdx, segmentIdx++, lastSegmentEndPoint, startPoint));
					createError(msg);
				}
			}
			segmentIdx++;
			lastSegmentEndPoint = segment.getEndPoint();
		}
		return geom;
	}

	Ring checkClosed(Ring ring) {
		Point startPoint = ring.getStartPoint();
		Point endPoint = ring.getEndPoint();
		if (!startPoint.equals(endPoint)) {
			double dist = startPoint.getDistance(endPoint, null).getValueAsDouble();
			String msg = createMessage(String.format("2.2.2.1: Ring nicht geschlossen: %s != %s, Abstand: %s",
					startPoint, endPoint, dist));
			createError(msg);
		}
		return ring;
	}

	private List<Geometry> calulateSelfIntersection(MultiSurface geom) {
		List<Geometry> selfIntersections = new ArrayList<>();
		List<Object> surfaces = new ArrayList<>(geom);
		int intersectionIndex = 1;
		for (Object curve1 : geom) {
			surfaces.remove(curve1);
			Surface surface1 = (Surface) curve1;
			for (Object curve2 : surfaces) {
				Surface surface2 = inspect((Surface) curve2);
				Geometry intersection = calculateSelfIntersection(surface1, surface2);
				if (intersection != null) {
					selfIntersections.add(intersection);
					intersection.setId(geom.getId() + "_intersection_" + intersectionIndex++);
					String intersectionMultiGeomMsg = "Geometrie der Selbst\u00fcberschneidung zwischen MulttPolygonen";
					badGeometries.add(new BadGeometry(intersection, intersectionMultiGeomMsg));
				}
			}
		}
		return selfIntersections;
	}

	private Geometry calculateSelfIntersection(Surface surface1, Surface surface2) {
		if (Surface.SurfaceType.Polygon.equals(surface1.getSurfaceType())
				&& Surface.SurfaceType.Polygon.equals(surface2.getSurfaceType())) {
			org.deegree.geometry.primitive.Polygon polygon1 = (org.deegree.geometry.primitive.Polygon) surface1;
			org.deegree.geometry.primitive.Polygon polygon2 = (org.deegree.geometry.primitive.Polygon) surface2;
			return polygon1.getExteriorRing().getIntersection(polygon2.getExteriorRing());

		}
		return surface1.getIntersection(surface2);
	}

	void checkSelfIntersection(Ring ring) {
		LineString jtsLineString = getJTSLineString(ring);
		boolean selfIntersection = !jtsLineString.isSimple();
		if (selfIntersection) {
			List<Point> points = calculateIntersectionsAndAddError(ring, jtsLineString);
			String msg = createMessage(String.format(INVALID_MSG_SELBSTUEBERSCHNEIDUNG,
					points.stream().map(this::pointAsReadableString).collect(Collectors.joining(","))));
			createError(msg);
		}
	}

	void checkDuplicateControlPoints(Ring ring) {
		List<Point> duplicateControlPoints = calculateDuplicateControlPointsAndAddErrors(ring);
		if (!duplicateControlPoints.isEmpty()) {
			String msg = createMessage(String.format(INVALID_MSG_IDENTISCHESTUETZPUNKTE,
					duplicateControlPoints.stream().map(this::pointAsReadableString).collect(Collectors.joining(","))));
			createError(msg);
		}
	}

	void checkSelfIntersection(PolygonPatch inspected) {
		try {
			Ring exteriorRing = inspected.getExteriorRing();
			LinearRing exteriorJTSRing = getJTSRing(exteriorRing);
			Polygon exteriorJTSRingAsPolygon = jtsFactory.createPolygon(exteriorJTSRing, null);

			List<Ring> interiorRings = inspected.getInteriorRings();
			List<LinearRing> interiorJTSRings = new ArrayList<>(interiorRings.size());
			List<Polygon> interiorJTSRingsAsPolygons = new ArrayList<>(interiorRings.size());
			for (Ring interiorRing : interiorRings) {
				LinearRing interiorJTSRing = getJTSRing(interiorRing);
				interiorJTSRings.add(interiorJTSRing);
				interiorJTSRingsAsPolygons.add(jtsFactory.createPolygon(interiorJTSRing, null));
			}

			LOG.debug("Validating spatial relations between exterior ring and interior rings.");
			for (int ringIdx = 0; ringIdx < interiorJTSRings.size(); ringIdx++) {
				LinearRing interiorJTSRing = interiorJTSRings.get(ringIdx);
				interiorJTSRingsAsPolygons.get(ringIdx);
				if (exteriorJTSRing.touches(interiorJTSRing)) {
					String msg = createMessage(String.format(
							"2.2.2.1: \u00c4u\u00dferer Ring ber\u00fchrt den inneren Ring mit Index %d.", ringIdx));
					createError(msg);
					String intersectionMsg = String.format(
							"Ber\u00fchrung(en) des \u00c4u\u00dferer Ring mit dem inneren Ring mit Index %d.",
							ringIdx);
					calculateIntersectionAndAddError(exteriorJTSRing, interiorJTSRing,
							exteriorRing.getCoordinateSystem(), intersectionMsg);
				}
				if (exteriorJTSRing.intersects(interiorJTSRing)) {
					String msg = createMessage(String.format(
							"2.2.2.1: \u00c4u\u00dferer Ring schneidet den inneren Ring mit Index %d.", ringIdx));
					createError(msg);
					String intersectionMsg = String.format(
							"Schnittpunkt(e) des \u00c4u\u00dferer Ring mit dem inneren Ring mit Index %d.", ringIdx);
					calculateIntersectionAndAddError(exteriorJTSRing, interiorJTSRing,
							exteriorRing.getCoordinateSystem(), intersectionMsg);
				}
				if (!interiorJTSRing.within(exteriorJTSRingAsPolygon)) {
					String msg = createMessage(String.format(
							"2.2.2.1: Innerer Ring mit Index %d befindet sich nicht innerhalb des \u00e4u\u00dferen Rings.",
							ringIdx));
					createError(msg);
				}
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
					if (interior1JTSRing.touches(interior2JTSRing)) {
						String msg = createMessage(
								String.format("2.2.2.1: Der innere Ring %d ber\u00fchrt den inneren Ring mit Index %d.",
										ring1Idx, ring2Idx));
						createError(msg);
					}
					if (interior1JTSRing.intersects(interior2JTSRing)) {
						LOG.debug("Interior intersects interior.");
						String msg = createMessage(
								String.format("2.2.2.1: Der innere Ring %d schneidet den inneren Ring mit Index %d.",
										ring1Idx, ring2Idx));
						createError(msg);
					}
					if (interior1JTSRing.within(interior2JTSRingAsPolygon)) {
						String msg = createMessage(String.format(
								"2.2.2.1: Der innere Ring %d liegt innerhalb des inneren Rings mit Index %d.", ring1Idx,
								ring2Idx));
						createError(msg);
					}
					if (interior2JTSRing.within(interior1JTSRingAsPolygon)) {
						String msg = createMessage(String.format(
								"2.2.2.1: Der innere Ring %d liegt innerhalb des inneren Rings mit Index %d.", ring2Idx,
								ring1Idx));
						createError(msg);
					}
				}
			}
		}
		catch (Exception e) {
			String msg = createMessage("Validierung der Oberfl\u00e4chen-Topologie fehlgeschlagen (Folgefehler!?).");
			getErrors().add(msg); // don't use cm errors - mocking!
		}
	}

	private void calculateIntersectionAndAddError(LinearRing exteriorJTSRing, LinearRing interiorJTSRing, ICRS crs,
			String msg) {
		com.vividsolutions.jts.geom.Geometry intersection = exteriorJTSRing.intersection(interiorJTSRing);
		AbstractDefaultGeometry intersectionGeom = DEFAULT_GEOM.createFromJTS(intersection, crs);
		badGeometries.add(new BadGeometry(intersectionGeom, msg));
	}

	private List<Point> calculateIntersectionsAndAddError(Ring ring, LineString jtsLineString) {
		List<Point> selfInterSectionPoints = new ArrayList<>();
		GeometryGraph graph = new GeometryGraph(0, jtsLineString);
		LineIntersector lineIntersector = new RobustLineIntersector();
		graph.computeSelfNodes(lineIntersector, true);
		List<Coordinate> selfInterSectionCoords = new ArrayList<>();
		Iterator edgeIterator = graph.getEdgeIterator();
		while (edgeIterator.hasNext()) {
			Edge edge = (Edge) edgeIterator.next();
			int maxSegmentIndex = edge.getMaximumSegmentIndex();
			Iterator edgeIntersections = edge.getEdgeIntersectionList().iterator();
			while (edgeIntersections.hasNext()) {
				EdgeIntersection intersection = (EdgeIntersection) edgeIntersections.next();
				if (!intersection.isEndPoint(maxSegmentIndex)) {
					Coordinate coordinate = intersection.getCoordinate();
					if (!selfInterSectionCoords.contains(coordinate)) {
						selfInterSectionCoords.add(coordinate);
						String intersectionId = ring.getId() + "_intersection_" + selfInterSectionCoords.size();
						Point intersectionGeom = new GeometryFactory().createPoint(intersectionId, coordinate.x,
								coordinate.y, coordinate.z, ring.getCoordinateSystem());
						String intersectionMsg = "Geomerie der Selbst\u00fcberschneidung";
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
		curveSegments.stream().filter(curveSegment -> LINE_STRING_SEGMENT.equals(curveSegment.getSegmentType()))
				.forEach(curveSegment -> {
					Points controlPoints = ((LineStringSegment) curveSegment).getControlPoints();
					AtomicReference<Point> previous = new AtomicReference(null);
					controlPoints.forEach(cp -> {
						if (previous.get() == null) {
							previous.set(cp);
						}
						else {
							if (cp.equals(previous.get())) {
								String duplicateControlPointId = ring.getId() + "_doppelterStuetzpunkt_"
										+ duplicateControlPointIndex.getAndIncrement();
								Point duplicateControlPointGeom = new GeometryFactory().createPoint(
										duplicateControlPointId, cp.get0(), cp.get1(), cp.get2(),
										ring.getCoordinateSystem());
								String duplicateControlPointMsg = "Doppelter St\u00fctzpunkte";
								badGeometries.add(new BadGeometry(duplicateControlPointGeom, duplicateControlPointMsg));
								duplicateControlPoints.add(duplicateControlPointGeom);
							}
							previous.set(cp);
						}
					});
				});
		return duplicateControlPoints;
	}

	private String geometriesAsReadableString(List<Geometry> selfIntersections) {
		return selfIntersections.stream().map(this::geometryAsReadableString).collect(Collectors.joining(","));
	}

	private String geometryAsReadableString(Geometry geom) {
		switch (geom.getGeometryType()) {
		case PRIMITIVE_GEOMETRY:
			return primitiveAsReadableString((GeometricPrimitive) geom);
		case MULTI_GEOMETRY:
			return multipleAsReadableString((MultiGeometry) geom);
		case COMPOSITE_GEOMETRY:
			return compositeAsReadableString((CompositeGeometry) geom);
		}
		return "Ausgabe nicht moeglich.";
	}

	private String primitiveAsReadableString(GeometricPrimitive geom) {
		switch (geom.getPrimitiveType()) {
		case Point:
			return pointAsReadableString((Point) geom);
		case Curve:
			return "Startpunkt: " + ((Curve) geom).getStartPoint() + "Endpunkt: " + ((Curve) geom).getEndPoint();
		}
		return "Ausgabe nicht moeglich.";
	}

	private String pointAsReadableString(Point geom) {
		return Arrays.stream(geom.getAsArray()).filter(value -> !Double.isNaN(value)).mapToObj(Double::toString)
				.collect(Collectors.joining(",", "(", ")"));
	}

	private String multipleAsReadableString(MultiGeometry geom) {
		StringBuilder sb = new StringBuilder();
		geom.stream().forEach(g -> {
			if (sb.length() > 0)
				sb.append(", ");
			sb.append(geometryAsReadableString((Geometry) g));
		});
		return sb.toString();
	}

	private String compositeAsReadableString(CompositeGeometry geom) {
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
		return String.format("%s (Zeile %d, Spalte %d): %s", elementName, lineNumber, columnNumber, msg);
	}

	void createError(String msg) {
		errors.add(msg);
		if (lastBadGeometry == null) {
			lastBadGeometry = new BadGeometry();
		}
		lastBadGeometry.addError(msg);
	}

	PolygonPatch checkRingOrientations(PolygonPatch patch) {

		PolygonPatch inspected = patch;
		boolean needsRebuild = false;

		Ring exteriorRing = checkOuterRing(inspected.getExteriorRing());
		if (exteriorRing != inspected.getExteriorRing()) {
			needsRebuild = true;
		}

		List<Ring> interiorRings = new ArrayList<>(patch.getInteriorRings().size());
		for (Ring interiorRing : patch.getInteriorRings()) {
			Ring newInteriorRings = checkInnerRing(interiorRing);
			interiorRings.add(newInteriorRings);
			if (interiorRing != newInteriorRings) {
				needsRebuild = true;
			}
		}

		if (needsRebuild) {
			inspected = new DefaultPolygonPatch(exteriorRing, interiorRings);
		}

		return inspected;
	}

	private Ring checkOuterRing(Ring ring) {
		Ring inspected = ring;
		LinearRing jTSRing = getJTSRing(inspected);
		if (!CGAlgorithms.isCCW(jTSRing.getCoordinates())) {
			String msg = createMessage("2.2.2.1: \u00c4u\u00dferer Ring verwendet falsche Laufrichtung (CW).");
			warnings.add(msg);
			inspected = GeometryFixer.invertOrientation(ring);
		}
		return inspected;
	}

	private Ring checkInnerRing(Ring ring) {
		Ring inspected = ring;
		LinearRing jTSRing = getJTSRing(inspected);
		if (CGAlgorithms.isCCW(jTSRing.getCoordinates())) {
			String msg = createMessage("2.2.2.1: Innerer Ring verwendet falsche Laufrichtung (CCW).");
			warnings.add(msg);
			inspected = GeometryFixer.invertOrientation(ring);
		}
		return inspected;
	}

	/**
	 * Returns a JTS geometry for the given {@link Curve} (which is linearized first).
	 * @param curve {@link Curve} that consists of {@link LineStringSegment} and
	 * {@link Arc} segments only
	 * @return linear JTS curve geometry
	 * @throws IllegalArgumentException if the given input ring contains other segment
	 * types than {@link LineStringSegment}, {@link Arc} and {@link Circle}
	 */
	private LineString getJTSLineString(Curve curve) {
		Curve linearizedCurve = linearizer.linearize(curve, crit);
		List<Coordinate> coordinates = new LinkedList<>();
		for (CurveSegment segment : linearizedCurve.getCurveSegments()) {
			for (Point point : ((LineStringSegment) segment).getControlPoints()) {
				coordinates.add(new Coordinate(point.get0(), point.get1()));
			}
		}
		return jtsFactory.createLineString(coordinates.toArray(new Coordinate[coordinates.size()]));
	}

	/**
	 * Returns a JTS geometry for the given {@link Ring} (which is linearized first).
	 * @param ring {@link Ring} that consists of {@link LineStringSegment}, {@link Arc}
	 * and {@link Circle} segments only
	 * @return linear JTS ring geometry, null if no
	 * @throws IllegalArgumentException if the given input ring contains other segment
	 * types than {@link LineStringSegment}, {@link Arc} and {@link Circle}
	 */
	private LinearRing getJTSRing(Ring ring) {

		Ring linearizedRing = (Ring) linearizer.linearize(ring, crit);
		List<Coordinate> coordinates = new LinkedList<Coordinate>();
		for (Curve member : linearizedRing.getMembers()) {
			for (CurveSegment segment : member.getCurveSegments()) {
				for (Point point : ((LineStringSegment) segment).getControlPoints()) {
					coordinates.add(new Coordinate(point.get0(), point.get1()));
				}
			}
		}
		return jtsFactory.createLinearRing(coordinates.toArray(new Coordinate[coordinates.size()]));
	}

	private void addLastBadGeometry(Geometry inspected) {
		if (lastBadGeometry != null) {
			lastBadGeometry.setOriginalGeometry(inspected);
			badGeometries.add(lastBadGeometry);
			lastBadGeometry = null;
		}
	}

}
