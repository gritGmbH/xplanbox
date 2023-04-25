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
package de.latlon.xplan.validator.i18n;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Access to messages for the validation report.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidationMessages {

	private static final String PROPERTIES_FILE = "validationMessages.properties";

	private static final Logger LOG = LoggerFactory.getLogger(ValidationMessages.class);

	private static final Properties MESSAGES;

	static {
		MESSAGES = new Properties();
		try {
			InputStream validationMessages = ValidationMessages.class.getResourceAsStream(PROPERTIES_FILE);
			MESSAGES.load(validationMessages);
		}
		catch (IOException e) {
			LOG.error("Could not load properties file with validation messages!");
		}
	}

	/**
	 * @param key of the message, nvere <code>null</code>
	 * @return the message associated to the key. If a message with the key is not found
	 * the key is returned. Never <code>null</code>
	 */
	public static String getMessage(String key) {
		return MESSAGES.getProperty(key, key);
	}

	/**
	 * @param key of the message, nvere <code>null</code>
	 * @param args the arguments used to format the string
	 * @return the formatted message associated to the key. If a message with the key is
	 * not found * the key is returned. Never <code>null</code>
	 */
	public static String format(String key, Object... args) {
		String message = MESSAGES.getProperty(key, key);
		return String.format(message, args);
	}

}
