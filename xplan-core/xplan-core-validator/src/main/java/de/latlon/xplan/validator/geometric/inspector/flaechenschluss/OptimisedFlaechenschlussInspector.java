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
package de.latlon.xplan.validator.geometric.inspector.flaechenschluss;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.inspector.GeometricFeatureInspector;
import de.latlon.xplan.validator.geometric.inspector.model.AbstractGeltungsbereichFeature;
import de.latlon.xplan.validator.geometric.inspector.model.BereichFeature;
import de.latlon.xplan.validator.geometric.inspector.model.FeatureUnderTest;
import de.latlon.xplan.validator.geometric.inspector.model.FeaturesUnderTest;
import de.latlon.xplan.validator.geometric.inspector.model.GeltungsbereichFeature;
import de.latlon.xplan.validator.geometric.inspector.model.PlanFeature;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.uom.Measure;
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
import org.locationtech.jts.geom.IntersectionMatrix;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.TopologyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.latlon.xplan.commons.XPlanType.LP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.validator.geometric.inspector.flaechenschluss.FlaechenschlussTolerance.ALLOWEDDISTANCE_METRE;
import static de.latlon.xplan.validator.i18n.ValidationMessages.format;
import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;

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
 * Algorithmus in Pseudocode mit Vereinfachungen:
 *
 * <pre>
 * 1. Hinzufuegen aller relevanten Features zum Kontext:
 *    - XP_Plan Features
 *    - XP_Bereich Features
 *    - FlaechenschlussFeatures (ebene==0, flaechenschluss==true, wirksamkeit ist gegeben)
 * 2. Pruefung des Flaechenschluss, fuer alle Geltungsbereiche und die zugehoerigen identifizierten FlaechenschlussFeatures
 * 	  1. Identifiziere Paare von einander ueberlappenden FlaechenschlussFeatures
 *    2. Fuer alle Paare
 *       1. Bilden des Schnittbereichs,
 *          1. Wenn es sich um ein Polygon handelt
 *          2. Identifizieren der Stuetzpunkte der beiden betroffenen FlaechenschlussFeatures im Schnittbereich
 *          3. Pruefen ob jeder Stuetzpunkt einen korrespondieren Stuetzpunkt besitzt
 *          4. => Wenn nicht, Ausgabe eines Fehlers
 *    3. Pruefen der Vereinigung des Flaechenschluss
 *       1. Bilden der Vereinigung der Geometrien aller FlaechenschlussFeatures inkl. der Loecher aus dem Geltungsbereich (Bereich oder Plan) mit einem Puffer von 2mm
 *       2. Pruefen innerer Luecken in der Vereinigung aller FlaechenschlussFeatures
 *          1. Fuer alle inneren Polygone
 *             1. Identifizieren aller FlaechenschlussFeatures, die an diesem Polygon liegen
 *             2. Identifizieren der Stuetzpunkte der beiden FlaechenschlussFeatures
 *             3. Pruefen ob jeder Stuetzpunkt einen korrespondieren Stuetzpunkt besitzt
 *             4. => Wenn nicht, Ausgabe eines Fehlers bzw. Warnung (bis 5.4) mit Hinweis auf potentielle Luecke
 *    4. Pruefen der Vereinigung des Flaechenschluss gegen den Geltungsbereich des Bereichs (wenn vorhanden)
 *       1. Bilden des Schnittbereichs der Vereinigung aller FlaechenschlussFeatures innerhalb des Breichs (aus 3.1.) mit dem Geltungsbereich des Bereichs
 *       2. Fuer alle Polygone im Schnittbereich
 *           1. Identifizieren der Stuetzpunkte der beiden betroffenen FlaechenschlussFeatures
 *           2. Pruefe ob das Polygon im Toleranzbereich liegt (Wenn das Polygon mit einem Puffer von -1 kein leeres Polygon ergibt)
 *           3. Wenn ja
 *              1. Pruefen ob jeder Stuetzpunkt einen korrespondieren Stuetzpunkt besitzt
 *              2. => Wenn nicht, Ausgabe eines Fehlers bzw. Warnung (bis 5.4) mit Hinweis auf potentielle Luecke
 *              3. => Wenn ja, Ausgabe eines Fehlers bzw. Warnung (bis 5.4) mit Hinweis auf eine Luecke
 *    5. Pruefen der Vereinigung des Flaechenschluss gegen den Geltungsbereich des Plans
 *       1. Bilden des Schnittbereichs der Vereinigung aller FlaechenschlussFeatures innerhalb des Plans (aus 3.1.) mit dem Geltungsbereich des Plans
 *       2. Fuer alle Polygone im Schnittbereich
 *           1. Identifizieren der Stuetzpunkte der beiden betroffenen FlaechenschlussFeatures
 *           2. Pruefe ob das Polygon im Toleranzbereich liegt (Wenn das Polygon mit einem Puffer von -1 kein leeres Polygon ergibt)
 *           3. Wenn ja
 *              1. Pruefen ob jeder Stuetzpunkt einen korrespondieren Stuetzpunkt besitzt
 *              2. => Wenn nicht, Ausgabe eines Fehlers bzw. Warnung (bis 5.4) mit Hinweis auf potentielle Luecke
 *              3. => Wenn ja, Ausgabe eines Fehlers bzw. Warnung (bis 5.4) mit Hinweis auf eine Luecke
 * </pre>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @see OptimisedFlaechenschlussInspector#inspect(Feature)
 * @see OptimisedFlaechenschlussInspector#checkGeometricRule()
 */
