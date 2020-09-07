package de.latlon.xplanbox.api.validator.handler;

import de.latlon.xplanbox.api.commons.handler.SystemConfigHandler;
import de.latlon.xplanbox.api.commons.v1.model.SystemConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class ConfigHandler {

    private static final Logger LOG = getLogger( ConfigHandler.class );

    @Autowired
    private SystemConfigHandler systemConfigHandler;

    public SystemConfig describeSystem()
                            throws IOException {
        LOG.info( "Describe system" );
        return new SystemConfig().rulesMetadata( systemConfigHandler.getRulesMetadata() ).supportedXPlanGmlVersions(
                                systemConfigHandler.allSupportedVersions() );
    }

}