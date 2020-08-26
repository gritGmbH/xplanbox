package de.latlon.xplanbox.api.validator.v1.handler;

import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ArtifactType;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_FLAECHENSCHLUSS;
import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_GELTUNGSBEREICH;
import static de.latlon.xplan.validator.web.shared.ArtifactType.PDF;
import static de.latlon.xplan.validator.web.shared.ArtifactType.PNG;
import static de.latlon.xplan.validator.web.shared.ArtifactType.SHP;
import static de.latlon.xplan.validator.web.shared.ValidationType.GEOMETRIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SEMANTIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SYNTACTIC;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class ValidationHandler {

    private static final Logger LOG = LoggerFactory.getLogger( ValidationHandler.class );

    @Autowired
    private XPlanValidator xPlanValidator;

    @Autowired
    private Path uploadFolder;

    @Autowired
    private ReportWriter reportWriter;

    public ValidatorReport validate( File uploadedPlan, String validationName, boolean skipGeometrisch,
                                     boolean skipSemantisch, boolean skipFlaechenschluss, boolean skipGeltungsbereich )
                            throws IOException, ValidatorException {
        LOG.debug( "Validate plan with validationName {}", validationName );
        return validatePlan( uploadedPlan, validationName, skipGeometrisch, skipSemantisch, skipFlaechenschluss,
                             skipGeltungsbereich );
    }

    public Path zipReports( ValidatorReport validatorReport )
                            throws IOException {
        Path workDir = createWorkDir();
        String validationName = validatorReport.getValidationName();
        LOG.debug( "Create zip report in directory {} with validationName {}", workDir, validationName );

        reportWriter.writeArtefacts( validatorReport, workDir.toFile() );
        List<ArtifactType> artifacts = Arrays.asList( PDF, SHP, PNG );

        Path zipArchive = workDir.resolve( validationName + ".zip" );
        try (OutputStream zipOutput = Files.newOutputStream( zipArchive )) {
            reportWriter.writeZipWithArtifacts( zipOutput, validationName, artifacts, workDir.toFile() );
        }
        return zipArchive;
    }

    public File writePdfReport( ValidatorReport validatorReport )
                            throws IOException {
        Path workDir = createWorkDir();
        String validationName = validatorReport.getValidationName();
        LOG.debug( "Create pdf report in directory {} with validationName {}", workDir, validationName );

        reportWriter.writeArtefacts( validatorReport, workDir.toFile() );
        return reportWriter.retrieveArtifactFile( workDir.toFile(), validationName, PDF );
    }

    private ValidatorReport validatePlan( File uploadedPlan, String validationName, boolean skipGeometrisch,
                                          boolean skipSemantisch, boolean skipFlaechenschluss,
                                          boolean skipGeltungsbereich )
                            throws ValidatorException, IOException {
        ValidationSettings settings = new ValidationSettings();
        settings.setValidationName( validationName );
        settings.setValidationTypes( asValidationTypes( skipGeometrisch, skipSemantisch ) );
        settings.setExtendedOptions( asValidationOptions( skipFlaechenschluss, skipGeltungsbereich ) );
        ValidatorReport validatorReport = xPlanValidator.validateNotWriteReport( settings, uploadedPlan,
                                                                                 validationName );
        return validatorReport;
    }

    private Path createWorkDir()
                            throws IOException {
        String id = UUID.randomUUID().toString();
        Path workDir = uploadFolder.resolve( id );
        Files.createDirectory( workDir );
        return workDir;
    }

    private List<ValidationType> asValidationTypes( boolean skipGeometrisch, boolean skipSemantisch ) {
        List<ValidationType> validationTypes = new ArrayList<>();
        validationTypes.add( SYNTACTIC );
        if ( !skipSemantisch )
            validationTypes.add( SEMANTIC );
        if ( !skipGeometrisch )
            validationTypes.add( GEOMETRIC );
        return validationTypes;
    }

    private List<ValidationOption> asValidationOptions( boolean skipFlaechenschluss, boolean skipGeltungsbereich ) {
        List<ValidationOption> validationOptions = new ArrayList<>();
        if ( skipFlaechenschluss )
            validationOptions.add( SKIP_FLAECHENSCHLUSS );
        if ( skipGeltungsbereich )
            validationOptions.add( SKIP_GELTUNGSBEREICH );
        return validationOptions;
    }
}
