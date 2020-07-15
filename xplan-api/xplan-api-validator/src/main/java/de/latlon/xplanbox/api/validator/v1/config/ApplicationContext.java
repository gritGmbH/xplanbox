package de.latlon.xplanbox.api.validator.v1.config;

import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Path;

import static java.nio.file.Files.createTempDirectory;

@Configuration
@ComponentScan(basePackages = { "de.latlon.xplanbox.api.validator.v1" })
public class ApplicationContext {

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
    public ValidatorConfiguration validatorConfiguration()
                            throws IOException, ConfigurationException {
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        return validatorConfigurationParser.parse( new SystemPropertyPropertiesLoader( ValidatorConfiguration.class ) );
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

}