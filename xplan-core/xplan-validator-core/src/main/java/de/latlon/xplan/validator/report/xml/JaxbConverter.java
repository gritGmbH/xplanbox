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
package de.latlon.xplan.validator.report.xml;

import static de.latlon.xplan.validator.report.ReportUtils.createValidLabel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ErrorsType;
import de.latlon.xplan.validator.report.ExternalReferencesType;
import de.latlon.xplan.validator.report.GeomType;
import de.latlon.xplan.validator.report.InvalidFeaturesType;
import de.latlon.xplan.validator.report.MessagesType;
import de.latlon.xplan.validator.report.ObjectFactory;
import de.latlon.xplan.validator.report.PlanType;
import de.latlon.xplan.validator.report.ReportUtils;
import de.latlon.xplan.validator.report.RuleType;
import de.latlon.xplan.validator.report.RulesMetadataType;
import de.latlon.xplan.validator.report.RulesType;
import de.latlon.xplan.validator.report.SemType;
import de.latlon.xplan.validator.report.SynType;
import de.latlon.xplan.validator.report.ValidationReport;
import de.latlon.xplan.validator.report.ValidationType;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.WarningsType;
import de.latlon.xplan.validator.report.reference.ExternalReferenceReport;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
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
        validationReportType.setExternalReferences( convertExternalReferences( report ) );
        validationReportType.setValidation( convertValidationResults( report ) );
        return validationReportType;
    }

    private ExternalReferencesType convertExternalReferences( ValidatorReport report ) {
        ExternalReferenceReport externalReferenceReport = report.getExternalReferenceReport();
        if ( externalReferenceReport == null ) {
            return null;
        }
        ObjectFactory objectFactory = new ObjectFactory();
        ExternalReferencesType externalReferencesType = objectFactory.createExternalReferencesType();

        ReportUtils.SkipCode skipCode = externalReferenceReport.getSkipCode();
        if ( skipCode != null )
            externalReferencesType.setSkipMessage( skipCode.getMessage() );
        List<String> references = externalReferenceReport.getReferences();
        externalReferencesType.getExternalReferences().addAll( references );
        return externalReferencesType;
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

        RulesMetadata rulesMetadata = result.getRulesMetadata();
        if ( rulesMetadata != null ) {
            RulesMetadataType rulesMetadataType = objectFactory.createRulesMetadataType();
            rulesMetadataType.setVersion( rulesMetadata.getVersion() );
            rulesMetadataType.setSource( rulesMetadata.getSource() );
            semType.setRulesMetadata( rulesMetadataType );
        }

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
