/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplan.manager;

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.storage.filesystem.DeegreeRasterCacheCleaner;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.Rechtsstand;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.config.RasterStorageContext;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.RasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.RasterEvaluation;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.XPlanRasterEvaluator;
import de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorage;
import org.deegree.commons.utils.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import static de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationType.gdal;
import static de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter.isGdalSuccessfullInitialized;
import static org.apache.commons.io.IOUtils.close;
import static org.apache.commons.io.IOUtils.copy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version 1.0
 */
// TODO enable Sprint TestContext and turn all mock objects created here into Spring
// Beans.
// @RunWith(SpringRunner.class)
// @ContextConfiguration(classes = {CoreTestContext.class, RasterStorageContext.class})
public class XPlanManagerTest {

	private static final String CONFIGURED_CRS = "epsg:4326";

	private File managerWorkspaceDirectory;

	private File wmsWorkspaceDirectory;

	@Before
	public void createWorkspaceFiles() throws IOException {
		managerWorkspaceDirectory = Files.createTempDirectory("manager").toFile();
		wmsWorkspaceDirectory = Files.createTempDirectory("wms").toFile();
		File themesDir = new File(managerWorkspaceDirectory, "themes");
		Files.createDirectory(themesDir.toPath());
		File file = new File(themesDir, "bplanraster.xml");
		file.createNewFile();
		// TODO build minimal temp workspace
	}

	@After
	public void deleteWorkspace() {
		managerWorkspaceDirectory.delete();
	}

	@Test
	public void testEvaluateRasterdata() throws Exception {
		assumeTrue(isGdalSuccessfullInitialized());

		XPlanManager xPlanManager = createXPlanManager();
		String pathToArchive = copyPlan();

		List<RasterEvaluationResult> results = xPlanManager.evaluateRasterdata(pathToArchive);

		assertThat(results.size(), is(1));
		RasterEvaluationResult result = results.get(0);

		assertThat(result.isCrsSet(), is(false));
		assertThat(result.isConfiguredCrs(), is(false));
	}

	@Test
	public void testDetermineLegislationStatus() throws Exception {
		XPlanManager xPlanManager = createXPlanManager();
		String pathToArchive = copyPlan();

		Pair<Rechtsstand, PlanStatus> legislationStatus = xPlanManager.determineRechtsstand(pathToArchive);

		assertThat(legislationStatus.first.getCodeNumber(), is(3000));
		assertThat(legislationStatus.second, is(PlanStatus.FESTGESTELLT));
	}

	private XPlanManager createXPlanManager() throws Exception {
		XPlanDao xPlanDao = mock(XPlanDao.class);
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		ManagerConfiguration managerConfiguration = mockManagerConfig();
		WmsWorkspaceWrapper wmsWorkspaceWrapper = mock(WmsWorkspaceWrapper.class);
		when(wmsWorkspaceWrapper.getLocation()).thenReturn(wmsWorkspaceDirectory.getAbsoluteFile());
		RasterEvaluation rasterEvaluation = createRasterEvaluation(managerConfiguration);
		XPlanRasterEvaluator xPlanRasterEvaluator = new XPlanRasterEvaluator(rasterEvaluation);
		DeegreeRasterCacheCleaner deegreeRasterCacheCleaner = new DeegreeRasterCacheCleaner(
				managerConfiguration.getWorkspaceReloaderConfiguration());
		RasterStorage rasterStorage = createRasterStorage(managerConfiguration, wmsWorkspaceWrapper, rasterEvaluation,
				deegreeRasterCacheCleaner);
		RasterConfigManager rasterConfigManager = createRasterConfigManager(wmsWorkspaceWrapper, managerConfiguration);
		ApplicationEventPublisher applicationEventPublisher = createApplicationEventPublisher();
		XPlanRasterManager xPlanRasterManager = new XPlanRasterManager(rasterStorage, rasterConfigManager,
				applicationEventPublisher);
		return new XPlanManager(xPlanDao, archiveCreator, managerConfiguration, wmsWorkspaceWrapper, null,
				xPlanRasterEvaluator, xPlanRasterManager, null, null, null, null, null);
	}

	private ApplicationEventPublisher createApplicationEventPublisher() {
		return mock(ApplicationEventPublisher.class);
	}

	private RasterConfigManager createRasterConfigManager(WmsWorkspaceWrapper wmsWorkspaceWrapper,
			ManagerConfiguration managerConfiguration) {
		// TODO turn into autowired field
		return new RasterStorageContext().rasterConfigManager(wmsWorkspaceWrapper, managerConfiguration);
	}

	private RasterStorage createRasterStorage(ManagerConfiguration managerConfiguration,
			WmsWorkspaceWrapper wmsWorkspaceWrapper, RasterEvaluation rasterEvaluation,
			DeegreeRasterCacheCleaner deegreeRasterCacheCleaner) {
		// TODO turn into autowired field
		return new RasterStorageContext().rasterStorage(managerConfiguration, wmsWorkspaceWrapper, rasterEvaluation,
				deegreeRasterCacheCleaner);
	}

	private RasterEvaluation createRasterEvaluation(ManagerConfiguration managerConfiguration) {
		// TODO turn into autowired field
		return new RasterStorageContext().rasterEvaluation(managerConfiguration);
	}

	private ManagerConfiguration mockManagerConfig() {
		ManagerConfiguration mockedConfiguration = mock(ManagerConfiguration.class);
		when(mockedConfiguration.getRasterConfigurationType()).thenReturn(gdal);
		when(mockedConfiguration.getRasterConfigurationCrs()).thenReturn(CONFIGURED_CRS);
		when(mockedConfiguration.getSortConfiguration()).thenReturn(new SortConfiguration());
		return mockedConfiguration;
	}

	private String copyPlan() throws IOException {
		InputStream resource = getClass().getResourceAsStream("/testdata/xplan41/BPlan001_4-1.zip");
		FileOutputStream output = null;
		try {
			File resourceFile = Files.createTempFile("XPlanManagerTest_", ".zip").toFile();
			output = new FileOutputStream(resourceFile);
			return resourceFile.getAbsolutePath();
		}
		finally {
			copy(resource, output);
			close(resource);
		}
	}

}
