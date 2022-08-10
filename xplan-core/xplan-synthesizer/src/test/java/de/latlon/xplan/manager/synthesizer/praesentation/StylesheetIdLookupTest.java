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
package de.latlon.xplan.manager.synthesizer.praesentation;

import de.latlon.xplan.manager.synthesizer.expression.praesentation.StylesheetIdLookup;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class StylesheetIdLookupTest extends AbstractPraesentationsobjektLookupTest {

	@Test
	public void testEvaluate() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");
		StylesheetIdLookup lookup = new StylesheetIdLookup();

		PrimitiveValue evaluate1 = getEvaluate(features, "GML_22989f35-59e8-4260-8c60-e706b916a886", lookup);
		assertThat(evaluate1.getAsText(), is("BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung=1200]_F"));

		PrimitiveValue evaluate2 = getEvaluate(features, "GML_a630c212-8ae0-4be6-91c4-b0dc8cba820a", lookup);
		assertThat(evaluate2.getAsText(), is("BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung=1500]_F"));

		PrimitiveValue evaluate3 = getEvaluate(features, "GML_458852b4-0f35-4405-b3be-90bb70688ddd", lookup);
		assertThat(evaluate3.getAsText(), is("BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung=1700]_F"));

		PrimitiveValue evaluate4 = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3", lookup);
		assertThat(evaluate4.getAsText(), is("SO_Gebiet[gebietsArt=1999][sonstGebietsArt=4242]_F"));

		PrimitiveValue evaluate5 = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3_1", lookup);
		assertThat(evaluate5.getAsText(), is("SO_Gebiet[gebietsArt=1999]_F"));

		PrimitiveValue evaluate6 = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3_2", lookup);
		assertThat(evaluate6.getAsText(), is("SO_Gebiet[gebietsArt=1999]_F"));

		PrimitiveValue evaluate7 = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3_3", lookup);
		assertThat(evaluate7.getAsText(), is("SO_Gebiet[gebietsArt=1999]_F"));
	}

	@Test
	public void testEvaluate_missing_dientZurDarstellungVon() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		StylesheetIdLookup lookup = new StylesheetIdLookup();
		PrimitiveValue evaluate = getEvaluate(features,
				"GML_22989f35-59e8-4260-8c60-e706b916a886_dientZurDarstellungVon", lookup);
		assertThat(evaluate, is(nullValue()));
	}

	@Test
	public void testEvaluate_missing_art() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		StylesheetIdLookup lookup = new StylesheetIdLookup();
		PrimitiveValue evaluate = getEvaluate(features, "GML_22989f35-59e8-4260-8c60-e706b916a886_art", lookup);
		assertThat(evaluate, is(nullValue()));
	}

	@Test
	public void testEvaluate_existing_stylesheetId() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		StylesheetIdLookup lookup = new StylesheetIdLookup();
		PrimitiveValue evaluate = getEvaluate(features, "GML_22989f35-59e8-4260-8c60-e706b916a886_stylesheetId",
				lookup);
		assertThat(evaluate.getAsText(), is("Freier Text"));
	}

}
