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
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

}
