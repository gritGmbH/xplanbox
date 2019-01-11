package de.latlon.xplan.manager.web.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Main Web UI class.
 * 
 * @author Florian Bingel
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class XPlan implements Serializable, Comparable<XPlan> {

    private static final long serialVersionUID = 573099017461370301L;

    private String name;

    private String id;

    private String type;

    private String additionalType;

    private String version;

    private String number;

    private String gkz;

    private int numFeatures = -1;

    private boolean raster;

    private String legislationStatus;

    private Date releaseDate;

    private Date importDate;

    private String ade;

    private Boolean validated = false;

    private Boolean valid = false;

    private Boolean inspirePublished = false;

    private XPlanEnvelope bbox;

    private String district;

    private XPlanMetadata xplanMetadata;
    
    public XPlan() {
        this.name = "N/A";
        this.id = "-";
        this.type = "NO TYPE";
    }

    public XPlan( String name, String id, String type ) {
        this.name = name;
        this.id = id;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion( String version ) {
        this.version = version;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber( String number ) {
        this.number = number;
    }

    public String getGkz() {
        return gkz;
    }

    public void setGkz( String gkz ) {
        this.gkz = gkz;
    }

    /**
     * @return the number of features of the XPlan feature collection, -1 if not known
     */
    public int getNumFeatures() {
        return numFeatures;
    }

    public void setNumFeatures( int numFeatures ) {
        this.numFeatures = numFeatures;
    }

    public boolean isRaster() {
        return raster;
    }

    public void setRaster( boolean raster ) {
        this.raster = raster;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate( Date releaseDate ) {
        this.releaseDate = releaseDate;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate( Date importDate ) {
        this.importDate = importDate;
    }

    public Boolean isValidated() {
        return validated;
    }

    public Boolean isValid() {
        return valid;
    }

    public void setValidated( Boolean validated ) {
        this.validated = validated;
    }

    public void setValid( Boolean valid ) {
        this.valid = valid;
    }

    public void setBbox( XPlanEnvelope bbox ) {
        this.bbox = bbox;
    }

    public XPlanEnvelope getBbox() {
        return bbox;
    }

    public String getLegislationStatus() {
        return legislationStatus;
    }

    public void setLegislationStatus( String legislationStatus ) {
        this.legislationStatus = legislationStatus;
    }

    public String getAde() {
        return ade;
    }

    public void setAde( String ade ) {
        this.ade = ade;
    }

    public String getAdditionalType() {
        return additionalType;
    }

    public void setAdditionalType( String additionalType ) {
        this.additionalType = additionalType;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict( String district ) {
        this.district = district;
    }

    public XPlanMetadata getXplanMetadata() {
        return xplanMetadata;
    }

    public void setXplanMetadata( XPlanMetadata xplanMetadata ) {
        this.xplanMetadata = xplanMetadata;
    }

    public Boolean isInspirePublished() {
        return inspirePublished;
    }

    public void setInspirePublished( Boolean inspirePublished ) {
        this.inspirePublished = inspirePublished;
    }

    @Override
    public int compareTo( XPlan o ) {
        return ( o == null || o.name == null ) ? -1 : -o.name.compareTo( name );
    }

}