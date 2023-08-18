/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class to open and close database resources.
 *
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 */
public final class DatabaseUtils {

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseUtils.class);

	private DatabaseUtils() {
	}

	/**
	 * quietly closes a connection and logs if closing failed
	 * @param conn connection to close
	 */
	public static void closeQuietly(Connection conn) {
		if (conn != null)
			try {
				conn.close();
			}
			catch (SQLException e) {
				LOG.trace("DB connection could not be closed!", e);
				LOG.warn("DB connection could not be closed: {}", e.getMessage());
			}
	}

	/**
	 * quietly closes a statement and logs if closing failed
	 * @param stmt statement to close
	 */
	public static void closeQuietly(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		}
		catch (SQLException e) {
			LOG.trace("DB connection could not be closed!", e);
			LOG.warn("DB connection could not be closed: {}", e.getMessage());
		}
	}

	/**
	 * quietly closes a connection and logs if closing failed
	 * @param rs result set to close
	 */
	public static void closeQuietly(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		}
		catch (SQLException e) {
			LOG.trace("DB connection could not be closed!", e);
			LOG.warn("DB connection could not be closed: {}", e.getMessage());
		}
	}

	/**
	 * quietly closes a statement and result set and logs if closing failed
	 * @param stmt
	 * @param rs
	 */
	public static void closeQuietly(Statement stmt, ResultSet rs) {
		closeQuietly(rs);
		closeQuietly(stmt);
	}

	/**
	 * quietly closes a connection, statement and result set and logs if closing failed
	 * @param conn
	 * @param stmt
	 * @param rs
	 */
	public static void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
		closeQuietly(rs);
		closeQuietly(stmt);
		closeQuietly(conn);
	}

}
