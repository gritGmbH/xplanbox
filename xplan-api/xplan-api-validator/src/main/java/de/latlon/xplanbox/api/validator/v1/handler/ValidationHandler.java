package de.latlon.xplanbox.api.validator.v1.handler;

import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ArtifactType;
import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;
import de.latlon.xplanbox.api.validator.v1.model.UploadReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_FLAECHENSCHLUSS;
import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_GELTUNGSBEREICH;
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

    public UploadReport upload( InputStream uploadedPlan, String fileName )
                            throws IOException {
        String id = UUID.randomUUID().toString();
        String suffix = parseSuffix( fileName );
        Path targetFile = uploadFolder.resolve( id + suffix );
        Files.copy( uploadedPlan, targetFile );
        LOG.debug( "Plan was written to {}", targetFile );
        return new UploadReport( id, fileName );
    }

    public ValidatorReport validate( String id, String name, boolean skipGeometrisch, boolean skipSemantisch,
                                     boolean skipFlaechenschluss, boolean skipGeltungsbereich )
                            throws IOException, ValidatorException {
        Path plan = uploadFolder.resolve( id + ".xml" );
        if ( !Files.exists( plan ) )
            plan = uploadFolder.resolve( id + ".gml" );
        if ( !Files.exists( plan ) )
            plan = uploadFolder.resolve( id + ".zip" );
        if ( !Files.exists( plan ) )
            throw new IllegalArgumentException( "Could not find plan with id " + id );

        ValidationSettings settings = new ValidationSettings();
        settings.setValidationName( name );
        settings.setValidationTypes( asValidationTypes( skipGeometrisch, skipSemantisch ) );
        settings.setExtendedOptions( asValidationOptions( skipFlaechenschluss, skipGeltungsbereich ) );
        ValidatorReport validatorReport = xPlanValidator.validateNotWriteReport( settings, plan.toFile(), name );

        Path reportDirectory = uploadFolder.resolve( id );
        reportWriter.writeArtefacts( validatorReport, reportDirectory.toFile() );

        return validatorReport;
    }

    public Path writeReport( String id, String validationName )
                            throws IOException {
        List<ArtifactType> artifacts = Arrays.asList( ArtifactType.values() );
        Path reportDirectory = uploadFolder.resolve( id );
        Files.createDirectory( reportDirectory );
        Path zipArchive = reportDirectory.resolve( id + ".zip" );
        try (OutputStream zipOutput = Files.newOutputStream( zipArchive )) {
            reportWriter.writeZipWithArtifacts( zipOutput, validationName, artifacts, reportDirectory.toFile() );
        }
        return zipArchive;
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

    private String parseSuffix( String fileName ) {
        int suffixStart = fileName.lastIndexOf( "." );
        if ( suffixStart < 0 )
            return ".xml";
        return fileName.substring( suffixStart );
    }

}
