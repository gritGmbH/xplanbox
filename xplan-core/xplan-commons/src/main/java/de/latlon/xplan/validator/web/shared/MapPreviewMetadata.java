package de.latlon.xplan.validator.web.shared;

import java.io.Serializable;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MapPreviewMetadata implements Serializable {

    /// TODO!
    private final String baseURL = "http://localhost:8081/xplan-validator/masterportal/";

    private String uuid;

    private XPlanEnvelope bbox;

    private String validationName;

    public MapPreviewMetadata() {
    }

    public MapPreviewMetadata( String uuid, String validationName, XPlanEnvelope bbox ) {
        this.uuid = uuid;
        this.validationName = validationName;
        this.bbox = bbox;
    }

    public String getUuid() {
        return uuid;
    }

    public String getValidationName() {
        return validationName;
    }

    public XPlanEnvelope getBbox() {
        return bbox;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setUuid( String uuid ) {
        this.uuid = uuid;
    }

    public void setValidationName( String validationName ) {
        this.validationName = validationName;
    }

    public void setBbox( XPlanEnvelope bbox ) {
        this.bbox = bbox;
    }
}
