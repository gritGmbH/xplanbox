package de.latlon.xplanbox.api.validator.v1.handler;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplanbox.api.commons.v1.model.RulesMetadata;
import de.latlon.xplanbox.api.commons.v1.model.SystemConfig;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class ConfigHandler {

    private static final Logger LOG = getLogger( ConfigHandler.class );

    @Autowired
    private XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever;

    public SystemConfig describeSystem()
                            throws IOException {
        LOG.info( "Describe system" );
        SemanticValidatorConfiguration semanticValidatorConfiguration = xQuerySemanticValidatorConfigurationRetriever.retrieveConfiguration();
        RulesMetadata rulesMetadata = new RulesMetadata();
        if ( semanticValidatorConfiguration != null && semanticValidatorConfiguration.getRulesMetadata() != null )
            rulesMetadata.source( semanticValidatorConfiguration.getRulesMetadata().getSource() ).version(
                                    semanticValidatorConfiguration.getRulesMetadata().getVersion() );
        return new SystemConfig().version( "TODO" ).supportedXPlanGmlVersions( allSupportedVersions() ).rulesMetadata(
                                rulesMetadata );
    }

    private List<VersionEnum> allSupportedVersions() {
        return Arrays.stream( XPlanVersion.values() ).filter(
                                xPlanVersion -> !XPlanVersion.XPLAN_SYN.equals( xPlanVersion ) ).map(
                                xPlanVersion -> VersionEnum.fromValue( xPlanVersion.name() ) ).collect(
                                Collectors.toList() );
    }

}