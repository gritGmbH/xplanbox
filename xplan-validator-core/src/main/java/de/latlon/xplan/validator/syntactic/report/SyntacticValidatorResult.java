package de.latlon.xplan.validator.syntactic.report;

import java.util.List;

import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorResult;

/**
 * contains the validator result of the syntactic validator
 *
 * @author bingel
 */
public class SyntacticValidatorResult extends ValidatorResult {

    private static final String VALIDATION_TYPE_NAME = "Syntaktische Validierung";

    private final List<String> messages;

    /**
     * @param messages
     *            list of messages describing validation errors, may be empty but never <code>null</code>
     * @param isValid
     *            <code>true</code> if the xplan is syntactical valid, <code>false</code> otherwise
     * @param validatorDetails
     *            details about the validation, mey be <code>null</code>
     */
    public SyntacticValidatorResult( List<String> messages, boolean isValid, ValidatorDetail validatorDetails ) {
        super( isValid, validatorDetails );
        this.messages = messages;
    }

    /**
     * @return list of messages describing validation errors, may be empty but never <code>null</code>
     */
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public String getType() {
        return VALIDATION_TYPE_NAME;
    }

    @Override
    public String toString() {
        return "SyntacticValidatorResult{" + "messages=" + messages + '}';
    }

}