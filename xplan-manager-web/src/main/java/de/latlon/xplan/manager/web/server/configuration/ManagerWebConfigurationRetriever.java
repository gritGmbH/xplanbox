package de.latlon.xplan.manager.web.server.configuration;

import static java.lang.Double.parseDouble;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;
import de.latlon.xplan.manager.web.shared.RasterLayerConfiguration;
import de.latlon.xplan.manager.web.shared.VectorLayerConfiguration;
import de.latlon.xplan.manager.web.shared.XPlanEnvelope;

/**
 * Reads a configuration file of the following format: <br/>
 * <br/>
 * activateInternalIdDialog={isInternalIdDialogActivated}<br/>
 * defaultCrs={EPSG Code}<br/>
 * chooseCrs={comma separated list of EPSG codes}<br/>
 * categoryFilterValues={categoryFilterValues}<br/>
 * <br/>
 * wmsUrl={wms url}<br/>
 * basemapUrl={basemap url}<br/>
 * basemapName={basemap name}<br/>
 * basemapLayer={basemap layer name}<br/>
 * mapCrs={crs}<br/>
 * mapExtent={extent}<br/>
 * <br/>
 * vectorWmsName={vector wms name}<br/>
 * bpVectorLayer={comma separated list of bp vector layers}<br/>
 * fpVectorLayer={comma separated list of fp vector layers}<br/>
 * lpVectorLayer={comma separated list of lp vector layers}<br/>
 * rpVectorLayer={comma separated list of rp vector layers}<br/>
 * <br/>
 * rasterWmsName={raster wms name}<br/>
 * bpRasterLayer={comma separated list of bp raster layers}<br/>
 * fpRasterLayer={comma separated list of fp raster layers}<br/>
 * lpRasterLayer={comma separated list of lp raster layers}<br/>
 * rpRasterLayer={comma separated list of rp raster layers}
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ManagerWebConfigurationRetriever {

    private static final String MANAGER_WEB_CONFIGURATION_PROPERTIES = "managerWebConfiguration.properties";

    /**
     * Sets up an instance of <link>ManagerWebConfiguration</link>
     * 
     * @param configurationFilePathVariable
     *            the environment variable to read the config file path
     * @return the web configuration
     * @throws URISyntaxException
     * @throws IOException
     */
    public ManagerWebConfiguration setupManagerWebConfiguration( String configurationFilePathVariable )
                    throws URISyntaxException, IOException, ConfigurationException {
        SystemPropertyPropertiesLoader configurationRetriever = new SystemPropertyPropertiesLoader(
                        configurationFilePathVariable, this.getClass() );
        Properties props = configurationRetriever.loadProperties( MANAGER_WEB_CONFIGURATION_PROPERTIES );
        checkIfPropsNotNull( props );
        return createManagerWebConfigurationFromProperties( props );
    }

    /**
     * Sets up an instance of <link>MapPreviewConfiguration</link>
     * 
     * @param configurationFilePathVariable
     *            the environment variable to read the config file path
     * @return the map preview configuration
     * @throws URISyntaxException
     * @throws IOException
     */
    public MapPreviewConfiguration setupMapPreviewConfiguration( String configurationFilePathVariable )
                    throws URISyntaxException, IOException, ConfigurationException {
        SystemPropertyPropertiesLoader configurationRetriever = new SystemPropertyPropertiesLoader(
                        configurationFilePathVariable, this.getClass() );
        Properties props = configurationRetriever.loadProperties( MANAGER_WEB_CONFIGURATION_PROPERTIES );
        checkIfPropsNotNull( props );
        return createMapPreviewConfigurationFromProperties( props );
    }

    private ManagerWebConfiguration createManagerWebConfigurationFromProperties( Properties props )
                    throws ConfigurationException {
        boolean internalIdActivated = parseActivateInternalIdDialog( props );
        boolean legislationStatusActivated = parseActivateLegislationStatusDialog( props );
        boolean validityPeriodActivated = parseActivateValidityPeriodDialog( props );
        boolean editorActivated = parseActivateEditor( props );
        boolean publishingInspirePluActivated = parseActivatePublishingInspirePlu( props );

        String defaultCrs = retrieveMandatoryPropertyValue( props, "defaultCrs" );
        String[] chooseCrs = parseChooseCrs( props );
        String[] categoryFilterValues = parseCategoryFilterValues( props );
        String[] hiddenColumns = parseHiddenColumns( props );
        return new ManagerWebConfiguration( internalIdActivated, legislationStatusActivated, validityPeriodActivated,
                                            editorActivated, publishingInspirePluActivated, defaultCrs, chooseCrs,
                                            categoryFilterValues, hiddenColumns );
    }

    private MapPreviewConfiguration createMapPreviewConfigurationFromProperties( Properties props )
                    throws ConfigurationException {
        String basemapUrl = retrieveMandatoryPropertyValue( props, "basemapUrl" );
        String basemapName = retrieveMandatoryPropertyValue( props, "basemapName" );
        String basemapLayer = retrieveMandatoryPropertyValue( props, "basemapLayer" );
        String wmsUrl = retrieveMandatoryPropertyValue( props, "wmsUrl" );
        String wmsEndpoint = props.getProperty( "wmsEndpoint" );
        String wmsPreEndpoint = props.getProperty( "wmsPreEndpoint" );
        String wmsArchiveEndpoint = props.getProperty( "wmsArchiveEndpoint" );
        XPlanEnvelope maxExtent = parseMaxExtent( props );
        VectorLayerConfiguration vectorLayerConfiguration = createVectorLayerConfigurationFromProperties( props );
        RasterLayerConfiguration rasterLayerConfiguration = createRasterLayerConfigurationFromProperties( props );
        return new MapPreviewConfiguration( basemapUrl, basemapName, basemapLayer, wmsUrl, wmsEndpoint, wmsPreEndpoint,
                        wmsArchiveEndpoint, maxExtent, vectorLayerConfiguration, rasterLayerConfiguration );
    }

    private VectorLayerConfiguration createVectorLayerConfigurationFromProperties( Properties props )
                    throws ConfigurationException {
        String vectorWmsName = retrieveMandatoryPropertyValue( props, "vectorWmsName" );
        String bpVectorLayer = retrieveMandatoryPropertyValue( props, "bpVectorLayer" );
        String fpVectorLayer = retrieveMandatoryPropertyValue( props, "fpVectorLayer" );
        String lpVectorLayer = retrieveMandatoryPropertyValue( props, "lpVectorLayer" );
        String rpVectorLayer = retrieveMandatoryPropertyValue( props, "rpVectorLayer" );
        return new VectorLayerConfiguration( vectorWmsName, bpVectorLayer, fpVectorLayer, lpVectorLayer,
                        rpVectorLayer );
    }

    private RasterLayerConfiguration createRasterLayerConfigurationFromProperties( Properties props )
                    throws ConfigurationException {
        String rasterWmsName = retrieveMandatoryPropertyValue( props, "rasterWmsName" );
        String bpRasterLayer = retrieveMandatoryPropertyValue( props, "bpRasterLayer" );
        String fpRasterLayer = retrieveMandatoryPropertyValue( props, "fpRasterLayer" );
        String lpRasterLayer = retrieveMandatoryPropertyValue( props, "lpRasterLayer" );
        String rpRasterLayer = retrieveMandatoryPropertyValue( props, "rpRasterLayer" );
        return new RasterLayerConfiguration( rasterWmsName, bpRasterLayer, fpRasterLayer, lpRasterLayer,
                        rpRasterLayer );
    }

    private boolean parseActivateInternalIdDialog( Properties props )
                    throws ConfigurationException {
        return "true".equals( retrieveMandatoryPropertyValue( props, "activateInternalIdDialog" ) );
    }

    private boolean parseActivateLegislationStatusDialog( Properties props )
                    throws ConfigurationException {
        return "true".equals( props.getProperty( "activateLegislationStatusDialog" ) );
    }

    private boolean parseActivateValidityPeriodDialog( Properties props )
                    throws ConfigurationException {
        return "true".equals( props.getProperty( "activateValidityPeriodDialog" ) );
    }

    private boolean parseActivateEditor( Properties props )
                    throws ConfigurationException {
        return "true".equals( props.getProperty( "activateEditor" ) );
    }


    private boolean parseActivatePublishingInspirePlu( Properties props )
                            throws ConfigurationException {
        return "true".equals( props.getProperty( "activatePublishingInspirePlu" ) );
    }
    
    private String[] parseChooseCrs( Properties props )
                    throws ConfigurationException {
        return parseAsArray( props, "chooseCrs" );
    }

    private String[] parseCategoryFilterValues( Properties props )
                    throws ConfigurationException {
        return parseAsArray( props, "categoryFilterValues" );
    }

    private String[] parseHiddenColumns( Properties props )
                            throws ConfigurationException {
        if ( props.containsKey( "hiddenColumns" ) )
            return parseAsArray( props, "hiddenColumns" );
        return new String[]{};
    }

    private String[] parseAsArray( Properties props, String key )
                    throws ConfigurationException {
        String chooseCrs = retrieveMandatoryPropertyValue( props, key );
        return chooseCrs.split( "," );
    }

    private XPlanEnvelope parseMaxExtent( Properties props )
                    throws ConfigurationException {
        String maxExtent = retrieveMandatoryPropertyValue( props, "mapExtent" );
        String[] split = maxExtent.split( "," );
        double minX = parseDouble( split[0].trim() );
        double minY = parseDouble( split[1].trim() );
        double maxX = parseDouble( split[2].trim() );
        double maxY = parseDouble( split[3].trim() );
        String mapCrs = retrieveMandatoryPropertyValue( props, "mapCrs" );
        return new XPlanEnvelope( minX, minY, maxX, maxY, mapCrs );
    }

    private void checkIfPropsNotNull( Properties props )
                    throws ConfigurationException {
        if ( props == null )
            throw new ConfigurationException(
                            "Configuration properties could not be loaded! Is the configuration file missing?" );
    }

    private String retrieveMandatoryPropertyValue( Properties props, String propName )
                    throws ConfigurationException {
        String prop = props.getProperty( propName );
        if ( prop == null )
            throw new ConfigurationException(
                            "Property " + propName + " could not be found! Please check the configuration file." );
        return prop;
    }

}
