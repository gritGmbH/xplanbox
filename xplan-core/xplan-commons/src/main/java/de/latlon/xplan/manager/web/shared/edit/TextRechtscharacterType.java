/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared.edit;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public enum TextRechtscharacterType {

	FESTSETZUNG(1000),

	NACHRICHTLICHEUEBERNAHME(2000),

	HINWEIS(3000),

	VERMERK(4000),

	KENNZEICHNUNG(5000),

	UNBEKANNT(9998);

	private final int code;

	TextRechtscharacterType(int code) {
		this.code = code;
	}

	public static TextRechtscharacterType fromCode(int code) {
		for (TextRechtscharacterType rechtscharacterType : values()) {
			if (rechtscharacterType.code == code)
				return rechtscharacterType;
		}
		throw new IllegalArgumentException("Could not find rechtscharacter with code " + code);
	}

	public int getCode() {
		return code;
	}

}
