package de.latlon.xplanbox.api.commons.handler;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplanbox.api.commons.v1.model.RulesMetadata;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SystemConfigHandler {

    private XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever;

    public SystemConfigHandler(
                            XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever ) {
        this.xQuerySemanticValidatorConfigurationRetriever = xQuerySemanticValidatorConfigurationRetriever;
    }

    public RulesMetadata getRulesMetadata()
                            throws IOException {
        SemanticValidatorConfiguration semanticValidatorConfiguration = xQuerySemanticValidatorConfigurationRetriever.retrieveConfiguration();
        RulesMetadata rulesMetadata = new RulesMetadata();
        if ( semanticValidatorConfiguration != null && semanticValidatorConfiguration.getRulesMetadata() != null )
            rulesMetadata.source( semanticValidatorConfiguration.getRulesMetadata().getSource() ).version(
                                    semanticValidatorConfiguration.getRulesMetadata().getVersion() );
        return rulesMetadata;
    }

    public List<VersionEnum> allSupportedVersions() {
        return Arrays.stream( XPlanVersion.values() ).filter(
                                xPlanVersion -> !XPlanVersion.XPLAN_SYN.equals( xPlanVersion ) ).map(
                                xPlanVersion -> VersionEnum.fromValue( xPlanVersion.name() ) ).collect(
                                Collectors.toList() );
    }

}