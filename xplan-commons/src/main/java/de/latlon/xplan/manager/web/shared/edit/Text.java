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

/**
 * Encapsulates the text of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class Text extends AbstractReference {

    private String featureId;

    private String key;

    private String basis;

    private String text;

    private int legalNatureCode = -1;

    public Text() {
    }

    /**
     * @param featureId
     *                 id of the feature member, never <code>null</code>
     */
    public Text( String featureId ) {
        this.featureId = featureId;
    }

    /**
     * @param featureId
     *                 id of the feature member, never <code>null</code>
     * @param key
     *                 key, may be <code>null</code>
     * @param basis
     *                 basis, may be <code>null</code>
     * @param text
     *                 text, may be <code>null</code>
     * @param reference
     *                 reference, may be <code>null</code>
     * @param geoReference
     *                 geoReference, may be <code>null</code>
     */
    public Text( String featureId, String key, String basis, String text, String reference, String geoReference ) {
        super( reference, geoReference );
        this.featureId = featureId;
        this.key = key;
        this.basis = basis;
        this.text = text;
    }

    /**
     * @param featureId
     *                 id of the feature member, never <code>null</code>
     * @param key
     *                 key, may be <code>null</code>
     * @param basis
     *                 basis, may be <code>null</code>
     * @param text
     *                 text, may be <code>null</code>
     * @param reference
     *                 reference, may be <code>null</code>
     * @param geoReference
     *                 geoReference, may be <code>null</code>
     * @param legalNatureCode
     *                 legalNatureCode, may be <code>null</code>
     */
    public Text( String featureId, String key, String basis, String text, int legalNatureCode, String reference,
                 String geoReference ) {
        super( reference, geoReference );
        this.featureId = featureId;
        this.key = key;
        this.basis = basis;
        this.text = text;
        this.legalNatureCode = legalNatureCode;
    }

    /**
     * @return the key, may be <code>null</code>
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *                 the key to set, may be <code>null</code>
     */
    public void setKey( String key ) {
        this.key = key;
    }

    /**
     * @return the basis, may be <code>null</code>
     */
    public String getBasis() {
        return basis;
    }

    /**
     * @param basis
     *                 the basis to set, may be <code>null</code>
     */
    public void setBasis( String basis ) {
        this.basis = basis;
    }

    /**
     * @return the text, may be <code>null</code>
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *                 the text to set, may be <code>null</code>
     */
    public void setText( String text ) {
        this.text = text;
    }

    /**
     * @return the id of the feature member describing this text, may be <code>null</code>
     */
    public String getFeatureId() {
        return featureId;
    }

    /**
     * @param featureId
     *                 the id of the feature member describing this text, may be <code>null</code>
     */
    public void setFeatureId( String featureId ) {
        this.featureId = featureId;
    }

    /**
     * @return the legalNature, may be <code>null</code>
     */
    public int getLegalNatureCode() {
        return legalNatureCode;
    }

    /**
     * @param legalNatureCode
     *                 the legalNature to set, may be <code>null</code>
     */
    public void setLegalNatureCode( int legalNatureCode ) {
        this.legalNatureCode = legalNatureCode;
    }

    @Override
    public String toString() {
        return "Text [featureId=" + featureId + ", key=" + key + ", basis=" + basis + ", text=" + text
               + ", legalNatureCode= " + legalNatureCode + "]";
    }

}