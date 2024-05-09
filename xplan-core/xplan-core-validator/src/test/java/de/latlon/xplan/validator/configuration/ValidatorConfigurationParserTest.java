/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.configuration;

import de.latlon.xplan.commons.configuration.DefaultPropertiesLoader;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.isDirectory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link ValidatorConfigurationParser}.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class ValidatorConfigurationParserTest {

	private static final String VALIDATION_REPORT_DIRECTORY_KEY = "validationReportDirectory";

	@ClassRule
	public static TemporaryFolder tempFolder = new TemporaryFolder();

	private static Path valid;

	private static Path invalidName;

	private static Path invalidId;

	private static Path duplicateId;

	private static Path duplicateName;

	@BeforeClass
	public static void copyProfiles() throws IOException {
		valid = tempFolder.newFolder("valid").toPath();
		Path profile = valid.resolve("profile.yaml");
		Path profiles = valid.resolve("profiles.yaml");

		copy("/de/latlon/xplan/validator/configuration/profile.yaml", profile);
		copy("/de/latlon/xplan/validator/configuration/profiles.yaml", profiles);

		invalidName = tempFolder.newFolder("invalidName").toPath();
		Path invalidName = ValidatorConfigurationParserTest.invalidName.resolve("profile-invalidName.yaml");
		copy("/de/latlon/xplan/validator/configuration/profile-invalidName.yaml", invalidName);

		invalidId = tempFolder.newFolder("invalidId").toPath();
		Path invalidPath = ValidatorConfigurationParserTest.invalidId.resolve("profile-invalidId.yaml");
		copy("/de/latlon/xplan/validator/configuration/profile-invalidId.yaml", invalidPath);

		duplicateId = tempFolder.newFolder("duplicateId").toPath();
		Path duplicateIdPath = ValidatorConfigurationParserTest.duplicateId.resolve("profiles-duplicateId.yaml");
		copy("/de/latlon/xplan/validator/configuration/profiles-duplicateId.yaml", duplicateIdPath);

		duplicateName = tempFolder.newFolder("duplicateName").toPath();
		Path duplicateNamePath = ValidatorConfigurationParserTest.duplicateName.resolve("profiles-duplicateName.yaml");
		copy("/de/latlon/xplan/validator/configuration/profiles-duplicateName.yaml", duplicateNamePath);
	}

	private static void copy(String name, Path profiles) throws IOException {
		String contentProfiles = IOUtils.resourceToString(name, UTF_8);
		String paths = contentProfiles.replaceAll("PATH", tempFolder.getRoot().getAbsolutePath());
		Files.write(profiles, paths.getBytes(UTF_8));
	}

	@Test
	public void testParse() throws Exception {
		PropertiesLoader propertiesLoader = mockPropertiesLoader("/home/xplanbox/report/");
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		ValidatorConfiguration validatorConfiguration = validatorConfigurationParser.parse(propertiesLoader);

		Path actualValidationReportDirectory = validatorConfiguration.getValidationReportDirectory();
		Path expectedValidationReportDirectory = Paths.get("/home/xplanbox/report/");

		assertEquals(expectedValidationReportDirectory, actualValidationReportDirectory);
	}

	@Test
	public void testParseWithEmptyValidationReportDirectory() throws Exception {
		PropertiesLoader propertiesLoader = mockPropertiesLoader("");
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		ValidatorConfiguration validatorConfiguration = validatorConfigurationParser.parse(propertiesLoader);

		Path actualValidationReportDirectory = validatorConfiguration.getValidationReportDirectory();

		assertTrue(isDirectory(actualValidationReportDirectory));
	}

	@Test
	public void testParseWithNullValidationReportDirectory() throws Exception {
		PropertiesLoader propertiesLoader = mockPropertiesLoader(null);
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		ValidatorConfiguration validatorConfiguration = validatorConfigurationParser.parse(propertiesLoader);

		Path actualValidationReportDirectory = validatorConfiguration.getValidationReportDirectory();

		assertTrue(isDirectory(actualValidationReportDirectory));
	}

	@Test
	public void testParseFromFile() throws Exception {
		PropertiesLoader propertiesLoader = new DefaultPropertiesLoader(ValidatorConfigurationParser.class);
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		ValidatorConfiguration validatorConfiguration = validatorConfigurationParser.parse(propertiesLoader);

		Path actualValidationReportDirectory = validatorConfiguration.getValidationReportDirectory();
		Path expectedValidationReportDirectory = Paths.get("/home/xplanbox/file/configuration/report/");

		assertEquals(expectedValidationReportDirectory, actualValidationReportDirectory);
	}

	@Test
	public void testParseProfile() throws Exception {
		PropertiesLoader propertiesLoader = mockPropertiesLoaderWithProfile("/home/xplanbox/report/", valid);
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		ValidatorConfiguration validatorConfiguration = validatorConfigurationParser.parse(propertiesLoader);

		assertEquals(3, validatorConfiguration.getValidatorProfiles().size());
	}

	@Test(expected = ConfigurationException.class)
	public void testParseProfile_invalidName() throws Exception {
		PropertiesLoader propertiesLoader = mockPropertiesLoaderWithProfile("/home/xplanbox/report/", invalidName);
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		validatorConfigurationParser.parse(propertiesLoader);
	}

	@Test(expected = ConfigurationException.class)
	public void testParseProfile_invalidId() throws Exception {
		PropertiesLoader propertiesLoader = mockPropertiesLoaderWithProfile("/home/xplanbox/report/", invalidId);
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		validatorConfigurationParser.parse(propertiesLoader);
	}

	@Test(expected = ConfigurationException.class)
	public void testParseProfile_DuplicateId() throws Exception {
		PropertiesLoader propertiesLoader = mockPropertiesLoaderWithProfile("/home/xplanbox/report/", duplicateId);
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		validatorConfigurationParser.parse(propertiesLoader);
	}

	@Test(expected = ConfigurationException.class)
	public void testParseProfile_DuplicateName() throws Exception {
		PropertiesLoader propertiesLoader = mockPropertiesLoaderWithProfile("/home/xplanbox/report/", duplicateName);
		ValidatorConfigurationParser validatorConfigurationParser = new ValidatorConfigurationParser();
		validatorConfigurationParser.parse(propertiesLoader);
	}

	private PropertiesLoader mockPropertiesLoader(String validationReportDirectory) throws ConfigurationException {
		PropertiesLoader propertiesLoader = mock(PropertiesLoader.class);
		Properties properties = new Properties();
		if (validationReportDirectory != null)
			properties.put(VALIDATION_REPORT_DIRECTORY_KEY, validationReportDirectory);
		when(propertiesLoader.loadProperties(anyString())).thenReturn(properties);
		return propertiesLoader;
	}

	private PropertiesLoader mockPropertiesLoaderWithProfile(String validationReportDirectory, Path profileFolder)
			throws ConfigurationException {
		PropertiesLoader propertiesLoader = mockPropertiesLoader(validationReportDirectory);
		when(propertiesLoader.resolveDirectory(eq("profiles"))).thenReturn(profileFolder);
		return propertiesLoader;
	}

}
