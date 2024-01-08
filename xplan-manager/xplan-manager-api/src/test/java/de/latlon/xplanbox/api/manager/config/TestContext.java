/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.InternalIdRetrieverConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.PlanNotFoundException;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.export.XPlanExporter;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.transaction.XPlanDeleteManager;
import de.latlon.xplan.manager.transaction.XPlanEditManager;
import de.latlon.xplan.manager.transaction.XPlanInsertManager;
import de.latlon.xplan.manager.transaction.service.XPlanDeleteService;
import de.latlon.xplan.manager.transaction.service.XPlanEditService;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.NoConfigRasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.RasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.GeotiffRasterEvaluation;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.RasterEvaluation;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.XPlanRasterEvaluator;
import de.latlon.xplan.manager.wmsconfig.raster.storage.FileSystemStorage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorage;
import de.latlon.xplan.manager.workspace.DeegreeWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplanbox.api.manager.v1.DefaultApi;
import de.latlon.xplanbox.api.manager.v1.InfoApi;
import de.latlon.xplanbox.api.manager.v1.PlanApi;
import de.latlon.xplanbox.api.manager.v1.PlansApi;
import liquibase.integration.spring.SpringLiquibase;

import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.feature.persistence.FeatureStore;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationType.gdal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
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
@EnableAutoConfiguration(exclude = { QuartzAutoConfiguration.class, LiquibaseAutoConfiguration.class })
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

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Bean
	public XPlanManager xPlanManager(XPlanDao xPlanDao, XPlanArchiveCreator archiveCreator,
			ManagerConfiguration managerConfiguration, WmsWorkspaceWrapper wmsWorkspaceWrapper,
			XPlanExporter xPlanExporter, XPlanRasterEvaluator xPlanRasterEvaluator,
			XPlanRasterManager xPlanRasterManager, SortPropertyReader sortPropertyReader,
			XPlanInsertManager xPlanInsertManager, XPlanEditManager xPlanEditManager,
			XPlanDeleteManager xPlanDeleteManager) throws Exception {
		return new XPlanManager(xPlanDao, archiveCreator, managerConfiguration, wmsWorkspaceWrapper, xPlanExporter,
				xPlanRasterEvaluator, xPlanRasterManager, sortPropertyReader, null, xPlanInsertManager,
				xPlanEditManager, xPlanDeleteManager);
	}

	@Bean
	@Primary
	public XPlanInsertManager xPlanInsertManager() throws Exception {
		XPlanInsertManager xplanInsertManager = mock(XPlanInsertManager.class);
		when(xplanInsertManager.importPlan(any(), nullable(ICRS.class), anyBoolean(), anyBoolean(),
				nullable(String.class), any()))
			.thenReturn(Collections.singletonList(123));
		return xplanInsertManager;
	}

	@Primary
	@Bean
	public XPlanEditManager xPlanEditManager(XPlanSynthesizer xPlanSynthesizer, XPlanDao xPlanDao,
			XPlanExporter xPlanExporter, ManagerConfiguration managerConfiguration, WorkspaceReloader workspaceReloader,
			XPlanRasterManager xPlanRasterManager, SortPropertyReader sortPropertyReader,
			XPlanEditService xPlanEditService) {
		return new XPlanEditManager(xPlanSynthesizer, xPlanDao, xPlanExporter, xPlanRasterManager, workspaceReloader,
				managerConfiguration, sortPropertyReader, xPlanEditService, null, null);
	}

	@Primary
	@Bean
	public XPlanEditService xPlanEditService() {
		return mock(XPlanEditService.class);
	}

	@Bean
	public XPlanDeleteManager xPlanDeleteManager(WorkspaceReloader workspaceReloader,
			XPlanRasterManager xPlanRasterManager, XPlanDeleteService xPlanDeleteService) {
		return new XPlanDeleteManager(xPlanRasterManager, workspaceReloader, xPlanDeleteService);
	}

	@Primary
	@Bean
	public XPlanDeleteService xPlanDeleteService() {
		return mock(XPlanDeleteService.class);
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
			throws SQLException {
		ManagerWorkspaceWrapper managerWorkspaceWrapper = mock(ManagerWorkspaceWrapper.class);
		FeatureStore featureStore41 = mock(FeatureStore.class);
		when(featureStore41.getSchema()).thenReturn(XPlanSchemas.getInstance().getAppSchema(XPLAN_41));
		FeatureStore featureStore51 = mock(FeatureStore.class);
		when(featureStore51.getSchema()).thenReturn(XPlanSchemas.getInstance().getAppSchema(XPLAN_51));
		when(managerWorkspaceWrapper.lookupStore(eq(XPLAN_41), any(PlanStatus.class))).thenReturn(featureStore41);
		when(managerWorkspaceWrapper.lookupStore(eq(XPLAN_51), any(PlanStatus.class))).thenReturn(featureStore51);
		Connection connection = mockConnection();
		when(managerWorkspaceWrapper.openConnection()).thenReturn(connection);
		return managerWorkspaceWrapper;
	}

	private static Connection mockConnection() throws SQLException {
		Connection connection = mock(Connection.class);
		DatabaseMetaData connectionMetadata = mock(DatabaseMetaData.class);
		Statement statement = mock(Statement.class);
		when(connection.createStatement()).thenReturn(statement);
		when(statement.executeQuery(anyString())).thenReturn(mock(ResultSet.class));
		when(connection.getMetaData()).thenReturn(connectionMetadata);
		when(connectionMetadata.getURL()).thenReturn("jdbc:h2:mem:testdb");
		when(connectionMetadata.getDatabaseProductName()).thenReturn("H2");
		when(connectionMetadata.getSQLKeywords()).thenReturn("CREATE DROP");
		return connection;
	}

	@Bean
	@Primary
	public XPlanRasterEvaluator xPlanRasterEvaluator(RasterEvaluation rasterEvaluation) {
		return new XPlanRasterEvaluator(rasterEvaluation);
	}

	@Bean
	@Primary
	public RasterEvaluation getRasterEvaluation(ManagerConfiguration managerConfiguration) {
		return new GeotiffRasterEvaluation(managerConfiguration.getRasterConfigurationCrs());
	}

	@Bean
	@Primary
	public XPlanRasterManager xPlanRasterManager(RasterStorage rasterStorage, RasterConfigManager rasterConfigManager)
			throws WorkspaceException {
		return new XPlanRasterManager(rasterStorage, rasterConfigManager, applicationEventPublisher);
	}

	@Bean
	public RasterStorage rasterStorage(WmsWorkspaceWrapper wmsWorkspaceWrapper, RasterEvaluation rasterEvaluation) {
		Path dataDirectory = wmsWorkspaceWrapper.getDataDirectory();
		return new FileSystemStorage(dataDirectory, rasterEvaluation);
	}

	@Bean
	public RasterConfigManager rasterConfigManager() {
		return new NoConfigRasterConfigManager();
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
	public XPlanDao xPlanDao(ManagerWorkspaceWrapper managerWorkspaceWrapper, ManagerConfiguration managerConfiguration)
			throws Exception {
		XPlanDao xplanDao = mock(XPlanDao.class);
		XPlan mockPlan_1 = new XPlan("bplan_41", "1", "BP_Plan", "XPLAN_41");
		XPlan mockPlan_123 = new XPlan("bplan_41", "123", "BP_Plan", "XPLAN_41");
		XPlan mockPlan_2 = new XPlan("bplan_51", "2", "BP_Plan", "XPLAN_51");
		XPlan mockPlan_3 = new XPlan("bplan_53", "3", "BP_Plan", "XPLAN_53");
		XPlan mockPlan_4 = new XPlan("fplan_51", "4", "FP_Plan", "XPLAN_51");
		XPlan mockPlan_5 = new XPlan("lplan_51", "5", "LP_Plan", "XPLAN_51");
		XPlan mockPlan_6 = new XPlan("soplan_40", "6", "SO_Plan", "XPLAN_40");
		XPlan mockPlan_7 = new XPlan("bplan_51", "7", "BP_Plan", "XPLAN_51");
		mockPlan_123.setXplanMetadata(new AdditionalPlanData(FESTGESTELLT));
		mockPlan_2.setXplanMetadata(new AdditionalPlanData(FESTGESTELLT));
		mockPlan_3.setXplanMetadata(new AdditionalPlanData(FESTGESTELLT));
		when(xplanDao.getXPlanById(1)).thenReturn(mockPlan_1);
		when(xplanDao.getXPlanById(123)).thenReturn(mockPlan_123);
		when(xplanDao.getXPlanById(2)).thenReturn(mockPlan_2);
		when(xplanDao.getXPlanById(3)).thenReturn(mockPlan_3);
		when(xplanDao.getXPlanById(4)).thenReturn(mockPlan_4);
		when(xplanDao.getXPlanById(5)).thenReturn(mockPlan_5);
		when(xplanDao.getXPlanById(6)).thenReturn(mockPlan_6);
		when(xplanDao.getXPlanById(7)).thenReturn(mockPlan_7);
		when(xplanDao.retrieveXPlanArtefact("2")).thenReturn(getClass().getResourceAsStream("/xplan51.gml"))
			.thenReturn(getClass().getResourceAsStream("/xplan51.gml"))
			.thenReturn(getClass().getResourceAsStream("/xplan51-edited.gml"));
		when(xplanDao.retrieveXPlanArtefact("7")).thenReturn(getClass().getResourceAsStream("/xplan51_ohneBereich.gml"))
			.thenReturn(getClass().getResourceAsStream("/xplan51_ohneBereich.gml"));
		when(xplanDao.retrieveXPlanArtefact(2)).thenReturn(getClass().getResourceAsStream("/xplan51.gml"))
			.thenReturn(getClass().getResourceAsStream("/xplan51.gml"))
			.thenReturn(getClass().getResourceAsStream("/xplan51-edited.gml"));
		when(xplanDao.retrieveXPlanArtefact(7)).thenReturn(getClass().getResourceAsStream("/xplan51_ohneBereich.gml"))
			.thenReturn(getClass().getResourceAsStream("/xplan51_ohneBereich.gml"));
		when(xplanDao.existsPlan(123)).thenReturn(true);
		List<XPlan> mockList = new ArrayList<>();
		mockList.add(mockPlan_123);
		when(xplanDao.getXPlanByName("bplan_41")).thenReturn(mockList);
		when(xplanDao.getXPlansLikeName("bplan_41")).thenReturn(mockList);
		when(xplanDao.getXPlanList()).thenReturn(mockList);
		when(xplanDao.retrieveAllXPlanArtefacts(anyString())).thenReturn(mock(List.class));
		when(xplanDao.retrieveAllXPlanArtefacts("42")).thenThrow(new PlanNotFoundException(42));
		return xplanDao;
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
		when(workspaceReloader.reloadWorkspace(1)).thenReturn(true);
		return workspaceReloader;
	}

	@PostConstruct
	void initLoggingAdapter() {
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		LOG.trace("JUL logging enabled");
	}

}
