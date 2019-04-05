package de.latlon.xplan.commons.configuration;

import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import de.latlon.xplan.manager.web.shared.ConfigurationException;

/**
 * Abstract {@link PropertiesLoader}, loading properties from properties file.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public abstract class AbstractPropertiesLoader implements PropertiesLoader {

    @Override
    public Properties loadProperties( String propertiesFileName )
                    throws ConfigurationException {
        InputStream filePath = retrieveAsStream( propertiesFileName );
        if ( filePath != null ) {
            return loadProperties( filePath );
        }
        return null;
    }

    @Override
    public Path resolveDirectory( String subdirectory ) {
        Path configDirectory = getConfigDirectory();
        if ( configDirectory != null )
            return configDirectory.resolve( subdirectory );
        return null;
    }

    /**
     * Retrieves the resource with the given name.
     * 
     * @param propertiesFileName
     *            name of the properties file, never <code>null</code>
     * @return the properties file as stream or <code>null</code> if the properties file could not be found
     */
    abstract InputStream retrieveAsStream( String propertiesFileName );

    private Properties loadProperties( InputStream filePath )
                    throws ConfigurationException {
        try {
            Properties props = new Properties();
            props.load( filePath );
            return props;
        } catch ( IOException e ) {
            throw new ConfigurationException( e );
        } finally {
            closeQuietly( filePath );
        }
    }

}