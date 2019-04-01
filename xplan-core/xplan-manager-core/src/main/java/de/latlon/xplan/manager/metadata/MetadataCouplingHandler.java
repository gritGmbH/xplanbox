package de.latlon.xplan.manager.metadata;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import org.deegree.geometry.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

import static java.nio.file.Files.readAllBytes;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MetadataCouplingHandler {

    private static final Logger LOG = LoggerFactory.getLogger( MetadataCouplingHandler.class );

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd" );

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );

    private static final SimpleDateFormat DATE_TIME_FILE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd_HH-mm" );

    private static final DecimalFormat COORD_FORMAT = new DecimalFormat( "0.000000" );

    private final CswClient cswClient;

    private final ServiceMetadataDocumentWriter serviceMetadataDocumentWriter;

    private final Path directoryToStoreDatasetMetadata;

    public MetadataCouplingHandler( CoupledResourceConfiguration config )
                    throws IOException {
        this.cswClient = new CswClient( config.getCswUrlProvidingDatasetMetadata(),
                                        config.getMetadataResourceTemplate() );
        Path configurationDirectory = config.getMetadataConfigDirectory();
        Path serviceTemplateDocument = configurationDirectory.resolve( "service-iso-metadata-template.xml" );
        if ( !Files.exists( serviceTemplateDocument ) )
            throw new IOException(
                            "Template for service metadata does not exist. Expected: " + serviceTemplateDocument );

        byte[] template = readAllBytes( serviceTemplateDocument );
        this.serviceMetadataDocumentWriter = new ServiceMetadataDocumentWriter( template );
        this.directoryToStoreDatasetMetadata = config.getDirectoryToStoreDatasetMetadata();
    }

    /**
     * Processes the metadata coupling:
     * <ol>
     * <li>Requests the metadata record describing the passed plan</li>
     * <li>Creates a new service metadata record describing the Planwerk WMS of the passed plan</li>
     * </ol>
     *
     * @param xPlanFeatureCollection
     *                 describing the plan, never <code>null</code>
     * @throws DataServiceCouplingException
     */
    public void processMetadataCoupling( XPlanFeatureCollection xPlanFeatureCollection )
                    throws DataServiceCouplingException {
        LocalDateTime now = LocalDateTime.now();
        CoupledResource coupledResource = this.cswClient.requestMetadataRecord();
        Properties properties = createProperties( xPlanFeatureCollection, coupledResource, now );
        createServiceMetadataDocument( xPlanFeatureCollection.getPlanName(), now, properties );
    }

    private void createServiceMetadataDocument( String planName, LocalDateTime now, Properties properties )
                    throws DataServiceCouplingException {
        OutputStream outputStream = null;
        try {
            String fileName = planName + "_" + DATE_TIME_FILE_FORMAT.format( now ) + ".xml";
            Path target = directoryToStoreDatasetMetadata.resolve( fileName );
            LOG.info( "Write Planwerk WMS service document to {}", target );
            outputStream = Files.newOutputStream( target );
            serviceMetadataDocumentWriter.writeServiceMetadataDocument( properties, outputStream );
        } catch ( IOException e ) {
            LOG.error( "Could not create output dule", e );
            throw new DataServiceCouplingException( e );
        } catch ( XMLStreamException e ) {
            LOG.error( "Could not create service metadata document", e );
            throw new DataServiceCouplingException( e );
        } finally {
            if ( outputStream != null ) {
                try {
                    outputStream.close();
                } catch ( IOException e ) {
                }
            }
        }
    }

    private Properties createProperties( XPlanFeatureCollection xPlanFeatureCollection, CoupledResource coupledResource,
                                         LocalDateTime now ) {
        Properties properties = new Properties();
        properties.setProperty( "METADATA_ID", UUID.randomUUID().toString() );
        properties.setProperty( "CURRENT_DATE", DATE_FORMAT.format( now ) );
        //properties.setProperty( "TITLE", xPlanFeatureCollection.getName() );
        properties.setProperty( "CURRENT_DATE_TIME", DATE_TIME_FORMAT.format( now ) );
        //properties.setProperty( "ABSTRACT", planwerkServiceMetadata.getDescription() );
        //properties.setProperty( "PLANWERKWMS_OVERVIEW", planwerkServiceMetadata.getPlanwerkWmsGetMapUrl() );
        Envelope envelope = xPlanFeatureCollection.getBboxIn4326();
        properties.setProperty( "WEST_BOUND_LONG", COORD_FORMAT.format( envelope.getMin().get0() ) );
        properties.setProperty( "EAST_BOUND_LONG", COORD_FORMAT.format( envelope.getMax().get0() ) );
        properties.setProperty( "SOUTH_BOUND_LAT", COORD_FORMAT.format( envelope.getMin().get1() ) );
        properties.setProperty( "NORTH_BOUND_LAT", COORD_FORMAT.format( envelope.getMax().get1() ) );
        properties.setProperty( "COUPLED_METADATA_RESOURCE_URL", coupledResource.getUrl() );
        properties.setProperty( "COUPLED_METADATA_RESOURCE_IDENTIFIER", coupledResource.getId() );
        //properties.setProperty( "PLANWERKWMS_CAPABILITIES", planwerkServiceMetadata.getPlanwerkWmsGetCapabilitiesUrl() );
        //properties.setProperty( "PLANWERKWMS_NAME", planwerkServiceMetadata.getPlanwerkWmsName() );
        return properties;
    }

}