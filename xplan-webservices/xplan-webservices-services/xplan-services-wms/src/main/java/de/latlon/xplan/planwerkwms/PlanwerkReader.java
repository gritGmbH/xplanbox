/*-
 * #%L
 * xplan-services-wms - deegree XPlan WebMapService
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
package de.latlon.xplan.planwerkwms;

import org.deegree.commons.utils.JDBCUtils;
import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Reads the metadata of the requested Planwerk from the database.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkReader {

	private static final Logger LOG = LoggerFactory.getLogger(PlanwerkReader.class);

	private String jdbcResourceId;

	/**
	 * @param jdbcResourceId ID of the JDBC resource in the workspace, never
	 * <code>null</code>
	 */
	public PlanwerkReader(String jdbcResourceId) {
		this.jdbcResourceId = jdbcResourceId;
	}

	/**
	 * Reads the metadata of the requested Planwerk from the database.
	 * @param workspace the current workspace, never <code>null</code>
	 * @param name the name of the requested Planwerk
	 * @param planStatus
	 * @return
	 */
	public Plan retrieveAvailablePlanwerke(Workspace workspace, String name, String planStatus) {
		Connection conn = getConnection(workspace);
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String decodedName = URLDecoder.decode(name, StandardCharsets.UTF_8.toString());
				String sql = "SELECT name, array_agg(id), ST_AsText(ST_Envelope(ST_Union(bbox))), array_remove(array_agg(DISTINCT title), NULL), array_remove(array_agg(DISTINCT resourceidentifier), NULL), array_remove(array_agg(DISTINCT datametadataurl), NULL), array_remove(array_agg(DISTINCT servicemetadataurl), NULL) "
						+ "FROM xplanmgr.plans " + "LEFT JOIN xplanmgr.planwerkwmsmetadata ON id = plan "
						+ "WHERE (replace(name, '/','') = ? OR (NOT EXISTS (SELECT 1 FROM xplanmgr.plans LEFT JOIN xplanmgr.planwerkwmsmetadata ON id = plan WHERE replace(name, '/','') = ? ) AND regexp_replace(name, '[^a-zA-Z0-9\\-_]', '', 'g' ) = ?)) "
						+ "AND planstatus = ? " + "GROUP BY name";
				ps = conn.prepareStatement(sql);
				ps.setString(1, decodedName);
				ps.setString(2, decodedName);
				ps.setString(3, decodedName);
				ps.setString(4, planStatus);
				rs = ps.executeQuery();
				if (rs.next()) {
					return createPlanwerk(rs);
				}
			}
			catch (SQLException e) {
				throw new IllegalArgumentException("Planwerke could not be requested", e);
			}
			catch (UnsupportedEncodingException e) {
				throw new IllegalArgumentException("Name could not be decoded", e);
			}
			finally {
				JDBCUtils.close(rs, ps, conn, LOG);
			}
		}
		return null;
	}

	private Plan createPlanwerk(ResultSet rs) throws SQLException {
		String name = rs.getString(1);
		List<Integer> managerIds = parseManagerIds(rs.getArray(2));
		String bbox = rs.getString(3);
		List<String> titles = parseStringArray(rs.getArray(4));
		List<String> resourceidentifiers = parseStringArray(rs.getArray(5));
		List<String> datametadataurls = parseStringArray(rs.getArray(6));
		List<String> servicemetadataurl = parseStringArray(rs.getArray(7));
		return new Plan(name, managerIds, bbox, titles, resourceidentifiers, datametadataurls, servicemetadataurl);
	}

	private List<Integer> parseManagerIds(Array managerIdArray) throws SQLException {
		Integer[] ids = (Integer[]) managerIdArray.getArray();
		return Arrays.asList(ids);
	}

	private List<String> parseStringArray(Array stringArray) throws SQLException {
		String[] text = (String[]) stringArray.getArray();
		return Arrays.asList(text);
	}

	private Connection getConnection(Workspace workspace) {
		ConnectionProvider connectionProvider = workspace.getResource(ConnectionProviderProvider.class, jdbcResourceId);
		return connectionProvider.getConnection();
	}

}
