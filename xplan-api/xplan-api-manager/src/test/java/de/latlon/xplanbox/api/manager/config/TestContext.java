package de.latlon.xplanbox.api.manager.config;

import com.google.common.io.Files;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.PlanNotFoundException;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanArchiveContent;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.workspace.DeegreeWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplanbox.api.manager.v1.DefaultApi;
import de.latlon.xplanbox.api.manager.v1.InfoApi;
import de.latlon.xplanbox.api.manager.v1.PlanApi;
import de.latlon.xplanbox.api.manager.v1.PlansApi;
import org.deegree.commons.config.DeegreeWorkspace;
import org.glassfish.jersey.server.ResourceConfig;
import org.mockito.Mockito;
import org.mockito.internal.listeners.CollectCreatedMocks;
import org.mockito.internal.progress.MockingProgress;
import org.mockito.internal.progress.ThreadSafeMockingProgress;
import org.slf4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Indented to register the JAX-RS resources within Spring Application Context.
 * TODO Resources not configured automatically. Using JerseyTest instead.
 */
@Configuration
public class TestContext {

    private static final Logger LOG = getLogger( TestContext.class );

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

    /**
     * Returns for plan with ID 1 and 123 a valid XPlanArchive (with XPlanGML 4.1 plan)
     * for plan with ID 42 returns an exception.
     *
     * @param categoryMapper not in use
     * @param managerWorkspaceWrapper not in use
     * @param managerConfiguration not in use
     * @return mock object
     * @throws Exception in any case of trouble
     */
    @Bean @Primary
    public XPlanDao xPlanDao(CategoryMapper categoryMapper, ManagerWorkspaceWrapper managerWorkspaceWrapper,
                             ManagerConfiguration managerConfiguration ) throws Exception {
        XPlanDao xplanDao = Mockito.mock( XPlanDao.class );
        XPlan mockPlan = new XPlan("bplan_41", "123", "B_PLAN", "XPLAN_41");
        when(xplanDao.getXPlanById(1)).thenReturn(mockPlan);
        when(xplanDao.getXPlanById(123)).thenReturn(mockPlan);
        List<XPlan> mockList = new ArrayList<>();
        mockList.add(mockPlan);
        when(xplanDao.getXPlanByName("bplan_41")).thenReturn(mockList);
        when(xplanDao.getXPlansLikeName("bplan_41")).thenReturn(mockList);
        when(xplanDao.getXPlanList(anyBoolean())).thenReturn(mockList);
        XPlanArchiveContent mockArchive = Mockito.mock(XPlanArchiveContent.class);
        when(xplanDao.retrieveAllXPlanArtefacts( anyString() )).thenReturn(mockArchive);
        when(xplanDao.retrieveAllXPlanArtefacts( "42" )).thenThrow( new PlanNotFoundException(42));
        return xplanDao;
    }

    @Bean @Primary
    public XPlanInsertManager xPlanInsertManager(XPlanDao xPlanDao, XPlanExporter xPlanExporter,
                                                 ManagerWorkspaceWrapper managerWorkspaceWrapper,
                                                 XPlanRasterManager xPlanRasterManager,
                                                 ManagerConfiguration managerConfiguration,
                                                 WorkspaceReloader workspaceReloader,
                                                 XPlanGmlTransformer xPlanGmlTransformer )
            throws Exception {
        XPlanInsertManager xplanInsertManager = Mockito.mock ( XPlanInsertManager.class );
        when(xplanInsertManager.importPlan(any(), any(), anyBoolean(), anyBoolean(), anyBoolean(), any(), anyString(), any() )).thenReturn(123);
        return xplanInsertManager;
    }

    @Bean @Primary
    public XPlanExporter xPlanExporter( ManagerConfiguration managerConfiguration ) {
        XPlanExporter xPlanExporter = Mockito.mock(XPlanExporter.class);
        doNothing().when(xPlanExporter).export(isA(OutputStream.class), isA(XPlanArchiveContent.class));
        return xPlanExporter;
    }

    private final List<Object> createdMocks = new LinkedList<Object>();

    @PostConstruct
    void initMockListener() {
        MockingProgress progress = new ThreadSafeMockingProgress();
        progress.setListener(new CollectCreatedMocks(createdMocks));
    }

    @PostConstruct
    void initLoggingAdapter() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @PreDestroy
    void showAllMocks() {
        createdMocks.forEach( mock -> {
            LOG.debug("Used " + mock);
            //verifyNoMoreInteractions(mock);
        });
    }
}