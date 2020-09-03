package de.latlon.xplanbox.api.manager.exception;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class UnsupportedParameterValue extends Exception {

    private static final String EXCEPTION_MESSAGE = "Unexpected value of parameter %s: %s";

    public UnsupportedParameterValue( String parameterName, String parameterValue ) {
        super( String.format( EXCEPTION_MESSAGE, parameterName, parameterValue ) );
    }

}