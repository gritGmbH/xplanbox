package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;

import org.deegree.feature.Feature;
import org.deegree.geometry.Geometry;
import org.junit.Assert;
import org.junit.Test;

public class XplanGeometryTest {

    @Test
    public void testEvaluate() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Plan_1" );
        XPlanGeometry expr = new XPlanGeometry( new Xpath( "xplan:raeumlicherGeltungsbereich" ) );
        Geometry value = expr.evaluate( feature );
        Assert.assertNotNull( value );
    }

    @Test
    public void testEvaluateEmptyProperty() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "XP_PPO_3" );
        XPlanGeometry expr = new XPlanGeometry( new Xpath( "xplan:position" ) );
        Geometry value = expr.evaluate( feature );
        Assert.assertNull( value );
    }
}
