package de.latlon.xplan.manager.synthesizer.expression.flatten;

import de.latlon.xplan.commons.synthesizer.Features;
import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;

import javax.xml.namespace.QName;

abstract class AbstractFlattener implements Flattener {

    TypedObjectNode getPropertyValue( TypedObjectNode xpVerfahrensmerkmal, String propName ) {
        if ( xpVerfahrensmerkmal instanceof Feature ) {
            Feature f = (Feature) xpVerfahrensmerkmal;
            QName qName = new QName( f.getName().getNamespaceURI(), propName );
            return Features.getPropertyValue( f, qName );
        } else if ( xpVerfahrensmerkmal instanceof ElementNode ) {
            return getPropertyValue( (ElementNode) xpVerfahrensmerkmal, propName );
        }
        throw new IllegalArgumentException();
    }

    TypedObjectNode getPropertyValue( ElementNode xpVerfahrensmerkmal, String propName ) {
        for ( TypedObjectNode child : xpVerfahrensmerkmal.getChildren() ) {
            if ( child instanceof ElementNode ) {
                ElementNode childEl = (ElementNode) child;
                if ( !childEl.getName().getLocalPart().equals( propName ) ) {
                    continue;
                }
                if ( childEl.getChildren().isEmpty() ) {
                    return new PrimitiveValue( "" );
                }
                if ( childEl.getChildren().size() == 1 ) {
                    return childEl.getChildren().get( 0 );
                }
                throw new IllegalArgumentException();
            }
        }
        return null;
    }

    String escape( String desc ) {
        String result = desc;
        if ( result.startsWith( "[" ) && result.endsWith( "]" ) ) {
            result = result.substring( 1, result.length() - 1 );
        }
        result = result.replace( "][", "][][" );
        return result;
    }
}
