package de.latlon.xplan.validator.geometric.inspector;

/**
 * Indicates an iinvalid geometry.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidGeometryException extends Exception {

    public InvalidGeometryException( String msg ) {
        super( msg );
    }

    public InvalidGeometryException( Exception e ) {
        super( e );
    }

}