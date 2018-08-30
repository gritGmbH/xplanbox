package de.latlon.xplan.validator.semantic.xquery;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(JUnitParamsRunner.class)
public class ParamerizedXQuerySemanticValidatorTest {

    @Parameters(method = "semanticTestData")
    @Test
    public void testValidateSemantic( String testResource, int expectedNumberOfRules )
                            throws Exception {
        Path xqueryFilePath = Paths.get( XQuerySemanticValidatorTest.class.getResource( "../configuration/xquery/rules" ).toURI() );
        XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
                                                                                                                     xqueryFilePath );
        SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator( retriever );
        ValidatorResult result = xQuerySemanticValidator.validateSemantic( getTestArchive( testResource ),
                                                                           Collections.emptyList() );
        SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

        assertThat( semanticValidatorResult.getRules().size(), is( expectedNumberOfRules ) );
    }

    private Object semanticTestData() {
        return new Object[] { new Object[] { "xplan41/BP2070.zip", 7 },
                             new Object[] { "xplan41/nsm/nsm_niedersachsen_lrop_small.zip", 7 } };
    }

    private XPlanArchive getTestArchive( String name )
                            throws Exception {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

}
