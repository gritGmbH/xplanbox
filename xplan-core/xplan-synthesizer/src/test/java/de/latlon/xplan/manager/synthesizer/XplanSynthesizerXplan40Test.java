package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanVersion;
import org.junit.Ignore;
import org.junit.Test;

public class XplanSynthesizerXplan40Test extends AbstractXplanSynthesizerTest {

    private final XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

    @Ignore("File size of referenced plan was too large for Github. Test plan was removed from repository.")
    @Test
    public void testId106()
                            throws Exception {
        createSynFeatures( "xplan40/V4_1_ID_106.zip" );
    }

    @Override
    XPlanVersion getXPlanVersion() {
        return XPlanVersion.XPLAN_41;
    }

}
