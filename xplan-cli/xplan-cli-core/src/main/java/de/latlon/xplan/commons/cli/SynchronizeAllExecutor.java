/*-
 * #%L
 * xplan-commons-cli - Commons Paket fuer CLIs
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
package de.latlon.xplan.commons.cli;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static de.latlon.xplan.commons.cli.DatabaseUtils.closeQuietly;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SynchronizeAllExecutor {

	private static final Logger LOG = LoggerFactory.getLogger(SynchronizeAllExecutor.class);

	private final String logTableName;

	private final SynchronizeExecutor executor;

	/**
	 * @param logTableName the name (including the schema) of the log table, never
	 * <code>null</code>
	 * @param synchronizer the {@link Synchronizer} used for the synchronization, never
	 * <code>null</code>
	 */
	public SynchronizeAllExecutor(String logTableName, Synchronizer synchronizer) {
		this.logTableName = logTableName;
		this.executor = new SynchronizeExecutor(logTableName, synchronizer);
	}

	/**
	 * Synchronizes all plan available from the log table.
	 */
	public void synchronizeAll(Connection conn) {
		insertInLogTable(conn);
		executor.synchronize(conn);
	}

	@SuppressFBWarnings(value = "SQL_INJECTION_JDBC", justification = "logTableName is a fix value")
	private void insertInLogTable(Connection conn) {
		LOG.info("Copy required metadata into {}", logTableName);
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"insert into " + logTableName + " (xplanmgrid, xp_version, newplanstatus, operation, datum)"
							+ " SELECT id, xp_version, planstatus, 'INSERT', now() from xplanmgr.plans;");

			LOG.debug("Execute insert in {}: {}", logTableName, ps);
			ps.execute();
			conn.commit();
		}
		catch (SQLException e) {
			LOG.error("Could not update plan {}", logTableName, e);
			try {
				if (conn != null) {
					conn.rollback();
				}
			}
			catch (SQLException s) {
				LOG.error("Rollback failed", e);
			}
		}
		finally {
			closeQuietly(ps);
		}
	}

}
