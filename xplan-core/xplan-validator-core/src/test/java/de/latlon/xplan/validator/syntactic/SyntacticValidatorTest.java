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
package de.latlon.xplan.validator.syntactic;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;

import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

/**
 * Tests for <link>SyntacticValidatorImpl</link>
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class SyntacticValidatorTest {

    @Test
    public void testValidateSyntaxWithXPlanNSM()
                            throws Exception {
        XPlanArchive archive = getTestArchive( "xplan41/nsm/nsm_niedersachsen_lrop_small.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        SyntacticValidatorResult result = (SyntacticValidatorResult) validator.validateSyntax( archive );
        assertThat( result.isValid(), is( true ) );
        assertThat( result.getValidatorDetail(), nullValue() );
    }

    @Test
    public void testEidelstedt_4_V4XPlan41_Syntaxfehler()
                            throws IOException {
        XPlanArchive archive = getTestArchive( "xplan41/Eidelstedt_4_V4_Syntaxfehler.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        SyntacticValidatorResult result = (SyntacticValidatorResult) validator.validateSyntax( archive );
        assertThat( result.isValid(), is( false ) );
        assertThat( result.getMessages().size(), is( 4 ) );
        assertThat( result.getMessages().get( 0 ), allOf( containsString( "Zeile" ), containsString( "Spalte" ) ) );
        assertThat( result.getValidatorDetail(), notNullValue() );
    }

    private XPlanArchive getTestArchive( String name )
                            throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchiveFromZip( name, ResourceAccessor.readResourceStream( name ) );
    }

}
