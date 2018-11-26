package de.latlon.xplan.validator.geometric.report;

import java.util.ArrayList;
import java.util.List;

import org.deegree.geometry.Geometry;

/**
 * contains a defect geometry and its error-Strings
 *
 * @author bingel
 */

public class BadGeometry {

    Geometry geometry;

    private final List<String> errors = new ArrayList<>();

    public BadGeometry() {
    }

    public BadGeometry( Geometry geometry ) {
        this.geometry = geometry;
    }

    public void setGeometry( Geometry geometry ) {
        this.geometry = geometry;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getErrorsSingleString() {
        StringBuilder allErrors = new StringBuilder();
        for ( String error : errors ) {
            allErrors.append( error );
            allErrors.append( "; " );
        }
        return allErrors.toString();
    }

    public void addError( String err ) {
        errors.add( err );
    }
}
