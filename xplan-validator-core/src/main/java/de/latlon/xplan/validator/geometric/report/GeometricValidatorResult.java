package de.latlon.xplan.validator.geometric.report;

import java.util.Collections;
import java.util.List;

import org.deegree.cs.coordinatesystems.ICRS;

import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.report.ReportUtils.SkipCode;

/**
 * contains the validator result of the geometric validator
 * 
 * @author bingel
 */

public class GeometricValidatorResult extends ValidatorResult {

    private static final String VALIDATION_TYPE_NAME = "Geometrische Validierung";

    private final List<String> warnings;

    private final List<String> errors;

    private final List<BadGeometry> badGeometries;

    private final ICRS crs;

    public GeometricValidatorResult( SkipCode skipCode ) {
        super( skipCode );
        this.warnings = Collections.emptyList();
        this.badGeometries = Collections.emptyList();
        this.crs = null;
        this.errors = Collections.emptyList();
    }

    public GeometricValidatorResult( List<String> warnings, List<String> errors, List<BadGeometry> badGeometries,
                                     ICRS crs, boolean isValid ) {
        super( isValid );
        this.warnings = warnings;
        this.badGeometries = badGeometries;
        this.crs = crs;
        this.errors = errors;
    }

    public String getType() {
        return VALIDATION_TYPE_NAME;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public List<String> getErrors() {
        return errors;
    }

    public List<BadGeometry> getBadGeometries() {
        return badGeometries;
    }

    public ICRS getCrs() {
        return crs;
    }

    @Override
    public String toString() {
        return "GeometricValidatorResult{" + "warnings=" + warnings + ", errors=" + errors + '}';
    }
}