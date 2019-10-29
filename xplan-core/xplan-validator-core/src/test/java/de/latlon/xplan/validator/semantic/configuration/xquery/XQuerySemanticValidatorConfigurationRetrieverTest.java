package de.latlon.xplan.validator.semantic.configuration.xquery;

import de.latlon.xplan.validator.semantic.SemanticValidatorRule;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.*;
import static java.nio.file.Paths.get;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for <link>XQuerySemanticValidatorConfigurationRetriever</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorConfigurationRetrieverTest {

    @Test
    public void testRetrieveConfigurationShouldReturnCorrectNumberOfRules()
        throws Exception {
        Path rulesPath =
            get( XQuerySemanticValidatorConfigurationRetriever.class.getResource( "rules" ).toURI() );
        XQuerySemanticValidatorConfigurationRetriever configurationRetriever =
            new XQuerySemanticValidatorConfigurationRetriever(
                rulesPath );
        SemanticValidatorConfiguration configuration = configurationRetriever.retrieveConfiguration();
        List<SemanticValidatorRule> rules = configuration.getAllRules();

        assertThat( rules.size(), is( 10 ) );
    }

    @Test
    public void testRetrieveConfigurationShouldRetrieveAllFilesRecursively()
        throws Exception {
        Path rulesPath = get( XQuerySemanticValidatorConfigurationRetriever.class.getResource( "rules" ).toURI() );
        XQuerySemanticValidatorConfigurationRetriever retriever =
            new XQuerySemanticValidatorConfigurationRetriever( rulesPath );
        SemanticValidatorConfiguration configuration = retriever.retrieveConfiguration();

        assertThat( configuration.getRules( singletonList( IGNORE_XP ) ).size(), is( 8 ) );
        assertThat( configuration.getRules( singletonList( IGNORE_SO ) ).size(), is( 9 ) );
        assertThat( configuration.getRules( XPLAN_40, singletonList( NONE ) ).size(), is( 7 ) );
    }

}