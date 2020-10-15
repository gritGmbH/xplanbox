package de.latlon.xplan.manager.database;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanNotFoundException extends Exception {

    public PlanNotFoundException( int id ) {
        super( String.format( "Could not find plan with id %s", id ) );
    }

}