package de.latlon.xplan.validator.geometric.inspector;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.TopologyException;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
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
import java.util.Arrays;
import java.util.Collections;
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

    private static final String ERROR_MSG = "2.2.3.1: Das Objekt mit der gml id %s liegt nicht im Geltungsbereich des Bereichs/Plans.";

    private static final String ERROR_MSG_INVALID_GELTUNGSBEREICH = "2.2.3.1: Der Geltungsbereich des Plans ist geometrisch nicht valide. Betroffen ist das Feature mit der gml id %s. Die Pruefung des Geltungsbereichs kann nicht durchgeführt werden.";

    private static final List<String> PRAESENTATIONSOBJEKTE = Arrays.asList( "XP_FPO", "XP_LPO", "XP_LTO", "XP_PPO",
                                                                             "XP_PTO", "XP_TPO" );

    private Feature planFeature;

    private Map<String, Feature> bereichFeatures = new HashMap<>();

    private Map<String, List<Feature>> bereichIdToFeatureGeoms = new HashMap<>();

    @Override
    public Feature inspect( Feature feature )
                            throws FeatureInspectionException {

        if ( isPlanFeature( feature ) ) {
            planFeature = feature;
        } else if ( isBereichFeature( feature ) ) {
            bereichFeatures.put( feature.getId(), feature );
        } else if ( hasGehoertZuBereichProperty( feature ) && !isPraesentationsobjekt( feature ) ) {
            String gehortZuBereich = getGehortZuBereichId( feature );
            if ( !bereichIdToFeatureGeoms.containsKey( gehortZuBereich ) )
                bereichIdToFeatureGeoms.put( gehortZuBereich, new ArrayList<>() );
            bereichIdToFeatureGeoms.get( gehortZuBereich ).add( feature );
        }
        return feature;
    }

    @Override
    public List<BadGeometry> checkGeometricRule() {
        List<Feature> allInvalidFeatures = new ArrayList<>();
        for ( Map.Entry<String, List<Feature>> bereichIdToGeoms : bereichIdToFeatureGeoms.entrySet() ) {
            Feature geltungsbereichFeature = retrieveGeltungsbereichFeature( bereichIdToGeoms.getKey() );
            Geometry geltungsbereich = getGeometry( geltungsbereichFeature );
            if ( !geltungsbereich.isValid() ) {
                BadGeometry error = new BadGeometry( getOriginalGeometry( geltungsbereichFeature ),
                                                     String.format( ERROR_MSG_INVALID_GELTUNGSBEREICH,
                                                                    geltungsbereichFeature.getId() ) );
                return Collections.singletonList( error );
            }
            List<Feature> invalidFeatures = bereichIdToGeoms.getValue().stream().filter(
                                    f -> !isInsideGeom( f, geltungsbereich ) ).collect( Collectors.toList() );
            allInvalidFeatures.addAll( invalidFeatures );
        }
        if ( allInvalidFeatures.isEmpty() ) {
            LOG.info( "No features outside geltungsbereich" );
        } else {
            LOG.info( "Features outside geltungsbereich:\n {}",
                      allInvalidFeatures.stream().map( f -> f.getId() ).collect( Collectors.joining( "\n" ) ) );
        }
        List<BadGeometry> badGeometries = allInvalidFeatures.stream().map( f -> {
            String error = String.format( ERROR_MSG, f.getId() );
            return new BadGeometry( getOriginalGeometry( f ), error );
        } ).collect( Collectors.toList() );
        return badGeometries;
    }

    private Feature retrieveGeltungsbereichFeature( String bereichId ) {
        if ( bereichFeatures.containsKey( bereichId ) && bereichFeatures.get( bereichId ) != null ) {
            Feature bereich = bereichFeatures.get( bereichId );
            AbstractDefaultGeometry bereichGeom = getOriginalGeometry( bereich );
            if ( bereichGeom != null )
                return bereich;
        }
        return planFeature;
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

    private boolean isPraesentationsobjekt( Feature feature ) {
        String featureLocalName = feature.getName().getLocalPart();
        return PRAESENTATIONSOBJEKTE.contains( featureLocalName );
    }

    private String getGehortZuBereichId( Feature feature ) {
        Property property = feature.getProperties(
                                new QName( feature.getName().getNamespaceURI(), GEHOERT_ZU_BEREICH ) ).get( 0 );
        TypedObjectNode value = property.getValue();
        if ( value instanceof FeatureReference )
            return ( (FeatureReference) value ).getURI().substring( 1 );
        return value.toString().substring( 1 );
    }

    private Geometry getGeometry( Feature feature ) {
        AbstractDefaultGeometry originalGeometry = getOriginalGeometry( feature );
        if ( originalGeometry != null )
            return originalGeometry.getJTSGeometry();
        return null;
    }

    private AbstractDefaultGeometry getOriginalGeometry( Feature feature ) {
        List<Property> geometryProperties = feature.getGeometryProperties();
        if ( !geometryProperties.isEmpty() )
            return  (AbstractDefaultGeometry) geometryProperties.get( 0 ).getValue();
        return null;
    }
}