package de.latlon.xplan.manager.metadata;

/**
 * Indicates an exception during creation of the data services coupling.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DataServiceCouplingException extends Exception {

    public DataServiceCouplingException( String message ) {
        super( message );
    }

    public DataServiceCouplingException( Exception e ) {
        super( e );
    }

    public DataServiceCouplingException( String message, Exception e ) {
        super( message, e );
    }
}
