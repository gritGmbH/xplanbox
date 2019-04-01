package de.latlon.xplan.manager.configuration;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class CoupledResourceConfiguration {

    private String cswUrlProvidingDatasetMetadata;

    private final String metadataResourceTemplate;

    public CoupledResourceConfiguration( String cswUrlProvidingDatasetMetadata, String metadataResourceTemplate ) {
        this.cswUrlProvidingDatasetMetadata = cswUrlProvidingDatasetMetadata;
        this.metadataResourceTemplate = metadataResourceTemplate;
    }

    public String getCswUrlProvidingDatasetMetadata() {
        return cswUrlProvidingDatasetMetadata;
    }

    public String getMetadataResourceTemplate() {
        return metadataResourceTemplate;
    }

}