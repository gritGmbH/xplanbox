/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplanbox.api.manager.config;

import com.google.common.io.Files;
import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.PlanNotFoundException;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanArchiveContent;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
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
import org.deegree.feature.persistence.FeatureStore;
import org.glassfish.jersey.server.ResourceConfig;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Creates mock objects for XPlanManager and underlying objects for in-memory unit tests.
 * Indented to register the JAX-RS resources within Spring Application Context.
 * Resources not configured automatically. Using Jersey <code>ResourceConfig</code> with profile <code>jaxrs</code> instead.
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

    @Bean
    @Primary
    public XPlanManager xPlanManager( XPlanArchiveCreator archiveCreator,
                                      ManagerConfiguration managerConfiguration, WorkspaceReloader workspaceReloader,
                                      InspirePluTransformator inspirePluTransformator,
                                      XPlanGmlTransformer xPlanGmlTransformer,
                                      ManagerWorkspaceWrapper managerWorkspaceWrapper, XPlanDao xPlanDao )
                    throws Exception {
        return new XPlanManager( archiveCreator, managerConfiguration, workspaceReloader,
                                 inspirePluTransformator, xPlanGmlTransformer, managerWorkspaceWrapper, xPlanDao );
    }

    @Bean @Primary
    public ManagerApiConfiguration managerApiConfiguration()
                            throws URISyntaxException {
        ManagerApiConfiguration managerApiConfiguration = mock( ManagerApiConfiguration.class );
        when( managerApiConfiguration.getApiUrl() ).thenReturn( new URI( "http://localhost:8080/xplan-api-manager" ) );
        when( managerApiConfiguration.getDefaultValidationConfiguration() ).thenReturn( new DefaultValidationConfiguration() );
        return managerApiConfiguration;
    }

    @Bean @Primary
    public ManagerWorkspaceWrapper managerWorkspaceWrapper()
                    throws WorkspaceException {
        ManagerWorkspaceWrapper managerWorkspaceWrapper = mock( ManagerWorkspaceWrapper.class );
        FeatureStore featureStore41 = mock( FeatureStore.class );
        when( featureStore41.getSchema() ).thenReturn(
                        XPlanSchemas.getInstance().getAppSchema( XPLAN_41, null ) );
        FeatureStore featureStore51 = mock( FeatureStore.class );
        when( featureStore51.getSchema() ).thenReturn(
                        XPlanSchemas.getInstance().getAppSchema( XPLAN_51, null ) );
        when( managerWorkspaceWrapper.lookupStore( eq( XPLAN_41 ), any( XPlanAde.class ),
                                                   any( PlanStatus.class ) ) ).thenReturn( featureStore41 );
        when( managerWorkspaceWrapper.lookupStore( eq( XPLAN_51 ), any( XPlanAde.class ),
                                                   any( PlanStatus.class ) ) ).thenReturn( featureStore51 );
        return managerWorkspaceWrapper;
    }

    @Bean @Primary
    public XPlanRasterManager xPlanRasterManager(ManagerConfiguration managerConfiguration )
            throws WorkspaceException {
        DeegreeWorkspace deegreeWorkspace = mock ( DeegreeWorkspace.class );
        DeegreeWorkspaceWrapper wmsWorkspace = mock( DeegreeWorkspaceWrapper.class );
        when(wmsWorkspace.getWorkspaceInstance()).thenReturn( deegreeWorkspace );
        when(deegreeWorkspace.getLocation()).thenReturn(Files.createTempDir().getAbsoluteFile());

        WmsWorkspaceWrapper wmsWorkspaceWrapper = new WmsWorkspaceWrapper( wmsWorkspace.getWorkspaceInstance() );
        return new XPlanRasterManager( wmsWorkspaceWrapper, managerConfiguration );
    }

    @Bean @Primary
    public XPlanDao xPlanDao(CategoryMapper categoryMapper, ManagerWorkspaceWrapper managerWorkspaceWrapper,
                             ManagerConfiguration managerConfiguration ) throws Exception {
        XPlanDao xplanDao = mock( XPlanDao.class );
        XPlan mockPlan41 = new XPlan("bplan_51", "123", "BP_Plan", "XPLAN_41");
        XPlan mockPlan51 = new XPlan("bplan_41", "2", "BP_Plan", "XPLAN_51");
        mockPlan41.setXplanMetadata( new AdditionalPlanData( FESTGESTELLT ) );
        mockPlan51.setXplanMetadata( new AdditionalPlanData( FESTGESTELLT ) );
        when(xplanDao.getXPlanById(1)).thenReturn(mockPlan41);
        when(xplanDao.getXPlanById(123)).thenReturn(mockPlan41);
        when(xplanDao.getXPlanById(2)).thenReturn(mockPlan51);
        when( xplanDao.retrieveXPlanArtefact( "2" ) ).thenReturn(
                        getClass().getResourceAsStream( "/xplan51.gml" ) ).thenReturn(
                        getClass().getResourceAsStream( "/xplan51.gml" ) );
        List<XPlan> mockList = new ArrayList<>();
        mockList.add(mockPlan41);
        when(xplanDao.getXPlanByName("bplan_41")).thenReturn(mockList);
        when(xplanDao.getXPlansLikeName("bplan_41")).thenReturn(mockList);
        when(xplanDao.getXPlanList(anyBoolean())).thenReturn(mockList);
        XPlanArchiveContent mockArchive = mock(XPlanArchiveContent.class);
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
        XPlanInsertManager xplanInsertManager = mock ( XPlanInsertManager.class );
        when(xplanInsertManager.importPlan(any(), any(), anyBoolean(), anyBoolean(), anyBoolean(), any(), anyString(), any() )).thenReturn(123);
        return xplanInsertManager;
    }

    @Bean @Primary
    public XPlanExporter xPlanExporter( ManagerConfiguration managerConfiguration ) {
        XPlanExporter xPlanExporter = mock(XPlanExporter.class);
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
