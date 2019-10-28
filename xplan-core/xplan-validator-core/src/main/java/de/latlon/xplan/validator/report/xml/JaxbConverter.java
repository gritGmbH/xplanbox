package de.latlon.xplan.validator.report.xml;

import static de.latlon.xplan.validator.report.ReportUtils.createValidLabel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ErrorsType;
import de.latlon.xplan.validator.report.GeomType;
import de.latlon.xplan.validator.report.InvalidFeaturesType;
import de.latlon.xplan.validator.report.MessagesType;
import de.latlon.xplan.validator.report.ObjectFactory;
import de.latlon.xplan.validator.report.PlanType;
import de.latlon.xplan.validator.report.RuleType;
import de.latlon.xplan.validator.report.RulesType;
import de.latlon.xplan.validator.report.SemType;
import de.latlon.xplan.validator.report.SynType;
import de.latlon.xplan.validator.report.ValidationReport;
import de.latlon.xplan.validator.report.ValidationType;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.WarningsType;
import de.latlon.xplan.validator.semantic.report.RuleResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;

/**
 * Converts the internal used {@link ValidatorReport} instances to jaxb
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class JaxbConverter {

    /**
     * Converts the passed report to jaxb including the name and plan name of the validation.
     *
     * @param report
     *            to convert, never <code>null</code>
     * @return the converted {@link JaxbConverter} instance, never <code>null</code>
     */
    public ValidationReport convertValidationReport( ValidatorReport report ) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidationReport validationReportType = objectFactory.createValidationReport();
        validationReportType.setDate( toCalendar( report.getDate() ) );
        validationReportType.setName( report.getValidationName() );
        validationReportType.setIsValid( report.isReportValid() );
        validationReportType.setPlan( convertPlanType( report ) );
        validationReportType.setValidation( convertValidationResults( report ) );
        return validationReportType;
    }

    private ValidationType convertValidationResults( ValidatorReport report ) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidationType jaxbValidation = objectFactory.createValidationType();
        if ( report.getGeometricValidatorResult() != null )
            convertResultToJaxb( report.getGeometricValidatorResult(), jaxbValidation );
        if ( report.getSemanticValidatorResult() != null )
            convertResultToJaxb( report.getSemanticValidatorResult(), jaxbValidation );
        if ( report.getSyntacticValidatorResult() != null )
            convertResultToJaxb( report.getSyntacticValidatorResult(), jaxbValidation );
        return jaxbValidation;
    }

    private PlanType convertPlanType( ValidatorReport report ) {
        ObjectFactory objectFactory = new ObjectFactory();
        PlanType pt = objectFactory.createPlanType();
        pt.setName( report.getPlanName() );
        return pt;
    }

    private void convertResultToJaxb( GeometricValidatorResult result, ValidationType val ) {
        ObjectFactory objectFactory = new ObjectFactory();
        GeomType geomType = objectFactory.createGeomType();

        if ( result.isSkipped() ) {
            geomType.setResult( result.getSkipCode().getMessage() );
        } else {
            WarningsType warningsXml = objectFactory.createWarningsType();
            warningsXml.getWarnings().addAll( result.getWarnings() );

            ErrorsType errorsXml = objectFactory.createErrorsType();
            errorsXml.getErrors().addAll( result.getErrors() );

            geomType.setWarnings( warningsXml );
            geomType.setErrors( errorsXml );
            geomType.setResult( createValidLabel( result.isValid() ) );
            if ( result.getValidatorDetail() != null )
                geomType.setDetails( result.getValidatorDetail().toString() );
        }

        val.setGeom( geomType );
    }

    private void convertResultToJaxb( SemanticValidatorResult result, ValidationType val ) {
        ObjectFactory objectFactory = new ObjectFactory();
        SemType semType = objectFactory.createSemType();

        if ( result.isSkipped() ) {
            semType.setResult( result.getSkipCode().getMessage() );
        } else {
            RulesType rulesXML = objectFactory.createRulesType();
            List<RuleType> rulesListXML = rulesXML.getRules();
            for ( RuleResult rule : result.getRules() ) {
                RuleType ruleXML = objectFactory.createRuleType();
                ruleXML.setName( rule.getName() );
                ruleXML.setIsValid( rule.isValid() );
                ruleXML.setMessage( rule.getMessage() );
                addInvalidFeatures( ruleXML, rule.getInvalidFeatures() );
                rulesListXML.add( ruleXML );
            }
            semType.setRules( rulesXML );

            semType.setResult( createValidLabel( result.isValid() ) );
            if ( result.getValidatorDetail() != null )
                semType.setDetails( result.getValidatorDetail().toString() );
        }

        val.setSem( semType );
    }

    private void addInvalidFeatures( RuleType ruleXML, List<String> invalidFeatures ) {
        ObjectFactory objectFactory = new ObjectFactory();
        for ( String invalidFeature : invalidFeatures ) {
            InvalidFeaturesType invalidFeaturesType = objectFactory.createInvalidFeaturesType();
            invalidFeaturesType.setGmlid( invalidFeature );
            ruleXML.getInvalidFeatures().add( invalidFeaturesType );
        }
    }

    private void convertResultToJaxb( SyntacticValidatorResult result, ValidationType val ) {
        ObjectFactory objectFactory = new ObjectFactory();
        SynType synType = objectFactory.createSynType();

        MessagesType messagesXml = objectFactory.createMessagesType();
        messagesXml.getMessages().addAll( result.getMessages() );

        synType.setMessages( messagesXml );
        synType.setResult( createValidLabel( result.isValid() ) );
        if ( result.getValidatorDetail() != null )
            synType.setDetails( result.getValidatorDetail().toString() );

        val.setSyn( synType );
    }

    private static Calendar toCalendar( Date date ) {
        if ( date == null )
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );
        return cal;
    }

}