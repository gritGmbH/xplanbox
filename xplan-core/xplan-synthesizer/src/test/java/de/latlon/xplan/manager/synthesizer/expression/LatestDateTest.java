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
package de.latlon.xplan.manager.synthesizer.expression;

import de.latlon.xplan.manager.synthesizer.PlanContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.SimpleProperty;
import org.junit.jupiter.api.Test;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.load;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class LatestDateTest {

	@Test
	void testEvaluate_Empty() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = load(XPLAN_51, "Praesentationsobjekte.gml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		LatestDate latestDate = new LatestDate(new Xpath("xplan:auslegungsStartDatum"));
		TypedObjectNode property = latestDate.evaluate(feature, features, planContext);
		assertNull(property);
	}

	@Test
	void testEvaluate() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = load(XPLAN_51, "MultipleDates.gml");
		Feature feature = getTestFeature(features, "BP_PLAN");
		LatestDate latestDate = new LatestDate(new Xpath("xplan:auslegungsStartDatum"));
		SimpleProperty property = (SimpleProperty) latestDate.evaluate(feature, features, planContext);
		assertEquals("1998-01-01", property.getValue().getAsText());
	}

}
