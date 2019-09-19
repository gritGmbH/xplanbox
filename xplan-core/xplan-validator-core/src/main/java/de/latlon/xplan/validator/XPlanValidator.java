package de.latlon.xplan.validator;

import static de.latlon.xplan.validator.report.ReportUtils.SkipCode.SYNTAX_ERRORS;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.report.RuleResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;

/**
 * Performs semantic, geometric and syntactic validation for the CLI
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
public class XPlanValidator {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanValidator.class );

    private final GeometricValidator geometricValidator;

    private final SyntacticValidator syntacticValidator;

    private final SemanticValidator semanticValidator;

    private final ReportArchiveGenerator reportArchiveGenerator;

    private final XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();

    private XPlanSchemas schemas;

    public XPlanValidator( GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
                           SemanticValidator semanticValidator, ReportArchiveGenerator reportArchiveGenerator ) {
        this.geometricValidator = geometricValidator;
        this.syntacticValidator = syntacticValidator;
        this.semanticValidator = semanticValidator;
        this.reportArchiveGenerator = reportArchiveGenerator;
        this.schemas = XPlanSchemas.getInstance();
    }

    /**
     * @param schemas
     *            never <code>null</code>
     */
    public void setSchemas( XPlanSchemas schemas ) {
        this.schemas = schemas;
    }

    /**
     * Validate a plan archive
     * 
     * @param validationSettings
     *            to apply, never <code>null</code>
     * @param planArchive
     *            to validate, never <code>null</code> and must point to a zip file with a gml plan
     * @return <link>ValidatorReport</link>
     * @throws ValidatorException
     * @throws ParseException
     * @throws IOException
     * @throws ReportGenerationException
     */
    public ValidatorReport validate( ValidationSettings validationSettings, File planArchive, String planName )
                    throws ValidatorException, ParseException, IOException, ReportGenerationException {
        XPlanArchive archive = retrieveXPlanArchive( planArchive );
        ValidatorReport report = validate( validationSettings, archive, planName );
        writeReport( report );
        File validationReportDirectory = createZipArchive( validationSettings, archive, report );
        LOG.info( "Archiv mit Validierungsergebnissen wurde unter {} abgelegt.", validationReportDirectory );
        return report;
    }

    /**
     * Validate a plan archive, but does not write the report
     * 
     * @param validationSettings
     *            to apply, never <code>null</code>
     * @param planArchive
     *            to validate, never <code>null</code> and must point to a zip file with a gml plan
     * @return <link>ValidatorReport</link>
     * @throws ValidatorException
     * @throws IOException
     */
    public ValidatorReport validateNotWriteReport( ValidationSettings validationSettings, File planArchive,
                                                   String planName )
                    throws ValidatorException, IOException {
        XPlanArchive archive = retrieveXPlanArchive( planArchive );
        ValidatorReport report = validate( validationSettings, archive, planName );
        return report;
    }

    /**
     * Override this method to change the lookup of the archive file
     * 
     * @param planArchive
     *            the file to create the archive from, never <code>null</code> and must point to a zip file with a gml
     *            plan
     * @return the archive
     * @throws IOException
     */
    XPlanArchive retrieveXPlanArchive( File planArchive )
                    throws IOException {
        return archiveCreator.createXPlanArchive( planArchive );
    }

    /**
     * Write a report
     *
     * @param report
     *            to write, never <code>null</code>
     */
    void writeReport( ValidatorReport report ) {
        writeReport( report.getGeometricValidatorResult() );
        writeReport( report.getSemanticValidatorResult() );
        writeReport( report.getSyntacticValidatorResult() );
    }

    private void writeReport( ValidatorResult result ) {
        if ( result != null ) {
            String validityMessage = result.isValid() ? "valide" : "nicht valide";
            LOG.info( "{} hat ergeben: Dokument ist {}", result.getType(), validityMessage );
        }
    }

    private ValidatorReport validate( ValidationSettings validationSettings, XPlanArchive archive, String planName )
                            throws ValidatorException {
        List<ValidationOption> voOptions = validationSettings.getExtendedOptions();
        List<SemanticValidationOptions> semanticValidationOptions = extractSemanticValidationOptions( validationSettings );

        ValidatorReport report = new ValidatorReport();
        report.setValidationName( validationSettings.getValidationName() );
        report.setPlanName( planName );
        report.setDate( new Date() );
        ValidationType validationType = validationSettings.getValidationType();
        if ( validationType == null )
            validationType = ValidationType.NONE;

        SyntacticValidatorResult syntacticallyResult = validateSyntacticallyAndWriteResult( archive );
        report.setSyntacticValidatorResult( syntacticallyResult );

        switch ( validationType ) {
        case SYNTACTIC:
            break;
        case GEOMETRIC: {
            GeometricValidatorResult geometricallyResult;
            if ( syntacticallyResult.isValid() ) {
                geometricallyResult = validateGeometricallyAndWriteResult( archive, voOptions );
            } else {
                geometricallyResult = new GeometricValidatorResult( SYNTAX_ERRORS );
            }
            report.setGeometricValidatorResult( geometricallyResult );
            break;
        }
        case SEMANTIC:
        default: {
            GeometricValidatorResult geometricallyResult;
            SemanticValidatorResult semanticallyResult;
            if ( syntacticallyResult.isValid() ) {
                geometricallyResult = validateGeometricallyAndWriteResult( archive, voOptions );
                semanticallyResult = validateSemanticallyAndWriteResult( archive, semanticValidationOptions );
            } else {
                geometricallyResult = new GeometricValidatorResult( SYNTAX_ERRORS );
                semanticallyResult = new SemanticValidatorResult( SYNTAX_ERRORS );
            }
            report.setGeometricValidatorResult( geometricallyResult );
            report.setSemanticValidatorResult( semanticallyResult );
        }
        }
        return report;
    }

    /**
     * Perform geometric validation of the given archive
     * 
     * @param archive
     *            archive to validate, never <code>null</code>
     * @param voOptions
     *            validation options, never <code>null</code>
     * @return the created report
     * @throws ValidatorException
     *             - validation failed
     */
    GeometricValidatorResult validateGeometricallyAndWriteResult( XPlanArchive archive,
                                                                  List<ValidationOption> voOptions )
                                                                                  throws ValidatorException {
        AppSchema appSchema = schemas.getAppSchema( archive.getVersion(), archive.getAde() );
        ValidatorResult result = geometricValidator.validateGeometry( archive, archive.getCrs(), appSchema, true,
                                                                      voOptions );
        GeometricValidatorResult validatorResult = (GeometricValidatorResult) result;

        log( validatorResult );
        return validatorResult;
    }

    /**
     * Perform semantic validation of the given archive
     * 
     * @param archive
     *            archive to validate, never <code>null</code>
     * @param semanticValidationOptions
     *            {@link List} of {@link SemanticValidationOptions}, considered by the validation, may be empty, but
     *            never <code>null</code>
     * @return the created report
     */
    SemanticValidatorResult
                    validateSemanticallyAndWriteResult( SemanticValidableXPlanArchive archive,
                                                        List<SemanticValidationOptions> semanticValidationOptions )
                                                                        throws ValidatorException {
        ValidatorResult result = semanticValidator.validateSemantic( archive, semanticValidationOptions );
        SemanticValidatorResult validatorResult = (SemanticValidatorResult) result;
        log( validatorResult );
        return validatorResult;
    }

    /**
     * Perform syntactic validation of the given archive
     * 
     * @param archive
     *            archive to validate, never <code>null</code>
     * @return the created report, never <code>null</code>
     */
    SyntacticValidatorResult validateSyntacticallyAndWriteResult( XPlanArchive archive ) {
        ValidatorResult result = syntacticValidator.validateSyntax( archive );
        SyntacticValidatorResult validatorResult = (SyntacticValidatorResult) result;
        log( validatorResult );
        return validatorResult;
    }

    private void log( GeometricValidatorResult validatorResult ) {
        LOG.info( "Ergebnisse der geometrischen Validerung:" );

        List<String> warnings = validatorResult.getWarnings();
        LOG.info( "  Warnungen: {}", warnings.size() );
        for ( String warn : warnings )
            LOG.info( "    - {}", warn );

        List<String> errors = validatorResult.getErrors();
        LOG.info( "  Fehler: {}", errors.size() );
        for ( String err : errors )
            LOG.info( "    - {}", err );
    }

    private void log( SemanticValidatorResult validatorResult ) {
        List<RuleResult> ruleResults = validatorResult.getRules();
        LOG.info( "Ergebnisse der semantischen Validerung: {}", ruleResults.size() );
        for ( RuleResult ruleResult : ruleResults ) {
            String label = ruleResult.isValid() ? "Erfolgreich" : "Fehler";
            LOG.info( "  - {}: {}", label, ruleResult.getMessage() );
        }
    }

    private void log( SyntacticValidatorResult validatorResult ) {
        List<String> messages = validatorResult.getMessages();
        LOG.info( "Ergebnisse der syntaktischen Validerung: {}", messages.size() );
        for ( String mess : messages )
            LOG.info( "  - {}", mess );
    }

    private List<SemanticValidationOptions> extractSemanticValidationOptions( ValidationSettings validationSettings ) {
        List<SemanticValidationOptions> semanticValidationOptions = new ArrayList<>();
        List<ValidationOption> extendedOptions = validationSettings.getExtendedOptions();
        if ( extendedOptions != null )
            for ( ValidationOption validationOption : extendedOptions ) {
                SemanticValidationOptions semanticValidationOption = SemanticValidationOptions.getByOption( validationOption );
                if ( !SemanticValidationOptions.NONE.equals( semanticValidationOption ) )
                    semanticValidationOptions.add( semanticValidationOption );
            }
        return semanticValidationOptions;
    }

    private File createZipArchive( ValidationSettings validationSettings, XPlanArchive archive, ValidatorReport report )
                    throws ReportGenerationException {
        String validationName = validationSettings.getValidationName();
        return reportArchiveGenerator.generateZipArchive( archive, report, validationName );
    }

}
