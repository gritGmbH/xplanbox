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