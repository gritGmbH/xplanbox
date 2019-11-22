package de.latlon.xplan.validator.wms;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MapPreviewCreationException extends Exception {

    public MapPreviewCreationException( String msg ) {
        super( msg );
    }

    public MapPreviewCreationException( String msg, Exception e ) {
        super( msg, e );
    }

    public MapPreviewCreationException( Throwable cause ) {
        super( cause );
    }
}