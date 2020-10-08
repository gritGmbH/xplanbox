package de.latlon.xplanbox.api.validator.config;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorApiConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger( ValidatorApiConfiguration.class );

    private static final String VALIDATOR_API_CONFIGURATION_PROPERTIES = "validatorApiConfiguration.properties";

    private static final String API_URL = "apiUrl";

    private URI apiUrl;

    public ValidatorApiConfiguration( PropertiesLoader propertiesLoader )
                            throws ConfigurationException {
        loadProperties( propertiesLoader );
        logProperties();
    }

    /**
     * @return the configured api url, may be <code>null</code>
     */
    public URI getApiUrl() {
        return apiUrl;
    }

    private void loadProperties( PropertiesLoader propertiesLoader )
                            throws ConfigurationException {
        if ( propertiesLoader != null ) {
            Properties loadProperties = propertiesLoader.loadProperties( VALIDATOR_API_CONFIGURATION_PROPERTIES );
            if ( loadProperties != null ) {
                apiUrl = parseUri( loadProperties, API_URL );
            }
        }
    }

    private void logProperties() {
        LOG.info( "-------------------------------------------" );
        LOG.info( "Configuration of the XPlanValidatorApi:" );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  API URL: {}", apiUrl );
        LOG.info( "-------------------------------------------" );
    }

    private URI parseUri( Properties loadProperties, String propName )
                            throws ConfigurationException {
        String property = loadProperties.getProperty( propName );
        if ( property == null || "".equals( property ) )
            return null;
        try {
            return new URI( property );
        } catch ( URISyntaxException e ) {
            throw new ConfigurationException( "Could not parse property " + property + " as URI.", e );
        }
    }

}