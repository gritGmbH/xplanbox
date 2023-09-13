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

import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RulesVersionParser {

	private static final Logger LOG = LoggerFactory.getLogger(RulesVersionParser.class);

	private static final String VERSION_PROPERTIES = "version.properties";

	private static final String VERSION = "version";

	private static final String SOURCE = "source";

	private final SemanticRulesConfiguration semanticRulesConfiguration;

	public RulesVersionParser() {
		this.semanticRulesConfiguration = null;
	}

	public RulesVersionParser(SemanticRulesConfiguration semanticRulesConfiguration) {
		this.semanticRulesConfiguration = semanticRulesConfiguration;
	}

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
		Path metadataFile = rulesPath.resolve(VERSION_PROPERTIES);
		if (!Files.exists(metadataFile)) {
			LOG.info("No file {} in {} available, metadata are unknown", VERSION_PROPERTIES, rulesPath);
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
			LOG.warn("{} in {} could not be read: {}", VERSION_PROPERTIES, rulesPath, e.getMessage());
			return new RulesVersion();
		}
	}

	/**
	 * Parses the rule metadata from the VERSION.properties file in the rulesPath if
	 * available
	 * @return never <code>null</code>, if the rulesPath is null or does not exist or the
	 * version.properties file could not be parsed an empty RulesVersion is returned
	 */
	public RulesVersion parserRulesVersion() {
		Optional<Path> rulesPath = semanticRulesConfiguration.getRulesPath();
		if (rulesPath.isPresent()) {
			Path metadataFile = rulesPath.get().resolve(VERSION_PROPERTIES);
			if (!Files.exists(metadataFile)) {
				LOG.info("No file {} in {} available, metadata are unknown", VERSION_PROPERTIES, rulesPath);
				return new RulesVersion();
			}
			try (InputStream props = Files.newInputStream(metadataFile)) {
				return parseRulesVersion(props);
			}
			catch (IOException e) {
				LOG.warn("{} in {} could not be read: {}", VERSION_PROPERTIES, rulesPath, e.getMessage());
			}
		}
		Optional<String> resource = semanticRulesConfiguration.getResource(VERSION_PROPERTIES);
		if (resource.isPresent()) {
			try (InputStream resourceAsStream = getClass().getResourceAsStream(resource.get())) {
				return parseRulesVersion(resourceAsStream);
			}
			catch (IOException e) {
				LOG.warn("{} from resource {} could not be read: {}", VERSION_PROPERTIES, resource.get(),
						e.getMessage());
			}
		}
		LOG.info("versions.properties are not available, metadata are unknown");
		return new RulesVersion();

	}

	private static RulesVersion parseRulesVersion(InputStream props) throws IOException {
		Properties properties = new Properties();
		properties.load(props);
		String version = properties.getProperty(VERSION);
		String source = properties.getProperty(SOURCE);
		return new RulesVersion(version, source);
	}

}
