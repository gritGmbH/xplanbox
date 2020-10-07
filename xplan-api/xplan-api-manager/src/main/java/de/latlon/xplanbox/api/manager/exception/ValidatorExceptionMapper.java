package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplan.validator.ValidatorException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Provider
public class ValidatorExceptionMapper implements ExceptionMapper<ValidatorException> {

    @Override
    public Response toResponse( ValidatorException exception ) {
        return Response.status( BAD_REQUEST ).entity( exception.getMessage() ).build();
    }
}
