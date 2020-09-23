package de.latlon.xplanbox.api.commons.exception;

import javax.ws.rs.core.Response;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidXPlanGmlOrArchive extends XPlanApiException {

    public InvalidXPlanGmlOrArchive( String message, Exception e ) {
        super( message, e );
    }

    @Override
    public int getStatusCode() {
        return Response.Status.NOT_ACCEPTABLE.getStatusCode();
    }
}
