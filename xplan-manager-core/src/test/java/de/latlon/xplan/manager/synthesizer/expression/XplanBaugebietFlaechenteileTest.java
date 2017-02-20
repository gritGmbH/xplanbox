package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.junit.Assert.assertEquals;

import org.deegree.feature.Feature;
import org.deegree.geometry.multi.MultiGeometry;
import org.deegree.geometry.primitive.Surface;
import org.junit.Test;

public class XplanBaugebietFlaechenteileTest {

    @Test
    public void testEvaluate() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Baugebiet_1" );
        XplanBaugebietFlaechenteile expr = new XplanBaugebietFlaechenteile();
        @SuppressWarnings("unchecked")
        MultiGeometry<Surface> geom = (MultiGeometry<Surface>) expr.evaluate( feature );
        assertEquals( 3, geom.size() );
    }
}
