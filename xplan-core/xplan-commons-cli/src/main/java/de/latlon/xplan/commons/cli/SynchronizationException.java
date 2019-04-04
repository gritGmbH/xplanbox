package de.latlon.xplan.commons.cli;

/**
 * Indicates an failure during the synchronization.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SynchronizationException extends Exception {

    public SynchronizationException( String message ) {
        super( message );
    }

    public SynchronizationException( Exception e ) {
        super( e );
    }

    public SynchronizationException( String message, Exception e ) {
        super( message, e );
    }
}