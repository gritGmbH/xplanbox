package de.latlon.xplan.validator.web.spring.config;

import static java.nio.file.Paths.get;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.validator.wms.ValidatorWmsManager;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import de.latlon.xplan.commons.configuration.DefaultPropertiesLoader;
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
                                          ReportArchiveGenerator reportArchiveGenerator,
                                          ValidatorWmsManager validatorWmsManager ) {
        return new XPlanValidator( geometricValidator, syntacticValidator, semanticValidator, reportArchiveGenerator,
                                   validatorWmsManager );
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
        return validatorConfigurationParser.parse( new DefaultPropertiesLoader( ValidatorConfiguration.class ) );
    }

    @Bean
    public ValidatorWmsManager validatorWmsManager() {
        XPlanSynthesizer synthesizer = new XPlanSynthesizer();
        Path workspaceLocation = Paths.get( DeegreeWorkspace.getWorkspaceRoot() ).resolve( XPLAN_GML_WMS_WORKSPACE );
        try {
            return new ValidatorWmsManager( synthesizer, workspaceLocation );
        } catch ( IOException | IllegalArgumentException e ) {
            LOG.error( "Could not initialise ValidatorWmsManager. WMS resources cannot be created" );
        }
        return null;
    }

    @Bean
    public Path rulesPath()
            throws URISyntaxException {
        URI rulesPath = XPlanValidatorWebSpringConfig.class.getResource( RULES_DIRECTORY ).toURI();
        return get( rulesPath );
    }

}