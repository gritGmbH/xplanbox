package de.latlon.xplan.validator.semantic.configuration;

import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_SO;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_XP;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.NONE;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.getByDirectoryName;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.getByOption;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.latlon.xplan.validator.web.shared.ValidationOption;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class SemanticValidationOptionsTest {

    @Test
    public void testGetByDirectoryNameSo() {
        SemanticValidationOptions soOption = getByDirectoryName( "so" );
        assertThat( soOption, is( IGNORE_SO ) );
    }

    @Test
    public void testGetByDirectoryNameXp() {
        SemanticValidationOptions soOption = getByDirectoryName( "xp" );
        assertThat( soOption, is( IGNORE_XP ) );
    }

    @Test
    public void testGetByDirectoryNameUnknown() {
        SemanticValidationOptions soOption = getByDirectoryName( "unknown" );
        assertThat( soOption, is( NONE ) );
    }

    @Test
    public void testGetByDirectoryNameEmpty() {
        SemanticValidationOptions soOption = getByDirectoryName( "" );
        assertThat( soOption, is( NONE ) );
    }

    @Test
    public void testGetByOptionSo() {
        SemanticValidationOptions soOption = getByOption( new ValidationOption( "ignore-so" ) );
        assertThat( soOption, is( IGNORE_SO ) );
    }

    @Test
    public void testGetByOptionXp() {
        SemanticValidationOptions soOption = getByOption( new ValidationOption( "ignore-xp" ) );
        assertThat( soOption, is( IGNORE_XP ) );
    }

    @Test
    public void testGetByOptionUnknown() {
        SemanticValidationOptions soOption = getByOption( new ValidationOption( "unknown" ) );
        assertThat( soOption, is( NONE ) );
    }

    @Test
    public void testIgnoreSoName() {
        assertThat( IGNORE_SO.getOptionName(), is( "ignore-so" ) );
    }

    @Test
    public void testIgnoreXpName() {
        assertThat( IGNORE_XP.getOptionName(), is( "ignore-xp" ) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetByDirectoryNameWithNullShouldFail() {
        getByDirectoryName( null );
    }

}