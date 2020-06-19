package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

/**
 * {@link Expression} that returns the type of plan.
 * 
 * @see de.latlon.xplan.commons.XPlanType
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class XPlanType implements Expression {

    private final PrimitiveValue xplanType;

    /**
     * @param xplanType
     */
    public XPlanType( String xplanType ) {
        this.xplanType = new PrimitiveValue( xplanType );
    }

    @Override
    public PrimitiveValue evaluate( Feature feature, FeatureCollection features ) {
        return xplanType;
    }
}
