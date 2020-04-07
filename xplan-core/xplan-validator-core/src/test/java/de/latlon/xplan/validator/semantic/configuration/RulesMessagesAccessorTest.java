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
package de.latlon.xplan.validator.semantic.configuration;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class RulesMessagesAccessorTest {

    @Test
    public void testRetrieveMessageForRule_KnownRuleWithoutVersion() {
        String message = RulesMessagesAccessor.retrieveMessageForRule( "3.1.2.3", null );
        assertThat( message, is( "Regel 3.1.2.3 muss erf\u00FCllt sein" ) );
    }

    @Test
    public void testRetrieveMessageForRule_UnknownRuleWithoutVersion() {
        String message = RulesMessagesAccessor.retrieveMessageForRule( "unknownRule", null );
        assertThat( message, notNullValue() );
    }

    @Test
    public void testRetrieveMessageForRule_KnownRuleWithVersion() {
        String message = RulesMessagesAccessor.retrieveMessageForRule( "3.1.2.3", XPLAN_51 );
        assertThat( message, is( "XP_Bereich: Relation auf Basis-Rasterplan" ) );
    }

    @Test
    public void testRetrieveMessageForRule_UnknownRuleWithVersion() {
        String message = RulesMessagesAccessor.retrieveMessageForRule( "unknownRule", XPLAN_51 );
        assertThat( message, notNullValue() );
    }

    @Test
    public void testRetrieveMessageForRule_KnownRuleWithVersionInKey() {
        String message = RulesMessagesAccessor.retrieveMessageForRule( "3.1.3.1", XPLAN_3 );
        assertThat( message, is( "Regel 3.1.3.1 muss erf\u00FCllt sein" ) );
    }

    @Test
    public void testRetrieveMessageForRule_EmptyRule() {
        String message = RulesMessagesAccessor.retrieveMessageForRule( "", null );
        assertThat( message, notNullValue() );
    }

    @Test
    public void testRetrieveMessageForRule_NullRule() {
        String message = RulesMessagesAccessor.retrieveMessageForRule( null, null );
        assertThat( message, notNullValue() );
    }

}