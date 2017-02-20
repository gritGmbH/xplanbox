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
package de.latlon.xplan.manager.web.client.gui.widget;

import static de.latlon.xplan.manager.web.client.utils.DateTimeUtils.parseDateStrict;

import com.google.gwt.user.datepicker.client.DateBox;

import de.latlon.xplan.manager.web.client.utils.DateTimeUtils;

/**
 * {@link DateBox} implementation being strict by by parsing the input as date. Component can validate himself by
 * implementing {@link Validable}.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class StrictDateBox extends DateBox implements Validable {

    private StrictDateBoxFormat dateBoxFormat;

    /**
     * @param dateBoxFormat
     *            the {@link StrictDateBoxFormat} to use for validation
     */
    public StrictDateBox( StrictDateBoxFormat dateBoxFormat ) {
        setFormat( dateBoxFormat );
    }

    @Override
    public void setFormat( Format format ) {
        if ( !( format instanceof StrictDateBoxFormat ) )
            throw new IllegalArgumentException( "Format must be a StrictDateBoxFormat" );
        super.setFormat( format );
        this.dateBoxFormat = (StrictDateBoxFormat) format;
    }

    @Override
    public boolean isValid() {
        String text = DateTimeUtils.retrieveDate( this );
        try {
            parseDateStrict( dateBoxFormat.getFormat(), text );
        } catch ( Exception e ) {
            return false;
        }
        return true;
    }

}