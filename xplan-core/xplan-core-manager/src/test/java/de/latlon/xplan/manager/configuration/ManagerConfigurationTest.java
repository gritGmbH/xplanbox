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
package de.latlon.xplan.manager.configuration;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SemanticConformityLinkConfiguration;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import org.junit.Test;

import java.util.Properties;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_51;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.RASTER_CONFIG_CRS;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.RASTER_LAYER_SCALE_DENOMINATOR_MAX;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.RASTER_LAYER_SCALE_DENOMINATOR_MIN;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.WORKSPACE_RELOAD_PASSWORD;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.WORKSPACE_RELOAD_URLS;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.WORKSPACE_RELOAD_USER;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerConfigurationTest {

	@Test
	public void testGetRasterConfigurationCrs() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoader());

		String rasterConfigurationCrs = managerConfiguration.getRasterConfigurationCrs();

		assertThat(rasterConfigurationCrs, is("epsg:4326"));
	}

	@Test
	public void testGetSortConfiguration() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoader());

		SortConfiguration sortConfiguration = managerConfiguration.getSortConfiguration();

		assertThat(sortConfiguration.retrieveFeatureType(BP_Plan, XPLAN_50), is("BP_Plan"));
		assertThat(sortConfiguration.retrievePropertyName(BP_Plan, XPLAN_50), is("rechtsverordnungsDatum"));
	}

	@Test
	public void testGetSemanticConformityLinkConfiguration() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoader());

		SemanticConformityLinkConfiguration semanticConformityLinkConfiguration = managerConfiguration
			.getSemanticConformityLinkConfiguration();
		assertThat(semanticConformityLinkConfiguration.retrieveLink(XPLAN_50), is("http://link.de/to.pdf"));
		assertThat(semanticConformityLinkConfiguration.retrieveLink(XPLAN_51), nullValue());
	}

	@Test
	public void testGetWorkspaceReloaderConfiguration() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoader());

		WorkspaceReloaderConfiguration workspaceReloaderConfiguration = managerConfiguration
			.getWorkspaceReloaderConfiguration();

		assertThat(workspaceReloaderConfiguration.getUrls().size(), is(2));
		assertThat(workspaceReloaderConfiguration.getUrls().get(0), is("url1"));
		assertThat(workspaceReloaderConfiguration.getUrls().get(1), is("url2"));
		assertThat(workspaceReloaderConfiguration.getUser(), is("user"));
		assertThat(workspaceReloaderConfiguration.getPassword(), is("password"));
	}

	@Test
	public void testDefaultScaleDenominators() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoader());

		assertThat(managerConfiguration.getRasterLayerMinScaleDenominator(), is(Double.NaN));
		assertThat(managerConfiguration.getRasterLayerMaxScaleDenominator(), is(Double.NaN));
	}

	@Test
	public void testScaleDenominators() throws Exception {
		double min = 5;
		double max = 1000;
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoader(min, max));

		assertThat(managerConfiguration.getRasterLayerMinScaleDenominator(), is(min));
		assertThat(managerConfiguration.getRasterLayerMaxScaleDenominator(), is(max));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testScaleDenominators_maxLessMin() throws Exception {
		double min = 5;
		double max = 1;
		new ManagerConfiguration(mockPropertiesLoader(min, max));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testScaleDenominators_maxNegative() throws Exception {
		double min = 5;
		double max = -1;
		new ManagerConfiguration(mockPropertiesLoader(min, max));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testScaleDenominators_minNegative() throws Exception {
		double min = -5;
		double max = 1;
		new ManagerConfiguration(mockPropertiesLoader(min, max));
	}

	private PropertiesLoader mockPropertiesLoader() throws ConfigurationException {
		return mockPropertiesLoader(Double.NaN, Double.NaN);
	}

	private PropertiesLoader mockPropertiesLoader(double minScaleDenominator, double maxScaleDenominator)
			throws ConfigurationException {
		PropertiesLoader propertiesLoader = mock(PropertiesLoader.class);
		Properties properties = new Properties();
		properties.put(RASTER_CONFIG_CRS, "epsg:4326");
		properties.put("wmsSortDate_BP_Plan_XPLAN_50", "BP_Plan,rechtsverordnungsDatum");
		properties.put("linkSemanticConformity_XPLAN_50", "http://link.de/to.pdf");
		properties.put(WORKSPACE_RELOAD_URLS, "url1,url2");
		properties.put(WORKSPACE_RELOAD_USER, "user");
		properties.put(WORKSPACE_RELOAD_PASSWORD, "password");
		if (!Double.isNaN(minScaleDenominator))
			properties.put(RASTER_LAYER_SCALE_DENOMINATOR_MIN, Double.toString(minScaleDenominator));
		if (!Double.isNaN(maxScaleDenominator))
			properties.put(RASTER_LAYER_SCALE_DENOMINATOR_MAX, Double.toString(maxScaleDenominator));
		when(propertiesLoader.loadProperties(anyString())).thenReturn(properties);
		return propertiesLoader;
	}

}
