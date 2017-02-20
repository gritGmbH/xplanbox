package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;

public class DefaultFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode node ) {
        return node instanceof Feature || node instanceof ElementNode;
    }

    @Override
    public String flatten( TypedObjectNode node ) {
        if ( node instanceof Feature ) {
            return flatten( (Feature) node );
        }
        if ( node instanceof PrimitiveValue ) {
            return node.toString();
        }
        if ( node instanceof GenericXMLElement ) {
            GenericXMLElement el = (GenericXMLElement) node;
            String s = "[" + el.getName().getLocalPart() + "=";
            for ( TypedObjectNode child : el.getChildren() ) {
                s += flatten( child );
            }
            s += "]";
            return s;
        }
        return "";
    }

    private String flatten( Feature feature ) {
        return "[" + feature.getId() + "]";
    }
}
