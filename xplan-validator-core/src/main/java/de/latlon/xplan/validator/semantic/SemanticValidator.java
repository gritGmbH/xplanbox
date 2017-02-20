package de.latlon.xplan.validator.semantic;

import java.util.List;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;

/**
 * Validates <link>XPlanArchives</link> semantically
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public interface SemanticValidator {

    /**
     * Perform semantic validation of an archive with an XPlanGml-Document Build the validator before usage!
     * 
     * @param archive
     *            the archive containing the plan to validate, never <code>null</code>
     * @param semanticValidationOptions
     *            a {@link List} of {@link SemanticValidationOptions}, considered by the validation, may be empty, but
     *            never <code>null</code>
     * @return a <link>ValidatorReport</link> containing the result of the validation
     * @throws IllegalArgumentException
     *             if one of the parameter is <code>null</code>
     */
    ValidatorResult validateSemantic( XPlanArchive archive, List<SemanticValidationOptions> semanticValidationOptions );
}
