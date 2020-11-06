package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class UnsupportedParameterValue extends XPlanApiException {

    private static final String EXCEPTION_MESSAGE = "Unexpected value of parameter %s: %s";

    public UnsupportedParameterValue( String parameterName, String parameterValue ) {
        super( String.format( EXCEPTION_MESSAGE, parameterName, parameterValue ) );
    }

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getStatusCode();
    }

}