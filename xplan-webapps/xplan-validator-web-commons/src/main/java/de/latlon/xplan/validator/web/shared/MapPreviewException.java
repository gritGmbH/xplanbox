package de.latlon.xplan.validator.web.shared;

import java.io.Serializable;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MapPreviewException extends Exception implements Serializable {

    public MapPreviewException() {
    }

    public MapPreviewException( String message ) {
        super( message );
    }
}
