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
package de.latlon.xplan.validator.semantic.configuration.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Allows access to default messages assigned to a specific rule.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public final class DefaultRulesMessagesAccessor extends RulesMessagesAccessor {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultRulesMessagesAccessor.class);

	private static final String RULES_MESSAGES_PROPERTIES = "/de/latlon/xplan/validator/semantic/configuration/rulesMessages.properties";

	private static final Properties PROPERTIES = new Properties();

	static {
		try (InputStream propsStream = DefaultRulesMessagesAccessor.class
			.getResourceAsStream(RULES_MESSAGES_PROPERTIES)) {
			PROPERTIES.load(propsStream);
		}
		catch (IOException | NullPointerException e) {
			LOG.error("Could not load properties file " + "" + RULES_MESSAGES_PROPERTIES, e);
		}
	}

	@Override
	protected Properties getProperties() {
		return PROPERTIES;
	}

}
