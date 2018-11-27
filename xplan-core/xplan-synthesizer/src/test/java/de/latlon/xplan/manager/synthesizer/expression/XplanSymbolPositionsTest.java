package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.junit.Assert.assertEquals;

import org.deegree.feature.Feature;
import org.deegree.geometry.multi.MultiPoint;
import org.junit.Test;

public class XplanSymbolPositionsTest {

    @Test
    public void testEvaluate() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_BaugebietsTeilFlaeche_1" );
        XplanSymbolPositions expr = new XplanSymbolPositions();
        MultiPoint geom = expr.evaluate( feature );
        assertEquals( 3, geom.size() );
    }
}
