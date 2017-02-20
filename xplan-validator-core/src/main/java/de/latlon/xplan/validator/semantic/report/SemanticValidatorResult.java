package de.latlon.xplan.validator.semantic.report;

import java.util.ArrayList;
import java.util.List;

import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorResult;

/**
 * contains the validator result of the semantic validator
 *
 * @author bingel
 */
public class SemanticValidatorResult extends ValidatorResult {

    private static final String VALIDATION_TYPE_NAME = "Semantische Validierung";

    private final List<RuleResult> rules = new ArrayList<>();

    /**
     * Instantiates a new {@link SemanticValidatorResult} without detailsHint.
     */
    public SemanticValidatorResult() {
        this( null );
    }

    /**
     * @param detail
     *            some details about the validation, may be <code>null</code>
     */
    public SemanticValidatorResult( ValidatorDetail detail ) {
        super( detail );
    }

    @Override
    public String getType() {
        return VALIDATION_TYPE_NAME;
    }

    /**
     * Creates a new {@link RuleResult} from the passed values and added them to the list of rules.
     * 
     * @param name
     *            the name of the rule, should not be <code>null</code>
     * @param isValid
     *            <code>true</code> if the rule passed, <code>false</code>otherwise
     * @param message
     *            a description of the rule
     */
    public void addRule( String name, boolean isValid, String message ) {
        rules.add( new RuleResult( name, isValid, message ) );
    }

    /**
     * @return all {@link RuleResult}s.
     */
    public List<RuleResult> getRules() {
        return rules;
    }

    @Override
    public String toString() {
        return "SemanticValidatorResult{" + "rules=" + rules + '}';
    }

}