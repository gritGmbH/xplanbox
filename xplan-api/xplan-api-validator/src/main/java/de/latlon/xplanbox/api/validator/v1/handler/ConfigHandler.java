package de.latlon.xplanbox.api.validator.v1.handler;

import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplanbox.api.validator.v1.model.RulesMetadata;
import de.latlon.xplanbox.api.validator.v1.model.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.IOException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class ConfigHandler {

    @Autowired
    private XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever;

    public SystemConfig describeSystem()
                            throws IOException {
        SemanticValidatorConfiguration semanticValidatorConfiguration = xQuerySemanticValidatorConfigurationRetriever.retrieveConfiguration();
        RulesMetadata rulesMetadata = new RulesMetadata();
        if ( semanticValidatorConfiguration != null && semanticValidatorConfiguration.getRulesMetadata() != null )
            rulesMetadata.source( semanticValidatorConfiguration.getRulesMetadata().getSource() ).version(
                                    semanticValidatorConfiguration.getRulesMetadata().getVersion() );
        return new SystemConfig().version( "TODO" ).rulesMetadata( rulesMetadata );
    }

}