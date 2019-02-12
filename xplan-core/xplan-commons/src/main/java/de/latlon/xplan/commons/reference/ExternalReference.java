package de.latlon.xplan.commons.reference;

/**
 * Relevant information contained in a single <code>XP_ExterneReferenz</code>/<code>XP_ExterneReferenzPlan</code>
 * object.
 * 
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * 
 * @since 1.0
 */
public class ExternalReference {

    private final String geoRefUrl;

    private final String geoRefMimeTypeCode;

    private final String referenzUrl;

    private final String referenzName;

    private final String referenzMimeTypeCode;

    private final boolean isPlan;

    public ExternalReference( String referenzUrl ) {
        this( null, null, referenzUrl, null, null, true );
    }

    ExternalReference( String geoRefUrl, String geoRefMimeType, String referenzUrl, String referenzName,
                       String referenzMimeTypeCode, boolean isPlan ) {
        this.geoRefUrl = geoRefUrl;
        this.geoRefMimeTypeCode = geoRefMimeType;
        this.referenzUrl = referenzUrl;
        this.referenzName = referenzName;
        this.referenzMimeTypeCode = referenzMimeTypeCode;
        this.isPlan = isPlan;
    }

    public String getGeoRefUrl() {
        return geoRefUrl;
    }

    public String getGeoRefMimeTypeCode() {
        return geoRefMimeTypeCode;
    }

    public String getReferenzUrl() {
        return referenzUrl;
    }

    public String getReferenzName() {
        return referenzName;
    }

    public String getReferenzMimeTypeCode() {
        return referenzMimeTypeCode;
    }

    public boolean isPlan() {
        return isPlan;
    }

    @Override
    public String toString() {
        return "ExternalReference [geoRefUrl=" + geoRefUrl + ", geoRefMimeTypeCode=" + geoRefMimeTypeCode
               + ", referenzUrl=" + referenzUrl + ", referenzName=" + referenzName + ", referenzMimeTypeCode="
               + referenzMimeTypeCode + ", isPlan=" + isPlan + "]";
    }
}
