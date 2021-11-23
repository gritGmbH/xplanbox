package de.latlon.xplanbox.api.manager.exception;

import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DuplicateDokument extends XPlanApiException {

    private static final String EXCEPTION_MESSAGE = "Dokument with referenzName %s und referenzUrl %s of Plan with ID %s already exists, this results in a duplicate ID %s!";

    public DuplicateDokument( String planId, String newDokumentId, String referenzName, String referenzUrl ) {
        super( String.format( EXCEPTION_MESSAGE, referenzName, referenzUrl, planId, newDokumentId ) );
    }

    @Override
    public int getStatusCode() {
        return BAD_REQUEST.getStatusCode();
    }
}
