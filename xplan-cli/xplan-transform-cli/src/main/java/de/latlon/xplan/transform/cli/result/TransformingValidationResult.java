/*-
 * #%L
 * xplan-transform-cli - Kommandozeilentool fuer die Transformation zwischen XPlanGML Versionen
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
package de.latlon.xplan.transform.cli.result;

import de.latlon.xplan.manager.transformation.TransformationResult;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformingValidationResult {

    private final XPlan plan;

    private final TransformationResult transformationResult;

    private final SyntacticValidatorResult validatorResult;

    public TransformingValidationResult( XPlan plan, TransformationResult transformationResult,
                                         SyntacticValidatorResult validatorResult ) {
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
