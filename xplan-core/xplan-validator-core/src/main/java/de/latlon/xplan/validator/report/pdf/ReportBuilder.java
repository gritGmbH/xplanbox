package de.latlon.xplan.validator.report.pdf;

import static de.latlon.xplan.validator.report.ReportUtils.createValidLabel;
import static de.latlon.xplan.validator.report.pdf.Templates.bold14LeftStyle;
import static de.latlon.xplan.validator.report.pdf.Templates.createFooter;
import static de.latlon.xplan.validator.report.pdf.Templates.createTemplate;
import static de.latlon.xplan.validator.report.pdf.Templates.root20LeftIndentStyle;
import static de.latlon.xplan.validator.report.pdf.Templates.simpleStyle;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.report.RuleResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.MultiPageListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 * Encapsulates a {@link JasperReportBuilder} building a validation report
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
class ReportBuilder {

    private static final String LABEL_TITLE = "ValidationReport";

    private static final String LABEL_HINT = "Hinweis: ";

    private static final String LABEL_WARNING = "Warnung: ";

    private static final String LABEL_ERROR = "Fehler: ";

    private static final String LABEL_OK = "Erfolgreich: ";

    private static final int VERTICAL_GAP = 10;

    /**
     * Creates a {@link JasperReportBuilder} with the {@link ValidatorReport}
     *
     * @param report
     *            the validation report to serialize, never <code>null</code>
     * @param validationName
     *            the Name of the Validation, may be <code>null</code> or empty
     * @param planName
     *            the Name of the validated plan, may be <code>null</code> or empty
     * @throws ReportGenerationException
     *             if an exception occurred during writing the report
     * @throws IllegalArgumentException
     *             if the passed report is <code>null</code>
     */
    JasperReportBuilder createReport( ValidatorReport report, String validationName, String planName )
                    throws ReportGenerationException {
        checkReportParam( report );
        try {
            return report().setTemplate( createTemplate() ).

            title( Templates.createTitleComponent( LABEL_TITLE ),
                   createMetadataSection( validationName, planName, report ) )

            .summary( createValidationResults( report ) )

            .pageFooter( createFooter() );
        } catch ( JRException e ) {
            throw new ReportGenerationException( e );
        }
    }

    private void checkReportParam( ValidatorReport report ) {
        if ( report == null )
            throw new IllegalArgumentException( "Report must not be null!" );
    }

    private VerticalListBuilder createValidationResults( ValidatorReport report ) {
        VerticalListBuilder verticalList = cmp.verticalList();

        SyntacticValidatorResult syntacticValidatorResult = report.getSyntacticValidatorResult();
        if ( syntacticValidatorResult != null ) {
            verticalList = verticalList.add( appendHeaderAndResult( syntacticValidatorResult ) );
            verticalList = appendDetailsHint( verticalList, syntacticValidatorResult );
            verticalList = verticalList.add( createSyntacticRules( syntacticValidatorResult ) ).add( cmp.verticalGap( 10 ) );
        }

        SemanticValidatorResult semanticValidatorResult = report.getSemanticValidatorResult();
        if ( semanticValidatorResult != null ) {
            verticalList = verticalList.add( appendHeaderAndResult( semanticValidatorResult ) );
            verticalList = appendDetailsHint( verticalList, semanticValidatorResult );
            verticalList = verticalList.add( createSemanticRules( semanticValidatorResult ) ).add( cmp.verticalGap( 10 ) );
        }

        GeometricValidatorResult geometricValidatorResult = report.getGeometricValidatorResult();
        if ( geometricValidatorResult != null ) {
            verticalList = verticalList.add( appendHeaderAndResult( geometricValidatorResult ) );
            verticalList = appendDetailsHint( verticalList, geometricValidatorResult );
            verticalList = verticalList.add( createGeometricRules( geometricValidatorResult ) );
        }
        return verticalList;
    }

    private MultiPageListBuilder createSemanticRules( SemanticValidatorResult result ) {
        MultiPageListBuilder rules = cmp.multiPageList();
        appendSemanticValidatorRules( rules, result.getRules() );
        return rules;
    }

