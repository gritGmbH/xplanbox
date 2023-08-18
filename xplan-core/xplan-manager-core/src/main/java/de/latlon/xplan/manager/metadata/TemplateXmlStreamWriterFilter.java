/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.metadata;

import org.apache.axiom.om.util.XMLStreamWriterFilterBase;

import java.util.Properties;

/**
 * Replaces all occurrences of properties (key pattern: ${PROPERTY_NAME}). If a key is
 * detected but no property value available the key is used as value.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TemplateXmlStreamWriterFilter extends XMLStreamWriterFilterBase {

	private final Properties properties;

	public TemplateXmlStreamWriterFilter(Properties properties) {
		this.properties = properties;
	}

	@Override
	protected String xmlData(String s) {
		if (containsProperty(s)) {
			String key = s.substring(s.indexOf("${") + 2, s.indexOf("}"));
			String propertyValue = properties.getProperty(key, key);
			return s.replace("${" + key + "}", propertyValue);
		}
		return s;
	}

	private boolean containsProperty(String s) {
		return s.matches(".*\\$\\{.*\\}.*");
	}

}
