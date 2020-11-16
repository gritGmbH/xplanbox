/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.validator.semantic;

import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;

import java.util.List;

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
    ValidatorResult validateSemantic( SemanticValidableXPlanArchive archive, List<SemanticValidationOptions> semanticValidationOptions );
}
