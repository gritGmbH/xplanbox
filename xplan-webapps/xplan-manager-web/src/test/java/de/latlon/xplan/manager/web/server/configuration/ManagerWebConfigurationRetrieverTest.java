/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.server.configuration;

import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;
import de.latlon.xplan.manager.web.shared.MapPreviewConfiguration;
import de.latlon.xplan.manager.web.shared.RasterLayerConfiguration;
import de.latlon.xplan.manager.web.shared.VectorLayerConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader.CONFIG_SYSTEM_PROPERTY;
import static java.io.File.createTempFile;
import static org.apache.commons.io.IOUtils.copy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Please note: This test uses static mocking of the <link>java.lang.System</link>-class,
 * so the behaviour of this class changes in all tested implementation code!
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 */
public class ManagerWebConfigurationRetrieverTest {

	private static final String PROPERTIES_NAME = "managerWebConfiguration.properties";

	private static String oldProperty;

	@BeforeClass
	public static void copyPropertiesFileAndSetProxyConfigSystemVaraiable() throws IOException {
		File configDir = copyPropertiesFileToNewConfigDir();
		oldProperty = System.getProperty(CONFIG_SYSTEM_PROPERTY);
		System.setProperty(CONFIG_SYSTEM_PROPERTY, configDir.toString());
	}

	@AfterClass
	public static void resetProxyConfigSystemProperty() {
		if (oldProperty != null)
			System.setProperty(CONFIG_SYSTEM_PROPERTY, oldProperty);
	}

	@Test
	public void testSetupManagerWebConfigurationShouldReturnMatchingPropertiesFromSystemEnv() throws Exception {
		ManagerWebConfiguration configuration = new ManagerWebConfigurationRetriever().setupManagerWebConfiguration();

		Properties properties = loadPropertiesFromOriginalFile();
		assertThat(configuration.getCrsDialogDefaultCrs(), is(properties.getProperty("defaultCrs")));
		assertThat(configuration.getInternalIdActivated(), is(false));
	}

	@Test
	public void testSetupMapPreviewConfigurationShouldReturnMatchingPropertiesFromSystemEnv() throws Exception {
		MapPreviewConfiguration configuration = new ManagerWebConfigurationRetriever().setupMapPreviewConfiguration();

		Properties properties = loadPropertiesFromOriginalFile();
		assertThat(configuration.getBasemapUrl(), is(properties.getProperty("basemapUrl")));
		assertThat(configuration.getBasemapName(), is(properties.getProperty("basemapName")));
		assertThat(configuration.getBasemapLayer(), is(properties.getProperty("basemapLayer")));
		assertThat(configuration.getWmsUrl(), is(properties.getProperty("wmsUrl")));
	}

	@Test
	public void testSetupVectorLayerConfigurationShouldReturnMatchingPropertiesFromSystemEnv() throws Exception {
		VectorLayerConfiguration configuration = new ManagerWebConfigurationRetriever().setupMapPreviewConfiguration()
			.getVectorLayerConfiguration();

		Properties properties = loadPropertiesFromOriginalFile();

		assertThat(configuration.getVectorWmsName(), is(properties.getProperty("vectorWmsName")));
		assertThat(configuration.getBpVectorLayer(), is(properties.getProperty("bpVectorLayer")));
		assertThat(configuration.getFpVectorLayer(), is(properties.getProperty("fpVectorLayer")));
		assertThat(configuration.getLpVectorLayer(), is(properties.getProperty("lpVectorLayer")));
		assertThat(configuration.getRpVectorLayer(), is(properties.getProperty("rpVectorLayer")));
	}

	@Test
	public void testSetupRasterLayerConfigurationShouldReturnMatchingPropertiesFromSystemEnv() throws Exception {
		RasterLayerConfiguration configuration = new ManagerWebConfigurationRetriever().setupMapPreviewConfiguration()
			.getRasterLayerConfiguration();

		Properties properties = loadPropertiesFromOriginalFile();

		assertThat(configuration.getRasterWmsName(), is(properties.getProperty("rasterWmsName")));
		assertThat(configuration.getBpRasterLayer(), is(properties.getProperty("bpRasterLayer")));
		assertThat(configuration.getFpRasterLayer(), is(properties.getProperty("fpRasterLayer")));
		assertThat(configuration.getLpRasterLayer(), is(properties.getProperty("lpRasterLayer")));
		assertThat(configuration.getRpRasterLayer(), is(properties.getProperty("rpRasterLayer")));
	}

	private static File copyPropertiesFileToNewConfigDir() throws IOException {
		File configDirectory = createConfigDirectory();
		File configPropertiesFile = new File(configDirectory, PROPERTIES_NAME);
		FileOutputStream fileOutputStream = new FileOutputStream(configPropertiesFile);

		InputStream testConfigProperties = ManagerWebConfigurationRetrieverTest.class
			.getResourceAsStream(PROPERTIES_NAME);
		copy(testConfigProperties, fileOutputStream);

		testConfigProperties.close();
		fileOutputStream.close();

		return configDirectory;
	}

	private static File createConfigDirectory() throws IOException {
		File configDirectory = createTempFile("xplanungisk-config", "");
		configDirectory.delete();
		configDirectory.mkdir();
		return configDirectory;
	}

	private Properties loadPropertiesFromOriginalFile() throws IOException {
		InputStream resource = ManagerWebConfigurationRetrieverTest.class.getResourceAsStream(PROPERTIES_NAME);
		Properties properties = new Properties();
		properties.load(resource);
		return properties;
	}

}
