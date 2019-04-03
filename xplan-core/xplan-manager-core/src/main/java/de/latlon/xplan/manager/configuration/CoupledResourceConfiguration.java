package de.latlon.xplan.manager.configuration;

import de.latlon.xplan.commons.XPlanType;
import org.slf4j.Logger;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CoupledResourceConfiguration {

    private final String planWerkWmsBaseUrl;

    private final Map<XPlanType, String> planWerkWmsGetMapLayers = new HashMap<>();

    private final Map<XPlanType, String> planWerkWmsGetMapStyles = new HashMap<>();

    private final int planWerkWmsGetMapWidth;

    private final int planWerkWmsGetMapHeight;

    private String cswUrlProvidingDatasetMetadata;

    private final Path metadataConfigDirectory;

    private final Path directoryToStoreDatasetMetadata;

    public CoupledResourceConfiguration( String cswUrlProvidingDatasetMetadata, Path metadataConfigDirectory,
                                         Path directoryToStoreDatasetMetadata, String planWerkWmsBaseUrl,
                                         int planWerkWmsGetMapWidth, int planWerkWmsGetMapHeight ) {
        this.cswUrlProvidingDatasetMetadata = cswUrlProvidingDatasetMetadata;
        this.metadataConfigDirectory = metadataConfigDirectory;
        this.directoryToStoreDatasetMetadata = directoryToStoreDatasetMetadata;
        this.planWerkWmsBaseUrl = planWerkWmsBaseUrl;
        this.planWerkWmsGetMapWidth = planWerkWmsGetMapWidth;
        this.planWerkWmsGetMapHeight = planWerkWmsGetMapHeight;
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

}