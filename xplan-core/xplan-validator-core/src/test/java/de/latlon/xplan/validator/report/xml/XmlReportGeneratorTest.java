package de.latlon.xplan.validator.report.xml;

import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.validator.report.ReportUtils.SkipCode.SYNTAX_ERRORS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.transform.XmlConverters.the;
import static org.xmlmatchers.xpath.XpathReturnType.returningANumber;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class XmlReportGeneratorTest {

    private static final String PLAN_NAME = "PLAN_NAME";

    private static final String VALIDATION_NAME = "VALIDATION_NAME";

    @Test
    public void testGenerateXmlReport()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        xmlReportGenerator.generateXmlReport( createValidatorReport(), os );
        String xml = os.toString();

        assertThat( the( xml ), hasXPath( "/ValidationReport/name", equalTo( VALIDATION_NAME ) ) );
        assertThat( the( xml ), hasXPath( "/ValidationReport/Plan/name", equalTo( PLAN_NAME ) ) );
        assertThat( the( xml ), hasXPath( "/ValidationReport/Validation" ) );
    }

    @Test
    public void testGenerateXmlReport_CheckSyntacticDetailsHint()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        xmlReportGenerator.generateXmlReport( createValidatorReportWithSyntacticDetailHint(), os );
        String xml = os.toString();

        assertThat( the( xml ), hasXPath( "/ValidationReport/Validation/Syn/details", equalTo( "detailsHint" ) ) );
    }

    @Test
    public void testGenerateXmlReport_CheckSemanticsResults()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        xmlReportGenerator.generateXmlReport( createValidatorReportWithSemanticFailures(), os );
        String xml = os.toString();

        assertThat( the( xml ), hasXPath( "/ValidationReport/Validation/Sem/result", equalTo( "nicht valide" ) ) );
        assertThat( the( xml ), hasXPath( "count(/ValidationReport/Validation/Sem/Rules/Rule)", returningANumber(),
                                               equalTo( 2d ) ) );
        assertThat( the( xml ),
                    hasXPath( "/ValidationReport/Validation/Sem/Rules/Rule[1]/name", equalTo( "1.1" ) ) );
        assertThat( the( xml ),
                    hasXPath( "/ValidationReport/Validation/Sem/Rules/Rule[2]/name", equalTo( "1.2" ) ) );
    }

    @Test
    public void testGenerateXmlReport_OrderOfValidations()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        xmlReportGenerator.generateXmlReport( createValidatorReportWithAllTypes(), os );
        String xml = os.toString();

        assertThat( the( xml ), hasXPath( "/ValidationReport/Validation/Sem" ) );
        assertThat( the( xml ), hasXPath( "/ValidationReport/Validation/*[local-name()= 'Sem']" ) );
        assertThat( the( xml ),
                    hasXPath( "count(/ValidationReport/Validation/*[local-name()= 'Sem']/preceding-sibling::*)+1.",
                              returningANumber(), equalTo( 1d ) ) );
        assertThat( the( xml ),
                    hasXPath( "count(/ValidationReport/Validation/*[local-name()= 'Geom']/preceding-sibling::*)+1.",
                              returningANumber(), equalTo( 2d ) ) );
        assertThat( the( xml ),
                    hasXPath( "count(/ValidationReport/Validation/*[local-name()= 'Syn']/preceding-sibling::*)+1.",
                              returningANumber(), equalTo( 3d ) ) );
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullReport()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        xmlReportGenerator.generateXmlReport( null, new ByteArrayOutputStream(  ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullOutputStream()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        xmlReportGenerator.generateXmlReport( createValidatorReport(), null );
    }

    private ValidatorReport createValidatorReport() {
        ValidatorReport validatorReport = new ValidatorReport();
        validatorReport.setPlanName( PLAN_NAME );
        validatorReport.setValidationName( VALIDATION_NAME );
        return validatorReport;
    }

    private ValidatorReport createValidatorReportWithSyntacticDetailHint() {
        ValidatorReport validatorReport = createValidatorReport();
        List<String> messages = Collections.singletonList( "Error in xml..." );
        ValidatorDetail detail = new ValidatorDetail( "detailsHint" );
        SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult( messages, false, detail );
        validatorReport.setSyntacticValidatorResult( syntacticValidatorResult );
        return validatorReport;
    }

    private ValidatorReport createValidatorReportWithSemanticFailures() {
        ValidatorReport validatorReport = createValidatorReport();
        SemanticValidatorResult semanticValidatorResult = new SemanticValidatorResult();
        semanticValidatorResult.addRule( "1.1", "Test valid", Collections.emptyList() );
        semanticValidatorResult.addRule( "1.2", "Test in valid", Collections.singletonList( "id_12" ) );
        validatorReport.setSemanticValidatorResult( semanticValidatorResult );
        return validatorReport;
    }

    private ValidatorReport createValidatorReportWithAllTypes() {
        ValidatorReport validatorReport = createValidatorReport();
        SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult( Collections.emptyList(), true,
                                                                                          null );
        validatorReport.setSyntacticValidatorResult( syntacticValidatorResult );

        SemanticValidatorResult semanticValidatorResult = new SemanticValidatorResult();
        semanticValidatorResult.addRule( "1.1", "Test valid", Collections.emptyList() );
        semanticValidatorResult.addRule( "1.2", "Test in valid", Collections.singletonList( "id_12" ) );
        validatorReport.setSemanticValidatorResult( semanticValidatorResult );

        GeometricValidatorResult geometricValidatorResult = new GeometricValidatorResult( SYNTAX_ERRORS );
        validatorReport.setGeometricValidatorResult( geometricValidatorResult );
        return validatorReport;
    }

}