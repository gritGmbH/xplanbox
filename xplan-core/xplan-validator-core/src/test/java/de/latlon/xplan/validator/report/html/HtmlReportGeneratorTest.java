package de.latlon.xplan.validator.report.html;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.junit.Test;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;

public class HtmlReportGeneratorTest {

    @Test
    public void testGenerateHtmlReport()
                            throws Exception {
        ByteArrayOutputStream html = new ByteArrayOutputStream();
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

        htmlReportGenerator.generateHtmlReport( createReport( "validationName", "planName" ), html );

        assertThat( document( html ), hasXPath( "/html/body/h1", containsString( "Validierungsbericht" ) ) );
    }

    @Test
    public void testGenerateHtmlReport_WithSemanticFailures()
                            throws Exception {
        ByteArrayOutputStream html = new ByteArrayOutputStream();
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

        htmlReportGenerator.generateHtmlReport( createValidatorReportWithSemanticFailures(), html );

        assertThat( document( html ), hasXPath( "/html/body/p[5]/p/ul/li[1]", containsString( "2 Validierungsregeln" ) ) );
        assertThat( document( html ), hasXPath( "/html/body/p[5]/p/ul/li[2]", containsString( "1 Validierungsregeln nicht" ) ) );
        assertThat( document( html ), hasXPath( "/html/body/p[5]/p/ul/li[3]", containsString( "1 Validierungsregeln" ) ) );
    }

    @Test
    public void testGenerateHtmlReport_WithSyntacticDetailHint()
                            throws Exception {
        ByteArrayOutputStream html = new ByteArrayOutputStream();
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

        htmlReportGenerator.generateHtmlReport( createValidatorReportWithSyntacticDetailHint(), html );

        assertThat( document( html ), hasXPath( "/html/body/p/div", containsString( "detailsHint" ) ) );
    }

    @Test
    public void testGenerateHtmlReport_WithGeometricWarnings()
                            throws Exception {
        ByteArrayOutputStream html = new ByteArrayOutputStream();
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

        htmlReportGenerator.generateHtmlReport( createValidatorReportWithGeometricWarnings(), html );

        assertThat( document( html ), hasXPath( "/html/body/p[5]/p[2]", containsString( "1 Warnungen" ) ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullReport()
                            throws Exception {
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();
        htmlReportGenerator.generateHtmlReport( null, createSimpleStream() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullOutputStream()
                            throws Exception {
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();
        htmlReportGenerator.generateHtmlReport( createReport( "validationName", "archiveName" ), null );
    }

    private static StreamSource document( ByteArrayOutputStream html ) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream( html.toByteArray() );
        return new StreamSource( inputStream );
    }

    private ValidatorReport createReport( String validationName, String planName ) {
        ValidatorReport report = new ValidatorReport();
        report.setValidationName( validationName );
        report.setPlanName( planName );
        return report;
    }

    private ValidatorReport createValidatorReportWithSemanticFailures() {
        ValidatorReport validatorReport = new ValidatorReport();
        validatorReport.setPlanName( "PLAN_NAME" );
        validatorReport.setValidationName( "VALIDATION_NAME" );
        List<String> messages = Collections.singletonList( "Error in xml..." );
        ValidatorDetail detail = new ValidatorDetail( "detailsHint" );
        SemanticValidatorResult semanticValidatorResult = new SemanticValidatorResult();
        semanticValidatorResult.addRule( "1.1", true, "Test valid" );
        semanticValidatorResult.addRule( "1.2", false, "Test valid" );
        validatorReport.setSemanticValidatorResult( semanticValidatorResult );
        return validatorReport;
    }

    private ValidatorReport createValidatorReportWithSyntacticDetailHint() {
        ValidatorReport validatorReport = new ValidatorReport();
        validatorReport.setPlanName( "PLAN_NAME" );
        validatorReport.setValidationName( "VALIDATION_NAME" );
        List<String> messages = Collections.singletonList( "Error in xml..." );
        ValidatorDetail detail = new ValidatorDetail( "detailsHint" );
        SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult( messages, false, detail );
        validatorReport.setSyntacticValidatorResult( syntacticValidatorResult );
        return validatorReport;
    }

    private ValidatorReport createValidatorReportWithGeometricWarnings() {
        ValidatorReport validatorReport = new ValidatorReport();
        validatorReport.setPlanName( "PLAN_NAME" );
        validatorReport.setValidationName( "VALIDATION_NAME" );
        List<String> warnings = Collections.singletonList( "Warning..." );
        List<String> errors = Collections.singletonList( "Error..." );
        List<BadGeometry> badGeometries = Collections.emptyList();
        GeometricValidatorResult geometricValidatorResult = new GeometricValidatorResult( warnings, errors,
                                                                                          badGeometries, null, false );
        validatorReport.setGeometricValidatorResult( geometricValidatorResult );
        return validatorReport;
    }

    private ByteArrayOutputStream createSimpleStream() {
        return new ByteArrayOutputStream();
    }

}