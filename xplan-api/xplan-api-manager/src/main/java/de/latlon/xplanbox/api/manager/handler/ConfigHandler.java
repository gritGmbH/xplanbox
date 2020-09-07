package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.configuration.DefaultValidationConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager;
import de.latlon.xplanbox.api.commons.handler.SystemConfigHandler;
import de.latlon.xplanbox.api.manager.v1.model.ManagerSystemConfig;
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

    @Autowired
    private ManagerConfiguration managerConfiguration;

    public ManagerSystemConfig describeManagerSystem()
                            throws IOException {
        LOG.info( "Describe system" );
        String rasterCrs = managerConfiguration.getRasterConfigurationCrs();
        WorkspaceRasterLayerManager.RasterConfigurationType rasterType = managerConfiguration.getRasterConfigurationType();
        DefaultValidationConfiguration defaultValidationConfiguration = managerConfiguration.getDefaultValidationConfiguration();

        return new ManagerSystemConfig().rulesMetadata(
                                systemConfigHandler.getRulesMetadata() ).supportedXPlanGmlVersions(
                                systemConfigHandler.allSupportedVersions() ).rasterCrs( rasterCrs ).rasterType(
                                rasterType.name() ).skipSemantisch(
                                defaultValidationConfiguration.isSkipSemantisch() ).skipGeometrisch(
                                defaultValidationConfiguration.isSkipGeometrisch() ).skipFlaechenschluss(
                                defaultValidationConfiguration.isSkipFlaechenschluss() ).skipGeltungsbereich(
                                defaultValidationConfiguration.isSkipGeltungsbereich() );
    }

}