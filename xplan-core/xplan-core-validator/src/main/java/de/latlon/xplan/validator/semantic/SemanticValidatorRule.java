/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.validator.semantic;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.report.InvalidFeaturesResult;

import java.util.List;

/**
 * Encapsulates a single validation rule
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public interface SemanticValidatorRule {

	/**
	 * Validate an <link>XPlanArchive</link> against the encapsulated rule
	 * @param archive the archive to validate, never <code>null</code>
	 * @return list of GML Ids of the invalid features, empty if the all features are
	 * valid
	 */
	List<InvalidFeaturesResult> validate(SemanticValidableXPlanArchive archive) throws ValidatorException;

	/**
	 * Returns the name of the rule
	 * @return name
	 */
	String getName();

	/**
	 * @return the {@link XPlanVersion} this rule applies to, may be <code>null</code> if
	 * this rule applies to all versions
	 */
	XPlanVersion getXPlanVersion();

	/**
	 * Checks if the rule should be ignored or not.
	 * @param option may be <code>null</code>
	 * @return true if this rule matches the passed option, false otherwise. When the
	 * option is <code>null</code> or the NONE option, false is returned.
	 */
	boolean isIgnoredByOption(SemanticValidationOptions option);

	/**
	 * @return message describing this rules, never <code>null</code>
	 */
	String getMessage();

}
