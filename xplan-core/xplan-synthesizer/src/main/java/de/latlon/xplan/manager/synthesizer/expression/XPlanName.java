package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

/**
 * {@link Expression} that returns the name of the plan.
 * 
 * @see de.latlon.xplan.commons.XPlanType
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class XPlanName implements Expression {

    private PrimitiveValue xplanName;

    /**
     * @param xplanName
     */
    public XPlanName( String xplanName ) {
        this.xplanName = new PrimitiveValue( xplanName );
    }

    @Override
    public PrimitiveValue evaluate( Feature feature, FeatureCollection features ) {
        return xplanName;
    }
}
