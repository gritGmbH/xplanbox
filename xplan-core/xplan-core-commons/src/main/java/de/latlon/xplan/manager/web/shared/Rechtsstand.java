/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared;

import java.io.Serializable;

/**
 * Container for legislation status. Can be used on server and client side.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class Rechtsstand implements Serializable {

	public static final Rechtsstand UNKNOWN_RECHTSSTAND = new Rechtsstand(-1);

	private int codeNumber;

	private String translatedCode;

	/**
	 * An empty constructor is mandatory for GWT applications.
	 */
	public Rechtsstand() {
	}

	/**
	 * @param codeNumber code number
	 */
	public Rechtsstand(int codeNumber) {
		this(codeNumber, null);
	}

	/**
	 * @param codeNumber code number
	 * @param translatedCode may be <code>null</code>
	 */
	public Rechtsstand(int codeNumber, String translatedCode) {
		this.codeNumber = codeNumber;
		this.translatedCode = translatedCode;
	}

	/**
	 * @return code number
	 */
	public int getCodeNumber() {
		return codeNumber;
	}

	/**
	 * @param codeNumber code number
	 */
	public void setCodeNumber(int codeNumber) {
		this.codeNumber = codeNumber;
	}

	/**
	 * @return translated code, may be <code>null</code>
	 */
	public String getTranslatedCode() {
		return translatedCode;
	}

	/**
	 * @param translatedCode may be <code>null</code>
	 */
	public void setTranslatedCode(String translatedCode) {
		this.translatedCode = translatedCode;
	}

}
