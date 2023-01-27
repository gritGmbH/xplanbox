package de.latlon.xplanbox.api.validator.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mockito;

import de.latlon.xplan.validator.configuration.ValidatorConfiguration;

public class ApplicationContextTest {

	@ClassRule
	public final static TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void rulesPathShouldGetPathFromJarIfNotConfigured() throws Exception {
		ValidatorConfiguration validatorConfig = Mockito.mock(ValidatorConfiguration.class);
		Path rulesPath = new ApplicationContext().rulesPath(validatorConfig);

		Path path = rulesPath.resolve("xplangml54/2.1.2.1.xq");

		String content = Files.readString(path, StandardCharsets.UTF_8);
		assertTrue(content.contains("namespace 'http://www.xplanung.de/xplangml/5/4'"));
	}

	@Test
	public void rulesPathShouldUseConfiguredPath() throws Exception {
		Path tmpDir = tempFolder.newFolder().toPath();
		Path p = tmpDir.resolve("xplangml54/2.1.2.1.xq");
		Files.createDirectories(p.getParent());
		Files.writeString(p, "hello world", StandardCharsets.UTF_8);

		ValidatorConfiguration validatorConfig = Mockito.mock(ValidatorConfiguration.class);
		Mockito.when(validatorConfig.getValidationRulesDirectory()).thenReturn(tmpDir);

		Path rulesPath = new ApplicationContext().rulesPath(validatorConfig);
		Path path = rulesPath.resolve("xplangml54/2.1.2.1.xq");

		String content = Files.readString(path, StandardCharsets.UTF_8);
		assertEquals("hello world", content);
	}

}
