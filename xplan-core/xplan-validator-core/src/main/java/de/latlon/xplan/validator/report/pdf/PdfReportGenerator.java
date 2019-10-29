package de.latlon.xplan.validator.report.pdf;

import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidatorReport;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

import java.io.OutputStream;

/**
 * Creates a pdf from a {@link ValidatorReport}
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class PdfReportGenerator {

    private final ReportBuilder reportBuilder = new ReportBuilder();

    /**
     * Writes the {@link ValidatorReport} to an OutputStream in PDF-Format
     *
     * @param report         the validation report to serialize, never <code>null</code>
     * @param os             the OutputStream where the PDF-Content is written into, never <code>null</code>
     * @throws ReportGenerationException if an exception occurred during writing the report
     * @throws IllegalArgumentException  if the passed report or outputstream are <code>null</code>
     */
    public void createPdfReport( ValidatorReport report, OutputStream os )
          throws ReportGenerationException {
        checkParameters( report, os );
        try {
            JasperReportBuilder print = reportBuilder.createReport( report );
            print.toPdf( os );
        } catch ( DRException e ) {
            throw new ReportGenerationException( e );
        }
    }

    private void checkParameters( ValidatorReport report, OutputStream os ) {
        if ( report == null )
            throw new IllegalArgumentException( "Report must not be null!" );
        if ( os == null )
            throw new IllegalArgumentException( "OutputStream must not be null!" );
    }

}