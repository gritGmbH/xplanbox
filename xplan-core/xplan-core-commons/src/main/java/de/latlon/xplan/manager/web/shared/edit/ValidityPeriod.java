/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared.edit;

import javax.validation.Valid;

import java.io.Serializable;
import java.util.Date;

/**
 * Encapsulates the validity period of a plan.
 *
 * @deprecated will be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
@Deprecated
public class ValidityPeriod implements Serializable {

	@Valid
	private Date start;

	@Valid
	private Date end;

	public ValidityPeriod() {
	}

	/**
	 * @param start may be <code>null</code>
	 * @param end may be <code>null</code>
	 */
	public ValidityPeriod(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * @return the start date, may be <code>null</code>
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @param start the start date to set, may be <code>null</code>
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * @return the end date, may be <code>null</code>
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param end the end date to set, may be <code>null</code>
	 */
	public void setEnd(Date end) {
		this.end = end;
	}

}
