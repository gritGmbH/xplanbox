package de.latlon.xplan.commons.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Retrieves a configuration from a path specified with a system property or classpath.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SystemPropertyPropertiesLoader extends AbstractPropertiesLoader {

    private static final Logger LOG = LoggerFactory.getLogger( SystemPropertyPropertiesLoader.class );

    public static final String CONFIG_SYSTEM_PROPERTY = "XPLANBOX_CONFIG";

    private static final String OLD_CONFIG_SYSTEM_PROPERTY = "MANAGER_WEB";

    private final Path configurationDirectory;

    private final Class<?> defaultBaseClass;

    /**
     * Instantiates a {@link SystemPropertyPropertiesLoader} loading properties from files specified by a
     * system property.
     *
     * @param defaultBaseClass
     *                         fallback to retrieve properties file from, if system property is not available, may be
     *                         <code>null</code> (this class is used then)
     */
    public SystemPropertyPropertiesLoader( Class<?> defaultBaseClass ) {
        this.configurationDirectory = findConfigDirectory();
        if ( defaultBaseClass != null )
            this.defaultBaseClass = defaultBaseClass;
        else
            this.defaultBaseClass = this.getClass();
    }

    /**
     * Instantiates a {@link SystemPropertyPropertiesLoader} loading properties from files specified with by a
     * system property.
     */
    public SystemPropertyPropertiesLoader() {
        this.configurationDirectory = findConfigDirectory();
        this.defaultBaseClass = this.getClass();
    }

    @Override
    InputStream retrieveAsStream( String configurationFileName ) {
        if ( configurationDirectory != null ) {
            Path pathToConfigFile = configurationDirectory.resolve( configurationFileName );
            LOG.info( "Configuration {} is read from file {}", configurationFileName, pathToConfigFile );
            try {
                return Files.newInputStream( pathToConfigFile );
            } catch ( IOException e ) {
                LOG.info( "Configuration does not exist: {}", e.getMessage() );
                LOG.info( "Internal {} configuration is used.", configurationFileName );
                return defaultBaseClass.getResourceAsStream( configurationFileName );
            }
        }
        LOG.info( "Internal {} configuration is used.", configurationFileName );
        return defaultBaseClass.getResourceAsStream( configurationFileName );
    }

    @Override
    public Path getConfigDirectory() {
        return configurationDirectory;
    }

    private Path findConfigDirectory() {
        LOG.info( "Try to receive configuration set with system property {}", CONFIG_SYSTEM_PROPERTY );
        String configFilePath = System.getProperty( CONFIG_SYSTEM_PROPERTY );
        if ( configFilePath != null )
            return findConfigDirectory( configFilePath );
        LOG.info( "Fallback: Try to receive configuration set with system property {}", OLD_CONFIG_SYSTEM_PROPERTY );
        String oldConfigFilePath = System.getProperty( OLD_CONFIG_SYSTEM_PROPERTY );
        if ( oldConfigFilePath != null )
            return findConfigDirectory( oldConfigFilePath );
        return null;
    }

    private Path findConfigDirectory( String configFilePath ) {
        LOG.info( "Configuration directory is {}", configFilePath );
        if ( configFilePath != null ) {
            Path configDirectory = Paths.get( configFilePath );
            if ( Files.isDirectory( configDirectory ) && Files.exists( configDirectory ) )
                return configDirectory;
            else
                LOG.info( "Configuration directory {} does not exist or is not a directory.", configFilePath );
        }
        return null;
    }

}