package de.latlon.xplan.manager.metadata;

import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import org.deegree.geometry.Envelope;

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

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat( "yyyy-MM-dd" );

    private static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );

    private static final DecimalFormat COORD_FORMAT = new DecimalFormat( "0.000000" );

    private final ManagerConfiguration managerConfiguration;

    private final ServiceMetadataDocumentWriter serviceMetadataDocumentWriter;

    public MetadataCouplingHandler( ManagerConfiguration managerConfiguration )
                    throws IOException {
        this.managerConfiguration = managerConfiguration;
        Path configurationDirectory = managerConfiguration.getConfigurationDirectory();
        Path serviceTemplateDocument = configurationDirectory.resolve( "metadata/service-iso-metadata-template.xml" );
        if ( !Files.exists( serviceTemplateDocument ) )
            throw new IOException(
                            "Template for service metadata does not exist. Expected: " + serviceTemplateDocument );

        byte[] template = readAllBytes( serviceTemplateDocument );
        serviceMetadataDocumentWriter = new ServiceMetadataDocumentWriter( template );
    }

    public void createServiceMetadataDocument( PlanwerkServiceMetadata planwerkServiceMetadata )
                    throws IOException, XMLStreamException {
        Properties properties = createProperties( planwerkServiceMetadata );

        Path target = Files.createTempFile( "isoMd_", ".xml" );
        OutputStream outputStream = Files.newOutputStream( target );
        serviceMetadataDocumentWriter.writeServiceMetadataDocument( properties, outputStream );
    }

    private Properties createProperties( PlanwerkServiceMetadata planwerkServiceMetadata ) {
        LocalDateTime now = LocalDateTime.now();
        Properties properties = new Properties();
        properties.setProperty( "METADATA_ID", UUID.randomUUID().toString() );
        properties.setProperty( "CURRENT_DATE", DATE_FORMAT.format( now ) );
        properties.setProperty( "TITLE", planwerkServiceMetadata.getTitle() );
        properties.setProperty( "CURRENT_DATE_TIME", DATE_TIME_FORMAT.format( now ) );
        properties.setProperty( "ABSTRACT", planwerkServiceMetadata.getDescription() );
        properties.setProperty( "PLANWERKWMS_OVERVIEW", planwerkServiceMetadata.getPlanwerkWmsGetMapUrl() );
        properties.setProperty( "DISTRICT", planwerkServiceMetadata.getDistrict() );
        Envelope envelope = planwerkServiceMetadata.getEnvelope();
        properties.setProperty( "WEST_BOUND_LONG", COORD_FORMAT.format( envelope.getMin().get0() ) );
        properties.setProperty( "EAST_BOUND_LONG", COORD_FORMAT.format( envelope.getMax().get0() ) );
        properties.setProperty( "SOUTH_BOUND_LAT", COORD_FORMAT.format( envelope.getMin().get1() ) );
        properties.setProperty( "NORTH_BOUND_LAT", COORD_FORMAT.format( envelope.getMax().get1() ) );
        properties.setProperty( "COUPLED_METADATA_RESOURCE_URL", planwerkServiceMetadata.getDataMetadataUrl() );
        properties.setProperty( "COUPLED_METADATA_RESOURCE_IDENTIFIER", planwerkServiceMetadata.getDataMetadataId() );
        properties.setProperty( "PLANWERKWMS_CAPABILITIES",
                                planwerkServiceMetadata.getPlanwerkWmsGetCapabilitiesUrl() );
        properties.setProperty( "PLANWERKWMS_NAME", planwerkServiceMetadata.getPlanwerkWmsName() );
        return properties;
    }

}