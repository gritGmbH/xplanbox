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
package de.latlon.xplan.validator.semantic.configuration.xquery;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.configuration.message.DefaultRulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.message.RulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidatorRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.NONE;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.nio.file.Files.isDirectory;
import static java.nio.file.Files.newDirectoryStream;
import static java.nio.file.Files.newInputStream;

/**
 * Retrieves XQuery configurations from file system
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorConfigurationRetriever implements SemanticValidatorConfigurationRetriever {

	private static final Logger LOG = LoggerFactory.getLogger(XQuerySemanticValidatorConfigurationRetriever.class);

	private static final XPlanVersion UNKNOWN_VERSION = null;

	private static final SemanticValidationOptions UNKNOWN_OPTION = NONE;

	private final Path rulesPath;

	private final RulesMetadata rulesMetadata;

	private final RulesMessagesAccessor rulesMessagesAccessor;

	public XQuerySemanticValidatorConfigurationRetriever(Path rulesPath, RulesMetadata rulesMetadata) {
		this.rulesPath = rulesPath;
		this.rulesMetadata = rulesMetadata;
		this.rulesMessagesAccessor = new DefaultRulesMessagesAccessor();
	}

	public XQuerySemanticValidatorConfigurationRetriever(Path rulesPath, RulesMetadata rulesMetadata,
			RulesMessagesAccessor rulesMessagesAccessor) {
		this.rulesPath = rulesPath;
		this.rulesMetadata = rulesMetadata;
		this.rulesMessagesAccessor = rulesMessagesAccessor;
	}

	@Override
	public SemanticValidatorConfiguration retrieveConfiguration() throws IOException {
		SemanticValidatorConfiguration config = new SemanticValidatorConfiguration();
		config.setRulesMetadata(rulesMetadata);
		if (rulesPath != null && isDirectory(rulesPath)) {
			try (DirectoryStream<Path> directoryStream = retrieveDirectoriesAndRules(rulesPath)) {
				for (Path path : directoryStream) {
					if (isDirectory(path)) {
						XPlanVersion planVersion = parseXPlanVersion(path);
						SemanticValidationOptions validationOption = parseSemanticValidationOption(path);
						boolean isVersionDirectory = isVersionDirectory(planVersion);
						boolean isIgnoreOptionDirectory = isIgnoreOptionDirectory(validationOption);
						if (isVersionDirectory) {
							collectAllRulesFromVersionDirectory(config, path, planVersion);
						}
						else if (isIgnoreOptionDirectory) {
							collectAllRulesFromDirectory(config, path, UNKNOWN_VERSION, validationOption);
						}
						else {
							collectAllRulesFromDirectory(config, path, UNKNOWN_VERSION, UNKNOWN_OPTION);
						}
					}
					else {
						createAndAddRule(config, path, UNKNOWN_VERSION, UNKNOWN_OPTION);
					}
				}
			}
		}

		return config;
	}

	private void collectAllRulesFromVersionDirectory(SemanticValidatorConfiguration config, Path versionDirectory,
			XPlanVersion planVersion) throws IOException {
		try (DirectoryStream<Path> directoryStream = retrieveDirectoriesAndRules(versionDirectory)) {
			for (Path path : directoryStream) {
				if (isDirectory(path)) {
					SemanticValidationOptions validationOption = parseSemanticValidationOption(path);
					if (isIgnoreOptionDirectory(validationOption)) {
						collectAllRulesFromDirectory(config, path, planVersion, validationOption);
					}
					else {
						collectAllRulesFromDirectory(config, path, planVersion, validationOption);
					}
				}
				else {
					createAndAddRule(config, path, planVersion, UNKNOWN_OPTION);
				}
			}
		}
	}

	private void collectAllRulesFromDirectory(SemanticValidatorConfiguration config, Path validationDirectory,
			XPlanVersion planVersion, SemanticValidationOptions validationOption) throws IOException {
		try (DirectoryStream<Path> directoryStream = retrieveDirectoriesAndRules(validationDirectory)) {
			for (Path path : directoryStream) {
				if (isDirectory(path)) {
					collectAllRulesFromDirectory(config, path, planVersion, validationOption);
				}
				else {
					createAndAddRule(config, path, planVersion, validationOption);
				}
			}
		}
	}

	private void createAndAddRule(SemanticValidatorConfiguration config, Path path, XPlanVersion version,
			SemanticValidationOptions option) {
		LOG.debug("Parse rule {}", path);
		String name = getNameWithoutExtension(path);
		try {
			String message = rulesMessagesAccessor.retrieveMessageForRule(name, version);
			XQuerySemanticValidatorRule rule = new XQuerySemanticValidatorRule(newInputStream(path), name, version,
					option, message);
			config.addRule(rule);
			LOG.debug(format("New rule: %s from file rulesPath %s", name, path.toAbsolutePath().toString()));
		}
		catch (Throwable e) {
			LOG.warn(format("Rule '%s' could not be parsed and will be skipped, reason: %s",
					path.toAbsolutePath().toString(), e.getMessage()), e);
		}
	}

	private static DirectoryStream<Path> retrieveDirectoriesAndRules(Path filesPath) throws IOException {
		return newDirectoryStream(filesPath,
				entry -> isDirectory(entry) || valueOf(entry.getFileName()).endsWith(".xq"));
	}

	private String getNameWithoutExtension(Path path) {
		String name = path.getFileName().toString();
		int indexOfExtensionBegin = name.lastIndexOf(".");
		if (indexOfExtensionBegin > 0) {
			return name.substring(0, indexOfExtensionBegin);
		}
		return name;
	}

	private boolean isVersionDirectory(XPlanVersion planVersion) {
		return planVersion != UNKNOWN_VERSION;
	}

	private boolean isIgnoreOptionDirectory(SemanticValidationOptions validationOption) {
		return !UNKNOWN_OPTION.equals(validationOption);
	}

	private SemanticValidationOptions parseSemanticValidationOption(Path path) {
		String dirName = path.getFileName().toString();
		return SemanticValidationOptions.getByDirectoryName(dirName);
	}

	private XPlanVersion parseXPlanVersion(Path path) {
		String dirName = path.getFileName().toString();
		try {
			return XPlanVersion.valueOfVersionDir(dirName);
		}
		catch (IllegalArgumentException e) {
			LOG.info("{} cannnot be assigned to a known XPlanVersion", dirName);
			return UNKNOWN_VERSION;
		}
	}

}
