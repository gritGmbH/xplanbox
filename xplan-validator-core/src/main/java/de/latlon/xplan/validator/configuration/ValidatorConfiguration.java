package de.latlon.xplan.validator.configuration;

import java.io.File;

/**
 * Encapsulates the validator configuration.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public class ValidatorConfiguration {

    private final File validationReportDirectory;

    /**
     * @param validationReportDirectory
     *            directory where validation reports are saved, never <code>null</code>
     */
    public ValidatorConfiguration( File validationReportDirectory ) {
        checkParameters( validationReportDirectory );
        this.validationReportDirectory = validationReportDirectory;
    }

    /**
     * Returns the directory where validation reports are saved.
     * 
     * @return directory where validation reports are saved, never <code>null</code>
     */
    public File getValidationReportDirectory() {
        return validationReportDirectory;
    }

    private void checkParameters( File validationReportDirectory ) {
        if ( validationReportDirectory == null )
            throw new IllegalArgumentException( "validationReportDirectory must not be null!" );
    }

}