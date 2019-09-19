package de.latlon.xplan.validator.report.html;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.xmlmatchers.XmlMatchers.hasXPath;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

public class HtmlReportGeneratorTest {

    @Test
    public void testGenerateHtmlReport()
                    throws Exception {
        ByteArrayOutputStream html = new ByteArrayOutputStream();
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

        htmlReportGenerator.generateHtmlReport( mockReport("validationName", "planName") , html );

        assertThat( document( html ), hasXPath( "/html/body/h1", containsString( "Validierungsbericht" ) ) );
    }

    @Test
    public void testGenerateHtmlReport_WithSyntacticDetailHint()
                    throws Exception {
        ByteArrayOutputStream html = new ByteArrayOutputStream();
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

        htmlReportGenerator.generateHtmlReport( mockValidatorReportWithSyntacticDetailHint(), html );
        assertThat( document( html ), hasXPath( "/html/body/p/div", containsString( "detailsHint" ) ) );
    }

    @Test
    public void testGenerateHtmlReport_WithGeometricWarnings()
                    throws Exception {
        ByteArrayOutputStream html = new ByteArrayOutputStream();
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();

        htmlReportGenerator.generateHtmlReport( mockValidatorReportWithGeometricWarnings(), html );
        assertThat( document( html ), hasXPath( "/html/body/p[3]/p[2]", containsString( "1 Warnungen" ) ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullReport()
                    throws Exception {
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();
        htmlReportGenerator.generateHtmlReport( null, createSimpleStream() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullValidationName()
                    throws Exception {
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();
        htmlReportGenerator.generateHtmlReport( mockReport( null, "archiveName" ),  createSimpleStream() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullArchiveName()
                    throws Exception {
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();
        htmlReportGenerator.generateHtmlReport( mockReport( "validationName", null), createSimpleStream() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullOutputStream()
                    throws Exception {
        HtmlReportGenerator htmlReportGenerator = new HtmlReportGenerator();
        htmlReportGenerator.generateHtmlReport( mockReport( "validationName", "archiveName" ), null );
    }

    private static StreamSource document( ByteArrayOutputStream html ) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream( html.toByteArray() );
        return new StreamSource( inputStream );
    }

    private ValidatorReport mockReport( String validationName, String planName ) {
        ValidatorReport report = mock( ValidatorReport.class );
        when( report.getPlanName() ).thenReturn( validationName );
        when( report.getValidationName() ).thenReturn( planName );
        return report;
    }

    private ValidatorReport mockValidatorReportWithSyntacticDetailHint() {
        ValidatorReport validatorReport = new ValidatorReport();
        validatorReport.setPlanName( "PLAN_NAME" );
        validatorReport.setValidationName( "VALIDATION_NAME" );
        List<String> messages = Collections.singletonList( "Error in xml..." );
        ValidatorDetail detail = new ValidatorDetail( "detailsHint" );
        SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult( messages, false, detail );
        validatorReport.setSyntacticValidatorResult( syntacticValidatorResult );
        return validatorReport;
    }

    private ValidatorReport mockValidatorReportWithGeometricWarnings() {
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