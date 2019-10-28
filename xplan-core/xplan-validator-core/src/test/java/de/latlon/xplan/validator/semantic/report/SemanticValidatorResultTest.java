package de.latlon.xplan.validator.semantic.report;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Text.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 */
public class SemanticValidatorResultTest {

    private static final String NAME_1 = "1.1.3";

    private static final String NAME_2 = "1.10.2";

    private static final String NAME_3 = "1.2.1";

    private static final String NAME_4 = "2";

    private static final String NAME_5 = "1.2.1.3";

    private static final String NAME_INVALID = "INVALID";

    @Test
    public void getRules() {
        SemanticValidatorResult result = retrieveResultWithRules();

        List<RuleResult> rules = result.getRules();

        assertThat( rules.get( 0 ).getName(), is( NAME_1 ) );
        assertThat( rules.get( 1 ).getName(), is( NAME_3 ) );
        assertThat( rules.get( 2 ).getName(), is( NAME_5 ) );
        assertThat( rules.get( 3 ).getName(), is( NAME_2 ) );
        assertThat( rules.get( 4 ).getName(), is( NAME_4 ) );
    }

    @Test
    public void getRulesWithInvalid() {
        SemanticValidatorResult result = retrieveResultWithRules();
        result.addRule( NAME_INVALID, "invalid", Collections.singletonList( "id_i" )  );

        List<RuleResult> rules = result.getRules();

        assertThat( rules.get( 5 ).getName(), is( NAME_INVALID ) );
    }

    private SemanticValidatorResult retrieveResultWithRules() {
        SemanticValidatorResult result = new SemanticValidatorResult();
        result.addRule( NAME_1, "message1", Collections.emptyList() );
        result.addRule( NAME_2, "message2", Collections.singletonList( "id_2" ) );
        result.addRule( NAME_3, "message3", Collections.emptyList() );
        result.addRule( NAME_4, "message4", Collections.emptyList() );
        result.addRule( NAME_5, "message5", Collections.emptyList() );
        return result;
    }

}