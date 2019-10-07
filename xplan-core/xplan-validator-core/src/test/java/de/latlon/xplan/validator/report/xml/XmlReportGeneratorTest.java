package de.latlon.xplan.validator.report.xml;

import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import org.junit.Test;
import org.xmlmatchers.xpath.XpathReturnType;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.newInputStream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.xmlmatchers.XmlMatchers.hasXPath;
import static org.xmlmatchers.xpath.XpathReturnType.returningANumber;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class XmlReportGeneratorTest {

    public static final String PLAN_NAME = "PLAN_NAME";

    public static final String VALIDATION_NAME = "VALIDATION_NAME";

    @Test
    public void testGenerateXmlReport()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        File xmlFile = createTmpFile();

        OutputStream os = new FileOutputStream( xmlFile );
        xmlReportGenerator.generateXmlReport( createValidatorReport(), os );

        Path xml = xmlFile.toPath();

        assertThat( document( xml ), hasXPath( "/ValidationReport/name", equalTo( VALIDATION_NAME ) ) );
        assertThat( document( xml ), hasXPath( "/ValidationReport/Plan/name", equalTo( PLAN_NAME ) ) );
        assertThat( document( xml ), hasXPath( "/ValidationReport/Validation" ) );
    }

    @Test
    public void testGenerateXmlReport_CheckSyntacticDetailsHint()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        File xmlFile = createTmpFile();

        OutputStream os = new FileOutputStream( xmlFile );
        xmlReportGenerator.generateXmlReport( createValidatorReportWithSyntacticDetailHint(), os );

        Path xml = xmlFile.toPath();

        assertThat( document( xml ), hasXPath( "/ValidationReport/Validation/Syn/details", equalTo( "detailsHint" ) ) );
    }

    @Test
    public void testGenerateXmlReport_CheckSemanticsResults()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        File xmlFile = createTmpFile();

        OutputStream os = new FileOutputStream( xmlFile );
        xmlReportGenerator.generateXmlReport( createValidatorReportWithSemanticFailures(), os );

        Path xml = xmlFile.toPath();

        assertThat( document( xml ), hasXPath( "/ValidationReport/Validation/Sem/result", equalTo( "nicht valide" ) ) );
        assertThat( document( xml ), hasXPath( "count(/ValidationReport/Validation/Sem/Rules/Rule)", returningANumber(),
                                               equalTo( 2d ) ) );
        assertThat( document( xml ),
                    hasXPath( "/ValidationReport/Validation/Sem/Rules/Rule[1]/name", equalTo( "1.1" ) ) );
        assertThat( document( xml ),
                    hasXPath( "/ValidationReport/Validation/Sem/Rules/Rule[2]/name", equalTo( "1.2" ) ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullReport()
                            throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        xmlReportGenerator.generateXmlReport( null, createSimpleStream() );
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
        semanticValidatorResult.addRule( "1.1", true, "Test valid" );
        semanticValidatorResult.addRule( "1.2", false, "Test valid" );
        validatorReport.setSemanticValidatorResult( semanticValidatorResult );
        return validatorReport;
    }

    private ByteArrayOutputStream createSimpleStream() {
        return new ByteArrayOutputStream();
    }

    private static StreamSource document( Path path )
                            throws IOException {
        return new StreamSource( newInputStream( path ) );
    }

    private File createTmpFile()
                            throws IOException {
        return createTempFile( "XmlReportGeneratorTest", ".xml" ).toFile();
    }

}