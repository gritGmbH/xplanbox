package de.latlon.xplanbox.api.commons;

import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import org.junit.Test;
import org.mockito.Mockito;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class ValidationReportBuilderTest {

    @Test
    public void validatorReport() {
        ValidationReport report = new ValidationReportBuilder().build();
        assertNotNull( report );
    }

    @Test
    public void filename() {
        ValidationReport report = new ValidationReportBuilder().filename("test.xml").build();
    }

    @Test
    public void wmsUrl() {

    }

    @Test
    public void build() throws URISyntaxException {
        ValidatorReport report = Mockito.mock( ValidatorReport.class );
        ValidationReport validationReport = new ValidationReportBuilder().filename("test.xml").validatorReport(report)
                .wmsUrl(new URI("file:///no/real/file/name"))
                .build();
    }
}