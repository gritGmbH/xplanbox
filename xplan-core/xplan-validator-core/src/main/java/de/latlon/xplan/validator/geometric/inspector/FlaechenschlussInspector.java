package de.latlon.xplan.validator.geometric.inspector;

import de.latlon.xplan.validator.geometric.ControlPoint;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.datetime.Date;
import org.deegree.commons.tom.datetime.ISO8601Converter;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.feature.Feature;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.i18n.Messages;
import org.deegree.geometry.multi.MultiSurface;
import org.deegree.geometry.points.Points;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.Surface;
import org.deegree.geometry.primitive.patches.PolygonPatch;
import org.deegree.geometry.primitive.patches.SurfacePatch;
import org.deegree.geometry.primitive.segments.Arc;
import org.deegree.geometry.primitive.segments.ArcByBulge;
import org.deegree.geometry.primitive.segments.ArcString;
import org.deegree.geometry.primitive.segments.ArcStringByBulge;
import org.deegree.geometry.primitive.segments.BSpline;
import org.deegree.geometry.primitive.segments.Bezier;
import org.deegree.geometry.primitive.segments.Circle;
import org.deegree.geometry.primitive.segments.CubicSpline;
import org.deegree.geometry.primitive.segments.CurveSegment;
import org.deegree.geometry.primitive.segments.Geodesic;
import org.deegree.geometry.primitive.segments.GeodesicString;
import org.deegree.geometry.primitive.segments.LineStringSegment;
import org.deegree.geometry.standard.AbstractDefaultGeometry;
import org.deegree.geometry.standard.points.PointsPoints;
import org.deegree.gml.feature.FeatureInspectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Collections;
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
public class FlaechenschlussInspector implements GeometricFeatureInspector {

    private static final Logger LOG = LoggerFactory.getLogger( FlaechenschlussInspector.class );

    private static final String ERROR_MSG = "2.2.1.1: Das Object mit der gml id %s erfuellt die Flaechenschlussbedingung am Stuetzpunkt %s nicht.";

    private static final String ERROR_MSG_INVALID_GELTUNGSBEREICH = "2.2.1.1: Der Geltungsbereich des Plans ist geometrisch nicht valide. Betroffen ist das Feature mit der gml id %s. Die Pruefung der Flaechenschlussbedingung kann nicht durchgeführt werden.";

    private Geometry geltungsbereich;

    private final List<ControlPoint> controlPoints = new ArrayList<>();

    private final Date now = new Date( new java.util.Date(), null );

    private BadGeometry invalidGeltungsbereich;

