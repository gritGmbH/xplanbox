package de.latlon.xplan.validator.semantic.report;

import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.validator.semantic.report.ValidationResultType.ERROR;

/**
 * Indicates invalid features.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidFeaturesResult {

	private static final String UNKNOWN_GML_ID = "unbekannt";

	private final List<String> gmlIds = new ArrayList<>();

	private final ValidationResultType resultType;

	private final String message;

	/**
	 * Unknown feature id, validation type is error.
	 * @param message the error message, should not be <code>null</code>
	 */
	public InvalidFeaturesResult(String message) {
		this(UNKNOWN_GML_ID, message);
	}

	/**
	 * Validation type is error.
	 * @param gmlId of the feature, never <code>null</code>
	 * @param message the error message, should not be <code>null</code>
	 */
	public InvalidFeaturesResult(String gmlId, String message) {
		this(gmlId, ERROR, message);
	}

	/**
	 * @param gmlId of the feature, never <code>null</code>
	 * @param resultType type of the validation, never <code>null</code>
	 * @param message the message, should not be <code>null</code>
	 */
	public InvalidFeaturesResult(String gmlId, ValidationResultType resultType, String message) {
		this.resultType = resultType;
		this.message = message;
		addGmlId(gmlId);
	}

	public void addGmlId(String gmlId) {
		this.gmlIds.add(gmlId);
	}

	/**
	 * @return the gmlId of the feature, never <code>null</code>
	 */
	public List<String> getGmlIds() {
		return gmlIds;
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
