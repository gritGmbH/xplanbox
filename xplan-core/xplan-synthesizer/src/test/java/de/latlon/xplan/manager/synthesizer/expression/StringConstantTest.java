package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.junit.Assert;
import org.junit.Test;

public class StringConstantTest {

    @Test
    public void testEvaluate() {
        StringConstant expr = new StringConstant( "3.0" );
        PrimitiveValue value = expr.evaluate( null, null );
        Assert.assertEquals( "3.0", value.toString() );
    }
}
