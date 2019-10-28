package de.latlon.xplan.validator.semantic;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;

import java.util.List;

/**
 * Encapsulates a single validation rule
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public interface SemanticValidatorRule {

    /**
     * Validate an <link>XPlanArchive</link> against the encapsulated rule
     * 
     * @param archive
     *            the archive to validate, never <code>null</code>
     * @return list of GML Ids of the invalid features, empty if the all features are valid
     */
    List<String> validate( SemanticValidableXPlanArchive archive )
                            throws ValidatorException;

    /**
     * Returns the name of the rule
     * 
     * @return name
     */
    String getName();

    /**
     * @return the {@link XPlanVersion} this rule applies to, may be <code>null</code> if this rule applies to all
     *         versions
     */
    XPlanVersion getXPlanVersion();

    /**
     * Checks if the rule should be ignored or not.
     * 
     * @param option
     *            may be <code>null</code>
     * @return true if this rule matches the passed option, false otherwise. When the option is <code>null</code> or the
     *         NONE option, false is returned.
     */
    boolean isIgnoredByOption( SemanticValidationOptions option );

}