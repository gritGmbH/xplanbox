package de.latlon.xplan.validator.geometric;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GemetricValidatorParsingResult {

    private XPlanFeatureCollection features;

    private GeometricValidatorResult validatorResult;

    public GemetricValidatorParsingResult( XPlanFeatureCollection features, GeometricValidatorResult validatorResult ) {
        this.features = features;
        this.validatorResult = validatorResult;
    }

    public XPlanFeatureCollection getFeatures() {
        return features;
    }

    public GeometricValidatorResult getValidatorResult() {
        return validatorResult;
    }
}