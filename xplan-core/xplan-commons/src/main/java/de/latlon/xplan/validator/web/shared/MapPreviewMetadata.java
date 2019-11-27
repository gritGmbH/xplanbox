package de.latlon.xplan.validator.web.shared;

import java.io.Serializable;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MapPreviewMetadata implements Serializable {

    private String configFileName;

    private XPlanEnvelope bbox;

    private String validationName;

    public MapPreviewMetadata() {
    }

    public MapPreviewMetadata( String configFileName, String validationName, XPlanEnvelope bbox ) {
        this.configFileName = configFileName;
        this.validationName = validationName;
        this.bbox = bbox;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public String getValidationName() {
        return validationName;
    }

    public XPlanEnvelope getBbox() {
        return bbox;
    }

    public void setConfigFileName( String configFileName ) {
        this.configFileName = configFileName;
    }

    public void setValidationName( String validationName ) {
        this.validationName = validationName;
    }

    public void setBbox( XPlanEnvelope bbox ) {
        this.bbox = bbox;
    }
}
