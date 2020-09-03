package de.latlon.xplanbox.api.validator.v1.handler;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.report.ValidatorReport;
import de.latlon.xplan.validator.web.shared.ArtifactType;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.wms.MapPreviewCreationException;
import de.latlon.xplan.validator.wms.ValidatorWmsManager;
import org.apache.http.client.utils.URIBuilder;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.types.AppSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static de.latlon.xplan.validator.web.shared.ArtifactType.PDF;
import static de.latlon.xplan.validator.web.shared.ArtifactType.PNG;
import static de.latlon.xplan.validator.web.shared.ArtifactType.SHP;

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

    @Autowired
    private ValidatorWmsManager validatorWmsManager;

    @Autowired
    private ValidatorConfiguration validatorConfiguration;

    @Autowired
    private GeometricValidator geometricValidator;

    public ValidatorReport validate( File uploadedPlan, String validationName, ValidationSettings validationSettings )
                            throws IOException, ValidatorException {
        LOG.debug( "Validate plan with validationName {}", validationName );
        return xPlanValidator.validateNotWriteReport( validationSettings, uploadedPlan, validationName );
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

    public URI addToWms( File uploadedPlan ) {
        try {
            if ( validatorWmsManager != null ) {

                XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
                XPlanArchive archive = archiveCreator.createXPlanArchive( uploadedPlan );
                XPlanFeatureCollection xPlanFeatureCollection = parseFeatures( archive );
                int id = validatorWmsManager.insert( xPlanFeatureCollection );
                return createWmsUrl( id );
            }
        } catch ( MapPreviewCreationException | URISyntaxException | IOException e ) {
            LOG.error( "Plan could not be added to the XPlanValidatorWMS. Reason {}", e.getMessage(), e );
        }
        return null;
    }

    private URI createWmsUrl( int id )
                            throws URISyntaxException {
        String validatorWmsEndpoint = validatorConfiguration.getValidatorWmsEndpoint();
        URIBuilder uriBuilder = new URIBuilder( validatorWmsEndpoint );
        uriBuilder.addParameter( "PLANWERK_MANAGERID", Integer.toString( id ) );
        uriBuilder.addParameter( "SERVICE", "WMS" );
        uriBuilder.addParameter( "REQUEST", "GetCapabilities" );
        return uriBuilder.build();
    }

    private XPlanFeatureCollection parseFeatures( XPlanArchive archive ) {
        try {
            XPlanSchemas schemas = XPlanSchemas.getInstance();
            AppSchema appSchema = schemas.getAppSchema( archive.getVersion(), archive.getAde() );
            XPlanFeatureCollection xPlanFeatureCollection = geometricValidator.retrieveGeometricallyValidXPlanFeatures(
                                    archive, archive.getCrs(), appSchema, true, null );
            return xPlanFeatureCollection;
        } catch ( XMLStreamException | UnknownCRSException | ValidatorException e ) {
            LOG.warn( "Parsing of external references failed", e );
            return null;
        }
    }

    private Path createWorkDir()
                            throws IOException {
        String id = UUID.randomUUID().toString();
        Path workDir = uploadFolder.resolve( id );
        Files.createDirectory( workDir );
        return workDir;
    }

}
