/*-
 * #%L
 * xplan-core-gwt - Modul zur Gruppierung von GWT Komponenten
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
package de.latlon.xplanbox.core.gwt.commons.shared;

import java.io.Serializable;

/**
 * Summarized informations about the validated plan
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ValidationSummary implements Serializable {

	private static final long serialVersionUID = -3319541573993693239L;

	private String planUuid;

	private String validationName;

	public ValidationSummary() {
	}

	/**
	 * @param planUuid uuid of the plan, may be <code>null</code>
	 * @param validationName name of the validation, may be <code>null</code>
	 */
	public ValidationSummary(String planUuid, String validationName) {
		this.planUuid = planUuid;
		this.validationName = validationName;
	}

	/**
	 * @return the uuid of the plan, may be <code>null</code>
	 */
	public String getPlanUuid() {
		return planUuid;
	}

	/**
	 * @param planUuid the uuid of the plan, may be <code>null</code>
	 */
	public void setPlanUuid(String planUuid) {
		this.planUuid = planUuid;
	}

	/**
	 * @return the name of the validation, may be <code>null</code>
	 */
	public String getValidationName() {
		return validationName;
	}

	/**
	 * @param validationName the name of the validation, may be <code>null</code>
	 */
	public void setValidationName(String validationName) {
		this.validationName = validationName;
	}

}
