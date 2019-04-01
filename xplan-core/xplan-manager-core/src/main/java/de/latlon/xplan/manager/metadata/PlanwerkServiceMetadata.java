package de.latlon.xplan.manager.metadata;

import org.deegree.geometry.Envelope;

/**
 * Encapsulates metadata describing the Planwerk WMS of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkServiceMetadata {

    private String title;

    private String description;

    private String planwerkWmsGetMapUrl;

    private String district;

    private String dataMetadataUrl;

    private String dataMetadataId;

    private String planwerkWmsGetCapabilitiesUrl;

    private String planwerkWmsName;

    private Envelope envelope;

    public PlanwerkServiceMetadata( String title, String description, String planwerkWmsGetMapUrl, String district,
                                    String dataMetadataUrl, String dataMetadataId, String planwerkWmsGetCapabilitiesUrl,
                                    String planwerkWmsName, Envelope envelope ) {
        this.title = title;
        this.description = description;
        this.planwerkWmsGetMapUrl = planwerkWmsGetMapUrl;
        this.district = district;
        this.dataMetadataUrl = dataMetadataUrl;
        this.dataMetadataId = dataMetadataId;
        this.planwerkWmsGetCapabilitiesUrl = planwerkWmsGetCapabilitiesUrl;
        this.planwerkWmsName = planwerkWmsName;
        this.envelope = envelope;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPlanwerkWmsGetMapUrl() {
        return planwerkWmsGetMapUrl;
    }

    public String getDistrict() {
        return district;
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public String getDataMetadataUrl() {
        return dataMetadataUrl;
    }

    public String getDataMetadataId() {
        return dataMetadataId;
    }

    public String getPlanwerkWmsGetCapabilitiesUrl() {
        return planwerkWmsGetCapabilitiesUrl;
    }

    public String getPlanwerkWmsName() {
        return planwerkWmsName;
    }

}