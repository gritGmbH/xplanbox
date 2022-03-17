package de.latlon.xplan.commons.jts;

import org.deegree.geometry.GeometryFactory;
import org.deegree.geometry.linearization.CurveLinearizer;
import org.deegree.geometry.linearization.LinearizationCriterion;
import org.deegree.geometry.linearization.MaxErrorCriterion;
import org.deegree.geometry.primitive.Curve;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.segments.Arc;
import org.deegree.geometry.primitive.segments.Circle;
import org.deegree.geometry.primitive.segments.CurveSegment;
import org.deegree.geometry.primitive.segments.LineStringSegment;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import java.util.LinkedList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class JtsParser {

	private final org.locationtech.jts.geom.GeometryFactory jtsFactory;

	private final LinearizationCriterion crit;

	private final CurveLinearizer linearizer;

	public JtsParser() {
		this.jtsFactory = new org.locationtech.jts.geom.GeometryFactory();
		this.crit = new MaxErrorCriterion(1.0, 500);
		this.linearizer = new CurveLinearizer(new GeometryFactory());
	}

	/**
	 * Returns a JTS geometry for the given {@link Curve} (which is linearized first).
	 * @param curve {@link Curve} that consists of {@link LineStringSegment} and
	 * {@link Arc} segments only
	 * @return linear JTS curve geometry
	 * @throws IllegalArgumentException if the given input ring contains other segment
	 * types than {@link LineStringSegment}, {@link Arc} and {@link Circle}
	 */
	public LineString getJTSLineString(Curve curve) {
		Curve linearizedCurve = linearizer.linearize(curve, crit);
		List<Coordinate> coordinates = new LinkedList<>();
		Point lastPoint = null;
		for (CurveSegment segment : linearizedCurve.getCurveSegments()) {
			for (Point point : ((LineStringSegment) segment).getControlPoints()) {
				if (lastPoint != null && lastPoint.equals(point)) {
					// ignore to avoid duplicate points.
				}
				else {
					coordinates.add(new Coordinate(point.get0(), point.get1()));
				}
				lastPoint = point;
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
	public LinearRing getJTSRing(Ring ring) {

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

	public Polygon getJTSPolygon(LinearRing interiorJTSRing) {
		return jtsFactory.createPolygon(interiorJTSRing, null);
	}

}
