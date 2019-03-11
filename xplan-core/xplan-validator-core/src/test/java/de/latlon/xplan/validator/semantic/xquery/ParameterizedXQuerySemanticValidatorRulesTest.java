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

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

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
@Ignore
@RunWith(JUnitParamsRunner.class)
public class ParameterizedXQuerySemanticValidatorRulesTest {

    @FileParameters("src/test/resources/de/latlon/xplan/validator/semantic/xquery/validateSemanticRulesTest-input.csv")
    @Test
    public void testValidationOfSingleRule( String resourceUnderTest, String rulePath, boolean isValid )
                            throws Exception {
        List<RuleResult> rules = testRule( resourceUnderTest, rulePath );

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( isValid ) );
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
        InputStream xPlanGml = ParameterizedXQuerySemanticValidatorRulesTest.class.getResourceAsStream( resourceName );
        XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader( xPlanGml );
        skipStartDocument( xmlReader );
        when( mockedArchive.getMainFileXmlReader() ).thenReturn( xmlReader );
        return mockedArchive;
    }

}