package de.latlon.xplanbox.api.commons.exception;

import de.latlon.xplanbox.api.commons.v1.model.XPlanApiExceptionReport;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Provider
public class XPlanApiExceptionMapper implements ExceptionMapper<XPlanApiException> {

    @Override
    public Response toResponse( XPlanApiException exception ) {
        XPlanApiExceptionReport exceptionReport = new XPlanApiExceptionReport( exception.getMessage() );
        return Response.status( exception.getStatusCode() ).entity( exceptionReport ).build();
    }

}