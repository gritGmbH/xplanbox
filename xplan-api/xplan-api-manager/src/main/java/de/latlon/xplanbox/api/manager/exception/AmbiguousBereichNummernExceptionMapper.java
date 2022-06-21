package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplan.manager.database.AmbiguousBereichNummernException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Provider
public class AmbiguousBereichNummernExceptionMapper implements ExceptionMapper<AmbiguousBereichNummernException> {

	@Override
	public Response toResponse(AmbiguousBereichNummernException e) {
		return Response.status(BAD_REQUEST).entity(e.getMessage()).build();
	}

}
