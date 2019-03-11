package de.latlon.xplan.validator.semantic.xquery;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_SO;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_XP;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.NONE;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import net.sf.saxon.trans.XPathException;

import org.junit.Test;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.ValidatorException;

/**
 * Tests for <link>XQuerySemanticValidatorRule</link>
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorRuleTest {

    @Test
    public void testValidRuleShouldReturnTrue()
                    throws Exception {
        ByteArrayInputStream byteArrayInputStream = getStream();
        XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule( byteArrayInputStream, "name",
                        XPLAN_41, NONE );
        assertThat( validatorRule.validate( retrieveArchive( "xplan41/BP2070.zip" ) ), is( true ) );
    }

    @Test
    public void testNonMatchingRuleShouldReturnFalse()
                    throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream( "exists(/notThere)".getBytes() );
        XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule( byteArrayInputStream, "name",
                        XPLAN_41, NONE );
        assertThat( validatorRule.validate( retrieveArchive( "xplan41/BP2070.zip" ) ), is( false ) );
    }

    @Test(expected = ValidatorException.class)
    public void testRuleReturningMultipleLinesShouldThrowException()
                    throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream( "//*".getBytes() );
        XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule( byteArrayInputStream, "name",
                        XPLAN_41, NONE );
        validatorRule.validate( retrieveArchive( "xplan41/BP2070.zip" ) );
    }

    @Test(expected = XPathException.class)
    public void testDefectedRuleShouldThrowException()
                    throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream( "<<".getBytes() );
        ( new XQuerySemanticValidatorRule( byteArrayInputStream, "name", XPLAN_41, NONE ) ).validate( retrieveArchive( "xplan41/BP2070.zip" ) );
    }

    @Test
    public void testIsIgnoredByOptionShouldReturnTrue()
                    throws Exception {
        XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule( getStream(), "name", XPLAN_41,
                        IGNORE_SO );
        boolean ignoredByOption = validatorRule.isIgnoredByOption( IGNORE_SO );
        assertThat( ignoredByOption, is( true ) );
    }

    @Test
    public void testIsIgnoredByOptionShouldReturnFalse()
                    throws Exception {
        XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule( getStream(), "name", XPLAN_41,
                        IGNORE_XP );
        boolean ignoredByOption = validatorRule.isIgnoredByOption( IGNORE_SO );
        assertThat( ignoredByOption, is( false ) );
    }

    @Test
    public void testIsIgnoredByOptionNoneShouldReturnFalse()
                    throws Exception {
        XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule( getStream(), "name", XPLAN_41,
                        NONE );
        boolean ignoredByOption = validatorRule.isIgnoredByOption( IGNORE_SO );
        assertThat( ignoredByOption, is( false ) );
    }

    @Test
    public void testIsIgnoredByOptionWithNoneShouldReturnFalse()
                    throws Exception {
        XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule( getStream(), "name", XPLAN_41,
                        IGNORE_SO );
        boolean ignoredByOption = validatorRule.isIgnoredByOption( NONE );
        assertThat( ignoredByOption, is( false ) );
    }

    @Test
    public void testIsIgnoredByOptionWithNullShouldReturnFalse()
                    throws Exception {
        XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule( getStream(), "name", XPLAN_41,
                        IGNORE_SO );
        boolean ignoredByOption = validatorRule.isIgnoredByOption( null );
        assertThat( ignoredByOption, is( false ) );
    }

    private ByteArrayInputStream getStream() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream( "exists(/)".getBytes() );
        return byteArrayInputStream;
    }

    private XPlanArchive retrieveArchive( String name )
                    throws IOException {
        XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
        return archiveCreator.createXPlanArchive( name, ResourceAccessor.readResourceStream( name ) );
    }

}