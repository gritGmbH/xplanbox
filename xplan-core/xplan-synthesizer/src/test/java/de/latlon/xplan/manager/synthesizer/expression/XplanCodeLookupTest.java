package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

public class XplanCodeLookupTest {

    @Test
    public void testEvaluateXplan3() {
        FeatureCollection features = getTestFeatures( XPLAN_3 );
        Feature feature = getTestFeature( features, "BP_Plan_1" );
        XplanCodeLookup expr = new XplanCodeLookup( new Xpath( "xplan:planArt" ), "BP_PlanArt" );
        PrimitiveValue value = expr.evaluate( feature, features );
        assertEquals( "BPlan", value + "" );
    }

    @Test
    public void testEvaluateXplan40() {
        FeatureCollection features = getTestFeatures( XPLAN_40 );
        Feature feature = getTestFeature( features, "BP_Plan_1" );
        XplanCodeLookup expr = new XplanCodeLookup( new Xpath( "xplan:planArt" ), "BP_PlanArt" );
        PrimitiveValue value = expr.evaluate( feature, features );
        assertEquals( "BPlan", value + "" );
    }

    @Test
    public void testEvaluateXplan41() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_Plan_1" );
        XplanCodeLookup expr = new XplanCodeLookup( new Xpath( "xplan:planArt" ), "BP_PlanArt" );
        PrimitiveValue value = expr.evaluate( feature, features );
        assertEquals( "BPlan", value + "" );
    }

}
