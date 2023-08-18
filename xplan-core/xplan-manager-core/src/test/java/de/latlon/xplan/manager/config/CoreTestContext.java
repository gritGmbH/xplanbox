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
package de.latlon.xplan.manager.config;

import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.manager.configuration.InternalIdRetrieverConfiguration;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.DeegreeWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import org.deegree.commons.config.DeegreeWorkspace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationType.gdal;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class CoreTestContext {

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
	public WmsWorkspaceWrapper wmsWorkspaceWrapper() throws WorkspaceException, IOException, URISyntaxException {
		DeegreeWorkspace deegreeWorkspace = mock(DeegreeWorkspace.class);
		DeegreeWorkspaceWrapper wmsWorkspace = mock(DeegreeWorkspaceWrapper.class);
		when(wmsWorkspace.getWorkspaceInstance()).thenReturn(deegreeWorkspace);
		Path tempWorkspaceDir = Files.createTempDirectory("xplan-manager-core");
		when(deegreeWorkspace.getLocation()).thenReturn(tempWorkspaceDir.toFile());
		return new WmsWorkspaceWrapper(wmsWorkspace.getWorkspaceInstance());
	}

}
