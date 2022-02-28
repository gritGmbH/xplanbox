/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.workspace;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.deegree.commons.config.DeegreeWorkspace;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class DeegreeWorkspaceWrapperTest {

	private static final String VAR_WORKSPACE_ROOT = "DEEGREE_WORKSPACE_ROOT";

	private static String oldWorkspaceRoot;

	private File workspaceDirectory;

	private String workspaceName;

	@BeforeClass
	public static void storeWorkspaceProperty() throws IOException {
		oldWorkspaceRoot = System.getProperty(VAR_WORKSPACE_ROOT);
	}

	@AfterClass
	public static void resetWorkspaceRoot() {
		if (oldWorkspaceRoot != null)
			System.setProperty(VAR_WORKSPACE_ROOT, oldWorkspaceRoot);
		else
			System.getProperties().remove(VAR_WORKSPACE_ROOT);
	}

	@Before
	public void createTestWorkspaceFrame() throws IOException {
		Path workspaceRoot = createWorkspaceRoot();
		Path workspaceDir = createTmpWorkspace(workspaceRoot);

		workspaceName = workspaceDir.getFileName().toString();
		workspaceDirectory = workspaceDir.toFile();

		System.setProperty(VAR_WORKSPACE_ROOT, workspaceRoot.toFile().getAbsolutePath());
	}

	@Test
	public void testGetWorkspaceInstanceByDirectory() throws Exception {
		DeegreeWorkspace workspace = new DeegreeWorkspaceWrapper(workspaceDirectory).getWorkspaceInstance();

		assertThat(workspace, is(notNullValue()));
	}

	@Test
	public void testGetWorkspaceInstanceByName() throws Exception {
		DeegreeWorkspace workspace = new DeegreeWorkspaceWrapper(workspaceName).getWorkspaceInstance();

		assertThat(workspace, is(notNullValue()));
	}

	@Test
	public void testGetWorkspaceInstanceByNameAndDirectory() throws Exception {
		DeegreeWorkspace workspace = new DeegreeWorkspaceWrapper(workspaceName, workspaceDirectory)
				.getWorkspaceInstance();

		assertThat(workspace, is(notNullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeegreeWorkspaceWrapperStringWithNullWorkspaceName() throws Exception {
		String workspaceName = null;
		new DeegreeWorkspaceWrapper(workspaceName);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeegreeWorkspaceWrapperStringWithNullWorkspaceDir() throws Exception {
		File workspaceDir = null;
		new DeegreeWorkspaceWrapper(workspaceDir);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeegreeWorkspaceWrapperStringWithNullWorkspaceNameAndDir() throws Exception {
		new DeegreeWorkspaceWrapper(null, null);
	}

	private Path createWorkspaceRoot() throws IOException {
		return Files.createTempDirectory("deegreeWorkspace-ROOT-WMS-WorkspaceManager-IT");
	}

	private Path createTmpWorkspace(Path workspaceRootDirectory) throws IOException {
		Path tmpWorkspaceDirectory = Files.createTempDirectory(workspaceRootDirectory,
				"deegreeWorkspace-WMS-WorkspaceManager-IT");
		Files.createDirectory(tmpWorkspaceDirectory.resolve("themes"));
		Files.createDirectory(tmpWorkspaceDirectory.resolve("layers"));
		Files.createDirectory(tmpWorkspaceDirectory.resolve("services"));
		return tmpWorkspaceDirectory;
	}

}
