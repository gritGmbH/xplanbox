/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.report;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * Encapsulates some details about the validator result.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class ValidatorDetail {

    private final String detailsString;

    private final String link;

    /**
     * @param detailsString
     *            the text value of this details, never <code>null</code>
     */
    public ValidatorDetail( String detailsString ) {
        this( detailsString, null );
    }

    /**
     * @param detailsString
     *            the text value of this details, the placeholder %s indicates the link, never <code>null</code>
     * @param link
     *            a link specified in the details string with %s, may be <code>null</code>
     * @throws NullPointerException
     *             if detailsString is <code>null</code>
     * @throws IllegalArgumentException
     *             if the detailsString contains a %s but no link is passed or detailsString does not contain a %s but a
     *             link is passed
     * 
     */
    public ValidatorDetail( String detailsString, String link ) {
        check( detailsString, link );
        this.detailsString = detailsString;
        this.link = link;
    }

    /**
     * @return the text value of this details, resources are formatted as %s, never <code>null</code>
     */
    public String getDetailsString() {
        return detailsString;
    }

    /**
     * @return a list of links specified in the details string wit %s, the order must be the same as the %s order in
     *         details string, may be empty but never <code>null</code>
     */
    public String getLink() {
        return link;
    }

    public List<DetailPart> getDetailParts() {
        List<DetailPart> detailParts = new ArrayList<>();
        String trimmedDetailsString = detailsString.trim();
        if ( link != null && !link.isEmpty() ) {
            LinkPart linkPart = new LinkPart( link );
            if ( trimmedDetailsString.startsWith( "%s" ) ) {
                detailParts.add( linkPart );
                String endString = trimmedDetailsString.substring( 2, trimmedDetailsString.length() ).trim();
                if ( !"".equals( endString ) )
                    detailParts.add( new StringPart( endString ) );
            } else if ( trimmedDetailsString.endsWith( "%s" ) ) {
                String startString = trimmedDetailsString.substring( 0, trimmedDetailsString.length() - 2 ).trim();
                if ( !"".equals( startString ) )
                    detailParts.add( new StringPart( startString ) );
                detailParts.add( linkPart );
            } else {
                int indexOfPlaceholder = trimmedDetailsString.indexOf( "%s" );
                String startString = trimmedDetailsString.substring( 0, indexOfPlaceholder ).trim();
                String endString = trimmedDetailsString.substring( indexOfPlaceholder + 2,
                                                                   trimmedDetailsString.length() ).trim();
                if ( !"".equals( startString ) )
                    detailParts.add( new StringPart( startString ) );
                detailParts.add( linkPart );
                if ( !"".equals( endString ) )
                    detailParts.add( new StringPart( endString ) );
            }
            return detailParts;
        } else {
            detailParts.add( new StringPart( trimmedDetailsString ) );
        }
        return detailParts;
    }

    @Override
    public String toString() {
        if ( link != null && !"".equals( link ) )
            return String.format( detailsString, link );
        return detailsString;
    }

    private void check( String detailsString, String link ) {
        if ( detailsString == null )
            throw new NullPointerException( "detailsString must not be null" );
        int matchesInDetailsString = StringUtils.countMatches( detailsString, "%s" );
        boolean linkIsSet = link != null && !"".equals( link );
        if ( linkIsSet && matchesInDetailsString != 1 )
            throw new IllegalArgumentException(
                            "%s in detailsString must occur exactly one time if a link is specified" );
        if ( !linkIsSet && matchesInDetailsString > 0 )
            throw new IllegalArgumentException( "%s in detailsString is not allowed if no link is specified" );
    }

    public abstract class DetailPart {

        private final String detailPart;

        public DetailPart( String detailPart ) {
            this.detailPart = detailPart;
        }

        public String getDetailPart() {
            return detailPart;
        }

        @Override
        public String toString() {
            return "DetailPart [detailPart=" + detailPart + ", isLink()=" + isLink() + "]";
        }

        public abstract boolean isLink();

    }

    private class StringPart extends DetailPart {

        public StringPart( String detailPart ) {
            super( detailPart );
        }

        @Override
        public boolean isLink() {
            return false;
        }

    }

    private class LinkPart extends DetailPart {

        public LinkPart( String detailPart ) {
            super( detailPart );
        }

        @Override
        public boolean isLink() {
            return true;
        }
    }

}
