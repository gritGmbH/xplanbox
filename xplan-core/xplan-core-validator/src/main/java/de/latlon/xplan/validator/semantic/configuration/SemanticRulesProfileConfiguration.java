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
package de.latlon.xplan.validator.semantic.configuration;

import de.latlon.xplan.validator.configuration.ValidatorProfile;
import de.latlon.xplan.validator.semantic.configuration.message.FileRulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.message.RulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersion;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersionParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;

import static de.latlon.xplan.validator.semantic.configuration.message.FileRulesMessagesAccessor.RULESMESSAGE_FILE_NAME;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0.1
 */
public class SemanticRulesProfileConfiguration extends SemanticRulesConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(SemanticRulesConfiguration.class);

	private static final String RULES_FILE_NAME = "/xqueryregeln-%s.txt";

	private final ValidatorProfile validatorProfile;

	/**
	 * Rules of the profiles will be parsed from /xqueryregeln-<profileId>.txt.
	 * @param validatorProfile the configuration of the profile, never <code>null</code>
	 */
	public SemanticRulesProfileConfiguration(ValidatorProfile validatorProfile) {
		super();
		this.validatorProfile = validatorProfile;
	}

	/**
	 * Rules of the profiles will be parsed from the passed directory.
	 * @param validatorProfile the configuration of the profile, never <code>null</code>
	 * @param rulesPath the directory containing the rules, never <code>null</code>
	 */
	public SemanticRulesProfileConfiguration(ValidatorProfile validatorProfile, Path rulesPath) {
		super(rulesPath);
		this.validatorProfile = validatorProfile;
	}

	@Override
	protected RulesMessagesAccessor createMessagesAccessor() {
		Optional<Path> rulesPath = getRulesPath();
		if (rulesPath.isPresent()) {
			return new FileRulesMessagesAccessor(rulesPath.get());
		}
		Optional<String> rulesMessageFile = getResource(RULESMESSAGE_FILE_NAME);
		if (rulesMessageFile.isPresent()) {
			try (InputStream resourceAsStream = getClass().getResourceAsStream(rulesMessageFile.get())) {
				return new FileRulesMessagesAccessor(resourceAsStream);
			}
			catch (IOException e) {
				LOG.warn("Could not read rules message file from {}", rulesMessageFile);
				LOG.debug("Could not read rules message file", e);
			}
		}
		return super.createMessagesAccessor();
	}

	@Override
	protected RulesMetadata createRulesMetadata() {
		RulesVersionParser rulesVersionParser = new RulesVersionParser(this);
		RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion();
		return new RulesMetadata(validatorProfile.getId(), validatorProfile.getName(),
				validatorProfile.getDescription(), rulesVersion);
	}

	@Override
	protected InputStream getResourcesFile() {
		return getClass().getResourceAsStream(String.format(RULES_FILE_NAME, validatorProfile.getId()));
	}

}
