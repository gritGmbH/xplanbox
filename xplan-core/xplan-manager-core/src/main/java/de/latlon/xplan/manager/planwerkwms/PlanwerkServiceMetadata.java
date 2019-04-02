package de.latlon.xplan.manager.planwerkwms;

import org.deegree.geometry.Envelope;

/**
 * Encapsulates metadata describing the Planwerk WMS of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkServiceMetadata {

    private String title;

    private String description;

    private Envelope envelope;

    private String planwerkWmsGetCapabilitiesUrl;

    private String planwerkWmsGetMapUrl;

    PlanwerkServiceMetadata( String title, String description, Envelope envelope, String planwerkWmsGetCapabilitiesUrl,
                             String planwerkWmsGetMapUrl ) {
        this.title = title;
        this.description = description;
        this.envelope = envelope;
        this.planwerkWmsGetCapabilitiesUrl = planwerkWmsGetCapabilitiesUrl;
        this.planwerkWmsGetMapUrl = planwerkWmsGetMapUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Envelope getEnvelope() {
        return envelope;
    }

    public String getPlanwerkWmsGetCapabilitiesUrl() {
        return planwerkWmsGetCapabilitiesUrl;
    }

    public String getPlanwerkWmsGetMapUrl() {
        return planwerkWmsGetMapUrl;
    }

    @Override
    public String toString() {
        return "PlanwerkServiceMetadata{" + "title='" + title + '\'' + ", description='" + description + '\''
               + ", envelope=" + envelope + ", planwerkWmsGetCapabilitiesUrl='" + planwerkWmsGetCapabilitiesUrl + '\''
               + ", planwerkWmsGetMapUrl='" + planwerkWmsGetMapUrl + '\'' + '}';
    }
}