/*-
 * #%L
 * xplan-api-validator - Modul zur Gruppierung der REST-API
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
