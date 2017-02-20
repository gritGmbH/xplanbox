package de.latlon.xplan.manager.web.server.configuration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.ServletException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.latlon.xplan.manager.web.server.service.ManagerWebConfigurationServiceImpl;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;
import de.latlon.xplan.manager.web.shared.RasterLayerConfiguration;
import de.latlon.xplan.manager.web.shared.VectorLayerConfiguration;
import de.latlon.xplan.manager.web.shared.XPlanEnvelope;

/**
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class ManagerWebConfigurationServiceImplTest {

    private static ManagerWebConfiguration managerWebConfiguration;

    private static MapPreviewConfiguration mapPreviewConfiguration;

    @BeforeClass
    public static void setup() {
        managerWebConfiguration = createManagerWbConfig();
        mapPreviewConfiguration = createMapPreviewConfig();
    }

    @Test
    public void testGetManagerWebConfiguration()
                    throws Exception {
        ManagerWebConfigurationServiceImpl configurationService = retrieveConfigurationService( managerWebConfiguration );

        ManagerWebConfiguration configuration = configurationService.getManagerWebConfiguration();

        assertThat( configuration, is( managerWebConfiguration ) );
    }

    @Test
    public void testGetMapPreviewConfiguration()
                    throws Exception {
        ManagerWebConfigurationServiceImpl configurationService = retrieveConfigurationService( mapPreviewConfiguration );

        MapPreviewConfiguration configuration = configurationService.getMapPreviewConfiguration();

        assertThat( configuration, is( mapPreviewConfiguration ) );
    }

    protected ManagerWebConfigurationServiceImpl retrieveConfigurationService( ManagerWebConfiguration config )
                    throws ServletException, URISyntaxException, IOException, ConfigurationException {
        ManagerWebConfigurationRetriever configurationRetriever = mock( ManagerWebConfigurationRetriever.class );
        when( configurationRetriever.setupManagerWebConfiguration( anyString() ) ).thenReturn( config );

        return new ManagerWebConfigurationServiceImpl( configurationRetriever );
    }

    protected ManagerWebConfigurationServiceImpl retrieveConfigurationService( MapPreviewConfiguration config )
                    throws ServletException, URISyntaxException, IOException, ConfigurationException {
        ManagerWebConfigurationRetriever configurationRetriever = mock( ManagerWebConfigurationRetriever.class );
        when( configurationRetriever.setupMapPreviewConfiguration( anyString() ) ).thenReturn( config );

        return new ManagerWebConfigurationServiceImpl( configurationRetriever );
    }

    private static MapPreviewConfiguration createMapPreviewConfig() {
        VectorLayerConfiguration vectorLayerConfiguration = createVectorLayerConfig();
        RasterLayerConfiguration rasterLayerConfiguration = createRasterLayerConfig();
        return new MapPreviewConfiguration( "basemapUrl", "basemapName", "basemapLayer", "wmsUrl", "wms", "wmspre",
                        "wmsarchive", new XPlanEnvelope( 0, 0, 1, 1, "epsg:4326" ), vectorLayerConfiguration,
                        rasterLayerConfiguration );
    }

    private static VectorLayerConfiguration createVectorLayerConfig() {
        return new VectorLayerConfiguration( "Vector WMS", "bpVectorlayer", "fpVectorLayer", "lpVectorLayer",
                        "rpVectorlayer" );
    }

    private static RasterLayerConfiguration createRasterLayerConfig() {
        return new RasterLayerConfiguration( "Raster WMS", "bpRasterlayer", "fpRasterLayer", "lpRasterLayer",
                        "rpRasterlayer" );
    }

    private static ManagerWebConfiguration createManagerWbConfig() {
        return new ManagerWebConfiguration( true, true, true,true, "EPSG:25832", new String[] { "EPSG:25832" },
                        new String[] { "Testdorf" } );
    }

}