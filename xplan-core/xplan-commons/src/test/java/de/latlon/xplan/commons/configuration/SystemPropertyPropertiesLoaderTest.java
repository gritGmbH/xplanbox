package de.latlon.xplan.commons.configuration;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import uk.org.webcompere.systemstubs.rules.SystemPropertiesRule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader.CONFIG_SYSTEM_PROPERTY;
import static de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader.DEFAULT_SUB_DIIRECTOY;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SystemPropertyPropertiesLoaderTest {

	@Rule
	public TemporaryFolder tmp = new TemporaryFolder();

	@Rule
	public SystemPropertiesRule systemProperties = new SystemPropertiesRule();

	@Test
	public void test_withProperty_XPLANBOX_COFIG() throws IOException {
		File xplanBoxConfig = tmp.newFolder("xplanbox");
		systemProperties.set(CONFIG_SYSTEM_PROPERTY, xplanBoxConfig.toString());

		SystemPropertyPropertiesLoader systemPropertyPropertiesLoader = new SystemPropertyPropertiesLoader();
		Path configDirectory = systemPropertyPropertiesLoader.getConfigDirectory();

		assertEquals(Paths.get(xplanBoxConfig.toURI()), configDirectory);
	}

	@Test
	public void test_userhome() throws IOException {
		File userHome = tmp.newFolder("userhome");
		Path xplanBoxDiectory = Paths.get(userHome.toURI()).resolve(DEFAULT_SUB_DIIRECTOY);
		Files.createDirectory(xplanBoxDiectory);
		systemProperties.set("user.home", userHome.toString());

		SystemPropertyPropertiesLoader systemPropertyPropertiesLoader = new SystemPropertyPropertiesLoader();
		Path configDirectory = systemPropertyPropertiesLoader.getConfigDirectory();

		assertEquals(xplanBoxDiectory, configDirectory);
	}

}