    private ComponentBuilder<?, ?> createSyntacticRules( SyntacticValidatorResult result ) {
        MultiPageListBuilder rules = cmp.multiPageList();
        appendMessageRules( rules, LABEL_HINT, ( result ).getMessages() );
        return rules;
    }

    private ComponentBuilder<?, ?> createGeometricRules( GeometricValidatorResult result ) {
        MultiPageListBuilder rules = cmp.multiPageList();
        appendMessageRules( rules, LABEL_WARNING, ( result ).getWarnings() );
        appendMessageRules( rules, LABEL_ERROR, ( result ).getErrors() );
        return rules;
    }

    private ComponentBuilder<?, ?> appendHeaderAndResult( ValidatorResult result ) {
        ComponentBuilder<?, ?> rulesHead = cmp.text( result.getType() ).setStyle( bold14LeftStyle );
        TextFieldBuilder<String> validString = cmp.text( getResultMessage( result ) ).setStyle( bold14LeftStyle.setBottomBorder( stl.pen1Point() ) );
        return cmp.horizontalList().add( rulesHead ).add( validString );
    }

    private VerticalListBuilder appendDetailsHint( VerticalListBuilder verticalList, ValidatorResult validatorResult ) {
        if ( validatorResult != null && validatorResult.getValidatorDetail() != null ) {
            ValidatorDetail detailsHint = validatorResult.getValidatorDetail();
            StyleBuilder detailsHintStyle = stl.style( simpleStyle ).setLeftIndent( 10 ).setTopPadding( 5 ).setBottomPadding( 5 );
            TextFieldBuilder<String> detailsString = cmp.text( detailsHint.toString() ).setStyle( detailsHintStyle );
            verticalList = verticalList.add( detailsString );
        }
        return verticalList;
    }

    private void appendMessageRules( MultiPageListBuilder rules, String label, List<String> messagesToWrite ) {
        for ( String message : messagesToWrite ) {
            TextFieldBuilder<String> labelField = cmp.text( label ).setFixedWidth( 100 ).setStyle( root20LeftIndentStyle );
            TextFieldBuilder<String> messageField = cmp.text( message ).setStyle( simpleStyle );
            rules.add( cmp.horizontalList().add( labelField ).add( messageField ) );
        }
    }

    private void appendSemanticValidatorRules( MultiPageListBuilder rules, List<RuleResult> ruleResults ) {
        for ( RuleResult ruleResult : ruleResults ) {
            String label = ruleResult.isValid() ? LABEL_OK : LABEL_ERROR;
            TextFieldBuilder<String> labelField = cmp.text( label ).setFixedWidth( 100 ).setStyle( root20LeftIndentStyle );
            TextFieldBuilder<String> messageField = cmp.text( ruleResult.getMessage() ).setStyle( simpleStyle );
            rules.add( cmp.horizontalList().add( labelField ).add( messageField ) );
        }
    }

    private ComponentBuilder<?, ?> createMetadataSection( String validationName, String planName,
                                                          ValidatorReport report )
                                                                          throws JRException {
        InputStream is = PdfReportGenerator.class.getResourceAsStream( "/jrxml/metadata.jrxml" );
        JasperReport jasperTitleSubreport = JasperCompileManager.compileReport( is );
        Map<String, Object> parameters = createParams( validationName, planName, report );
        return cmp.verticalList().add( cmp.subreport( jasperTitleSubreport ).setParameters( parameters ) ).add( cmp.verticalGap( VERTICAL_GAP ) );
    }

    private String getResultMessage( ValidatorResult result ) {
        if ( result.isSkipped() ) {
            return result.getSkipCode().getMessage();
        }
        return createValidLabel( result.isValid() );
    }

    private Map<String, Object> createParams( String validationName, String planName, ValidatorReport report ) {
        String isValid = createValidLabel( report.isReportValid() );
        Map<String, Object> params = new HashMap<String, Object>();
        params.put( "valName", validationName );
        params.put( "planName", planName );
        params.put( "valResult", isValid );
        params.put( "date", new Date() );
        return params;
    }

}
