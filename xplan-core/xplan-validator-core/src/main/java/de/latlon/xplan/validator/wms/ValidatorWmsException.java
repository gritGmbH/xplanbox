package de.latlon.xplan.validator.wms;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorWmsException extends Exception {

    public ValidatorWmsException( String msg ) {
        super( msg );
    }

    public ValidatorWmsException( String msg, Exception e ) {
        super( msg, e );
    }

    public ValidatorWmsException( Throwable cause ) {
        super( cause );
    }
}