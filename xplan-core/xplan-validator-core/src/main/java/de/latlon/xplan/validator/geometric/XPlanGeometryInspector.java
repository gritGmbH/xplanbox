package de.latlon.xplan.validator.geometric;

import com.vividsolutions.jts.algorithm.CGAlgorithms;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.Polygon;
import de.latlon.xplan.validator.geometric.report.BadGeometry;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.GeometryFactory;
import org.deegree.geometry.GeometryInspectionException;
import org.deegree.geometry.GeometryInspector;
import org.deegree.geometry.linearization.CurveLinearizer;
import org.deegree.geometry.linearization.LinearizationCriterion;
import org.deegree.geometry.linearization.MaxErrorCriterion;
import org.deegree.geometry.points.Points;
import org.deegree.geometry.primitive.Curve;
import org.deegree.geometry.primitive.GeometricPrimitive;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.primitive.Surface;
import org.deegree.geometry.primitive.patches.PolygonPatch;
import org.deegree.geometry.primitive.patches.SurfacePatch;
import org.deegree.geometry.primitive.segments.Arc;
import org.deegree.geometry.primitive.segments.Circle;
import org.deegree.geometry.primitive.segments.CurveSegment;
import org.deegree.geometry.primitive.segments.LineStringSegment;
import org.deegree.geometry.standard.primitive.DefaultPolygon;
import org.deegree.geometry.standard.surfacepatches.DefaultPolygonPatch;
import org.deegree.geometry.validation.GeometryFixer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Validiert die aus XPlan-Dokumenten geparsten Geometrien und prüft sie auf topologische Korrektheit / Randbedingungen.
 * <p>
 * <li>Punkte/Punktelisten: Prüfung auf 2D-Koordinaten (Fehler, wird nicht korrigiert)</li>
 * <li>Kurven: Segment-Kontinuität (Fehler, wird nicht korrigiert)</li>
 * <li>Generische Kurve/LineString: falls geschlossen, Laufrichtung CCW (Warnung, wird korrigiert)</li>
 * <li>Ring: Geschlossenheit (Warnung/Fehler, wird korrigiert wenn Abstand Startpunkt zu Endpunkt kleiner als Epsilon)</li>
 * <li>Ring: Keine Selbstüberschneidung (Fehler)</li>
 * <li>Polygon/PolygonPatch: Äußerer Ring, Orientierung CCW (Warnung, wird korrigiert).</li>
 * <li>Polygon/PolygonPatch: Innere Ringe, Orientierung CW (Warnung, wird korrigiert).</li>
 * <li>Polygon/PolygonPatch: Keine Berührung äußerer Ring / innere Ringe (Fehler)</li>
 * <li>Polygon/PolygonPatch: Kein Schnitt äußerer Ring / innere Ringe (Fehler)</li>
 * <li>Polygon/PolygonPatch: Innere Ringe liegen innerhalb der vom äußeren Ring umschlossenen Fläche (Fehler)</li>
 * <li>Polygon/PolygonPatch: Keine Berührung innere Ringe / innere Ringe (Fehler)</li>
 * <li>Polygon/PolygonPatch: Schnittmenge der von zwei inneren Ringen gebildeten Flächen ist leer (Fehler)</li>
 * </p>
 *
 * @author <a href="mailto:schneider@lat-lon.de">Markus Schneider</a>
 * @author last edited by: $Author: schneider $
 * @version $Revision: 1154 $, $Date: 2010-03-02 13:23:14 +0100 (Di, 02 Mrz 2010) $
 */
