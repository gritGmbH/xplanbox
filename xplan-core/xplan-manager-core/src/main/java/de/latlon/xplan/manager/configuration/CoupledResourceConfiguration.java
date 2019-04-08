package de.latlon.xplan.manager.configuration;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CoupledResourceConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger( CoupledResourceConfiguration.class );

    private final String planWerkWmsBaseUrl;

    private final Map<XPlanType, String> planWerkWmsGetMapLayers = new HashMap<>();

    private final Map<XPlanType, String> planWerkWmsGetMapStyles = new HashMap<>();

    private final int planWerkWmsGetMapWidth;

    private final int planWerkWmsGetMapHeight;

    private final String cswUrlProvidingDatasetMetadata;

    private final Path metadataConfigDirectory;

    private final Path directoryToStoreDatasetMetadata;

    /**
     * Instantiates the configuration and checks mandatory parameters.
     *
     * @param cswUrlProvidingDatasetMetadata
     *                 never <code>null</code>
     * @param metadataConfigDirectory
     *                 never <code>null</code> and must exist
     * @param directoryToStoreDatasetMetadata
     *                 never <code>null</code> and must exist
     * @param planWerkWmsBaseUrl
     *                 never <code>null</code>
     * @param planWerkWmsGetMapWidth
     *                 default: 750 if less or equal than 0
     * @param planWerkWmsGetMapHeight
     *                 default: 750 if less or equal than 0
     * @throws IllegalArgumentException
     *                 if at least one of the mandatory parameters is null or one of the directories does not exist
     */
    public CoupledResourceConfiguration( String cswUrlProvidingDatasetMetadata, Path metadataConfigDirectory,
                                         Path directoryToStoreDatasetMetadata, String planWerkWmsBaseUrl,
                                         int planWerkWmsGetMapWidth, int planWerkWmsGetMapHeight ) {
        checkParameter( cswUrlProvidingDatasetMetadata, planWerkWmsBaseUrl, metadataConfigDirectory,
                        directoryToStoreDatasetMetadata );
        this.cswUrlProvidingDatasetMetadata = cswUrlProvidingDatasetMetadata;
        this.metadataConfigDirectory = metadataConfigDirectory;
        this.directoryToStoreDatasetMetadata = directoryToStoreDatasetMetadata;
        this.planWerkWmsBaseUrl = planWerkWmsBaseUrl;
        this.planWerkWmsGetMapWidth = planWerkWmsGetMapWidth >= 0 ? planWerkWmsGetMapWidth : 750;
        this.planWerkWmsGetMapHeight = planWerkWmsGetMapHeight >= 0 ? planWerkWmsGetMapHeight : 750;
    }

    public String getCswUrlProvidingDatasetMetadata() {
        return cswUrlProvidingDatasetMetadata;
    }

    public Path getMetadataConfigDirectory() {
        return metadataConfigDirectory;
    }

    public Path getDirectoryToStoreDatasetMetadata() {
        return directoryToStoreDatasetMetadata;
    }

    public String getPlanWerkWmsBaseUrl() {
        return planWerkWmsBaseUrl;
    }

    public int getPlanWerkWmsGetMapWidth() {
        return planWerkWmsGetMapWidth;
    }

    public int getPlanWerkWmsGetMapHeight() {
        return planWerkWmsGetMapHeight;
    }

    public String getLayerByType( XPlanType type ) {
        return this.planWerkWmsGetMapLayers.get( type );
    }

    public String getStyleByType( XPlanType type ) {
        return this.planWerkWmsGetMapStyles.get( type );
    }

    public void addPlanWerkWmsGetMapLayer( XPlanType type, String layer ) {
        this.planWerkWmsGetMapLayers.put( type, layer );

    }

    public void addPlanWerkWmsGetMapStyle( XPlanType type, String style ) {
        this.planWerkWmsGetMapStyles.put( type, style );
    }

    /**
     * Logs the configuration on info level.
     *
     * @param log
     *                 to log into, never <code>null</code>
     */
    public void logConfiguration( final Logger log ) {
        log.info( "  CoupledResourceConfiguration" );

        log.info( "   - planWerkWmsBaseUrl: {}", planWerkWmsBaseUrl );
        log.info( "   - planWerkWmsGetMapWidth: {}", planWerkWmsGetMapWidth );
        log.info( "   - planWerkWmsGetMapHeight: {}", planWerkWmsGetMapHeight );
        for ( Map.Entry<XPlanType, String> layer : planWerkWmsGetMapLayers.entrySet() ) {
            log.info( "   - Layer of type {}: {}", layer.getKey(), layer.getValue() );
        }
        for ( Map.Entry<XPlanType, String> style : planWerkWmsGetMapStyles.entrySet() ) {
            log.info( "   - Style of type {}: {}", style.getKey(), style.getValue() );
        }
        log.info( "   - CSW Url: {}", cswUrlProvidingDatasetMetadata );
        log.info( "   - Metadata config directory: {}", metadataConfigDirectory );
        log.info( "   - Directory to store dataset metadata: {}", directoryToStoreDatasetMetadata );
    }

    public static CoupledResourceConfiguration parseCoupledResourceConfiguration( PropertiesLoader propertiesLoader,
                                                                                  Properties properties ) {
        String cswUrlProvidingDatasetMetadata = properties.getProperty( "cswUrlProvidingDatasetMetadata" );
        Path directoryToStoreDatasetMetadata = getDirectoryToStoreDatasetMetadata( properties );
        Path metadataConfigDirectory = propertiesLoader.resolveDirectory( "metadata" );
        String planWerkWmsBaseUrl = properties.getProperty( "planWerkWmsBaseUrl" );
        int planWerkWmsGetMapWidth = parseInteger( properties, "planWerkWmsGetMapWidth", 750 );
        int planWerkWmsGetMapHeight = parseInteger( properties, "planWerkWmsGetMapHeight", 750 );

        try {
            CoupledResourceConfiguration configuration = new CoupledResourceConfiguration(
                            cswUrlProvidingDatasetMetadata, metadataConfigDirectory, directoryToStoreDatasetMetadata,
                            planWerkWmsBaseUrl, planWerkWmsGetMapWidth, planWerkWmsGetMapHeight );

            for ( XPlanType type : XPlanType.values() ) {
                String layerKey = "planWerkWmsGetMapLayers_" + type;
                String layer = properties.getProperty( layerKey, getDefaultValue( type ) );
                configuration.addPlanWerkWmsGetMapLayer( type, layer );
                String styleKey = "planWerkWmsGetMapStyles_" + type;
                String style = properties.getProperty( styleKey, "" );
                configuration.addPlanWerkWmsGetMapStyle( type, style );
            }
            return configuration;
        } catch ( IllegalArgumentException e ) {
            LOG.warn( "Data-Service-Coupling configuration is not configured or contains invalid entries: {}",
                      e.getMessage() );
        }
        return null;
    }

    private void checkParameter( String cswUrlProvidingDatasetMetadata, String planWerkWmsBaseUrl,
                                 Path metadataConfigDirectory, Path directoryToStoreDatasetMetadata ) {
        if ( cswUrlProvidingDatasetMetadata == null || cswUrlProvidingDatasetMetadata.isEmpty() )
            throw new IllegalArgumentException( "Property cswUrlProvidingDatasetMetadata is null or empty" );
        if ( planWerkWmsBaseUrl == null || planWerkWmsBaseUrl.isEmpty() )
            throw new IllegalArgumentException( "Property planWerkWmsBaseUrl is null or empty" );
        if ( metadataConfigDirectory == null )
            throw new IllegalArgumentException( "Property metadataConfigDirectory is missing" );
        if ( !Files.exists( metadataConfigDirectory ) )
            throw new IllegalArgumentException(
                            "metadataConfigDirectory (" + metadataConfigDirectory + ") does not exist" );
        if ( directoryToStoreDatasetMetadata == null )
            throw new IllegalArgumentException( "Property directoryToStoreDatasetMetadata is missing" );
        if ( !Files.exists( directoryToStoreDatasetMetadata ) )
            throw new IllegalArgumentException( "directoryToStoreDatasetMetadata (" + directoryToStoreDatasetMetadata
                                                + ") does not exist" );
    }

    private static String getDefaultValue( XPlanType type ) {
        switch ( type ) {
        case RP_Plan:
            return "RP_Planvektor,RP_Planraster";
        case LP_Plan:
            return "LP_Planvektor,LP_Planraster";
        case FP_Plan:
            return "FP_Planvektor,FP_Planraster";
        case SO_Plan:
            return "SO_Planvektor,SO_Planraster";
        case BP_Plan:
        default:
            return "BP_Planvektor,BP_Planraster";
        }

    }

    private static Path getDirectoryToStoreDatasetMetadata( Properties properties ) {
        String directoryToStoreDatasetMetadata = properties.getProperty( "directoryToStoreDatasetMetadata" );
        if ( directoryToStoreDatasetMetadata != null )
            return Paths.get( directoryToStoreDatasetMetadata );
        return null;
    }

    private static int parseInteger( Properties loadProperties, String propName, int defaultValue ) {
        String property = loadProperties.getProperty( propName );
        if ( property == null || "".equals( property ) )
            return defaultValue;
        return Integer.parseInt( property );
    }

}