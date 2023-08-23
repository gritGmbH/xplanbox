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
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.configuration.ValidatorProfile;
import de.latlon.xplan.validator.semantic.configuration.message.FileRulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersion;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersionParser;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;

import java.nio.file.Path;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SemanticProfilesCreator {

	private final ValidatorConfiguration validatorConfiguration;

	private final PropertiesLoader validatorPropertiesLoader;

	public SemanticProfilesCreator(ValidatorConfiguration validatorConfiguration,
			PropertiesLoader validatorPropertiesLoader) {
		this.validatorConfiguration = validatorConfiguration;
		this.validatorPropertiesLoader = validatorPropertiesLoader;
	}

	/**
	 * @return the SemanticProfiles containing metadata and validators, never
	 * <code>null</code>
	 * @throws ValidatorException
	 */
	public SemanticProfiles createSemanticProfiles() throws ConfigurationException {
		SemanticProfiles semanticProfiles = new SemanticProfiles();
		for (ValidatorProfile validatorProfile : validatorConfiguration.getValidatorProfiles()) {
			String profileId = validatorProfile.getId();
			Path rulesDirectory = validatorPropertiesLoader.resolveDirectory("profiles").resolve(profileId);
			RulesMetadata newRulesMetadata = createRulesMetadata(validatorProfile, rulesDirectory, profileId);
			DelegatingSemanticProfileValidator semanticProfileValidator = createDelegatingSemanticProfileValidator(
					rulesDirectory, newRulesMetadata);
			semanticProfiles.add(newRulesMetadata, semanticProfileValidator);
		}
		return semanticProfiles;
	}

	private RulesMetadata createRulesMetadata(ValidatorProfile validatorProfile, Path rulesDirectory,
			String profileId) {
		RulesVersionParser rulesVersionParser = new RulesVersionParser();
		RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion(rulesDirectory);
		return new RulesMetadata(profileId, validatorProfile.getName(), validatorProfile.getDescription(),
				rulesVersion.getVersion(), rulesVersion.getSource());
	}

	private DelegatingSemanticProfileValidator createDelegatingSemanticProfileValidator(Path rulesDirectory,
			RulesMetadata newRulesMetadata) throws ConfigurationException {
		FileRulesMessagesAccessor messagesAccessor = new FileRulesMessagesAccessor(rulesDirectory);
		XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever = new XQuerySemanticValidatorConfigurationRetriever(
				rulesDirectory, newRulesMetadata, messagesAccessor);
		XQuerySemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(
				xQuerySemanticValidatorConfigurationRetriever);
		return new DelegatingSemanticProfileValidator(newRulesMetadata.getId(), xQuerySemanticValidator);
	}

}
