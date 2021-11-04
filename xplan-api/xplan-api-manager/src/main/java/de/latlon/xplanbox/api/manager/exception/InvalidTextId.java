package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidTextId extends XPlanApiException {

    private static final String EXCEPTION_MESSAGE = "Text with ID %s of Plan with ID %s is not known or Plan with ID %s contains multiple Text with the same id!";

    public InvalidTextId( String planId, String resourceId ) {
        super( String.format( EXCEPTION_MESSAGE, planId, resourceId ) );
    }

    @Override
    public int getStatusCode() {
        return NOT_FOUND.getStatusCode();
    }
}
