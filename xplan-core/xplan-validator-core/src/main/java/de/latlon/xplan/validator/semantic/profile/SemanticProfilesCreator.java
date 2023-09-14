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
import de.latlon.xplan.validator.semantic.configuration.message.FileRulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersion;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersionParser;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
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
				addSemanticProfileFromResource(semanticProfiles, activatedProfiles, validatorProfiles, resource);
			}
		}
		catch (IOException e) {
			throw new ConfigurationException(e);
		}
	}

	private void addSemanticProfileFromResource(SemanticProfiles semanticProfiles, List<String> activatedProfiles,
			List<ValidatorProfile> validatorProfiles, Resource resource) throws IOException, ConfigurationException {
		for (ValidatorProfile validatorProfile : validatorProfiles) {
			if (activatedProfiles.contains(validatorProfile.getId())) {
				addSemanticProfileFromResource(semanticProfiles, resource, validatorProfile);
			}
			else {
				LOG.info("Profile with id {} is available but not activated.", validatorProfile.getId());
			}
		}
	}

	private void addSemanticProfileFromResource(SemanticProfiles semanticProfiles, Resource resource,
			ValidatorProfile validatorProfile) throws IOException, ConfigurationException {
		URL uri = resource.getURL();
		if (resource.isFile()) {
			Path path = Path.of(resource.getFile().getPath()).getParent();
			Path profileRulesDirectory = path.resolve(validatorProfile.getId());
			addSemanticProfile(semanticProfiles, validatorProfile, profileRulesDirectory);
		}
		else if ("jar".equals(uri.getProtocol())) {
			String jarPath = uri.getFile().replaceFirst("file:(.*)!.*", "$1");
			if (jarPath != null) {
				FileSystem zipfs = FileSystems.newFileSystem(Path.of(jarPath), getClass().getClassLoader());
				Path profileRulesDirectory = zipfs.getPath(validatorProfile.getId());
				addSemanticProfile(semanticProfiles, validatorProfile, profileRulesDirectory);
			}
		}
		else {
			LOG.warn("Cannot handle profile with id {} from {}", validatorProfile.getId(), uri);
		}
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
			Path rulesDirectory = validatorPropertiesLoader.resolveDirectory("profiles")
				.resolve(validatorProfile.getId());
			addSemanticProfile(semanticProfiles, validatorProfile, rulesDirectory);
		}
	}

	private void addSemanticProfile(SemanticProfiles semanticProfiles, ValidatorProfile validatorProfile,
			Path rulesDirectory) throws ConfigurationException {
		RulesMetadata rulesMetadata = createRulesMetadata(validatorProfile, rulesDirectory);
		DelegatingSemanticProfileValidator semanticProfileValidator = createDelegatingSemanticProfileValidator(
				rulesDirectory, rulesMetadata);
		LOG.info("Add profile with id {} and rules from {}.", rulesMetadata.getId(), rulesDirectory);
		semanticProfiles.add(rulesMetadata, semanticProfileValidator);
	}

	private RulesMetadata createRulesMetadata(ValidatorProfile validatorProfile, Path rulesDirectory) {
		RulesVersionParser rulesVersionParser = new RulesVersionParser();
		RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion(rulesDirectory);
		return new RulesMetadata(validatorProfile.getId(), validatorProfile.getName(),
				validatorProfile.getDescription(), rulesVersion.getVersion(), rulesVersion.getSource());
	}

	private DelegatingSemanticProfileValidator createDelegatingSemanticProfileValidator(Path rulesDirectory,
			RulesMetadata rulesMetadata) throws ConfigurationException {
		FileRulesMessagesAccessor messagesAccessor = new FileRulesMessagesAccessor(rulesDirectory);
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(rulesDirectory,
				rulesMetadata, messagesAccessor);
		XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration);
		XQuerySemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(
				xQuerySemanticValidatorConfigurationRetriever);
		return new DelegatingSemanticProfileValidator(rulesMetadata.getId(), xQuerySemanticValidator);
	}

}
