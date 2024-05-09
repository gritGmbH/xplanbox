/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.validator.geometric.inspector.geltungsbereich;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromGml;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(Parameterized.class)
public class GeltungsbereichInspectorKompensationsbereichTest {

	private final String planName;

	private final boolean isExpectedValid;

	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ "kompensationsbereich/Bereich_1800_Bereich_mitGeltungsbereich_2_Flaechenschlussobjekte_korrekt.gml",
						true },
				{ "kompensationsbereich/Bereich_1800_Bereich_mitGeltungsbereich_groesser_Fehler.gml", true },
				{ "kompensationsbereich/Bereich_1800_Bereich_mitGeltungsbereich_kleiner_Fehler.gml", false },
				{ "kompensationsbereich/Bereich_1800_Bereich_mitGeltungsbereich_korrekt.gml", true },
				{ "kompensationsbereich/Bereich_1800_Bereich_mitGeltungsbereich_ohne_Flaechenschlussobjekte_korrekt.gml",
						true },
				{ "kompensationsbereich/Bereich_1800_Bereich_ohneGeltungsbereich_Fehler.gml", true },
				{ "kompensationsbereich/Bereich_1800_Bereich_ohneGeltungsbereich_ohne_Flaechenschlussobjekte_Fehler.gml",
						true },

				{ "outside/Bereich_2000_Bereich_mitGeltungsbereich_2_Flaechenschlussobjekte_korrekt.gml", true },
				{ "outside/Bereich_2000_Bereich_mitGeltungsbereich_groesser_Fehler.gml", true },
				{ "outside/Bereich_2000_Bereich_mitGeltungsbereich_kleiner_Fehler.gml", false },
				{ "outside/Bereich_2000_Bereich_mitGeltungsbereich_korrekt.gml", true },
				{ "outside/Bereich_2000_Bereich_mitGeltungsbereich_ohne_Flaechenschlussobjekte_korrekt.gml", true },
				{ "outside/Bereich_2000_Bereich_ohneGeltungsbereich_Fehler.gml", true },
				{ "outside/Bereich_2000_Bereich_ohneGeltungsbereich_ohne_Flaechenschlussobjekte_Fehler.gml", true } });
	}

	public GeltungsbereichInspectorKompensationsbereichTest(String planName, boolean expectedIsValid) {
		this.planName = planName;
		this.isExpectedValid = expectedIsValid;
	}

	@Test
	public void testCheck_Kompensationsbereiche_valide() throws Exception {
		GeltungsbereichInspector geltungsbereichInspector = new GeltungsbereichInspector(XPLAN_60);
		readFeaturesFromGml(planName, GeltungsbereichInspector.class, geltungsbereichInspector);

		boolean isValid = geltungsbereichInspector.checkGeometricRule();
		assertThat(isValid, is(isExpectedValid));
	}

}
