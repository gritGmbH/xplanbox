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
package de.latlon.xplan.manager.workspace;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLANSYN_WMS_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.findWorkspaceDirectory;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.deegree.commons.config.DeegreeWorkspace;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class WorkspaceUtilsTest {

	private static final String SERVICES = "services";

	private static final String LAYERS = "layers";

	private static final String THEMES = "themes";

	private static final String VAR_WORKSPACE_ROOT = "DEEGREE_WORKSPACE_ROOT";

	private static String oldWorkspaceRoot;

	private File wmsWorkspaceDirectory;

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
		Path wmsWorkspaceDir = createWmsWorkspace(workspaceRoot);

		workspaceName = workspaceDir.getFileName().toString();
		workspaceDirectory = workspaceDir.toFile();
		wmsWorkspaceDirectory = wmsWorkspaceDir.toFile();

		System.setProperty(VAR_WORKSPACE_ROOT, workspaceRoot.toFile().getAbsolutePath());
	}

	@Test
	@Ignore
	public void testInstantiateWorkspaceByDirectory() throws Exception {
		DeegreeWorkspace workspace = instantiateWorkspace(workspaceDirectory);

		assertThat(workspace.getLocation(), is(workspaceDirectory));
	}

	@Test
	public void testInstantiateWorkspaceByName() throws Exception {
		DeegreeWorkspace workspace = instantiateWorkspace(workspaceName);

		assertThat(workspace.getLocation(), is(workspaceDirectory));
	}

	@Test
	public void testInstantiateWorkspaceFromDirectory() throws Exception {
		DeegreeWorkspace workspace = instantiateWorkspace(workspaceName, workspaceDirectory);

		assertThat(workspace.getLocation(), is(workspaceDirectory));
	}

	@Test
	public void testInstantiateWorkspaceFromNameWithUnknownDirectory() throws Exception {
		DeegreeWorkspace workspace = instantiateWorkspace(workspaceName, new File("/UNKOWN/PATH"));

		assertThat(workspace.getLocation(), is(workspaceDirectory));
	}

	@Test
	public void testInstantiateWorkspaceFromNameWithNullDirectory() throws Exception {
		DeegreeWorkspace workspace = instantiateWorkspace(workspaceName, null);

		assertThat(workspace.getLocation(), is(workspaceDirectory));
	}

	@Test
	@Ignore
	public void testInstantiateWorkspaceFromDirectoryWithUnknownName() throws Exception {
		DeegreeWorkspace workspace = instantiateWorkspace("workspaceName-2", workspaceDirectory);

		assertThat(workspace.getLocation(), is(workspaceDirectory));
	}

	@Test
	@Ignore
	public void testInstantiateWorkspaceFromDirectoryWithNullName() throws Exception {
		DeegreeWorkspace workspace = instantiateWorkspace(null, workspaceDirectory);

		assertThat(workspace.getLocation(), is(workspaceDirectory));
	}

	@Test(expected = Exception.class)
	public void testInstantiateWorkspaceByDirectoryWithNotExistingPath() throws Exception {
		instantiateWorkspace(new File("/UNKOWN/PATH"));
	}

	@Test(expected = Exception.class)
	public void testInstantiateWorkspaceByNameWithUnknownName() throws Exception {
		instantiateWorkspace("workspaceName-2");
	}

	@Test(expected = Exception.class)
	public void testInstantiateWorkspaceWithUnknownNameAndNullDirectory() throws Exception {
		instantiateWorkspace("workspaceName-2", null);
	}

	@Test
	@Ignore
	public void testFindWorkspaceByDirectory() throws Exception {
		File workspaceDir = findWorkspaceDirectory(workspaceDirectory);

		assertThat(workspaceDir, is(workspaceDirectory));
	}

	@Test
	public void testFindWorkspaceFromNameWithNullDirectory() throws Exception {
		File workspaceDir = findWorkspaceDirectory(null);

		assertThat(workspaceDir, is(wmsWorkspaceDirectory));
	}

	@Test(expected = Exception.class)
	public void testFindWorkspaceWithNotExistingPath() throws Exception {
		findWorkspaceDirectory(new File("/UNKOWN/PATH"));
	}

	private Path createWorkspaceRoot() throws IOException {
		return Files.createTempDirectory("deegreeWorkspace-ROOT-WMS-WorkspaceManager-IT");
	}

	private Path createTmpWorkspace(Path workspaceRootDirectory) throws IOException {
		Path tmpWorkspaceDirectory = Files.createTempDirectory(workspaceRootDirectory,
				"deegreeWorkspace-WMS-WorkspaceManager-IT");
		Files.createDirectory(tmpWorkspaceDirectory.resolve(THEMES));
		Files.createDirectory(tmpWorkspaceDirectory.resolve(LAYERS));
		Files.createDirectory(tmpWorkspaceDirectory.resolve(SERVICES));
		return tmpWorkspaceDirectory;
	}

	private Path createWmsWorkspace(Path workspaceRoot) throws IOException {
		Path wmsWorkspaceDirectory = workspaceRoot.resolve(DEFAULT_XPLANSYN_WMS_WORKSPACE);
		Files.createDirectory(wmsWorkspaceDirectory);
		return wmsWorkspaceDirectory;
	}

}
