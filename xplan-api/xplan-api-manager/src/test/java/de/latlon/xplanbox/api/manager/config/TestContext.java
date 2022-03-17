/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplanbox.api.manager.config;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.InternalIdRetrieverConfiguration;
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
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.persistence.FeatureStore;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType.gdal;
import static org.mockito.ArgumentMatchers.nullable;
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
 * Indented to register the JAX-RS resources within Spring Application Context. Resources
 * not configured automatically. Using Jersey <code>ResourceConfig</code> with profile
 * <code>jaxrs</code> instead.
 *
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Configuration
public class TestContext {

	private static final Logger LOG = getLogger(TestContext.class);

	@Bean
	@Profile("jaxrs")
	ResourceConfig resourceConfig() {
		ResourceConfig jerseyConfig = new ResourceConfig();
		jerseyConfig.register(PlanApi.class);
		jerseyConfig.register(PlansApi.class);
		jerseyConfig.register(InfoApi.class);
		jerseyConfig.register(DefaultApi.class);
		return jerseyConfig;
	}

	@Bean
	@Primary
	public XPlanManager xPlanManager(XPlanDao xPlanDao, XPlanArchiveCreator archiveCreator,
			ManagerWorkspaceWrapper managerWorkspaceWrapper, WorkspaceReloader workspaceReloader,
			Optional<InspirePluTransformator> inspirePluTransformator,
			Optional<XPlanGmlTransformer> xPlanGmlTransformer, WmsWorkspaceWrapper wmsWorkspaceWrapper)
			throws Exception {
		return new XPlanManager(xPlanDao, archiveCreator, managerWorkspaceWrapper, workspaceReloader,
				inspirePluTransformator.orElse(null), xPlanGmlTransformer.orElse(null), wmsWorkspaceWrapper);
	}

	@Bean
	@Primary
	public ManagerApiConfiguration managerApiConfiguration() throws URISyntaxException {
		ManagerApiConfiguration managerApiConfiguration = mock(ManagerApiConfiguration.class);
		when(managerApiConfiguration.getApiUrl()).thenReturn(new URI("http://localhost:8080/xplan-api-manager"));
		when(managerApiConfiguration.getDefaultValidationConfiguration())
				.thenReturn(new DefaultValidationConfiguration());
		return managerApiConfiguration;
	}

	@Bean
	@Primary
	public ManagerWorkspaceWrapper managerWorkspaceWrapper(ManagerConfiguration managerConfiguration)
			throws WorkspaceException {
		ManagerWorkspaceWrapper managerWorkspaceWrapper = mock(ManagerWorkspaceWrapper.class);
		FeatureStore featureStore41 = mock(FeatureStore.class);
		when(featureStore41.getSchema()).thenReturn(XPlanSchemas.getInstance().getAppSchema(XPLAN_41, null));
		FeatureStore featureStore51 = mock(FeatureStore.class);
		when(featureStore51.getSchema()).thenReturn(XPlanSchemas.getInstance().getAppSchema(XPLAN_51, null));
		when(managerWorkspaceWrapper.lookupStore(eq(XPLAN_41), nullable(XPlanAde.class), any(PlanStatus.class)))
				.thenReturn(featureStore41);
		when(managerWorkspaceWrapper.lookupStore(eq(XPLAN_51), nullable(XPlanAde.class), any(PlanStatus.class)))
				.thenReturn(featureStore51);
		when(managerWorkspaceWrapper.getConfiguration()).thenReturn(managerConfiguration());
		return managerWorkspaceWrapper;
	}

	@Bean
	@Primary
	public XPlanRasterManager xPlanRasterManager(WmsWorkspaceWrapper wmsWorkspaceWrapper,
			ManagerConfiguration managerConfiguration) throws WorkspaceException {
		return new XPlanRasterManager(wmsWorkspaceWrapper, managerConfiguration);
	}

	@Bean
	@Primary
	public WmsWorkspaceWrapper wmsWorkspaceWrapper() throws WorkspaceException, IOException, URISyntaxException {
		DeegreeWorkspace deegreeWorkspace = mock(DeegreeWorkspace.class);
		DeegreeWorkspaceWrapper wmsWorkspace = mock(DeegreeWorkspaceWrapper.class);
		when(wmsWorkspace.getWorkspaceInstance()).thenReturn(deegreeWorkspace);
		Path tempWorkspaceDir = Files.createTempDirectory("xplan-api-manager");
		initWorkspace(tempWorkspaceDir);
		when(deegreeWorkspace.getLocation()).thenReturn(tempWorkspaceDir.toFile());
		return new WmsWorkspaceWrapper(wmsWorkspace.getWorkspaceInstance());
	}

