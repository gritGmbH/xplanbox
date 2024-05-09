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
 * Encapsulates results of the plan name.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanNameWithStatusResult implements Serializable {

	private String name;

	private PlanStatus status;

	private boolean planWithSameNameAndStatusExists;

	public PlanNameWithStatusResult() {
	}

	public PlanNameWithStatusResult(String name, PlanStatus status, boolean planWithSameNameAndStatusExists) {
		this.name = name;
		this.status = status;
		this.planWithSameNameAndStatusExists = planWithSameNameAndStatusExists;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlanStatus getStatus() {
		return status;
	}

	public void setStatus(PlanStatus status) {
		this.status = status;
	}

	public boolean isPlanWithSameNameAndStatusExists() {
		return planWithSameNameAndStatusExists;
	}

	public void setPlanWithSameNameAndStatusExists(boolean planWithSameNameAndStatusExists) {
		this.planWithSameNameAndStatusExists = planWithSameNameAndStatusExists;
	}

}
