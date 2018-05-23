package de.latlon.xplan.validator.semantic.xquery;

import static org.deegree.commons.xml.stax.XMLStreamUtils.skipStartDocument;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.junit.Test;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.rules.XPlanRules;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.report.RuleResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XQuerySemanticValidatorRulesTest {

    @Test
    public void testRule_41_4121()
                            throws Exception {
        List<RuleResult> rules = testRule( "xplan.gml", "xplangml41/4.1.2.1.xq" );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( true ) );
    }

    @Test
    public void testRule_41_4121_invalid()
                            throws Exception {
        List<RuleResult> rules = testRule( "xplan-invalid.gml", "xplangml41/4.1.2.1.xq" );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( false ) );
    }

    @Test
    public void testRule_41_423()
                            throws Exception {
        List<RuleResult> rules = testRule( "xplan.gml", "xplangml41/4.2.3.xq" );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( true ) );
    }

    @Test
    public void testRule_41_423_invalid()
                            throws Exception {
        List<RuleResult> rules = testRule( "xplan-invalid.gml", "xplangml41/4.2.3.xq" );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( false ) );
    }

    @Test
    public void testRule_41_429()
                            throws Exception {
        List<RuleResult> rules = testRule( "xplan.gml", "xplangml41/4.2.9.xq" );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( true ) );
    }

    @Test
    public void testRule_41_429_invalid()
                            throws Exception {
        List<RuleResult> rules = testRule( "xplan-invalid.gml", "xplangml41/4.2.9.xq" );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( false ) );
    }

    @Test
    public void testRule_41_45131()
                            throws Exception {
        List<RuleResult> rules = testRule( "xplan.gml", "xplangml41/4.5.13.1.xq" );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( true ) );
    }

    @Test
    public void testRule_41_451313_invalid_nurWeitereZweckbestimmung()
                            throws Exception {
        List<RuleResult> rules = testRule( "BP_NebenanlagenFlaeche-nurWeitereZB.xml",
                                           "xplangml41/4.5.13.1.xq" );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( false ) );
    }

    @Test
    public void testRule_41_451313_invalid_mehrfacheZweckbestimmungUndWeitere()
                            throws Exception {
        List<RuleResult> rules = testRule( "BP_NebenanlagenFlaeche-mehrfacheZBUndWeitere.xml", "xplangml41/4.5.13.1.xq" );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( false ) );
    }

    private List<RuleResult> testRule( String resourceUnderTest, String rulePath )
                            throws URISyntaxException, ValidatorException, XMLStreamException {
        Path xqueryFilePath = XPlanRules.retrieveInternalRulesPath( rulePath );
        XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
                                                                                                                     xqueryFilePath );
        SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator( retriever );
        ValidatorResult result = xQuerySemanticValidator.validateSemantic( mockArchive( resourceUnderTest ),
                                                                           Collections.emptyList() );
        SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

        return semanticValidatorResult.getRules();
    }

    private XPlanArchive mockArchive( String resourceName )
                            throws XMLStreamException {
        XPlanArchive mockedArchive = mock( XPlanArchive.class );
        when( mockedArchive.getVersion() ).thenReturn( XPlanVersion.XPLAN_41 );
        InputStream xPlanGml = XQuerySemanticValidatorRulesTest.class.getResourceAsStream( resourceName );
        XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader( xPlanGml );
        skipStartDocument( xmlReader );
        when( mockedArchive.getMainFileXmlReader() ).thenReturn( xmlReader );
        return mockedArchive;
    }

}
