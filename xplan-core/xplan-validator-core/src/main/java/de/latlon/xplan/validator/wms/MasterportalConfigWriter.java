package de.latlon.xplan.validator.wms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllBytes;
import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MasterportalConfigWriter {

    private static final Logger LOG = LoggerFactory.getLogger( MasterportalConfigWriter.class );

    private static final String CONFIG_TEMPLATE = "config.template.json";

    private static final String SERVICE_TEMPLATE = "service.template.json";

    private static final String SERVICES_TEMPLATE = "services-internet.template.json";

    private static final String SERVICES_JSON = "services-internet.json";

    private static final String CONFIG_FILENAME_TEMPLATE = "config.%s.json";

    private final String configTemplate;

    private final String serviceTemplate;

    private final String servicesTemplate;

    private final String validatorWmsEndpoint;

    private final Path configDirectory;

    private final Path servicesConfigFile;

    private final ConcurrentMap<String, String> idToServiceConfig = new ConcurrentHashMap<>();

    public MasterportalConfigWriter( String validatorWmsEndpoint )
                            throws MapPreviewCreationException {
        this.validatorWmsEndpoint = validatorWmsEndpoint;
        Path masterportalDirectory = getMasterportalDirectory();
        this.configDirectory = getConfigDirectory( masterportalDirectory );
        this.servicesConfigFile = getServicesConfigFile( this.configDirectory );
        Path templateDirectory = getTemplateDirectory( masterportalDirectory );
        this.configTemplate = readTemplate( templateDirectory, CONFIG_TEMPLATE );
        this.serviceTemplate = readTemplate( templateDirectory, SERVICE_TEMPLATE );
        this.servicesTemplate = readTemplate( templateDirectory, SERVICES_TEMPLATE );
    }

    public void createMasterportalConfig( String id, int managerId )
                            throws MapPreviewCreationException {
        createConfigJson( id );
        addToServicesJson( id, managerId );

    }

    private void createConfigJson( String id )
                            throws MapPreviewCreationException {
        Path configFile = configDirectory.resolve( String.format( CONFIG_FILENAME_TEMPLATE, id ) );
        LOG.info( "Write config file {}", configFile );
        OutputStream out = null;
        try {
            Files.createFile( configFile );
            out = Files.newOutputStream( configFile );
            Files.write( configFile, configTemplate.replace( "${PLANID}", id ).getBytes() );
        } catch ( Exception e ) {
            throw new MapPreviewCreationException( "Could not write config file " + configFile, e );
        } finally {
            closeQuietly( out );
        }
    }

    private void addToServicesJson( String id, int managerId )
                            throws MapPreviewCreationException {
        try {
            String serviceConfigSection = createServiceConfigFromTemplate( id, managerId );
            idToServiceConfig.put( id, serviceConfigSection );
            String servicesConfigSection = createServicesConfigFromTemplate();
            synchronized ( servicesConfigFile  ) {
                LOG.info( "Append service config to {}", servicesConfigFile );
                Files.write( servicesConfigFile, servicesConfigSection.getBytes() );
            }
        } catch ( IOException e ) {
            throw new MapPreviewCreationException( "Could not add service config", e );
        }
    }

    private String createServiceConfigFromTemplate( String id, int managerId ) {
        return serviceTemplate.replace( "${PLANID}", id ).replace( "${WMSURL}", validatorWmsEndpoint ).replace(
                                "${MANAGERID}", Integer.toString( managerId ) ).replace( "${LAYERS}", "BP_Planvektor" );
    }

    private String createServicesConfigFromTemplate() {
        String allServiceConfigs = idToServiceConfig.values().stream().collect( Collectors.joining( "," ) );
        return servicesTemplate.replace( "${SERVICESCONFIG}", allServiceConfigs );
    }

    private Path getMasterportalDirectory()
                            throws MapPreviewCreationException {
        try {
            URL masterportal = MasterportalConfigWriter.class.getResource( "../../../../../../../masterportal" );
            if ( masterportal == null )
                throw new MapPreviewCreationException( "masterportal directory does not exist" );
            Path path = Paths.get( masterportal.toURI() );
            if ( !Files.exists( path ) )
                throw new MapPreviewCreationException( "masterportal directory does not exist" );
            return path;
        } catch ( URISyntaxException e ) {
            throw new MapPreviewCreationException( "Config directory in masterportal is not available" );
        }
    }

    private Path getConfigDirectory( Path masterportalDirectory )
                            throws MapPreviewCreationException {
        Path path = masterportalDirectory.resolve( "config" );
        if ( !Files.exists( path ) )
            throw new MapPreviewCreationException( "Config directory in masterportal does not exist" );
        return path;
    }

    private Path getServicesConfigFile( Path configDirectory )
                            throws MapPreviewCreationException {
        Path servicesConfig = configDirectory.resolve( SERVICES_JSON );
        if ( !Files.exists( servicesConfig ) )
            throw new MapPreviewCreationException( "File " + servicesConfig + " does not exist" );
        return servicesConfig;
    }

    private Path getTemplateDirectory( Path masterportalDirectory )
                            throws MapPreviewCreationException {
        Path path = masterportalDirectory.resolve( "template" );
        if ( !Files.exists( path ) )
            throw new MapPreviewCreationException( "Config directory in masterportal does not exist" );
        return path;
    }

    private String readTemplate( Path templateirectory, String fileName )
                            throws MapPreviewCreationException {
        Path template = templateirectory.resolve( fileName );
        if ( template == null )
            throw new MapPreviewCreationException( "Template " + fileName + " does not exist." );
        try {
            byte[] bytes = readAllBytes( template );
            return new String( bytes );
        } catch ( IOException e ) {
            throw new MapPreviewCreationException( "Template " + fileName + " could not be read", e );
        }
    }

}