package de.latlon.xplan.commons.hale;

/**
 * Indicates that the transformation failed.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformationException extends Exception {

    public TransformationException( String message ) {
        super( message );
    }

    public TransformationException( String message, Throwable cause ) {
        super( message, cause );
    }

}
