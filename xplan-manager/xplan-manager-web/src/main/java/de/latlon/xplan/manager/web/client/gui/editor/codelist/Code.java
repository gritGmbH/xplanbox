/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.gui.editor.codelist;

/**
 * Represents a code of a codelist.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class Code {

	private final String code;

	private final String item;

	/**
	 * @param code the code value as integer
	 * @param item the item (text label) of the code, , should not be <code>null</code>
	 */
	public Code(int code, String item) {
		this(Integer.toString(code), item);
	}

	/**
	 * @param code the code value, should not be <code>null</code>
	 * @param item the item (text label) of the code, , should not be <code>null</code>
	 */
	public Code(String code, String item) {
		this.code = code;
		this.item = item;
	}

	/**
	 * @return the code value, should not be <code>null</code>
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the item (text label) of the code, , should not be <code>null</code>
	 */
	public String getItem() {
		return item;
	}

}
