package de.latlon.xplan.manager.synthesizer.expression.flatten;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;

public class XpVerfahrensMerkmalFlattener extends AbstractFlattener {

    @Override
    public boolean accepts( TypedObjectNode node ) {
        String elName = null;
        if ( node instanceof Feature ) {
            elName = ( (Feature) node ).getName().getLocalPart();
        } else if ( node instanceof ElementNode ) {
            elName = ( (ElementNode) node ).getName().getLocalPart();
        }
        return "XP_VerfahrensMerkmal".equals( elName );
    }

    @Override
    public String flatten( TypedObjectNode xpVerfahrensmerkmal ) {
        PrimitiveValue datum = (PrimitiveValue) getPropertyValue( xpVerfahrensmerkmal, "datum" );
        PrimitiveValue vermerk = (PrimitiveValue) getPropertyValue( xpVerfahrensmerkmal, "vermerk" );
        PrimitiveValue signatur = (PrimitiveValue) getPropertyValue( xpVerfahrensmerkmal, "signatur" );
        PrimitiveValue signiert = (PrimitiveValue) getPropertyValue( xpVerfahrensmerkmal, "signiert" );
        return encode( datum, vermerk, signatur, signiert );
    }

    private String encode( PrimitiveValue datum, PrimitiveValue vermerk, PrimitiveValue signatur,
                           PrimitiveValue signiert ) {
        String s = "[";
        if ( datum != null ) {
            s += datum + ": ";
        }
        if ( vermerk != null ) {
            s += "\"" + vermerk + "\"";
        }
        s += " (";
        if ( signatur != null && !signatur.toString().isEmpty() ) {
            s += signatur + ", ";
        }
        if ( signiert != null && "true".equalsIgnoreCase( signiert.toString() ) ) {
            s += "signiert";
        } else {
            s += "nicht signiert";
        }
        s += ")]";
        return s;
    }

}
