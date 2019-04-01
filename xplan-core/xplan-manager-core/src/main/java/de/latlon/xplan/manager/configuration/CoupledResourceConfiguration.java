package de.latlon.xplan.manager.configuration;

import java.nio.file.Path;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CoupledResourceConfiguration {

    private String cswUrlProvidingDatasetMetadata;

    private final String metadataResourceTemplate;

    private final Path metadataConfigDirectory;

    private final Path directoryToStoreDatasetMetadata;

    public CoupledResourceConfiguration( String cswUrlProvidingDatasetMetadata, String metadataResourceTemplate,
                                         Path metadataConfigDirectory, Path directoryToStoreDatasetMetadata ) {
        this.cswUrlProvidingDatasetMetadata = cswUrlProvidingDatasetMetadata;
        this.metadataResourceTemplate = metadataResourceTemplate;
        this.metadataConfigDirectory = metadataConfigDirectory;
        this.directoryToStoreDatasetMetadata = directoryToStoreDatasetMetadata;
    }

    public String getCswUrlProvidingDatasetMetadata() {
        return cswUrlProvidingDatasetMetadata;
    }

    public String getMetadataResourceTemplate() {
        return metadataResourceTemplate;
    }

    public Path getMetadataConfigDirectory() {
        return metadataConfigDirectory;
    }

    public Path getDirectoryToStoreDatasetMetadata() {
        return directoryToStoreDatasetMetadata;
    }
}