public class OptimisedFlaechenschlussInspector implements GeometricFeatureInspector {

	private static final Logger LOG = LoggerFactory.getLogger(OptimisedFlaechenschlussInspector.class);

	private static final AbstractDefaultGeometry DEFAULT_GEOM = new DefaultPoint(null, null, null,
			new double[] { 0.0, 0.0 });

	private final List<String> flaechenschlussErrors = new ArrayList<>();

	private final List<String> flaechenschlussWarnings = new ArrayList<>();

	private final List<BadGeometry> badGeometries = new ArrayList<>();

	private final XPlanVersion xPlanVersion;

	private final XPlanType xPlanType;

	private final FlaechenschlussContext flaechenschlussContext;

	public OptimisedFlaechenschlussInspector(XPlanVersion xPlanVersion, XPlanType xPlanType) {
		this.xPlanVersion = xPlanVersion;
		this.xPlanType = xPlanType;
		this.flaechenschlussContext = new FlaechenschlussContext(xPlanVersion);
	}

	private enum TestStep {

		FLAECHENSCHLUSSPAIRS, FLAECHENSCHLUSSUNION, GELTUNGSBEREICH_BEREICH, GELTUNGSBEREICH_PLAN

	}

	@Override
	public Feature inspect(Feature feature) throws FeatureInspectionException {
		flaechenschlussContext.addToContext(feature);
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
		try {
			Map<GeltungsbereichFeature, List<FeatureUnderTest>> allFlaechenschlussFeaturesOfAPlan = flaechenschlussContext
				.getAllFlaechenschlussFeaturesOfAPlan();
			Map<PlanFeature, List<FeaturesUnderTest>> planFeaturesWithFeaturesUnderTest = new HashMap<>();
			Map<BereichFeature, FeaturesUnderTest> bereichFeaturesWithFeaturesUnderTest = new HashMap<>();
			allFlaechenschlussFeaturesOfAPlan.forEach((geltungsbereichFeature, featuresUnderTest) -> {
				if (!featuresUnderTest.isEmpty()) {
					analyseFlaechenschlussFeaturePairs(featuresUnderTest);
					analyseFlaechenschlussUnionFlaechenschlussFeatures(geltungsbereichFeature, featuresUnderTest,
							bereichFeaturesWithFeaturesUnderTest, planFeaturesWithFeaturesUnderTest);
				}
			});
			if (!LP_Plan.equals(xPlanType)) {
				bereichFeaturesWithFeaturesUnderTest.forEach((bereichFeature, featuresUnderTest) -> {
					analyseFlaechenschlussUnionOfBereich(bereichFeature, featuresUnderTest);
				});
				planFeaturesWithFeaturesUnderTest.forEach((planFeature, featuresUnderTest) -> {
					analyseFlaechenschlussUnionOfPlan(planFeature, featuresUnderTest);
				});
			}
			if (flaechenschlussErrors.isEmpty()) {
				LOG.info("No features with invalid flaechenschluss");
			}
			else {
				LOG.info("Features with invalid flaechenschluss:\n {}",
						flaechenschlussErrors.stream().collect(Collectors.joining("\n")));
			}
		}
		catch (ValidatorException e) {
			flaechenschlussErrors.add(e.getMessage());
		}
		catch (TopologyException e) {
			String msg = getMessage("FlaechenschlussInspector_geom_abort");
			flaechenschlussErrors.add(msg);
		}
		catch (Exception e) {
			String msg = getMessage("FlaechenschlussInspector_abort");
			flaechenschlussErrors.add(msg);
			LOG.debug(msg, e);
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

	@Override
	public boolean applicableForVersion(XPlanVersion version) {
		return true;
	}

	private void analyseFlaechenschlussFeaturePairs(List<FeatureUnderTest> featuresUnderTest) {
		List<Pair<FeatureUnderTest, FeatureUnderTest>> flaechenschlussFeaturePairs = detectFlaechenschlussFeaturePairsToAnalyse(
				featuresUnderTest);
		LOG.debug("Found {} intersecting flaechenschluss features.", flaechenschlussFeaturePairs.size());
		flaechenschlussFeaturePairs
			.forEach(flaechenschlussFeaturePair -> analyseFlaechenschlussFeaturePair(flaechenschlussFeaturePair));
	}

	private void analyseFlaechenschlussUnionFlaechenschlussFeatures(GeltungsbereichFeature geltungsbereichFeature,
			List<FeatureUnderTest> featuresUnderTest,
			Map<BereichFeature, FeaturesUnderTest> bereichFeaturesWithFeaturesUnderTest,
			Map<PlanFeature, List<FeaturesUnderTest>> planFeaturesWithFeaturesUnderTest) {
		Geometry flaechenschlussUnion = createFlaechenschlussUnion(geltungsbereichFeature, featuresUnderTest);
		LOG.debug("Union of all flaechenschluss geometries: {}", WKTWriter.write(flaechenschlussUnion));
		checkFlaechenschlussFeaturesIntersectingAnInteriorRing(geltungsbereichFeature, featuresUnderTest,
				flaechenschlussUnion, TestStep.FLAECHENSCHLUSSUNION);
		addBereichFeature(bereichFeaturesWithFeaturesUnderTest, geltungsbereichFeature, featuresUnderTest,
				flaechenschlussUnion);
		addPlanFeature(planFeaturesWithFeaturesUnderTest, geltungsbereichFeature, featuresUnderTest,
				flaechenschlussUnion);
	}

	private void analyseFlaechenschlussUnionOfBereich(BereichFeature bereichFeature,
			FeaturesUnderTest featuresUnderTest) {
		Geometry flaechenschlussUnion = featuresUnderTest.getFlaechenschlussUnion();
		checkFlaechenschlussFeaturesWithGeltungsbereich(bereichFeature, featuresUnderTest.getFeaturesUnderTest(),
				flaechenschlussUnion, TestStep.GELTUNGSBEREICH_BEREICH);
	}

	private void analyseFlaechenschlussUnionOfPlan(PlanFeature planFeature,
			List<FeaturesUnderTest> featuresUnderTests) {
		if (planFeature.isAenderungsPlan()) {
			LOG.info("Plan {} is an Aenderungsplan. Complete covering is not checked for the plan.",
					planFeature.getFeatureId());
			return;
		}
		Geometry flaechenschlussUnion = createFlaechenschlussUnion(featuresUnderTests);
		LOG.debug("Union of all flaechenschluss geometries assigned to plan {}: {}", planFeature.getFeatureId(),
				WKTWriter.write(flaechenschlussUnion));

		List<FeatureUnderTest> featureUnderTestOfPlan = featuresUnderTests.stream()
			.flatMap(featuresUnderTest -> featuresUnderTest.getFeaturesUnderTest().stream())
			.collect(Collectors.toList());
		checkFlaechenschlussFeaturesWithGeltungsbereich(planFeature, featureUnderTestOfPlan, flaechenschlussUnion,
				TestStep.GELTUNGSBEREICH_PLAN);
	}

	@SuppressWarnings("unchecked")
	private void checkFlaechenschlussFeaturesIntersectingAnInteriorRing(GeltungsbereichFeature geltungsbereichFeature,
			List<FeatureUnderTest> flaechenschlussFeatures, Geometry flaechenschlussUnion, TestStep testStep) {
		if (flaechenschlussUnion == null)
			return;
		if (flaechenschlussUnion instanceof DefaultSurface) {
			// Simple Polygon without interior ring must be valid!
			if (((DefaultSurface) flaechenschlussUnion).getInteriorRingsCoordinates().isEmpty())
				return;
			checkFlaechenschlussFeaturesIntersectingAnInteriorRing(geltungsbereichFeature, flaechenschlussFeatures,
					(DefaultSurface) flaechenschlussUnion, testStep);
		}
		else if (flaechenschlussUnion instanceof MultiSurface) {
			checkFlaechenschlussFeaturesIntersectingAnInteriorRing(geltungsbereichFeature, flaechenschlussFeatures,
					(MultiSurface<Surface>) flaechenschlussUnion, testStep);
		}
	}

	@SuppressWarnings("unchecked")
	private void checkFlaechenschlussFeaturesWithGeltungsbereich(GeltungsbereichFeature geltungsbereichFeature,
			List<FeatureUnderTest> flaechenschlussFeatures, Geometry flaechenschlussUnion, TestStep testStep) {
		if (flaechenschlussUnion == null)
			return;
		Geometry diffGeltungsbereich = calculateDifference(geltungsbereichFeature, flaechenschlussUnion);
		LOG.debug("Difference with Geltungsbereich: " + WKTWriter.write(diffGeltungsbereich));
		if (diffGeltungsbereich == null)
			return;
		if (diffGeltungsbereich instanceof DefaultSurface) {
			checkFlaechenschlussFeaturesIntersectingGeltungsbereich(flaechenschlussFeatures, geltungsbereichFeature,
					(DefaultSurface) diffGeltungsbereich, testStep);
		}
		else if (diffGeltungsbereich instanceof MultiSurface) {
			checkFlaechenschlussFeaturesIntersectingGeltungsbereich(flaechenschlussFeatures, geltungsbereichFeature,
					(MultiSurface<Surface>) diffGeltungsbereich, testStep);
		}
	}

	private Geometry calculateDifference(GeltungsbereichFeature geltungsbereichFeature, Geometry flaechenschlussUnion) {
		Geometry diffGeltungsbereich1 = geltungsbereichFeature.getOriginalGeometry()
			.getDifference(flaechenschlussUnion);
		Geometry diffGeltungsbereich2 = flaechenschlussUnion
			.getDifference(geltungsbereichFeature.getOriginalGeometry());
		if (diffGeltungsbereich1 == null && diffGeltungsbereich2 == null)
			return null;
		if (diffGeltungsbereich1 != null && diffGeltungsbereich2 == null)
			return diffGeltungsbereich1;
		if (diffGeltungsbereich1 == null && diffGeltungsbereich2 != null)
			return diffGeltungsbereich2;
		return diffGeltungsbereich1.getUnion(diffGeltungsbereich2);
	}

	private void checkFlaechenschlussFeaturesIntersectingAnInteriorRing(GeltungsbereichFeature geltungsbereichFeature,
			List<FeatureUnderTest> flaechenschlussFeatures, MultiSurface<Surface> flaechenschlussUnion,
			TestStep testStep) {
		for (Surface flaechenschlussUnionGeom : flaechenschlussUnion) {
			if (flaechenschlussUnionGeom instanceof DefaultSurface) {
				checkFlaechenschlussFeaturesIntersectingAnInteriorRing(geltungsbereichFeature, flaechenschlussFeatures,
						(DefaultSurface) flaechenschlussUnionGeom, testStep);
			}
			else {
				LOG.warn("Could not handle surface of type " + flaechenschlussUnionGeom.getClass());
			}
		}
	}

	private void checkFlaechenschlussFeaturesIntersectingAnInteriorRing(GeltungsbereichFeature geltungsbereichFeature,
			List<FeatureUnderTest> flaechenschlussFeatures, DefaultSurface flaechenschlussUnion, TestStep testStep) {
		List<? extends SurfacePatch> patches = flaechenschlussUnion.getPatches();
		PolygonPatch patch = (PolygonPatch) patches.get(0);
		List<Ring> interiorRings = patch.getInteriorRings();
		for (Ring interiorRing : interiorRings) {
			List<FeatureUnderTest> intersectingFlaechenschlussFeatures = collectFlaechenschlussFeaturesIntersectingTheInteriorRing(
					flaechenschlussFeatures, interiorRing);
			LOG.debug("Analyse flaechenschluss features intersection {}", WKTWriter.write(interiorRing));
			LOG.debug("Flaechenschluss Features: \n  " + intersectingFlaechenschlussFeatures.stream()
				.map(flaechenschlussFeature -> flaechenschlussFeature.getFeatureId() + "("
						+ flaechenschlussFeature.getFeatureType() + ")")
				.collect(Collectors.joining("\n  ")));
			List<ControlPoint> controlPointsInIntersection = collectControlPointsIntersectingTheInteriorRing(
					interiorRing, geltungsbereichFeature, intersectingFlaechenschlussFeatures);
			checkControlPointsAndAddFailures(controlPointsInIntersection, testStep);
		}
	}

	private void checkFlaechenschlussFeaturesIntersectingGeltungsbereich(List<FeatureUnderTest> flaechenschlussFeatures,
			GeltungsbereichFeature geltungsbereich, MultiSurface<Surface> diffGeltungsbereich, TestStep testStep) {
		for (Surface diffGeltungsbereichGeom : diffGeltungsbereich) {
			if (diffGeltungsbereichGeom instanceof DefaultSurface) {
				checkFlaechenschlussFeaturesIntersectingGeltungsbereich(flaechenschlussFeatures, geltungsbereich,
						(DefaultSurface) diffGeltungsbereichGeom, testStep);
			}
			else {
				LOG.warn("Could not handle surface of type " + diffGeltungsbereichGeom.getClass());
			}
		}
	}

	private void checkFlaechenschlussFeaturesIntersectingGeltungsbereich(List<FeatureUnderTest> flaechenschlussFeatures,
			GeltungsbereichFeature geltungsbereichFeature, DefaultSurface diffGeltungsbereich, TestStep testStep) {
		if (isDiffInTolerance(diffGeltungsbereich))
			return;
		IntersectionMatrix relate = geltungsbereichFeature.getJtsGeometry()
			.relate(diffGeltungsbereich.getJTSGeometry());
		// The feature geometry must have at least one point in common with the interior
		// of the geltungsbereich geometry. Also the boundaries of the feature and
		// geltungsbereich geometry.
		LOG.debug("Intersection matrix: {}", relate);
		if (relate.matches("TTT*****T")) {
			List<? extends SurfacePatch> patches = diffGeltungsbereich.getPatches();
			PolygonPatch patch = (PolygonPatch) patches.get(0);
			Ring exteriorRing = patch.getExteriorRing();
			List<FeatureUnderTest> intersectingFlaechenschlussFeatures = collectFlaechenschlussFeaturesIntersectingTheInteriorRing(
					flaechenschlussFeatures, exteriorRing);
			LOG.debug("Analyse flaechenschluss features intersection with difference from geltungsbereich {}",
					WKTWriter.write(exteriorRing));
			LOG.debug("Flaechenschluss Features: \n  " + intersectingFlaechenschlussFeatures.stream()
				.map(flaechenschlussFeature -> flaechenschlussFeature.getFeatureId() + "("
						+ flaechenschlussFeature.getFeatureType() + ")")
				.collect(Collectors.joining("\n  ")));
			List<ControlPoint> controlPointsInIntersection = collectControlPointsIntersectingTheInteriorRing(
					exteriorRing, geltungsbereichFeature, intersectingFlaechenschlussFeatures);
			boolean foundInvalidControlPoints = checkControlPointsAndAddFailures(controlPointsInIntersection, testStep);
			if (!foundInvalidControlPoints) {
				boolean handleAsFailure = handleAsFailure(testStep);
				String lueckeMessage = getMessage("FlaechenschlussInspector_error_Luecke");
				if (TestStep.GELTUNGSBEREICH_BEREICH.equals(testStep)) {
					lueckeMessage = getMessage("FlaechenschlussInspector_error_bereich_Luecke");
				}
				else if (TestStep.GELTUNGSBEREICH_PLAN.equals(testStep)) {
					lueckeMessage = getMessage("FlaechenschlussInspector_error_plan_Luecke");
				}
				BadGeometry badGeometry = new BadGeometry(diffGeltungsbereich, lueckeMessage);
				badGeometries.add(badGeometry);
				if (handleAsFailure) {
					flaechenschlussErrors.add(lueckeMessage);
				}
				else {
					flaechenschlussWarnings.add(lueckeMessage);
				}
			}
		}
		else if (relate.isDisjoint()) {
			String disjointMessage;
			if (TestStep.GELTUNGSBEREICH_BEREICH.equals(testStep)) {
				disjointMessage = getMessage("FlaechenschlussInspector_error_bereich_disjoint");
			}
			else if (TestStep.GELTUNGSBEREICH_PLAN.equals(testStep)) {
				disjointMessage = getMessage("FlaechenschlussInspector_error_plan_disjoint");
			}
			else {
				return;
			}
			BadGeometry badGeometry = new BadGeometry(diffGeltungsbereich, disjointMessage);
			badGeometries.add(badGeometry);
			flaechenschlussErrors.add(disjointMessage);
		}
	}

	private boolean isDiffInTolerance(AbstractDefaultGeometry diffGeltungsbereich) {
		double negativeAllowedTolerance = ALLOWEDDISTANCE_METRE / 2 * -1;
		org.locationtech.jts.geom.Geometry diffGeltungsbereichBufferAllowedTolerance = diffGeltungsbereich
			.getJTSGeometry()
			.buffer(negativeAllowedTolerance);
		return diffGeltungsbereichBufferAllowedTolerance.isEmpty();
	}

	private boolean checkControlPointsAndAddFailures(List<ControlPoint> controlPoints, TestStep testStep) {
		List<ControlPoint> controlPointsInIntersectionTmp = new ArrayList<>(controlPoints);
		controlPoints.forEach(cpToCheck -> {
			controlPointsInIntersectionTmp.remove(cpToCheck);
			controlPointsInIntersectionTmp.forEach(cpInIntersection2 -> cpToCheck.checkIfIdentical(cpInIntersection2));
		});
		List<ControlPoint> controlPointsWithInvalidFlaechenschluss = controlPoints.stream()
			.filter(cp -> !cp.hasIdenticalControlPoint())
			.collect(Collectors.toList());
		controlPointsWithInvalidFlaechenschluss.stream().forEach(cp -> {
			boolean handleAsFailure = handleAsFailure(testStep);
			String msg;
			if (!handleAsFailure && !TestStep.FLAECHENSCHLUSSPAIRS.equals(testStep)) {
				if (TestStep.GELTUNGSBEREICH_BEREICH.equals(testStep)) {
					msg = format("FlaechenschlussInspector_possibleLuecke_bereich", cp.getFeatureGmlId(),
							cp.getPoint());
				}
				else if (TestStep.GELTUNGSBEREICH_PLAN.equals(testStep)) {
					msg = format("FlaechenschlussInspector_possibleLuecke_plan", cp.getFeatureGmlId(), cp.getPoint());
				}
				else {
					msg = format("FlaechenschlussInspector_possibleLuecke", cp.getFeatureGmlId(), cp.getPoint());
				}
			}
			else if (TestStep.GELTUNGSBEREICH_BEREICH.equals(testStep)) {
				msg = format("FlaechenschlussInspector_error_bereich", cp.getFeatureGmlId(), cp.getPoint());
			}
			else if (TestStep.GELTUNGSBEREICH_PLAN.equals(testStep)) {
				msg = format("FlaechenschlussInspector_error_plan", cp.getFeatureGmlId(), cp.getPoint());
			}
			else {
				msg = format("FlaechenschlussInspector_error", cp.getFeatureGmlId(), cp.getPoint());
			}
			BadGeometry badGeometry = new BadGeometry(cp.getPoint(), msg);
			if (!badGeometries.contains(badGeometry)) {
				badGeometries.add(badGeometry);
				if (handleAsFailure) {
					flaechenschlussErrors.add(msg);
				}
				else {
					flaechenschlussWarnings.add(msg);
				}
			}
		});
		return !controlPointsWithInvalidFlaechenschluss.isEmpty();
	}

	private void addBereichFeature(Map<BereichFeature, FeaturesUnderTest> bereichFeaturesWithFeaturesUnderTest,
			GeltungsbereichFeature geltungsbereichFeature, List<FeatureUnderTest> featuresUnderTest,
			Geometry flaechenschlussUnion) {
		if (geltungsbereichFeature instanceof BereichFeature) {
			FeaturesUnderTest bereichFeaturesUnderTest = new FeaturesUnderTest(flaechenschlussUnion, featuresUnderTest);
			bereichFeaturesWithFeaturesUnderTest.put((BereichFeature) geltungsbereichFeature, bereichFeaturesUnderTest);
		}
	}

	private void addPlanFeature(Map<PlanFeature, List<FeaturesUnderTest>> planFeaturesWithFeaturesUnderTest,
			GeltungsbereichFeature geltungsbereichFeature, List<FeatureUnderTest> featuresUnderTest,
			Geometry flaechenschlussUnion) {
		if (geltungsbereichFeature instanceof BereichFeature
				&& !((BereichFeature) geltungsbereichFeature).isKompensationsbereichOrOutsideGeltungsbereich()) {
			FeaturesUnderTest featuresUnderTest1 = new FeaturesUnderTest(flaechenschlussUnion, featuresUnderTest);
			PlanFeature planFeature = ((BereichFeature) geltungsbereichFeature).getPlanFeature();
			if (!planFeaturesWithFeaturesUnderTest.containsKey(planFeature)) {
				planFeaturesWithFeaturesUnderTest.put(planFeature, new ArrayList<>());
			}
			planFeaturesWithFeaturesUnderTest.get(planFeature).add(featuresUnderTest1);
		}
		else if (geltungsbereichFeature instanceof PlanFeature) {
			FeaturesUnderTest planFeaturesUnderTest = new FeaturesUnderTest(flaechenschlussUnion, featuresUnderTest);
			PlanFeature planFeature = (PlanFeature) geltungsbereichFeature;
			if (!planFeaturesWithFeaturesUnderTest.containsKey(planFeature)) {
				planFeaturesWithFeaturesUnderTest.put(planFeature, new ArrayList<>());
			}
			planFeaturesWithFeaturesUnderTest.get(planFeature).add(planFeaturesUnderTest);
		}
	}

	private List<ControlPoint> collectControlPointsIntersectingTheInteriorRing(Ring interiorRing,
			GeltungsbereichFeature geltungsbereichFeature, List<FeatureUnderTest> intersectingFlaechenschlussFeatures) {
		List<ControlPoint> controlPointsInIntersection = intersectingFlaechenschlussFeatures.stream()
			.map(flaechenschlussFeature -> parseControlPointsInIntersection(flaechenschlussFeature, interiorRing,
					false))
			.flatMap(List::stream)
			.collect(Collectors.toList());
		controlPointsInIntersection
			.addAll(parseControlPointsInIntersection(geltungsbereichFeature, interiorRing, true));
		return controlPointsInIntersection;
	}

	private List<FeatureUnderTest> collectFlaechenschlussFeaturesIntersectingTheInteriorRing(
			List<FeatureUnderTest> flaechenschlussFeatures, Ring interiorRing) {
		List<FeatureUnderTest> intersectingFlaechenschlussFeatures = new ArrayList<>();
		for (FeatureUnderTest flaechenschlussFeature : flaechenschlussFeatures) {
			if (flaechenschlussFeature.getOriginalGeometry().intersects(interiorRing)) {
				intersectingFlaechenschlussFeatures.add(flaechenschlussFeature);
			}
		}
		return intersectingFlaechenschlussFeatures;
	}

	@SuppressWarnings("deprecation")
	private Geometry createFlaechenschlussUnion(GeltungsbereichFeature geltungsbereichFeature,
			List<FeatureUnderTest> featuresUnderTest) {
		ICRS coordinateSystem = null;
		GeometryFactory factory = new GeometryFactory();
		List<org.locationtech.jts.geom.Geometry> geometries = new ArrayList<>();
		for (FeatureUnderTest flaechenschlussFeature : featuresUnderTest) {
			coordinateSystem = flaechenschlussFeature.getOriginalGeometry().getCoordinateSystem();
			geometries.add(flaechenschlussFeature.getJtsGeometry());
		}
		addHolesFromGeltungsbereich(geltungsbereichFeature, factory, geometries);
		GeometryCollection geometryCollection = factory
			.createGeometryCollection(geometries.toArray(new org.locationtech.jts.geom.Geometry[] {}));

		org.locationtech.jts.geom.Geometry union = geometryCollection.union();
		return DEFAULT_GEOM.createFromJTS(union, coordinateSystem);
	}

	@SuppressWarnings("deprecation")
	private Geometry createFlaechenschlussUnion(List<FeaturesUnderTest> featuresUnderTests) {
		ICRS coordinateSystem = null;
		GeometryFactory factory = new GeometryFactory();
		List<org.locationtech.jts.geom.Geometry> geometries = new ArrayList<>();
		for (FeaturesUnderTest featuresUnderTest : featuresUnderTests) {
			coordinateSystem = featuresUnderTest.getFlaechenschlussUnion().getCoordinateSystem();
			geometries.add(((AbstractDefaultGeometry) featuresUnderTest.getFlaechenschlussUnion()).getJTSGeometry());
		}
		GeometryCollection geometryCollection = factory
			.createGeometryCollection(geometries.toArray(new org.locationtech.jts.geom.Geometry[] {}));

		org.locationtech.jts.geom.Geometry union = geometryCollection.union();
		return DEFAULT_GEOM.createFromJTS(union, coordinateSystem);
	}

	private void addHolesFromGeltungsbereich(GeltungsbereichFeature geltungsbereichFeature, GeometryFactory factory,
			List<org.locationtech.jts.geom.Geometry> geometries) {
		org.locationtech.jts.geom.Geometry geltungsbereichGeometry = geltungsbereichFeature.getJtsGeometry();
		if (geltungsbereichGeometry instanceof Polygon) {
			Polygon geltungsbereichPolygon = (Polygon) geltungsbereichGeometry;
			addHolesFromPolygon(factory, geometries, geltungsbereichPolygon);
		}
		else if (geltungsbereichGeometry instanceof MultiPolygon) {
			MultiPolygon geltungsbereichMultiPolygon = (MultiPolygon) geltungsbereichGeometry;
			for (int j = 0; j < geltungsbereichMultiPolygon.getNumGeometries(); j++) {
				org.locationtech.jts.geom.Geometry geometry = geltungsbereichMultiPolygon.getGeometryN(j);
				if (geometry instanceof Polygon) {
					Polygon polygon = (Polygon) geometry;
					addHolesFromPolygon(factory, geometries, polygon);
				}
			}
		}
	}

	private void addHolesFromPolygon(GeometryFactory factory, List<org.locationtech.jts.geom.Geometry> geometries,
			Polygon geltungsbereichGeometry) {
		Polygon polygon = geltungsbereichGeometry;
		for (int j = 0; j < polygon.getNumInteriorRing(); j++) {
			LinearRing interiorRingN = polygon.getInteriorRingN(j);
			Polygon hole = factory.createPolygon(interiorRingN);
			org.locationtech.jts.geom.Geometry bufferedHole = hole.buffer(ALLOWEDDISTANCE_METRE);
			geometries.add(bufferedHole);
		}
	}

	private List<Pair<FeatureUnderTest, FeatureUnderTest>> detectFlaechenschlussFeaturePairsToAnalyse(
			List<FeatureUnderTest> featuresUnderTest) {
		List<Pair<FeatureUnderTest, FeatureUnderTest>> flaechenschlussFeaturePairs = new ArrayList<>();
		List<FeatureUnderTest> flaechenschlussFeaturesCopy = new ArrayList<>(featuresUnderTest);
		for (FeatureUnderTest flaechenschlussFeature1 : featuresUnderTest) {
			flaechenschlussFeaturesCopy.remove(flaechenschlussFeature1);
			for (FeatureUnderTest flaechenschlussFeature2 : flaechenschlussFeaturesCopy) {
				IntersectionMatrix relate = flaechenschlussFeature1.getJtsGeometry()
					.relate(flaechenschlussFeature2.getJtsGeometry());
				if (relate.isEquals(2, 2)) {
					String error = format("FlaechenschlussInspector_error_overlapping",
							flaechenschlussFeature1.getFeatureId(), flaechenschlussFeature2.getFeatureId());
					BadGeometry badGeometry = new BadGeometry(flaechenschlussFeature1.getOriginalGeometry(), error);
					badGeometries.add(badGeometry);
					flaechenschlussErrors.add(error);
				}
				else if (relate.isCoveredBy()) {
					String error = format("FlaechenschlussInspector_error_overlapping",
							flaechenschlussFeature2.getFeatureId(), flaechenschlussFeature1.getFeatureId());
					BadGeometry badGeometry = new BadGeometry(flaechenschlussFeature2.getOriginalGeometry(), error);
					badGeometries.add(badGeometry);
					flaechenschlussErrors.add(error);
				}
				else if (relate.isCovers()) {
					String error = format("FlaechenschlussInspector_error_overlapping",
							flaechenschlussFeature1.getFeatureId(), flaechenschlussFeature2.getFeatureId());
					BadGeometry badGeometry = new BadGeometry(flaechenschlussFeature1.getOriginalGeometry(), error);
					badGeometries.add(badGeometry);
					flaechenschlussErrors.add(error);
				}
				else if (relate.isIntersects()) {
					flaechenschlussFeaturePairs.add(new Pair<>(flaechenschlussFeature1, flaechenschlussFeature2));
				}
			}
		}
		return flaechenschlussFeaturePairs;
	}

	private void analyseFlaechenschlussFeaturePair(
			Pair<FeatureUnderTest, FeatureUnderTest> flaechenschlussFeaturePair) {
		FeatureUnderTest flaechenschlussFeature1 = flaechenschlussFeaturePair.getFirst();
		FeatureUnderTest flaechenschlussFeature2 = flaechenschlussFeaturePair.getSecond();
		Geometry intersection = flaechenschlussFeature1.getOriginalGeometry()
			.getIntersection(flaechenschlussFeature2.getOriginalGeometry());
		// intersection may be null if JTS.intersection returns an empty geometry
		if (intersection != null) {
			LOG.debug("Intersection of {} ({}) and {} ({}) is {}", flaechenschlussFeature1.getFeatureId(),
					flaechenschlussFeature1.getFeatureType(), flaechenschlussFeature2.getFeatureId(),
					flaechenschlussFeature2.getFeatureType(), intersection.getClass().getSimpleName());
			checkAndAddInvalidFlaechenschlussFeature(flaechenschlussFeature1, flaechenschlussFeature2, intersection,
					TestStep.FLAECHENSCHLUSSPAIRS);
		}
	}

	private void checkAndAddInvalidFlaechenschlussFeature(FeatureUnderTest flaechenschlussFeature1,
			FeatureUnderTest flaechenschlussFeature2, Geometry intersection, TestStep testStep) {
		switch (intersection.getGeometryType()) {
			case PRIMITIVE_GEOMETRY:
				checkAndAddInvalidFlaechenschlussFeature(flaechenschlussFeature1, flaechenschlussFeature2,
						(AbstractDefaultGeometry) intersection, testStep);
				break;
			case MULTI_GEOMETRY:
				checkAndAddInvalidFlaechenschlussFeature(flaechenschlussFeature1, flaechenschlussFeature2,
						(MultiGeometry<?>) intersection, testStep);
				break;
			default:
		}
	}

	private void checkAndAddInvalidFlaechenschlussFeature(FeatureUnderTest flaechenschlussFeature1,
			FeatureUnderTest flaechenschlussFeature2, MultiGeometry<?> intersection, TestStep testStep) {
		if (intersection instanceof MultiLineString)
			return;
		intersection.stream()
			.forEach(geom -> checkAndAddInvalidFlaechenschlussFeature(flaechenschlussFeature1, flaechenschlussFeature2,
					(Geometry) geom, testStep));
	}

	private void checkAndAddInvalidFlaechenschlussFeature(FeatureUnderTest flaechenschlussFeature1,
			FeatureUnderTest flaechenschlussFeature2, AbstractDefaultGeometry intersection, TestStep testStep) {
		if (!(intersection instanceof Point) && !(intersection instanceof Curve)) {
			LOG.debug("Analyse intersection of {} ({}) and {} ({}): {}", flaechenschlussFeature1.getFeatureId(),
					flaechenschlussFeature1.getFeatureType(), flaechenschlussFeature2.getFeatureId(),
					flaechenschlussFeature2.getFeatureType(), WKTWriter.write(intersection));

			List<ControlPoint> controlPointsFlaechenschlussFeature1 = parseControlPointsInIntersection(
					flaechenschlussFeature1, intersection, false);
			List<ControlPoint> controlPointsFlaechenschlussFeature2 = parseControlPointsInIntersection(
					flaechenschlussFeature2, intersection, false);
			LOG.trace("Control points of flaechenschluss feature {} ({}): {}", flaechenschlussFeature1.getFeatureId(),
					flaechenschlussFeature1.getFeatureType(),
					controlPointsFlaechenschlussFeature1.stream()
						.map(p -> WKTWriter.write(p.getPoint()))
						.collect(Collectors.joining(",")));
			LOG.trace("Control points of flaechenschluss feature {} ({}): {}", flaechenschlussFeature2.getFeatureId(),
					flaechenschlussFeature2.getFeatureType(),
					controlPointsFlaechenschlussFeature2.stream()
						.map(p -> WKTWriter.write(p.getPoint()))
						.collect(Collectors.joining(",")));

			List<ControlPoint> controlPointsToCheck = new ArrayList<>();
			controlPointsToCheck.addAll(controlPointsFlaechenschlussFeature1);
			controlPointsToCheck.addAll(controlPointsFlaechenschlussFeature2);
			checkControlPointsAndAddFailuresFlaechenschlussFeature(flaechenschlussFeature1, flaechenschlussFeature2,
					intersection, controlPointsToCheck, testStep);
		}
	}

	private void checkControlPointsAndAddFailuresFlaechenschlussFeature(FeatureUnderTest flaechenschlussFeature1,
			FeatureUnderTest flaechenschlussFeature2, AbstractDefaultGeometry intersection,
			List<ControlPoint> controlPointsToCheck, TestStep testStep) {
		boolean foundControlPointFailures = checkControlPointsAndAddFailures(controlPointsToCheck, testStep);
		if (!foundControlPointFailures && !isDiffInTolerance(intersection)) {
			String intersectionAsWkt = WKTWriter.write(intersection);
			String msg = format("FlaechenschlussInspector_error_ueberlappung", flaechenschlussFeature1.getFeatureId(),
					flaechenschlussFeature2.getFeatureId(), intersectionAsWkt);
			BadGeometry badGeometry = new BadGeometry(intersection, msg);
			if (!badGeometries.contains(badGeometry)) {
				badGeometries.add(badGeometry);
				flaechenschlussErrors.add(msg);
			}
		}
	}

	private boolean handleAsFailure(TestStep testStep) {
		if (TestStep.FLAECHENSCHLUSSPAIRS.equals(testStep))
			return true;
		if (XPLAN_40.equals(xPlanVersion) || XPLAN_41.equals(xPlanVersion) || XPLAN_50.equals(xPlanVersion)
				|| XPLAN_51.equals(xPlanVersion) || XPLAN_52.equals(xPlanVersion)) {
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

	private List<ControlPoint> parseControlPointsInIntersection(AbstractGeltungsbereichFeature flaechenschlussFeature,
			Geometry intersection, boolean handleAsHasIdenticalControlPoints) {
		List<ControlPoint> controlPointsInIntersection = new ArrayList<>();
		parseControlPointsInIntersection(flaechenschlussFeature, flaechenschlussFeature.getOriginalGeometry(),
				intersection, controlPointsInIntersection, handleAsHasIdenticalControlPoints);
		return controlPointsInIntersection;
	}

	private void parseControlPointsInIntersection(AbstractGeltungsbereichFeature flaechenschlussFeature,
			Geometry flaechenschlussFeatureGeometry, Geometry intersection,
			List<ControlPoint> controlPointsInIntersection, boolean handleAsHasIdenticalControlPoints) {
		if (flaechenschlussFeatureGeometry instanceof Surface) {
			Surface surface = (Surface) flaechenschlussFeatureGeometry;
			controlPointsInIntersection.addAll(parseControlPointsInIntersection(flaechenschlussFeature.getFeatureId(),
					surface, intersection, handleAsHasIdenticalControlPoints));
		}
		else if (flaechenschlussFeatureGeometry instanceof MultiSurface) {
			MultiSurface<?> multiSurface = (MultiSurface<?>) flaechenschlussFeatureGeometry;
			multiSurface.stream()
				.forEach(o -> parseControlPointsInIntersection(flaechenschlussFeature, (Geometry) o, intersection,
						controlPointsInIntersection, handleAsHasIdenticalControlPoints));
		}
	}

	private List<ControlPoint> parseControlPointsInIntersection(String featureId, Surface surface,
			Geometry intersection, boolean handleAsHasIdenticalControlPoints) {
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
		Geometry intersectionWithBuffer = intersection
			.getBuffer(new Measure(new BigDecimal(ALLOWEDDISTANCE_METRE), "m"));
		List<ControlPoint> allPoints = new ArrayList<>();
		for (Points points : pointsList) {
			Iterator<Point> iterator = points.iterator();
			while (iterator.hasNext()) {
				Point point = iterator.next();
				if (intersectionWithBuffer.intersects(point)) {
					allPoints.add(new ControlPoint(featureId, point, handleAsHasIdenticalControlPoints));
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
