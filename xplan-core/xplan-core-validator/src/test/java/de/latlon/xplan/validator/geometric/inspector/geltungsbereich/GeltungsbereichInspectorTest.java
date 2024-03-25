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

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromGml;
import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromZip;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichInspectorTest {

	@Test
	public void testCheck() throws Exception {
		long startTimeMillis = System.currentTimeMillis();
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_51);
		readFeaturesFromZip("xplan51/BPlan001_5-1.zip", geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		long endTimeMillis = System.currentTimeMillis();
		assertThat(isValid, is(true));
		assertThat(geltungsbereichInspector.getErrors().size(), is(0));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(0));

		System.out.println(String.format("Geltungsbereich check needed %s [ms]", endTimeMillis - startTimeMillis));
	}

	@Test
	public void testCheck_invalid_withLine() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_51);
		readFeaturesFromZip("xplan51/BP2070.zip", geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(2));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(2));

		BadGeometry badGeometry1 = geltungsbereichInspector.getBadGeometries().get(0);
		assertThat(badGeometry1.getOriginalGeometry(), is(notNullValue()));
		assertThat(badGeometry1.getMarkerGeometries().size(), is(1));

		BadGeometry badGeometry2 = geltungsbereichInspector.getBadGeometries().get(1);
		assertThat(badGeometry2.getOriginalGeometry(), is(notNullValue()));
		assertThat(badGeometry2.getMarkerGeometries().size(), is(2));
	}

	@Test
	public void testCheck_valid_tolerance() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_51);
		readFeaturesFromGml("V4_1_ID_103_withtolerance.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheck_MultipePlanNoBereich() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_54);
		readFeaturesFromGml("HafenCity11_HafenCity14_Bereich_ohne_Geometrie.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheck_missing_gehoertZuBereich() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_41);
		readFeaturesFromGml("test41-54_1-missing-gehoertZuBereich.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(geltungsbereichInspector.getWarnings().size(), is(0));
		assertThat(geltungsbereichInspector.getErrors().size(), is(0));
	}

	@Test
	public void testCheck_missing_gehoertZuBereich_multipleBereiche() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_41);
		readFeaturesFromGml("test41-54_1-missing-gehoertZuBereich-multipleBereiche.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(geltungsbereichInspector.getWarnings().size(), is(0));
		assertThat(geltungsbereichInspector.getErrors().size(), is(0));
	}

	@Test
	public void testCheck_LinienPolygon() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_54);
		readFeaturesFromGml("BPlan001_5-4_Sliver-Polygon.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheck_ZweiSchnittpunkteGeltungsbereich_Polygon() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("BPlan002_5-2_Schnittpunkt_Geltungsbereich.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getErrors().get(0), containsString("574547.88296709,5947355.4007816175"));
		assertThat(geltungsbereichInspector.getErrors().get(0), containsString("574620.3839,5947354.7003"));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(1));
	}

	@Test
	public void testCheck_1_Schnittpunkte_Geltungsbereich_Polygon() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("1_Schnittpunkte_Geltungsbereich_Polygon.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_27BB7449-1EFB-4947-BA10-DDF479C4D9C1"));
		assertThat(error, containsString("(574515.3505587827,5947128.145350721)"));
		assertThat(error, containsString("(574677.987755435,5947128.829007624)"));
		assertThat(error, not(containsString("(574748.6957451908,5947180.179117006)")));
		assertThat(error, not(containsString("(574748.8564204557,5947139.54031087)")));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(1));
	}

	@Test
	public void testCheck_2_Schnittpunkte_Geltungsbereich_Polygon() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("2_Schnittpunkte_Geltungsbereich_Polygon.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_27BB7449-1EFB-4947-BA10-DDF479C4D9C1"));
		assertThat(error, containsString("(574515.3505587532,5947128.14535072)"));
		assertThat(error, containsString("(574566.816,5947128.362)"));
		assertThat(error, containsString("(574677.9877482426,5947128.829007594)"));
		assertThat(error, not(containsString("(574748.7651186404,5947162.62734369)")));
		assertThat(error, not(containsString("(574748.8842241626,5947132.571045563)")));
		assertThat(error, not(containsString("(574748.851,5947140.899)")));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(2));
	}

	@Test
	public void testCheck_3_Schnittpunkte_Geltungsbereich_Polygon() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("3_Schnittpunkte_Geltungsbereich_Polygon.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_99EFCE54-6C2F-41CF-824B-F5A193BB7017"));

		assertThat(error, containsString("(574660.776,5947128.758)"));
		assertThat(error, containsString("(574498.8049,5947128.0757)"));

		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(1));
	}

	@Test
	public void testCheck_4_Schnittpunkte_Geltungsbereich_Polygon() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("4_Schnittpunkte_Geltungsbereich_Polygon.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_407A895A-D256-4AC4-A72B-BEDDE2373AB4"));

		assertThat(error, containsString("(574626.3768727767,5947128.613022836)"));
		assertThat(error, containsString("(574695.0096843867,5947128.899232112)"));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(1));
	}

	@Test
	public void testCheck_5_Schnittpunkte_Geltungsbereich_Linie() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("5_Schnittpunkte_Geltungsbereich_Linie.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_680BB290-67C9-4231-B2F2-EE84F6A4ED6A"));

		assertThat(error, containsString("(574767.01,5947198.217)"));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(1));
	}

	@Test
	public void testCheck_6_Schnittpunkte_Geltungsbereich_Linie() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("6_Schnittpunkte_Geltungsbereich_Linie.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_187912A3-F601-4334-8868-64C917A46ED2"));
		assertThat(error, containsString("(574391.1320890521,5947122.911581698)"));
		assertThat(error, not(containsString("(574514.2873433107,5947128.140875001)")));
		assertThat(error, not(containsString("(574525.4640847385,5947128.18792469)")));
		assertThat(error, not(containsString("(574575.1600013181,5947128.397166288)")));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(1));
	}

	@Test
	public void testCheck_7_Schnittpunkte_Geltungsbereich_Linie() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("7_Schnittpunkte_Geltungsbereich_Linie.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_0B9CB99B-923E-416D-9F81-D2D0A7E395B4"));
		assertThat(error, containsString("(574435.849566645,5947124.208629552)"));
		assertThat(error, not(containsString("(574491.1751997543,5947128.043805587)")));
		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(1));
	}

	@Test
	public void testCheck_8_Schnittpunkte_Geltungsbereich_Linie() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("8_Schnittpunkte_Geltungsbereich_Linie.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_2098707D-58D1-4407-ACD3-8A8F5B3BBD43"));

		assertThat(error, containsString("(574411.8776150414,5947123.258430074)"));
		assertThat(error, containsString("(574476.3185422348,5947127.718108666)"));
		assertThat(error, containsString("(574564.2880092764,5947128.351358152)"));
		assertThat(error, not(containsString("(574677.8111035376,5947128.828278841)")));

		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(2));
	}

	/**
	 * BEGIN: https://www.jira.geoportal-hamburg.de/browse/XPLANBOX-1856
	 *
	 * <pre>
	 * Systematische Tests für die Prüfung gegen Geltunsgbereich des Plans (GP) und
	 * Bereichs (GB): Abbildung in SystematischeGeltungsbereichspruefung_GPundGB.jpg
	 *
	 * Tabelle:
	 *
	 * Nr | Beziehung Ojekt Plan/Bereich		| Gesamterg. | Ergebnis GP | Ergebnis GB
	 *  1 | Objekt in GP und GB 				| 	ok 		 | 	ok		   |	ok
	 *  2 | Objekt schneidet GP und GB 			| 	nOk		 | 	nOk		   |	nOk
	 *  3 | Objekt in GP, außerhalb GB 			| 	ok 		 | 	ok		   |	nOk
	 *  4 | Objekt schneidet GP, außerhalb GB 	| 	ok 		 | 	nOk		   |	nOk
	 *  5 | Objekt außerhalb GP, in GB 			| 	ok 		 | 	nOk		   |	ok
	 *  6 | Objekt außerhalb GP, schneidet GB 	| 	ok 		 | 	nOk		   |	nOk
	 *  7 | Objekt außerhalb GP, außerhalb GB 	| 	ok 		 | 	nOk		   |	nOk
	 *
	 * </pre>
	 **/
	@Test
	public void testCheck_1_GeltungsbereichPlanBereich_Objekt_InGP_InGB_valide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("1_Geltungsbereich-Test.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
		assertThat(geltungsbereichInspector.getErrors().size(), is(0));
	}

	@Test
	public void testCheck_2_GeltungsbereichPlanBereich_Objekt_SchneidetGP_SchneidetGB_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("2_Geltungsbereich-Test.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(2));
	}

	@Test
	public void testCheck_3_GeltungsbereichPlanBereich_Objekt_InGP_AusserhalbGB_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("3_Geltungsbereich-Test.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
	}

	@Test
	public void testCheck_4_GeltungsbereichPlanBereich_Objekt_SchneidetGP_AusserhalbGB_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("4_Geltungsbereich-Test.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(2));
	}

	@Test
	public void testCheck_5_GeltungsbereichPlanBereich_Objekt_AusserhalbGP_InGB_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("5_Geltungsbereich-Test.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
	}

	@Test
	public void testCheck_6_GeltungsbereichPlanBereich_Objekt_AusserhalbGP_SchneidetGB_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("6_Geltungsbereich-Test.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(2));
	}

	@Test
	public void testCheck_7_GeltungsbereichPlanBereich_Objekt_AusserhalbGP_AusserhalbGB_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_52);
		readFeaturesFromGml("7_Geltungsbereich-Test.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
	}

	/**
	 * END: https://www.jira.geoportal-hamburg.de/browse/XPLANBOX-1856
	 */

	/**
	 * Die Geometrie des Fachobjekts entspricht exakt dem Geltungsbereich des Plans. Das
	 * Validierungsergebnis ist: valide.
	 */
	@Test
	public void testCheck_FeatureGeometryIsGeltungsbereich() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_51);
		readFeaturesFromGml("Testplan_position_geltungsbereich.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	/**
	 * Die Geometrie des Fachobjekts entspricht exakt dem Geltungsbereich des Plans, der
	 * Geltungsbereich des Plans hat aber ein Loch, die Geometrie des Fachobjekts nicht.
	 * Das Validierungsergebnis ist: invalide. Es wird kein Schnittpunkt ausgegegen.
	 */
	@Test
	public void testCheck_FeatureGeometryIsGeltungsbereichWithoutHole() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_51);
		readFeaturesFromGml("Testplan_position_geltungsbereichLoch.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("GML_046c1737-4b09-4b0e-8271-0632eb0d62e0"));

		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(0));
	}

	@Test
	public void testCheck_Teilbereich_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_51);
		readFeaturesFromGml("BPlan_Teilbereich_6-0-2_invalide.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_303B15E7-1CC4-4829-A81F-5CAA4E7D1E74"));

		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(0));
	}

	@Test
	public void testCheck_Kompensationsbereich_valide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_51);
		readFeaturesFromGml("BPlan_Kompensationsbereich_6-0-2_valide.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(true));
	}

	@Test
	public void testCheck_Linie_Ausserhalb_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_60);
		readFeaturesFromGml("BPlan_6-0_Linie_ausserhalb.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_020FEBF9-58C5-48B2-8091-CCA81ABFB03A"));
		assertThat(error, containsString("(557151.6116, 5936529.2681)"));

		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(0));
	}

	@Test
	public void testCheck_Polygon_Ausserhalb_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_60);
		readFeaturesFromGml("BPlan_6-0_Polygon_ausserhalb.gml", GeltungsbereichInspector.class,
				geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);
		assertThat(error, containsString("Gml_758D6453-D193-40D3-964F-30E05A6BDB5D"));
		assertThat(error, containsString("(557476.6925, 5936735.4001)"));

		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(0));
	}

	@Test
	public void testCheck_Punkt_Ausserhalb_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_60);
		readFeaturesFromGml("BPlan_6-0_Punkt_ausserhalb.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);

		assertThat(error, containsString("Gml_86CEE2F5-F5F9-4F06-962F-33469C2ACB5D"));
		assertThat(error, containsString("(557066.0319, 5936740.9087)"));

		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(0));
	}

	@Test
	public void testCheck_Punkt_Ausserhalb_Plan_invalide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_60);
		readFeaturesFromGml("BP_5.1.gml", GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(false));
		assertThat(geltungsbereichInspector.getErrors().size(), is(1));
		String error = geltungsbereichInspector.getErrors().get(0);

		assertThat(error, containsString("FEATURE_dc61f364-f67c-4e0f-846c-fb8da12644fe"));
		assertThat(error, containsString("(418279.388, 5715854.865)"));

		assertThat(geltungsbereichInspector.getBadGeometries().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getErrors().size(), is(1));
		assertThat(geltungsbereichInspector.getBadGeometries().get(0).getMarkerGeometries().size(), is(0));
	}

}
