package de.latlon.xplan.validator.configuration;

import java.nio.file.Path;

/**
 * Encapsulates the validator configuration.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class ValidatorConfiguration {

    private final Path validationReportDirectory;

    private final Path validationRulesDirectory;

    /**
     * @param validationReportDirectory
     *                         directory where validation reports are saved, never <code>null</code>
     * @param validationRulesDirectory
     *                         directory containing the semantic validation rules, may be <code>null</code>
     */
    public ValidatorConfiguration( Path validationReportDirectory, Path validationRulesDirectory ) {
        checkParameters( validationReportDirectory );
        this.validationReportDirectory = validationReportDirectory;
        this.validationRulesDirectory = validationRulesDirectory;
    }

    /**
     * Returns the directory where validation reports are saved.
     * 
     * @return directory where validation reports are saved, never <code>null</code>
     */
    public Path getValidationReportDirectory() {
        return validationReportDirectory;
    }

    /**
     * Returns the directory containing the semantic validation rules.
     *
     * @return directory containing the semantic validation rules, may be <code>null</code>
     */
    public Path getValidationRulesDirectory() {
        return validationRulesDirectory;
    }

    private void checkParameters( Path validationReportDirectory ) {
        if ( validationReportDirectory == null )
            throw new IllegalArgumentException( "validationReportDirectory must not be null!" );
    }
}