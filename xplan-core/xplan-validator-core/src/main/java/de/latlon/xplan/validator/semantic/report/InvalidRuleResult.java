package de.latlon.xplan.validator.semantic.report;

import static de.latlon.xplan.validator.semantic.report.ValidationResultType.ERROR;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidRuleResult {

    private static final String UNKNOWN_GML_ID = "unbekannt";

    private final String gmlId;

    private final ValidationResultType resultType;

    private final String message;

    public InvalidRuleResult( String message ) {
        this( UNKNOWN_GML_ID, message );
    }

    public InvalidRuleResult( String gmlId, String message ) {
        this( gmlId, ERROR, message );
    }

    public InvalidRuleResult( String gmlId, ValidationResultType resultType, String message ) {
        this.gmlId = gmlId;
        this.resultType = resultType;
        this.message = message;
    }

    public String getGmlId() {
        return gmlId;
    }

    public ValidationResultType getResultType() {
        return resultType;
    }

    public String getMessage() {
        return message;
    }

}
