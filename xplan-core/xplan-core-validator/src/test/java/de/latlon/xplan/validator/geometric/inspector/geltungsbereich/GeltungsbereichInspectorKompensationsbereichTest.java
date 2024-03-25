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
