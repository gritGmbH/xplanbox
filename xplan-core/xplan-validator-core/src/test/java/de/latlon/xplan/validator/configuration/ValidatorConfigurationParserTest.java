package de.latlon.xplan.validator.configuration;

import de.latlon.xplan.commons.configuration.DefaultPropertiesLoader;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static java.nio.file.Files.isDirectory;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ValidatorConfigurationParser}.
 * 
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * 
 * @version $Revision: $, $Date: $
 */
public class ValidatorConfigurationParserTest {

    private static final String VALIDATION_REPORT_DIRECTORY_KEY = "validationReportDirectory";

    @Test
    public void testParse()
                    throws Exception {
        PropertiesLoader propertiesLoader = mockPropertiesLoader( "/home/xplanbox/report/" );
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        ValidatorConfiguration validatorConfiguration = validatorConfigurationParser.parse( propertiesLoader );

        Path actualValidationReportDirectory = validatorConfiguration.getValidationReportDirectory();
        Path expectedValidationReportDirectory = Paths.get( "/home/xplanbox/report/" );

        assertThat( actualValidationReportDirectory, is( expectedValidationReportDirectory ) );
    }

    @Test
    public void testParseWithEmptyValidationReportDirectory()
                    throws Exception {
        PropertiesLoader propertiesLoader = mockPropertiesLoader( "" );
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        ValidatorConfiguration validatorConfiguration = validatorConfigurationParser.parse( propertiesLoader );

        Path actualValidationReportDirectory = validatorConfiguration.getValidationReportDirectory();

        assertThat( isDirectory( actualValidationReportDirectory ), is( true ) );
    }

    @Test
    public void testParseWithNullValidationReportDirectory()
                    throws Exception {
        PropertiesLoader propertiesLoader = mockPropertiesLoader( null );
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        ValidatorConfiguration validatorConfiguration = validatorConfigurationParser.parse( propertiesLoader );

        Path actualValidationReportDirectory = validatorConfiguration.getValidationReportDirectory();

        assertThat( isDirectory( actualValidationReportDirectory ), is( true ) );
    }

    @Test
    public void testParseFromFile()
                    throws Exception {
        PropertiesLoader propertiesLoader = new DefaultPropertiesLoader( ValidatorConfigurationParser.class );
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        ValidatorConfiguration validatorConfiguration = validatorConfigurationParser.parse( propertiesLoader );

        Path actualValidationReportDirectory = validatorConfiguration.getValidationReportDirectory();
        Path expectedValidationReportDirectory = Paths.get( "/home/xplanbox/file/configuration/report/" );

        assertThat( actualValidationReportDirectory, is( expectedValidationReportDirectory ) );
    }

    private PropertiesLoader mockPropertiesLoader( String validationReportDirectory )
                    throws ConfigurationException {
        PropertiesLoader propertiesLoader = mock( PropertiesLoader.class );
        Properties properties = new Properties();
        if ( validationReportDirectory != null )
            properties.put( VALIDATION_REPORT_DIRECTORY_KEY, validationReportDirectory );
        when( propertiesLoader.loadProperties( anyString() ) ).thenReturn( properties );
        return propertiesLoader;
    }

}