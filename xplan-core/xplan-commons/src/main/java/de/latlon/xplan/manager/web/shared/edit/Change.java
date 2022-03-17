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
 * Encapsulates a change of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class Change {

	private String planName;

	private int legalNatureCode = -1;

	private String number;

	private ChangeType type;

	public Change() {
	}

	/**
	 * @param planName planName, may be <code>null</code>
	 * @param type type, may be <code>null</code>
	 */
	public Change(String planName, ChangeType type) {
		this(planName, -1, null, type);
	}

	/**
	 * @param planName planName, may be <code>null</code>
	 * @param legalNature legalNature, may be <code>null</code>
	 * @param number number, may be <code>null</code>
	 * @param type type, may be <code>null</code>
	 */
	public Change(String planName, int legalNature, String number, ChangeType type) {
		this.planName = planName;
		this.legalNatureCode = legalNature;
		this.number = number;
		this.type = type;
	}

	/**
	 * @return the planName, may be <code>null</code>
	 */
	public String getPlanName() {
		return planName;
	}

	/**
	 * @param planName the planName to set, may be <code>null</code>
	 */
	public void setPlanName(String planName) {
		this.planName = planName;
	}

	/**
	 * @return the legalNature, may be <code>null</code>
	 */
	public int getLegalNatureCode() {
		return legalNatureCode;
	}

	/**
	 * @param legalNatureCode the legalNature to set, may be <code>null</code>
	 */
	public void setLegalNatureCode(int legalNatureCode) {
		this.legalNatureCode = legalNatureCode;
	}

	/**
	 * @return the number, may be <code>null</code>
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set, may be <code>null</code>
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the type, may be <code>null</code>
	 */
	public ChangeType getType() {
		return type;
	}

	/**
	 * @param type the type to set, may be <code>null</code>
	 */
	public void setType(ChangeType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Change [planName=" + planName + ", legalNatureCode=" + legalNatureCode + ", number=" + number
				+ ", type=" + type + "]";
	}

}
