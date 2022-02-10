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
package de.latlon.xplan.manager.configuration;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SemanticConformityLinkConfiguration;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.workspace.WorkspaceReloaderConfiguration;
import org.deegree.geometry.Envelope;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_50;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.ACTIVATE_EXPORT_OF_REEXPORTED;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.CATEGORIES_TO_PARTS_KEY;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.DEFAULT_BBOX_IN_4326;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.RASTER_CONFIG_CRS;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.RASTER_LAYER_SCALE_DENOMINATOR_MAX;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.RASTER_LAYER_SCALE_DENOMINATOR_MIN;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.WORKSPACE_RELOAD_PASSWORD;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.WORKSPACE_RELOAD_URLS;
import static de.latlon.xplan.manager.configuration.ManagerConfiguration.WORKSPACE_RELOAD_USER;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerConfigurationTest {

	@Test
	public void testGetCategoryMapping() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoader());

		Map<String, List<String>> categoryMapping = managerConfiguration.getCategoryMapping();
		List<String> cat1Parts = categoryMapping.get("Cat1");
		List<String> cat2Parts = categoryMapping.get("Cat2");

		assertThat(categoryMapping.size(), is(2));

		assertThat(cat1Parts.size(), is(3));
		assertThat(cat2Parts.size(), is(2));

		assertThat(cat1Parts, hasItem("A"));
		assertThat(cat1Parts, hasItem("B"));
		assertThat(cat1Parts, hasItem("C D"));

		assertThat(cat2Parts, hasItem("1"));
		assertThat(cat2Parts, hasItem("7"));
	}

	@Test
	public void testGetRasterConfigurationCrs() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoader());

		String rasterConfigurationCrs = managerConfiguration.getRasterConfigurationCrs();

		assertThat(rasterConfigurationCrs, is("epsg:4326"));
	}

	@Test
	public void testGetCategoryMappingWithLoaderReturningNull() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoaderReturningNull());

		Map<String, List<String>> categoryMapping = managerConfiguration.getCategoryMapping();
		assertThat(categoryMapping.size(), is(0));
	}

	@Test
	public void testGetDefaultBboxIn4326() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoader());

		Envelope defaultBboxIn4326 = managerConfiguration.getDefaultBboxIn4326();

		assertThat(defaultBboxIn4326.getMin().get0(), is(-180.0));
		assertThat(defaultBboxIn4326.getMin().get1(), is(-90.0));
		assertThat(defaultBboxIn4326.getMax().get0(), is(180.0));
		assertThat(defaultBboxIn4326.getMax().get1(), is(90.0));
	}

	@Test
	public void testGetDefaultBboxIn4326WithNullValue() throws Exception {
		ManagerConfiguration managerConfiguration = new ManagerConfiguration(mockPropertiesLoaderReturningNull());

		Envelope defaultBboxIn4326 = managerConfiguration.getDefaultBboxIn4326();

		assertThat(defaultBboxIn4326, nullValue());
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
		assertThat(semanticConformityLinkConfiguration.retrieveLink(XPLAN_3), nullValue());
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
	public void testIsExportOfReexportedActive() throws Exception {
		ManagerConfiguration managerConfigurationWithTrue = new ManagerConfiguration(mockPropertiesLoader("true"));
		assertThat(managerConfigurationWithTrue.isExportOfReexportedActive(), is(true));

		ManagerConfiguration managerConfigurationWithFalse = new ManagerConfiguration(mockPropertiesLoader("false"));
		assertThat(managerConfigurationWithFalse.isExportOfReexportedActive(), is(false));

		ManagerConfiguration managerConfigurationWithUnknown = new ManagerConfiguration(
				mockPropertiesLoader("unknown"));
		assertThat(managerConfigurationWithUnknown.isExportOfReexportedActive(), is(false));

		ManagerConfiguration managerConfigurationWithEmpty = new ManagerConfiguration(mockPropertiesLoader(""));
		assertThat(managerConfigurationWithEmpty.isExportOfReexportedActive(), is(false));
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
		return mockPropertiesLoader("true", Double.NaN, Double.NaN);
	}

	private PropertiesLoader mockPropertiesLoader(String isExportOfReexportedActive) throws ConfigurationException {
		return mockPropertiesLoader(isExportOfReexportedActive, Double.NaN, Double.NaN);
	}

	private PropertiesLoader mockPropertiesLoader(double minScaleDenominator, double maxScaleDenominator)
			throws ConfigurationException {
		return mockPropertiesLoader("true", minScaleDenominator, maxScaleDenominator);
	}

	private PropertiesLoader mockPropertiesLoader(String isExportOfReexportedActive, double minScaleDenominator,
			double maxScaleDenominator) throws ConfigurationException {
		PropertiesLoader propertiesLoader = mock(PropertiesLoader.class);
		Properties properties = new Properties();
		properties.put(CATEGORIES_TO_PARTS_KEY, "Cat1(A,B,C D);Cat2(1 , 7)");
		properties.put(RASTER_CONFIG_CRS, "epsg:4326");
		properties.put(DEFAULT_BBOX_IN_4326, "-180,-90,180,90");
		properties.put("wmsSortDate_BP_Plan_XPLAN_50", "BP_Plan,rechtsverordnungsDatum");
		properties.put("linkSemanticConformity_XPLAN_50", "http://link.de/to.pdf");
		properties.put(WORKSPACE_RELOAD_URLS, "url1,url2");
		properties.put(WORKSPACE_RELOAD_USER, "user");
		properties.put(WORKSPACE_RELOAD_PASSWORD, "password");
		properties.put(ACTIVATE_EXPORT_OF_REEXPORTED, isExportOfReexportedActive);
		if (!Double.isNaN(minScaleDenominator))
			properties.put(RASTER_LAYER_SCALE_DENOMINATOR_MIN, Double.toString(minScaleDenominator));
		if (!Double.isNaN(maxScaleDenominator))
			properties.put(RASTER_LAYER_SCALE_DENOMINATOR_MAX, Double.toString(maxScaleDenominator));
		when(propertiesLoader.loadProperties(anyString())).thenReturn(properties);
		return propertiesLoader;
	}

	private PropertiesLoader mockPropertiesLoaderReturningNull() throws ConfigurationException {
		PropertiesLoader propertiesLoader = mock(PropertiesLoader.class);
		when(propertiesLoader.loadProperties(anyString())).thenReturn(null);
		return propertiesLoader;
	}

}
