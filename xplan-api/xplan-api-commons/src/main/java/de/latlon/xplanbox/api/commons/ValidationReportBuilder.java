package de.latlon.xplanbox.api.commons;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import de.latlon.xplanbox.api.commons.v1.model.RulesMetadata;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReport;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReportValidationResult;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReportValidationResultGeometrisch;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReportValidationResultSemantisch;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReportValidationResultSemantischRules;
import de.latlon.xplanbox.api.commons.v1.model.ValidationReportValidationResultSyntaktisch;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplanbox.api.commons.v1.model.VersionEnum.fromXPlanVersion;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidationReportBuilder {

    private ValidatorReport validatorReport;

    private String filename;

    private URI wmsUrl;

    public ValidationReportBuilder validatorReport( ValidatorReport validatorReport ) {
        this.validatorReport = validatorReport;
        return this;
    }

    public ValidationReportBuilder filename( String filename ) {
        this.filename = filename;
        return this;
    }

    public ValidationReportBuilder wmsUrl( URI wmsUrl ) {
        this.wmsUrl = wmsUrl;
        return this;
    }

    public ValidationReport build() {
        ValidationReport validationReport = new ValidationReport();
        if ( validatorReport != null ) {
            validationReport.date( validatorReport.getDate() ).name( validatorReport.getValidationName() ).version(
                                    fromXPlanVersion( validatorReport.getXPlanVersion() ) ).valid(
                                    validatorReport.isReportValid() ).filename( filename ).externalReferences(
                                    externalReferences() ).wmsUrl( wmsUrl ).rulesMetadata(
                                    rulesMetadata() ).validationResult( createValidationResult() );
        }
        return validationReport;
    }

    private List<String> externalReferences() {
        if ( validatorReport != null && validatorReport.getExternalReferenceReport() != null )
            return validatorReport.getExternalReferenceReport().getReferences();
        return null;
    }

    private RulesMetadata rulesMetadata() {
        if ( validatorReport != null && validatorReport.getSemanticValidatorResult() != null ) {
            de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata rulesMetadata = validatorReport.getSemanticValidatorResult().getRulesMetadata();
            if ( rulesMetadata != null )
                return new RulesMetadata().version( rulesMetadata.getVersion() ).source( rulesMetadata.getSource() );
        }
        return null;
    }

    private ValidationReportValidationResult createValidationResult() {
        return new ValidationReportValidationResult().syntaktisch( syntaktischResult() ).semantisch(
                                semantischResult() ).geometrisch( geometrischResult() );
    }

    private ValidationReportValidationResultSyntaktisch syntaktischResult() {
        if ( validatorReport != null && validatorReport.getSyntacticValidatorResult() != null ) {
            SyntacticValidatorResult result = validatorReport.getSyntacticValidatorResult();
            return new ValidationReportValidationResultSyntaktisch().valid( result.isValid() ).messages(
                                    result.getMessages() );
        }
        return null;
    }

    private ValidationReportValidationResultSemantisch semantischResult() {
        if ( validatorReport != null && validatorReport.getSemanticValidatorResult() != null ) {
            SemanticValidatorResult result = validatorReport.getSemanticValidatorResult();
            List<ValidationReportValidationResultSemantischRules> rules = result.getRules().stream().map(
                                    ruleResult -> new ValidationReportValidationResultSemantischRules().isValid(
                                                            ruleResult.isValid() ).name( ruleResult.getName() ).message(
                                                            ruleResult.getMessage() ).invalidFeatures(
                                                            ruleResult.getInvalidFeatures() ) ).collect(
                                    Collectors.toList() );
            return new ValidationReportValidationResultSemantisch().valid( result.isValid() ).rules( rules );
        }
        return null;
    }

    private ValidationReportValidationResultGeometrisch geometrischResult() {
        if ( validatorReport != null && validatorReport.getGeometricValidatorResult() != null ) {
            GeometricValidatorResult result = validatorReport.getGeometricValidatorResult();
            return new ValidationReportValidationResultGeometrisch().valid( result.isValid() ).errors(
                                    result.getErrors() ).warnings( result.getWarnings() );
        }
        return null;
    }
}
