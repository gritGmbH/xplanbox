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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import de.latlon.xplan.manager.synthesizer.PlanContext;
import de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils;
import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AusrichtungTest {

	@Test
	public void testEvaluate_Empty() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_51, "Praesentationsobjekte.gml");
		Feature feature = TestFeaturesUtils.getTestFeature(features, "XP_PTO_EMPTY");
		Ausrichtung horzontaleAusrichtung = new Ausrichtung(new Xpath("xplan:horizontaleAusrichtung"));
		TypedObjectNode horizontaleValue = horzontaleAusrichtung.evaluate(feature, features, planContext);
		assertThat(asDouble(horizontaleValue), is(0.5));

		Ausrichtung vertikaleAusrichtung = new Ausrichtung(new Xpath("xplan:vertikaleAusrichtung"));
		TypedObjectNode vertikaleValue = vertikaleAusrichtung.evaluate(feature, features, planContext);
		assertThat(asDouble(vertikaleValue), is(0.5));
	}

	@Test
	public void testEvaluate() throws Exception {
		PlanContext planContext = new PlanContext(BP_Plan, "dummy");
		FeatureCollection features = TestFeaturesUtils.load(XPLAN_51, "Praesentationsobjekte.gml");
		Feature feature = TestFeaturesUtils.getTestFeature(features, "XP_PTO");
		Ausrichtung horzontaleAusrichtung = new Ausrichtung(new Xpath("xplan:horizontaleAusrichtung"));
		TypedObjectNode horizontaleValue = horzontaleAusrichtung.evaluate(feature, features, planContext);
		assertThat(asDouble(horizontaleValue), is(0.0));

		Ausrichtung vertikaleAusrichtung = new Ausrichtung(new Xpath("xplan:vertikaleAusrichtung"));
		TypedObjectNode vertikaleValue = vertikaleAusrichtung.evaluate(feature, features, planContext);
		assertThat(asDouble(vertikaleValue), is(1.0));
	}

	private double asDouble(TypedObjectNode value) {
		PrimitiveValue primitiveValue = (PrimitiveValue) value;
		return (double) primitiveValue.getValue();
	}

}
