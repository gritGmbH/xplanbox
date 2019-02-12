package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.junit.Assert.assertEquals;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.junit.Test;

public class XplanCodeLookupTest {

    @Test
    public void testEvaluateXplan3() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_3, "BP_Plan_1" );
        XplanCodeLookup expr = new XplanCodeLookup( new Xpath( "xplan:planArt" ), "BP_PlanArt" );
        PrimitiveValue value = expr.evaluate( feature );
        assertEquals( "BPlan", value + "" );
    }

    @Test
    public void testEvaluateXplan40() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_40, "BP_Plan_1" );
        XplanCodeLookup expr = new XplanCodeLookup( new Xpath( "xplan:planArt" ), "BP_PlanArt" );
        PrimitiveValue value = expr.evaluate( feature );
        assertEquals( "BPlan", value + "" );
    }
    
    @Test
    public void testEvaluateXplan41() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Plan_1" );
        XplanCodeLookup expr = new XplanCodeLookup( new Xpath( "xplan:planArt" ), "BP_PlanArt" );
        PrimitiveValue value = expr.evaluate( feature );
        assertEquals( "BPlan", value + "" );
    }
    
}
