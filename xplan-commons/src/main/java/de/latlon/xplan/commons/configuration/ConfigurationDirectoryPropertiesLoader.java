package de.latlon.xplan.commons.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Retrieves a configuration from a specified path.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ConfigurationDirectoryPropertiesLoader extends AbstractPropertiesLoader {

    private static final Logger LOG = LoggerFactory.getLogger( ConfigurationDirectoryPropertiesLoader.class );

    private final File directoryContainingTheConfig;

    private final Class<?> defaultBaseClass;

    /**
     * Instantiates a {@link ConfigurationDirectoryPropertiesLoader} loading properties from files in the specified
     * directory.
     * 
     * @param directoryContainingTheConfig
     *            the directory containing the configuration, may be <code>null</code> if the configuration should be
     *            retrieved from classpath
     * @param defaultBaseClass
     *            fallback to retrieve properties file from, if system property is not available, may be
     *            <code>null</code> (this class is used then)
     */
    public ConfigurationDirectoryPropertiesLoader( File directoryContainingTheConfig, Class<?> defaultBaseClass ) {
        this.directoryContainingTheConfig = directoryContainingTheConfig;
        if ( defaultBaseClass != null )
            this.defaultBaseClass = defaultBaseClass;
        else
            this.defaultBaseClass = this.getClass();
    }

    /**
     * Instantiates a {@link ConfigurationDirectoryPropertiesLoader} loading properties from files in the specified
     * directory.
     * 
     * @param directoryContainingTheConfig
     *            the directory containing the configuration, may be <code>null</code> if the configuration should be
     *            retrieved from classpath
     */
    public ConfigurationDirectoryPropertiesLoader( File directoryContainingTheConfig ) {
        this( directoryContainingTheConfig, null );
    }

    @Override
    InputStream retrieveAsStream( String configurationFileName ) {
        if ( directoryContainingTheConfig != null ) {
            LOG.info( "Configuration directory is {}", directoryContainingTheConfig );
            File pathToConfigFile = new File( directoryContainingTheConfig, configurationFileName );
            LOG.info( "Configuration is read from file {}", pathToConfigFile );
            try {
                return new FileInputStream( pathToConfigFile );
            } catch ( FileNotFoundException e ) {
                LOG.info( "Configuration does not exist: {}", e.getMessage() );
                LOG.info( "Internal {} configuration is used.", configurationFileName );
                return defaultBaseClass.getResourceAsStream( configurationFileName );
            }
        }
        LOG.info( "Internal {} configuration is used.", configurationFileName );
        return defaultBaseClass.getResourceAsStream( configurationFileName );
    }

}