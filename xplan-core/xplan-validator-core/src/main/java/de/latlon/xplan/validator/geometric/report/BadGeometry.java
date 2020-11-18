package de.latlon.xplan.validator.geometric.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deegree.geometry.Geometry;

/**
 * contains a defect geometry and its error-Strings
 *
 * @author bingel
 */

public class BadGeometry {

    private Geometry originalGeometry;

    private final List<String> errors = new ArrayList<>();

    private final Map<String, Geometry> markerGeometries = new HashMap<>();

    public BadGeometry() {
    }

    public BadGeometry( Geometry originalGeometry, String error ) {
        this.originalGeometry = originalGeometry;
        addError( error );
    }

    public void setOriginalGeometry( Geometry originalGeometry ) {
        this.originalGeometry = originalGeometry;
    }

    public Geometry getOriginalGeometry() {
        return originalGeometry;
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

    public Map<String, Geometry> getMarkerGeometries() {
        return markerGeometries;
    }

    public void addMarkerGeometry( String error, Geometry markerGeometry ) {
        markerGeometries.put( error, markerGeometry );
    }

}
