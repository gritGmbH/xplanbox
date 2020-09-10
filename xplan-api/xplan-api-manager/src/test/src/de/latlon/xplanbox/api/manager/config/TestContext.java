package de.latlon.xplanbox.api.manager.config;

import com.google.common.io.Files;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.DeegreeWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import de.latlon.xplanbox.api.manager.v1.DefaultApi;
import de.latlon.xplanbox.api.manager.v1.InfoApi;
import de.latlon.xplanbox.api.manager.v1.PlanApi;
import de.latlon.xplanbox.api.manager.v1.PlansApi;
import org.deegree.commons.config.DeegreeWorkspace;
import org.glassfish.jersey.server.ResourceConfig;
import org.mockito.Mockito;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLANSYN_WMS_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLAN_MANAGER_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static org.mockito.Mockito.when;

/**
 * Indented to register the JAX-RS resources within Spring Application Context.
 * TODO Resources not configured automatically. Using JerseyTest instead.
 */
@Configuration
public class TestContext {

    @Bean
    @Profile("jaxrs")
    ResourceConfig resourceConfig() {
        ResourceConfig jerseyConfig = new ResourceConfig();
        jerseyConfig.register( PlanApi.class );
        jerseyConfig.register( PlansApi.class );
        jerseyConfig.register( InfoApi.class );
        jerseyConfig.register( DefaultApi.class );
        return jerseyConfig;
    }

    @Bean @Primary
    public ManagerWorkspaceWrapper managerWorkspaceWrapper(ManagerConfiguration managerConfiguration )
            throws WorkspaceException {
        DeegreeWorkspace managerWorkspace = Mockito.mock( DeegreeWorkspace.class );
        ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper(
                managerWorkspace.getNewWorkspace(), managerConfiguration );
        return managerWorkspaceWrapper;
    }

    @Bean @Primary
    public XPlanRasterManager xPlanRasterManager(ManagerConfiguration managerConfiguration )
            throws WorkspaceException {
        DeegreeWorkspace deegreeWorkspace = Mockito.mock ( DeegreeWorkspace.class );
        DeegreeWorkspaceWrapper wmsWorkspace = Mockito.mock( DeegreeWorkspaceWrapper.class );
        when( wmsWorkspace.getWorkspaceInstance() ).thenReturn( deegreeWorkspace );
        when(deegreeWorkspace.getLocation()).thenReturn(Files.createTempDir().getAbsoluteFile());

        WmsWorkspaceWrapper wmsWorkspaceWrapper = new WmsWorkspaceWrapper( wmsWorkspace.getWorkspaceInstance() );
        return new XPlanRasterManager( wmsWorkspaceWrapper, managerConfiguration );
    }

    @PostConstruct
    void initLoggingAdapter() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }
}