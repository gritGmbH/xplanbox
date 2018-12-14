//$HeadURL$
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.web.shared.edit;

import java.util.Date;

/**
 * Encapsulate a raster reference (refScan, refLegend, refText).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class RasterReference extends AbstractReference {

    private RasterReferenceType type;

    private String featureId;

    private MimeTypes georefMimeType;

    private ExterneReferenzArt art;

    private String informationssystemURL;

    private String referenzName;

    private MimeTypes referenzMimeType;

    private String beschreibung;

    private Date datum;

    public RasterReference() {
    }

    /**
     * Instantiates a new {@link RasterReference} as copy of the passed.
     *
     * @param rasterReference
     *                 to copy, never <code>null</code>
     */
    public RasterReference( RasterReference rasterReference ) {
        this( rasterReference.getFeatureId(), rasterReference.getReference(), rasterReference.getGeoReference(),
              rasterReference.getType(), rasterReference.getGeorefMimeType(), rasterReference.getArt(),
              rasterReference.getInformationssystemURL(), rasterReference.getReferenzName(),
              rasterReference.getReferenzMimeType(), rasterReference.getBeschreibung(), rasterReference.getDatum() );
    }

    /**
     * @param reference
     *                 reference, may be <code>null</code>
     * @param geoReference
     *                 geoReference, may be <code>null</code>
     * @param type
     *                 type, should not be <code>null</code>
     */
    public RasterReference( String reference, String geoReference, RasterReferenceType type, MimeTypes georefMimeType,
                            ExterneReferenzArt art, String informationssystemURL, String referenzName,
                            MimeTypes referenzMimeType, String beschreibung, Date datum ) {
        this( null, reference, geoReference, type, georefMimeType, art, informationssystemURL, referenzName,
              referenzMimeType, beschreibung, datum );
    }

    /**
     * @param featureId
     *                 the id of the feature containing this reference, may be <code>null</code>
     * @param reference
     *                 reference, may be <code>null</code>
     * @param geoReference
     *                 geoReference, may be <code>null</code>
     * @param type
     *                 type, should not be <code>null</code>
     */
    public RasterReference( String featureId, String reference, String geoReference, RasterReferenceType type,
                            MimeTypes georefMimeType, ExterneReferenzArt art, String informationssystemURL,
                            String referenzName, MimeTypes referenzMimeType, String beschreibung, Date datum ) {
        super( reference, geoReference );
        this.featureId = featureId;
        this.type = type;
        this.georefMimeType = georefMimeType;
        this.art = art;
        this.informationssystemURL = informationssystemURL;
        this.referenzName = referenzName;
        this.referenzMimeType = referenzMimeType;
        this.beschreibung = beschreibung;
        this.datum = datum;
    }

    /**
     * @return the type, may be <code>null</code>
     */
    public RasterReferenceType getType() {
        return type;
    }

    /**
     * @param type
     *                 the type to set, may be <code>null</code>
     */
    public void setType( RasterReferenceType type ) {
        this.type = type;
    }

    /**
     * @return the id of the feature containing this reference, may be <code>null</code>
     */
    public String getFeatureId() {
        return featureId;
    }

    /**
     * @param featureId
     *                 the id of the feature containing this reference, may be <code>null</code>
     */
    public void setFeatureId( String featureId ) {
        this.featureId = featureId;
    }

    /**
     * @return the mime type of the georeference, may be <code>null</code>
     */
    public MimeTypes getGeorefMimeType() {
        return georefMimeType;
    }

    /**
     * @param georefMimeType
     *                 the mime type of the georeference, may be <code>null</code>
     */
    public void setGeorefMimeType( MimeTypes georefMimeType ) {
        this.georefMimeType = georefMimeType;
    }

    /**
     * @return the type of this reference, may be <code>null</code>
     */
    public ExterneReferenzArt getArt() {
        return art;
    }

    /**
     * @param art
     *                 the type of this reference, may be <code>null</code>
     */
    public void setArt( ExterneReferenzArt art ) {
        this.art = art;
    }

    /**
     * @return the url, may be <code>null</code>
     */
    public String getInformationssystemURL() {
        return informationssystemURL;
    }

    /**
     * @param informationssystemURL
     *                 the url, may be <code>null</code>
     */
    public void setInformationssystemURL( String informationssystemURL ) {
        this.informationssystemURL = informationssystemURL;
    }

    /**
     * @return the name of the reference, may be <code>null</code>
     */
    public String getReferenzName() {
        return referenzName;
    }

    /**
     * @param referenzName
     *                 the name of the reference, may be <code>null</code>
     */
    public void setReferenzName( String referenzName ) {
        this.referenzName = referenzName;
    }

    /**
     * @return the mime type of the reference, may be <code>null</code>
     */
    public MimeTypes getReferenzMimeType() {
        return referenzMimeType;
    }

    /**
     * @param referenzMimeType
     *                 the mime type of the reference, may be <code>null</code>
     */
    public void setReferenzMimeType( MimeTypes referenzMimeType ) {
        this.referenzMimeType = referenzMimeType;
    }

    /**
     * @return the description, may be <code>null</code>
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * @param beschreibung
     *                 the description, may be <code>null</code>
     */
    public void setBeschreibung( String beschreibung ) {
        this.beschreibung = beschreibung;
    }

    /**
     * @return the date, may be <code>null</code>
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * @param datum
     *                 the date, may be <code>null</code>
     */
    public void setDatum( Date datum ) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "RasterReference{" + "type=" + type + ", featureId='" + featureId + '\'' + ", georefMimeType="
               + georefMimeType + ", art=" + art + ", informationssystemURL='" + informationssystemURL + '\''
               + ", referenzName='" + referenzName + '\'' + ", referenzMimeType=" + referenzMimeType
               + ", beschreibung='" + beschreibung + '\'' + ", datum=" + datum + '}';
    }

}