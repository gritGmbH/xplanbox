package de.latlon.xplanbox.api.commons;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.net.URISyntaxException;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_52;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ValidationReportBuilderTest {

    @Test
    public void verifyThat_Builder_ReturnsInstance() {
        ValidationReport report = new ValidationReportBuilder().build();
        assertNotNull( report );
    }

    @Test
    public void verifyThat_Builder_AddsFilename() {
        ValidatorReport sourceReport = Mockito.mock( ValidatorReport.class );
        ValidationReport report = new ValidationReportBuilder().validatorReport( sourceReport )
                .filename( "test.xml" ).build();
        assertThat( report.getFilename(), containsString( "test.xml" ) );
    }

    @Test
    public void verifyThat_Builder_AddsVersion() {
        ValidatorReport sourceReport = Mockito.mock( ValidatorReport.class );
        when( sourceReport.getXPlanVersion() ).thenReturn( XPLAN_52 );
        ValidationReport report = new ValidationReportBuilder().validatorReport( sourceReport ).build();
        assertThat( report.getVersion(), is( VersionEnum.XPLAN_52 ));
    }

    @Test
    public void verifyThat_Builder_AddsWmsUrl() throws URISyntaxException {
        ValidatorReport sourceReport = Mockito.mock( ValidatorReport.class );
        ValidationReport report = new ValidationReportBuilder().validatorReport( sourceReport )
                .wmsUrl( new URI ("file://here") ).build();
        assertThat( report.getWmsUrl(), is( notNullValue() ));
    }

    @Test
    public void verifyThat_Builder_ReturnsCompleteInstance() throws URISyntaxException {
        ValidatorReport sourceReport = Mockito.mock( ValidatorReport.class );
        ValidationReport validationReport = new ValidationReportBuilder().validatorReport( sourceReport)
                .filename( "test.xml" )
                .wmsUrl( new URI("file:///no/real/file/name" ) )
                .build();
        assertThat( validationReport.getWmsUrl(), is( notNullValue() ) );
        assertThat( validationReport.getFilename(), containsString("test") );
    }
}