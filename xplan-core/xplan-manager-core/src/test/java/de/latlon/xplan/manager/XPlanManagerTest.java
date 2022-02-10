/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.Rechtsstand;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceUtils;
import org.apache.commons.io.IOUtils;
import org.deegree.commons.config.DeegreeWorkspace;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

import static de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType.gdal;
import static de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager.isGdalSuccessfullInitialized;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assume.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version 1.0
 */
public class XPlanManagerTest {

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

		Rechtsstand legislationStatus = xPlanManager.determineRechtsstand(pathToArchive);

		assertThat(legislationStatus.getCodeNumber(), is(4000));
	}

	private XPlanManager createXPlanManager() throws Exception {
		XPlanDao xPlanDao = mock(XPlanDao.class);
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		ManagerConfiguration managerConfiguration = mockManagerConfig();
		DeegreeWorkspace managerWorkspace = WorkspaceUtils
				.instantiateManagerWorkspace(managerWorkspaceDirectory.getAbsoluteFile());
		ManagerWorkspaceWrapper managerWorkspaceWrapper = mock(ManagerWorkspaceWrapper.class);
		when(managerWorkspaceWrapper.getConfiguration()).thenReturn(managerConfiguration);
		DeegreeWorkspace wmsWorkspace = WorkspaceUtils.instantiateWmsWorkspace(wmsWorkspaceDirectory.getAbsoluteFile());
		WmsWorkspaceWrapper wmsWorkspaceWrapper = mock(WmsWorkspaceWrapper.class);
		when(wmsWorkspaceWrapper.getLocation()).thenReturn(wmsWorkspaceDirectory.getAbsoluteFile());
		return new XPlanManager(xPlanDao, archiveCreator, managerWorkspaceWrapper, null, null, null,
				wmsWorkspaceWrapper);
	}

	private ManagerConfiguration mockManagerConfig() {
		ManagerConfiguration mockedConfiguration = mock(ManagerConfiguration.class);
		when(mockedConfiguration.getRasterConfigurationType()).thenReturn(gdal);
		when(mockedConfiguration.getRasterConfigurationCrs()).thenReturn("epsg:4326");
		when(mockedConfiguration.getSortConfiguration()).thenReturn(new SortConfiguration());
		return mockedConfiguration;
	}

	private String copyPlan() throws IOException {
		InputStream resource = ResourceAccessor.readResourceStream("xplan41/V4_1_ID_103.zip");
		FileOutputStream output = null;
		try {
			File resourceFile = File.createTempFile("XPlanManagerTest_", ".zip");
			output = new FileOutputStream(resourceFile);
			return resourceFile.getAbsolutePath();
		}
		finally {
			IOUtils.copy(resource, output);
			IOUtils.closeQuietly(resource);
		}
	}

}
