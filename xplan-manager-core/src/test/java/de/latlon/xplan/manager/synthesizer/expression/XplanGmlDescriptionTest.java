package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.junit.Assert.assertEquals;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.junit.Test;

public class XplanGmlDescriptionTest {

    @Test
    public void testEvaluate() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Plan_1" );
        XPlanGmlDescription expr = new XPlanGmlDescription();
        PrimitiveValue value = expr.evaluate( feature );
        assertEquals( "description", value.toString() );
    }
}
