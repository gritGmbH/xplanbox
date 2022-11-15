/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.flatten.complex;

import de.latlon.xplan.manager.dictionary.XPlanCodelists;
import de.latlon.xplan.manager.dictionary.XPlanDictionaries;
import de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XplanFlattenProperty;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.load;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ComplexFlattenerTest {

	@Test
	public void testFlatten_KomplexeZweckbestimmung_zweckbestimmungTranslate() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/BPlan001_6-0.zip");
		Feature feature = getTestFeature(features, "GML_fa0eea57-ebb1-4d50-b205-95865d6b9284");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:zweckbestimmung"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Allgemein: Naturerfahrungsraum|Aufschrift: Grüne Hölle]", value.toString());
	}

	@Test
	public void testFlatten_KomplexeZweckbestimmung_zweckbestimmungCode() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/BPlan001_6-0.zip");
		Feature feature = getTestFeature(features, "GML_fa0eea57-ebb1-4d50-b205-95865d6b9284");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:zweckbestimmung"),
				false, true);
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Allgemein: 2700|Aufschrift: Grüne Hölle]", value.toString());
	}

	@Test
	public void testFlatten_KomplexeZweckbestimmung_zweckbestimmungTranslate_Code_MissingCodelist() throws Exception {
		FeatureCollection features = load(XPLAN_60, "flatten/BP_KomplexeZweckbestGruen.xml");
		Feature feature = getTestFeature(features, "GML_fa0eea57-ebb1-4d50-b205-95865d6b9284");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:zweckbestimmung"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[Allgemein: Naturerfahrungsraum|Detail: 1000|Textliche Ergänzung: Textliche Ergänzung|Aufschrift: Grüne Hölle]",
				value.toString());
	}

	@Test
	public void testFlatten_KomplexeZweckbestimmung_zweckbestimmungTranslate_Code_WithCodelist() throws Exception {
		FeatureCollection features = load(XPLAN_60, "flatten/BP_KomplexeZweckbestGruen.xml");
		Feature feature = getTestFeature(features, "GML_fa0eea57-ebb1-4d50-b205-95865d6b9284");
		XplanFlattenProperty expr = new XplanFlattenProperty(mockXPlanCodelists(), new Xpath("xplan:zweckbestimmung"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[Allgemein: Naturerfahrungsraum|Detail: Uebersetzte1000|Textliche Ergänzung: Textliche Ergänzung|Aufschrift: Grüne Hölle]",
				value.toString());
	}

	private XPlanCodelists mockXPlanCodelists() {
		XPlanDictionaries codelists = mock(XPlanDictionaries.class);
		when(codelists.getTranslation(eq("BP_DetailZweckbestGruenFlaeche"), eq("1000"))).thenReturn("Uebersetzte1000");
		XPlanCodelists xPlanCodelists = mock(XPlanCodelists.class);
		when(xPlanCodelists.getCodelists(XPLAN_60)).thenReturn(codelists);
		return xPlanCodelists;
	}

	@Test
	public void testFlatten_KomplexeZweckbestimmung_zweckbestimmungCode_Code() throws Exception {
		FeatureCollection features = load(XPLAN_60, "flatten/BP_KomplexeZweckbestGruen.xml");
		Feature feature = getTestFeature(features, "GML_fa0eea57-ebb1-4d50-b205-95865d6b9284");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:zweckbestimmung"),
				false, true);
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Allgemein: 2700|Detail: 1000|Textliche Ergänzung: Textliche Ergänzung|Aufschrift: Grüne Hölle]",
				value.toString());
	}

	@Test
	public void testFlatten_BPDachgestaltung() throws Exception {
		FeatureCollection features = load(XPLAN_51, "flatten/BpDachgestaltung.xml");
		Feature feature = getTestFeature(features, "BP_BAUGEBTF");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:dachgestaltung"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[Dachneigung: 7|Dachneigung Min: 9|Dachneigung Max: 90|Dachneigung Zwingend: 8|Dachform: Kegeldach]",
				value.toString());
	}

	@Test
	public void testFlatten_LpEingriffsregelungKomplex() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/LP-Test_60.zip");
		Feature feature = getTestFeature(features, "Gml_35828929-2C80-4454-A4E3-8EA08D4F5D13");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(),
				new Xpath("xplan:eingriffsregelungFlaechenTyp"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Flächenart: Potenzielle Fläche Kompensation]", value.toString());
	}

	@Test
	public void testFlatten_SoKomplexeFestlegungGewaesser() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/FNP_Test_60.zip");
		Feature feature = getTestFeature(features, "GML_8FDB6A1E-EFF1-9413-3AE1-B3CDAC3D573DA");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:artDerFestlegung"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Allgemeine Zweckbestimmung: Stehendes Gewässer]", value.toString());
	}

	@Test
	public void testFlatten_SoKomplexeZweckbestStrassenverkehr() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/FNP_Test_60.zip");
		Feature feature = getTestFeature(features, "GML_2F2B7735-5081-24D2-6BFB-FF4545106711D");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:artDerFestlegung"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Allgemeine Zweckbestimmung: Ruhender Verkehr]", value.toString());
	}

	@Test
	public void testFlatten_XpGesetzlicheGrundlage_gesetzlicheGrundlage() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/FNP_Test_60.zip");
		Feature feature = getTestFeature(features, "GML_24922052-2F62-B415-9862-CA135DBEC0CBA");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(),
				new Xpath("xplan:gesetzlicheGrundlage"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Name: Kurortegesetz, KurorteG]", value.toString());
	}

	@Test
	public void testFlatten_XpGesetzlicheGrundlage_versionBauGB() throws Exception {
		FeatureCollection features = TestFeaturesUtils.load("xplan60/BPlan002_6-0.zip");
		Feature feature = getTestFeature(features, "GML_bf2168c4-c292-4340-bc50-7a2aa2cab5be");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:versionBauGB"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Name: BaugGB|Datum: 2004-03-02|Detail: §10]", value.toString());
	}

	@Test
	public void testFlatten_XpSPEMassnahmenDaten() throws Exception {
		FeatureCollection features = load(XPLAN_51, "flatten/XpSPEMassnahmenDaten.xml");
		Feature feature = getTestFeature(features, "BP_SCHUTZPFLENTWFLAECHE");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:massnahme"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[Maßnahme: Trockenrasen]", value.toString());
	}

	@Test
	public void testFlatten_XpVerbundenerPlan_aendert() throws Exception {
		FeatureCollection features = load(XPLAN_51, "flatten/XpVerbundenerPlanFlattener.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(), new Xpath("xplan:aendert"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[Verbundener Plan: Heideweg1|Rechtscharakter Planänderung: Ergaenzung|Nummer verbundener Plan: 42]",
				value.toString());
	}

	@Test
	public void testFlatten_XpVerbundenerPlan_wurdeGeaendertVon() throws Exception {
		FeatureCollection features = load(XPLAN_51, "flatten/XpVerbundenerPlanFlattener.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(),
				new Xpath("xplan:wurdeGeaendertVon"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[Verbundener Plan: Heideweg8|Rechtscharakter Planänderung: Aufhebung|Nummer verbundener Plan: 88]",
				value.toString());
	}

}
