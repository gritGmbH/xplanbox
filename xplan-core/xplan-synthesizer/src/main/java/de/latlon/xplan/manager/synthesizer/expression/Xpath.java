package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.xml.NamespaceBindings;
import org.deegree.feature.Feature;
import org.deegree.feature.xpath.TypedObjectNodeXPathEvaluator;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.expression.ValueReference;

import de.latlon.xplan.commons.util.XPlanVersionUtils;

/**
 * {@link Expression} that fetches a specific property or node (of the feature).
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class Xpath implements Expression {

    private final String expression;

    private final Object defaultValue;

    public Xpath( String expression ) {
        this( expression, null );
    }

    public Xpath( String expression, Object defaultValue ) {
        this.expression = expression;
        this.defaultValue = defaultValue;
    }

    @Override
    public TypedObjectNode evaluate( Feature feature ) {
        NamespaceBindings nsContext = XPlanVersionUtils.retrieveNamespaceBindings( feature.getName() );
        ValueReference propName = new ValueReference( expression, nsContext );
        TypedObjectNodeXPathEvaluator evaluator = new TypedObjectNodeXPathEvaluator();
        TypedObjectNode[] valueNodes;
        try {
            valueNodes = evaluator.eval( feature, propName );
        } catch ( FilterEvaluationException e ) {
            throw new RuntimeException( e.getMessage() );
        }
        if ( valueNodes == null || valueNodes.length == 0 ) {
            if ( defaultValue == null )
                return null;
            else
                return new PrimitiveValue( defaultValue );
        }
        if ( valueNodes.length == 1 ) {
            return valueNodes[0];
        }
        return new TypedObjectNodeArray<>( valueNodes );
    }

    public String toString() {
        return "xpath( " + expression + " )";
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

}