package de.latlon.xplan.commons.util;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.synthesizer.Features.getPropertyStringValue;
import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValue;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.deegree.commons.tom.ElementNode;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.xml.stax.XMLStreamReaderWrapper;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.feature.types.AppSchema;
import org.deegree.geometry.GeometryFactory;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.deegree.gml.GMLVersion;

import java.util.UUID;

/**
 * Contains utilities for deegree {@link org.deegree.feature.FeatureCollection}s.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class FeatureCollectionUtils {

    private FeatureCollectionUtils() {
    }

    /**
     * Parses a FeatureCollection from the passed stream
     *
     * @param plan
     *                 as XMLStreamReader, never <code>null</code>
     * @param version
     *                 of the plan, never <code>null</code>
     * @param appSchema
     *                 of the plan, never <code>null</code>
     * @return never <code>null</code>
     * @throws XMLStreamException
     *                 if the plan could not be read
     * @throws UnknownCRSException
     *                 if the CRS of a geometry in the plan is not known
     */
    public static FeatureCollection parseFeatureCollection( XMLStreamReader plan, XPlanVersion version,
                                                            AppSchema appSchema )
                    throws XMLStreamException, UnknownCRSException {
        XMLStreamReaderWrapper xmlStream = new XMLStreamReaderWrapper( plan, null );
        GMLVersion gmlVersion = version.getGmlVersion();
        GMLStreamReader gmlStream = GMLInputFactory.createGMLStreamReader( gmlVersion, xmlStream );
        gmlStream.setGeometryFactory( new GeometryFactory() );
        gmlStream.setApplicationSchema( appSchema );
        return (FeatureCollection) gmlStream.readFeature( true );
    }

    /**
     * Finds the XP_Plan-Feature of a XPlan-FeatureCollection.
     * 
     * @param fc
     *            XPlan-FeatureCollection, never <code>null</code>
     * @param type
     *            XPlan-Type, never <code>null</code>
     * @return XP_Plan-Feature
     */
    public static Feature findPlanFeature( FeatureCollection fc, XPlanType type ) {
        for ( Feature feature : fc ) {
            QName featureName = feature.getName();
            if ( featureName.getLocalPart().equals( type.name() ) ) {
                return feature;
            }
        }
        throw new IllegalArgumentException( "Keine XPlan-FeatureCollection. Kein XP_Plan-Feature enthalten." );
    }

    /**
     * Retrieves the legislation status ("rechtsstand") of a XPlan-FeatureCollection.
     * 
     * @param fc
     *            XPlan-FeatureCollection, never <code>null</code>
     * @param type
     *            XPlan-Type, never <code>null</code>
     * @return legislation status value or <code>null</code> if no value was found
     */
    public static String retrieveLegislationStatus( FeatureCollection fc, XPlanType type ) {
        return retrievePlanProperty( fc, type, "rechtsstand" );
    }

    /**
     * Retrieves the additional type ("sonstPlanArt") of a XPlan-FeatureCollection.
     * 
     * @param fc
     *            XPlan-FeatureCollection, never <code>null</code>
     * @param type
     *            XPlan-Type, never <code>null</code>
     * @return additional type value or <code>null</code> if no value was found
     */
    public static String retrieveAdditionalType( FeatureCollection fc, XPlanType type ) {
        return retrievePlanProperty( fc, type, "sonstPlanArt" );
    }

    /**
     * Retrieves the district (XPlan3: "ortsteil"; XPlan40 and XPlan41: "ortsteilName") of a XPlan-FeatureCollection.
     * XPlan3, XPlan40 and XPlan41 plans are considered. In other cases <code>null</code> ist returned.
     * 
     * @param fc
     *            XPlan-FeatureCollection, never <code>null</code>
     * @param type
     *            XPlan-Type, never <code>null</code>
     * @param version
     *            XPlan-Version, never <code>null</code>
     * @return district value or <code>null</code> if no value was found
     */
    public static String retrieveDistrict( FeatureCollection fc, XPlanType type, XPlanVersion version ) {
        if ( version == XPLAN_3 ) {
            return retrieveXPlan3District( fc, type );
        }
        return retrieveXPlan4District( fc, type );
    }

    /**
     * Retrieves the value of XX_Plan/beschreibung of the {@link FeatureCollection}.
     *
     * @param fc
     *                 XPlan-FeatureCollection, never <code>null</code>
     * @param type
     *                 XPlan-Type, never <code>null</code>
     * @return additional type value or <code>null</code> if no value was found
     */
    public static String retrieveDescription( FeatureCollection fc, XPlanType type ) {
        return retrievePlanProperty( fc, type, "beschreibung" );
    }

    /**
     * Retrieves the value of XX_Plan/name of the {@link FeatureCollection}.
     *
     * @param fc
     *                 XPlan-FeatureCollection, never <code>null</code>
     * @param type
     *                 XPlan-Type, never <code>null</code>
     * @return name of the plan, never <code>null</code> (a new name is created)
     */
    public static String retrievePlanName( FeatureCollection fc, XPlanType type ) {
        Feature planFeature = findPlanFeature( fc, type );
        return retrievePlanName( planFeature );
    }

    /**
     * Retrieves the value of the attribute name of the passed {@link Feature}.
     *
     * @param planFeature
     *                 never <code>null</code>
     * @return name of the plan, never <code>null</code> (a new name is created)
     */
    public static String retrievePlanName( Feature planFeature ) {
        String ns = planFeature.getName().getNamespaceURI();
        String name = getPropertyStringValue( planFeature, new QName( ns, "name" ) );
        if ( name == null || name.isEmpty() ) {
            name = "Unbenannter XPlan (" + UUID.randomUUID().toString() + ")";
        }
        return name;
    }

    private static String retrieveXPlan3District( FeatureCollection fc, XPlanType type ) {
        return retrievePlanProperty( fc, type, "ortsteil" );
    }

    private static String retrieveXPlan4District( FeatureCollection fc, XPlanType type ) {
        Feature planFeature = findPlanFeature( fc, type );
        String ns = planFeature.getName().getNamespaceURI();
        TypedObjectNode municipality = getPropertyValue( planFeature, new QName( ns, "gemeinde" ) );
        if ( municipality != null && municipality instanceof ElementNode ) {
            return scanMunicipalityChildren( ns, (ElementNode) municipality );
        }
        return null;
    }

    private static String scanMunicipalityChildren( String ns, ElementNode municipality ) {
        String district = null;
        for ( TypedObjectNode municipalityChild : municipality.getChildren() ) {
            if ( municipalityChild instanceof ElementNode ) {
                ElementNode municipalityElement = (ElementNode) municipalityChild;
                if ( new QName( ns, "ortsteilName" ).equals( municipalityElement.getName() ) ) {
                    district = retrieveDistrictName( municipalityElement );
                }
            }
        }
        return district;
    }

    private static String retrieveDistrictName( ElementNode municipalityElement ) {
        TypedObjectNode districtName = municipalityElement.getChildren().get( 0 );
        if ( districtName instanceof PrimitiveValue ) {
            PrimitiveValue value = (PrimitiveValue) districtName;
            String district = value.getAsText();
            if ( district.contains( "," ) ) {
                String[] split = district.split( "," );
                return split[0].trim();
            }
            return district;
        }
        return null;
    }

    private static String retrievePlanProperty( FeatureCollection fc, XPlanType type, String propertyName ) {
        Feature planFeature = findPlanFeature( fc, type );
        String ns = planFeature.getName().getNamespaceURI();
        return getPropertyStringValue( planFeature, new QName( ns, propertyName ) );
    }

}
