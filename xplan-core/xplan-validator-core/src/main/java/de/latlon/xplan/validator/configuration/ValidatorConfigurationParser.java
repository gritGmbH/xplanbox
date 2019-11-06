package de.latlon.xplan.validator.configuration;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static java.nio.file.Files.createTempDirectory;

/**
 * Parses validator configuration and returns {@link ValidatorConfiguration}.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class ValidatorConfigurationParser {

    private static final Logger LOG = LoggerFactory.getLogger( ValidatorConfigurationParser.class );

    private static final String VALIDATOR_CONFIGURATION_PROPERTIES = "validatorConfiguration.properties";

    private static final String VALIDATION_REPORT_DIRECTORY = "validationReportDirectory";

    private static final String VALIDATION_RULES_DIRECTORY = "validationRulesDirectory";

    /**
     * Parse validator configuration.
     * 
     * @param propertiesLoader
     *            properties loader used to load properties from configuration file, never <code>null</code>
     * @return validator configuration, never <code>null</code>
     * @throws ConfigurationException
     *             if the properties file could not be loaded
     * @throws IOException
     *             if the validation report directory could not be created
     * @throws IllegalArgumentException
     *             if the propertiesLoader is <code>null</code>
     */
    public ValidatorConfiguration parse( PropertiesLoader propertiesLoader )
                    throws ConfigurationException, IOException {
        checkParameters( propertiesLoader );
        Properties properties = propertiesLoader.loadProperties( VALIDATOR_CONFIGURATION_PROPERTIES );

        ValidatorConfiguration configuration = parseConfiguration( properties );
        logConfiguration( configuration );
        return configuration;
    }

    private ValidatorConfiguration parseConfiguration( Properties properties )
                            throws IOException {
        Path reportDirectory = createReportDirectory( properties );
        Path rulesDirectory = createRulesDirectory( properties );
        return new ValidatorConfiguration( reportDirectory, rulesDirectory );
    }

    private void logConfiguration( ValidatorConfiguration configuration ) {
        LOG.info( "-------------------------------------------" );
        LOG.info( "Configuration of the XPlanValidator:" );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  validation report directory" );
        LOG.info( "   - {}", configuration.getValidationReportDirectory() );
        LOG.info( "  validation rules directory" );
        LOG.info( "   - {}", configuration.getValidationRulesDirectory() != null ?
                             configuration.getValidationRulesDirectory() :
                             "internal rules are used" );
        LOG.info( "-------------------------------------------" );
    }

    private Path createReportDirectory( Properties properties )
                            throws IOException {
        String validationReportDirectory = properties.getProperty( VALIDATION_REPORT_DIRECTORY );
        if ( validationReportDirectory == null || validationReportDirectory.isEmpty() )
            return createTempDirectory( "validationReport" );
        else
            return Paths.get( validationReportDirectory );
    }

    private Path createRulesDirectory( Properties properties ) {
        String validationRulesDirectory = properties.getProperty( VALIDATION_RULES_DIRECTORY );
        if ( validationRulesDirectory != null && !validationRulesDirectory.isEmpty() )
            return Paths.get( validationRulesDirectory );
        return null;
    }

    private void checkParameters( PropertiesLoader propertiesLoader ) {
        if ( propertiesLoader == null )
            throw new IllegalArgumentException( "propertiesLoader must not be null!" );
    }

}