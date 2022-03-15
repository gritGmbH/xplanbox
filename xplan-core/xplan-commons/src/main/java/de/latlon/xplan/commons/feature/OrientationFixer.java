package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.jts.JtsParser;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.GeometryFactory;
import org.deegree.geometry.GeometryInspectionException;
import org.deegree.geometry.GeometryInspector;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.points.Points;
import org.deegree.geometry.primitive.GeometricPrimitive;
import org.deegree.geometry.primitive.Polygon;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.Surface;
import org.deegree.geometry.primitive.patches.PolygonPatch;
import org.deegree.geometry.primitive.patches.SurfacePatch;
import org.deegree.geometry.primitive.segments.CurveSegment;
import org.deegree.geometry.standard.primitive.DefaultPolygon;
import org.deegree.geometry.standard.surfacepatches.DefaultPolygonPatch;
import org.deegree.geometry.validation.GeometryFixer;
import org.locationtech.jts.algorithm.Orientation;
import org.locationtech.jts.geom.LinearRing;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OrientationFixer implements GeometryInspector {

	private final JtsParser jtsParser = new JtsParser();

	@Override
	public Geometry inspect(Geometry geom) throws GeometryInspectionException {
		if (geom instanceof GeometricPrimitive) {
			return inspect((GeometricPrimitive) geom);
		}
		if (geom instanceof MultiGeometry) {
			return inspect((MultiGeometry) geom);
		}
		return geom;
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
		return patch;
	}

	@Override
	public Points inspect(Points points) {
		return points;
	}

	private MultiGeometry inspect(MultiGeometry geom) throws GeometryInspectionException {
		if (geom instanceof MultiSurface) {
			return inspect((MultiSurface) geom);
		}
		return geom;
	}

	private MultiSurface inspect(MultiSurface geom) throws GeometryInspectionException {
		List<Surface> surfaces = new ArrayList<>();
		for (Object surface : geom) {
			Surface inspect = inspect((Surface) surface);
			surfaces.add(inspect);
		}
		return new GeometryFactory().createMultiSurface(geom.getId(), geom.getCoordinateSystem(), surfaces);
	}

	private GeometricPrimitive inspect(GeometricPrimitive geom) throws GeometryInspectionException {
		if (geom instanceof Surface) {
			return inspect((Surface) geom);
		}
		return geom;
	}

	private Surface inspect(Surface geom) {
		if (geom instanceof Polygon) {
			org.deegree.geometry.primitive.Polygon polygon = (org.deegree.geometry.primitive.Polygon) geom;
			PolygonPatch firstOriginalPatch = polygon.getPatches().get(0);
			PolygonPatch inspectedPatch = inspect(firstOriginalPatch);
			if (inspectedPatch != firstOriginalPatch) {
				return new DefaultPolygon(geom.getId(), geom.getCoordinateSystem(), geom.getPrecision(),
						inspectedPatch.getExteriorRing(), inspectedPatch.getInteriorRings());
			}
		}
		return geom;
	}

	private PolygonPatch inspect(PolygonPatch patch) {
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
			return new DefaultPolygonPatch(exteriorRing, interiorRings);
		}
		return patch;
	}

	private Ring checkOuterRing(Ring ring) {
		LinearRing jTSRing = jtsParser.getJTSRing(ring);
		if (!Orientation.isCCW(jTSRing.getCoordinates())) {
			return GeometryFixer.invertOrientation(ring);
		}
		return ring;

	}

	private Ring checkInnerRing(Ring ring) {
		LinearRing jTSRing = jtsParser.getJTSRing(ring);
		if (Orientation.isCCW(jTSRing.getCoordinates())) {
			return GeometryFixer.invertOrientation(ring);
		}
		return ring;
	}

}
