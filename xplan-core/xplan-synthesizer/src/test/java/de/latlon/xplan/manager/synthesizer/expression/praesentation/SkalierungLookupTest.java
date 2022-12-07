package de.latlon.xplan.manager.synthesizer.expression.praesentation;

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

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.load;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SkalierungLookupTest extends AbstractPraesentationsobjektLookupTest {

	@Test
	public void testEvaluate_calculatedWithErstellungsMassstab() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");
		SkalierungLookup lookup = new SkalierungLookup();

		PrimitiveValue evaluate1 = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3_1", lookup);
		assertThat(evaluate1, is(10));
	}

	@Test
	public void testEvaluate_calculatedWithoutErstellungsMassstab() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4_ohneErstellungsMassstab.gml");
		SkalierungLookup lookup = new SkalierungLookup();

		PrimitiveValue evaluate1 = getEvaluate(features, "GML_22989f35-59e8-4260-8c60-e706b916a886_FP", lookup);
		assertThat(evaluate1, is(0.2));
	}

	@Test
	public void testEvaluate_missingDientZurDarstellungVon() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		SkalierungLookup lookup = new SkalierungLookup();
		PrimitiveValue evaluate = getEvaluate(features,
				"GML_22989f35-59e8-4260-8c60-e706b916a886_dientZurDarstellungVon", lookup);
		assertThat(evaluate, is(nullValue()));
	}

	@Test
	public void testEvaluate_missingArt() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		SkalierungLookup lookup = new SkalierungLookup();
		PrimitiveValue evaluate = getEvaluate(features, "GML_22989f35-59e8-4260-8c60-e706b916a886_art", lookup);
		assertThat(evaluate, is(nullValue()));
	}

	@Test
	public void testEvaluate_existingSkalierung() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		SkalierungLookup lookup = new SkalierungLookup();
		PrimitiveValue evaluate = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3", lookup);
		assertThat(evaluate, is(9));
	}

}