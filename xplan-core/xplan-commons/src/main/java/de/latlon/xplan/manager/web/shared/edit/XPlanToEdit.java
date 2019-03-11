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

import java.util.ArrayList;
import java.util.List;

/**
 * Summarizes all properties of a plan to edit.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class XPlanToEdit {

    private BaseData baseData;

    private ValidityPeriod validityPeriod;

    private List<Change> changes;

    private List<Text> texts;

    private List<Reference> references;

    private RasterBasis rasterBasis;

    public XPlanToEdit() {
    }

    /**
     * @param baseData
     *            may be <code>null</code>
     * @param validityPeriod
     *            may be <code>null</code>
     * @param changes
     *            may be <code>null</code>
     * @param texts
     *            may be <code>null</code>
     * @param references
     *            may be <code>null</code>
     * @param rasterBasis
     *            may be <code>null</code>
     */
    public XPlanToEdit( BaseData baseData, ValidityPeriod validityPeriod, List<Change> changes, List<Text> texts,
                        List<Reference> references, RasterBasis rasterBasis ) {
        this.baseData = baseData;
        this.validityPeriod = validityPeriod;
        this.changes = changes;
        this.texts = texts;
        this.references = references;
        this.rasterBasis = rasterBasis;
    }

    /**
     * @return base data of the plan, never <code>null</code>
     */
    public BaseData getBaseData() {
        if ( this.baseData == null )
            this.baseData = new BaseData();
        return baseData;
    }

    /**
     * @param baseData
     *            baseData to set, may be <code>null</code>
     */
    public void setBaseData( BaseData baseData ) {
        this.baseData = baseData;
    }

    /**
     * @return the validityPeriod, never <code>null</code>
     */
    public ValidityPeriod getValidityPeriod() {
        if ( this.validityPeriod == null )
            this.validityPeriod = new ValidityPeriod();
        return validityPeriod;
    }

    /**
     * @param validityPeriod
     *            the validityPeriod to set, may be <code>null</code>
     */
    public void setValidityPeriod( ValidityPeriod validityPeriod ) {
        this.validityPeriod = validityPeriod;
    }

    /**
     * @return the changes, never <code>null</code>
     */
    public List<Change> getChanges() {
        if ( changes == null )
            changes = new ArrayList<Change>();
        return changes;
    }

    /**
     * @param changes
     *            the changes to set, may be <code>null</code>
     */
    public void setChanges( List<Change> changes ) {
        this.changes = changes;
    }

    /**
     * @param change
     *            to add, may be <code>null</code> (nothing is added)
     */
    public void addChange( Change change ) {
        if ( change != null )
            getChanges().add( change );
    }

    /**
     * @return the text, never <code>null</code>
     */
    public List<Text> getTexts() {
        if ( texts == null )
            texts = new ArrayList<Text>();
        return texts;
    }

    /**
     * @param texts
     *            the text to set, may be <code>null</code>
     */
    public void setTexts( List<Text> texts ) {
        this.texts = texts;
    }

    /**
     * @param text
     *            to add, may be <code>null</code> (nothing is added)
     */
    public void addText( Text text ) {
        if ( text != null )
            getTexts().add( text );
    }

    /**
     * @return the references, never <code>null</code>
     */
    public List<Reference> getReferences() {
        if ( references == null )
            references = new ArrayList<Reference>();
        return references;
    }

    /**
     * @param references
     *            the references to set, may be <code>null</code>
     */
    public void setReferences( List<Reference> references ) {
        this.references = references;
    }

    /**
     * @param reference
     *            to add, may be <code>null</code> (nothing is added)
     */
    public void addReference( Reference reference ) {
        if ( reference != null )
            getReferences().add( reference );
    }

    /**
     * @return the rasterBasis, may be <code>null</code>
     */
    public RasterBasis getRasterBasis() {
        return rasterBasis;
    }

    /**
     * @param rasterBasis
     *            the rasterBasis to set, may be <code>null</code>
     */
    public void setRasterBasis( RasterBasis rasterBasis ) {
        this.rasterBasis = rasterBasis;
    }

    @Override
    public String toString() {
        return "XPlanToEdit [baseData=" + baseData + ", validityPeriod=" + validityPeriod + ", changes=" + changes
               + ", texts=" + texts + ", references=" + references + ", rasterBasis=" + rasterBasis + "]";
    }

}