package de.latlon.xplanbox.api.commons.exception;

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
        Response.ResponseBuilder status = Response.status( exception.getStatusCode() );
        Object responseEntity = exception.getResponseEntity();
        if ( responseEntity != null )
            status.entity( responseEntity );
        return status.build();
    }

}