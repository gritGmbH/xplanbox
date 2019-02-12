package de.latlon.xplan.commons.synthesizer;

import org.deegree.commons.tom.Reference;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

public class Features {

    public static TypedObjectNode getPropertyValue( Feature f, QName propName ) {
        List<Property> props = f.getProperties( propName );
        if ( props.isEmpty() ) {
            return null;
        }
        if ( props.get( 0 ).getValue() instanceof GenericXMLElement ) {
            GenericXMLElement xmlEl = (GenericXMLElement) props.get( 0 ).getValue();
            if ( xmlEl.getChildren().size() == 1 ) {
                return xmlEl.getChildren().get( 0 );
            }
            return null;
        } else {
            return props.get( 0 ).getValue();
        }
    }

    public static List<TypedObjectNode> getPropertyValues( Feature f, QName propName ) {
        List<Property> props = f.getProperties( propName );
        List<TypedObjectNode> values = new ArrayList<TypedObjectNode>();
        for ( Property prop : props ) {
            values.add( prop.getValue() );
        }
        return values;
    }

    public static Feature getPropertyFeatureValue( Feature f, QName propName ) {
        List<Property> props = f.getProperties( propName );
        if ( props.isEmpty() ) {
            return null;
        }
        TypedObjectNode value = props.get( 0 ).getValue();
        if ( value instanceof Feature ) {
            return (Feature) value;
        }
        if ( value instanceof Reference<?> ) {
            Object refObject = ( (Reference<?>) value ).getReferencedObject();
            if ( refObject instanceof Feature ) {
                return (Feature) refObject;
            }
        }
        throw new RuntimeException( "Value of property '" + propName + "' is not a Feature." );
    }

    public static String getPropertyStringValue( Feature f, QName propName ) {
        List<Property> props = f.getProperties( propName );
        if ( props.isEmpty() ) {
            return null;
        }
        Property prop = props.get( 0 );
        TypedObjectNode value = prop.getValue();
        if ( value == null ) {
            return null;
        }
        return "" + value;
    }

    public static Feature getPropertyFeatureValue( Property prop ) {
        TypedObjectNode value = prop.getValue();
        if ( value instanceof Feature ) {
            return (Feature) value;
        }
        if ( value instanceof Reference<?> ) {
            Object refObject = ( (Reference<?>) value ).getReferencedObject();
            if ( refObject instanceof Feature ) {
                return (Feature) refObject;
            }
        }
        throw new RuntimeException( "Value of property '" + prop.getName() + "' is not a Feature." );
    }
}
