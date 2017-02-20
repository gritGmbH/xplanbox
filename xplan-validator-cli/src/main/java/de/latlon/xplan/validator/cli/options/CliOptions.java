package de.latlon.xplan.validator.cli.options;

import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationType;

import java.util.List;

/**
 * Encapsulates the CLI options
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class CliOptions {

    private final String validationName;

    private final List<ValidationOption> voOptions;

    private final ValidationType validationType;

    private final String archive;

    public CliOptions( String validationName, List<ValidationOption> voOptions,
                       String archive, ValidationType validationType ) {
        this.validationName = validationName;
        this.voOptions = voOptions;
        this.archive = archive;
        this.validationType = validationType;
    }

    public String getValidationName() {
        return validationName;
    }

    public List<ValidationOption> getVoOptions() {
        return voOptions;
    }

    public String getArchive() {
        return archive;
    }

    public ValidationType getValidationType() {
        return validationType;
    }
}
