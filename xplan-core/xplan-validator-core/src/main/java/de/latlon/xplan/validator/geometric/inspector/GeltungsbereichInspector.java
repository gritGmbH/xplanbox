package de.latlon.xplan.validator.geometric.inspector;

import com.vividsolutions.jts.geom.Geometry;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.gml.feature.FeatureInspectionException;
import org.deegree.gml.reference.FeatureReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Inspector for 2.2.3.1 Raumbezogene Objekte im Innern des Geltungsbereichs:
 * <p>
 * Bei allen raumbezogenen Objekten, die zu einem Bereich gehören, muss die
 * Objektgeometrie innerhalb des Geltungsbereichs des Bereichs liegen, bzw. im
 * Innern des Geltungsbereichs des Plans, wenn der Bereich keinen eigenen Gel-
 * tungsbereich hat.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichInspector implements GeometricFeatureInspector {

    private static final Logger LOG = LoggerFactory.getLogger( GeltungsbereichInspector.class );

    private static final String GEHOERT_ZU_BEREICH = "gehoertZuBereich";

    private static final String ERROR_MSG = "Das Objekt mit der gml id %s liegt nicht im Geltungsbereich des Bereichs/Plans.";

    private Geometry planGeom;

    private Map<String, Geometry> bereichGeoms = new HashMap<>();

    private Map<String, List<Feature>> bereichIdToFeatureGeoms = new HashMap<>();

    @Override
    public Feature inspect( Feature feature )
                            throws FeatureInspectionException {

        if ( isPlanFeature( feature ) ) {
            planGeom = getGeometry( feature );
        } else if ( isBereichFeature( feature ) ) {
            Geometry bereichGeom = getGeometry( feature );
            bereichGeoms.put( feature.getId(), bereichGeom );
        } else if ( hasGehoertZuBereichProperty( feature ) ) {
            String gehortZuBereich = getGehortZuBereichId( feature );
            if ( !bereichIdToFeatureGeoms.containsKey( gehortZuBereich ) )
                bereichIdToFeatureGeoms.put( gehortZuBereich, new ArrayList<>() );
            bereichIdToFeatureGeoms.get( gehortZuBereich ).add( feature );
        }
        return feature;
    }

    @Override
    public List<String> checkGeometricRule() {
        List<String> allInvalidFeatureIds = new ArrayList<>();
        for ( Map.Entry<String, List<Feature>> bereichIdToGeoms : bereichIdToFeatureGeoms.entrySet() ) {
            Geometry geltungsbereich = retrieveGeltungsbereichGeom( bereichIdToGeoms.getKey() );
            List<String> invalidFeatureIds = bereichIdToGeoms.getValue().stream().filter(
                                    f -> !isInsideGeom( f, geltungsbereich ) ).map( f -> f.getId() ).collect(
                                    Collectors.toList() );
            allInvalidFeatureIds.addAll( invalidFeatureIds );
        }
        if ( allInvalidFeatureIds.isEmpty() ) {
            LOG.info( "No features outside geltungsbereich" );
        } else {
            LOG.info( "Features outside geltungsbereich:\n {}",
                      allInvalidFeatureIds.stream().collect( Collectors.joining( "\n" ) ) );
        }
        List<String> errorsOfBereich = allInvalidFeatureIds.stream().map(
                                id -> String.format( ERROR_MSG, id ) ).collect( Collectors.toList() );
        return errorsOfBereich;
    }

    private Geometry retrieveGeltungsbereichGeom( String bereichId ) {
        if ( bereichGeoms.containsKey( bereichId ) && bereichGeoms.get( bereichId ) != null )
            return bereichGeoms.get( bereichId );
        return planGeom;
    }

    private boolean isInsideGeom( Feature feature, Geometry geltungsbereich ) {
        Geometry geometry = getGeometry( feature );
        return geltungsbereich.covers( geometry );
    }

    private boolean isPlanFeature( Feature feature ) {
        return feature.getName().getLocalPart().endsWith( "_Plan" );
    }

    private boolean isBereichFeature( Feature feature ) {
        return feature.getName().getLocalPart().endsWith( "_Bereich" );
    }

    private boolean hasGehoertZuBereichProperty( Feature feature ) {
        return !feature.getProperties( new QName( feature.getName().getNamespaceURI(), GEHOERT_ZU_BEREICH ) ).isEmpty();
    }

    private String getGehortZuBereichId( Feature feature ) {
        Property property = feature.getProperties(
                                new QName( feature.getName().getNamespaceURI(), GEHOERT_ZU_BEREICH ) ).get( 0 );
        TypedObjectNode value = property.getValue();
        if ( value instanceof FeatureReference )
            return ( (FeatureReference) value ).getURI().substring( 1 );
        return value.toString().substring( 1 );
    }

    private com.vividsolutions.jts.geom.Geometry getGeometry( Feature feature ) {
        List<Property> geometryProperties = feature.getGeometryProperties();
        if ( !geometryProperties.isEmpty() )
            return ( (AbstractDefaultGeometry) geometryProperties.get( 0 ).getValue() ).getJTSGeometry();
        return null;
    }

}