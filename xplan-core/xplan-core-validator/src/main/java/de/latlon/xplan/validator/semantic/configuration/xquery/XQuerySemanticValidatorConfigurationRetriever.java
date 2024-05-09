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
package de.latlon.xplan.validator.semantic.configuration.xquery;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidatorRule;
import net.sf.saxon.trans.XPathException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

	private static final String FILE_SUFFIX = ".xq";

	private SemanticRulesConfiguration semanticRulesConfiguration;

	/**
	 * @param semanticRulesConfiguration never <code>null</code>
	 */
	public XQuerySemanticValidatorConfigurationRetriever(SemanticRulesConfiguration semanticRulesConfiguration) {
		this.semanticRulesConfiguration = semanticRulesConfiguration;
	}

	@Override
	public SemanticValidatorConfiguration retrieveConfiguration() throws IOException {
		SemanticValidatorConfiguration config = new SemanticValidatorConfiguration();
		config.setRulesMetadata(semanticRulesConfiguration.getRulesMetadata());
		Optional<Path> rulesPathFromConfig = semanticRulesConfiguration.getRulesPath();
		if (rulesPathFromConfig.isPresent() && isDirectory(rulesPathFromConfig.get())) {
			parseRulesFromPath(rulesPathFromConfig.get(), config);
		}
		else {
			parseInternalRules(config);
		}
		return config;
	}

	private void parseRulesFromPath(Path rulesPath, SemanticValidatorConfiguration config) throws IOException {
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

	private void parseInternalRules(SemanticValidatorConfiguration config) {
		List<String> ruleResources = semanticRulesConfiguration.getResources(FILE_SUFFIX);
		for (String ruleResource : ruleResources) {
			List<String> paths = Arrays.asList(ruleResource.split("/"));
			parseRule(config, ruleResource, paths);
		}
	}

	private void parseRule(SemanticValidatorConfiguration config, String ruleResource, List<String> paths) {
		String fileName = paths.get(paths.size() - 1);
		XPlanVersion version = parseXPlanVersion(paths);
		if (version == null) {
			LOG.warn("Version from rule {} could not be parsed or is not supported, rule will be skipped.",
					ruleResource);
			return;
		}
		try (InputStream resource = getClass().getResourceAsStream(ruleResource)) {
			createAndAddRule(config, fileName, version, UNKNOWN_OPTION, resource);
			LOG.debug(format("New rule: %s from resource %s", fileName, ruleResource));
		}
		catch (Throwable e) {
			LOG.warn(format("Rule '%s' could not be parsed and will be skipped, reason: %s", ruleResource,
					e.getMessage()), e);
		}
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
		try {
			InputStream statementStream = newInputStream(path);
			String fileName = path.getFileName().toString();
			String name = createAndAddRule(config, fileName, version, option, statementStream);
			LOG.debug(format("New rule: %s from file rulesPath %s", name, path.toAbsolutePath()));
		}
		catch (Throwable e) {
			LOG.warn(format("Rule '%s' could not be parsed and will be skipped, reason: %s", path.toAbsolutePath(),
					e.getMessage()), e);
		}
	}

	private String createAndAddRule(SemanticValidatorConfiguration config, String fileName, XPlanVersion version,
			SemanticValidationOptions option, InputStream statementStream) throws IOException, XPathException {
		String name = getNameWithoutExtension(fileName);
		String message = semanticRulesConfiguration.getRulesMessageAccessor().retrieveMessageForRule(name, version);
		XQuerySemanticValidatorRule rule = new XQuerySemanticValidatorRule(statementStream, name, version, option,
				message);
		config.addRule(rule);
		return name;
	}

	private static DirectoryStream<Path> retrieveDirectoriesAndRules(Path filesPath) throws IOException {
		return newDirectoryStream(filesPath,
				entry -> isDirectory(entry) || valueOf(entry.getFileName()).endsWith(FILE_SUFFIX));
	}

	private String getNameWithoutExtension(String fileName) {
		int indexOfExtensionBegin = fileName.lastIndexOf(".");
		if (indexOfExtensionBegin > 0) {
			return fileName.substring(0, indexOfExtensionBegin);
		}
		return fileName;
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

	private XPlanVersion parseXPlanVersion(List<String> paths) {
		for (String path : paths) {
			try {
				return XPlanVersion.valueOfVersionDir(path);
			}
			catch (IllegalArgumentException e) {
				// skip exception, maye next path is version dir?
			}
		}
		return UNKNOWN_VERSION;
	}

}
