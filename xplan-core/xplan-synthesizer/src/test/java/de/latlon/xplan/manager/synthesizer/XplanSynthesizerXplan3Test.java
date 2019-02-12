package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;

public class XplanSynthesizerXplan3Test extends AbstractXplanSynthesizerTest {

    @Override
    XPlanVersion getXPlanVersion() {
        return XPLAN_3;
    }

    @Test
    public void testBp2070()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "BP2070_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/BP2070.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testBp2135()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "BP2135_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/BP2135.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testDemo()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "Demo_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/Demo.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testFplan()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "FPlan_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/FPlan.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testHc7()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "hc7_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/hc7.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testLa22()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "LA22_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/LA22.zip" );
        assertEqualContent( expected, actual );
    }

    @Test
    public void testLa67()
                    throws Exception {
        FeatureCollection expected = readSynFeatures( "LA67_XPLAN3.gml" );
        FeatureCollection actual = createSynFeatures( "xplan3/LA67.zip" );
        assertEqualContent( expected, actual );
    }

}
