package de.latlon.xplan.inspire.plu.transformation;

/**
 * Indicates that the transformation failed.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformationException extends Exception {

    public TransformationException( String message, Throwable cause ) {
        super( message, cause );
    }

}
