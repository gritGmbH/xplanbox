package de.latlon.xplan.validator.semantic.configuration;

import de.latlon.xplan.validator.web.shared.ValidationOption;

/**
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public enum SemanticValidationOptions {

    IGNORE_XP( new ValidationOption( "ignore-xp" ), "xp" ), IGNORE_SO( new ValidationOption( "ignore-so" ), "so" ), NONE(
                                                                                                                         new ValidationOption(),
                                                                                                                         null );

    private ValidationOption option;

    private String directorName;

    private SemanticValidationOptions( ValidationOption option, String directorName ) {
        this.option = option;
        this.directorName = directorName;
    }

    /**
     * @return the name of the options, never <code>null</code>
     */
    public String getOptionName() {
        return option.getName();
    }

    /**
     * @param directoryName
     *            the name of the directory a rules is inside if ignored, never <code>null</code>
     * @return the {@link SemanticValidationOptions} with the passed directory name or NONE option if no option exists
     * @throws IllegalArgumentException
     *             if the optionName is <code>null</code>
     */
    public static SemanticValidationOptions getByDirectoryName( String directoryName ) {
        checkDirectoryNameParameter( directoryName );
        for ( SemanticValidationOptions option : values() ) {
            if ( directoryName.equals( option.directorName ) )
                return option;
        }
        return NONE;
    }

    /**
     * @param optionName
     *            the name of the option, never <code>null</code>
     * @return the {@link SemanticValidationOptions} with the passed option name or NONE option if no option exists
     * @throws IllegalArgumentException
     *             if the optionName is <code>null</code>
     */
    public static SemanticValidationOptions getByOption( ValidationOption validationOption ) {
        checkOptionParameter( validationOption );
        for ( SemanticValidationOptions option : values() ) {
            if ( validationOption.equals( option.option ) )
                return option;
        }
        return NONE;
    }

    private static void checkDirectoryNameParameter( String directoryName ) {
        if ( directoryName == null )
            throw new IllegalArgumentException( "directory name must not be null!" );
    }

    private static void checkOptionParameter( ValidationOption validationOption ) {
        if ( validationOption == null )
            throw new IllegalArgumentException( "validationOption name must not be null!" );
    }

}