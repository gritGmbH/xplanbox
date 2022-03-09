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
package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.inspector.GeometricFeatureInspector;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.utils.Pair;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.Feature;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.i18n.Messages;
import org.deegree.geometry.io.WKTWriter;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.multi.MultiLineString;
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
import org.deegree.geometry.primitive.segments.ArcByBulge;
import org.deegree.geometry.primitive.segments.ArcString;
import org.deegree.geometry.primitive.segments.ArcStringByBulge;
import org.deegree.geometry.primitive.segments.BSpline;
import org.deegree.geometry.primitive.segments.Bezier;
import org.deegree.geometry.primitive.segments.Circle;
import org.deegree.geometry.primitive.segments.CubicSpline;
import org.deegree.geometry.primitive.segments.CurveSegment;
import org.deegree.geometry.primitive.segments.Geodesic;
import org.deegree.geometry.primitive.segments.GeodesicString;
import org.deegree.geometry.primitive.segments.LineStringSegment;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.geometry.standard.primitive.DefaultPoint;
import org.deegree.geometry.standard.primitive.DefaultSurface;
import org.deegree.gml.feature.FeatureInspectionException;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;

/**
 * Inspector for 2.2.1 Flaechenschlussbedingung:
 *
 * <ul>
 * <li>Bei allen Objekten mit flaechenhaftem Raumbezug muss das Attribut flaechenschluss
 * belegt sein.</li>
 * <li>Alle Objekte mit flaechenhaftem Raumbezug, die zur Ebene 0 (ebene == 0) gehoeren
 * und bei denen das Attribut flaechenschluss den Wert true hat (Flaechenschlussobjekte),
 * muessen die Flaechenschlussbedingung erfuellen. Das bedeutet, dass sich die jeweiligen
 * Flaechen nicht ueberlappen, sondern nur an gemeinsamen Raendern beruehren duerfen und
 * jeweils identische Stuetzpunkte aufweisen muessen. Zwei Punktkoordinaten werden als
 * identisch betrachtet wenn ihr euklidischer Abstand kleiner als 2 mm ist.</li>
 * <li>Von der Erfuellung der Flaechenschlussbedingung ausgenommen sind die raumbezogenen
 * Objekte des BPlan-Schemas (s. Kap. 0), deren Wirksamkeit durch sachliche oder zeitliche
 * Bedingungen (Attribute startBedingung und endeBedingung in BP_Objekt) so eingeschraenkt
 * sind, dass sie nicht gleichzeitig rechtswirksam sind.</li>
 * </ul>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OptimisedFlaechenschlussInspector implements GeometricFeatureInspector {

	private static final Logger LOG = LoggerFactory.getLogger(OptimisedFlaechenschlussInspector.class);

	private static final AbstractDefaultGeometry DEFAULT_GEOM = new DefaultPoint(null, null, null,
			new double[] { 0.0, 0.0 });

	private static final String ERROR_MSG = "2.2.1.1: Das Flaechenschlussobjekt mit der gml id %s erfuellt die Flaechenschlussbedingung an folgender Stelle nicht: %s";

	private static final String WARNINGERROR_MSG = "2.2.1.1: Das Flaechenschlussobjekt mit der gml id %s koennte die Flaechenschlussbedingung an folgender Stelle nicht erfuellen: %s";

	private final List<FlaechenschlussFeature> flaechenschlussFeatures = new ArrayList<>();

	private final List<String> flaechenschlussErrors = new ArrayList<>();

	private final List<String> flaechenschlussWarnings = new ArrayList<>();

	private final List<BadGeometry> badGeometries = new ArrayList<>();

	private final XPlanVersion xPlanVersion;

	private FlaechenschlussFeatureInspector flaechenschlussFeatureInspector = new FlaechenschlussFeatureInspector();

	public OptimisedFlaechenschlussInspector(XPlanVersion xPlanVersion) {
		this.xPlanVersion = xPlanVersion;
	}

	@Override
	public Feature inspect(Feature feature) throws FeatureInspectionException {
		if (flaechenschlussFeatureInspector.isFlaechenschlussobjekt(feature)) {
			flaechenschlussFeatures.add(new FlaechenschlussFeature(feature));
		}
		return feature;
	}

	/**
	 * Checks if the Flaechenschlussbedingung is satisfied: Das bedeutet, dass sich die
	 * jeweiligen Flaechen nicht ueberlappen, sondern nur an gemeinsamen Raendern
	 * beruehren duerfen und jeweils identische Stuetzpunkte aufweisen muessen. Zwei
	 * Punktkoordinaten werden als identisch betrachtet wenn ihr euklidischer Abstand
	 * kleiner als 2 mm ist.
	 * @return <code>true</code> if the Flaechenschlussbedingung is satisfied,
	 * <code>false</code> otherwise
	 */
	@Override
	public boolean checkGeometricRule() {
		analyseFlaechenschlussFeaturePairs();
		analyseFlaechenschlussUnion();

		if (flaechenschlussErrors.isEmpty()) {
			LOG.info("No features with invalid flaechenschluss");
		}
		else {
			LOG.info("Features with invalid flaechenschluss:\n {}",
					flaechenschlussErrors.stream().collect(Collectors.joining("\n")));
		}
		return flaechenschlussErrors.isEmpty();
	}

	@Override
	public List<String> getErrors() {
		return flaechenschlussErrors;
	}

	@Override
	public List<String> getWarnings() {
		return flaechenschlussWarnings;
	}

	@Override
	public List<BadGeometry> getBadGeometries() {
		return badGeometries;
	}

	private void analyseFlaechenschlussFeaturePairs() {
		List<Pair<FlaechenschlussFeature, FlaechenschlussFeature>> flaechenschlussFeaturePairs = detectFlaechenschlussFeaturePairsToAnalyse();
		LOG.debug("Found {} intersecting flaechenschluss features.", flaechenschlussFeaturePairs.size());
		flaechenschlussFeaturePairs
				.forEach(flaechenschlussFeaturePair -> analyseFlaechenschlussFeaturePair(flaechenschlussFeaturePair));
	}

	private void analyseFlaechenschlussUnion() {
		boolean handleHolesAsFailure = handleAsFailure();
		Geometry flaechenschlussUnion = createFlaechenschlussUnion();
		LOG.debug("Union of all flaechenschluss geometries: " + WKTWriter.write(flaechenschlussUnion));
		checkFlaechenschlussFeaturesIntersectingAnInteriorRing(flaechenschlussUnion, handleHolesAsFailure);
	}

	private void checkFlaechenschlussFeaturesIntersectingAnInteriorRing(Geometry flaechenschlussUnion,
			boolean handleHolesAsFailure) {
		if (flaechenschlussUnion == null)
			return;
		if (flaechenschlussUnion instanceof DefaultSurface) {
			// Simple Polygon without interior ring must be valid!
			if (((DefaultSurface) flaechenschlussUnion).getInteriorRingsCoordinates().isEmpty())
				return;
			checkFlaechenschlussFeaturesIntersectingAnInteriorRing((DefaultSurface) flaechenschlussUnion,
					handleHolesAsFailure);
		}
		else if (flaechenschlussUnion instanceof MultiSurface) {
			checkFlaechenschlussFeaturesIntersectingAnInteriorRing((MultiSurface) flaechenschlussUnion,
					handleHolesAsFailure);
		}
	}

	private void checkFlaechenschlussFeaturesIntersectingAnInteriorRing(MultiSurface<Surface> flaechenschlussUnion,
			boolean handleHolesAsFailure) {
		for (Surface flaechenschlussUnionGeom : flaechenschlussUnion) {
			if (flaechenschlussUnionGeom instanceof DefaultSurface) {
				checkFlaechenschlussFeaturesIntersectingAnInteriorRing((DefaultSurface) flaechenschlussUnionGeom,
						handleHolesAsFailure);
			}
			else {
				LOG.warn("Could not handle surface of type " + flaechenschlussUnionGeom.getClass());
			}
		}
	}

	private void checkFlaechenschlussFeaturesIntersectingAnInteriorRing(DefaultSurface flaechenschlussUnion,
			boolean handleHolesAsFailure) {
		List<? extends SurfacePatch> patches = flaechenschlussUnion.getPatches();
		PolygonPatch patch = (PolygonPatch) patches.get(0);
		List<Ring> interiorRings = patch.getInteriorRings();
		for (Ring interiorRing : interiorRings) {
			List<FlaechenschlussFeature> intersectingFlaechenschlussFeatures = collectFlaechenschlussFeaturesIntersectingTheInteriorRing(
					interiorRing);
			LOG.debug("Analyse flaechenschluss features intersection {}", WKTWriter.write(interiorRing));
			LOG.debug(
					"Flaechenschluss Features: \n  " + intersectingFlaechenschlussFeatures.stream()
							.map(flaechenschlussFeature -> flaechenschlussFeature.getFeatureId() + "("
									+ flaechenschlussFeature.getFeatureType() + ")")
							.collect(Collectors.joining("\n  ")));
			List<ControlPoint> controlPointsInIntersection = collectControlPointsIntersectingTheInteriorRing(
					interiorRing, intersectingFlaechenschlussFeatures);
			checkControlPointsAndAddFailures(controlPointsInIntersection, handleHolesAsFailure);
		}
	}

	private void checkControlPointsAndAddFailures(List<ControlPoint> controlPoints, boolean handleHolesAsFailure) {
		List<ControlPoint> controlPointsInIntersectionTmp = new ArrayList<>(controlPoints);
		controlPoints.forEach(cpToCheck -> {
			controlPointsInIntersectionTmp.remove(cpToCheck);
			controlPointsInIntersectionTmp.forEach(cpInIntersection2 -> cpToCheck.checkIfIdentical(cpInIntersection2));
		});
		List<ControlPoint> controlPointsWithInvalidFlaechenschluss = controlPoints.stream()
				.filter(cp -> !cp.hasIdenticalControlPoint()).collect(Collectors.toList());
		controlPointsWithInvalidFlaechenschluss.stream().forEach(cp -> {
			if (handleHolesAsFailure) {
				String error = String.format(ERROR_MSG, cp.getFeatureGmlId(), cp.getPoint());
				BadGeometry badGeometry = new BadGeometry(cp.getPoint(), error);
				badGeometries.add(badGeometry);
				flaechenschlussErrors.add(error);
			}
			else {
				String warning = String.format(ERROR_MSG, cp.getFeatureGmlId(), cp.getPoint());
				flaechenschlussWarnings.add(warning);
				BadGeometry badGeometry = new BadGeometry(cp.getPoint(), warning);
				badGeometries.add(badGeometry);
			}
		});
	}

	private List<ControlPoint> collectControlPointsIntersectingTheInteriorRing(Ring interiorRing,
			List<FlaechenschlussFeature> intersectingFlaechenschlussFeatures) {
		List<ControlPoint> controlPointsInIntersection = intersectingFlaechenschlussFeatures.stream()
				.map(flaechenschlussFeature -> parseControlPointsInIntersection(flaechenschlussFeature, interiorRing))
				.flatMap(List::stream).collect(Collectors.toList());
		return controlPointsInIntersection;
	}

	private List<FlaechenschlussFeature> collectFlaechenschlussFeaturesIntersectingTheInteriorRing(Ring interiorRing) {
		List<FlaechenschlussFeature> intersectingFlaechenschlussFeatures = new ArrayList<>();
		for (FlaechenschlussFeature flaechenschlussFeature : flaechenschlussFeatures) {
			if (flaechenschlussFeature.getGeometry().intersects(interiorRing)) {
				intersectingFlaechenschlussFeatures.add(flaechenschlussFeature);
			}
		}
		return intersectingFlaechenschlussFeatures;
	}

	private Geometry createFlaechenschlussUnion() {
		ICRS coordinateSystem = null;
		GeometryFactory factory = new GeometryFactory();
		org.locationtech.jts.geom.Geometry[] geometries = new org.locationtech.jts.geom.Geometry[flaechenschlussFeatures
				.size()];
		int i = 0;
		for (FlaechenschlussFeature flaechenschlussFeature : flaechenschlussFeatures) {
			coordinateSystem = flaechenschlussFeature.getGeometry().getCoordinateSystem();
			geometries[i++] = flaechenschlussFeature.getJtsGeometry();
		}
		GeometryCollection geometryCollection = factory.createGeometryCollection(geometries);
		org.locationtech.jts.geom.Geometry union = geometryCollection.buffer(0);
		return DEFAULT_GEOM.createFromJTS(union, coordinateSystem);
	}

	private List<Pair<FlaechenschlussFeature, FlaechenschlussFeature>> detectFlaechenschlussFeaturePairsToAnalyse() {
		List<Pair<FlaechenschlussFeature, FlaechenschlussFeature>> flaechenschlussFeaturePairs = new ArrayList<>();
		List<FlaechenschlussFeature> flaechenschlussFeaturesCopy = new ArrayList<>(flaechenschlussFeatures);
		for (FlaechenschlussFeature flaechenschlussFeature1 : flaechenschlussFeatures) {
			flaechenschlussFeaturesCopy.remove(flaechenschlussFeature1);
			for (FlaechenschlussFeature flaechenschlussFeature2 : flaechenschlussFeaturesCopy) {
				if (flaechenschlussFeature1.getGeometry().intersects(flaechenschlussFeature2.getGeometry())) {
					flaechenschlussFeaturePairs.add(new Pair<>(flaechenschlussFeature1, flaechenschlussFeature2));
				}
			}
		}
		return flaechenschlussFeaturePairs;
	}

	private void analyseFlaechenschlussFeaturePair(
			Pair<FlaechenschlussFeature, FlaechenschlussFeature> flaechenschlussFeaturePair) {
		FlaechenschlussFeature flaechenschlussFeature1 = flaechenschlussFeaturePair.getFirst();
		FlaechenschlussFeature flaechenschlussFeature2 = flaechenschlussFeaturePair.getSecond();
		Geometry intersection = flaechenschlussFeature1.getGeometry()
				.getIntersection(flaechenschlussFeature2.getGeometry());
		LOG.debug("Intersection of {} ({}) and {} ({}) is {}", flaechenschlussFeature1.getFeatureId(),
				flaechenschlussFeature1.getFeatureType(), flaechenschlussFeature2.getFeatureId(),
				flaechenschlussFeature2.getFeatureType(), intersection.getClass().getSimpleName());
		checkAndAddInvalidFlaechenschlussFeature(flaechenschlussFeature1, flaechenschlussFeature2, intersection);
	}

	private void checkAndAddInvalidFlaechenschlussFeature(FlaechenschlussFeature flaechenschlussFeature1,
			FlaechenschlussFeature flaechenschlussFeature2, Geometry intersection) {
		switch (intersection.getGeometryType()) {
		case PRIMITIVE_GEOMETRY:
			checkAndAddInvalidFlaechenschlussFeature(flaechenschlussFeature1, flaechenschlussFeature2,
					(GeometricPrimitive) intersection);
			break;
		case MULTI_GEOMETRY:
			checkAndAddInvalidFlaechenschlussFeature(flaechenschlussFeature1, flaechenschlussFeature2,
					(MultiGeometry) intersection);
			break;
		}
	}

	private void checkAndAddInvalidFlaechenschlussFeature(FlaechenschlussFeature flaechenschlussFeature1,
			FlaechenschlussFeature flaechenschlussFeature2, MultiGeometry intersection) {
		if (intersection instanceof MultiLineString)
			return;
		intersection.stream().forEach(geom -> checkAndAddInvalidFlaechenschlussFeature(flaechenschlussFeature1,
				flaechenschlussFeature2, (Geometry) geom));
	}

	private void checkAndAddInvalidFlaechenschlussFeature(FlaechenschlussFeature flaechenschlussFeature1,
			FlaechenschlussFeature flaechenschlussFeature2, GeometricPrimitive intersection) {
		if (!(intersection instanceof Point) && !(intersection instanceof Curve)) {
			LOG.debug("Analyse intersection of {} ({}) and {} ({}): {}", flaechenschlussFeature1.getFeatureId(),
					flaechenschlussFeature1.getFeatureType(), flaechenschlussFeature2.getFeatureId(),
					flaechenschlussFeature2.getFeatureType(), WKTWriter.write(intersection));

			List<ControlPoint> controlPointsFlaechenschlussFeature1 = parseControlPointsInIntersection(
					flaechenschlussFeature1, intersection);
			List<ControlPoint> controlPointsFlaechenschlussFeature2 = parseControlPointsInIntersection(
					flaechenschlussFeature2, intersection);
			LOG.trace("Control points of flaechenschluss feature {} ({}): {}", flaechenschlussFeature1.getFeatureId(),
					flaechenschlussFeature1.getFeatureType(), controlPointsFlaechenschlussFeature1.stream()
							.map(p -> WKTWriter.write(p.getPoint())).collect(Collectors.joining(",")));
			LOG.trace("Control points of flaechenschluss feature {} ({}): {}", flaechenschlussFeature2.getFeatureId(),
					flaechenschlussFeature2.getFeatureType(), controlPointsFlaechenschlussFeature2.stream()
							.map(p -> WKTWriter.write(p.getPoint())).collect(Collectors.joining(",")));

			List<ControlPoint> controlPointsToCheck = new ArrayList<>();
			controlPointsToCheck.addAll(controlPointsFlaechenschlussFeature1);
			controlPointsToCheck.addAll(controlPointsFlaechenschlussFeature2);
			checkControlPointsAndAddFailures(controlPointsToCheck, true);
		}
	}

	private boolean handleAsFailure() {
		if (XPLAN_3.equals(xPlanVersion) || XPLAN_40.equals(xPlanVersion) || XPLAN_41.equals(xPlanVersion)
				|| XPLAN_50.equals(xPlanVersion) || XPLAN_51.equals(xPlanVersion) || XPLAN_52.equals(xPlanVersion)) {
			LOG.info(
					"Loecher im Flaechenschluss sind in der Version {} zugelassen, werden aber als Warnung ausgegeben.",
					xPlanVersion);
			return false;
		}
		else if (XPLAN_53.equals(xPlanVersion) || XPLAN_54.equals(xPlanVersion)) {
			LOG.info(
					"Loecher im Flaechenschluss sind in der Version {} zugelassen sollten aber ueber BP_FlaecheOhneFestsetzung bzw. FP_FlaecheOhneDarstellung modelliert werden. Potentielle Fehler werden als Warnung ausgegeben",
					xPlanVersion);
			return false;
		}
		LOG.info(
				"Loecher im Flaechenschluss sind in der Version {} nicht mehr zugelassen muessen ueber BP_FlaecheOhneFestsetzung bzw. FP_FlaecheOhneDarstellung modelliert werden.",
				xPlanVersion);
		return true;
	}

	private List<ControlPoint> parseControlPointsInIntersection(FlaechenschlussFeature flaechenschlussFeature,
			Geometry intersection) {
		List<ControlPoint> controlPointsInIntersection = new ArrayList<>();
		parseControlPointsInIntersection(flaechenschlussFeature, flaechenschlussFeature.getGeometry(), intersection,
				controlPointsInIntersection);
		return controlPointsInIntersection;
	}

	private void parseControlPointsInIntersection(FlaechenschlussFeature flaechenschlussFeature,
			Geometry flaechenschlussFeatureGeometry, Geometry intersection,
			List<ControlPoint> controlPointsInIntersection) {
		if (flaechenschlussFeatureGeometry instanceof Surface) {
			Surface surface = (Surface) flaechenschlussFeatureGeometry;
			controlPointsInIntersection.addAll(
					parseControlPointsInIntersection(flaechenschlussFeature.getFeatureId(), surface, intersection));
		}
		else if (flaechenschlussFeatureGeometry instanceof MultiSurface) {
			MultiSurface multiSurface = (MultiSurface) flaechenschlussFeatureGeometry;
			multiSurface.stream().forEach(o -> parseControlPointsInIntersection(flaechenschlussFeature, (Geometry) o,
					intersection, controlPointsInIntersection));
		}
	}

	private List<ControlPoint> parseControlPointsInIntersection(String featureId, Surface surface,
			Geometry intersection) {
		List<CurveSegment> segments = getSegments(surface);

		List<Points> pointsList = new ArrayList<>(segments.size());
		for (CurveSegment segment : segments) {
			CurveSegment.CurveSegmentType segmentType = segment.getSegmentType();
			switch (segmentType) {
			case ARC:
				pointsList.add(((Arc) segment).getControlPoints());
				break;
			case ARC_BY_BULGE:
				pointsList.add(((ArcByBulge) segment).getControlPoints());
				break;
			case ARC_STRING:
				pointsList.add(((ArcString) segment).getControlPoints());
				break;
			case ARC_STRING_BY_BULGE:
				pointsList.add(((ArcStringByBulge) segment).getControlPoints());
				break;
			case BEZIER:
				pointsList.add(((Bezier) segment).getControlPoints());
				break;
			case BSPLINE:
				pointsList.add(((BSpline) segment).getControlPoints());
				break;
			case CIRCLE:
				pointsList.add(((Circle) segment).getControlPoints());
				break;
			case CUBIC_SPLINE:
				pointsList.add(((CubicSpline) segment).getControlPoints());
				break;
			case GEODESIC:
				pointsList.add(((Geodesic) segment).getControlPoints());
				break;
			case GEODESIC_STRING:
				pointsList.add(((GeodesicString) segment).getControlPoints());
				break;
			case LINE_STRING_SEGMENT:
				pointsList.add(((LineStringSegment) segment).getControlPoints());
				break;
			case OFFSET_CURVE:
				break;
			case ARC_BY_CENTER_POINT:
			case CIRCLE_BY_CENTER_POINT:
			case CLOTHOID:
			default:
				throw new IllegalArgumentException(
						"Surfaces with segments of type " + segmentType + " are currently not supported.");
			}
		}
		List<ControlPoint> allPoints = new ArrayList<>();
		for (Points points : pointsList) {
			Iterator<Point> iterator = points.iterator();
			while (iterator.hasNext()) {
				Point point = iterator.next();
				if (intersection.intersects(point)) {
					allPoints.add(new ControlPoint(featureId, point));
				}
			}
		}
		return allPoints;
	}

	private List<CurveSegment> getSegments(Surface surface) {
		List<? extends SurfacePatch> patches = surface.getPatches();
		if (patches.size() == 1) {
			if (patches.get(0) instanceof PolygonPatch) {
				PolygonPatch patch = (PolygonPatch) patches.get(0);
				List<CurveSegment> curveSegments = new ArrayList<>();
				curveSegments.addAll(patch.getExteriorRing().getCurveSegments());
				for (Ring ir : patch.getInteriorRings()) {
					curveSegments.addAll(ir.getCurveSegments());
				}
				return curveSegments;
			}
			throw new IllegalArgumentException(Messages.getMessage("SURFACE_IS_NON_PLANAR"));
		}
		throw new IllegalArgumentException(Messages.getMessage("SURFACE_MORE_THAN_ONE_PATCH"));
	}

}
