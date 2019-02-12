package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.feature.FeatureCollection;
import org.junit.Ignore;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_2;

public class XplanSynthesizerXplan2Test extends AbstractXplanSynthesizerTest {

    @Override
    XPlanVersion getXPlanVersion() {
        return XPLAN_2;
    }

    @Test
    public void testBp2070()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "BP2070_XPLAN2.gml" );
        FeatureCollection actual = createSynFeatures( "xplan2/BP2070.zip" );
        assertEqualContent( expected, actual );
    }

    @Ignore
    @Test
    public void testBp2135()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "BP2135_XPLAN2.gml" );
        FeatureCollection actual = createSynFeatures( "xplan2/BP2135.zip" );
        assertEqualContent( expected, actual );
    }

    @Ignore
    @Test
    public void testFplan()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "FPlan_XPLAN2.gml" );
        FeatureCollection actual = createSynFeatures( "xplan2/FPlan.zip" );
        assertEqualContent( expected, actual );
    }

    @Ignore
    @Test
    public void testLa22()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "LA22_XPLAN2.gml" );
        FeatureCollection actual = createSynFeatures( "xplan2/LA22.zip" );
        assertEqualContent( expected, actual );
    }

    @Ignore
    @Test
    public void testLa67()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "LA67_XPLAN2.gml" );
        FeatureCollection actual = createSynFeatures( "xplan2/LA67.zip" );
        assertEqualContent( expected, actual );
    }

}
