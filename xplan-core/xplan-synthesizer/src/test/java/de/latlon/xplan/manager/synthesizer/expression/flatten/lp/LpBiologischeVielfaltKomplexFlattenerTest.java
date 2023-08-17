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
package de.latlon.xplan.manager.synthesizer.expression.flatten.lp;

import de.latlon.xplan.manager.dictionary.XPlanCodelists;
import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import de.latlon.xplan.manager.synthesizer.expression.flatten.XplanFlattenProperty;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanType.LP_Plan;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LpBiologischeVielfaltKomplexFlattenerTest {

	@Test
	public void testFlatten_biologischeVielfalt() throws Exception {
		PlanContext planContext = new PlanContext(LP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load("xplan60/LP-Test_60.zip");
		Feature feature = getTestFeature(features, "Gml_8AC988AC-0E6C-44C6-9522-A32244FBBCE0");
		XplanFlattenProperty expr = new XplanFlattenProperty(new XPlanCodelists(),
				new Xpath("xplan:biologischeVielfalt"));
		PrimitiveValue value = expr.evaluate(feature, features, planContext);
		assertEquals(
				"[Biologische Vielfalt: [Bestandteil: Lebensstätte, Arthabitat]|von gemeinschaftlichem Interesse kartiert: ja]",
				value.toString());
	}

}
