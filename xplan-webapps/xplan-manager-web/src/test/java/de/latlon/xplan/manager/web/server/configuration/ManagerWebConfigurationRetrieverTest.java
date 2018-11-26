package de.latlon.xplan.manager.web.server.configuration;

import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;
import de.latlon.xplan.manager.web.shared.RasterLayerConfiguration;
import de.latlon.xplan.manager.web.shared.VectorLayerConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static java.io.File.createTempFile;
import static org.apache.commons.io.IOUtils.copy;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Please note: This test uses static mocking of the <link>java.lang.System</link>-class, so the behaviour of this class
 * changes in all tested implementation code!
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class ManagerWebConfigurationRetrieverTest {

    private static final String PROPERTIES_NAME = "managerWebConfiguration.properties";

    private static final String CONF_PATH_VARIABLE = "MANAGER_HOME";

    private static String oldProperty;

    @BeforeClass
    public static void copyPropertiesFileAndSetProxyConfigSystemVaraiable()
                            throws IOException {
        File configDir = copyPropertiesFileToNewConfigDir();
        oldProperty = System.getProperty( CONF_PATH_VARIABLE );
        System.setProperty( CONF_PATH_VARIABLE, configDir.toString() );
    }

    @AfterClass
    public static void resetProxyConfigSystemProperty() {
        if ( oldProperty != null )
            System.setProperty( CONF_PATH_VARIABLE, oldProperty );
    }

    @Test
    public void testSetupManagerWebConfigurationShouldReturnMatchingPropertiesFromSystemEnv()
                            throws Exception {
        ManagerWebConfiguration configuration = new ManagerWebConfigurationRetriever().setupManagerWebConfiguration( CONF_PATH_VARIABLE );

        Properties properties = loadPropertiesFromOriginalFile();
        assertThat( configuration.getCrsDialogDefaultCrs(), is( properties.getProperty( "defaultCrs" ) ) );
        assertThat( configuration.getInternalIdActivated(), is( false ) );
    }

    @Test
    public void testSetupManagerWebConfigurationShouldReturnMatchingPropertiesFromDefaultPath()
                            throws Exception {
        ManagerWebConfiguration configuration = new ManagerWebConfigurationRetriever().setupManagerWebConfiguration( "FOO" );

        Properties properties = loadPropertiesFromOriginalFile();
        assertThat( configuration.getCrsDialogDefaultCrs(), is( properties.getProperty( "defaultCrs" ) ) );
        assertThat( configuration.getInternalIdActivated(), is( false ) );
    }

    @Test
    public void testSetupMapPreviewConfigurationShouldReturnMatchingPropertiesFromSystemEnv()
                            throws Exception {
        MapPreviewConfiguration configuration = new ManagerWebConfigurationRetriever().setupMapPreviewConfiguration( CONF_PATH_VARIABLE );

        Properties properties = loadPropertiesFromOriginalFile();
        assertThat( configuration.getBasemapUrl(), is( properties.getProperty( "basemapUrl" ) ) );
        assertThat( configuration.getBasemapName(), is( properties.getProperty( "basemapName" ) ) );
        assertThat( configuration.getBasemapLayer(), is( properties.getProperty( "basemapLayer" ) ) );
        assertThat( configuration.getWmsUrl(), is( properties.getProperty( "wmsUrl" ) ) );
    }

    @Test
    public void testSetupMapPreviewConfigurationShouldReturnMatchingPropertiesFromDefaultPath()
                            throws Exception {
        MapPreviewConfiguration configuration = new ManagerWebConfigurationRetriever().setupMapPreviewConfiguration( "FOO" );

        Properties properties = loadPropertiesFromOriginalFile();

        assertThat( configuration.getBasemapUrl(), is( properties.getProperty( "basemapUrl" ) ) );
        assertThat( configuration.getBasemapName(), is( properties.getProperty( "basemapName" ) ) );
        assertThat( configuration.getBasemapLayer(), is( properties.getProperty( "basemapLayer" ) ) );
        assertThat( configuration.getWmsUrl(), is( properties.getProperty( "wmsUrl" ) ) );
    }

    @Test
    public void testSetupVectorLayerConfigurationShouldReturnMatchingPropertiesFromSystemEnv()
                            throws Exception {
        VectorLayerConfiguration configuration = new ManagerWebConfigurationRetriever().setupMapPreviewConfiguration( CONF_PATH_VARIABLE ).getVectorLayerConfiguration();

        Properties properties = loadPropertiesFromOriginalFile();

        assertThat( configuration.getVectorWmsName(), is( properties.getProperty( "vectorWmsName" ) ) );
        assertThat( configuration.getBpVectorLayer(), is( properties.getProperty( "bpVectorLayer" ) ) );
        assertThat( configuration.getFpVectorLayer(), is( properties.getProperty( "fpVectorLayer" ) ) );
        assertThat( configuration.getLpVectorLayer(), is( properties.getProperty( "lpVectorLayer" ) ) );
        assertThat( configuration.getRpVectorLayer(), is( properties.getProperty( "rpVectorLayer" ) ) );
    }

    @Test
    public void testSetupVectorLayerConfigurationShouldReturnMatchingPropertiesFromDefaultPath()
                            throws Exception {
        VectorLayerConfiguration configuration = new ManagerWebConfigurationRetriever().setupMapPreviewConfiguration( "FOO" ).getVectorLayerConfiguration();

        Properties properties = loadPropertiesFromOriginalFile();

        assertThat( configuration.getVectorWmsName(), is( properties.getProperty( "vectorWmsName" ) ) );
        assertThat( configuration.getBpVectorLayer(), is( properties.getProperty( "bpVectorLayer" ) ) );
        assertThat( configuration.getFpVectorLayer(), is( properties.getProperty( "fpVectorLayer" ) ) );
        assertThat( configuration.getLpVectorLayer(), is( properties.getProperty( "lpVectorLayer" ) ) );
        assertThat( configuration.getRpVectorLayer(), is( properties.getProperty( "rpVectorLayer" ) ) );
    }

    @Test
    public void testSetupRasterLayerConfigurationShouldReturnMatchingPropertiesFromSystemEnv()
                            throws Exception {
        RasterLayerConfiguration configuration = new ManagerWebConfigurationRetriever().setupMapPreviewConfiguration( CONF_PATH_VARIABLE ).getRasterLayerConfiguration();

        Properties properties = loadPropertiesFromOriginalFile();

        assertThat( configuration.getRasterWmsName(), is( properties.getProperty( "rasterWmsName" ) ) );
        assertThat( configuration.getBpRasterLayer(), is( properties.getProperty( "bpRasterLayer" ) ) );
        assertThat( configuration.getFpRasterLayer(), is( properties.getProperty( "fpRasterLayer" ) ) );
        assertThat( configuration.getLpRasterLayer(), is( properties.getProperty( "lpRasterLayer" ) ) );
        assertThat( configuration.getRpRasterLayer(), is( properties.getProperty( "rpRasterLayer" ) ) );
    }

    @Test
    public void testSetupRasterLayerConfigurationShouldReturnMatchingPropertiesFromDefaultPath()
                            throws Exception {
        RasterLayerConfiguration configuration = new ManagerWebConfigurationRetriever().setupMapPreviewConfiguration( "FOO" ).getRasterLayerConfiguration();

        Properties properties = loadPropertiesFromOriginalFile();

        assertThat( configuration.getRasterWmsName(), is( properties.getProperty( "rasterWmsName" ) ) );
        assertThat( configuration.getBpRasterLayer(), is( properties.getProperty( "bpRasterLayer" ) ) );
        assertThat( configuration.getFpRasterLayer(), is( properties.getProperty( "fpRasterLayer" ) ) );
        assertThat( configuration.getLpRasterLayer(), is( properties.getProperty( "lpRasterLayer" ) ) );
        assertThat( configuration.getRpRasterLayer(), is( properties.getProperty( "rpRasterLayer" ) ) );
    }

    private static File copyPropertiesFileToNewConfigDir()
                            throws IOException {
        File configDirectory = createConfigDirectory();
        File configPropertiesFile = new File( configDirectory, PROPERTIES_NAME );
        FileOutputStream fileOutputStream = new FileOutputStream( configPropertiesFile );

        InputStream testConfigProperties = ManagerWebConfigurationRetrieverTest.class.getResourceAsStream( PROPERTIES_NAME );
        copy( testConfigProperties, fileOutputStream );

        testConfigProperties.close();
        fileOutputStream.close();

        return configDirectory;
    }

    private static File createConfigDirectory()
                            throws IOException {
        File configDirectory = createTempFile( "xplanungisk-config", "" );
        configDirectory.delete();
        configDirectory.mkdir();
        return configDirectory;
    }

    private Properties loadPropertiesFromOriginalFile()
                            throws IOException {
        InputStream resource = ManagerWebConfigurationRetrieverTest.class.getResourceAsStream( PROPERTIES_NAME );
        Properties properties = new Properties();
        properties.load( resource );
        return properties;
    }

}
