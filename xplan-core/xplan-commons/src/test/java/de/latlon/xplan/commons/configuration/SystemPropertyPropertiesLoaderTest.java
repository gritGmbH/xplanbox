/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.configuration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.properties.SystemProperties;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader.CONFIG_SYSTEM_PROPERTY;
import static de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader.DEFAULT_SUB_DIIRECTOY;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@ExtendWith(SystemStubsExtension.class)
class SystemPropertyPropertiesLoaderTest {

	@Test
	void test_withProperty_XPLANBOX_COFIG(@TempDir File xplanBoxConfig, SystemProperties systemProperties)
			throws IOException {
		systemProperties.set(CONFIG_SYSTEM_PROPERTY, xplanBoxConfig.toString());

		SystemPropertyPropertiesLoader systemPropertyPropertiesLoader = new SystemPropertyPropertiesLoader();
		Path configDirectory = systemPropertyPropertiesLoader.getConfigDirectory();

		assertEquals(Paths.get(xplanBoxConfig.toURI()), configDirectory);
	}

	@Test
	void test_userhome(@TempDir File userHome, SystemProperties systemProperties) throws IOException {
		Path xplanBoxDiectory = Paths.get(userHome.toURI()).resolve(DEFAULT_SUB_DIIRECTOY);
		Files.createDirectory(xplanBoxDiectory);
		systemProperties.set("user.home", userHome.toString());

		SystemPropertyPropertiesLoader systemPropertyPropertiesLoader = new SystemPropertyPropertiesLoader();
		Path configDirectory = systemPropertyPropertiesLoader.getConfigDirectory();

		assertEquals(xplanBoxDiectory, configDirectory);
	}

}
