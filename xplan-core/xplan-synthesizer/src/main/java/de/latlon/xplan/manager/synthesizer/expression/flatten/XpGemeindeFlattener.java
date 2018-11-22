package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;

public class XpGemeindeFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof ElementNode ) {
            elName = ( (ElementNode) node ).getName().getLocalPart();
        }
        return "XP_Gemeinde".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode xpHoehenangabe ) {
        if ( xpHoehenangabe instanceof ElementNode ) {
            ElementNode elNode = (ElementNode) xpHoehenangabe;
            String s = "[";
            for ( TypedObjectNode child : elNode.getChildren() ) {
                if ( child instanceof ElementNode ) {
                    ElementNode childEl = (ElementNode) child;
                    String propName = ( (ElementNode) child ).getName().getLocalPart();
                    if ( childEl.getChildren().size() == 1 ) {
                        s += concatenateValues( propName, childEl.getChildren().get( 0 ) ) + ";";
                    }
                }
            }
            s += "]";
            return s;
        }
        return "";
    }

    private String concatenateValues( String propName, TypedObjectNode value ) {
        String resultString = propName + "=";
        if ( value instanceof Property ) {
            value = ( (Property) value ).getValue();
        }
        if ( value instanceof PrimitiveValue ) {
            resultString += value.toString();
        }
        return resultString;
    }

}
