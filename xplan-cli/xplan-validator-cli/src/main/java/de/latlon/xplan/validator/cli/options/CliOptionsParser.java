package de.latlon.xplan.validator.cli.options;

import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationType;
import org.apache.commons.cli.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.validator.cli.options.CliOptionsParser.CLIParams.*;


/**
 * Parses CLI Options from argument array
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class CliOptionsParser {

  private static final boolean HAS_ARG = true;

  /**
   * Parse command line options for the XPlanValidator CLI
   *
   * @param args
   *     never <code>null</code>, may contain the following name: the name of the validation run validate: path to the
   *     archive to validate vo: validation options vtype[semantic, geometric, syntactic]: type of validation, defaults
   *     to semantic
   * @return CliOptions parsed from the arguments
   * @throws ParseException
   */
  public CliOptions parse( String[] args ) throws ParseException {
    CommandLine commandLine = ( new BasicParser() ).parse( createOptions(), args );
    File archive = parseArchive( commandLine );
    String validationName = parseValidationName( commandLine, archive );
    List<ValidationOption> voOptions = parseValidationOptions( commandLine.getOptionValues( is( VO ) ) );
    ValidationType validationType = parseValidationTypeSemanticIfNone(
        commandLine.getOptionValue( is( CLIParams.VALIDATE_TYPE ) ) );
    return new CliOptions( validationName, voOptions, archive, validationType );
  }

  private File parseArchive( CommandLine commandLine ) {
    return new File( commandLine.getOptionValue( is( VALIDATE ) ) );
  }

  private String parseValidationName( CommandLine commandLine, File defaultValue ) {
    String name = commandLine.getOptionValue( is( NAME ) );
    if ( name != null )
      return name;
    String fileName = defaultValue.getName();
    return fileName.substring( 0, fileName.indexOf( "." ) );
  }

  private Options createOptions() {
    Options options = new Options();
    Option zipFileNameOption = new Option( is( VALIDATE ), HAS_ARG, "zip file path" );
    Option nameOption = new Option( is( NAME ), HAS_ARG, "name of the validation" );
    Option voOption = new Option( is( VO ), HAS_ARG, "validation options" );
    Option xqOption = new Option( is( XQUERY ), HAS_ARG, "xquery file path" );
    Option vTypeOption = new Option( is( VALIDATE_TYPE ), HAS_ARG, "" );
    nameOption.setRequired( false );
    zipFileNameOption.setRequired( true );
    voOption.setRequired( false );
    xqOption.setRequired( false );
    vTypeOption.setRequired( false );
    options.addOption( zipFileNameOption ).addOption( nameOption ).addOption( xqOption )
        .addOption( vTypeOption )
        .addOption( voOption );
    return options;
  }

  private ValidationType parseValidationTypeSemanticIfNone( String s ) {
    if ( s == null )
      return ValidationType.SEMANTIC;
    switch ( s ) {
    case "syntax":
      return ValidationType.SYNTACTIC;
    case "geometric":
      return ValidationType.GEOMETRIC;
    case "semantic":
      return ValidationType.SEMANTIC;
    default:
      return ValidationType.SEMANTIC;
    }
  }


  /**
   * Parses validation related CLI options
   *
   * @param optionValues
   *     the cli options, never <code>null</code>
   * @return List of validation options, never <code>null</code>
   */
  List<ValidationOption> parseValidationOptions( String[] optionValues ) {
    List<ValidationOption> validationOptions = new ArrayList<>();
    if ( optionValues != null )
      for ( String optionValue : optionValues ) {
        if ( optionValue.contains( "=" ) ) {
          String[] nameAndArgument = optionValue.split( "=", 2 );
          validationOptions.add( new ValidationOption( nameAndArgument[0], nameAndArgument[1] ) );
        } else {
          validationOptions.add( new ValidationOption( optionValue ) );
        }
      }
    return validationOptions;
  }

  /**
   * Discriminates CLI params
   */
  enum CLIParams {
    NAME( "name" ),
    VALIDATE( "validate" ),
    VO( "vo" ),
    XQUERY( "xq" ), VALIDATE_TYPE( "vtype" );

    private final String option;

    private CLIParams( String option ) {
      this.option = option;
    }

    public String option() {
      return option;
    }

    static String is( CLIParams param ) {
      return param.option();
    }

  }
}