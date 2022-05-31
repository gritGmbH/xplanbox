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
package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XplanCodeLookupTest {

	@Test
	public void testEvaluateXplan3() {
		FeatureCollection features = getTestFeatures(XPLAN_3);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XplanCodeLookup expr = new XplanCodeLookup(new Xpath("xplan:planArt"), "BP_PlanArt");
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("BPlan", value + "");
	}

	@Test
	public void testEvaluateXplan40() {
		FeatureCollection features = getTestFeatures(XPLAN_40);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XplanCodeLookup expr = new XplanCodeLookup(new Xpath("xplan:planArt"), "BP_PlanArt");
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("BPlan", value + "");
	}

	@Test
	public void testEvaluateXplan41() {
		FeatureCollection features = getTestFeatures(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XplanCodeLookup expr = new XplanCodeLookup(new Xpath("xplan:planArt"), "BP_PlanArt");
		PrimitiveValue value = expr.evaluate(feature, features);
		assertEquals("BPlan", value + "");
	}

}
