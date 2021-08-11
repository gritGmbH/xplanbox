/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.web.shared;

import de.latlon.xplan.validator.web.shared.XPlanEnvelope;

import java.io.Serializable;
import java.util.Date;

/**
 * Main Web UI class.
 *
 * @author Florian Bingel
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
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

    private String internalId;

    private AdditionalPlanData xplanMetadata;

    private boolean hasMultipleXPlanElements;

    public XPlan() {
        this( "N/A", "-", "NO TYPE" );
    }

    public XPlan( String name, String id, String type ) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name must not be null or empty");
        this.name = name;
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("Id must not be null or empty");
        this.id = id;
        this.type = type;
    }

    public XPlan( String name, String id, String type, String version ) {
        this( name, id, type );
        this.version = version;
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

    public AdditionalPlanData getXplanMetadata() {
        return xplanMetadata;
    }

    public void setXplanMetadata( AdditionalPlanData xplanMetadata ) {
        this.xplanMetadata = xplanMetadata;
    }

    public Boolean isInspirePublished() {
        return inspirePublished;
    }

    public void setInspirePublished( Boolean inspirePublished ) {
        this.inspirePublished = inspirePublished;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId( String internalId ) {
        this.internalId = internalId;
    }

    public void setHasMultipleXPlanElements( boolean hasMultipleXPlanElements ) {
        this.hasMultipleXPlanElements = hasMultipleXPlanElements;
    }

    public boolean isHasMultipleXPlanElements() {
        return this.hasMultipleXPlanElements;
    }

    @Override
    public int compareTo( XPlan o ) {
        return ( o == null || o.name == null ) ? -1 : -o.name.compareTo( name );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XPlan xPlan = (XPlan) o;

        if (!id.equals(xPlan.id)) return false;
        return name.equals(xPlan.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "XPlan[" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", version='" + version + '\'' +
                ']';
    }
}
