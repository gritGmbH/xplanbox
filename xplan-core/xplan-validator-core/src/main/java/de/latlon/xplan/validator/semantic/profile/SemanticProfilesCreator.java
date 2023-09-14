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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.configuration.ValidatorProfile;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesProfileConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.1
 */
public class SemanticProfilesCreator {

	private static final Logger LOG = LoggerFactory.getLogger(SemanticProfilesCreator.class);

	private final ValidatorConfiguration validatorConfiguration;

	private final PropertiesLoader validatorPropertiesLoader;

	private final ResourceLoader resourceLoader;

	public SemanticProfilesCreator(ValidatorConfiguration validatorConfiguration,
			PropertiesLoader validatorPropertiesLoader, ResourceLoader resourceLoader) {
		this.validatorConfiguration = validatorConfiguration;
		this.validatorPropertiesLoader = validatorPropertiesLoader;
		this.resourceLoader = resourceLoader;
	}

	/**
	 * @param activatedProfiles list of activated profiles, may be <code>empty</code>, but
	 * never <code>null</code>
	 * @return the SemanticProfiles containing metadata and validators, never
	 * <code>null</code>
	 * @throws ValidatorException
	 */
	public SemanticProfiles createSemanticProfiles(List<String> activatedProfiles) throws ConfigurationException {
		SemanticProfiles semanticProfiles = new SemanticProfiles();
		addSemanticProfilesFromConfigDirectory(semanticProfiles);
		addSemanticProfilesFromClasspath(semanticProfiles, activatedProfiles);
		return semanticProfiles;
	}

	private void addSemanticProfilesFromClasspath(SemanticProfiles semanticProfiles, List<String> activatedProfiles)
			throws ConfigurationException {
		try {
			Resource[] resources = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
				.getResources("classpath*:/profiles-*.y*ml");
			for (int i = 0; i < resources.length; i++) {
				Resource resource = resources[i];
				LOG.info("Found profile resource: {}", resource);
				List<ValidatorProfile> validatorProfiles = parseProfiles(resource);
				addSemanticProfileFromResource(semanticProfiles, activatedProfiles, validatorProfiles);
			}
		}
		catch (IOException e) {
			throw new ConfigurationException(e);
		}
	}

	private void addSemanticProfileFromResource(SemanticProfiles semanticProfiles, List<String> activatedProfiles,
			List<ValidatorProfile> validatorProfiles) throws ConfigurationException {
		for (ValidatorProfile validatorProfile : validatorProfiles) {
			String profileId = validatorProfile.getId();
			if (activatedProfiles.contains(profileId)) {
				addSemanticProfileFromResource(validatorProfile, semanticProfiles);
			}
			else {
				LOG.info("Profile with id {} is available but not activated.", validatorProfile.getId());
			}
		}
	}

	private void addSemanticProfileFromResource(ValidatorProfile validatorProfile, SemanticProfiles semanticProfiles)
			throws ConfigurationException {
		String profileId = validatorProfile.getId();
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesProfileConfiguration(validatorProfile);
		DelegatingSemanticProfileValidator semanticProfileValidator = createDelegatingSemanticProfileValidator(
				profileId, semanticRulesConfiguration);
		LOG.info("Add profile with id {}.", profileId);
		semanticProfiles.add(semanticRulesConfiguration.getRulesMetadata(), semanticProfileValidator);
	}

	private List<ValidatorProfile> parseProfiles(Resource resource) throws IOException {
		try (InputStream configFile = resource.getInputStream()) {
			ObjectMapper om = new ObjectMapper(new YAMLFactory());
			CollectionType javaType = om.getTypeFactory().constructCollectionType(List.class, ValidatorProfile.class);
			return om.readValue(configFile, javaType);
		}
	}

	private void addSemanticProfilesFromConfigDirectory(SemanticProfiles semanticProfiles)
			throws ConfigurationException {
		for (ValidatorProfile validatorProfile : validatorConfiguration.getValidatorProfiles()) {
			String profileId = validatorProfile.getId();
			Path rulesDirectory = validatorPropertiesLoader.resolveDirectory("profiles").resolve(profileId);
			SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesProfileConfiguration(
					validatorProfile, rulesDirectory);
			DelegatingSemanticProfileValidator semanticProfileValidator = createDelegatingSemanticProfileValidator(
					profileId, semanticRulesConfiguration);
			LOG.info("Add profile with id {} and rules from {}.", profileId, rulesDirectory);
			semanticProfiles.add(semanticRulesConfiguration.getRulesMetadata(), semanticProfileValidator);
		}
	}

	private DelegatingSemanticProfileValidator createDelegatingSemanticProfileValidator(String profileId,
			SemanticRulesConfiguration semanticRulesConfiguration) throws ConfigurationException {
		XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration);
		XQuerySemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(
				xQuerySemanticValidatorConfigurationRetriever);
		return new DelegatingSemanticProfileValidator(profileId, xQuerySemanticValidator);
	}

}
