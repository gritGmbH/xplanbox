/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.workspace;

import org.junit.Test;

import java.util.List;

import static de.latlon.xplan.manager.workspace.WorkspaceReloadAction.ALL;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for {@link WorkspaceReloader}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class WorkspaceReloaderTest {

	@Test
	public void testReloadWorkspaceWithInvalidConfigurationShouldFail() {
		WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration();
		WorkspaceReloader workspaceReloader = new WorkspaceReloader(configuration);
		boolean isReloadSuccessful = workspaceReloader.reloadWorkspace(1);

		assertThat(isReloadSuccessful, is(false));
	}

	@Test
	public void testReloadWorkspaceWithInvalidUrlShouldFail() {
		List<String> urlList = singletonList("http://invalid-url");
		WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration(urlList, null, "user",
				"password", ALL);
		WorkspaceReloader workspaceReloader = new WorkspaceReloader(configuration);
		boolean isReloadSuccessful = workspaceReloader.reloadWorkspace(1);

		assertThat(isReloadSuccessful, is(false));
	}

	@Test
	public void testReloadWorkspaceWithTwoInvalidUrlsShouldFail() {
		List<String> urlList = asList("http://invalid-url1", "http://invalid-url2");
		WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration(urlList, null, "user",
				"password", ALL);
		WorkspaceReloader workspaceReloader = new WorkspaceReloader(configuration);
		boolean isReloadSuccessful = workspaceReloader.reloadWorkspace(1);

		assertThat(isReloadSuccessful, is(false));
	}

	@Test
	public void testReloadWorkspaceWithThreeInvalidUrlsShouldFail() {
		List<String> urlList = asList("http://invalid-url1", "http://invalid-url2", "http://invalid-url3");
		WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration(urlList, "apiKey", "user",
				"password", ALL);
		WorkspaceReloader workspaceReloader = new WorkspaceReloader(configuration);
		boolean isReloadSuccessful = workspaceReloader.reloadWorkspace(1);

		assertThat(isReloadSuccessful, is(false));
	}

}
