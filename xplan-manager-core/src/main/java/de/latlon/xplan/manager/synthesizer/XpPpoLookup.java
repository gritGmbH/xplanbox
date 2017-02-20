package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.synthesizer.Features;
import de.latlon.xplan.manager.synthesizer.expression.XplanSymbolPositions;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.primitive.Point;
import org.deegree.geometry.refs.PointReference;

import javax.xml.namespace.QName;
import java.util.*;

import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValue;

/**
 * The <code>XPlanAggregatePPOLookup</code> class is auxiliary to {@link XplanSymbolPositions}. It first statically
 * registers all the points from the symbolPosition of fachobjekte in a map. Then it can statically return the set of
 * points by providing the feauture id of the fachobjekt.
 *
 * @author <a href="mailto:ionita@lat-lon.de">Andrei Ionita</a>
 * @author last edited by: $Author: schneider $
 * @version $Revision: 1028 $, $Date: 2010-02-18 15:48:22 +0100 (Do, 18 Feb 2010) $
 */
public class XpPpoLookup {

    private static final Map<String, Set<Point>> fachobjektIdToPoints = new HashMap<String, Set<Point>>();

    /**
     * Registers all the points from the symbolPosition property of fachobjekte in a map, that can later be accessed by
     * {@link #lookup}
     *
     * @param fc the FeatureCollection that might contain XP_PPO objects (containing points) with references to
     *           fachobjekte
     */
    public static void init( FeatureCollection fc ) {
        fachobjektIdToPoints.clear();
        for ( Feature feature : fc ) {
            if ( "XP_PPO".equals( feature.getName().getLocalPart() ) ) {
                QName positionName = new QName( feature.getName().getNamespaceURI(), "position" );
                Point positionProp;
                if ( getPropertyValue( feature, positionName ) instanceof PointReference ) {
                    positionProp = ( (PointReference) getPropertyValue( feature, positionName ) ).getReferencedObject();
                } else {
                    positionProp = (Point) getPropertyValue( feature, positionName );
                }

                QName dientZurPropName = new QName( feature.getName().getNamespaceURI(), "dientZurDarstellungVon" );
                List<Property> featureProps = feature.getProperties( dientZurPropName );
                if ( featureProps != null ) {
                    for ( Property prop : featureProps ) {
                        Feature refFeature = Features.getPropertyFeatureValue( prop );
                        String refFeatureId = refFeature.getId();
                        if ( refFeatureId == null ) {
                            // if the XP_Objekt feature has no id, then it will not need be referenced, hence it doesn't
                            // need any points from XP_PPO objects
                            continue;
                        }
                        Set<Point> currentPoints = fachobjektIdToPoints.get( refFeatureId );
                        if ( currentPoints == null ) {
                            currentPoints = new LinkedHashSet<Point>();
                        }
                        currentPoints.add( positionProp );
                        fachobjektIdToPoints.put( refFeatureId, currentPoints );
                    }
                }
            }
        }
    }

    /**
     * Returns all Points from the XP_PPO features that reference the fachobjekt that has the given featureId.
     *
     * @param fachobjektId
     * @return the points, or <code>null</code> if XP_PPO features reference the fachobjekt
     */
    public static Set<Point> lookup( String fachobjektId ) {
        return fachobjektIdToPoints.get( fachobjektId );
    }
}
