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
 * Encapsulate a raster reference (raster plan change or raster base).
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class RasterWithReferences {

    private String featureId;

    private List<RasterReference> rasterReferences;

    public RasterWithReferences() {
    }

    public RasterWithReferences( String featureId ) {
        this( featureId, null );
    }

    public RasterWithReferences( String featureId, List<RasterReference> rasterReferences ) {
        this.featureId = featureId;
        this.rasterReferences = rasterReferences;
    }

    /**
     * @return the id of the feature containing the references, may be <code>null</code>
     */
    public String getFeatureId() {
        return featureId;
    }

    /**
     * 
     * @param featureId
     *            the id of the feature containing the references, may be <code>null</code>
     */
    public void setFeatureId( String featureId ) {
        this.featureId = featureId;
    }

    /**
     * @return the raster references, never <code>null</code>
     */
    public List<RasterReference> getRasterReferences() {
        if ( rasterReferences == null )
            rasterReferences = new ArrayList<RasterReference>();
        return rasterReferences;
    }

    /**
     * @param rasterReference
     *            to add, may be <code>null</code> (nothing is added)
     */
    public void addRasterReference( RasterReference rasterReference ) {
        if ( rasterReference != null )
            getRasterReferences().add( rasterReference );
    }

    /**
     * @param rasterReferences
     *            the raster references to set, may be <code>null</code>
     */
    public void setRasterReferences( List<RasterReference> rasterReferences ) {
        this.rasterReferences = rasterReferences;
    }

    @Override
    public String toString() {
        return "RasterWithReferences [featureId=" + featureId + ", rasterReferences=" + rasterReferences + "]";
    }

}