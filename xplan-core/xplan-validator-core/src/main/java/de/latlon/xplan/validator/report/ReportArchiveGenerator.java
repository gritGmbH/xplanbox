package de.latlon.xplan.validator.report;

import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.report.badgeometryimg.BadGeometryImgGenerator;
import de.latlon.xplan.validator.report.html.HtmlReportGenerator;
import de.latlon.xplan.validator.report.pdf.PdfReportGenerator;
import de.latlon.xplan.validator.report.shapefile.ShapefileGenerator;
import de.latlon.xplan.validator.report.xml.XmlReportGenerator;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Generates an archive the {@link ValidatorReport} as XMl, HTML and PDF
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 */
public class ReportArchiveGenerator {

    private final XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();

    private final PdfReportGenerator pdfGenerator = new PdfReportGenerator();

    private final HtmlReportGenerator htmlGenerator = new HtmlReportGenerator();

    private final BadGeometryImgGenerator badGeometryImgGenerator = new BadGeometryImgGenerator();

    private final ShapefileGenerator shapefileGenerator = new ShapefileGenerator();

    private final ValidatorConfiguration validatorConfiguration;

    /**
     * @param validatorConfiguration
     *            configuration of validator, never <code>null</code>
     */
    public ReportArchiveGenerator( ValidatorConfiguration validatorConfiguration ) {
        checkParameters( validatorConfiguration );
        this.validatorConfiguration = validatorConfiguration;
    }

    /**
     * Creates a zip Archive containing the {@link ValidatorReport} as XML, HTML and PDF
     * 
     * @param report
     *            the report to write, never <code>null</code>
     * @param validationName
     *            the name of the validation run, never <code>null</code>
     * @return the validation report directory, never <code>null</code>
     * @throws ReportGenerationException
     *             if an exception occurred during writing the reports or zip archive
     */
    public Path generateZipArchive( ValidatorReport report, String validationName )
                    throws ReportGenerationException {
        Path validationReportDirectory = validatorConfiguration.getValidationReportDirectory();
        Path outputFile = validationReportDirectory.resolve( validationName + ".zip" );
        try ( OutputStream fileOutStream = Files.newOutputStream( outputFile );
              ZipOutputStream zipOutStream = new ZipOutputStream( fileOutStream ) ) {
            addXmlEntry( report, validationName, zipOutStream );
            addHtmlEntry( report, validationName, zipOutStream );
            addPdfEntry( report, validationName, zipOutStream );
            addPNGEntry( report, validationName, zipOutStream );
            addShapeDirectoryEntry( report, validationName, validationReportDirectory, zipOutStream );
            return validationReportDirectory;
        } catch ( IOException e ) {
            throw new ReportGenerationException( e );
        }
    }

    private void addPdfEntry( ValidatorReport report, String validationName,
                              ZipOutputStream zipOutStream )
                    throws IOException, ReportGenerationException {
        ZipEntry pdfEntry = new ZipEntry( validationName + ".pdf" );
        zipOutStream.putNextEntry( pdfEntry );
        pdfGenerator.createPdfReport( report, zipOutStream );
        zipOutStream.closeEntry();
    }

    private void addXmlEntry( ValidatorReport report, String validationName,
                              ZipOutputStream zipOutStream )
                    throws IOException, ReportGenerationException {
        ZipEntry xmlEntry = new ZipEntry( validationName + ".xml" );
        zipOutStream.putNextEntry( xmlEntry );
        xmlReportGenerator.generateXmlReport( report, zipOutStream );
        zipOutStream.closeEntry();
    }

    private void addHtmlEntry( ValidatorReport report, String validationName,
                               ZipOutputStream zipOutStream )
                    throws IOException, ReportGenerationException {
        ZipEntry xmlEntry = new ZipEntry( validationName + ".html" );
        zipOutStream.putNextEntry( xmlEntry );
        htmlGenerator.generateHtmlReport( report, zipOutStream );
        zipOutStream.closeEntry();
    }

    private void addPNGEntry( ValidatorReport report, String validationName, ZipOutputStream zipOutStream )
                    throws IOException, ReportGenerationException {
        if ( badGeometryImgGenerator.hasBadGeometry( report ) ) {
            ZipEntry pngEntry = new ZipEntry( validationName + ".png" );
            zipOutStream.putNextEntry( pngEntry );
            badGeometryImgGenerator.generateReport( report, zipOutStream );
            zipOutStream.closeEntry();
        }
    }

    private void addShapeDirectoryEntry( ValidatorReport report, String validationName,
                                         Path directoryToCreateShapes, ZipOutputStream zipOutStream )
                    throws IOException, ReportGenerationException {
        if ( shapefileGenerator.hasBadGeometry( report ) ) {
            shapefileGenerator.generateReport( report, validationName, directoryToCreateShapes.toFile() );
            ReportUtils.writeShapefilesToZipOS( directoryToCreateShapes.toFile(), zipOutStream );
            ReportUtils.deleteShapefiles( directoryToCreateShapes.toFile() );
        }
    }

    private void checkParameters( ValidatorConfiguration validatorConfiguration ) {
        if ( validatorConfiguration == null )
            throw new IllegalArgumentException( "validatorConfiguration must not be null!" );
    }

}