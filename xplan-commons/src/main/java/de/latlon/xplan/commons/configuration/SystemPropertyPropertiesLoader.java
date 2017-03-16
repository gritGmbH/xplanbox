package de.latlon.xplan.commons.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Retrieves a configuration from a path specified with a system property or classpath.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class SystemPropertyPropertiesLoader extends AbstractPropertiesLoader {

    private static final Logger LOG = LoggerFactory.getLogger( SystemPropertyPropertiesLoader.class );

    private final Path configurationDirectory;

    private final Class<?> defaultBaseClass;

    /**
     * Instantiates a {@link SystemPropertyPropertiesLoader} loading properties from files specified with the given
     * system property.
     * 
     * @param configurationFilePathVariable
     *            the environment variable to read the config file path, may be <code>null</code> if the configuration
     *            should be retrieved from classpath
     * @param defaultBaseClass
     *            fallback to retrieve properties file from, if system property is not available, may be
     *            <code>null</code> (this class is used then)
     */
    public SystemPropertyPropertiesLoader( String configurationFilePathVariable, Class<?> defaultBaseClass ) {
        this.configurationDirectory = getConfigDirectory( configurationFilePathVariable );
        if ( defaultBaseClass != null )
            this.defaultBaseClass = defaultBaseClass;
        else
            this.defaultBaseClass = this.getClass();
    }

    /**
     * Instantiates a {@link SystemPropertyPropertiesLoader} loading properties from files specified with the given
     * system property.
     * 
     * @param configurationFilePathVariable
     *            the environment variable to read the config file path, may be <code>null</code> if the configuration
     *            should be retrieved from classpath
     */
    public SystemPropertyPropertiesLoader( String configurationFilePathVariable ) {
        this( configurationFilePathVariable, null );
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

    private Path getConfigDirectory( String configurationFilePathVariable ) {
        if ( configurationFilePathVariable != null ) {
            LOG.info( "Configuration directory system property is {}", configurationFilePathVariable );
            String configFilePath = System.getProperty( configurationFilePathVariable );
            LOG.info( "Configuration directory is {}", configFilePath );
            if ( configFilePath != null ) {
                Path configDirectory = Paths.get( configFilePath );
                if ( Files.isDirectory( configDirectory ) && Files.exists( configDirectory ) )
                    return configDirectory;
                else
                    LOG.info( "Configuration directory {} does not exist or is not a directory.", configFilePath );
            }
        }
        return null;
    }

}