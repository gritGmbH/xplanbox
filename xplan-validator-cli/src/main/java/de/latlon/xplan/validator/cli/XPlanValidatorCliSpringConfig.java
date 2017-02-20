package de.latlon.xplan.validator.cli;

import static java.nio.file.Paths.get;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.XPlanValidator;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.configuration.ValidatorConfigurationParser;
import de.latlon.xplan.validator.geometric.GeometricValidator;
import de.latlon.xplan.validator.geometric.GeometricValidatorImpl;
import de.latlon.xplan.validator.report.ReportArchiveGenerator;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidator;
import de.latlon.xplan.validator.syntactic.SyntacticValidatorImpl;

/**
 * Defines the application context for the validator CLI
 * 
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 */
@Configuration
public class XPlanValidatorCliSpringConfig {

    @Bean
    @Lazy
    @Scope("prototype")
    public SemanticValidator semanticValidator()
                    throws ValidatorException, URISyntaxException {
        URL pathToRules = XPlanValidatorCliSpringConfig.class.getProtectionDomain().getCodeSource().getLocation();
        Path rulesPath = get( pathToRules.toURI() ).getParent().resolve( "etc/rules" );
        return new XQuerySemanticValidator( new XQuerySemanticValidatorConfigurationRetriever( rulesPath ) );
    }

    @Bean
    public GeometricValidator geometricValidator() {
        return new GeometricValidatorImpl();
    }

    @Bean
    public SyntacticValidator syntacticValidator() {
        return new SyntacticValidatorImpl();
    }

    @Bean
    public XPlanValidator xplanValidator( GeometricValidator geometricValidator, SyntacticValidator syntacticValidator,
                                          SemanticValidator semanticValidator,
                                          ReportArchiveGenerator reportArchiveGenerator ) {
        return new XPlanValidator( geometricValidator, syntacticValidator, semanticValidator, reportArchiveGenerator );
    }

    @Bean
    public ReportArchiveGenerator reportArchiveGenerator()
                    throws IOException, ConfigurationException, URISyntaxException {
        return new ReportArchiveGenerator( validatorConfiguration() );
    }

    private ValidatorConfiguration validatorConfiguration()
                    throws IOException, ConfigurationException, URISyntaxException {
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        return validatorConfigurationParser.parse( validatorPropertiesLoader() );
    }

    private PropertiesLoader validatorPropertiesLoader() throws URISyntaxException {
        return new ConfigurationDirectoryPropertiesLoader( retrieveEtcPath(), ValidatorConfiguration.class );
    }

    private File retrieveEtcPath() throws URISyntaxException {
        URL jarPath = XPlanValidatorCliSpringConfig.class.getProtectionDomain().getCodeSource().getLocation();
        Path etcPath = get( jarPath.toURI() ).getParent().resolve( "etc" );
        return etcPath.toFile();
    }

}
