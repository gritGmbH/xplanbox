/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
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
package de.latlon.xplan.wms.visibility;

import org.deegree.db.ConnectionProvider;
import org.deegree.db.ConnectionProviderProvider;
import org.deegree.layer.metadata.LayerMetadata;
import org.deegree.services.controller.OGCFrontController;
import org.deegree.services.wms.visibility.LayerVisibilityInspector;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * Abstract {@link LayerVisibilityInspector} to check if the plan a (raster) layer is part
 * if currently valid according the validity period. Subclasses have to specify the
 * database schema.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public abstract class ValidityPeriodInspector implements LayerVisibilityInspector {

	private static final Logger LOG = LoggerFactory.getLogger(ValidityPeriodInspector.class);

	private static final String JDBC_CONFIG_ID = "xplan";

	private final String schema;

	/**
	 * @param schema the database schema (depends on the plan status), never
	 * <code>null</code>
	 */
	public ValidityPeriodInspector(String schema) {
		this.schema = schema;
	}

	public boolean isVisible(LayerMetadata layerMetadata) {
		String layerName = layerMetadata.getName();
		int planId = parsePlanId(layerName);
		if (planId < 0)
			return true;
		Timestamp now = new Timestamp(System.currentTimeMillis());

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try (Connection connection = openConnection()) {
			String sql = createSql();

			stmt = connection.prepareStatement(sql);

			stmt.setTimestamp(1, now);
			stmt.setTimestamp(2, now);
			stmt.setInt(3, planId);
			stmt.setInt(4, planId);
			stmt.setInt(5, planId);
			stmt.setInt(6, planId);

			LOG.debug("SQL-Statement to check if layer with id {} is visible: {}", planId, stmt);

			rs = stmt.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);
		}
		catch (Exception e) {
			LOG.error("Exception requesting database if layer with id " + planId + " is visible!", e);
		}
		finally {
			closeQuietly(stmt);
			closeQuietly(rs);
		}

		// layer should be visible by default (or if an exception occured)
		return true;
	}

	private int parsePlanId(String layerName) {
		if (layerName.contains("_")) {
			String planId = layerName.substring(0, layerName.indexOf("_"));
			return Integer.parseInt(planId);
		}
		return -1;
	}

	private String createSql() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("((a.xplan_gueltigkeitbeginn < ? OR a.xplan_gueltigkeitbeginn IS NULL) ");
		sql.append("AND (a.xplan_gueltigkeitende > ? OR a.xplan_gueltigkeitende IS NULL )) AS isVisible ");
		sql.append("FROM (");
		sql.append("SELECT xplan_gueltigkeitbeginn, xplan_gueltigkeitende FROM ");
		sql.append(schema).append(".xplan_bp_plan WHERE xplan_mgr_planid = ? ");
		sql.append("UNION ");
		sql.append("SELECT xplan_gueltigkeitbeginn, xplan_gueltigkeitende FROM ");
		sql.append(schema).append(".xplan_fp_plan WHERE xplan_mgr_planid = ? ");
		sql.append("UNION ");
		sql.append("SELECT xplan_gueltigkeitbeginn, xplan_gueltigkeitende FROM ");
		sql.append(schema).append(".xplan_lp_plan WHERE xplan_mgr_planid = ? ");
		sql.append("UNION ");
		sql.append("SELECT xplan_gueltigkeitbeginn, xplan_gueltigkeitende FROM ");
		sql.append(schema).append(".xplan_rp_plan WHERE xplan_mgr_planid = ? ");
		sql.append(") AS a");
		return sql.toString();
	}

	private Connection openConnection() {
		Workspace workspace = OGCFrontController.getServiceWorkspace().getNewWorkspace();
		ConnectionProvider resource = workspace.getResource(ConnectionProviderProvider.class, JDBC_CONFIG_ID);
		return resource.getConnection();
	}

	private void closeQuietly(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		}
		catch (SQLException e) {
			LOG.trace("DB connection could not be closed!", e);
			LOG.warn("DB connection could not be closed: {}", e.getMessage());
		}
	}

	private void closeQuietly(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		}
		catch (SQLException e) {
			LOG.trace("DB connection could not be closed!", e);
			LOG.warn("DB connection could not be closed: {}", e.getMessage());
		}
	}

}
