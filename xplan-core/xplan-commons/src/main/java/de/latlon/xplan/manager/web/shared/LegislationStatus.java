/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.web.shared;

/**
 * Container for legislation status. Can be used on server and client side.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class LegislationStatus {

	private int codeNumber;

	private String translatedCode;

	/**
	 * An empty constructor is mandatory for GWT applications.
	 */
	public LegislationStatus() {
	}

	/**
	 * @param codeNumber code number
	 */
	public LegislationStatus(int codeNumber) {
		this(codeNumber, null);
	}

	/**
	 * @param codeNumber code number
	 * @param translatedCode may be <code>null</code>
	 */
	public LegislationStatus(int codeNumber, String translatedCode) {
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
