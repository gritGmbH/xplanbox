package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidPlanName extends XPlanApiException {

    private static final String EXCEPTION_MESSAGE = "Plan with name %s is not known!";

    public InvalidPlanName( String planName ) {
        super( String.format( EXCEPTION_MESSAGE, planName ) );
    }

    @Override
    public int getStatusCode() {
        return NOT_FOUND.getStatusCode();
    }

}
