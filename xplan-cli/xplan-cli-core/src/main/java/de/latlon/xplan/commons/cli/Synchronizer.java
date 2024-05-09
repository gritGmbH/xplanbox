/*-
 * #%L
 * xplan-cli-core - Commons Paket fuer CLIs
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
package de.latlon.xplan.commons.cli;

import java.sql.Connection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface Synchronizer {

	/**
	 * Synchronizes the plan with the passed id.
	 * @param oldid of the entry in the log table, never <code>null</code>
	 * @param newid of the entry in the log table, never <code>null</code>
	 * @param conn the database connection, never <code>null</code>
	 * @param xPlanManagerId the id of the plan, never <code>null</code>
	 * @param planVersion the version of the plan, never <code>null</code>
	 * @param oldPlanStatus the old status of the plan, never <code>null</code>
	 * @param lastnew the new status of the plan, never <code>null</code>
	 * @param operation the operation, never <code>null</code>
	 * @throws SynchronizationException
	 */
	void synchronize(Connection conn, int oldid, int newid, int xPlanManagerId, String planVersion,
			String oldPlanStatus, String lastnew, Operation operation) throws SynchronizationException;

}
