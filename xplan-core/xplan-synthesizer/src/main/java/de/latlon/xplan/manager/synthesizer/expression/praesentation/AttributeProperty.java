/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.praesentation;

/**
 * Encapsulates an Attribute/Value Pair for the stylesheetId
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AttributeProperty {

	private String attribute;

	private String value;

	/**
	 * @param attribute name of the attribute, never <code>null</code>
	 */
	public AttributeProperty(String attribute) {
		this.attribute = attribute;
	}

	/**
	 * @param attribute name of the attribute, never <code>null</code>
	 * @param value of the attribute, may be <code>null</code>
	 */
	public AttributeProperty(String attribute, String value) {
		this.attribute = attribute;
		this.value = value;
	}

	/**
	 * @return name of the attribute, never <code>null</code>
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @return value of the attribute, may be <code>null</code>
	 */
	public String getValue() {
		return value;
	}

}
