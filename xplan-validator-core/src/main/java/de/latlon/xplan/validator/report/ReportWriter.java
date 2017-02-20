package de.latlon.xplan.validator.report;

import static org.apache.commons.io.IOUtils.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import de.latlon.xplan.validator.report.badgeometryimg.BadGeometryImgGenerator;
import de.latlon.xplan.validator.report.html.HtmlReportGenerator;
import de.latlon.xplan.validator.report.pdf.PdfReportGenerator;
import de.latlon.xplan.validator.report.shapefile.ShapefileGenerator;
import de.latlon.xplan.validator.report.xml.XmlReportGenerator;
import de.latlon.xplan.validator.web.shared.ArtifactType;

/**
 * Generates an archive the {@link ValidatorReport} as XMl, HTML and PDF
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ReportWriter {

    private static final String SHAPES = "shapes";

    private final XmlReportGenerator xmlReportGenerator = new XmlReportGenerator();

    private final PdfReportGenerator pdfGenerator = new PdfReportGenerator();

    private final HtmlReportGenerator htmlGenerator = new HtmlReportGenerator();

    private final BadGeometryImgGenerator badGeometryImgGenerator = new BadGeometryImgGenerator();

    private final ShapefileGenerator shapefileGenerator = new ShapefileGenerator();

    /**
     * Writes all artefacts (XML, HTML and PDF as well as shp and png) into the passed directory.
     * 
     * @param report
     *            the report to write, never <code>null</code>
     * @param planName
     *            the name of the plane, never <code>null</code>
     * @param validationName
     *            the name of the validation run, never <code>null</code>
     * @param targetDirectory
     *            the directory to put the archive in, never <code>null</code>
     * @throws ReportGenerationException
     *             if an exception occurred during writing the reports or zip archive
     */
    public void writeArtefacts( ValidatorReport report, String planName, String validationName, File targetDirectory )
                            throws ReportGenerationException {
        try {
            addXmlEntry( report, planName, validationName, targetDirectory );
            addHtmlEntry( report, planName, validationName, targetDirectory );
            addPdfEntry( report, planName, validationName, targetDirectory );
            addPNGEntry( report, validationName, targetDirectory );
            addShapeDirectoryEntry( report, planName, validationName, targetDirectory );
        } catch ( IOException e ) {
            throw new ReportGenerationException( e );
        }
    }

    public File retrieveReport( ArtifactType artifactType, String validationName, File sourceDirectory ) {
        switch ( artifactType ) {
        case SHP:
            return new File( sourceDirectory, SHAPES );
        case PNG:
        case HTML:
        case XML:
        case PDF:
            return retrieveArtifactFile( sourceDirectory, validationName, artifactType );
        }
        return null;
    }

    public void writeZipWithArtifacts( OutputStream outputStream, String validationName, List<ArtifactType> artifacts,
                                       File sourceDirectory )
                            throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream( outputStream )) {
            for ( ArtifactType artifactType : artifacts ) {
                addZipEntry( artifactType, validationName, zipOutputStream, sourceDirectory );
            }
        }
    }

    private void addPdfEntry( ValidatorReport report, String planName, String validationName, File directoryToCreateZip )
                            throws IOException, ReportGenerationException {
        File pdfFile = new File( directoryToCreateZip, validationName + ".pdf" );
        try (FileOutputStream fileOutputStream = new FileOutputStream( pdfFile )) {
            pdfGenerator.createPdfReport( report, validationName, planName, fileOutputStream );
        }
    }

    private void addXmlEntry( ValidatorReport report, String planName, String validationName, File directoryToCreateZip )
                            throws IOException, ReportGenerationException {
        File xmlFile = new File( directoryToCreateZip, validationName + ".xml" );
        try (FileOutputStream fileOutputStream = new FileOutputStream( xmlFile )) {
            xmlReportGenerator.generateXmlReport( report, validationName, planName, fileOutputStream );
        }
    }

    private void addHtmlEntry( ValidatorReport report, String planName, String validationName, File directoryToCreateZip )
                            throws IOException, ReportGenerationException {
        File htmlFile = new File( directoryToCreateZip, validationName + ".html" );
        try (FileOutputStream fileOutputStream = new FileOutputStream( htmlFile )) {
            htmlGenerator.generateHtmlReport( report, validationName, planName, fileOutputStream );

        }
    }

    private void addPNGEntry( ValidatorReport report, String validationName, File directoryToCreateZip )
                            throws IOException, ReportGenerationException {
        if ( badGeometryImgGenerator.hasBadGeometry( report ) ) {
            File pngFile = new File( directoryToCreateZip, validationName + ".png" );
            try (FileOutputStream fileOutputStream = new FileOutputStream( pngFile )) {
                badGeometryImgGenerator.generateReport( report, fileOutputStream );
            }
        }
    }

    private void addShapeDirectoryEntry( ValidatorReport report, String planName, String validationName,
                                         File directoryToCreateZip )
                            throws IOException, ReportGenerationException {
        if ( shapefileGenerator.hasBadGeometry( report ) ) {
            File directoryToCreateShapes = new File( directoryToCreateZip, "shapes" );
            directoryToCreateShapes.mkdir();
            shapefileGenerator.generateReport( report, validationName, directoryToCreateShapes );
        }
    }

    private void addZipEntry( ArtifactType artifactType, String validationName, ZipOutputStream zipOutputStream,
                              File sourceDirectory )
                            throws IOException {
        switch ( artifactType ) {
        case SHP:
            addShpArtifact( zipOutputStream, sourceDirectory );
            break;
        case PNG:
        case HTML:
        case XML:
        case PDF:
            addSimpleArtifact( artifactType, validationName, zipOutputStream, sourceDirectory );
            break;
        default:
            break;
        }
    }

    private void addSimpleArtifact( ArtifactType artifactType, String validationName, ZipOutputStream zipOutputStream,
                                    File sourceDirectory )
                            throws IOException, FileNotFoundException {
        File artifactFile = retrieveArtifactFile( sourceDirectory, validationName, artifactType );
        if ( artifactFile.exists() ) {
            addArtifact( zipOutputStream, artifactFile );
        }
    }

    private void addShpArtifact( ZipOutputStream zipOutputStream, File sourceDirectory )
                            throws IOException {
        File shapesDirectory = new File( sourceDirectory, SHAPES );
        if ( shapesDirectory.exists() ) {
            addDirToArchive( zipOutputStream, shapesDirectory );
        }
    }

    private void addDirToArchive( ZipOutputStream zipOutputStream, File directory )
                            throws IOException {
        File[] files = directory.listFiles();
        if ( files != null )
            for ( File file : files ) {
                addArtifact( zipOutputStream, file );
            }
    }

    private void addArtifact( ZipOutputStream zipOutputStream, File artifactFile )
                            throws IOException, FileNotFoundException {
        ZipEntry entry = new ZipEntry( artifactFile.getName() );
        zipOutputStream.putNextEntry( entry );
        try (FileInputStream input = new FileInputStream( artifactFile )) {
            copy( input, zipOutputStream );
            zipOutputStream.closeEntry();
        }
    }

    private File retrieveArtifactFile( File sourceDirectory, String validationName, ArtifactType artifactType ) {
        String suffix = artifactType.name().toLowerCase();
        return new File( sourceDirectory, validationName + "." + suffix );
    }

}