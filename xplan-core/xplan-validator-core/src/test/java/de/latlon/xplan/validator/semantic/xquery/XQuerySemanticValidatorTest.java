package de.latlon.xplan.validator.semantic.xquery;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.SemanticConformityLinkConfiguration;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;

/**
 * Tests for <link>XQuerySemanticValidator</link>
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
@RunWith(JUnitParamsRunner.class)
public class XQuerySemanticValidatorTest {

    @Parameters({ "xplan41/BP2070.zip,7 ", "xplan41/nsm/nsm_niedersachsen_lrop_small.zip,7", "xplan50/BP2070.zip,4", "xplan51/BP2070.zip,4" })
    @Test
    public void testValidateSemantic( String testResource, int expectedNumberOfRules )
                            throws Exception {
        Path xqueryFilePath = pathToSampleRules();
        XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
                                                                                                                     xqueryFilePath );
        SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator( retriever );
        ValidatorResult result = xQuerySemanticValidator.validateSemantic( getTestArchive( testResource ),
                                                                           Collections.emptyList() );
        SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

        assertThat( semanticValidatorResult.getRules().size(), is( expectedNumberOfRules ) );
    }

    @Test
    public void testValidatorResultContainsDetailsHint()
                            throws Exception {
        Path xqueryFilePath = pathToSampleRules();
        XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
                                                                                                                     xqueryFilePath );
        SemanticConformityLinkConfiguration linkConfig = new SemanticConformityLinkConfiguration();
        linkConfig.addLink( XPLAN_41, "link" );
        SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator( retriever, linkConfig );
        ValidatorResult result = xQuerySemanticValidator.validateSemantic( getTestArchive( "xplan41/nsm/nsm_niedersachsen_lrop_small.zip" ),
                                                                           Collections.emptyList() );
        SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

        assertThat( semanticValidatorResult.getValidatorDetail().getDetailsString(), is( notNullValue() ) );
        assertThat( semanticValidatorResult.getValidatorDetail().getLink(), is( "link" ) );
    }

    @Test
    public void testValidatorResultWithoutLinkNotContainsDetailsHint()
                            throws Exception {
        Path xqueryFilePath = pathToSampleRules();
        XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
                                                                                                                     xqueryFilePath );
        SemanticConformityLinkConfiguration linkConfig = new SemanticConformityLinkConfiguration();
        linkConfig.addLink( XPLAN_40, "link" );
        SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator( retriever, linkConfig );
        ValidatorResult result = xQuerySemanticValidator.validateSemantic( getTestArchive( "xplan41/nsm/nsm_niedersachsen_lrop_small.zip" ),
                                                                           Collections.emptyList() );
        SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

        assertThat( semanticValidatorResult.getValidatorDetail(), is( nullValue() ) );
    }

    @Test
    public void testValidatorResultWithoutLinkConfigNotContainsDetailsHint()
                            throws Exception {
        Path xqueryFilePath = pathToSampleRules();
        XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
                                                                                                                     xqueryFilePath );
        SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator( retriever );
        ValidatorResult result = xQuerySemanticValidator.validateSemantic( getTestArchive( "xplan41/nsm/nsm_niedersachsen_lrop_small.zip" ),
                                                                           Collections.emptyList() );
        SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

        assertThat( semanticValidatorResult.getValidatorDetail(), is( nullValue() ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateSemanticWithNullArchiveShouldFail()
                            throws Exception {
        Path xqueryFilePath = pathToSampleRules();
        XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
                                                                                                                     xqueryFilePath );
        SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator( retriever );
        xQuerySemanticValidator.validateSemantic( null, Collections.emptyList() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateSemanticWithOptionsArchiveShouldFail()
                            throws Exception {
        Path xqueryFilePath = pathToSampleRules();
        XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
                                                                                                                     xqueryFilePath );
        SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator( retriever );
        xQuerySemanticValidator.validateSemantic( getTestArchive( "xplan41/BP2070.zip" ), null );
    }

    private XPlanArchive getTestArchive( String name )
                            throws Exception {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

    private Path pathToSampleRules()
                            throws URISyntaxException {
        return Paths.get( XQuerySemanticValidatorTest.class.getResource( "../configuration/xquery/rules" ).toURI() );
    }

}
