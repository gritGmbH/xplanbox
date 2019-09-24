package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.junit.Test;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class XplanRefTextAbschnitteTest {

    @Test
    public void testEvaluate() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Plan_1" );
        XplanRefTextAbschnitte expr = new XplanRefTextAbschnitte();
        PrimitiveValue abschnitte = expr.evaluate( feature );
        assertThat( abschnitte.getAsText(),
                    is( "[/getAttachment?featureID=XP_TEXTABSCHNITT_1&filename=text1.pdf][/getAttachment?featureID=XP_TEXTABSCHNITT_2&filename=text2.pdf]" ) );
    }

    @Test
    public void testEvaluate_NoXPlanTextAbschnitt() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Bereich_1" );
        XplanRefTextAbschnitte expr = new XplanRefTextAbschnitte();
        PrimitiveValue abschnitte = expr.evaluate( feature );
        assertThat( abschnitte, nullValue() );
    }

}
