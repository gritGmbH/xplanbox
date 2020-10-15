package de.latlon.xplanbox.api.commons.exception;

import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class XPlanApiException extends Exception {

    public XPlanApiException( String message ) {
        super( message );
    }

    public XPlanApiException( String message, Exception e ) {
        super( message, e );
    }

    public int getStatusCode() {
        return INTERNAL_SERVER_ERROR.getStatusCode();
    }

    public Object getResponseEntity() {
        return null;
    }

}