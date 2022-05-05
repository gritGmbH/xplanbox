package de.latlon.xplan.manager.synthesizer.expression.flatten.xp;

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

import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.XplanFlattenProperty;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpTextAbschnittFlattenerTest {

	@Test
	public void testFlattenTexte() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_51, "flatten/XpTextAbschnittFlattener.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:texte"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[§2 Nr.21 | text 3][§2 Nr.1 | text 1][§2 Nr.3 | text 2]", value.toString());
	}

	@Test
	public void testFlattenTexte_sorted() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_51, "flatten/XpTextAbschnittFlattener.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:texte"), true);
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("[§2 Nr.1 | text 1][§2 Nr.3 | text 2][§2 Nr.21 | text 3]", value.toString());
	}

	@Test
	public void testFlattenTexte_refText() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_51, "flatten/XpTextAbschnittWithRefText.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:texte"), true);
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[§2 Nr.9 | Externe Referenz: schluesseltest.pdf][§2 Nr.21 | text 3 | Externe Referenz: test.pdf][Externe Referenz: test.pdf]",
				value.toString());
	}

	@Test
	public void testEvaluate() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Baugebiet_1");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:refTextInhalt"), true);
		PrimitiveValue abschnitte = expr.evaluate(feature, features);
		assertEquals(
				"[text1 | Das ist Textabschnitt No 1 | Externe Referenz: text1.pdf][text2 | Das ist Textabschnitt No 2 (Gesetzliche Grundlage: BGB) | Externe Referenz: text2.pdf]",
				abschnitte.toString());
	}

}
