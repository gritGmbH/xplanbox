package de.latlon.xplanbox.api.manager.exception;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidPlan extends Exception {

    private static final String EXCEPTION_MESSAGE = "Invalid plan";

    public InvalidPlan() {
        super( EXCEPTION_MESSAGE );
    }

}
