package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.junit.Assert.assertEquals;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.junit.Test;

public class XplanTextAbschnitteTest {

    @Test
    public void testEvaluate() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Baugebiet_1" );
        XplanTextAbschnitte expr = new XplanTextAbschnitte();
        PrimitiveValue abschnitte = expr.evaluate( feature );
        assertEquals( "[Das ist Textabschnitt No 1 (Keine gesetzliche Grundlage)]"
                      + "[Das ist Textabschnitt No 2 (Gesetzliche Grundlage: BGB)]", abschnitte.toString() );
    }
}
