package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;

/**
 * {@link Expression} that returns a string constant.
 * 
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class StringConstant implements Expression {

    private final PrimitiveValue value;

    public StringConstant( String s ) {
        this.value = new PrimitiveValue( s );
    }

    @Override
    public PrimitiveValue evaluate( Feature feature ) {
        return value;
    }
}
