package de.latlon.xplan.validator.semantic.configuration;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_SO;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.semantic.SemanticValidatorRule;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class SemanticValidatorConfigurationTest {

    @Test
    public void testGetRulesForVersion40AndOptionToIgnore() {
        SemanticValidatorConfiguration configuration = createConfigurationWithRules();
        List<SemanticValidatorRule> rules = configuration.getRules( XPLAN_40, singletonList( IGNORE_SO ) );
        assertThat( rules.size(), is( 1 ) );
    }

    @Test
    public void testGetRulesForVersion41AndOptionToIgnore() {
        SemanticValidatorConfiguration configuration = createConfigurationWithRules();
        List<SemanticValidatorRule> rules = configuration.getRules( XPLAN_41, singletonList( IGNORE_SO ) );
        assertThat( rules.size(), is( 2 ) );
    }

    @Test
    public void testGetRulesForVersion41AndEmptyOptions() {
        SemanticValidatorConfiguration configuration = createConfigurationWithRules();
        List<SemanticValidatorRule> rules = configuration.getRules( XPLAN_40,
                                                                    Collections.<SemanticValidationOptions> emptyList() );
        assertThat( rules.size(), is( 2 ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRUlesWithNullVersionShouldFail() {
        SemanticValidatorConfiguration configuration = createConfigurationWithRules();
        configuration.getRules( null, singletonList( IGNORE_SO ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRUlesWithNullOptionsShouldFail() {
        SemanticValidatorConfiguration configuration = createConfigurationWithRules();
        configuration.getRules( XPLAN_40, null );
    }

    private SemanticValidatorConfiguration createConfigurationWithRules() {
        SemanticValidatorConfiguration configuration = new SemanticValidatorConfiguration();
        configuration.addRule( createMockedRule( XPLAN_41, true ) );
        configuration.addRule( createMockedRule( XPLAN_41, false ) );
        configuration.addRule( createMockedRule( XPLAN_40, true ) );
        configuration.addRule( createMockedRule( null, false ) );
        return configuration;
    }

    private SemanticValidatorRule createMockedRule( XPlanVersion xplanVersion, boolean ignoreOption ) {
        SemanticValidatorRule rule = mock( SemanticValidatorRule.class );
        when( rule.getXPlanVersion() ).thenReturn( xplanVersion );
        when( rule.isIgnoredByOption( IGNORE_SO ) ).thenReturn( ignoreOption );
        return rule;
    }
}