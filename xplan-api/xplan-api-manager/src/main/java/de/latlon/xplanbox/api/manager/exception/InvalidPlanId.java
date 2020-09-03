package de.latlon.xplanbox.api.manager.exception;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidPlanId extends Exception {

    private static final String EXCEPTION_MESSAGE = "Plan with ID %s is not known!";

    public InvalidPlanId( String planId ) {
        super( String.format( EXCEPTION_MESSAGE, planId ) );

    }

}
