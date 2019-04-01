package de.latlon.xplan.manager.configuration;

import java.nio.file.Path;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CoupledResourceConfiguration {

    private String cswUrlProvidingDatasetMetadata;

    private final String metadataResourceTemplate;

    private final Path metadataConfigDirectory;

    public CoupledResourceConfiguration( String cswUrlProvidingDatasetMetadata, String metadataResourceTemplate,
                                         Path metadataConfigDirectory ) {
        this.cswUrlProvidingDatasetMetadata = cswUrlProvidingDatasetMetadata;
        this.metadataResourceTemplate = metadataResourceTemplate;
        this.metadataConfigDirectory = metadataConfigDirectory;
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

}