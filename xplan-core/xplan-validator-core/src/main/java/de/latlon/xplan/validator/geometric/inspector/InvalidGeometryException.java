package de.latlon.xplan.validator.geometric.inspector;

import com.vividsolutions.jts.geom.TopologyException;

/**
 * Indicates an iinvalid geometry.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidGeometryException extends Exception {

    public InvalidGeometryException( String msg ) {
        super( msg );
    }

    public InvalidGeometryException( TopologyException e ) {
        super( e );
    }

}