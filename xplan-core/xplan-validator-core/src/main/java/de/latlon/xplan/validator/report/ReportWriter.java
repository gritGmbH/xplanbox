package de.latlon.xplan.validator.report;

import static de.latlon.xplan.validator.web.shared.ArtifactType.HTML;
import static org.apache.commons.io.IOUtils.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOG = LoggerFactory.getLogger( ReportWriter.class );

    private static final String SHAPES = "shapes";

    public static final String ERROR_LOG_FILENAME = "error.log";

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
     * @param targetDirectory
     *            the directory to put the archive in, never <code>null</code>
     * @throws ReportGenerationException
     *             if an exception occurred during writing the reports or zip archive
     */
    public void writeArtefacts( ValidatorReport report, File targetDirectory ) {
        List<String> failures = new ArrayList<>();
        addXmlEntry( report, targetDirectory, failures );
        addHtmlEntry( report, targetDirectory, failures );
        addPdfEntry( report, targetDirectory, failures );
        addPNGEntry( report, targetDirectory, failures );
        addShapeDirectoryEntry( report, targetDirectory, failures );

        addFailureLog( failures, targetDirectory );
    }

    public File retrieveHtmlReport( String validationName, File sourceDirectory ) {
        return retrieveArtifactFile( sourceDirectory, validationName, HTML );
    }

    public void writeZipWithArtifacts( OutputStream outputStream, String validationName, List<ArtifactType> artifacts,
                                       File sourceDirectory )
                            throws IOException {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream( outputStream )) {
            for ( ArtifactType artifactType : artifacts ) {
                addZipEntry( artifactType, validationName, zipOutputStream, sourceDirectory );
            }
            File errorlog = new File( sourceDirectory, ERROR_LOG_FILENAME );
            if ( errorlog.exists() ) {
                addArtifact( zipOutputStream, errorlog );
            }
        }
    }

    private void addPdfEntry( ValidatorReport report, File directoryToCreateZip, List<String> failures ) {
        String validationName = report.getValidationName();
        File pdfFile = new File( directoryToCreateZip, validationName + ".pdf" );
        try (FileOutputStream fileOutputStream = new FileOutputStream( pdfFile )) {
            pdfGenerator.createPdfReport( report, fileOutputStream );
        } catch ( Exception e ) {
            failures.add( e.getMessage() );
        }
    }

    private void addXmlEntry( ValidatorReport report, File directoryToCreateZip, List<String> failures ) {
        String validationName = report.getValidationName();
        File xmlFile = new File( directoryToCreateZip, validationName + ".xml" );
        try (FileOutputStream fileOutputStream = new FileOutputStream( xmlFile )) {
            xmlReportGenerator.generateXmlReport( report, fileOutputStream );
        } catch ( Exception e ) {
            failures.add( e.getMessage() );
        }

    }

    private void addHtmlEntry( ValidatorReport report, File directoryToCreateZip, List<String> failures ) {
        String validationName = report.getValidationName();
        File htmlFile = new File( directoryToCreateZip, validationName + ".html" );
        try (FileOutputStream fileOutputStream = new FileOutputStream( htmlFile )) {
            htmlGenerator.generateHtmlReport( report, fileOutputStream );
        } catch ( Exception e ) {
            failures.add( e.getMessage() );
        }
    }

    private void addPNGEntry( ValidatorReport report, File directoryToCreateZip, List<String> failures ) {
        String validationName = report.getValidationName();
        try {
            if ( badGeometryImgGenerator.hasBadGeometry( report ) ) {
                File pngFile = new File( directoryToCreateZip, validationName + ".png" );
                try (FileOutputStream fileOutputStream = new FileOutputStream( pngFile )) {
                    badGeometryImgGenerator.generateReport( report, fileOutputStream );

                }
            }
        } catch ( Exception e ) {
            failures.add( e.getMessage() );
        }
    }

    private void addShapeDirectoryEntry( ValidatorReport report, File directoryToCreateZip, List<String> failures ) {
        String validationName = report.getValidationName();
        try {
            if ( shapefileGenerator.hasBadGeometry( report ) ) {
                File directoryToCreateShapes = new File( directoryToCreateZip, "shapes" );
                directoryToCreateShapes.mkdir();
                shapefileGenerator.generateReport( report, validationName, directoryToCreateShapes );
            }
        } catch ( Exception e ) {
            failures.add( e.getMessage() );
        }
    }

    private void addFailureLog( List<String> failures, File directoryToCreateZip ) {
        if ( !failures.isEmpty() ) {
            File errorlog = new File( directoryToCreateZip, ERROR_LOG_FILENAME );
            try (FileOutputStream fos = new FileOutputStream( errorlog )) {
                String errorlogContent = String.join( "\n", failures );
                IOUtils.write( errorlogContent, fos );
            } catch ( IOException e ) {
                LOG.error( "Could not write error.log", e );
            }
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
                            throws IOException {
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
                            throws IOException {
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