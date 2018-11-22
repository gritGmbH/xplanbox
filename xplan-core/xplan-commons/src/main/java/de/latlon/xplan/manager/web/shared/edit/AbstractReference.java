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
 * TODO add class documentation here
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public abstract class AbstractReference {

    private String reference;

    private String geoReference;

    public AbstractReference() {
    }

    /**
     * @param reference
     *            reference, may be <code>null</code>
     * @param geoReference
     *            reference, may be <code>null</code>
     */
    public AbstractReference( String reference, String geoReference ) {
        this.reference = reference;
        this.geoReference = geoReference;
    }

    /**
     * @return the reference, may be <code>null</code>
     */
    public String getReference() {
        return reference;
    }

    /**
     * @param reference
     *            the reference to set, may be <code>null</code>
     */
    public void setReference( String reference ) {
        this.reference = reference;
    }

    /**
     * @return the geoReference, may be <code>null</code>
     */
    public String getGeoReference() {
        return geoReference;
    }

    /**
     * @param geoReference
     *            the geoReference to set, may be <code>null</code>
     */
    public void setGeoReference( String geoReference ) {
        this.geoReference = geoReference;
    }

    @Override
    public String toString() {
        return "reference=" + reference + ", geoReference=" + geoReference;
    }

}