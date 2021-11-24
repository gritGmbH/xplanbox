/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.manager.internalid;

import static de.latlon.xplan.manager.database.DatabaseUtils.closeQuietly;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.manager.configuration.InternalIdRetrieverConfiguration;
import de.latlon.xplan.manager.web.shared.ConfigurationException;

/**
 * Retrieves the internal id of a plan by the plan name from database.
 *
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class InternalIdRetriever {

	private static final Logger LOG = LoggerFactory.getLogger(InternalIdRetriever.class);

	private InternalIdRetrieverConfiguration configuration;

	/**
	 * Instantiates a new internal id retriever.
	 * @param configuration for this {@link InternalIdRetriever}, never <code>null</code>
	 * @throws IllegalArgumentException if configuration is <code>null</code>
	 */
	public InternalIdRetriever(InternalIdRetrieverConfiguration configuration) {
		if (configuration == null)
			throw new IllegalArgumentException("InternalIdRetrieverConfiguration must never be null");
		this.configuration = configuration;
	}

	/**
	 * Get matching internal ids.
	 * @param matchString plan name to match with the internal id
	 * @throws ConfigurationException
	 */
	public Map<String, String> getMatchingInternalIds(String matchString) throws ConfigurationException {
		return getInternalIdsFromStatement(matchString, configuration.getSelectMatchingIdsSql());
	}

	/**
	 * Get all internal ids.
	 * @throws ConfigurationException
	 */
	public Map<String, String> getAllInternalIds() throws ConfigurationException {
		return getInternalIdsFromStatement(configuration.getSelectAllSql());
	}

	/**
	 * Creates a connection from workspace.
	 * @return connection
	 * @throws ConfigurationException
	 */
	Connection createConnectionFromWorkspace() throws ConfigurationException {
		DeegreeWorkspace instance = DeegreeWorkspace.getInstance(configuration.getWorkspaceName());
		Workspace workspace = instance.getNewWorkspace();
		ConnectionProvider resource = workspace.getResource(ConnectionProviderProvider.class,
				configuration.getJdbcConnectionId());
		return resource.getConnection();
	}

	/**
	 * Retrieves a prepared statement from connection.
	 * @param matchString string to match with '?' from sql, may be <code>null</code>
	 * @param sql sql statement, never <code>null</code>
	 * @param conn connection, never <code>null</code>
	 * @return prepared statement
	 * @throws SQLException
	 */
	PreparedStatement retrievePreparedStatement(String matchString, String sql, Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(sql);
		if (matchString != null)
			ps.setString(1, matchString);
		LOG.trace("SQL to select internal IDs: {}", ps);
		return ps;
	}

	/**
	 * Retrieves a result set from prepared statement.
	 * @param ps prepared statement, never <code>null</code>
	 * @return result set
	 * @throws SQLException
	 */
	ResultSet retrieveResultSet(PreparedStatement ps) throws SQLException {
		return ps.executeQuery();
	}

	private Map<String, String> getInternalIdsFromStatement(String sql) throws ConfigurationException {
		return getInternalIdsFromStatement(null, sql);
	}

	private Map<String, String> getInternalIdsFromStatement(String matchString, String sql)
			throws ConfigurationException {
		Connection conn = createConnectionFromWorkspace();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = retrievePreparedStatement(matchString, sql, conn);
			rs = retrieveResultSet(ps);
			Map<String, String> result = collectResultSet(rs);
			LOG.debug("Result: {}", result);
			return result;
		}
		catch (SQLException e) {
			LOG.error("Error while internal id: {}", e.getMessage());
			LOG.debug("Exception: ", e);
			throw new ConfigurationException(e);
		}
		finally {
			closeQuietly(conn, ps, rs);
		}
	}

	private Map<String, String> collectResultSet(ResultSet rs) throws SQLException {
		Map<String, String> map = new LinkedHashMap<>();
		while (rs.next()) {
			String internalId = rs.getString(configuration.getInternalIdLabel());
			String internalName = rs.getString(configuration.getInternalNameLabel());
			LOG.debug("adding entry: {} with value {}", internalId, internalName);
			map.put(internalId, internalName);
		}
		return map;
	}

}
