package de.latlon.xplan.validator.web.spring.config;

import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.configuration.ValidatorConfigurationParser;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.report.ReportWriter;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;
import de.latlon.xplan.validator.web.server.service.ReportProvider;
import de.latlon.xplan.validator.web.server.service.ValidatorReportProvider;
import de.latlon.xplan.validator.wms.MapPreviewManager;
import de.latlon.xplan.validator.wms.MapPreviewCreationException;
import de.latlon.xplan.validator.wms.ValidatorWmsManager;
import org.deegree.commons.config.DeegreeWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Paths.get;

/**
 * XPlanValidatorWeb Application Configuration.
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author last edited by: $Author: erben $
 * @version $Revision: $, $Date: $
 */
@Configuration
public class XPlanValidatorWebSpringConfig {

    private static final Logger LOG = LoggerFactory.getLogger( XPlanValidatorWebSpringConfig.class );

    private static final String RULES_DIRECTORY = "/rules";

    public static final String XPLAN_GML_WMS_WORKSPACE = "xplan-gml-wms-workspace";

    @Bean
    public SyntacticValidator syntacticValidator() {
        return new SyntacticValidatorImpl();
    }

    @Bean
    public GeometricValidator geometricValidator() {
        return new GeometricValidatorImpl();
    }

    @Bean
    public SemanticValidator semanticValidator( XQuerySemanticValidatorConfigurationRetriever configurationRetriever )
                    throws ValidatorException {
        return new XQuerySemanticValidator( configurationRetriever );
    }

    @Bean
    public XQuerySemanticValidatorConfigurationRetriever configurationRetriever( Path rulesPath ) {
        return new XQuerySemanticValidatorConfigurationRetriever(rulesPath);
    }

    @Bean
    public XPlanValidator xplanValidator( GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
                                          SemanticValidator semanticValidator,
                                          ReportArchiveGenerator reportArchiveGenerator) {
        return new XPlanValidator( geometricValidator, syntacticValidator, semanticValidator, reportArchiveGenerator );
    }

    @Bean
    public ReportArchiveGenerator reportArchiveGenerator(ValidatorConfiguration validatorConfiguration) {
        return new ReportArchiveGenerator( validatorConfiguration );
    }

    @Bean
    public ReportWriter reportWriter() {
        return new ReportWriter();
    }

    @Bean
    public ReportProvider reportProvider() {
        return new ValidatorReportProvider();
    }

    @Bean
    public ValidatorConfiguration validatorConfiguration()
                    throws IOException, ConfigurationException {
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        return validatorConfigurationParser.parse( new SystemPropertyPropertiesLoader( ValidatorConfiguration.class ) );
    }

    @Bean
    public MapPreviewManager mapPreviewManager( GeometricValidator geometricValidator,
                                                ValidatorConfiguration validatorConfiguration ) {
        String validatorWmsEndpoint = validatorConfiguration.getValidatorWmsEndpoint();
        if ( validatorWmsEndpoint == null ) {
            LOG.info( "XPlanValidatorWMS endpoint URL is not configured. Map preview will not be available." );
            return null;
        }
        try {
            ValidatorWmsManager validatorWmsManager = createValidatorWmsManager();
            return new MapPreviewManager( validatorWmsManager, geometricValidator, validatorWmsEndpoint );
        } catch ( IOException | IllegalArgumentException | MapPreviewCreationException e ) {
            LOG.error( "Could not initialise ValidatorWmsManager. WMS resources cannot be created" );
        }
        return null;
    }

    @Bean
    public Path rulesPath( ValidatorConfiguration validatorConfiguration )
                            throws URISyntaxException {
        Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
        if ( validationRulesDirectory != null )
            return validationRulesDirectory;
        URI rulesPath = XPlanValidatorWebSpringConfig.class.getResource( RULES_DIRECTORY ).toURI();
        return get( rulesPath );
    }

    private ValidatorWmsManager createValidatorWmsManager()
                            throws IOException {
        XPlanSynthesizer synthesizer = new XPlanSynthesizer();
        Path workspaceLocation = Paths.get( DeegreeWorkspace.getWorkspaceRoot() ).resolve( XPLAN_GML_WMS_WORKSPACE );
        return new ValidatorWmsManager( synthesizer, workspaceLocation );
    }

}