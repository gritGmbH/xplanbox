package de.latlon.xplan.manager.metadata;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DataServiceCouplingException extends Exception {

    public DataServiceCouplingException( String message ) {
        super( message );
    }

    public DataServiceCouplingException( Exception e ) {
        super( e );
    }
}
