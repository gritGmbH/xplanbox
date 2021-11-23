package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidDokumentId extends XPlanApiException {

    private static final String EXCEPTION_MESSAGE = "Dokument with ID %s of Plan with ID %s is not known or Plan with ID %s contains multiple Dokumente with the same id!";

    public InvalidDokumentId( String planId, String resourceId ) {
        super( String.format( EXCEPTION_MESSAGE, planId, resourceId, planId ) );
    }

    @Override
    public int getStatusCode() {
        return NOT_FOUND.getStatusCode();
    }
}
