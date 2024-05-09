/*-
 * #%L
 * xplan-core-job - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplan.job.validator.memory;

import java.util.Calendar;
import java.util.List;

/**
 * Container storing the list of inserted IDs.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InsertedFids {

	public static final String INSERTED_FIDS_KEY = "insertedFids";

	private Calendar insertTime;

	private List<String> fids;

	/**
	 * @param fids Ids of the inserted features, never <code>null</code>
	 */
	public InsertedFids(List<String> fids) {
		this.insertTime = Calendar.getInstance();
		this.fids = fids;
	}

	/**
	 * @return the date this {@link InsertedFids} instance was created, never
	 * <code>null</code>
	 */
	public Calendar getInsertTime() {
		return insertTime;
	}

	/**
	 * @return Ids of the inserted features, never <code>null</code>
	 */
	public List<String> getFids() {
		return fids;
	}

	@Override
	public String toString() {
		return "InsertedFids{" + "insertTime=" + insertTime + ", fids=" + fids + '}';
	}

}
