package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

public class XplanGmlNameTest {

    @Test
    public void testEvaluate() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_Plan_1" );
        XplanGmlName expr = new XplanGmlName();
        PrimitiveValue value = expr.evaluate( feature, features );
        assertEquals( "[name1][{codespace2}name2][{codespace3}name3]", value.toString() );
    }
}
