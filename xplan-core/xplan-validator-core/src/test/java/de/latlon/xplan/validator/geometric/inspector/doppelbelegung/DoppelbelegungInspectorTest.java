package de.latlon.xplan.validator.geometric.inspector.doppelbelegung;

import org.junit.Test;

import java.util.List;

import static de.latlon.xplan.validator.FeatureParserUtils.readFeaturesFromZip;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DoppelbelegungInspectorTest {

	@Test
	public void test_InspectDoppelbelegung() throws Exception {
		DoppelbelegungInspector doppelbelegungInspector = new DoppelbelegungInspector();
		readFeaturesFromZip("xplan60/BPlan001_6-0.zip", doppelbelegungInspector);

		List<String> errors = doppelbelegungInspector.getErrors();
		assertThat(errors.size(), is(0));
	}

}
