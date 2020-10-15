package de.latlon.xplanbox.api.validator.handler;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.exception.InvalidXPlanGmlOrArchive;
import de.latlon.xplanbox.api.validator.config.ApplicationContext;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class })
public class ValidationHandlerTest {

    @Autowired
    private ValidationHandler validationHandler;

    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void verifyThat_FromZip_ArchiveCanBeCreated()
                            throws URISyntaxException, InvalidXPlanGmlOrArchive {
        final File file = new File( ValidationHandlerTest.class.getResource( "/bplan_valid_41.zip" ).toURI() );
        XPlanArchive archive = validationHandler.createArchiveFromZip( file, "bplan_valid_41" );
        assertNotNull( archive );
    }

    @Test
    public void verifyThat_FromGml_ArchiveCanBeCreated()
                            throws URISyntaxException, InvalidXPlanGmlOrArchive {
        final File file = new File( ValidationHandlerTest.class.getResource( "/xplan.gml" ).toURI() );
        XPlanArchive archive = validationHandler.createArchiveFromGml( file, "bplan_valid_41" );
        assertNotNull( archive );
    }

    @Test(expected = InvalidXPlanGmlOrArchive.class)
    public void verifyThat_FromGml_ZipArchiveCanBeCreated()
                            throws URISyntaxException, InvalidXPlanGmlOrArchive {
        final File file = new File( ValidationHandlerTest.class.getResource( "/xplan.gml" ).toURI() );
        validationHandler.createArchiveFromZip( file, "bplan_valid_41" );
    }

    @Test(expected = InvalidXPlanGmlOrArchive.class)
    public void verifyThat_FromZip_GmlArchiveCanBeCreated()
                            throws URISyntaxException, InvalidXPlanGmlOrArchive {
        final File file = new File( ValidationHandlerTest.class.getResource( "/bplan_valid_41.zip" ).toURI() );
        validationHandler.createArchiveFromGml( file, "bplan_valid_41" );
    }

    @Test
    public void verifyThat_ValidPlan_ReturnsValidReport()
                            throws ValidatorException, URISyntaxException, InvalidXPlanGmlOrArchive {
        final File file = new File( ValidationHandlerTest.class.getResource( "/bplan_valid_41.zip" ).toURI() );
        final ValidationSettings settings = Mockito.mock( ValidationSettings.class );

        XPlanArchive archive = validationHandler.createArchiveFromZip( file, "bplan_valid_41" );
        ValidatorReport report = validationHandler.validate( archive, "noName", settings );
        assertTrue( report.isReportValid() );
    }

    @Test
    public void verifyThat_PathToReport_ContainsReportName()
                            throws IOException {
        final ValidatorReport report = Mockito.mock( ValidatorReport.class );
        when( report.getValidationName() ).thenReturn( "mockReport" );

        Path path = validationHandler.zipReports( report );
        assertThat( path.toString(), containsString( "mockReport" ) );
        verify( report, atLeastOnce() ).getValidationName();
    }

    @Test
    public void verifyThat_WritePdfReport_CreatesFile()
                            throws IOException {
        final ValidatorReport report = Mockito.mock( ValidatorReport.class );

        File file = validationHandler.writePdfReport( report );
        assertTrue( file.exists() );
    }

    @Test
    public void verifyThat_PlanwerkWmsUrl_IsAddedToIncompleteValidationHandler_NullIsReturned()
                            throws URISyntaxException, InvalidXPlanGmlOrArchive {
        final File file = new File( ValidationHandlerTest.class.getResource( "/xplan.gml" ).toURI() );

        XPlanArchive archive = validationHandler.createArchiveFromGml( file, "xplan" );
        URI wmsUrl = validationHandler.addToWms( archive );
        assertNull( wmsUrl );
    }
}