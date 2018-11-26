package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.feature.Feature;

/**
 * Base interface for all expressions that can be used for deriving property values in XPlanSyn features.
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public interface Expression {

    /**
     * Evaluates this expression on the given {@link Feature}.
     * 
     * @param feature
     *            feature to operate on, must not be <code>null</code>
     * @return expression value, suitable as property value, can be <code>null</code> (no value, omit property)
     */
    public TypedObjectNode evaluate( Feature feature );
}
