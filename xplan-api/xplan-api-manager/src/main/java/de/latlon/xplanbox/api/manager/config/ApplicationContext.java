package de.latlon.xplanbox.api.manager.config;

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.inspire.plu.transformation.hale.HaleCliInspirePluTransformator;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.internalid.InternalIdRetriever;
import de.latlon.xplan.manager.transformation.HaleXplan41ToXplan51Transformer;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static java.nio.file.Paths.get;

@Configuration
@ComponentScan(basePackages = { "de.latlon.xplanbox.api.manager" })
public class ApplicationContext {

    private static final String RULES_DIRECTORY = "/rules";

    @Bean
    public SyntacticValidator syntacticValidator() {
        return new SyntacticValidatorImpl();
    }

    @Bean
    public GeometricValidator geometricValidator() {
        return new GeometricValidatorImpl();
    }

    @Bean
    public SemanticValidator semanticValidator( ManagerConfiguration managerConfiguration, Path rulesPath )
                            throws URISyntaxException, ValidatorException {
        return new XQuerySemanticValidator( new XQuerySemanticValidatorConfigurationRetriever( rulesPath ),
                                            managerConfiguration.getSemanticConformityLinkConfiguration() );
    }

    @Bean
    public XPlanValidator xplanValidator( GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
                                          SemanticValidator semanticValidator,
                                          ReportArchiveGenerator reportArchiveGenerator ) {
        return new XPlanValidator( geometricValidator, syntacticValidator, semanticValidator, reportArchiveGenerator );
    }

    @Bean
    public XPlanManager xPlanManager( CategoryMapper categoryMapper, XPlanArchiveCreator archiveCreator,
                                      ManagerConfiguration managerConfiguration, WorkspaceReloader workspaceReloader,
                                      InspirePluTransformator inspirePluTransformator,
                                      XPlanGmlTransformer xPlanGmlTransformer )
                            throws Exception {
        return new XPlanManager( categoryMapper, archiveCreator, managerConfiguration, workspaceReloader,
                                 inspirePluTransformator, xPlanGmlTransformer );
    }

    @Bean
    public InternalIdRetriever internalIdRetriever( ManagerConfiguration managerConfiguration )
                            throws Exception {
        return new InternalIdRetriever( managerConfiguration.getInternalIdRetrieverConfiguration() );
    }

    @Bean
    public ReportArchiveGenerator reportArchiveGenerator()
                            throws IOException, ConfigurationException {
        return new ReportArchiveGenerator( validatorConfiguration() );
    }

    @Bean
    public ReportWriter reportWriter() {
        return new ReportWriter();
    }

    @Bean
    public XPlanArchiveCreator archiveCreator( CategoryMapper categoryMapper )
                            throws ConfigurationException {
        return new XPlanArchiveCreator( categoryMapper );
    }

    @Bean
    public CategoryMapper categoryMapper( ManagerConfiguration managerConfiguration )
                            throws ConfigurationException {
        return new CategoryMapper( managerConfiguration );
    }

    @Bean
    public ManagerConfiguration managerConfiguration( PropertiesLoader managerPropertiesLoader )
                            throws ConfigurationException {
        return new ManagerConfiguration( managerPropertiesLoader );
    }

    @Bean
    public WorkspaceReloader workspaceReloader() {
        return new WorkspaceReloader();
    }

    @Bean
    public InspirePluTransformator inspirePluTransformator( ManagerConfiguration managerConfiguration ) {
        String pathToHaleCli = managerConfiguration.getPathToHaleCli();
        Path pathToHaleProjectDirectory = managerConfiguration.getPathToHaleProjectDirectory();
        if ( pathToHaleCli != null && pathToHaleProjectDirectory != null )
            return new HaleCliInspirePluTransformator( pathToHaleCli, pathToHaleProjectDirectory );
        return null;
    }

    @Bean
    public XPlanGmlTransformer xPlanGmlTransformer( ManagerConfiguration managerConfiguration ) {
        String pathToHaleCli = managerConfiguration.getPathToHaleCli();
        Path pathToHaleProjectDirectory = managerConfiguration.getPathToHaleProjectDirectory();
        if ( pathToHaleCli != null && pathToHaleProjectDirectory != null ) {
            HaleXplan41ToXplan51Transformer haleXplan41ToXplan51Transformer = new HaleXplan41ToXplan51Transformer(
                                    pathToHaleCli, pathToHaleProjectDirectory );
            return new XPlanGmlTransformer( haleXplan41ToXplan51Transformer );
        }
        return null;
    }

    @Bean
    public ValidatorConfiguration validatorConfiguration()
                            throws IOException, ConfigurationException {
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        return validatorConfigurationParser.parse( new SystemPropertyPropertiesLoader( ValidatorConfiguration.class ) );
    }

    @Bean
    public PropertiesLoader managerPropertiesLoader() {
        return new SystemPropertyPropertiesLoader( ManagerConfiguration.class );
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

}