package de.latlon.xplan.validator;

/**
 * 
 * Indicates an exception during validation.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class ValidatorException extends Exception {

    private static final long serialVersionUID = 6819754379702774873L;

    public ValidatorException() {
        super();
    }

    public ValidatorException( String message ) {
        super( message );
    }

    public ValidatorException( String message, Throwable cause ) {
        super( message, cause );
    }

    public ValidatorException( Throwable cause ) {
        super( cause );
    }

}