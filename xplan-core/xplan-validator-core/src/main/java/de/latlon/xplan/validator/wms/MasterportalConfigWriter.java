package de.latlon.xplan.validator.wms;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.nio.file.Files.readAllBytes;
import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MasterportalConfigWriter {

    private static final String CONFIG_TEMPLATE = "config.template.json";

    private static final String SERVICE_TEMPLATE = "service.template.json";

    private static final String SERVICES_TEMPLATE = "services-internet.template.json";

    private static final String SERVICES_JSON = "services-internet.json";

    private static final String CONFIG_FILENAME_TEMPLATE = "config.%s.json";

    private final String configTemplate;

    private final String serviceTemplate;

    private final String servicesTemplate;

    private Path configDirectory;

    private final Map<String, String> idToServiceConfig = new HashMap<>();

    public MasterportalConfigWriter()
                            throws ValidatorWmsException {
        Path masterportalDirectory = getMasterportalDirectory();
        this.configDirectory = getConfigDirectory( masterportalDirectory );
        Path templateDirectory = getTemplateDirectory( masterportalDirectory );
        this.configTemplate = readTemplate( templateDirectory, CONFIG_TEMPLATE );
        this.serviceTemplate = readTemplate( templateDirectory, SERVICE_TEMPLATE );
        this.servicesTemplate = readTemplate( templateDirectory, SERVICES_TEMPLATE );
    }

    public void createMasterportalConfig( String id )
                            throws ValidatorWmsException {
        createConfigJson( id );
        addToServicesJson( id );

    }

    private void createConfigJson( String id )
                            throws ValidatorWmsException {
        Path configFile = configDirectory.resolve( String.format( CONFIG_FILENAME_TEMPLATE, id ) );
        OutputStream out = null;
        try {
            Files.createFile( configFile );
            out = Files.newOutputStream( configFile );
            Files.write( configFile, configTemplate.replace( "${PLANID}", id ).getBytes() );
        } catch ( Exception e ) {
            throw new ValidatorWmsException( "Could not write config file " + configFile, e );
        } finally {
            closeQuietly( out );
        }
    }

    private void addToServicesJson( String id )
                            throws ValidatorWmsException {
        Path servicesJson = configDirectory.resolve( SERVICES_JSON );
        if ( !Files.exists( servicesJson ) )
            throw new ValidatorWmsException( "File " + servicesJson + " does not exist" );

        try {
            String serviceConfig = createServiceConfigFromTemplate( id );
            idToServiceConfig.put( id, serviceConfig );

            String servicesConfig = createServicesConfigFromTemplate();
            /// TODO: must be thread safe!
            Files.write( servicesJson, servicesConfig.getBytes() );
        } catch ( IOException e ) {
            throw new ValidatorWmsException( "Could not add service config", e );
        }
    }

    private String createServiceConfigFromTemplate( String id ) {
        return serviceTemplate.replace( "${PLANID}", id ).replace( "${WMSURL}",
                                                                   "https://xplanbox2019.lat-lon.de/xplan-wms/services/wms" ).replace(
                                "${MANAGERID}", "2" ).replace( "${LAYERS}", "BP_Planvektor" );
    }

    private String createServicesConfigFromTemplate() {
        String allServiceConfigs = idToServiceConfig.values().stream().collect( Collectors.joining( "," ) );
        System.out.println( allServiceConfigs );
        return servicesTemplate.replace( "${SERVICESCONFIG}", allServiceConfigs );
    }

    private Path getMasterportalDirectory()
                            throws ValidatorWmsException {
        try {
            URL masterportal = MasterportalConfigWriter.class.getResource( "../../../../../../../masterportal" );
            if ( masterportal == null )
                throw new ValidatorWmsException( "masterportal directory does not exist" );
            Path path = configDirectory = Paths.get( masterportal.toURI() );
            if ( !Files.exists( path ) )
                throw new ValidatorWmsException( "masterportal directory does not exist" );
            return path;
        } catch ( URISyntaxException e ) {
            throw new ValidatorWmsException( "Config directory in masterportal is not available" );
        }
    }

    private Path getConfigDirectory( Path masterportalDirectory )
                            throws ValidatorWmsException {
        Path path = masterportalDirectory.resolve( "config" );
        if ( !Files.exists( path ) )
            throw new ValidatorWmsException( "Config directory in masterportal does not exist" );
        return path;
    }

    private Path getTemplateDirectory( Path masterportalDirectory )
                            throws ValidatorWmsException {
        Path path = masterportalDirectory.resolve( "template" );
        if ( !Files.exists( path ) )
            throw new ValidatorWmsException( "Config directory in masterportal does not exist" );
        return path;
    }

    private String readTemplate( Path templateirectory, String fileName )
                            throws ValidatorWmsException {
        Path template = templateirectory.resolve( fileName );
        if ( template == null )
            throw new ValidatorWmsException( "Template " + fileName + " does not exist." );
        try {
            byte[] bytes = readAllBytes( template );
            return new String( bytes );
        } catch ( IOException e ) {
            throw new ValidatorWmsException( "Template " + fileName + " could not be read", e );
        }
    }

}