class XPlanGeometryInspector implements GeometryInspector {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanGeometryInspector.class );

    private final com.vividsolutions.jts.geom.GeometryFactory jtsFactory;

    private final LinearizationCriterion crit;

    private final CurveLinearizer linearizer;

    private final XMLStreamReaderWrapper xmlStream;

    private final List<String> errors = new ArrayList<>();

    private final List<String> warnings = new ArrayList<>();

    private final List<BadGeometry> badGeometries = new ArrayList<>();

    private BadGeometry lastBadGeometry;

    public XPlanGeometryInspector( XMLStreamReaderWrapper xmlStream ) {
        this.xmlStream = xmlStream;
        this.crit = new MaxErrorCriterion( 1.0, 500 );
        this.linearizer = new CurveLinearizer( new GeometryFactory() );
        this.jtsFactory = new com.vividsolutions.jts.geom.GeometryFactory();
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public List<BadGeometry> getBadGeometries() {
        return badGeometries;
    }

    @Override
    public Geometry inspect( Geometry geom )
                    throws GeometryInspectionException {
        Geometry inspected = geom;
        try {
            switch ( inspected.getGeometryType() ) {
            case PRIMITIVE_GEOMETRY: {
                inspected = inspect( (GeometricPrimitive) inspected );
                break;
            }
            case COMPOSITE_GEOMETRY: {
                String msg = createMessage( "Composites k\u00f6nnen lt. XPlanGML-Schema nicht auftreten." );
                createError( msg );
            }
            default:
                LOG.warn( "Unsupported geometry type (will be ignored) {}", inspected.getGeometryType() );
            }
        } catch ( Exception e ) {
            LOG.trace( "Fehler bei der geometrischen Validierung!", e );
            String message = createMessage( e.getMessage() );
            createError( message );
        }
        addLastBadGeometry( inspected );

        return inspected;
    }

    @Override
    public CurveSegment inspect( CurveSegment segment )
                    throws GeometryInspectionException {
        return segment;
    }

    @Override
    public SurfacePatch inspect( SurfacePatch patch )
                    throws GeometryInspectionException {
        SurfacePatch inspected = patch;
        if ( !( patch instanceof PolygonPatch ) ) {
            String msg = createMessage( "Nicht-planare Patches d\u00fcrfen gem\u00e4\u00df XPlanGML-Schema nicht auftreten." );
            createError( msg );
        } else {
            inspected = inspect( (PolygonPatch) patch );
        }
        return inspected;
    }

    @Override
    public Points inspect( Points points )
                    throws GeometryInspectionException {
        if ( points.getDimension() != 2 ) {
            String msg = createMessage( String.format( "Punkteliste mit ung\u00fcltiger Dimensionalit\u00e4t (%d). Nur 2D Koordinaten sind erlaubt.",
                                                       points.getDimension() ) );
            createError( msg );
        }
        return points;
    }

    private Curve checkSegmentContinuity( Curve geom ) {
        Point lastSegmentEndPoint = null;
        int segmentIdx = 0;
        for ( CurveSegment segment : geom.getCurveSegments() ) {
            Point startPoint = segment.getStartPoint();
            if ( lastSegmentEndPoint != null ) {
                if ( startPoint.get0() != lastSegmentEndPoint.get0() || startPoint.get1() != lastSegmentEndPoint.get1() ) {
                    String msg = createMessage( String.format( "L\u00fccke zwischen Kurvensegment %d und %d: %s != %s",
                                                               segmentIdx, segmentIdx++, lastSegmentEndPoint,
                                                               startPoint ) );
                    createError( msg );
                }
            }
            segmentIdx++;
            lastSegmentEndPoint = segment.getEndPoint();
        }
        return geom;
    }

    void checkSelfIntersection( Ring ring ) {
        LineString jtsLineString = getJTSLineString( ring );
        boolean selfIntersection = !jtsLineString.isSimple();
        if ( selfIntersection ) {
            String msg = createMessage( "2.2.2.1: Selbst\u00fcberschneidung." );
            createError( msg );
        }
    }

    Ring checkClosed( Ring ring ) {
        Point startPoint = ring.getStartPoint();
        Point endPoint = ring.getEndPoint();
        if ( !startPoint.equals( endPoint ) ) {
            double dist = startPoint.getDistance( endPoint, null ).getValueAsDouble();
            String msg = createMessage(
                                    String.format( "2.2.2.1: Ring nicht geschlossen: %s != %s, Abstand: %s", startPoint,
                                                   endPoint, dist ) );
            createError( msg );
        }
        return ring;
    }

    void checkSelfIntersection( PolygonPatch inspected ) {
        try {
            Ring exteriorRing = inspected.getExteriorRing();
            LinearRing exteriorJTSRing = getJTSRing( exteriorRing );
            Polygon exteriorJTSRingAsPolygon = jtsFactory.createPolygon( exteriorJTSRing, null );

            List<Ring> interiorRings = inspected.getInteriorRings();
            List<LinearRing> interiorJTSRings = new ArrayList<LinearRing>( interiorRings.size() );
            List<Polygon> interiorJTSRingsAsPolygons = new ArrayList<Polygon>( interiorRings.size() );
            for ( Ring interiorRing : interiorRings ) {
                LinearRing interiorJTSRing = getJTSRing( interiorRing );
                interiorJTSRings.add( interiorJTSRing );
                interiorJTSRingsAsPolygons.add( jtsFactory.createPolygon( interiorJTSRing, null ) );
            }

            LOG.debug( "Validating spatial relations between exterior ring and interior rings." );
            for ( int ringIdx = 0; ringIdx < interiorJTSRings.size(); ringIdx++ ) {
                LinearRing interiorJTSRing = interiorJTSRings.get( ringIdx );
                interiorJTSRingsAsPolygons.get( ringIdx );
                if ( exteriorJTSRing.touches( interiorJTSRing ) ) {
                    String msg = createMessage( String.format( "2.2.2.1: \u00c4u\u00dferer Ring ber\u00fchrt den inneren Ring mit Index %d.",
                                                               ringIdx ) );
                    createError( msg );
                }
                if ( exteriorJTSRing.intersects( interiorJTSRing ) ) {
                    String msg = createMessage( String.format( "2.2.2.1: \u00c4u\u00dferer Ring schneidet den inneren Ring mit Index %d.",
                                                               ringIdx ) );
                    createError( msg );
                }
                if ( !interiorJTSRing.within( exteriorJTSRingAsPolygon ) ) {
                    String msg = createMessage( String.format( "2.2.2.1: Innerer Ring mit Index %d befindet sich nicht innerhalb des \u00e4u\u00dferen Rings.",
                                                               ringIdx ) );
                    createError( msg );
                }
            }

            LOG.debug( "Validating spatial relations between pairs of interior rings." );
            for ( int ring1Idx = 0; ring1Idx < interiorJTSRings.size(); ring1Idx++ ) {
                for ( int ring2Idx = ring1Idx; ring2Idx < interiorJTSRings.size(); ring2Idx++ ) {
                    if ( ring1Idx == ring2Idx ) {
                        continue;
                    }
                    LinearRing interior1JTSRing = interiorJTSRings.get( ring1Idx );
                    Polygon interior1JTSRingAsPolygon = interiorJTSRingsAsPolygons.get( ring1Idx );
                    LinearRing interior2JTSRing = interiorJTSRings.get( ring2Idx );
                    Polygon interior2JTSRingAsPolygon = interiorJTSRingsAsPolygons.get( ring2Idx );
                    if ( interior1JTSRing.touches( interior2JTSRing ) ) {
                        String msg = createMessage( String.format( "2.2.2.1: Der innere Ring %d ber\u00fchrt den inneren Ring mit Index %d.",
                                                                   ring1Idx, ring2Idx ) );
                        createError( msg );
                    }
                    if ( interior1JTSRing.intersects( interior2JTSRing ) ) {
                        LOG.debug( "Interior intersects interior." );
                        String msg = createMessage( String.format( "2.2.2.1: Der innere Ring %d schneidet den inneren Ring mit Index %d.",
                                                                   ring1Idx, ring2Idx ) );
                        createError( msg );
                    }
                    if ( interior1JTSRing.within( interior2JTSRingAsPolygon ) ) {
                        String msg = createMessage( String.format( "2.2.2.1: Der innere Ring %d liegt innerhalb des inneren Rings mit Index %d.",
                                                                   ring1Idx, ring2Idx ) );
                        createError( msg );
                    }
                    if ( interior2JTSRing.within( interior1JTSRingAsPolygon ) ) {
                        String msg = createMessage( String.format( "2.2.2.1: Der innere Ring %d liegt innerhalb des inneren Rings mit Index %d.",
                                                                   ring2Idx, ring1Idx ) );
                        createError( msg );
                    }
                }
            }
        } catch ( Exception e ) {
            String msg = createMessage( "Validierung der Oberfl\u00e4chen-Topologie fehlgeschlagen (Folgefehler!?)." );
            getErrors().add( msg ); // don't use cm errors - mocking!
        }
    }

    String createMessage( String msg ) {
        String elementName = xmlStream.getName().getLocalPart();
        int lineNumber = xmlStream.getLocation().getLineNumber();
        int columnNumber = xmlStream.getLocation().getColumnNumber();
        return String.format( "%s (Zeile %d, Spalte %d): %s", elementName, lineNumber, columnNumber, msg );
    }

    void createError( String msg ) {
        errors.add( msg );
        if ( lastBadGeometry == null ) {
            lastBadGeometry = new BadGeometry();
        }
        lastBadGeometry.addError( msg );
    }

    PolygonPatch checkRingOrientations( PolygonPatch patch ) {

        PolygonPatch inspected = patch;
        boolean needsRebuild = false;

        Ring exteriorRing = checkOuterRing( inspected.getExteriorRing() );
        if ( exteriorRing != inspected.getExteriorRing() ) {
            needsRebuild = true;
        }

        List<Ring> interiorRings = new ArrayList<Ring>( patch.getInteriorRings().size() );
        for ( Ring interiorRing : patch.getInteriorRings() ) {
            Ring newInteriorRings = checkInnerRing( interiorRing );
            interiorRings.add( newInteriorRings );
            if ( interiorRing != newInteriorRings ) {
                needsRebuild = true;
            }
        }

        if ( needsRebuild ) {
            inspected = new DefaultPolygonPatch( exteriorRing, interiorRings );
        }

        return inspected;
    }

    private Ring checkOuterRing( Ring ring ) {
        Ring inspected = ring;
        LinearRing jTSRing = getJTSRing( inspected );
        if ( !CGAlgorithms.isCCW( jTSRing.getCoordinates() ) ) {
            String msg = createMessage( "2.2.2.1: \u00c4u\u00dferer Ring verwendet falsche Laufrichtung (CW)." );
            warnings.add( msg );
            inspected = GeometryFixer.invertOrientation( ring );
        }
        return inspected;
    }

    private Ring checkInnerRing( Ring ring ) {
        Ring inspected = ring;
        LinearRing jTSRing = getJTSRing( inspected );
        if ( CGAlgorithms.isCCW( jTSRing.getCoordinates() ) ) {
            String msg = createMessage( "2.2.2.1: Innerer Ring verwendet falsche Laufrichtung (CCW)." );
            warnings.add( msg );
            inspected = GeometryFixer.invertOrientation( ring );
        }
        return inspected;
    }

    /**
     * Returns a JTS geometry for the given {@link Curve} (which is linearized first).
     *
     * @param curve
     *            {@link Curve} that consists of {@link LineStringSegment} and {@link Arc} segments only
     * @return linear JTS curve geometry
     * @throws IllegalArgumentException
     *             if the given input ring contains other segment types than {@link LineStringSegment}, {@link Arc} and
     *             {@link Circle}
     */
    private LineString getJTSLineString( Curve curve ) {

        Curve linearizedCurve = linearizer.linearize( curve, crit );
        List<Coordinate> coordinates = new LinkedList<Coordinate>();
        for ( CurveSegment segment : linearizedCurve.getCurveSegments() ) {
            for ( Point point : ( (LineStringSegment) segment ).getControlPoints() ) {
                coordinates.add( new Coordinate( point.get0(), point.get1() ) );
            }
        }
        return jtsFactory.createLineString( coordinates.toArray( new Coordinate[coordinates.size()] ) );
    }

    /**
     * Returns a JTS geometry for the given {@link Ring} (which is linearized first).
     *
     * @param ring
     *            {@link Ring} that consists of {@link LineStringSegment}, {@link Arc} and {@link Circle} segments only
     * @return linear JTS ring geometry, null if no
     * @throws IllegalArgumentException
     *             if the given input ring contains other segment types than {@link LineStringSegment}, {@link Arc} and
     *             {@link Circle}
     */
    private LinearRing getJTSRing( Ring ring ) {

        Ring linearizedRing = (Ring) linearizer.linearize( ring, crit );
        List<Coordinate> coordinates = new LinkedList<Coordinate>();
        for ( Curve member : linearizedRing.getMembers() ) {
            for ( CurveSegment segment : member.getCurveSegments() ) {
                for ( Point point : ( (LineStringSegment) segment ).getControlPoints() ) {
                    coordinates.add( new Coordinate( point.get0(), point.get1() ) );
                }
            }
        }
        return jtsFactory.createLinearRing( coordinates.toArray( new Coordinate[coordinates.size()] ) );
    }

    private GeometricPrimitive inspect( GeometricPrimitive geom )
                    throws GeometryInspectionException {

        GeometricPrimitive inspected = geom;
        switch ( geom.getPrimitiveType() ) {
        case Point: {
            Point point = (Point) inspected;
            if ( point.getCoordinateDimension() != 2 ) {
                String msg = createMessage( String.format( "Punkt (=%s) mit ung\u00fcltiger Dimensionalit\u00e4t (%d). Nur 2D Koordinaten sind erlaubt.",
                                                           geom, point.getCoordinateDimension() ) );
                createError( msg );
            }
            break;
        }
        case Curve: {
            inspected = inspect( (Curve) inspected );
            break;
        }
        case Surface: {
            inspected = inspect( (Surface) inspected );
            break;
        }
        case Solid: {
            String msg = createMessage( "Solids d\u00fcrfen gem\u00e4\u00df XPlanGML-Schema nicht auftreten." );
            createError( msg );
        }
        default:
            // nothing to do (unknown geometry)
        }
        return inspected;
    }

    private Curve inspect( Curve geom )
                    throws GeometryInspectionException {

        Curve inspected = checkSegmentContinuity( geom );
        switch ( inspected.getCurveType() ) {
        case Curve:
        case LineString: {
            break;
        }
        case Ring: {
            inspected = checkClosed( (Ring) inspected );
            checkSelfIntersection( (Ring) inspected );
            break;
        }
        case OrientableCurve:
        case CompositeCurve: {
            String msg = createMessage( inspected.getCurveType().name()
                                        + " darf gem\u00e4\u00df XPlanGML-Schema nicht auftreten." );
            createError( msg );
            break;
        }
        default:
            // nothing to do (unknown geometry)
        }
        return inspected;
    }

    private Surface inspect( Surface geom )
                    throws GeometryInspectionException {

        Surface inspected = geom;
        switch ( geom.getSurfaceType() ) {
        case Surface: {
            // nothing to do (all patches have been checked already)
            inspected = geom;
            break;
        }
        case Polygon: {
            PolygonPatch patch = inspect( ( (org.deegree.geometry.primitive.Polygon) geom ).getPatches().get( 0 ) );
            if ( patch != ( (org.deegree.geometry.primitive.Polygon) geom ).getPatches().get( 0 ) ) {
                inspected = new DefaultPolygon( geom.getId(), geom.getCoordinateSystem(), geom.getPrecision(),
                                patch.getExteriorRing(), patch.getInteriorRings() );
            } else {
                inspected = geom;
            }
            break;
        }
        case CompositeSurface:
        case OrientableSurface:
        case PolyhedralSurface:
        case Tin:
        case TriangulatedSurface: {
            String msg = createMessage( inspected.getSurfaceType().name()
                                        + " darf gem\u00e4\u00df XPlanGML-Schema nicht auftreten." );
            createError( msg );
            break;
        }
        default:
            // nothing to do (unknown geometry)
        }
        return inspected;
    }

    private PolygonPatch inspect( PolygonPatch patch ) {
        PolygonPatch inspected = checkRingOrientations( patch );
        checkSelfIntersection( inspected );
        return inspected;
    }

    private void addLastBadGeometry( Geometry inspected ) {
        if ( lastBadGeometry != null ) {
            lastBadGeometry.setOriginalGeometry( inspected );
            badGeometries.add( lastBadGeometry );
            lastBadGeometry = null;
        }
    }
}
