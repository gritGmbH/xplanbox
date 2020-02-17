package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.manager.synthesizer.expression.Xpath;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RuleParserTest {

    @Test
    public void testParse_Xpath() {
        RuleParser ruleParser = new RuleParser( "BP_Plan", "Name", null );
        Xpath xpath = (Xpath) ruleParser.parse( "xpath('xplan:drehwinkel/text()')" );

        assertThat( xpath.getDefaultValue(), is( nullValue() ) );
    }

    @Test
    public void testParse_XpathWithDefaultValue() {
        RuleParser ruleParser = new RuleParser( "BP_Plan", "Name", null );
        Xpath xpath = (Xpath) ruleParser.parse( "xpath('xplan:drehwinkel/text()', 42.0)" );

        assertThat( xpath.getDefaultValue(), is( 42.0 ) );
    }

}