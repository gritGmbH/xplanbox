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
package de.latlon.xplan.manager.codelists;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URL;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;

import de.latlon.xplan.commons.XPlanVersion;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class XPlanCodeListsTest {

    @Test
    public void testGetDescription() {
        XPlanVersion version = XPlanVersion.XPLAN_41;
        XPlanCodeLists xPlanCodeLists = XPlanCodeListsFactory.get( version );
        String legislationStatusTranslation = xPlanCodeLists.getDescription( "BP_Rechtsstand", "4000" );

        assertThat( legislationStatusTranslation, is( "InkraftGetreten" ) );
    }

    @Test
    public void testParseOld()
                            throws Exception {
        URL codeListFile = XPlanCodeListsTest.class.getResource( "../synthesizer/XP_BesondereArtDerBaulNutzung.xml" );
        XPlanCodeLists codeLists = new XPlanCodeLists( codeListFile );

        Map<String, Map<String, String>> codesToDescriptions = codeLists.getCodesToDescriptions();
        assertThat( codesToDescriptions.size(), is( 1 ) );
        assertThat( codesToDescriptions.values().iterator().next().size(), is( 14 ) );
    }

    @Ignore
    @Test
    public void testParseNew()
                            throws Exception {
        URL codeListFile = XPlanCodeListsTest.class.getResource( "../synthesizer/XP_BesondereArtDerBaulNutzung_neu.xml" );
        XPlanCodeLists codeLists = new XPlanCodeLists( codeListFile );

        Map<String, Map<String, String>> codesToDescriptions = codeLists.getCodesToDescriptions();
        assertThat( codesToDescriptions.size(), is( 1 ) );
        assertThat( codesToDescriptions.values().iterator().next().size(), is( 3 ) );
    }

}