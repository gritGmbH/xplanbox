package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.multi.MultiPoint;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

public class XplanSymbolPositionsTest {

    @Test
    public void testEvaluate() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_BaugebietsTeilFlaeche_1" );
        XplanSymbolPositions expr = new XplanSymbolPositions();
        MultiPoint geom = expr.evaluate( feature, features );
        assertEquals( 3, geom.size() );
    }
}
