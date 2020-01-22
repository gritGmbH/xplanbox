package de.latlon.xplan.validator.geometric.inspector;

import com.vividsolutions.jts.geom.Geometry;
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

    private static final List<String> GEHOERT_ZU_BEREICH_PROPNAMES = new ArrayList<>();

    private static final String ERROR_MSG = "2.2.3.1: Das Objekt mit der gml id %s liegt nicht im Geltungsbereich des Bereichs/Plans.";

    private static final String ERROR_MSG_INVALID_GELTUNGSBEREICH = "2.2.3.1: Der Geltungsbereich des Plans ist geometrisch nicht valide. Betroffen ist das Feature mit der gml id %s. Die Pruefung des Geltungsbereichs kann nicht durchgeführt werden.";

    private static final List<String> PRAESENTATIONSOBJEKTE = Arrays.asList( "XP_FPO", "XP_LPO", "XP_LTO", "XP_PPO",
                                                                             "XP_PTO", "XP_TPO" );

    public static final double TOLERANCE_METRE = 0.001;

    private Feature planFeature;

    private Map<String, Feature> bereichFeatures = new HashMap<>();

    private Map<String, List<Feature>> bereichIdToFeatureGeoms = new HashMap<>();

    static {
        GEHOERT_ZU_BEREICH_PROPNAMES.add( "gehoertZuBereich" );
        GEHOERT_ZU_BEREICH_PROPNAMES.add( "gehoertZuBereich" );
        GEHOERT_ZU_BEREICH_PROPNAMES.add( "gehoertZuBP_Bereich" );
        GEHOERT_ZU_BEREICH_PROPNAMES.add( "gehoertZuLP_Bereich" );
        GEHOERT_ZU_BEREICH_PROPNAMES.add( "gehoertZuFP_Bereich" );
        GEHOERT_ZU_BEREICH_PROPNAMES.add( "gehoertZuRP_Bereich" );
        GEHOERT_ZU_BEREICH_PROPNAMES.add( "gehoertZuSO_Bereich" );
    }

    @Override
    public Feature inspect( Feature feature )
                            throws FeatureInspectionException {

        if ( isPlanFeature( feature ) ) {
            planFeature = feature;
        } else if ( isBereichFeature( feature ) ) {
            bereichFeatures.put( feature.getId(), feature );
        } else if ( !isPraesentationsobjekt( feature ) ) {
            String gehortZuBereich = getGehortZuBereichId( feature );
            if ( gehortZuBereich != null ) {
                if ( !bereichIdToFeatureGeoms.containsKey( gehortZuBereich ) )
                    bereichIdToFeatureGeoms.put( gehortZuBereich, new ArrayList<>() );
                bereichIdToFeatureGeoms.get( gehortZuBereich ).add( feature );
            }
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
            Geometry geltungsbereichWithBuffer = geltungsbereich.buffer( TOLERANCE_METRE );
            List<Feature> invalidFeatures = bereichIdToGeoms.getValue().stream().filter(
                                    f -> !isInsideGeom( f, geltungsbereichWithBuffer ) ).collect( Collectors.toList() );
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

    private boolean isPraesentationsobjekt( Feature feature ) {
        String featureLocalName = feature.getName().getLocalPart();
        return PRAESENTATIONSOBJEKTE.contains( featureLocalName );
    }

    private String getGehortZuBereichId( Feature feature ) {
        for ( String propName : GEHOERT_ZU_BEREICH_PROPNAMES ) {
            String gehortZuBereichId = getGehortZuBereichId( feature, propName );
            if ( gehortZuBereichId != null )
                return gehortZuBereichId;
        }
        return null;
    }

    private String getGehortZuBereichId( Feature feature, String propName ) {
        QName qName = new QName( feature.getName().getNamespaceURI(), propName );
        List<Property> properties = feature.getProperties( qName );
        if ( properties == null || properties.isEmpty() )
            return null;
        Property property = properties.get( 0 );
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
            return (AbstractDefaultGeometry) geometryProperties.get( 0 ).getValue();
        return null;
    }
}