    @Override
    public Feature inspect( Feature feature )
                            throws FeatureInspectionException {
        if ( invalidGeltungsbereich != null )
            return feature;
        if ( isFlaechenschlussobjekt( feature ) ) {
            Geometry geometry = getGeometry( feature );
            checkAndAddGeometry( feature, geometry );
        }
        if ( isGeltungsbereichFeature( feature ) ) {
            geltungsbereich = (Geometry) feature.getGeometryProperties().get( 0 ).getValue();
            if ( !( (AbstractDefaultGeometry) geltungsbereich ).getJTSGeometry().isValid() ) {
                invalidGeltungsbereich = new BadGeometry( geltungsbereich,
                                                          String.format( ERROR_MSG_INVALID_GELTUNGSBEREICH,
                                                                         feature.getId() ) );
            }
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
    @Override
    public List<BadGeometry> checkGeometricRule() {
        if ( invalidGeltungsbereich != null )
            return Collections.singletonList( invalidGeltungsbereich );

        controlPoints.stream().forEach( cp -> checkForIdenticalControlPoint( cp ) );
        List<ControlPoint> controlPointsWithInvalidFlaechenschluss = controlPoints.stream().filter(
                                cp -> !isPartOfGeltungsbereich( cp.getPoint() )
                                      && !cp.hasIdenticalControlPoint() ).collect( Collectors.toList() );

        List<BadGeometry> flaechenschlussErrors = controlPointsWithInvalidFlaechenschluss.stream().map( cp -> {
            String error = String.format( ERROR_MSG, cp.getFeatureGmlId(), cp.getPoint() );
            return new BadGeometry( cp.getPoint(), error ); } ).collect( Collectors.toList() );
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
        Points controlPoints = getControlPoints( surface );
        controlPoints.forEach( p -> checkAndAdd( feature.getId(), p ) );
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
     * Von der Erfuellung der Flaechenschlussbedingung ausgenommen sind die
     * raumbezogenen Objekte des BPlan-Schemas (s. Kap. 0), deren Wirksamkeit
     * durch sachliche oder zeitliche Bedingungen (Attribute startBedingung und
     * endeBedingung in BP_Objekt) so eingeschraenkt sind, dass sie nicht
     * gleichzeitig rechtswirksam sind.
     */
    private boolean isFlaechenschlussobjekt( Feature feature ) {
        String namespaceURI = feature.getType().getName().getNamespaceURI();
        return isFlaechenschluss( feature, namespaceURI ) && isEbene0( feature, namespaceURI ) && isFlaechenhaft(
                                feature, namespaceURI ) && isWirksam( feature, namespaceURI );
    }

    /* Alle Objekte mit flaechenhaftem Raumbezug, */
    private boolean isFlaechenhaft( Feature feature, String namespaceURI ) {
        QName positionPropName = new QName( namespaceURI, "position" );
        Property property = getProperty( positionPropName, feature );
        if ( property != null ) {
            TypedObjectNode value = property.getValue();
            return value != null && ( value instanceof MultiSurface || value instanceof Surface );
        }
        return false;
    }

    /* die zur Ebene 0 (ebene == 0) gehoeren */
    private boolean isEbene0( Feature feature, String namespaceURI ) {
        QName ebenePropName = new QName( namespaceURI, "ebene" );
        Property property = getProperty( ebenePropName, feature );
        if ( property != null ) {
            PrimitiveValue value = (PrimitiveValue) property.getValue();
            return value == null || Integer.valueOf( value.getAsText() ) == 0;
        }
        return true;
    }

    /* und bei denen das Attribut flaechenschluss den Wert true hat */
    private boolean isFlaechenschluss( Feature feature, String namespaceURI ) {
        QName flaechenschlussPropName = new QName( namespaceURI, "flaechenschluss" );
        Property property = getProperty( flaechenschlussPropName, feature );
        if ( property != null ) {
            PrimitiveValue value = (PrimitiveValue) property.getValue();
            return value != null && Boolean.valueOf( value.getAsText() );
        }
        return false;
    }

    /* ... raumbezogenen Objekte des BPlan-Schemas [...], deren Wirksamkeit durch [...] zeitliche Bedingungen (Attribute startBedingung und endeBedingung in BP_Objekt) so eingeschraenkt sind, dass sie nicht gleichzeitig rechtswirksam sind. */
    private boolean isWirksam( Feature feature, String namespaceURI ) {
        if ( !feature.getName().getLocalPart().startsWith( "BP_" ) )
            return true;
        QName startBedingungPropName = new QName( namespaceURI, "startBedingung" );
        Date startBedingung = getWirsamkeitDatum( feature, startBedingungPropName );
        boolean isAfterStart = true;
        if ( startBedingung != null ) {
            int i = now.compareTo( startBedingung );
            isAfterStart = i >= 0;
        }
        QName endeBedingungPropName = new QName( namespaceURI, "endeBedingung" );
        Date endeBedingung = getWirsamkeitDatum( feature, endeBedingungPropName );
        boolean isBeforeEnd = true;
        if ( endeBedingung != null ) {
            int i = now.compareTo( endeBedingung );
            isBeforeEnd = i <= 0;
        }
        return isAfterStart && isBeforeEnd;
    }

    private Date getWirsamkeitDatum( Feature feature, QName propName ) {
        Property property = getProperty( propName, feature );
        if ( property != null && !property.getChildren().isEmpty() ) {
            String namespaceURI = feature.getName().getNamespaceURI();
            QName xpWirksamkeitBedingungName = new QName( namespaceURI, "XP_WirksamkeitBedingung" );
            GenericXMLElement xpWirksamkeitBedingung = getChildWithName( property.getChildren(),
                                                                         xpWirksamkeitBedingungName );
            if ( xpWirksamkeitBedingung != null ) {
                QName datumAbsolutName = new QName( namespaceURI, "datumAbsolut" );
                GenericXMLElement datumAbsolut = getChildWithName( xpWirksamkeitBedingung.getChildren(),
                                                                   datumAbsolutName );
                if ( datumAbsolut != null ) {
                    String dateAsText = datumAbsolut.getValue().getAsText();
                    return ISO8601Converter.parseDate( dateAsText );
                }
            }
        }
        return null;
    }

    private GenericXMLElement getChildWithName( List<TypedObjectNode> childs, QName name ) {
        if ( childs.isEmpty() )
            return null;
        for ( TypedObjectNode child : childs ) {
            GenericXMLElement genericXMLElement = (GenericXMLElement) child;
            if ( genericXMLElement instanceof GenericXMLElement )
                if ( name.equals( genericXMLElement.getName() ) ) {
                    return genericXMLElement;
                }
        }
        return null;
    }

    private Points getControlPoints( Surface surface ) {
        final List<CurveSegment> segments = getSegments( surface );

        List<Points> pointsList = new ArrayList<>( segments.size() );
        for ( CurveSegment segment : segments ) {
            CurveSegment.CurveSegmentType segmentType = segment.getSegmentType();
            switch ( segmentType ) {
            case ARC:
                pointsList.add( ( (Arc) segment ).getControlPoints() );
                break;
            case ARC_BY_BULGE:
                pointsList.add( ( (ArcByBulge) segment ).getControlPoints() );
                break;
            case ARC_STRING:
                pointsList.add( ( (ArcString) segment ).getControlPoints() );
                break;
            case ARC_STRING_BY_BULGE:
                pointsList.add( ( (ArcStringByBulge) segment ).getControlPoints() );
                break;
            case BEZIER:
                pointsList.add( ( (Bezier) segment ).getControlPoints() );
                break;
            case BSPLINE:
                pointsList.add( ( (BSpline) segment ).getControlPoints() );
                break;
            case CIRCLE:
                pointsList.add( ( (Circle) segment ).getControlPoints() );
                break;
            case CUBIC_SPLINE:
                pointsList.add( ( (CubicSpline) segment ).getControlPoints() );
                break;
            case GEODESIC:
                pointsList.add( ( (Geodesic) segment ).getControlPoints() );
                break;
            case GEODESIC_STRING:
                pointsList.add( ( (GeodesicString) segment ).getControlPoints() );
                break;
            case LINE_STRING_SEGMENT:
                pointsList.add( ( (LineStringSegment) segment ).getControlPoints() );
                break;
            case OFFSET_CURVE:
                break;
            case ARC_BY_CENTER_POINT:
            case CIRCLE_BY_CENTER_POINT:
            case CLOTHOID:
            default:
                throw new IllegalArgumentException( "Surfaces with segments of type " + segmentType
                                                    + " are currently not supported." );
            }
        }
        return new PointsPoints( pointsList );
    }

    private List<CurveSegment> getSegments( Surface surface ) {
        List<? extends SurfacePatch> patches = surface.getPatches();
        if ( patches.size() == 1 ) {
            if ( patches.get( 0 ) instanceof PolygonPatch ) {
                PolygonPatch patch = (PolygonPatch) patches.get( 0 );
                List<CurveSegment> curveSegments = new ArrayList<>();
                curveSegments.addAll( patch.getExteriorRing().getCurveSegments() );
                for ( Ring ir : patch.getInteriorRings() ) {
                    curveSegments.addAll( ir.getCurveSegments() );
                }
                return curveSegments;
            }
            throw new IllegalArgumentException( Messages.getMessage( "SURFACE_IS_NON_PLANAR" ) );
        }
        throw new IllegalArgumentException( Messages.getMessage( "SURFACE_MORE_THAN_ONE_PATCH" ) );
    }

    private Property getProperty( QName propName, Feature feature ) {
        List<Property> positionProperties = feature.getProperties( propName );
        if ( !positionProperties.isEmpty() )
            return positionProperties.get( 0 );
        return null;
    }

}