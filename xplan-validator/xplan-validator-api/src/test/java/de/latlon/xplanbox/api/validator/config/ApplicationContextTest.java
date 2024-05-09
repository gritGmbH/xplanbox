/*-
 * #%L
 * xplan-validator-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.validator.config;

import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicationContextTest {

	@TempDir
	public static Path tempFolder;

	@Test
	void rulesPathShouldGetPathFromJarIfNotConfigured() throws Exception {
		ValidatorConfiguration validatorConfig = Mockito.mock(ValidatorConfiguration.class);
		SemanticRulesConfiguration semanticRulesConfiguration = new ApplicationContext()
			.semanticRulesConfiguration(validatorConfig);
		Optional<String> resource = semanticRulesConfiguration.getResource("xplangml54/2.1.2.1.xq");
		assertTrue(resource.isPresent());

		InputStream resourceAsStream = getClass().getResourceAsStream(resource.get());
		String content = IOUtils.toString(resourceAsStream, StandardCharsets.UTF_8);
		assertTrue(content.contains("namespace 'http://www.xplanung.de/xplangml/5/4'"));
	}

	@Test
	void rulesPathShouldUseConfiguredPath() throws Exception {
		Path tmpDir = tempFolder.resolve("junit");
		Path p = tmpDir.resolve("xplangml54/2.1.2.1.xq");
		Files.createDirectories(p.getParent());
		Files.writeString(p, "hello world", StandardCharsets.UTF_8);

		ValidatorConfiguration validatorConfig = Mockito.mock(ValidatorConfiguration.class);
		Mockito.when(validatorConfig.getValidationRulesDirectory()).thenReturn(tmpDir);

		Optional<Path> rulesPath = new ApplicationContext().semanticRulesConfiguration(validatorConfig).getRulesPath();
		assertTrue(rulesPath.isPresent());

		Path path = rulesPath.get().resolve("xplangml54/2.1.2.1.xq");

		String content = Files.readString(path, StandardCharsets.UTF_8);
		assertEquals("hello world", content);
	}

}
