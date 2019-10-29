package de.latlon.xplan.validator.semantic.report;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.report.ReportUtils.SkipCode;

/**
 * contains the validator result of the semantic validator
 *
 * @author bingel
 */
public class SemanticValidatorResult extends ValidatorResult {

    private static final String VALIDATION_TYPE_NAME = "Semantische Validierung";

    private final List<RuleResult> rules = new ArrayList<>();

    public SemanticValidatorResult( SkipCode skipCode ) {
        super( skipCode );
    }

    /**
     * Instantiates a new {@link SemanticValidatorResult} without detailsHint.
     */
    public SemanticValidatorResult() {
        this( (ValidatorDetail) null );
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
     *  @param name
     *            the name of the rule, should not be <code>null</code>
     * @param message
     *            a description of the rule
     * @param invalidFeatures
     * @return
     */
    public boolean addRule( String name, String message, List<String> invalidFeatures ) {
        boolean isValid = invalidFeatures.isEmpty();
        rules.add( new RuleResult( name, isValid, message, invalidFeatures ) );
        return isValid;
    }

    /**
     * @return all {@link RuleResult}s.
     */
    public List<RuleResult> getRules() {
        return rules.stream().sorted().collect( Collectors.toList() );
    }

    @Override
    public String toString() {
        return "SemanticValidatorResult{" + "rules=" + rules + '}';
    }

}