package de.latlon.xplan.validator.semantic.report;

import static de.latlon.xplan.validator.semantic.report.ValidationResultType.ERROR;

/**
 * Indicates an invalid feature.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidFeatureResult {

    private static final String UNKNOWN_GML_ID = "unbekannt";

    private final String gmlId;

    private final ValidationResultType resultType;

    private final String message;

    /**
     * Unknown feature id, validation type is error.
     *
     * @param message
     *                 the error message, should not be <code>null</code>
     */
    public InvalidFeatureResult( String message ) {
        this( UNKNOWN_GML_ID, message );
    }

    /**
     * Validation type is error.
     *
     * @param gmlId
     *                 of the feature, never <code>null</code>
     * @param message
     *                 the error message, should not be <code>null</code>
     */
    public InvalidFeatureResult( String gmlId, String message ) {
        this( gmlId, ERROR, message );
    }

    /**
     * @param gmlId
     *                 of the feature, never <code>null</code>
     * @param resultType
     *                 type of the validation, never <code>null</code>
     * @param message
     *                 the message, should not be <code>null</code>
     */
    public InvalidFeatureResult( String gmlId, ValidationResultType resultType, String message ) {
        this.gmlId = gmlId;
        this.resultType = resultType;
        this.message = message;
    }

    /**
     * @return the gmlId of the feature, never <code>null</code>
     */
    public String getGmlId() {
        return gmlId;
    }

    /**
     * @return type of the validation, never <code>null</code>
     */
    public ValidationResultType getResultType() {
        return resultType;
    }

    /**
     * @return the message, should not be <code>null</code>d
     */
    public String getMessage() {
        return message;
    }

}
