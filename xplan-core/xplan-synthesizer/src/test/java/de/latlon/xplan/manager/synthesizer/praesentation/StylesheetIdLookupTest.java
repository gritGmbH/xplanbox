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

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.manager.synthesizer.expression.praesentation.StylesheetIdLookup;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.junit.Test;

import java.io.IOException;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class StylesheetIdLookupTest {

	@Test
	public void testEvaluate() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/plans/xplan54/BPlan002_5-4.gml");

		PrimitiveValue evaluate1 = getEvaluate(features, "GML_22989f35-59e8-4260-8c60-e706b916a886");
		assertThat(evaluate1.getAsText(), is("BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung=1200]_F"));

		PrimitiveValue evaluate2 = getEvaluate(features, "GML_a630c212-8ae0-4be6-91c4-b0dc8cba820a");
		assertThat(evaluate2.getAsText(), is("BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung=1500]_F"));

		PrimitiveValue evaluate3 = getEvaluate(features, "GML_458852b4-0f35-4405-b3be-90bb70688ddd");
		assertThat(evaluate3.getAsText(), is("BP_BaugebietsTeilFlaeche[besondereArtDerBaulNutzung=1700]_F"));

		PrimitiveValue evaluate4 = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3");
		assertThat(evaluate4.getAsText(), is("SO_Gebiet[gebietsArt=1999][sonstGebietsArt=4242]_F"));

		PrimitiveValue evaluate5 = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3_1");
		assertThat(evaluate5.getAsText(), is("SO_Gebiet[gebietsArt=1999]_F"));

		PrimitiveValue evaluate6 = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3_2");
		assertThat(evaluate6.getAsText(), is("SO_Gebiet[gebietsArt=1999]_F"));

		PrimitiveValue evaluate7 = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3_3");
		assertThat(evaluate7.getAsText(), is("SO_Gebiet[gebietsArt=1999]_F"));
	}

	@Test
	public void testEvaluate_missing_dientZurDarstellungVon() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/plans/xplan54/BPlan002_5-4.gml");

		PrimitiveValue evaluate = getEvaluate(features,
				"GML_22989f35-59e8-4260-8c60-e706b916a886_dientZurDarstellungVon");
		assertThat(evaluate, is(nullValue()));
	}

	@Test
	public void testEvaluate_missing_art() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/plans/xplan54/BPlan002_5-4.gml");

		PrimitiveValue evaluate = getEvaluate(features, "GML_22989f35-59e8-4260-8c60-e706b916a886_art");
		assertThat(evaluate, is(nullValue()));
	}

	@Test
	public void testEvaluate_stylesheetId() throws Exception {
		FeatureCollection features = getTestFeatures(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/plans/xplan54/BPlan002_5-4.gml");

		PrimitiveValue evaluate = getEvaluate(features, "GML_22989f35-59e8-4260-8c60-e706b916a886_stylesheetId");
		assertThat(evaluate.getAsText(), is("Freier Text"));
	}

	private static PrimitiveValue getEvaluate(FeatureCollection features, String gmlId) {
		Feature feature = getTestFeature(features, gmlId);
		StylesheetIdLookup expr = new StylesheetIdLookup();
		TypedObjectNode evaluate = expr.evaluate(feature, features);
		if (evaluate instanceof GenericProperty) {
			GenericProperty genericProperty = (GenericProperty) evaluate;
			TypedObjectNode child = genericProperty.getChildren().get(0);
			return (PrimitiveValue) child;
		}
		return (PrimitiveValue) evaluate;
	}

	protected XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

}
