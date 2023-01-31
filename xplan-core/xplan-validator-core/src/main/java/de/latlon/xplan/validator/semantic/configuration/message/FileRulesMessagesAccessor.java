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
package de.latlon.xplan.validator.semantic.configuration.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Allows access to messages assigned to a specific rule from a file.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public final class FileRulesMessagesAccessor extends RulesMessagesAccessor {

	private static final Logger LOG = LoggerFactory.getLogger(FileRulesMessagesAccessor.class);

	private static final String RULESMESSAGE_FILE_NAME = "rules.properties";

	private final Properties properties = new Properties();

	/**
	 * @param rulesDirectory the directory containing the rules.properties, never
	 * <code>null</code>. A file with this name may exists. If no file with this name
	 * exists, a default message will be created.
	 */
	public FileRulesMessagesAccessor(Path rulesDirectory) {
		Path rulesMessagesFile = rulesDirectory.resolve(RULESMESSAGE_FILE_NAME);
		if (!Files.exists(rulesMessagesFile) || !Files.isRegularFile(rulesMessagesFile)) {
			LOG.info("Rules messages file {} does not exist or is not a file.", rulesMessagesFile);
			return;
		}
		try (InputStream propsStream = Files.newInputStream(rulesMessagesFile)) {
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
