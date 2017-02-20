package de.latlon.xplan.validator.syntactic;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

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
    public void testBP2070XPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/BP2070.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        validator.validateSyntax( archive );
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testBP2135XPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/BP2135.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testFPlanXPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/FPlan.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );

    }

    @Test
    public void testLA22XPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/LA22.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testLA67XPlan2()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan2/LA67.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testBP2070XPlan3()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan3/BP2070.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testBP2135XPlan3()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan3/BP2135.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testFPlanXPlan3()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan3/FPlan.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testBP2070XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/BP2070.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testBP2135XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/BP2135.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testDemoXPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/Demo.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testEidelstedt_4_V4XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/Eidelstedt_4_V4.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testFPlanXPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/FPlan.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testLA22XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/LA22.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );

    }

    @Test
    public void testLA67XPlan41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/LA67.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );

    }

    @Test
    public void testV4_1_ID_103_41()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan41/V4_1_ID_103.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

    @Test
    public void testV4_1_ID_66_40()
                    throws IOException, URISyntaxException {
        XPlanArchive archive = getTestArchive( "xplan40/V4_1_ID_66.zip" );
        SyntacticValidator validator = new SyntacticValidatorImpl();
        assertTrue( validator.validateSyntax( archive ).isValid() );
    }

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
                    throws IOException, URISyntaxException {
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
