package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplanbox.api.commons.ValidationReportBuilder;
import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidPlan extends XPlanApiException {

    private static final String EXCEPTION_MESSAGE = "Invalid plan";

    private final ValidatorReport validatorReport;

    private final String xFileName;

    public InvalidPlan( ValidatorReport validatorReport, String xFileName ) {
        super( EXCEPTION_MESSAGE );
        this.validatorReport = validatorReport;
        this.xFileName = xFileName;
    }

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getStatusCode();
    }

    @Override
    public Object getResponseEntity() {
        return new ValidationReportBuilder().validatorReport( validatorReport ).filename( xFileName ).build();
    }

}
