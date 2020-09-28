package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidPlanId extends XPlanApiException {

    private static final String EXCEPTION_MESSAGE = "Plan with ID %s is not known!";

    public InvalidPlanId( int planId ) {
        super( String.format( EXCEPTION_MESSAGE, planId ) );
    }

    public InvalidPlanId( String planId ) {
        super( String.format( EXCEPTION_MESSAGE, planId ) );
    }

    @Override
    public int getStatusCode() {
        return NOT_FOUND.getStatusCode();
    }

}
