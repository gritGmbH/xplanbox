package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeature;
import static de.latlon.xplan.manager.synthesizer.expression.TestFeaturesUtils.getTestFeatures;
import static org.junit.Assert.assertEquals;

public class XplanTextAbschnitteTest {

    @Test
    public void testEvaluate() {
        FeatureCollection features = getTestFeatures( XPLAN_41 );
        Feature feature = getTestFeature( features, "BP_Baugebiet_1" );
        XplanTextAbschnitte expr = new XplanTextAbschnitte();
        PrimitiveValue abschnitte = expr.evaluate( feature, features );
        assertEquals( "[text1 | Das ist Textabschnitt No 1 (Keine gesetzliche Grundlage)]"
                      + "[text2 | Das ist Textabschnitt No 2 (Gesetzliche Grundlage: BGB)]", abschnitte.toString() );
    }
}
