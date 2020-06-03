package de.latlon.xplan.manager.synthesizer.expression;

import static de.latlon.xplan.manager.synthesizer.XplanAbschnittLookup.lookupXpBegruendungAbschnitt;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.namespace.QName;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.reference.FeatureReference;

import de.latlon.xplan.commons.synthesizer.Features;

/**
 * {@link Expression} that returns a textual representation of the "XP_BegruendungAbschnitt" features referenced by an
 * "XP_Objekt" feature.
 * 
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class XplanBegruendungAbschnitte implements Expression {

    @Override
    public PrimitiveValue evaluate( Feature feature, FeatureCollection features ) {
        Set<Feature> abschnittFeatures = new LinkedHashSet<Feature>();
        abschnittFeatures.addAll( getBegruendungAbschnitteReferencedBySchluessel( feature ) );
        abschnittFeatures.addAll( getBegruendungAbschnitteReferencedByRef( feature ) );
        if ( abschnittFeatures.isEmpty() ) {
            return null;
        }
        StringBuilder sBuilder = new StringBuilder();
        for ( Feature abschnitt : abschnittFeatures ) {
            sBuilder.append( escape( toString( abschnitt ) ) );
        }
        return new PrimitiveValue( sBuilder.toString() );
    }

    public static String toString( Feature abschnitt ) {
        QName textProp = new QName( abschnitt.getName().getNamespaceURI(), "text" );
        TypedObjectNode node = Features.getPropertyValue( abschnitt, textProp );
        if ( node != null ) {
            String text = node.toString();
            return "[" + text + "]";
        }
        return null;
    }

    private Set<Feature> getBegruendungAbschnitteReferencedBySchluessel( Feature feature ) {
        Set<Feature> abschnittFeatures = new LinkedHashSet<Feature>();
        QName propName = new QName( feature.getName().getNamespaceURI(), "textSchluesselBegruendung" );
        List<Property> props = feature.getProperties( propName );
        for ( Property prop : props ) {
            TypedObjectNode value = prop.getValue();
            if ( value != null ) {
                String[] schluessels = value.toString().split( "[,;\\s]+" );
                for ( String schluessel : schluessels ) {
                    Feature begruendungAbschnitt = lookupXpBegruendungAbschnitt( schluessel );
                    if ( begruendungAbschnitt != null ) {
                        abschnittFeatures.add( begruendungAbschnitt );
                    }
                }
            }
        }
        return abschnittFeatures;
    }

    private Set<Feature> getBegruendungAbschnitteReferencedByRef( Feature feature ) {
        Set<Feature> abschnittFeatures = new LinkedHashSet<Feature>();
        QName propName = new QName( feature.getName().getNamespaceURI(), "refBegruendungInhalt" );
        List<Property> props = feature.getProperties( propName );
        for ( Property prop : props ) {
            FeatureReference ref = (FeatureReference) prop.getValue();
            if ( ref != null ) {
                Feature begruendungAbschnitt = ref.getReferencedObject();
                abschnittFeatures.add( begruendungAbschnitt );
            }
        }
        return abschnittFeatures;
    }

    private String escape( String desc ) {
        String result = desc;
        result = result.replace( "][", "][][" );
        return result;
    }
}
