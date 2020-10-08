package de.latlon.xplanbox.api.validator.config;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
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
import de.latlon.xplan.validator.wms.ValidatorWmsManager;
import de.latlon.xplanbox.api.commons.handler.SystemConfigHandler;
import org.deegree.commons.config.DeegreeWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Paths.get;

@Configuration
@ComponentScan(basePackages = { "de.latlon.xplanbox.api.validator" })
public class ApplicationContext {

    private static final Logger LOG = LoggerFactory.getLogger( ApplicationContext.class );

    private static final String RULES_DIRECTORY = "/rules";

    private static final String XPLAN_GML_WMS_WORKSPACE = "xplan-validator-wms-workspace";

    @Bean
    public SystemConfigHandler systemConfigHandler(
                            XQuerySemanticValidatorConfigurationRetriever configurationRetriever ) {
        return new SystemConfigHandler( configurationRetriever );
    }

    @Bean
    public Path uploadFolder()
                            throws IOException {
        return createTempDirectory( "xplan-validator" );
    }

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
        return new XQuerySemanticValidatorConfigurationRetriever( rulesPath );
    }

    @Bean
    public PropertiesLoader validatorPropertiesLoader() {
        return new SystemPropertyPropertiesLoader( ValidatorConfiguration.class );
    }

    @Bean
    public ValidatorConfiguration validatorConfiguration( PropertiesLoader validatorPropertiesLoader )
                            throws IOException, ConfigurationException {
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        return validatorConfigurationParser.parse( validatorPropertiesLoader );
    }

    @Bean
    public ValidatorApiConfiguration validatorApiConfiguration( PropertiesLoader validatorPropertiesLoader )
                            throws ConfigurationException {
        return new ValidatorApiConfiguration( validatorPropertiesLoader );
    }

    @Bean
    public ReportArchiveGenerator reportArchiveGenerator( ValidatorConfiguration validatorConfiguration ) {
        return new ReportArchiveGenerator( validatorConfiguration );
    }

    @Bean
    public XPlanValidator xplanValidator( GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
                                          SemanticValidator semanticValidator,
                                          ReportArchiveGenerator reportArchiveGenerator ) {
        return new XPlanValidator( geometricValidator, syntacticValidator, semanticValidator, reportArchiveGenerator );
    }

    @Bean
    public ReportWriter reportWriter() {
        return new ReportWriter();
    }

    @Bean
    public ValidatorWmsManager validatorWmsManager( ValidatorConfiguration validatorConfiguration ) {
        String validatorWmsEndpoint = validatorConfiguration.getValidatorWmsEndpoint();
        if ( validatorWmsEndpoint == null ) {
            LOG.info( "XPlanValidatorWMS endpoint URL is not configured. Map preview will not be available." );
            return null;
        }
        try {
            XPlanSynthesizer synthesizer = new XPlanSynthesizer();
            Path workspaceLocation = Paths.get( DeegreeWorkspace.getWorkspaceRoot() ).resolve(
                                    XPLAN_GML_WMS_WORKSPACE );
            return new ValidatorWmsManager( synthesizer, workspaceLocation );
        } catch ( IOException | IllegalArgumentException e ) {
            LOG.error( "Could not initialise ValidatorWmsManager. WMS resources cannot be created. Reason: {}",
                       e.getMessage(), e );
        }
        return null;
    }

    @Bean
    public Path rulesPath( ValidatorConfiguration validatorConfiguration )
                            throws URISyntaxException {
        Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
        if ( validationRulesDirectory != null )
            return validationRulesDirectory;
        URI rulesPath = getClass().getResource( RULES_DIRECTORY ).toURI();
        return get( rulesPath );
    }

    private ValidatorWmsManager createValidatorWmsManager()
                            throws IOException {
        XPlanSynthesizer synthesizer = new XPlanSynthesizer();
        Path workspaceLocation = Paths.get( DeegreeWorkspace.getWorkspaceRoot() ).resolve( XPLAN_GML_WMS_WORKSPACE );
        return new ValidatorWmsManager( synthesizer, workspaceLocation );
    }

}