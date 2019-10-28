package de.latlon.xplan.validator.report;

import static de.latlon.xplan.validator.web.shared.ArtifactType.HTML;
import static org.deegree.cs.persistence.CRSManager.lookup;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
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

    private static final String FAILURE = "Failure";

    private ReportWriter reportWriter = new ReportWriter();

    private File targetDirectory;

    @Before
    public void createTargetDirectory()
                            throws Exception {
        targetDirectory = createDirectory();
    }

    @Test
    public void testWriteArtefacts_ShouldHaveSubdirectoryWithArtifacts()
                            throws Exception {
        reportWriter.writeArtefacts( createReport(), targetDirectory );

        assertThat( targetDirectory, containsFile( VALIDATION_NAME + ".html" ) );
        assertThat( targetDirectory, containsFile( VALIDATION_NAME + ".pdf" ) );
        assertThat( targetDirectory, containsFile( VALIDATION_NAME + ".xml" ) );
        assertThat( targetDirectory, containsFile( VALIDATION_NAME + ".png" ) );
        assertThat( targetDirectory, containsDirectory( "shapes" ) );
    }

    @Test
    public void testWriteArtefacts_WithFailure() {
        reportWriter.writeArtefacts( createReportThrowingFailure(), targetDirectory );

        assertThat( targetDirectory, containsFile( "error.log" ) );
    }

    @Test
    public void testRetrieveHtmlReport_ShouldExistWithCorrectName()
                            throws Exception {
        reportWriter.writeArtefacts( createReport(), targetDirectory );

        File htmlReport = reportWriter.retrieveHtmlReport( VALIDATION_NAME, targetDirectory );

        assertThat( htmlReport.exists(), is( true ) );
        assertThat( htmlReport.getName(), is( VALIDATION_NAME + ".html" ) );
    }

    @Test
    public void testWriteZipWithArtifacts_ShouldContainHtml()
                            throws Exception {
        reportWriter.writeArtefacts( createReport(), targetDirectory );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        reportWriter.writeZipWithArtifacts( outputStream, VALIDATION_NAME, Collections.singletonList( HTML ),
                                            targetDirectory );
        ZipInputStream zipInputStream = new ZipInputStream( new ByteArrayInputStream( outputStream.toByteArray() ) );
        assertThat( zipInputStream, hasEntryWithNameAndSize( VALIDATION_NAME + ".html", 1 ) );
    }

    @Test
    public void testWriteZipWithArtifacts_WithFailure()
                            throws Exception {
        reportWriter.writeArtefacts( createReportThrowingFailure(), targetDirectory );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        reportWriter.writeZipWithArtifacts( outputStream, VALIDATION_NAME, Collections.singletonList( HTML ),
                                            targetDirectory );
        ZipInputStream zipInputStream = new ZipInputStream( new ByteArrayInputStream( outputStream.toByteArray() ) );
        assertThat( zipInputStream, hasEntryWithNameAndSize( "error.log", 2 ) );
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
        report.setPlanName( PLAN_NAME );
        report.setValidationName( VALIDATION_NAME );
        return report;
    }

    private ValidatorReport createReportThrowingFailure() {
        ValidatorReport report = mock( ValidatorReport.class );
        when( report.getPlanName() ).thenReturn( PLAN_NAME );
        when( report.getValidationName() ).thenReturn( VALIDATION_NAME );
        when( report.getGeometricValidatorResult() ).thenThrow( new IllegalArgumentException( FAILURE ) );
        return report;
    }

    private static File createDirectory()
                            throws IOException {
        File targetDirectory = Files.createTempDirectory( "ReportWriterTest" ).toFile();
        targetDirectory.deleteOnExit();
        return targetDirectory;
    }

    private Matcher<? super File> containsFile( final String fileName ) {
        return new TypeSafeMatcher<File>() {

            @Override
            public boolean matchesSafely( File directory ) {
                return new File( directory, fileName ).isFile();
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Directory must contain a file " + fileName );
            }
        };
    }

    private Matcher<? super File> containsDirectory( final String directoryName ) {
        return new TypeSafeMatcher<File>() {

            @Override
            public boolean matchesSafely( File directory ) {
                return new File( directory, directoryName ).isDirectory();
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Directory must contain a directory " + directoryName );
            }
        };
    }

    private Matcher<ZipInputStream> hasEntryWithNameAndSize( final String expectedName, final int expectedSize ) {
        return new TypeSafeMatcher<ZipInputStream>() {
            @Override
            protected boolean matchesSafely( ZipInputStream zip ) {
                try {
                    boolean hasExpectedName = false;
                    int numberOfEntries = 0;
                    ZipEntry nextEntry = zip.getNextEntry();
                    while ( nextEntry != null ) {
                        if ( expectedName.equals( nextEntry.getName() ) ) {
                            hasExpectedName = true;
                        }
                        numberOfEntries++;
                        nextEntry = zip.getNextEntry();
                    }
                    return expectedSize == numberOfEntries && hasExpectedName;
                } catch ( IOException e ) {
                    throw new IllegalArgumentException( "zip cannot be read" );
                }
            }

            @Override
            public void describeTo( Description description ) {
                description.appendText( "Zip must contain " + expectedSize + "entries and an entry with name "
                                        + expectedName );
            }
        };
    }

}