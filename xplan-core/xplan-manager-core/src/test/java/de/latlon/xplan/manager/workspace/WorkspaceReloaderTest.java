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
package de.latlon.xplan.manager.workspace;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;

/**
 * Tests for {@link WorkspaceReloader}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class WorkspaceReloaderTest {

	private final WorkspaceReloader workspaceReloader = new WorkspaceReloader();

	@Test
	public void testReloadWorkspaceWithInvalidConfigurationShouldFail() throws Exception {
		WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration();
		boolean isReloadSuccessful = workspaceReloader.reloadWorkspace(configuration);

		assertThat(isReloadSuccessful, is(false));
	}

	@Test
	public void testReloadWorkspaceWithInvalidUrlShouldFail() throws Exception {
		List<String> urlList = singletonList("http://invalid-url");
		WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration(urlList, "user", "password");
		boolean isReloadSuccessful = workspaceReloader.reloadWorkspace(configuration);

		assertThat(isReloadSuccessful, is(false));
	}

	@Test
	public void testReloadWorkspaceWithTwoInvalidUrlsShouldFail() throws Exception {
		List<String> urlList = asList("http://invalid-url1", "http://invalid-url2");
		WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration(urlList, "user", "password");
		boolean isReloadSuccessful = workspaceReloader.reloadWorkspace(configuration);

		assertThat(isReloadSuccessful, is(false));
	}

	@Test
	public void testReloadWorkspaceWithThreeInvalidUrlsShouldFail() throws Exception {
		List<String> urlList = asList("http://invalid-url1", "http://invalid-url2", "http://invalid-url3");
		WorkspaceReloaderConfiguration configuration = new WorkspaceReloaderConfiguration(urlList, "user", "password");
		boolean isReloadSuccessful = workspaceReloader.reloadWorkspace(configuration);

		assertThat(isReloadSuccessful, is(false));
	}

}
