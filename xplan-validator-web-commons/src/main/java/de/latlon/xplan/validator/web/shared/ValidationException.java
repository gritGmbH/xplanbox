package de.latlon.xplan.validator.web.shared;

/**
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ValidationException extends Exception {

    private static final long serialVersionUID = -6311980632011601926L;

    public ValidationException() {
    }

    public ValidationException( String message ) {
        super( message );
    }

}