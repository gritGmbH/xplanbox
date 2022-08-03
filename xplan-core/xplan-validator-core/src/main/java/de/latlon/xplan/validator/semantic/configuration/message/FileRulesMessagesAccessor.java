/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.validator.semantic.configuration.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Allows access to messages assigned to a specific rule from a file.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public final class FileRulesMessagesAccessor extends RulesMessagesAccessor {

	private static final Logger LOG = LoggerFactory.getLogger(FileRulesMessagesAccessor.class);

	private final Properties properties = new Properties();

	/**
	 * @param rulesMessagesFile may be empty (results in a default message)
	 */
	public FileRulesMessagesAccessor(String rulesMessagesFile) {
		if (rulesMessagesFile == null) {
			return;
		}
		Path path = Paths.get(rulesMessagesFile);
		if (!Files.exists(path) || !Files.isRegularFile(path)) {
			LOG.warn("Configured rules messages file {} does not exist or is not a file.", rulesMessagesFile);
		}
		try (InputStream propsStream = Files.newInputStream(path)) {
			properties.load(propsStream);
		}
		catch (IOException | NullPointerException e) {
			LOG.error("Could not load properties file " + "" + rulesMessagesFile, e);
		}
	}

	@Override
	protected Properties getProperties() {
		return properties;
	}

}
