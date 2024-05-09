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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SynchronizeExecutor {

	private static final Logger LOG = LoggerFactory.getLogger(SynchronizeExecutor.class);

	private String logTableName;

	private Synchronizer synchronizer;

	/**
	 * @param logTableName the name (including the schema) of the log table, never
	 * <code>null</code>
	 * @param synchronizer the {@link Synchronizer} used for the synchronization, never
	 * <code>null</code>
	 */
	public SynchronizeExecutor(String logTableName, Synchronizer synchronizer) {
		this.logTableName = logTableName;
		this.synchronizer = synchronizer;
	}

	/**
	 * Starts the synchronization.
	 * @param conn to the dataase with th log table, never <code>null</code>
	 */
	@SuppressFBWarnings(value = { "SQL_INJECTION_JDBC", "SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING" },
			justification = "logTableName is a fix value")
	public void synchronize(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT xplanmgrid, ");
			sb.append("(SELECT xp_version FROM ")
				.append(logTableName)
				.append(" log0 WHERE log0.xplanmgrid = log.xplanmgrid LIMIT 1), ");
			sb.append("(SELECT id oldid FROM ")
				.append(logTableName)
				.append(" log4 WHERE log4.xplanmgrid = log.xplanmgrid ORDER BY datum ASC LIMIT 1), ");
			sb.append("(SELECT oldplanstatus FROM ")
				.append(logTableName)
				.append(" log1 WHERE log1.xplanmgrid = log.xplanmgrid ORDER BY datum ASC LIMIT 1), ");
			sb.append("(SELECT operation oldoperation FROM ")
				.append(logTableName)
				.append(" log2 WHERE log2.xplanmgrid = log.xplanmgrid ORDER BY datum ASC LIMIT 1), ");
			sb.append("(SELECT id newid FROM ")
				.append(logTableName)
				.append(" log4 WHERE log4.xplanmgrid = log.xplanmgrid ORDER BY datum DESC LIMIT 1), ");
			sb.append("(SELECT newplanstatus FROM ")
				.append(logTableName)
				.append(" log3 WHERE log3.xplanmgrid = log.xplanmgrid ORDER BY datum DESC LIMIT 1), ");
			sb.append("(SELECT operation newoperation FROM ")
				.append(logTableName)
				.append(" log4 WHERE log4.xplanmgrid = log.xplanmgrid ORDER BY datum DESC LIMIT 1) ");
			sb.append("FROM ").append(logTableName).append(" log GROUP BY xplanmgrid;");
			ps = conn.prepareStatement(sb.toString());
			LOG.debug("Execute select plans to update: {}", ps);

			rs = ps.executeQuery();
			while (rs.next()) {
				synchronizePlan(conn, rs);
			}
		}
		catch (SQLException e) {
			LOG.error("Could not select plans to synchronize from " + logTableName, e);
		}
		finally {
			DatabaseUtils.closeQuietly(ps, rs);
		}
	}

	private void synchronizePlan(Connection conn, ResultSet rs) {
		int xplanmgrid = -1;
		try {
			xplanmgrid = rs.getInt(1);
			String version = rs.getString(2);
			int oldid = rs.getInt(3);
			String oldplanstatus = rs.getString(4);
			String oldoperation = rs.getString(5);
			int newid = rs.getInt(6);
			String newplanstatus = rs.getString(7);
			String newoperation = rs.getString(8);

			Operation operation = determineOperationForSynchronization(oldoperation, newoperation);
			if (operation != null) {
				LOG.info(
						"Synchronize tables in lgv syn schema for plan with id {}, operation is {}, oldplanstatus {}, newplanstatus {}",
						xplanmgrid, operation, StringUtils.normalizeSpace(oldplanstatus),
						StringUtils.normalizeSpace(newplanstatus));
				synchronizer.synchronize(conn, oldid, newid, xplanmgrid, version, oldplanstatus, newplanstatus,
						operation);
			}
			else {
				LOG.info("Plan with id {} is already removed from xPlanBox. Will be removed from {}.", xplanmgrid,
						operation, logTableName);
			}
			removePlanFromLog(conn, xplanmgrid);

			conn.commit();
		}
		catch (SQLException | SynchronizationException e) {
			LOG.error("Could not update plan with id {}. Plan is not synchronized", xplanmgrid, e);
			try {
				if (conn != null) {
					conn.rollback();
				}
			}
			catch (SQLException s) {
				LOG.error("Rollback failed", e);
			}
		}
	}

	private Operation determineOperationForSynchronization(String oldoperation, String newoperation) {
		if (Operation.INSERT.name().equals(oldoperation) && Operation.INSERT.name().equals(newoperation))
			return Operation.INSERT;
		if (Operation.UPDATE.name().equals(oldoperation) && Operation.UPDATE.name().equals(newoperation))
			return Operation.UPDATE;
		if (Operation.DELETE.name().equals(oldoperation) && Operation.DELETE.name().equals(newoperation))
			return Operation.DELETE;

		if (Operation.INSERT.name().equals(oldoperation) && !Operation.DELETE.name().equals(newoperation))
			return Operation.INSERT;
		if (Operation.UPDATE.name().equals(oldoperation) && Operation.DELETE.name().equals(newoperation))
			return Operation.DELETE;
		if (Operation.UPDATE.name().equals(oldoperation) && Operation.UPDATE.name().equals(newoperation))
			return Operation.UPDATE;
		return null;
	}

	@SuppressFBWarnings(value = "SQL_INJECTION_JDBC", justification = "logTableName is a fix value")
	private void removePlanFromLog(Connection conn, int xplanmgrid) {
		PreparedStatement ps = null;
		try {
			String selectSynTableNames = "DELETE FROM " + logTableName + " WHERE xplanmgrid = ?";
			ps = conn.prepareStatement(selectSynTableNames);
			ps.setInt(1, xplanmgrid);
			LOG.debug("Execute select plans to update: {}", ps);
			ps.execute();
		}
		catch (SQLException e) {
			LOG.warn("Could not remove plan with id {} from {}: {}", xplanmgrid, logTableName, e);
		}
		finally {
			DatabaseUtils.closeQuietly(ps);
		}
	}

}
