/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.expression.praesentation.attribute;

/**
 * Encapsulates an Attribute/Value Pair for the stylesheetId
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AttributeProperty {

	private String attribute;

	private AttributePropertyType attributePropertyType;

	private String value;

	private String codeListId;

	/**
	 * @param
	 * @param attribute name of the attribute, never <code>null</code>
	 * @param attributePropertyType type of the attribute, never <code>null</code>
	 * @param value of the attribute, may be <code>null</code>
	 * @param codeListId of the attribute if attributePropertyType is ENUM, may be
	 * <code>null</code> if attributePropertyType is not ENUM
	 */
	public AttributeProperty(String attribute, AttributePropertyType attributePropertyType, String value,
			String codeListId) {
		this.attribute = attribute;
		this.value = value;
		this.attributePropertyType = attributePropertyType;
		this.codeListId = codeListId;
	}

	/**
	 * @return name of the attribute, never <code>null</code>
	 */
	public String getAttribute() {
		return attribute;
	}

	/**
	 * @return type of the attribute, never <code>null</code>
	 */
	public AttributePropertyType getAttributePropertyType() {
		return attributePropertyType;
	}

	/**
	 * @return value of the attribute, may be <code>null</code>
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the codeListId, must not be <code>null</code> if the
	 * {@link AttributePropertyType} is ENUM, otherwise <code>null</code>
	 */
	public String getCodeListId() {
		return codeListId;
	}

}
