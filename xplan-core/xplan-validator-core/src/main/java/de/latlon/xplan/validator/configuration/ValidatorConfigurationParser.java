package de.latlon.xplan.validator.configuration;

import static java.nio.file.Files.createTempDirectory;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;

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
        File reportDirectoryFile = createReportDirectoryFile( properties );
        return new ValidatorConfiguration( reportDirectoryFile );
    }

    private void logConfiguration( ValidatorConfiguration configuration ) {
        LOG.info( "-------------------------------------------" );
        LOG.info( "Configuration of the XPlanValidator:" );
        LOG.info( "-------------------------------------------" );
        LOG.info( "  validation report directory" );
        LOG.info( "   - {}", configuration.getValidationReportDirectory() );
        LOG.info( "-------------------------------------------" );
    }

    private File createReportDirectoryFile( Properties properties )
                    throws IOException {
        String validationReportDirectory = properties.getProperty( VALIDATION_REPORT_DIRECTORY );
        if ( validationReportDirectory == null || validationReportDirectory.isEmpty() )
            return createTempDirectory( "validationReport" ).toFile();
        else
            return new File( validationReportDirectory );
    }

    private void checkParameters( PropertiesLoader propertiesLoader ) {
        if ( propertiesLoader == null )
            throw new IllegalArgumentException( "propertiesLoader must not be null!" );
    }

}