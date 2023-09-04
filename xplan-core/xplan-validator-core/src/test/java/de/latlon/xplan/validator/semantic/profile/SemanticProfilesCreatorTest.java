/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.semantic.profile;

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestContext.class })
public class SemanticProfilesCreatorTest {

	@Autowired
	private ResourceLoader resourceLoader;

	@Test
	public void testSemanticProfilesCreator_ProfilesInDependency_allProfileActivated() throws ConfigurationException {
		ValidatorConfiguration validatorConfiguration = mock(ValidatorConfiguration.class);
		PropertiesLoader validatorPropertiesLoader = mock(PropertiesLoader.class);

		SemanticProfilesCreator semanticProfilesCreator = new SemanticProfilesCreator(validatorConfiguration,
				validatorPropertiesLoader, resourceLoader);

		SemanticProfiles semanticProfiles = semanticProfilesCreator
			.createSemanticProfiles(Arrays.asList("test1", "test2", "test3"));
		List<RulesMetadata> profileMetadata = semanticProfiles.getProfileMetadata();
		List<SemanticProfileValidator> profileValidators = semanticProfiles.getProfileValidators();

		assertEquals(3, profileMetadata.size());
		assertEquals(3, profileValidators.size());
	}

	@Test
	public void testSemanticProfilesCreator_ProfilesInDependency_oneProfileActivated() throws ConfigurationException {
		ValidatorConfiguration validatorConfiguration = mock(ValidatorConfiguration.class);
		PropertiesLoader validatorPropertiesLoader = mock(PropertiesLoader.class);

		SemanticProfilesCreator semanticProfilesCreator = new SemanticProfilesCreator(validatorConfiguration,
				validatorPropertiesLoader, resourceLoader);

		SemanticProfiles semanticProfiles = semanticProfilesCreator.createSemanticProfiles(Arrays.asList("test1"));
		List<RulesMetadata> profileMetadata = semanticProfiles.getProfileMetadata();
		List<SemanticProfileValidator> profileValidators = semanticProfiles.getProfileValidators();

		assertEquals(1, profileMetadata.size());
		assertEquals(1, profileValidators.size());
	}

}
