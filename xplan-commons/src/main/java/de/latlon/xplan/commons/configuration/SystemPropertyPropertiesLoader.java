package de.latlon.xplan.commons.configuration;

import static java.io.File.separatorChar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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

    private final String configurationFilePathVariable;

    private Class<?> defaultBaseClass;

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
        this.configurationFilePathVariable = configurationFilePathVariable;
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
        if ( configurationFilePathVariable != null ) {
            LOG.info( "Configuration directory system property is {}", configurationFilePathVariable );
            String configFilePath = System.getProperty( configurationFilePathVariable );
            LOG.info( "Configuration directory is {}", configFilePath );
            if ( configFilePath != null ) {
                String pathToConfigFile = configFilePath + separatorChar + configurationFileName;
                LOG.info( "Configuration {} is read from file {}", configurationFileName, pathToConfigFile );
                try {
                    return new FileInputStream( pathToConfigFile );
                } catch ( FileNotFoundException e ) {
                    LOG.info( "Configuration does not exist: {}", e.getMessage() );
                    LOG.info( "Internal {} configuration is used.", configurationFileName );
                    return defaultBaseClass.getResourceAsStream( configurationFileName );
                }
            }
        }
        LOG.info( "Internal {} configuration is used.", configurationFileName );
        return defaultBaseClass.getResourceAsStream( configurationFileName );
    }

}