	private void initWorkspace(Path dir) throws IOException, URISyntaxException {
		Path themesDir = dir.resolve("themes");
		java.nio.file.Files.createDirectory(themesDir);
		Files.copy(Paths.get(getClass().getResource("/bplanraster.xml").toURI()), themesDir.resolve("bplanraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/bplanraster.xml").toURI()),
				themesDir.resolve("bplanpreraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/bplanraster.xml").toURI()),
				themesDir.resolve("bplanarchiveraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/fplanraster.xml").toURI()), themesDir.resolve("fplanraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/fplanraster.xml").toURI()),
				themesDir.resolve("fplanpreraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/fplanraster.xml").toURI()),
				themesDir.resolve("fplanarchiveraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/rplanraster.xml").toURI()), themesDir.resolve("rplanraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/rplanraster.xml").toURI()),
				themesDir.resolve("rplanpreraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/rplanraster.xml").toURI()),
				themesDir.resolve("rplanarchiveraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/lplanraster.xml").toURI()), themesDir.resolve("lplanraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/lplanraster.xml").toURI()),
				themesDir.resolve("lplanpreraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/lplanraster.xml").toURI()),
				themesDir.resolve("lplanarchiveraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/soplanraster.xml").toURI()),
				themesDir.resolve("soplanraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/soplanraster.xml").toURI()),
				themesDir.resolve("soplanpreraster.xml"));
		Files.copy(Paths.get(getClass().getResource("/soplanraster.xml").toURI()),
				themesDir.resolve("soplanarchiveraster.xml"));
	}

	@Bean
	@Primary
	public XPlanDao xPlanDao(CategoryMapper categoryMapper, ManagerWorkspaceWrapper managerWorkspaceWrapper,
			ManagerConfiguration managerConfiguration) throws Exception {
		XPlanDao xplanDao = mock(XPlanDao.class);
		XPlan mockPlan3 = new XPlan("bplan_30", "3", "BP_Plan", "XPLAN_3");
		XPlan mockPlan41 = new XPlan("bplan_51", "123", "BP_Plan", "XPLAN_41");
		XPlan mockPlan51 = new XPlan("bplan_41", "2", "BP_Plan", "XPLAN_51");
		mockPlan3.setXplanMetadata(new AdditionalPlanData(FESTGESTELLT));
		mockPlan41.setXplanMetadata(new AdditionalPlanData(FESTGESTELLT));
		mockPlan51.setXplanMetadata(new AdditionalPlanData(FESTGESTELLT));
		when(xplanDao.getXPlanById(1)).thenReturn(mockPlan41);
		when(xplanDao.getXPlanById(123)).thenReturn(mockPlan41);
		when(xplanDao.getXPlanById(2)).thenReturn(mockPlan51);
		when(xplanDao.getXPlanById(3)).thenReturn(mockPlan3);
		when(xplanDao.retrieveXPlanArtefact("2")).thenReturn(getClass().getResourceAsStream("/xplan51.gml"))
				.thenReturn(getClass().getResourceAsStream("/xplan51.gml"))
				.thenReturn(getClass().getResourceAsStream("/xplan51-edited.gml"));
		List<XPlan> mockList = new ArrayList<>();
		mockList.add(mockPlan41);
		when(xplanDao.getXPlanByName("bplan_41")).thenReturn(mockList);
		when(xplanDao.getXPlansLikeName("bplan_41")).thenReturn(mockList);
		when(xplanDao.getXPlanList(anyBoolean())).thenReturn(mockList);
		XPlanArchiveContent mockArchive = mock(XPlanArchiveContent.class);
		when(xplanDao.retrieveAllXPlanArtefacts(anyString())).thenReturn(mockArchive);
		when(xplanDao.retrieveAllXPlanArtefacts("42")).thenThrow(new PlanNotFoundException(42));
		return xplanDao;
	}

	@Bean
	@Primary
	public XPlanInsertManager xPlanInsertManager(XPlanDao xPlanDao, XPlanExporter xPlanExporter,
			ManagerWorkspaceWrapper managerWorkspaceWrapper, XPlanRasterManager xPlanRasterManager,
			ManagerConfiguration managerConfiguration, WorkspaceReloader workspaceReloader,
			XPlanGmlTransformer xPlanGmlTransformer) throws Exception {
		XPlanInsertManager xplanInsertManager = mock(XPlanInsertManager.class);
		when(xplanInsertManager.importPlan(any(), nullable(ICRS.class), anyBoolean(), anyBoolean(), anyBoolean(),
				nullable(File.class), nullable(String.class), any())).thenReturn(Collections.singletonList(123));
		return xplanInsertManager;
	}

	@Bean
	@Primary
	public XPlanExporter xPlanExporter(ManagerConfiguration managerConfiguration) {
		XPlanExporter xPlanExporter = mock(XPlanExporter.class);
		doNothing().when(xPlanExporter).export(isA(OutputStream.class), isA(XPlanArchiveContent.class));
		return xPlanExporter;
	}

	@Bean
	@Primary
	public ManagerConfiguration managerConfiguration() {
		ManagerConfiguration mockedConfiguration = mock(ManagerConfiguration.class);
		when(mockedConfiguration.getRasterConfigurationType()).thenReturn(gdal);
		when(mockedConfiguration.getRasterConfigurationCrs()).thenReturn("EPSG:25832");
		when(mockedConfiguration.getSortConfiguration()).thenReturn(new SortConfiguration());
		when(mockedConfiguration.getInternalIdRetrieverConfiguration())
				.thenReturn(new InternalIdRetrieverConfiguration());
		return mockedConfiguration;
	}

	@Bean
	@Primary
	public WorkspaceReloader workspaceReloader() {
		WorkspaceReloader workspaceReloader = mock(WorkspaceReloader.class);
		when(workspaceReloader.reloadWorkspace(any())).thenReturn(true);
		return workspaceReloader;
	}

	@PostConstruct
	void initLoggingAdapter() {
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		LOG.trace("JUL logging enabled");
	}

}
