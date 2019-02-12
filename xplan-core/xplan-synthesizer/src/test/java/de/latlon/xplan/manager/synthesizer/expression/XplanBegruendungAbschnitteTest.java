package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.junit.Assert.assertEquals;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.junit.Test;

public class XplanBegruendungAbschnitteTest {

    @Test
    public void testEvaluate() {
        Feature feature = new ScenarioLoader().getTestFeature( XPLAN_41, "BP_Baugebiet_1" );
        XplanBegruendungAbschnitte expr = new XplanBegruendungAbschnitte();
        PrimitiveValue abschnitte = expr.evaluate( feature );
        assertEquals( "[Das ist Begründungsabschnitt No 1][Das ist Begründungsabschnitt No 2]",
                             abschnitte.toString() );
    }
}
