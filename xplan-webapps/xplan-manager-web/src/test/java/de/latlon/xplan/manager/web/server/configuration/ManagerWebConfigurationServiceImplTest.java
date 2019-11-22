package de.latlon.xplan.manager.web.server.configuration;

import de.latlon.xplan.manager.web.server.service.ManagerWebConfigurationServiceImpl;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;
import de.latlon.xplan.manager.web.shared.RasterLayerConfiguration;
import de.latlon.xplan.manager.web.shared.VectorLayerConfiguration;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
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
                            throws ConfigurationException {
        ManagerWebConfigurationRetriever configurationRetriever = mock( ManagerWebConfigurationRetriever.class );
        when( configurationRetriever.setupManagerWebConfiguration() ).thenReturn( config );

        return new ManagerWebConfigurationServiceImpl( configurationRetriever );
    }

    protected ManagerWebConfigurationServiceImpl retrieveConfigurationService( MapPreviewConfiguration config )
                            throws ConfigurationException {
        ManagerWebConfigurationRetriever configurationRetriever = mock( ManagerWebConfigurationRetriever.class );
        when( configurationRetriever.setupMapPreviewConfiguration() ).thenReturn( config );

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
                        "rpVectorlayer", "soVectorlayer" );
    }

    private static RasterLayerConfiguration createRasterLayerConfig() {
        return new RasterLayerConfiguration( "Raster WMS", "bpRasterlayer", "fpRasterLayer", "lpRasterLayer",
                        "rpRasterlayer", "soRasterlayer" );
    }

    private static ManagerWebConfiguration createManagerWbConfig() {
        return new ManagerWebConfiguration( true, true, true,true, true, "EPSG:25832", new String[] { "EPSG:25832" },
                        new String[] { "Testdorf" }, new String[] { "ADE" }  );
    }

}