package de.latlon.xplan.validator.report.xml;

import static java.nio.file.Files.createTempFile;
import static java.nio.file.Files.newInputStream;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.xmlmatchers.XmlMatchers.hasXPath;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class XmlReportGeneratorTest {

    @Test
    public void testGenerateXmlReport()
                    throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        File xmlFile = createTempFile( null, null ).toFile();
        String validationName = "ValidationName";
        String archiveName = "ArchiveName";

        OutputStream os = new FileOutputStream( xmlFile );
        xmlReportGenerator.generateXmlReport( mockValidatorReport(), validationName, archiveName, os );

        Path xml = xmlFile.toPath();

        assertThat( document( xml ), hasXPath( "/ValidationReport/name", equalTo( validationName ) ) );
        assertThat( document( xml ), hasXPath( "/ValidationReport/Plan/name", equalTo( archiveName ) ) );
        assertThat( document( xml ), hasXPath( "/ValidationReport/Validation" ) );
    }

    @Test
    public void testGenerateXmlReport_CheckSyntacticDetailsHint()
                    throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        File xmlFile = createTempFile( null, null ).toFile();

        OutputStream os = new FileOutputStream( xmlFile );
        xmlReportGenerator.generateXmlReport( mockValidatorReportWithSyntacticDetailHint(), "ValidationName",
                                              "ArchiveName", os );

        Path xml = xmlFile.toPath();

        assertThat( document( xml ), hasXPath( "/ValidationReport/Validation/Syn/details", equalTo( "detailsHint" ) ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullReport()
                    throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        xmlReportGenerator.generateXmlReport( null, "validationName", "archiveName", createSimpleStream() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullValidationName()
                    throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        xmlReportGenerator.generateXmlReport( mockValidatorReport(), null, "archiveName", createSimpleStream() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullArchiveName()
                    throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        xmlReportGenerator.generateXmlReport( mockValidatorReport(), "validatioNName", null, createSimpleStream() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateXmlReportWithNullOutputStream()
                    throws Exception {
        XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();
        xmlReportGenerator.generateXmlReport( mockValidatorReport(), "validatioNName", "archiveName", null );
    }

    private ValidatorReport mockValidatorReport() {
        return mock( ValidatorReport.class );
    }

    private ValidatorReport mockValidatorReportWithSyntacticDetailHint() {
        ValidatorReport validatorReport = new ValidatorReport();
        List<String> messages = Collections.singletonList( "Error in xml..." );
        ValidatorDetail detail = new ValidatorDetail( "detailsHint" );
        SyntacticValidatorResult syntacticValidatorResult = new SyntacticValidatorResult( messages, false, detail );
        validatorReport.setSyntacticValidatorResult( syntacticValidatorResult );
        return validatorReport;
    }

    private ByteArrayOutputStream createSimpleStream() {
        return new ByteArrayOutputStream();
    }

    private static StreamSource document( Path path )
                    throws IOException {
        return new StreamSource( newInputStream( path ) );
    }

}