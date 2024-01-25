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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.FeatureCollection;
import org.junit.jupiter.api.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.load;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class SchriftinhaltLookupTest extends AbstractPraesentationsobjektLookupTest {

	@Test
	void testEvaluate_textFromArt() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");
		SchriftinhaltLookup lookup = new SchriftinhaltLookup();

		PrimitiveValue evaluate = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82e77", lookup);
		assertNull(evaluate);
	}

	@Test
	void testEvaluate_textFromArtEnum() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");
		SchriftinhaltLookup lookup = new SchriftinhaltLookup();

		PrimitiveValue evaluate = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82ee3", lookup);
		// Kuerzel are not available for XPLan 5.4
		assertNull(evaluate);
	}

	@Test
	void testEvaluate_textFromArtCodeValue() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");
		SchriftinhaltLookup lookup = new SchriftinhaltLookup();

		PrimitiveValue evaluate = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82e78", lookup);
		assertNull(evaluate);
	}

	@Test
	void testEvaluate_missingDientZurDarstellungVon() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		SchriftinhaltLookup lookup = new SchriftinhaltLookup();
		PrimitiveValue evaluate = getEvaluate(features,
				"GML_a81f7f4e-071f-44fd-af3e-826e80b82e77_dientZurDarstellungVon", lookup);
		assertNull(evaluate);
	}

	@Test
	void testEvaluate_missingArt() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		SchriftinhaltLookup lookup = new SchriftinhaltLookup();
		PrimitiveValue evaluate = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82e77_art", lookup);
		assertNull(evaluate);
	}

	@Test
	void testEvaluate_existingSchriftinhalt() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		SchriftinhaltLookup lookup = new SchriftinhaltLookup();
		PrimitiveValue evaluate = getEvaluate(features, "GML_a81f7f4e-071f-44fd-af3e-826e80b82e77_schriftinhalt",
				lookup);
		assertEquals("Test mit Schriftinhalt", evaluate.getAsText());
	}

	@Test
	void testEvaluate_complexProperty() throws Exception {
		FeatureCollection features = load(XPLAN_54,
				"/de/latlon/xplan/manager/synthesizer/praesentation/BPlan002_5-4.gml");

		SchriftinhaltLookup lookup = new SchriftinhaltLookup();
		PrimitiveValue evaluate = getEvaluate(features, "GML_22989f35-59e8-4260-8c60-e706b916a886_dachgestaltung",
				lookup);
		assertNull(evaluate);
	}

}
