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

    public RasterReference() {
    }

    /**
     * Instantiates a new {@link RasterReference} as copy of the passed.
     *
     * @param rasterReference
     *                         to copy, never <code>null</code>
     */
    public RasterReference( RasterReference rasterReference ) {
        this( rasterReference.getFeatureId(), rasterReference.getReference(), rasterReference.getGeoReference(),
              rasterReference.getType(), rasterReference.getGeorefMimeType(), rasterReference.getArt(),
              rasterReference.getInformationssystemURL(), rasterReference.getReferenzName(),
              rasterReference.getReferenzMimeType(), rasterReference.getBeschreibung(), rasterReference.getDatum() );
    }

    /**
     * @param reference
     *                         reference, may be <code>null</code>
     * @param geoReference
     *                         geoReference, may be <code>null</code>
     * @param type
     *                         type, should not be <code>null</code>
     */
    public RasterReference( String reference, String geoReference, RasterReferenceType type, MimeTypes georefMimeType,
                            ExterneReferenzArt art, String informationssystemURL, String referenzName,
                            MimeTypes referenzMimeType, String beschreibung, Date datum ) {
        this( null, reference, geoReference, type, georefMimeType, art, informationssystemURL, referenzName,
              referenzMimeType, beschreibung, datum );
    }

    /**
     * @param featureId
     *                         the id of the feature containing this reference, may be <code>null</code>
     * @param reference
     *                         reference, may be <code>null</code>
     * @param geoReference
     *                         geoReference, may be <code>null</code>
     * @param type
     *                         type, should not be <code>null</code>
     */
    public RasterReference( String featureId, String reference, String geoReference, RasterReferenceType type,
                            MimeTypes georefMimeType, ExterneReferenzArt art, String informationssystemURL,
                            String referenzName, MimeTypes referenzMimeType, String beschreibung, Date datum ) {
        super( reference, geoReference, georefMimeType, art, informationssystemURL, referenzName, referenzMimeType,
               beschreibung, datum );
        this.featureId = featureId;
        this.type = type;
    }

    /**
     * @return the type, may be <code>null</code>
     */
    public RasterReferenceType getType() {
        return type;
    }

    /**
     * @param type
     *                         the type to set, may be <code>null</code>
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
     *                         the id of the feature containing this reference, may be <code>null</code>
     */
    public void setFeatureId( String featureId ) {
        this.featureId = featureId;
    }

    @Override
    public String toString() {
        return "RasterReference{" + "type=" + type + ", featureId='" + featureId + '\'' + '}';
    }
}
