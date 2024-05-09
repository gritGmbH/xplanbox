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

import de.latlon.xplan.validator.i18n.ValidationMessages;
import de.latlon.xplan.commons.XPlanVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class RulesMessagesAccessor {

	private static final Logger LOG = LoggerFactory.getLogger(RulesMessagesAccessor.class);

	private static final String DEFAULT_MESSAGE = ValidationMessages.getMessage("RulesMessageAccessor_defaultMessage");

	/**
	 * @param rule to retrieve message for, may be <code>null</code> (default message is
	 * returned)
	 * @param version the rule is assigned to, may be <code>null</code>
	 * @return the message for the passed rule and version or for the passed rule if
	 * version is <code>null</code> or not known, a default message if a property with
	 * this name could not be found, never <code>null</code>
	 */
	public String retrieveMessageForRule(String rule, XPlanVersion version) {
		Properties properties = getProperties();
		if (properties == null) {
			return String.format(DEFAULT_MESSAGE, rule);
		}
		if (rule != null) {
			if (version != null) {
				String key = rule + "_" + version;
				String propertyByRuleAndVersion = properties.getProperty(key);
				if (propertyByRuleAndVersion != null)
					return propertyByRuleAndVersion;
			}
			String propertyByRule = properties.getProperty(rule);
			if (propertyByRule != null)
				return propertyByRule;
		}
		LOG.warn("Message for rule {} and version {} is missing in properties file.", rule, version);
		return String.format(DEFAULT_MESSAGE, rule);
	}

	protected abstract Properties getProperties();

}
