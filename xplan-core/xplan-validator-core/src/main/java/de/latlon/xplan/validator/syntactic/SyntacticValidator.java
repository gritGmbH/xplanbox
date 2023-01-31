/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.validator.syntactic;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorResult;

import java.io.InputStream;

/**
 * Validates <link>XPlanArchives</link> syntactically
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public interface SyntacticValidator {

	/**
	 * Perform syntactic validation of an archive with an XPlanGml-Document
	 * @param archive the archive containing the plan to validate
	 * @return a <link>ValidatorReport</link> containing the validation result
	 */
	ValidatorResult validateSyntax(XPlanArchive archive);

	ValidatorResult validateSyntax(InputStream is, XPlanVersion version);

	/**
	 * Validate all XLink-References in the plan
	 * @param archive the archive containing the plan to validate, necer <code>null</code>
	 * @param externalReferenceInfo information on external references, never
	 * <code>null</code>
	 * @param force should validation be forced on error
	 * @throws ValidatorException ⁻ validation failed
	 */
	void validateReferences(XPlanArchive archive, ExternalReferenceInfo externalReferenceInfo, boolean force)
			throws ValidatorException;

}
