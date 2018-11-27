package de.latlon.xplan.validator.syntactic;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorResult;

/**
 * Validates <link>XPlanArchives</link> syntactically
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public interface SyntacticValidator {

    /**
     * Perform syntactic validation of an archive with an XPlanGml-Document
     * 
     * @param archive
     *            the archive containing the plan to validate
     * @return a <link>ValidatorReport</link> containing the validation result
     */
    ValidatorResult validateSyntax( XPlanArchive archive );

    /**
     * Validate all XLink-References in the plan
     * 
     * @param archive
     *            the archive containing the plan to validate, necer <code>null</code>
     * @param externalReferenceInfo
     *            information on external references, never <code>null</code>
     * @param force
     *            should validation be forced on error
     * @throws ValidatorException
     *             ⁻ validation failed
     */
    void validateReferences( XPlanArchive archive, ExternalReferenceInfo externalReferenceInfo, boolean force )
                    throws ValidatorException;

}
