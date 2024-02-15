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

import org.junit.Ignore;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromGml;
import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromZip;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OptimisedFlaechenschlussInspectorTest {

	@Ignore
	@Test
	public void testCheckFlaechenschluss_executionTime() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_54,
				BP_Plan);
		long start = System.currentTimeMillis();
		readFeaturesFromZip("Testplan.zip", OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);
		flaechenschlussInspector.checkGeometricRule();
		long end = System.currentTimeMillis();
		long timeNeeded = end - start;
		System.out.println("Flaechenschluss with optimized implementation: " + timeNeeded + " [ms]");
	}

	@Test
	public void testCheckFlaechenschluss() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_51,
				BP_Plan);
		readFeaturesFromZip("xplan51/BPlan001_5-1.zip", flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheckFlaechenschluss_wirksamkeit() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_51,
				BP_Plan);
		readFeaturesFromGml("V4_1_ID_103_wirksamkeit.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheckFlaechenschluss_invalid() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_51,
				BP_Plan);
		readFeaturesFromGml("V4_1_ID_103_kein-flaechenschluss.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
	}

	@Test
	public void testCheckFlaechenschluss_equalFlaechenschlussGeometries() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_51,
				BP_Plan);
		readFeaturesFromGml("equalFlaechenschlussGeometries.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(flaechenschlussInspector.getErrors().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_vollstaendigeUeberlappungFlaechenschlussGeometries() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_51,
				BP_Plan);
		readFeaturesFromGml("xplan52_Flaechenschlussfehler_vollstaendige_Ueberlappung.gml",
				OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(flaechenschlussInspector.getErrors().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_LueckeGeltungsbereich() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_52,
				BP_Plan);
		readFeaturesFromGml("xplan52_Flaechenschlussfehler_Luecke_Geltungsbereich.gml",
				OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(7));
	}

	@Test
	public void testCheckFlaechenschluss_Luecken() throws Exception {
		// same plan as xplan52_Flaechenschlussfehler_Luecke_Geltungsbereich.gml but
		// XPlanGML 5.4
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_54,
				BP_Plan);
		readFeaturesFromGml("xplan54_Flaechenschlussfehler_Luecken.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		System.out.println(flaechenschlussInspector.getWarnings());
		assertThat(flaechenschlussInspector.getWarnings().size(), is(7));
	}

	@Test
	public void testCheckFlaechenschluss_TestLuecke() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_52,
				BP_Plan);
		readFeaturesFromGml("Test_Luecke.gml", OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(2));
	}

	@Test
	public void testCheckFlaechenschluss_TestInsel() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_52,
				BP_Plan);
		readFeaturesFromGml("FNP_Insel-Test.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(0));
	}

	@Test
	public void testCheckFlaechenschluss_TestAenderungsplanWithLuecke() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_52,
				BP_Plan);
		readFeaturesFromGml("xplan60_Aenderungsplan_Luecke.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_TestAenderungsplanValid() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_52,
				BP_Plan);
		readFeaturesFromGml("xplan60_Aenderungsplan_valide.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(0));
	}

	@Test
	public void testCheckFlaechenschluss_Toleranz() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_52,
				BP_Plan);
		readFeaturesFromGml("Flaechenschluss-Test_1mm.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(0));
	}

	@Test
	public void testCheckFlaechenschluss_Toleranz_nichtUeberlappend() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_52,
				BP_Plan);
		readFeaturesFromGml("Flaechenschluss-Test_1mm-nichtUeberlappend.gml",
				OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(0));
	}

	@Test
	public void testCheckFlaechenschluss_Luecke_Ebene1() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_51,
				BP_Plan);
		readFeaturesFromGml("xplan51-5_1_4_1_Flaechenschlussfehler.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(2));
		assertThat(flaechenschlussInspector.getWarnings(), hasItem(
				"2.2.1.1: Die Flächenschlussbedingung ist nicht erfüllt, es wurde ein Lücke bei der Prüfung des Geltungsbereichs des Bereichs identifiziert. Die Geometrie mit der Lücke wird in der Shape-Datei ausgegeben."));
		assertThat(flaechenschlussInspector.getWarnings(), hasItem(
				"2.2.1.1: Die Flächenschlussbedingung ist nicht erfüllt, es wurde ein Lücke bei der Prüfung des Geltungsbereichs des Bereichs identifiziert. Die Geometrie mit der Lücke wird in der Shape-Datei ausgegeben."));
	}

	@Test
	public void testCheckFlaechenschluss_Luecke_Missing() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_51,
				BP_Plan);
		readFeaturesFromGml("xplan51-5_1_4_2_Flaechenschlussfehler.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(2));
		assertThat(flaechenschlussInspector.getWarnings(), hasItem(
				"2.2.1.1: Die Flächenschlussbedingung ist nicht erfüllt, es wurde ein Lücke bei der Prüfung des Geltungsbereichs des Plans identifiziert. Die Geometrie mit der Lücke wird in der Shape-Datei ausgegeben."));
		assertThat(flaechenschlussInspector.getWarnings(), hasItem(
				"2.2.1.1: Die Flächenschlussbedingung ist nicht erfüllt, es wurde ein Lücke bei der Prüfung des Geltungsbereichs des Bereichs identifiziert. Die Geometrie mit der Lücke wird in der Shape-Datei ausgegeben."));
	}

	@Test
	public void testCheckFlaechenschluss_Luecke_FlaechenschlussFalse() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_51,
				BP_Plan);
		readFeaturesFromGml("xplan51-5_5_4_2_Flaechenschlussfehler.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(2));
		assertThat(flaechenschlussInspector.getWarnings(), hasItem(
				"2.2.1.1: Die Flächenschlussbedingung ist nicht erfüllt, es wurde ein Lücke bei der Prüfung des Geltungsbereichs des Plans identifiziert. Die Geometrie mit der Lücke wird in der Shape-Datei ausgegeben."));
		assertThat(flaechenschlussInspector.getWarnings(), hasItem(
				"2.2.1.1: Die Flächenschlussbedingung ist nicht erfüllt, es wurde ein Lücke bei der Prüfung des Geltungsbereichs des Bereichs identifiziert. Die Geometrie mit der Lücke wird in der Shape-Datei ausgegeben."));
	}

	@Test
	public void testCheckFlaechenschluss_Luecke_RandGeltungsbereich() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_53,
				BP_Plan);
		readFeaturesFromGml("xplan53-4_1_7_1_Flaechenschlussfehler.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_Luecke_LochGeltungsbereichImToleranzbereich() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_54,
				BP_Plan);
		readFeaturesFromGml("BPlan001_5-4_Toleranz_Geltungsbereich_0008.gml",
				OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(0));
	}

	@Test
	public void testCheckFlaechenschluss_missing_gehoertZuBereich() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_54,
				BP_Plan);
		readFeaturesFromGml("test41-54_1-missing-gehoertZuBereich.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getErrors().size(), is(0));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(2));
	}

	@Test
	public void testCheckFlaechenschluss_missing_gehoertZuBereich_multipleBereiche() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_54,
				BP_Plan);
		readFeaturesFromGml("test41-54_1-missing-gehoertZuBereich-multipleBereiche.gml",
				OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(flaechenschlussInspector.getErrors().size(), is(1));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(0));
	}

	@Test
	public void testCheckFlaechenschluss_OneFlaechenschlussFeature_Luecke() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_54,
				BP_Plan);
		readFeaturesFromGml("Test_Insel_Luecke_5mm.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_TeilbereichAusserhalbPlanbereich() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_60,
				BP_Plan);
		readFeaturesFromGml("BPlan_TeilbereichAusserhalbPlanbereich_6-0-2.gml",
				OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(flaechenschlussInspector.getErrors().size(), is(1));
		assertThat(flaechenschlussInspector.getBadGeometries().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_Kompensationsbereich() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_60,
				BP_Plan);
		readFeaturesFromGml("BPlan_Kompensationsbereich_6-0-2.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheckFlaechenschluss_Kompensationsbereich_Luecke() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_60,
				BP_Plan);
		readFeaturesFromGml("BPlan_Kompensationsbereich_6-0-2_Luecke.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(flaechenschlussInspector.getErrors().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_Kompensationsbereich_Ueberlappung() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_60,
				BP_Plan);
		readFeaturesFromGml("BPlan_Kompensationsbereich_6-0-2_Ueberlappung.gml",
				OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(flaechenschlussInspector.getErrors().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_Wirksamkeit_valide() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_60,
				BP_Plan);
		readFeaturesFromGml("BPlan_Wirksamkeit_6-0-2_valide.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheckFlaechenschluss_Wirksamkeit_invalide() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_60,
				BP_Plan);
		readFeaturesFromGml("BPlan_Wirksamkeit_6-0-2_invalide.gml", OptimisedFlaechenschlussInspectorTest.class,
				flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(flaechenschlussInspector.getErrors().size(), is(1));
	}

	@Test
	public void testCheckFlaechenschluss_MultipleBereicheAndPlan_OneBereichWithoutGeom() throws Exception {
		OptimisedFlaechenschlussInspector flaechenschlussInspector = new OptimisedFlaechenschlussInspector(XPLAN_54,
				BP_Plan);
		readFeaturesFromGml("BPlan_5_4-MultipleBereicheAndPlan_OneBereichWithoutGeom.gml",
				OptimisedFlaechenschlussInspectorTest.class, flaechenschlussInspector);

		boolean isValid = flaechenschlussInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(flaechenschlussInspector.getWarnings().size(), is(0));
	}

}
