package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.manager.synthesizer.expression.Expressions.toPrimitiveValue;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.array.TypedObjectNodeArray;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;

/**
 * {@link Expression} for translating codes from external codelists to their textual representation.
 * 
 * TODO actually take dictionaries into account that are referenced by the main document
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class XplanCodeLookupExt implements Expression {

    private final Expression exp;

    private final String codeList;

    public XplanCodeLookupExt( Expression exp, String codeList ) {
        this.exp = exp;
        this.codeList = codeList;
    }

    @Override
    public PrimitiveValue evaluate( Feature feature ) {
        String descriptions = null;
        TypedObjectNodeArray<TypedObjectNode> props = Expressions.castToArray( exp.evaluate( feature ) );
        if ( props != null ) {
            for ( TypedObjectNode node : props.getElements() ) {
                if ( node instanceof Property ) {
                    Property prop = (Property) node;
                    TypedObjectNode value = prop.getValue();
                    String desc = toString( value );
                    if ( desc != null && !desc.isEmpty() ) {
                        if ( descriptions == null ) {
                            descriptions = desc;
                        } else {
                            descriptions += ";" + desc;
                        }
                    }
                }
            }
        }
        return toPrimitiveValue( descriptions );
    }

    private String toString( TypedObjectNode value ) {
        if ( value == null ) {
            return null;
        }
        if ( value instanceof ElementNode ) {
            ElementNode el = (ElementNode) value;
            String s = "";
            PrimitiveValue codeSpace = el.getAttributes().get( new QName( "codeSpace" ) );
            if ( codeSpace != null ) {
                s = "{" + codeSpace + "}";
            }
            for ( TypedObjectNode child : el.getChildren() ) {
                s += child;
            }
            return s;
        }
        return value.toString();
    }
}
