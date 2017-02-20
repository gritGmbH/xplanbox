package de.latlon.xplan.validator.report;

import static de.latlon.xplan.validator.web.shared.ArtifactType.HTML;
import static org.deegree.cs.persistence.CRSManager.lookup;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.deegree.commons.uom.Measure;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.geometry.GeometryFactory;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;

import de.latlon.xplan.validator.geometric.report.BadGeometry;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ReportWriterTest {

    private static final String PLAN_NAME = "planName";

    private static final String VALIDATION_NAME = "validationName";

    private static File targetDirectory;

    @BeforeClass
    public static void createTest()
                            throws Exception {
        ReportWriter reportWriter = new ReportWriter();
        targetDirectory = createDirectory();
        reportWriter.writeArtefacts( createReport(), PLAN_NAME, VALIDATION_NAME, targetDirectory );
    }

    @Test
    public void testWriteArtefactsShouldHaveSubdirectoryWithArtifacts()
                            throws Exception {
        ReportWriter reportWriter = new ReportWriter();
        File targetDirectory = createDirectory();
        reportWriter.writeArtefacts( createReport(), PLAN_NAME, VALIDATION_NAME, targetDirectory );

        assertThat( targetDirectory, containsFile( VALIDATION_NAME + ".html" ) );
        assertThat( targetDirectory, containsFile( VALIDATION_NAME + ".pdf" ) );
        assertThat( targetDirectory, containsFile( VALIDATION_NAME + ".xml" ) );
        assertThat( targetDirectory, containsFile( VALIDATION_NAME + ".png" ) );
        assertThat( targetDirectory, containsDirectory( "shapes" ) );
    }

    @Test
    public void testRetrieveReportShouldExistWithCorrectName() {
        ReportWriter reportWriter = new ReportWriter();
        File htmlReport = reportWriter.retrieveReport( HTML, VALIDATION_NAME, targetDirectory );

        assertThat( htmlReport.exists(), is( true ) );
        assertThat( htmlReport.getName(), is( VALIDATION_NAME + ".html" ) );
    }

    @Test
    public void testWriteZipWithArtifactsShouldContainHtml()
                            throws Exception {
        ReportWriter reportWriter = new ReportWriter();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        reportWriter.writeZipWithArtifacts( outputStream, VALIDATION_NAME, Collections.singletonList( HTML ),
                                            targetDirectory );
        ZipInputStream zipInputStream = new ZipInputStream( new ByteArrayInputStream( outputStream.toByteArray() ) );

        ZipEntry htmlEntry = zipInputStream.getNextEntry();
        assertThat( htmlEntry.getName(), is( VALIDATION_NAME + ".html" ) );

        ZipEntry nextEntry = zipInputStream.getNextEntry();
        assertThat( nextEntry, is( nullValue() ) );

    }

    private static ValidatorReport createReport()
                            throws UnknownCRSException {
        List<BadGeometry> badGeometries = new ArrayList<>();
        ICRS crs = lookup( "epsg:4326" );
        String uomURI = "m";
        Measure measure = new Measure( BigDecimal.TEN, uomURI );
        badGeometries.add( new BadGeometry( new GeometryFactory().createPoint( "id", 20, 10, crs ).getBuffer( measure ) ) );
        GeometricValidatorResult result = new GeometricValidatorResult( Collections.<String> emptyList(),
                                                                        Collections.<String> emptyList(),
                                                                        badGeometries, crs, false );
        ValidatorReport report = new ValidatorReport();
        report.setGeometricValidatorResult( result );
        return report;
    }

    private static File createDirectory()
                            throws IOException {
        File targetDirectory = Files.createTempDirectory( "ReportWriterTest" ).toFile();
        targetDirectory.deleteOnExit();
        return targetDirectory;
    }

    private Matcher<? super File> containsFile( final String fileName ) {
        return new BaseMatcher<File>() {

            @Override
            public boolean matches( Object item ) {
                File directory = (File) item;
                return new File( directory, fileName ).isFile();
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Directory must contain a file " + fileName );
            }
        };
    }

    private Matcher<? super File> containsDirectory( final String directoryName ) {
        return new BaseMatcher<File>() {

            @Override
            public boolean matches( Object item ) {
                File directory = (File) item;
                return new File( directory, directoryName ).isDirectory();
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Directory must contain a directory " + directoryName );
            }
        };
    }

}