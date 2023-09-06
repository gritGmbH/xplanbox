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
package de.latlon.xplan.validator.semantic.configuration.metadata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RulesVersionParser {

	private static final Logger LOG = LoggerFactory.getLogger(RulesVersionParser.class);

	private static final String RELATIVE_PATH_TO_FILE = "version.properties";

	private static final String VERSION = "version";

	private static final String SOURCE = "source";

	/**
	 * Parses the rule metadata from the VERSION.txt file in the rulesPath if available
	 * @param rulesPath Path to rules, may be <code>null</code>
	 * @return never <code>null</code>, if the rulesPath is null or does not exist or the
	 * version.properties file could not be parsed an empty RulesVersion is returned
	 */
	public RulesVersion parserRulesVersion(Path rulesPath) {
		if (rulesPath == null) {
			LOG.info("No rulesPath available, metadata are unknown");
			return new RulesVersion();
		}
		Path metadataFile = rulesPath.resolve(RELATIVE_PATH_TO_FILE);
		if (!Files.exists(metadataFile)) {
			LOG.info("No file {} in {} available, metadata are unknown", RELATIVE_PATH_TO_FILE, rulesPath);
			return new RulesVersion();
		}
		Properties properties = new Properties();
		try (InputStream props = Files.newInputStream(metadataFile)) {
			properties.load(props);
			String version = properties.getProperty(VERSION);
			String source = properties.getProperty(SOURCE);
			return new RulesVersion(version, source);
		}
		catch (IOException e) {
			LOG.warn("{} in {} could not be read: {}", RELATIVE_PATH_TO_FILE, rulesPath, e.getMessage());
			return new RulesVersion();
		}
	}

}
