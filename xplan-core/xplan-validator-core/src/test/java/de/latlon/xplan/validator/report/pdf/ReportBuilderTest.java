package de.latlon.xplan.validator.report.pdf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ReportBuilderTest {

    // @Ignore("Make a visibility check if the report looks like expected!")
    @Test
    public void testCreateReportAsPdf()
                    throws Exception {
        PdfReportGenerator reportBuilder = new PdfReportGenerator();

        File pdf = File.createTempFile( "report", ".pdf" );
        OutputStream os = new FileOutputStream( pdf );
        reportBuilder.createPdfReport( createReport(), os );
        os.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateReportAsPdfWithNullReport()
                    throws Exception {
        PdfReportGenerator reportBuilder = new PdfReportGenerator();
        reportBuilder.createPdfReport( null, createSimpleOutputStream() );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateReportAsPdfWithNullStream()
                    throws Exception {
        PdfReportGenerator reportBuilder = new PdfReportGenerator();
        reportBuilder.createPdfReport( createReport(), null );
    }

    private ValidatorReport createReport() {
        ValidatorReport report = new ValidatorReport();

        report.setSemanticValidatorResult( createSemanticResult() );
        report.setSyntacticValidatorResult( createSyntacticResult() );
        report.setGeometricValidatorResult( createGeometricResult() );
        return report;
    }

    private SemanticValidatorResult createSemanticResult() {
        SemanticValidatorResult semanticResult = new SemanticValidatorResult();
        semanticResult.setValid( true );
        for ( int i = 0; i < 20; i++ ) {
            if ( i == 8 || i == 2 )
                semanticResult.addRule( "Name" + i, true,
                                        "HinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweisHinweis"
                                                          + i );
            else
                semanticResult.addRule( "Name" + i, true, "Hinweis" + i );
        }
        return semanticResult;
    }

    private SyntacticValidatorResult createSyntacticResult() {
        List<String> messages = new ArrayList<String>();
        addMessages( "Syntactic ", messages );
        ValidatorDetail detail = new ValidatorDetail( "detailsHint" );
        return new SyntacticValidatorResult( messages, false, detail );
    }

    private GeometricValidatorResult createGeometricResult() {
        List<String> warnings = new ArrayList<String>();
        addMessages( "Geom ", warnings );
        List<String> errors = new ArrayList<String>();
        addMessages( "Geom ", warnings );
        addMessages( "Geom ", errors );
        List<BadGeometry> badGeometries = new ArrayList<>();
        return new GeometricValidatorResult( warnings, errors, badGeometries, null, true );
    }

    private void addMessages( String prefix, List<String> allMessages ) {
        for ( int i = 0; i < 20; i++ ) {
            allMessages.add( prefix + i );
        }
    }

    private OutputStream createSimpleOutputStream() {
        return new ByteArrayOutputStream();
    }
}