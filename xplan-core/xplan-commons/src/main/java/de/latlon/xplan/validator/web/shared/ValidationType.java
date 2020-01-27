package de.latlon.xplan.validator.web.shared;

/**
 * Validation type parameters
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public enum ValidationType {
    GEOMETRIC( "geometric" ),
    SYNTACTIC( "syntactic" ),
    SEMANTIC( "semantic" );

    private final String option;

    ValidationType( String option ) {
        this.option = option;
    }

    public String option() {
        return option;
    }

}