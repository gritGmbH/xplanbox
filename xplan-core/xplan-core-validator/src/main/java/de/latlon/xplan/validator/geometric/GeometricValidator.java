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
package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.types.AppSchema;

import java.util.List;

/**
 * Validates <link>XPlanArchives</link> geometrically
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public interface GeometricValidator {

	/**
	 * Validate geometrically and return a <link>List</link> of error messages
	 * @param archive the archive to validate, never <code>null</code>
	 * @param crs the crs to validate against, never <code>null</code>
	 * @param schema the application schema, never <code>null</code>
	 * @param force true if validation shall continue on eror, false if not
	 * @param voOptions options to specify validation, may be empty, but never
	 * <code>null</code>
	 * @return a <link>ValidatorReport</link> containing the result of the validation
	 * @throws ValidatorException - validation failed
	 */
	GeometricValidatorResult validateGeometry(XPlanArchive archive, ICRS crs, AppSchema schema, boolean force,
			List<ValidationOption> voOptions) throws ValidatorException;

}
