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
package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.XplanFlattenProperty;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XpVerbundenerPlanFlattenerTest {

	@Test
	public void testFlattenAendert() {
		FeatureCollection features = getTestFeatures(XPLAN_51, "flatten/XpVerbundenerPlanFlattener.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:aendert"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[Verbundener Plan: Heideweg1|Rechtscharakter Planänderung: Ergaenzung|Nummer verbundener Plan: 42]",
				value.toString());
	}

	@Test
	public void testFlattenWurdeGeaendertVon() {
		FeatureCollection features = getTestFeatures(XPLAN_51, "flatten/XpVerbundenerPlanFlattener.xml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		XplanFlattenProperty expr = new XplanFlattenProperty(new Xpath("xplan:wurdeGeaendertVon"));
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals(
				"[Verbundener Plan: Heideweg8|Rechtscharakter Planänderung: Aufhebung|Nummer verbundener Plan: 88]",
				value.toString());
	}

}
