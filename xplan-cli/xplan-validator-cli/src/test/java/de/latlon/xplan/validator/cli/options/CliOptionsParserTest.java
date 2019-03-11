package de.latlon.xplan.validator.cli.options;

import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationType;
import org.apache.commons.cli.ParseException;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for <link>CliOptionsParser</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class CliOptionsParserTest {

  @Test
  public void testParseValidationOptions() throws IOException, ParseException {
    CliOptionsParser parser = new CliOptionsParser();
    String[] optionsToParse = new String[] { "-validate", ".",
                                             "-name", "validation",
                                             "-vo", "ignore-orientation",
                                             "-vo", "node-tolerance=1" };
    CliOptions options = parser.parse( optionsToParse );
    List<ValidationOption> parsedOptions = options.getVoOptions();
    assertThat( parsedOptions.size(), is( 2 ) );
    assertThat( parsedOptions, containsOption( "ignore-orientation", null ) );
    assertThat( parsedOptions, containsOption( "node-tolerance", "1" ) );
  }

  @Test
  public void testParseValidationOptionsWithNullArgumentShouldReturnEmptyList() {
    List<ValidationOption> parsedOptions = new CliOptionsParser().parseValidationOptions( null );
    assertThat( parsedOptions.size(), is( 0 ) );
  }

  @Test(expected = IllegalArgumentException.class)
  public void testParseValidationOptionsWithEmptyNameShouldFail() {
    new CliOptionsParser().parseValidationOptions( new String[] { "" } );
  }

  @Test(expected = ParseException.class)
  public void testInvalidInputIllegalArgument()
      throws Exception {
    String[] invalidArgs = new String[] { "-invalid", "name", "-validate", "validation" };
    new CliOptionsParser().parse( invalidArgs );
  }

  @Test(expected = ParseException.class)
  public void testInvalidInputWrongArgumentNumber()
      throws Exception {
    String[] invalidArgs = new String[] { "-validate", "name" };
    new CliOptionsParser().parse( invalidArgs );
  }

  @Test(expected = ParseException.class)
  public void testInvalidInputNoArgs()
      throws Exception {
    new CliOptionsParser().parse( new String[0] );
  }

  @Test
  public void testValidateWithVoOptionsExpectCorrectOptions() throws Exception {
    String[] args = new String[] { "-validate", "path",
                                   "-name", "validation",
                                   "-vo", "ignore-orientation",
                                   "-vo", "ignore-self-intersection",
                                   "-vo", "node-tolerance=1" };
    CliOptions options = new CliOptionsParser().parse( args );
    List<ValidationOption> voOptions = options.getVoOptions();
    assertThat( voOptions, allOf(
        hasCorrectArgument( "node-tolerance", "1" ),
        hasCorrectArgument( "ignore-orientation", null ),
        hasCorrectArgument( "ignore-self-intersection", null ) ) );
  }

  @Test
  public void testNoVtypeShouldDefaultToSemantic() throws Exception {
    String[] args = new String[] { "-validate", "path", "-name", "validation" };
    CliOptions options = new CliOptionsParser().parse( args );
    assertThat( options.getValidationType(), is( ValidationType.SEMANTIC ) );
  }

  @Test
  public void testVoShouldNotBeRequired() throws Exception {
    String[] args = new String[] { "-validate", "path", "-name", "validation" };
    new CliOptionsParser().parse( args );
  }

  private BaseMatcher<List<ValidationOption>> hasCorrectArgument( final String parameter, final String value ) {
    return new BaseMatcher<List<ValidationOption>>() {

      @Override
      public boolean matches( Object item ) {
        //noinspection unchecked
        for ( ValidationOption validationOption : (List<ValidationOption>) item ) {
          if ( validationOption.getName().equals( parameter ) )
            if ( validationOption.getArgument() != null )
              return validationOption.getArgument().equals( value );
            else
              return value == null;
        }
        return false;
      }

      @Override
      public void describeTo( Description description ) {
        description.appendText( "ValidationOptions should contain " + parameter );
      }
    };
  }

  private Matcher<? super List<ValidationOption>> containsOption( final String name, final String argument ) {
    return new BaseMatcher<List<ValidationOption>>() {

      @Override
      public boolean matches( Object item ) {
        @SuppressWarnings("unchecked")
        List<ValidationOption> validationOptions = (List<ValidationOption>) item;
        for ( ValidationOption validationOption : validationOptions ) {
          String optName = validationOption.getName();
          String optArgument = validationOption.getArgument();
          if ( optName.equals( name )
               && ( optArgument == null && argument == null || optArgument != null && optArgument.equals(
              argument ) ) ) {
            return true;
          }
        }
        return false;
      }

      @Override
      public void describeTo( Description description ) {
        description.appendText( "Expected is an option with name " + name + "and argument " + argument );
      }
    };
  }
}