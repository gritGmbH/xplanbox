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
package de.latlon.xplan.validator.report;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import de.latlon.xplan.validator.report.ValidatorDetail.DetailPart;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class ValidatorDetailTest {

    @Test
    public void testGetDetailParts_startWithPlaceholder()
                    throws Exception {
        String detailsString = " %s abc ";
        ValidatorDetail validatorDetail = new ValidatorDetail( detailsString, "link1" );

        List<DetailPart> detailParts = validatorDetail.getDetailParts();
        assertThat( detailParts.size(), is( 2 ) );
        assertThat( detailParts.get( 0 ).getDetailPart(), is( "link1" ) );
        assertThat( detailParts.get( 0 ).isLink(), is( true ) );
        assertThat( detailParts.get( 1 ).getDetailPart(), is( "abc" ) );
        assertThat( detailParts.get( 1 ).isLink(), is( false ) );
    }

    @Test
    public void testGetDetailParts_endWithPlaceholder()
                    throws Exception {
        String detailsString = " def %s ";
        ValidatorDetail validatorDetail = new ValidatorDetail( detailsString, "link2" );

        List<DetailPart> detailParts = validatorDetail.getDetailParts();
        assertThat( detailParts.size(), is( 2 ) );
        assertThat( detailParts.get( 0 ).getDetailPart(), is( "def" ) );
        assertThat( detailParts.get( 0 ).isLink(), is( false ) );
        assertThat( detailParts.get( 1 ).getDetailPart(), is( "link2" ) );
        assertThat( detailParts.get( 1 ).isLink(), is( true ) );
    }

    @Test
    public void testGetDetailParts_withPlaceholderInMiddle()
                    throws Exception {
        String detailsString = " ghi %s jkl ";
        ValidatorDetail validatorDetail = new ValidatorDetail( detailsString, "link3" );

        List<DetailPart> detailParts = validatorDetail.getDetailParts();
        assertThat( detailParts.size(), is( 3 ) );
        assertThat( detailParts.get( 0 ).getDetailPart(), is( "ghi" ) );
        assertThat( detailParts.get( 0 ).isLink(), is( false ) );
        assertThat( detailParts.get( 1 ).getDetailPart(), is( "link3" ) );
        assertThat( detailParts.get( 1 ).isLink(), is( true ) );
        assertThat( detailParts.get( 2 ).getDetailPart(), is( "jkl" ) );
        assertThat( detailParts.get( 2 ).isLink(), is( false ) );
    }

    @Test
    public void testToString_withPlaceholder()
                    throws Exception {
        String detailsString = " ghi %s jkl ";
        ValidatorDetail validatorDetail = new ValidatorDetail( detailsString, "link3" );
        assertThat( validatorDetail.toString(), is( " ghi link3 jkl " ) );
    }

    @Test
    public void testToString_withoutPlaceholder()
                    throws Exception {
        String detailsString = " ghi jkl ";
        ValidatorDetail validatorDetail = new ValidatorDetail( detailsString );
        assertThat( validatorDetail.toString(), is( " ghi jkl " ) );
    }

    @Test(expected = NullPointerException.class)
    public void testConstructor_withNullDetailsString()
                    throws Exception {
        new ValidatorDetail( null );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_withPlaceholderButNoLinks()
                    throws Exception {
        new ValidatorDetail( "details %s", null );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_withoutPlaceholderButLink()
                    throws Exception {
        new ValidatorDetail( "details", "link" );
    }

}