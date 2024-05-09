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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DatabaseUtils {

	/**
	 * Creates a Postgres JDBC Url
	 * @param host never <code>null</code>
	 * @param port may be<code>null</code>
	 * @param database never <code>null</code>
	 * @return the jdbc url, never <code>null</code>
	 */
	public static String createJdbcUrl(String host, String port, String database) {
		StringBuffer jdbcUrl = new StringBuffer();
		jdbcUrl.append("jdbc:postgresql://");
		jdbcUrl.append(host);
		if (port != null)
			jdbcUrl.append(":").append(port);
		jdbcUrl.append("/");
		jdbcUrl.append(database);
		return jdbcUrl.toString();
	}

	/**
	 * @param ps to close, may be <code>null</code>
	 * @param rs to close, may be <code>null</code>
	 */
	public static void closeQuietly(PreparedStatement ps, ResultSet rs) {
		closeQuietly(ps);
		if (rs != null) {
			try {
				rs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param ps to close, may be <code>null</code>
	 */
	public static void closeQuietly(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			}
			catch (SQLException e) {
			}
		}
	}

}
