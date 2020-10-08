package de.latlon.xplanbox.api.manager.config;

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
public class ManagerApiConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger( ManagerApiConfiguration.class );

    private static final String MANAGER_API_CONFIGURATION_PROPERTIES = "managerApiConfiguration.properties";

    private static final String API_URL = "apiUrl";

    private static final String WMS_URL = "wmsUrl";

    private URI apiUrl;

    private URI wmsUrl;

    private DefaultValidationConfiguration defaultValidationConfiguration;

    public ManagerApiConfiguration( PropertiesLoader propertiesLoader )
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

    /**
     * @return the configured WMS url, may be <code>null</code>
     */
    public URI getWmsUrl() {
        return this.wmsUrl;
    }

    /**
     * @return the default validation configuration, never <code>null</code>
     */
    public DefaultValidationConfiguration getDefaultValidationConfiguration() {
        return defaultValidationConfiguration;
    }

    private void loadProperties( PropertiesLoader propertiesLoader )
                            throws ConfigurationException {
        if ( propertiesLoader != null ) {
            Properties loadProperties = propertiesLoader.loadProperties( MANAGER_API_CONFIGURATION_PROPERTIES );
            if ( loadProperties != null ) {
                apiUrl = parseUri( loadProperties, API_URL );
                wmsUrl = parseUri( loadProperties, WMS_URL );
                defaultValidationConfiguration = parseDefaultValidationConfiguration( loadProperties );
            } else {
                defaultValidationConfiguration = new DefaultValidationConfiguration();
            }
        } else {
            defaultValidationConfiguration = new DefaultValidationConfiguration();
        }
    }

    private void logProperties() {
        LOG.info( "-------------------------------------------" );
        LOG.info( "Configuration of the XPlanManagerApi:" );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  API URL: {}", apiUrl );
        LOG.info( "  WMS URL: {}", wmsUrl );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  default validation configuration" );
        LOG.info( "   - skip semantisch: {}", defaultValidationConfiguration.isSkipSemantisch() );
        LOG.info( "   - skip geometrisch: {}", defaultValidationConfiguration.isSkipGeometrisch() );
        LOG.info( "   - skip Flaechenschluss: {}", defaultValidationConfiguration.isSkipFlaechenschluss() );
        LOG.info( "   - skip Geltungsbereich: {}", defaultValidationConfiguration.isSkipGeltungsbereich() );
        LOG.info( "-------------------------------------------" );
    }

    private DefaultValidationConfiguration parseDefaultValidationConfiguration( Properties loadProperties ) {
        boolean skipSemantisch = parseBoolean( loadProperties, "skipSemantisch", false );
        boolean skipGeometrisch = parseBoolean( loadProperties, "skipGeometrisch", false );
        boolean skipFlaechenschluss = parseBoolean( loadProperties, "skipFlaechenschluss", false );
        boolean skipGeltungsbereich = parseBoolean( loadProperties, "skipGeltungsbereich", false );
        return new DefaultValidationConfiguration( skipSemantisch, skipGeometrisch, skipFlaechenschluss,
                                                   skipGeltungsbereich );
    }

    private boolean parseBoolean( Properties loadProperties, String propName, boolean defaultValue ) {
        String property = loadProperties.getProperty( propName );
        if ( property == null || "".equals( property ) )
            return defaultValue;
        return Boolean.parseBoolean( property );
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