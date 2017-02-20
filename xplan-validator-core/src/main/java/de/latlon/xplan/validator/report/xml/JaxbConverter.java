package de.latlon.xplan.validator.report.xml;

import static de.latlon.xplan.validator.report.ReportUtils.createValidLabel;

import java.util.List;

import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ErrorsType;
import de.latlon.xplan.validator.report.GeomType;
import de.latlon.xplan.validator.report.MessagesType;
import de.latlon.xplan.validator.report.ObjectFactory;
import de.latlon.xplan.validator.report.PlanType;
import de.latlon.xplan.validator.report.RuleType;
import de.latlon.xplan.validator.report.RulesType;
import de.latlon.xplan.validator.report.SemType;
import de.latlon.xplan.validator.report.SynType;
import de.latlon.xplan.validator.report.ValidationReportType;
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
     * @param validationName
     *            to put in the converted {@link ValidationReportType}, never <code>null</code>
     * @param planName
     *            to put in the converted {@link ValidationReportType}, never <code>null</code>
     * @return the converted {@link JaxbConverter} instance, never <code>null</code>
     */
    public ValidationReportType convertValidationReport( ValidatorReport report, String validationName,
                                                         String planName ) {
        ObjectFactory objectFactory = new ObjectFactory();
        ValidationReportType validationReportType = objectFactory.createValidationReportType();
        validationReportType.setName( validationName );
        validationReportType.setPlan( convertPlanType( planName ) );
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

    private PlanType convertPlanType( String planName ) {
        ObjectFactory objectFactory = new ObjectFactory();
        PlanType pt = objectFactory.createPlanType();
        pt.setName( planName );
        return pt;
    }

    private void convertResultToJaxb( GeometricValidatorResult result, ValidationType val ) {
        ObjectFactory objectFactory = new ObjectFactory();
        GeomType geomType = objectFactory.createGeomType();

        WarningsType warningsXml = objectFactory.createWarningsType();
        warningsXml.getWarning().addAll( result.getWarnings() );

        ErrorsType errorsXml = objectFactory.createErrorsType();
        errorsXml.getError().addAll( result.getErrors() );

        geomType.setWarnings( warningsXml );
        geomType.setErrors( errorsXml );
        geomType.setResult( createValidLabel( result.isValid() ) );
        if ( result.getValidatorDetail() != null )
            geomType.setDetails( result.getValidatorDetail().toString() );

        val.setGeom( geomType );
    }

    private void convertResultToJaxb( SemanticValidatorResult result, ValidationType val ) {
        ObjectFactory objectFactory = new ObjectFactory();

        RulesType rulesXML = objectFactory.createRulesType();
        List<RuleType> rulesListXML = rulesXML.getRule();
        for ( RuleResult rule : result.getRules() ) {
            RuleType ruleXML = objectFactory.createRuleType();
            ruleXML.setName( rule.getName() );
            ruleXML.setIsValid( rule.isValid() );
            ruleXML.setMessage( rule.getMessage() );
            rulesListXML.add( ruleXML );
        }

        SemType semType = objectFactory.createSemType();

        semType.setRules( rulesXML );
        semType.setResult( createValidLabel( result.isValid() ) );
        if ( result.getValidatorDetail() != null )
            semType.setDetails( result.getValidatorDetail().toString() );

        val.setSem( semType );
    }

    private void convertResultToJaxb( SyntacticValidatorResult result, ValidationType val ) {
        ObjectFactory objectFactory = new ObjectFactory();
        SynType synType = objectFactory.createSynType();

        MessagesType messagesXml = objectFactory.createMessagesType();
        messagesXml.getMessage().addAll( result.getMessages() );

        synType.setMessages( messagesXml );
        synType.setResult( createValidLabel( result.isValid() ) );
        if ( result.getValidatorDetail() != null )
            synType.setDetails( result.getValidatorDetail().toString() );

        val.setSyn( synType );
    }

}