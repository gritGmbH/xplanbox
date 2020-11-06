package de.latlon.xplan.manager.web.shared;

/**
 * Indicates, that an configuration exception occurred.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ConfigurationException extends Exception {

    private static final long serialVersionUID = 3735020561258189807L;

    public ConfigurationException() {
    }

    public ConfigurationException( String message ) {
        super( message );
    }

    public ConfigurationException( Exception e ) {
        super( e );
    }

    public ConfigurationException( String message, Exception e ) {
        super( message, e );
    }
}