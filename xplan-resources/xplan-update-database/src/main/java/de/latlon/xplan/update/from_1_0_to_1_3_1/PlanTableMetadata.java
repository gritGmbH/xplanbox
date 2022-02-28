/*-
 * #%L
 * xplan-update-database - update of database
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.update.from_1_0_to_1_3_1;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class PlanTableMetadata {

	private final String planTableName;

	private final String bereichTableName;

	private final String dateColumnName;

	public PlanTableMetadata(String planTableName, String bereichTableName, String dateColumnName) {
		this.planTableName = planTableName;
		this.bereichTableName = bereichTableName;
		this.dateColumnName = dateColumnName;
	}

	/**
	 * @return the planTableName
	 */
	public String getPlanTableName() {
		return planTableName;
	}

	/**
	 * @return the dateColumnName
	 */
	public String getDateColumnName() {
		return dateColumnName;
	}

	/**
	 * @return the bereichTableName
	 */
	public String getBereichTableName() {
		return bereichTableName;
	}

}
