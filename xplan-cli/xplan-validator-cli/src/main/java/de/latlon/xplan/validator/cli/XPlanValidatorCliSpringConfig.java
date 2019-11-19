package de.latlon.xplan.validator.cli;

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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import static java.nio.file.Paths.get;

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
    public SemanticValidator semanticValidator( Path rulesPath )
                            throws ValidatorException {
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
    public ReportArchiveGenerator reportArchiveGenerator( ValidatorConfiguration validatorConfiguration ) {
        return new ReportArchiveGenerator( validatorConfiguration );
    }

    @Bean
    public Path rulesPath( ValidatorConfiguration validatorConfiguration )
                            throws URISyntaxException {
        Path validationRulesDirectory = validatorConfiguration.getValidationRulesDirectory();
        if ( validationRulesDirectory != null )
            return validationRulesDirectory;
        URL pathToRules = XPlanValidatorCliSpringConfig.class.getProtectionDomain().getCodeSource().getLocation();
        return get( pathToRules.toURI() ).getParent().resolve( "etc/rules" );
    }

    @Bean
    public ValidatorConfiguration validatorConfiguration()
                    throws IOException, ConfigurationException, URISyntaxException {
        ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
        return validatorConfigurationParser.parse( validatorPropertiesLoader() );
    }

    private PropertiesLoader validatorPropertiesLoader() throws URISyntaxException {
        return new ConfigurationDirectoryPropertiesLoader( retrieveEtcPath(), ValidatorConfiguration.class );
    }

    private Path retrieveEtcPath() throws URISyntaxException {
        URL jarPath = XPlanValidatorCliSpringConfig.class.getProtectionDomain().getCodeSource().getLocation();
        return get( jarPath.toURI() ).getParent().resolve( "etc" );
    }

}
