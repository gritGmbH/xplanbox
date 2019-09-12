package de.latlon.xplan.validator.geometric;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.primitive.Surface;
import org.deegree.gml.feature.FeatureInspectionException;
import org.deegree.gml.feature.FeatureInspector;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Inspector for 2.2.1 Flaechenschlussbedingung:
 *
 * <ul>
 *     <li>Bei allen Objekten mit flaechenhaftem Raumbezug muss das Attribut flaechenschluss belegt sein.</li>
 *     <li>Alle Objekte mit flaechenhaftem Raumbezug, die zur Ebene 0 (ebene == 0) gehoeren und bei denen das Attribut flaechenschluss den Wert true hat (Flaechenschlussobjekte), muessen die Flaechenschlussbedingung erfuellen. Das bedeutet, dass sich die jeweiligen Flaechen nicht ueberlappen, sondern nur an
 *  * gemeinsamen Raendern beruehren duerfen und jeweils identische Stuetzpunkte aufweisen muessen. Zwei Punktkoordinaten werden als identisch betrachtet wenn ihr euklidischer Abstand kleiner als 2 mm ist.</li>
 *     <li>Von der Erfuellung der Flaechenschlussbedingung ausgenommen sind die raumbezogenen Objekte des BPlan-Schemas (s. Kap. 0), deren Wirksamkeit durch sachliche oder zeitliche Bedingungen (Attribute startBedingung und endeBedingung in BP_Objekt) so eingeschraenkt sind, dass sie nicht gleichzeitig rechtswirksam sind.</li>
 * </ul>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FlaechenschlussInspector implements FeatureInspector {

    private List<Feature> flaechenschlussFeatures = new ArrayList<>();

    @Override
    public Feature inspect( Feature feature )
                            throws FeatureInspectionException {
        if ( isFlaechenschlussobjekt( feature ) ) {
            flaechenschlussFeatures.add( feature );
        }
        return feature;
    }

    public boolean checkFlaechenschluss() {
        System.out.println( "Flachenschlussobjekcte: " + flaechenschlussFeatures.size() );
        System.out.println( flaechenschlussFeatures.stream().map( f -> f.getId() ).collect(
                                Collectors.joining( "," ) ) );
        return true;
    }

    /**
     * Alle Objekte mit flaechenhaftem Raumbezug, die zur Ebene 0 (ebene == 0)
     * gehoeren und bei denen das Attribut flaechenschluss den Wert true hat
     * (Flaechenschlussobjekte).
     * <p>
     * TODO: Von der Erfuellung der Flaechenschlussbedingung ausgenommen sind die
     * raumbezogenen Objekte des BPlan-Schemas (s. Kap. 0), deren Wirksamkeit
     * durch sachliche oder zeitliche Bedingungen (Attribute startBedingung und
     * endeBedingung in BP_Objekt) so eingeschraenkt sind, dass sie nicht
     * gleichzeitig rechtswirksam sind.
     */
    private boolean isFlaechenschlussobjekt( Feature feature ) {
        String namespaceURI = feature.getType().getName().getNamespaceURI();
        return isFlaechenschluss( feature, namespaceURI ) && isEbene0( feature, namespaceURI ) && isFlaechenhaft(
                                feature, namespaceURI );
    }

    private boolean isFlaechenschluss( Feature feature, String namespaceURI ) {
        QName flaechenschlussPropName = new QName( namespaceURI, "flaechenschluss" );
        List<Property> flaechenschlussProperties = feature.getProperties( flaechenschlussPropName );
        if ( !flaechenschlussProperties.isEmpty() ) {
            PrimitiveValue value = (PrimitiveValue) flaechenschlussProperties.get( 0 ).getValue();
            return value != null && Boolean.valueOf( value.getAsText() );
        }
        return false;
    }

    private boolean isEbene0( Feature feature, String namespaceURI ) {
        QName ebenePropName = new QName( namespaceURI, "ebene" );
        List<Property> ebeneProperties = feature.getProperties( ebenePropName );
        if ( !ebeneProperties.isEmpty() ) {
            PrimitiveValue value = (PrimitiveValue) ebeneProperties.get( 0 ).getValue();
            return value == null || Integer.valueOf( value.getAsText() ) == 0;
        }
        return true;
    }

    private boolean isFlaechenhaft( Feature feature, String namespaceURI ) {
        QName positionPropName = new QName( namespaceURI, "position" );
        List<Property> positionProperties = feature.getProperties( positionPropName );
        if ( !positionProperties.isEmpty() ) {
            Property property = positionProperties.get( 0 );
            TypedObjectNode value = property.getValue();
            return value != null && ( value instanceof MultiSurface || value instanceof Surface );
        }
        return false;
    }

}