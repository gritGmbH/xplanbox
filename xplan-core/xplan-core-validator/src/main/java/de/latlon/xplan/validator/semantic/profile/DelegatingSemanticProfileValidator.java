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
package de.latlon.xplan.validator.semantic.profile;

import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;

import java.util.List;

/**
 * Validates <link>XPlanArchives</link> semantically
 */
public class DelegatingSemanticProfileValidator implements SemanticProfileValidator {

	private String id;

	private SemanticValidator semanticValidator;

	public DelegatingSemanticProfileValidator(String id, SemanticValidator semanticValidator) {
		this.id = id;
		this.semanticValidator = semanticValidator;
	}

	@Override
	public ValidatorResult validateSemantic(SemanticValidableXPlanArchive archive,
			List<SemanticValidationOptions> semanticValidationOptions) {
		return semanticValidator.validateSemantic(archive, semanticValidationOptions);
	}

	@Override
	public String getId() {
		return id;
	}

}
