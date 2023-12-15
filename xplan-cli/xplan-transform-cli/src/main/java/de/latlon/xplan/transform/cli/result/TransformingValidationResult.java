/*-
 * #%L
 * xplan-transform-cli - Kommandozeilentool fuer die Transformation zwischen XPlanGML Versionen
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
package de.latlon.xplan.transform.cli.result;

import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

/**
 * @deprecated will be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Deprecated
public class TransformingValidationResult {

	private final XPlan plan;

	private final TransformationResult transformationResult;

	private final SyntacticValidatorResult validatorResult;

	public TransformingValidationResult(XPlan plan, TransformationResult transformationResult,
			SyntacticValidatorResult validatorResult) {
		this.plan = plan;
		this.transformationResult = transformationResult;
		this.validatorResult = validatorResult;
	}

	public XPlan getPlan() {
		return plan;
	}

	public TransformationResult getTransformationResult() {
		return transformationResult;
	}

	public SyntacticValidatorResult getValidatorResult() {
		return validatorResult;
	}

}
