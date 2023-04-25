/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.dictionary;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XplanEnumerationLookupTest {

	@Test
	public void testEvaluateXplan40() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_40);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XPlanEnumerationLookup expr = new XPlanEnumerationLookup(new Xpath("xplan:planArt"), "BP_PlanArt");
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertThat(value.getAsText(), is("BPlan"));
	}

	@Test
	public void testEvaluateXplan41() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_41);
		Feature feature = getTestFeature(features, "BP_Plan_1");
		XPlanEnumerationLookup expr = new XPlanEnumerationLookup(new Xpath("xplan:planArt"), "BP_PlanArt");
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertThat(value.getAsText(), is("BPlan"));
	}

	@Test
	public void testComplexProperty() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_60);
		Feature feature = getTestFeature(features, "GML_fa0eea57-ebb1-4d50-b205-95865d6b9284");
		Xpath xpath = new Xpath("xplan:zweckbestimmung/xplan:BP_KomplexeZweckbestGruen/xplan:allgemein");
		XPlanEnumerationLookup expr = new XPlanEnumerationLookup(xpath, "XP_ZweckbestimmungGruen");
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertThat(value.getAsText(), is("Parkanlage"));
	}

}
