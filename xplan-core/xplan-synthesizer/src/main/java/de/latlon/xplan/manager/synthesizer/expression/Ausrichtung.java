package de.latlon.xplan.manager.synthesizer.expression;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.property.SimpleProperty;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class Ausrichtung implements Expression {

    private static final double AUSRICHTUNG_DEFAULT = 0.5;

    private enum AUSRICHTUNG {

        /* HORIZONTAL */
        LINKS( "linksbündig", 0.0 ),

        RECHTS( "rechtsbündig", 1.0 ),

        ZENTRUM( "zentrisch", 0.5 ),

        /* VERTIKAL*/
        BASIS( "Basis", 0.0 ),

        MITTE( "Mitte", 0.5 ),

        OBEN( "Oben", 1.0 );

        private final String codelistName;

        private final double anchor;

        AUSRICHTUNG( String codelistName, double anchorX ) {
            this.codelistName = codelistName;
            this.anchor = anchorX;
        }

        public static AUSRICHTUNG valueOfCodelistName( String codelistName ) {
            for ( AUSRICHTUNG ausrichtung : values() ) {
                if ( ausrichtung.codelistName.equals( codelistName ) ) {
                    return ausrichtung;
                }
            }
            return null;
        }
    }

    private final Expression exp;

    public Ausrichtung( Expression exp ) {
        this.exp = exp;
    }

    @Override
    public TypedObjectNode evaluate( Feature feature ) {
        TypedObjectNode property = exp.evaluate( feature );
        if ( property != null ) {
            if ( property instanceof SimpleProperty ) {
                return asTypedObjectNode( ( (SimpleProperty) property ).getValue() );
            }
            if ( property instanceof GenericXMLElement ) {
                return asTypedObjectNode( ( (GenericXMLElement) property ).getValue() );
            }
        }
        return asTypedObjectNode( AUSRICHTUNG_DEFAULT );
    }

    private TypedObjectNode asTypedObjectNode( PrimitiveValue property ) {
        if ( property != null ) {
            String asText = property.getAsText();
            AUSRICHTUNG ausrichtung = AUSRICHTUNG.valueOfCodelistName( asText );
            if ( ausrichtung != null ) {
                return asTypedObjectNode( ausrichtung.anchor );
            }
        }
        return asTypedObjectNode( AUSRICHTUNG_DEFAULT );
    }

    private TypedObjectNode asTypedObjectNode( double anchor ) {
        return Expressions.toPrimitiveValue( anchor );
    }

}