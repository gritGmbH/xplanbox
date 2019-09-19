package de.latlon.xplan.manager.planwerkwms;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.GeometryTransformer;
import org.deegree.geometry.SimpleGeometryFactory;
import org.deegree.geometry.primitive.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Encapsulates metadata describing the Planwerk WMS of a plan.
 */
public class PlanwerkServiceMetadataBuilder {

    private static final Logger LOG = LoggerFactory.getLogger( PlanwerkServiceMetadataBuilder.class );

    private static final SimpleGeometryFactory GEOMETRY_FACTORY = new SimpleGeometryFactory();

    private final XPlanType planType;

    private final String title;

    private final String normalizedPlanName;

    private final String description;

    private final Envelope envelope;

    private final CoupledResourceConfiguration coupledResourceConfiguration;

    public PlanwerkServiceMetadataBuilder( XPlanType planType, String planName, String description, Envelope envelope,
                                           CoupledResourceConfiguration coupledResourceConfiguration ) {
        this.planType = planType;
        this.title = planName;
        this.normalizedPlanName = normalizedPlanName( planName );
        this.description = description;
        this.envelope = envelope;
        this.coupledResourceConfiguration = coupledResourceConfiguration;
    }

    public PlanwerkServiceMetadata build( ICRS crs ) {
        String planWerkWmsBaseUrl = coupledResourceConfiguration.getPlanWerkWmsBaseUrl();
        String planwerkWmsGetCapabilitiesUrl = createPlanWerkWmsGetCapabilitiesUrl( planWerkWmsBaseUrl );
        String planwerkWmsGetMapUrl = createPlanWerkWmsGetMapUrl( planWerkWmsBaseUrl, crs );
        return new PlanwerkServiceMetadata( title, description, envelope, planwerkWmsGetCapabilitiesUrl,
                                            planwerkWmsGetMapUrl );
    }

    private String createPlanWerkWmsGetCapabilitiesUrl( String planWerkWmsBaseUrl ) {
        StringBuilder url = new StringBuilder();
        url.append( planWerkWmsBaseUrl );
        if ( !planWerkWmsBaseUrl.endsWith( "/" ) )
            url.append( "/" );
        url.append( "services/planwerkwms/planname/" );
        url.append( normalizedPlanName );
        url.append( "?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities" );
        return url.toString();
    }

    private String createPlanWerkWmsGetMapUrl( String planWerkWmsBaseUrl, ICRS crs ) {
        StringBuilder url = new StringBuilder();
        url.append( planWerkWmsBaseUrl );
        if ( !planWerkWmsBaseUrl.endsWith( "/" ) )
            url.append( "/" );
        url.append( "services/planwerkwms/planname/" );
        url.append( normalizedPlanName );
        url.append( "?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetMap" );
        url.append( "&LAYERS=" ).append( coupledResourceConfiguration.getLayerByType( planType ) );
        url.append( "&STYLES=" ).append( coupledResourceConfiguration.getStyleByType( planType ) );
        url.append( "&FORMAT=image/png&TRANSPARENT=true&EXCEPTIONS=application/vnd.ogc.se_inimage" );

        Envelope transformedEnvelope = transformAndResizeEnvelop( coupledResourceConfiguration, envelope, crs );
        url.append( "&CRS=" ).append( transformedEnvelope.getCoordinateSystem().getAlias() );
        url.append( "&BBOX=" ).append( transformedEnvelope.getMin().get0() ).append( "," ).append(
                        transformedEnvelope.getMin().get1() ).append( "," ).append(
                        transformedEnvelope.getMax().get0() ).append( "," ).append(
                        transformedEnvelope.getMax().get1() );

        url.append( "&WIDTH=" ).append( coupledResourceConfiguration.getPlanWerkWmsGetMapWidth() );
        url.append( "&HEIGHT=" ).append( coupledResourceConfiguration.getPlanWerkWmsGetMapHeight() );
        return url.toString();
    }

    private Envelope transformAndResizeEnvelop( CoupledResourceConfiguration configuration, Envelope envelope,
                                                ICRS crs ) {
        LOG.debug( "Transform envelope {} to CRS {} and fit to map width/height", envelope, crs );
        int width = configuration.getPlanWerkWmsGetMapWidth();
        int height = configuration.getPlanWerkWmsGetMapHeight();

        double mapRatio = width / height;

        GeometryTransformer transformer = new GeometryTransformer( crs );
        try {
            envelope = transformer.transform( envelope );
        } catch ( UnknownCRSException | TransformationException e ) {
            LOG.warn( "Could not transform bbox to " + crs, e );
        }

        double envelopeWidth = envelope.getSpan0();
        double envelopeHeight = envelope.getSpan1();

        double envelopeRatio = envelopeWidth / envelopeHeight;

        if ( mapRatio > envelopeRatio ) {
            Point centroid = envelope.getCentroid();
            double newEnvelopeWidth = ( mapRatio / envelopeRatio ) * envelopeWidth;
            double minX = centroid.get0() - ( newEnvelopeWidth / 2 );
            double maxX = centroid.get0() + ( newEnvelopeWidth / 2 );

            double minY = envelope.getMin().get1();
            double maxY = envelope.getMax().get1();
            envelope = GEOMETRY_FACTORY.createEnvelope( minX, minY, maxX, maxY, envelope.getCoordinateSystem() );
        } else if ( mapRatio < envelopeRatio ) {
            double minX = envelope.getMin().get0();
            double maxX = envelope.getMax().get0();
            double newEnvelopeHeight = ( envelopeRatio / mapRatio ) * envelopeHeight;
            Point centroid = envelope.getCentroid();
            double minY = centroid.get1() - ( newEnvelopeHeight / 2 );
            double maxY = centroid.get1() + ( newEnvelopeHeight / 2 );
            envelope = GEOMETRY_FACTORY.createEnvelope( minX, minY, maxX, maxY, envelope.getCoordinateSystem() );
        }
        return envelope;
    }

    private String normalizedPlanName( String planName ) {
        if ( !planName.matches( "^[a-zA-Z0-9\\-_]*$" ) ) {
            LOG.info( "Remove other characters than a-z, A-Z, -, _ from the plan name" );
            return planName.replaceAll( "[^a-zA-Z0-9\\-_]", "" );
        }
        return planName;
    }

}