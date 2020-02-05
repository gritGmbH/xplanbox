package de.latlon.xplan.validator;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.validator.geometric.GemetricValidatorParsingResult;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.report.GeometricValidatorResult;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.report.ReportGenerationException;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.report.reference.ExternalReferenceReport;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.report.RuleResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.report.SyntacticValidatorResult;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.report.ReportUtils.SkipCode.INTERNAL_ERRORS;
import static de.latlon.xplan.validator.report.ReportUtils.SkipCode.SYNTAX_ERRORS;
import static de.latlon.xplan.validator.web.shared.ValidationType.GEOMETRIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SEMANTIC;

/**
 * Performs semantic, geometric and syntactic validation for the CLI
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
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
     * Validate a plan archive
     *
     * @param validationSettings
     *                         to apply, never <code>null</code>
     * @param planArchive
     *                         to validate, never <code>null</code> and must point to a zip file with a gml plan
     * @return <link>ValidatorReport</link>
     * @throws ValidatorException
     * @throws ParseException
     * @throws IOException
     * @throws ReportGenerationException
     */
    public ValidatorReport validate( ValidationSettings validationSettings, File planArchive, String planName )
                            throws ValidatorException, ParseException, IOException, ReportGenerationException {
        XPlanArchive archive = archiveCreator.createXPlanArchive( planArchive );
        ValidatorReport report = validate( validationSettings, archive, planName );
        writeReport( report );
        LOG.info( "Archiv mit Validierungsergebnissen wird erstellt." );
        Path validationReportDirectory = createZipArchive( validationSettings, report );
        LOG.info( "Archiv mit Validierungsergebnissen wurde unter {} abgelegt.", validationReportDirectory );
        return report;
    }

    /**
     * Validate a plan archive, but does not write the report
     *
     * @param validationSettings
     *                         to apply, never <code>null</code>
     * @param planArchive
     *                         to validate, never <code>null</code> and must point to a zip file with a gml plan
     * @return <link>ValidatorReport</link>
     * @throws ValidatorException
     * @throws IOException
     */
    public ValidatorReport validateNotWriteReport( ValidationSettings validationSettings, File planArchive,
                                                   String planName )
                            throws ValidatorException, IOException {
        XPlanArchive archive = archiveCreator.createXPlanArchive( planArchive );
        return validate( validationSettings, archive, planName );
    }

    /**
     * Write a report
     *
     * @param report
     *                         to write, never <code>null</code>
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
        List<SemanticValidationOptions> semanticValidationOptions = extractSemanticValidationOptions(
                                validationSettings );

        ValidatorReport report = new ValidatorReport();
        report.setValidationName( validationSettings.getValidationName() );
        report.setPlanName( planName );
        report.setDate( new Date() );

        List<ValidationType> validationType = getValidationType( validationSettings );
        validateSyntactic( archive, report );
        if ( validationType.contains( GEOMETRIC ) )
            validateGeometric( archive, voOptions, report );
        if ( validationType.contains( SEMANTIC ) )
            validateSemantic( archive, semanticValidationOptions, report );
        return report;
    }

    private void validateSyntactic( XPlanArchive archive, ValidatorReport report ) {
        SyntacticValidatorResult syntacticallyResult = validateSyntacticallyAndWriteResult( archive );
        report.setSyntacticValidatorResult( syntacticallyResult );
        if ( !syntacticallyResult.isValid() ) {
            report.setExternalReferenceReport( new ExternalReferenceReport( SYNTAX_ERRORS ) );
        } else {
            parseReferences( archive, report, null );
        }
    }

    private void validateGeometric( XPlanArchive archive, List<ValidationOption> voOptions, ValidatorReport report )
                            throws ValidatorException {
        if ( !report.getSyntacticValidatorResult().isValid() ) {
            report.setGeometricValidatorResult( new GeometricValidatorResult( SYNTAX_ERRORS ) );
        } else {
            GemetricValidatorParsingResult featuresAndResult = validateGeometricallyAndWriteResult( archive,
                                                                                                    voOptions );
            report.setGeometricValidatorResult( featuresAndResult.getValidatorResult() );
        }
    }

    private void validateSemantic( XPlanArchive archive, List<SemanticValidationOptions> semanticValidationOptions,
                                   ValidatorReport report ) {
        if ( !report.getSyntacticValidatorResult().isValid() ) {
            report.setSemanticValidatorResult( new SemanticValidatorResult( SYNTAX_ERRORS ) );
        } else {
            SemanticValidatorResult semanticallyResult = validateSemanticallyAndWriteResult( archive,
                                                                                             semanticValidationOptions );
            report.setSemanticValidatorResult( semanticallyResult );
        }
    }

    private List<ValidationType> getValidationType( ValidationSettings validationSettings ) {
        if ( validationSettings == null || validationSettings.getValidationTypes() == null
             || validationSettings.getValidationTypes().isEmpty() )
            return Collections.emptyList();
        return validationSettings.getValidationTypes();
    }

    /**
     * Perform geometric validation of the given archive
     *
     * @param archive
     *                         archive to validate, never <code>null</code>
     * @param voOptions
     *                         validation options, never <code>null</code>
     * @return the created report
     * @throws ValidatorException
     *                         - validation failed
     */
    GemetricValidatorParsingResult validateGeometricallyAndWriteResult( XPlanArchive archive,
                                                                        List<ValidationOption> voOptions )
                            throws ValidatorException {
        AppSchema appSchema = schemas.getAppSchema( archive.getVersion(), archive.getAde() );
        GemetricValidatorParsingResult result = geometricValidator.validateGeometry( archive, archive.getCrs(),
                                                                                     appSchema, true, voOptions );
        GeometricValidatorResult validatorResult = result.getValidatorResult();

        log( validatorResult );
        return result;
    }

    /**
     * Perform semantic validation of the given archive
     *
     * @param archive
     *                         archive to validate, never <code>null</code>
     * @param semanticValidationOptions
     *                         {@link List} of {@link SemanticValidationOptions}, considered by the validation, may be empty, but
     *                         never <code>null</code>
     * @return the created report
     */
    SemanticValidatorResult validateSemanticallyAndWriteResult( SemanticValidableXPlanArchive archive,
                                                                List<SemanticValidationOptions> semanticValidationOptions ) {
        ValidatorResult result = semanticValidator.validateSemantic( archive, semanticValidationOptions );
        SemanticValidatorResult validatorResult = (SemanticValidatorResult) result;
        log( validatorResult );
        return validatorResult;
    }

    /**
     * Perform syntactic validation of the given archive
     *
     * @param archive
     *                         archive to validate, never <code>null</code>
     * @return the created report, never <code>null</code>
     */
    SyntacticValidatorResult validateSyntacticallyAndWriteResult( XPlanArchive archive ) {
        ValidatorResult result = syntacticValidator.validateSyntax( archive );
        SyntacticValidatorResult validatorResult = (SyntacticValidatorResult) result;
        log( validatorResult );
        return validatorResult;
    }

    private void parseReferences( XPlanArchive archive, ValidatorReport report,
                                  GemetricValidatorParsingResult featuresAndResult ) {
        XPlanFeatureCollection featureCollection = parseFeatures( featuresAndResult, archive );
        parseAndAddExternalReferences( report, featureCollection );
    }

    private void parseAndAddExternalReferences( ValidatorReport report, XPlanFeatureCollection features ) {
        ExternalReferenceReport externalReferenceReport;
        if ( features != null )
            externalReferenceReport = parseAndAddExternalReferences( features.getFeatures() );
        else
            externalReferenceReport = new ExternalReferenceReport( INTERNAL_ERRORS );
        report.setExternalReferenceReport( externalReferenceReport );
    }

    private ExternalReferenceReport parseAndAddExternalReferences( FeatureCollection fc ) {
        ExternalReferenceScanner scanner = new ExternalReferenceScanner();
        ExternalReferenceInfo externalReferenceInfo = scanner.scan( fc );
        List<ExternalReference> allExternalReferences = externalReferenceInfo.getExternalRefs();
        List<String> references = new ArrayList<>();
        for ( ExternalReference ref : allExternalReferences ) {
            String referenzUrl = ref.getReferenzUrl();
            if ( referenzUrl != null )
                references.add( referenzUrl );
            String geoRefUrl = ref.getGeoRefUrl();
            if ( geoRefUrl != null )
                references.add( geoRefUrl );
        }
        return new ExternalReferenceReport( references );

    }

    private XPlanFeatureCollection parseFeatures( GemetricValidatorParsingResult validatorParsingResult,
                                                  XPlanArchive archive ) {
        if ( validatorParsingResult != null && validatorParsingResult.getFeatures() != null )
            return validatorParsingResult.getFeatures();
        try {
            AppSchema appSchema = schemas.getAppSchema( archive.getVersion(), archive.getAde() );
            XPlanFeatureCollection xPlanFeatureCollection = geometricValidator.retrieveGeometricallyValidXPlanFeatures(
                                    archive, archive.getCrs(), appSchema, true, null );
            return xPlanFeatureCollection;
        } catch ( XMLStreamException | UnknownCRSException | ValidatorException e ) {
            LOG.warn( "Parsing of external references failed", e );
            return null;
        }
    }

    private void log( GeometricValidatorResult validatorResult ) {
        LOG.info( "Ergebnisse der geometrischen Validierung:" );

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
        RulesMetadata rulesMetadata = validatorResult.getRulesMetadata();
        if ( rulesMetadata != null ) {
            LOG.info( "Informationen zur semantischen Validierung:" );
            LOG.info( "  - Version: {}", rulesMetadata.getVersion() );
            LOG.info( "  - Quelle: {}", rulesMetadata.getSource() );
        }
        List<RuleResult> ruleResults = validatorResult.getRules();
        LOG.info( "Ergebnisse der semantischen Validierung: {}", ruleResults.size() );
        for ( RuleResult ruleResult : ruleResults ) {
            if ( ruleResult.isValid() ) {
                LOG.info( "  - Erfolgreich: {}", ruleResult.getMessage() );
            } else {
                List<String> invalidFeatures = ruleResult.getInvalidFeatures();
                LOG.info( "  - Fehler: {}, fehlerhafte Features: {}", ruleResult.getMessage(),
                          invalidFeatures.stream().collect( Collectors.joining( ", " ) ) );
            }
        }
    }

    private void log( SyntacticValidatorResult validatorResult ) {
        List<String> messages = validatorResult.getMessages();
        LOG.info( "Ergebnisse der syntaktischen Validierung: {}", messages.size() );
        for ( String mess : messages )
            LOG.info( "  - {}", mess );
    }

    private List<SemanticValidationOptions> extractSemanticValidationOptions( ValidationSettings validationSettings ) {
        List<SemanticValidationOptions> semanticValidationOptions = new ArrayList<>();
        List<ValidationOption> extendedOptions = validationSettings.getExtendedOptions();
        if ( extendedOptions != null )
            for ( ValidationOption validationOption : extendedOptions ) {
                SemanticValidationOptions semanticValidationOption = SemanticValidationOptions.getByOption(
                                        validationOption );
                if ( !SemanticValidationOptions.NONE.equals( semanticValidationOption ) )
                    semanticValidationOptions.add( semanticValidationOption );
            }
        return semanticValidationOptions;
    }

    private Path createZipArchive( ValidationSettings validationSettings, ValidatorReport report )
                            throws ReportGenerationException {
        String validationName = validationSettings.getValidationName();
        return reportArchiveGenerator.generateZipArchive( report, validationName );
    }

}
