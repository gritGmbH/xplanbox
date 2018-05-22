package de.latlon.xplan.validator.semantic.xquery;

import static org.deegree.commons.xml.stax.XMLStreamUtils.skipStartDocument;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.junit.Test;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
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
        Path xqueryFilePath = XPlanRules.retrieveInternalRulesPath( "xplangml41/4.1.2.1.xq" );
        XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
                                                                                                                     xqueryFilePath );
        SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator( retriever );
        ValidatorResult result = xQuerySemanticValidator.validateSemantic( mockArchive( "xplan.gml" ),
                                                                           Collections.emptyList() );
        SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

        List<RuleResult> rules = semanticValidatorResult.getRules();

        assertThat( rules.size(), is( 1 ) );
        assertThat( rules.get( 0 ).isValid(), is( true ) );
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
