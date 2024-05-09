/*-
 * #%L
 * xplan-cli-tools - Kommandozeilenwerkzeuge fuer die xPlanBox
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
package de.latlon.xplanbox.cli.admin.evaluation;

import de.latlon.xplan.commons.cli.Operation;
import de.latlon.xplan.commons.cli.SynchronizationException;
import de.latlon.xplan.commons.cli.Synchronizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.gdal.gdal.gdal;
import org.gdal.ogr.Geometry;
import org.gdal.ogr.ogr;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static de.latlon.xplan.commons.cli.DatabaseUtils.closeQuietly;
import static de.latlon.xplanbox.cli.admin.evaluation.UpdateUtils.detectBlobSchemaByVersion;
import static de.latlon.xplanbox.cli.admin.evaluation.UpdateUtils.detectNamespace;
import static de.latlon.xplanbox.cli.admin.evaluation.UpdateUtils.detectSynSchemaByPlanStatus;
import static de.latlon.xplanbox.cli.admin.evaluation.UpdateUtils.detectXPath;

/**
 * Inserts/Updates/Deletes the evaluation xplansyn schema.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class EvaluationSchemaSynchronizer implements Synchronizer {

	private static final Logger LOG = LogManager.getLogger(EvaluationSchemaSynchronizer.class);

	static {
		try {
			gdal.AllRegister();
		}
		catch (Exception e) {
			LOG.error("Registration of GDAL JNI adapter failed: " + e.getMessage(), e);
		}
	}

	public void synchronize(Connection conn, int oldid, int newid, int xPlanManagerId, String planVersion,
			String oldPlanStatus, String newPlanStatus, Operation operation) throws SynchronizationException {
		LOG.info("Synchronize plan with id {}.", xPlanManagerId);
		String blobSchema = detectBlobSchemaByVersion(planVersion);
		String oldSynSchema = detectSynSchemaByPlanStatus(oldPlanStatus);
		String newSynSchema = detectSynSchemaByPlanStatus(newPlanStatus);

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String synSchema = oldSynSchema != null ? oldSynSchema : newSynSchema;
			// table names should be the same for all syn schemas!
			String selectSynTableNames = "SELECT TABLE_NAME::text FROM information_schema.TABLES WHERE TABLE_NAME LIKE 'xplan_%' AND table_schema = ?";
			ps = conn.prepareStatement(selectSynTableNames);
			ps.setString(1, synSchema);
			LOG.debug("Execute select syn table names: {}", ps);

			rs = ps.executeQuery();
			while (rs.next()) {
				String synTableName = rs.getString(1);
				synchronizeTable(conn, xPlanManagerId, oldSynSchema, newSynSchema, blobSchema, operation, synTableName);
			}
		}
		catch (SQLException e) {
			throw new SynchronizationException(e);
		}
		finally {
			closeQuietly(ps, rs);
		}
	}

	private void synchronizeTable(Connection conn, int xPlanManagerId, String oldSynSchema, String newSynSchema,
			String blobSchema, Operation operation, String synTableName) throws SQLException {
		LOG.info("Synchronize table {}, old schema {}, new schema {}, operation {}", synTableName, oldSynSchema,
				newSynSchema, operation);
		switch (operation) {
			case INSERT:
				insertInEvaluationTable(conn, xPlanManagerId, newSynSchema, synTableName);
				updateGeomColumns(conn, xPlanManagerId, newSynSchema, synTableName, blobSchema);
				break;
			case UPDATE:
				deleteFromEvaluationTable(conn, xPlanManagerId, oldSynSchema, synTableName);
				insertInEvaluationTable(conn, xPlanManagerId, newSynSchema, synTableName);
				updateGeomColumns(conn, xPlanManagerId, newSynSchema, synTableName, blobSchema);
				break;
			case DELETE:
				deleteFromEvaluationTable(conn, xPlanManagerId, oldSynSchema, synTableName);
				break;
			default:
				LOG.warn("Unsupported operation: {}", operation);
		}
	}

	private void updateGeomColumns(Connection conn, int xPlanManagerId, String synSchema, String synTableName,
			String blobSchema) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String selectGeomColumns = "SELECT column_name, table_schema FROM information_schema.columns WHERE table_schema = ? AND table_name = ? AND udt_name = 'geometry'";
			ps = conn.prepareStatement(selectGeomColumns);
			ps.setString(1, synSchema);
			ps.setString(2, synTableName);
			LOG.debug("Execute select geom columns: {}", ps);

			rs = ps.executeQuery();
			while (rs.next()) {
				String geomColumn = rs.getString(1);
				LOG.info("Update geometry column {}", geomColumn);
				updateGeomColumn(xPlanManagerId, conn, synSchema, blobSchema, synTableName, geomColumn);
			}
		}
		finally {
			closeQuietly(ps, rs);
		}
	}

	@SuppressFBWarnings(value = { "SQL_INJECTION_JDBC", "SQL_PREPARED_STATEMENT_GENERATED_FROM_NONCONSTANT_STRING" },
			justification = "xPath, blobSchema and synTableWithSchema are fix values")
	private void updateGeomColumn(int xPlanManagerId, Connection conn, String synSchema, String blobSchema,
			String synTableName, String geomColumn) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String synTableWithSchema = synSchema + "." + synTableName;
			String xPath = detectXPath(geomColumn);
			String namespace = detectNamespace(blobSchema);
			StringBuffer selectGmlGeom = new StringBuffer();
			selectGmlGeom.append("SELECT g.gml_id, array_to_string( ");
			selectGmlGeom.append("xpath( '").append(xPath).append("', ");
			selectGmlGeom.append("XMLPARSE(CONTENT encode(binary_object, 'escape')), ARRAY[ARRAY['xplan', '")
				.append(namespace)
				.append("']]),',') ");
			selectGmlGeom.append("as geom ");
			selectGmlGeom.append("FROM ").append(blobSchema).append(".gml_objects g ");
			selectGmlGeom.append("INNER JOIN ").append(synTableWithSchema).append(" t ");
			selectGmlGeom.append("ON t.xplan_gmlid = g.gml_id ");
			selectGmlGeom.append("WHERE xpath_exists('").append(xPath).append("', ");
			selectGmlGeom.append("XMLPARSE(CONTENT encode(binary_object, 'escape')), ARRAY[ARRAY['xplan', '")
				.append(namespace)
				.append("']]) ");
			selectGmlGeom.append("AND t.xplan_mgr_planid = ?");
			ps = conn.prepareStatement(selectGmlGeom.toString());
			ps.setInt(1, xPlanManagerId);
			LOG.debug("Execute select geom: {}", ps);

			rs = ps.executeQuery();
			int updateCount = 0;
			while (rs.next()) {
				String gmlId = rs.getString(1);
				String gmlGeom = rs.getString(2);
				int update = update(conn, synTableWithSchema, geomColumn, gmlId, gmlGeom);
				updateCount += update;
			}
			if (updateCount > 0) {
				LOG.info("Updated {} ", updateCount);
			}
		}
		finally {
			closeQuietly(ps, rs);
		}
	}

	@SuppressFBWarnings(value = "SQL_INJECTION_JDBC", justification = "synTableWithSchema is a fix value")
	private int update(Connection conn, String synTableWithSchema, String geomColumn, String gmlId, String gmlGeom)
			throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Geometry geom = ogr.CreateGeometryFromGML(gmlGeom);
			if (geom != null) {
				StringBuffer updateGeomColumn = new StringBuffer();
				updateGeomColumn.append("UPDATE xplanevaluation").append(synTableWithSchema);
				updateGeomColumn.append(" SET ")
					.append(geomColumn)
					.append(" = ST_GeomFromWKB( ? ) WHERE xplan_gmlid = ?");
				ps = conn.prepareStatement(updateGeomColumn.toString());
				byte[] geomBytes = geom.ExportToWkb();
				ps.setBytes(1, geomBytes);
				ps.setString(2, gmlId);
				LOG.debug("Execute update geom column: {}", ps);

				return ps.executeUpdate();
			}
		}
		finally {
			closeQuietly(ps, rs);
		}
		return 0;
	}

	@SuppressFBWarnings(value = "SQL_INJECTION_JDBC", justification = "synTableWithSchema is a fix value")
	private void insertInEvaluationTable(Connection conn, int xPlanManagerId, String synSchema, String synTableName)
			throws SQLException {
		String synTableWithSchema = synSchema + "." + synTableName;
		PreparedStatement ps = null;
		try {
			StringBuffer insertInEvaluationSyn = new StringBuffer();
			insertInEvaluationSyn.append("INSERT INTO xplanevaluation").append(synTableWithSchema);
			insertInEvaluationSyn.append(" SELECT * FROM ").append(synTableWithSchema);
			insertInEvaluationSyn.append(" WHERE xplan_mgr_planid = ?");
			ps = conn.prepareStatement(insertInEvaluationSyn.toString());
			ps.setInt(1, xPlanManagerId);

			LOG.debug("Execute insert in xplanevaluation syn schema: {}", ps);
			ps.execute();
		}
		finally {
			closeQuietly(ps);
		}
	}

	@SuppressFBWarnings(value = "SQL_INJECTION_JDBC", justification = "synSchema and synTableName are fix values")
	private void deleteFromEvaluationTable(Connection conn, int xPlanManagerId, String synSchema, String synTableName)
			throws SQLException {
		PreparedStatement ps = null;
		try {
			StringBuffer insertInEvaluationSyn = new StringBuffer();
			insertInEvaluationSyn.append("DELETE FROM xplanevaluation")
				.append(synSchema)
				.append(".")
				.append(synTableName);
			insertInEvaluationSyn.append(" WHERE xplan_mgr_planid = ?");
			ps = conn.prepareStatement(insertInEvaluationSyn.toString());
			ps.setInt(1, xPlanManagerId);

			LOG.debug("Execute delete from xplanevaluation syn schema: {}", ps);
			ps.execute();
		}
		finally {
			closeQuietly(ps);
		}
	}

}
