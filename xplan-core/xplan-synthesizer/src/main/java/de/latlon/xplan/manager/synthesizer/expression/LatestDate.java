package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.property.SimpleProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class LatestDate implements Expression {

    private final Expression expression;

    public LatestDate( Expression expression ) {
        this.expression = expression;
    }

    @Override
    public TypedObjectNode evaluate( Feature feature ) {
        TypedObjectNode prop = expression.evaluate( feature );
        TypedObjectNodeArray<TypedObjectNode> props = Expressions.castToArray( prop );
        if ( props == null || props.getElements().length == 0 )
            return null;

        TypedObjectNode[] elements = props.getElements();
        if ( elements.length == 1 )
            return elements[0];

        List<TypedObjectNode> typedObjectNodes = Arrays.asList( elements );
        Collections.sort( typedObjectNodes, ( o1, o2 ) -> {
            PrimitiveValue value1 = getValue( o1 );
            PrimitiveValue value2 = getValue( o2 );
            return value1.compareTo( value2 );
        } );
        return typedObjectNodes.get( typedObjectNodes.size() - 1 );
    }

    private PrimitiveValue getValue( TypedObjectNode prop ) {
        if ( !( prop instanceof SimpleProperty ) ) {
            String msg = "Trying to compare  '" + prop.getClass() + "', only properties are supported.";
            throw new IllegalArgumentException( msg );
        }
        return ( (SimpleProperty) prop ).getValue();
    }

}