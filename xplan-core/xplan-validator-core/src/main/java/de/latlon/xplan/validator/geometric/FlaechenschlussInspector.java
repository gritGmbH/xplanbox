package de.latlon.xplan.validator.geometric;

import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.points.Points;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Surface;
import org.deegree.gml.feature.FeatureInspectionException;
import org.deegree.gml.feature.FeatureInspector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Inspector for 2.2.1 Flaechenschlussbedingung:
 *
 * <ul>
 *     <li>Bei allen Objekten mit flaechenhaftem Raumbezug muss das Attribut flaechenschluss belegt sein.</li>
 *     <li>Alle Objekte mit flaechenhaftem Raumbezug, die zur Ebene 0 (ebene == 0) gehoeren und bei denen das Attribut flaechenschluss den Wert true hat (Flaechenschlussobjekte), muessen die Flaechenschlussbedingung erfuellen. Das bedeutet, dass sich die jeweiligen Flaechen nicht ueberlappen, sondern nur an gemeinsamen Raendern beruehren duerfen und jeweils identische Stuetzpunkte aufweisen muessen. Zwei Punktkoordinaten werden als identisch betrachtet wenn ihr euklidischer Abstand kleiner als 2 mm ist.</li>
 *     <li>Von der Erfuellung der Flaechenschlussbedingung ausgenommen sind die raumbezogenen Objekte des BPlan-Schemas (s. Kap. 0), deren Wirksamkeit durch sachliche oder zeitliche Bedingungen (Attribute startBedingung und endeBedingung in BP_Objekt) so eingeschraenkt sind, dass sie nicht gleichzeitig rechtswirksam sind.</li>
 * </ul>
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class FlaechenschlussInspector implements FeatureInspector {

    private static final Logger LOG = LoggerFactory.getLogger( FlaechenschlussInspector.class );

    private static final String ERROR_MSG = "Das Object mit der gml id %s erfuellt die Flaechenschlussbedingung am Stuetzpunkt %s nicht.";

    private Geometry geltungsbereich;

    private List<ControlPoint> controlPoints = new ArrayList<>();

    @Override
    public Feature inspect( Feature feature )
                            throws FeatureInspectionException {
        if ( isFlaechenschlussobjekt( feature ) ) {
            Geometry geometry = getGeometry( feature );
            checkAndAddGeometry( feature, geometry );
        }
        if ( isGeltungsbereichFeature( feature ) ) {
            geltungsbereich = (Geometry) feature.getGeometryProperties().get( 0 ).getValue();
        }
        return feature;
    }

    /**
     * Checks if the Flaechenschlussbedingung is satisfied:
     * Das bedeutet, dass sich die jeweiligen Flaechen nicht ueberlappen,
     * sondern nur an gemeinsamen Raendern beruehren duerfen und jeweils identische Stuetzpunkte aufweisen muessen.
     * Zwei Punktkoordinaten werden als identisch betrachtet wenn ihr euklidischer Abstand kleiner als 2 mm ist.
     *
     * @return <code>true</code> if the Flaechenschlussbedingung is satisfied, <code>false</code> otherwise
     */
    public List<String> checkFlaechenschluss() {
        controlPoints.stream().forEach( cp -> checkForIdenticalControlPoint( cp ) );
        List<ControlPoint> controlPointsWithInvalidFlaechenschluss = controlPoints.stream().filter(
                                cp -> !isPartOfGeltungsbereich( cp.getPoint() )
                                      && !cp.hasIdenticalControlPoint() ).collect( Collectors.toList() );

        List<String> flaechenschlussErrors = controlPointsWithInvalidFlaechenschluss.stream().map(
                                cp -> String.format( ERROR_MSG, cp.getFeatureGmlId(), cp.getPoint() ) ).collect(
                                Collectors.toList() );
        if ( flaechenschlussErrors.isEmpty() ) {
            LOG.info( "No features with invalid flaechenschluss" );
        } else {
            LOG.info( "Features with invalid flaechenschluss:\n {}",
                      controlPointsWithInvalidFlaechenschluss.stream().map(
                                              cp -> cp.getFeatureGmlId() + ": " + cp.getPoint() ).collect(
                                              Collectors.joining( "\n" ) ) );
        }
        return flaechenschlussErrors;
    }

    private void checkAndAddGeometry( Feature feature, Geometry geometry ) {
        if ( geometry instanceof Surface ) {
            Surface surface = (Surface) geometry;
            checkAndAddSurface( feature, surface );
        } else if ( geometry instanceof MultiSurface ) {
            MultiSurface multiSurface = (MultiSurface) geometry;
            multiSurface.stream().forEach( o -> checkAndAddGeometry( feature, (Geometry) o ) );
        } else {
            LOG.warn( "Geometry type {} is not supported to be part of the flaechenschluss",
                      geometry.getGeometryType() );
        }
    }

    private void checkAndAddSurface( Feature feature, Surface surface ) {
        Points exteriorRingCoordinates = surface.getExteriorRingCoordinates();
        exteriorRingCoordinates.forEach( p -> checkAndAdd( feature.getId(), p ) );

        List<Points> interiorRingsCoordinates = surface.getInteriorRingsCoordinates();
        interiorRingsCoordinates.forEach( ir -> ir.forEach( p -> checkAndAdd( feature.getId(), p ) ) );
    }

    private void checkAndAdd( String id, Point point ) {
        if ( isPartOfGeltungsbereich( point ) ) {
            LOG.debug( "Control point {} of {} is on the polygon of the plan", point, id );
        } else {
            LOG.debug( "Control point {} of {} is not on the polygon of the plan", point, id );
            boolean hasIdenticalControlPoint = hasIdenticalControlPoints( point );
            controlPoints.add( new ControlPoint( id, point, hasIdenticalControlPoint ) );
        }
    }

    private void checkForIdenticalControlPoint( ControlPoint cpToCheck ) {
        if ( cpToCheck.hasIdenticalControlPoint() ) {
            return;
        }
        LOG.debug( "Check control point {}", cpToCheck );
        List<ControlPoint> controlPointsWithIdenticalPoint = controlPoints.stream().filter(
                                cp -> cp != cpToCheck && cp.checkIfIdentical( cpToCheck ) ).collect(
                                Collectors.toList() );
        boolean hasIdenticalControlsPoints = !controlPointsWithIdenticalPoint.isEmpty();
        if ( hasIdenticalControlsPoints ) {
            LOG.debug( "Control point {} is identical to control points {} ", cpToCheck,
                       controlPointsWithIdenticalPoint.stream().map( cp -> cp.toString() ).collect(
                                               Collectors.joining( ", " ) ) );
        } else {
            LOG.debug( "Control point {} has no identical control points", cpToCheck );
        }
    }

    private boolean hasIdenticalControlPoints( Point point ) {
        long numberOfControlPointsWithIdenticalPoint = controlPoints.stream().filter(
                                cp -> cp.checkIfIdentical( point ) ).count();
        return numberOfControlPointsWithIdenticalPoint > 0;
    }

    private boolean isPartOfGeltungsbereich( Point point ) {
        if ( geltungsbereich != null ) {
            return geltungsbereich.touches( point );
        }
        return false;
    }

    private Geometry getGeometry( Feature feature ) {
        String namespaceURI = feature.getType().getName().getNamespaceURI();
        QName positionPropName = new QName( namespaceURI, "position" );
        List<Property> positionProperties = feature.getProperties( positionPropName );
        Property property = positionProperties.get( 0 );
        return (Geometry) property.getValue();
    }

    private boolean isGeltungsbereichFeature( Feature feature ) {
        return feature.getName().getLocalPart().endsWith( "_Plan" );
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