package de.latlon.xplan.validator.report.pdf;

import de.latlon.xplan.validator.report.ValidatorReport;
import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class PdfReportGeneratorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testCreateReportAsPdfWithNullReport()
          throws Exception {
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.createReport( null );
    }

    @Test
    public void testCreateReportAsPdfWithNullValidationName()
          throws Exception {
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.createReport( createReport( null, "PlanName" ));
    }

    @Test
    public void testCreateReportAsPdfWithEmptyValidationName()
          throws Exception {
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.createReport( createReport( "", "PlanName" ) );
    }

    @Test
    public void testCreateReportAsPdfWithNullPlanName()
          throws Exception {
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.createReport( createReport( "ValName", null ) );
    }

    @Test
    public void testCreateReportAsPdfWithEmptyPlanName()
          throws Exception {
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.createReport( createReport( "ValName", "" ) );
    }

    private ValidatorReport createReport( String archiveName, String planName ) {
        ValidatorReport validatorReport = new ValidatorReport();
        validatorReport.setPlanName( archiveName );
        validatorReport.setValidationName( planName );
        return validatorReport;
    }

}