package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import org.deegree.commons.tom.TypedObjectNode;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.commons.xml.NamespaceBindings;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.xpath.TypedObjectNodeXPathEvaluator;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.expression.ValueReference;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.GeometryTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static de.latlon.xplan.commons.synthesizer.Features.getPropertyStringValue;
import static de.latlon.xplan.commons.synthesizer.Features.getPropertyValues;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.findPlanFeature;
import static org.deegree.cs.CRSUtils.EPSG_4326;

/**
 * Provides convenient access to the information contained in the main document of an {@link XPlanArchive}.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanFeatureCollection {

    public static final String BP_RELEASE_DATE_PROP_NAME = "inkrafttretensDatum";

    public static final String FP_RELEASE_DATE_PROP_NAME = "wirksamkeitsDatum";

    public static final String LP_RELEASE_DATE_PROP_NAME = "inkrafttretenDatum";

    public static final String RP_RELEASE_DATE_PROP_NAME = "datumDesInkrafttretens";

    private final Logger log = LoggerFactory.getLogger( XPlanFeatureCollection.class );

    private final FeatureCollection fc;

    private final XPlanType type;

    private final String name;

    private final String nummer;

    private final String gkz;

    private final Date planReleaseDate;

    private final ExternalReferenceInfo externalRefInfo;

    private final Envelope bboxIn4326;

    private XPlanVersion version;

    private XPlanAde ade;

    public XPlanFeatureCollection( FeatureCollection fc, XPlanVersion version, XPlanType type, XPlanAde ade ) {
        this( fc, version, type, ade, new ExternalReferenceScanner().scan( fc ) );
    }

    public XPlanFeatureCollection( FeatureCollection fc, XPlanVersion version, XPlanType type, XPlanAde ade,
                                   ExternalReferenceInfo externalRefInfo ) {
        this.fc = fc;
        this.version = version;
        this.type = type;
        this.ade = ade;
        this.externalRefInfo = externalRefInfo;
        Feature planFeature = findPlanFeature( fc, type );
        this.name = parsePlanName( planFeature );
        this.nummer = parsePlanNummer( planFeature );
        this.gkz = parsePlanGemeindeKennzahl( planFeature );
        this.planReleaseDate = parsePlanReleaseDate( type, planFeature );
        this.bboxIn4326 = createBboxIn4326( fc );
    }

    public XPlanType getType() {
        return type;
    }

    public XPlanVersion getVersion() {
        return version;
    }

    public XPlanAde getAde() {
        return ade;
    }

    public String getPlanName() {
        return name;
    }

    public String getPlanNummer() {
        return nummer;
    }

    public String getPlanGkz() {
        return gkz;
    }

    public boolean getHasRaster() {
        return !externalRefInfo.getRasterPlanBaseScans().isEmpty();
    }

    public Date getPlanReleaseDate() {
        return planReleaseDate;
    }

    public FeatureCollection getFeatures() {
        return fc;
    }

    public ExternalReferenceInfo getExternalReferenceInfo() {
        return externalRefInfo;
    }

    /**
     * Returns BBOX of feature collection in EPSG:4326.
     *
     * @return BBOX in EPSG:4326, may be <code>null</code> if the feature collection does not contain any geometry
     * properties/envelope informations or the bounding box could not be transformed to EPSG:4326
     */
    public Envelope getBboxIn4326() {
        return bboxIn4326;
    }

    private String parsePlanName( Feature planFeature ) {
        String ns = planFeature.getName().getNamespaceURI();
        String name = getPropertyStringValue( planFeature, new QName( ns, "name" ) );
        if ( name == null || name.isEmpty() ) {
            name = "Unbenannter XPlan (" + UUID.randomUUID().toString() + ")";
        }
        return name;
    }

    private String parsePlanNummer( Feature planFeature ) {
        String ns = planFeature.getName().getNamespaceURI();
        return getPropertyStringValue( planFeature, new QName( ns, "nummer" ) );
    }

    private String parsePlanGemeindeKennzahl( Feature planFeature ) {
        String ns = planFeature.getName().getNamespaceURI();
        List<TypedObjectNode> gkzValues = getPropertyValues( planFeature, new QName( ns, "gkz" ) );
        if ( gkzValues.isEmpty() ) {
            TypedObjectNodeXPathEvaluator evaluator = new TypedObjectNodeXPathEvaluator();
            NamespaceBindings nsBindings = new NamespaceBindings();
            nsBindings.addNamespace( "xplan", ns );
            ValueReference propName = new ValueReference( "xplan:gemeinde/xplan:XP_Gemeinde/xplan:ags", nsBindings );
            try {
                TypedObjectNode[] nodes = evaluator.eval( planFeature, propName );
                gkzValues = Arrays.asList( nodes );
            } catch ( FilterEvaluationException e ) {
                throw new IllegalArgumentException( e.getMessage() );
            }
        }
        String gkz = "";
        if ( !gkzValues.isEmpty() ) {
            gkz = "" + gkzValues.get( 0 );
            for ( int i = 1; i < gkzValues.size(); i++ ) {
                gkz += ";" + gkzValues.get( i );
            }
        }
        return gkz;
    }

    private Date parsePlanReleaseDate( XPlanType type, Feature planFeature ) {
        String propName = detectReleaseDatePropertyName( type );
        if ( propName != null ) {
            QName releaseDatePropName = new QName( planFeature.getName().getNamespaceURI(), propName );
            List<Property> releaseDateProps = planFeature.getProperties( releaseDatePropName );
            if ( !releaseDateProps.isEmpty() ) {
                Property releaseDateProp = releaseDateProps.get( 0 );
                PrimitiveValue value = (PrimitiveValue) releaseDateProp.getValue();
                if ( value != null ) {
                    org.deegree.commons.tom.datetime.Date dateValue = (org.deegree.commons.tom.datetime.Date) value.getValue();
                    return dateValue.getDate();
                }
            }
        }
        return null;
    }

    private Envelope createBboxIn4326( FeatureCollection fc ) {
        try {
            Envelope envelope = fc.getEnvelope();
            if ( envelope != null ) {
                GeometryTransformer geometryTransformer = new GeometryTransformer( EPSG_4326 );
                return geometryTransformer.transform( envelope );
            }
        } catch ( IllegalArgumentException | UnknownCRSException | TransformationException e ) {
            log.error( "Could not create transformed envelope! Reason: " + e.getMessage() );
        }
        return null;
    }

    private String detectReleaseDatePropertyName( XPlanType type ) {
        switch ( type ) {
        case BP_Plan:
            return BP_RELEASE_DATE_PROP_NAME;
        case FP_Plan:
            return FP_RELEASE_DATE_PROP_NAME;
        case LP_Plan:
            return LP_RELEASE_DATE_PROP_NAME;
        case RP_Plan:
            return RP_RELEASE_DATE_PROP_NAME;
        default:
            return null;
        }
    }

}