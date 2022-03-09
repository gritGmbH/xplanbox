/*-
 * #%L
 * xplan-update-database - update of database
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.update.from_1_0_to_1_3_1;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.update.AbstractUpdater;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanType.LP_Plan;
import static de.latlon.xplan.commons.XPlanType.RP_Plan;
import static de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder.BP_RELEASE_DATE_PROP_NAME;
import static de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder.FP_RELEASE_DATE_PROP_NAME;
import static de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder.LP_RELEASE_DATE_PROP_NAME;
import static de.latlon.xplan.commons.feature.XPlanFeatureCollectionBuilder.RP_RELEASE_DATE_PROP_NAME;
import static de.latlon.xplan.manager.database.DatabaseUtils.closeQuietly;

/**
 * Updates the data from version 1.0 to 1.3.1.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class UpdaterFrom1_0To1_3_1 extends AbstractUpdater {

	private final Logger LOG = LoggerFactory.getLogger(UpdaterFrom1_0To1_3_1.class);

	private static final Map<XPlanType, PlanTableMetadata> PLAN_TABLE_METADATA = new HashMap<XPlanType, PlanTableMetadata>();

	private static final List<String> BEREICH_COLUMN_NAMES = new ArrayList<String>();

	static {
		PLAN_TABLE_METADATA.put(BP_Plan,
				new PlanTableMetadata("xplan_bp_plan", "xplan_bp_bereich", "xplan_" + BP_RELEASE_DATE_PROP_NAME));
		PLAN_TABLE_METADATA.put(LP_Plan,
				new PlanTableMetadata("xplan_lp_plan", "xplan_lp_bereich", "xplan_" + LP_RELEASE_DATE_PROP_NAME));
		PLAN_TABLE_METADATA.put(FP_Plan,
				new PlanTableMetadata("xplan_fp_plan", "xplan_fp_bereich", "xplan_" + FP_RELEASE_DATE_PROP_NAME));
		PLAN_TABLE_METADATA.put(RP_Plan,
				new PlanTableMetadata("xplan_rp_plan", "xplan_rp_bereich", "xplan_" + RP_RELEASE_DATE_PROP_NAME));

		BEREICH_COLUMN_NAMES.add("xplan_gehoertzubp_bereich");
		BEREICH_COLUMN_NAMES.add("xplan_gehoertzulp_bereich");
		BEREICH_COLUMN_NAMES.add("xplan_gehoertzufp_bereich");
		BEREICH_COLUMN_NAMES.add("xplan_gehoertzurp_bereich");
		BEREICH_COLUMN_NAMES.add("xplan_gehoertzuso_bereich");
		BEREICH_COLUMN_NAMES.add("xplan_gehoertnachrichtlichzubereich");
		BEREICH_COLUMN_NAMES.add("xplan_gehoertzubereich");
	}

	/**
	 * @param xplanDao allows access to the database, never <code>null</code>
	 */
	public UpdaterFrom1_0To1_3_1(XPlanDao xplanDao) {
		super(xplanDao);
	}

	@Override
	public void update(Connection conn) throws Exception {
		updateXPlanSynTables(conn);
	}

	private void updateXPlanSynTables(Connection conn) throws Exception {
		try {
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet tables = metaData.getTables(conn.getCatalog(), "xplansyn", "xplan_%", null);
			while (tables.next()) {
				String tableName = tables.getString("TABLE_NAME");
				if (!isPlanTable(tableName) && !tableName.endsWith("_pkey")) {
					updateXPlanSynTable(conn, metaData, tableName);
				}
			}
		}
		catch (SQLException e) {
			LOG.warn("Select of xplan syn schema failed.", e);
		}
	}

	private void updateXPlanSynTable(Connection conn, DatabaseMetaData metaData, String tableName) throws SQLException {
		LOG.info("Try to update table {}", tableName);
		String planReferenceColumn = findPlanReferenceColumnName(conn, metaData, tableName);
		if (planReferenceColumn != null) {
			updateXPlanSynTableWithPlanReference(conn, tableName, planReferenceColumn);
		}
		else {
			List<String> bereichReferenceColumnNames = findBereichReferenceColumnNames(conn, metaData, tableName);
			if (!bereichReferenceColumnNames.isEmpty())
				updateXPlanSynTableWithBereichReference(conn, tableName, bereichReferenceColumnNames);
			else
				LOG.warn("Table '{}' could not be updated - reference to plan or bereich table could not be resolved.",
						tableName);
		}
	}

	private void updateXPlanSynTableWithPlanReference(Connection conn, String tableName, String planReferenceColumn) {
		LOG.info("Update table {} with reference to plan (column {}).", tableName, planReferenceColumn);
		PreparedStatement stmt = null;
		StringBuffer selectSql = new StringBuffer();
		selectSql.append("SELECT attr_gml_id,").append(planReferenceColumn);
		selectSql.append(" FROM xplansyn.").append(tableName);
		selectSql.append(" WHERE ").append(planReferenceColumn).append(" IS NOT NULL");

		try {
			stmt = conn.prepareStatement(selectSql.toString());
			ResultSet referencesToPlan = stmt.executeQuery();
			while (referencesToPlan.next()) {
				String gmlId = referencesToPlan.getString("attr_gml_id");
				String planReferenceId = referencesToPlan.getString(planReferenceColumn);

				updateRowInXPlanSynTableWithPlanReference(conn, gmlId, planReferenceId, tableName);
			}
		}
		catch (SQLException e) {
			LOG.warn("Update of table xplansyn." + tableName + " failed.", e);
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void updateXPlanSynTableWithBereichReference(Connection conn, String tableName,
			List<String> bereichReferenceColumnNames) throws SQLException {
		for (String bereichReferenceColumnName : bereichReferenceColumnNames) {
			LOG.info("Update table {} with reference to bereich (column {}).", tableName, bereichReferenceColumnName);
			PreparedStatement selectMainStmt = null;
			StringBuffer selectSql = new StringBuffer();
			selectSql.append("SELECT attr_gml_id,").append(bereichReferenceColumnName);
			selectSql.append(" FROM xplansyn.").append(tableName);
			selectSql.append(" WHERE ").append(bereichReferenceColumnName).append(" IS NOT NULL");

			try {
				selectMainStmt = conn.prepareStatement(selectSql.toString());
				ResultSet referencesToPlan = selectMainStmt.executeQuery();
				while (referencesToPlan.next()) {
					String gmlId = referencesToPlan.getString("attr_gml_id");
					String bereichReferenceId = referencesToPlan.getString(bereichReferenceColumnName);

					updateRowInXplanSynTableWithBereichReference(conn, tableName, gmlId, bereichReferenceId);
				}

			}
			catch (SQLException e) {
				LOG.warn("Update of table xplansyn." + tableName + " failed.", e);
			}
			finally {
				closeQuietly(selectMainStmt);
			}
		}
	}

	private void updateRowInXplanSynTableWithBereichReference(Connection conn, String tableName, String gmlId,
			String bereichReferenceId) {
		XPlanType type = detectType(bereichReferenceId);
		if (type != null) {
			PlanTableMetadata planTableMetadata = PLAN_TABLE_METADATA.get(type);
			String columnToUpdate = planTableMetadata.getDateColumnName();
			String planTableName = planTableMetadata.getPlanTableName();
			String bereichTableName = planTableMetadata.getBereichTableName();

			StringBuffer updateSql = new StringBuffer();
			updateSql.append("UPDATE xplansyn.").append(tableName);
			updateSql.append(" SET ").append(columnToUpdate).append(" = subquery.date");
			updateSql.append(" FROM ( ");
			updateSql.append("SELECT p.").append(columnToUpdate).append(" as date");
			updateSql.append(" FROM xplansyn.").append(planTableName).append(" p, xplansyn.");
			updateSql.append(bereichTableName).append(" b, xplansyn.").append(tableName);
			updateSql.append(" WHERE '[' || p.xplan_gmlid || ']' = b.xplan_gehoertzuplan");
			updateSql.append(" AND '[' || b.xplan_gmlid ||']' = ? ");
			updateSql.append(") AS subquery WHERE attr_gml_id = ?");

			executeUpdate(conn, updateSql.toString(), bereichTableName, gmlId, bereichReferenceId);
		}
		else {
			LOG.warn("Could not detect type for reference {}", bereichReferenceId);
		}
	}

	private void updateRowInXPlanSynTableWithPlanReference(Connection conn, String gmlId, String planReferenceId,
			String tableName) {
		XPlanType type = detectType(planReferenceId);
		if (type != null) {
			PlanTableMetadata planTableMetadata = PLAN_TABLE_METADATA.get(type);
			String columnToUpdate = planTableMetadata.getDateColumnName();
			String planTableName = planTableMetadata.getPlanTableName();

			StringBuffer updateSql = new StringBuffer();
			updateSql.append("UPDATE xplansyn.").append(tableName);
			updateSql.append(" SET ").append(columnToUpdate).append(" = subquery.date");
			updateSql.append(" FROM ( ");
			updateSql.append("SELECT p.").append(columnToUpdate).append(" as date");
			updateSql.append(" FROM xplansyn.").append(planTableName).append(" p, xplansyn.").append(tableName);
			updateSql.append(" WHERE '[' || p.xplan_gmlid || ']' = ? ");
			updateSql.append(") AS subquery WHERE attr_gml_id = ?");

			executeUpdate(conn, updateSql.toString(), tableName, gmlId, planReferenceId);
		}
		else {
			LOG.info("Could not detect type for reference {}", planReferenceId);
		}
	}

	private void executeUpdate(Connection conn, String updateSql, String tableName, String gmlId, String referenceId) {
		LOG.info("Execute SQL '{}' with parameters '{}', '{}'", updateSql, referenceId, gmlId);
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(updateSql.toString());
			stmt.setString(1, referenceId);
			stmt.setString(2, gmlId);
			stmt.executeUpdate();
			conn.commit();
			LOG.info("Update of table '{}' data finished!", tableName);
		}
		catch (SQLException e) {
			LOG.error("An exception occurred during update of the '" + tableName + "' data", e);
			rollback(conn);
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private XPlanType detectType(String referenceId) {
		for (Entry<XPlanType, PlanTableMetadata> planTableMetadataEntry : PLAN_TABLE_METADATA.entrySet()) {
			PlanTableMetadata planTableMetadata = planTableMetadataEntry.getValue();
			String planTableName = planTableMetadata.getPlanTableName();
			String bereichTableName = planTableMetadata.getBereichTableName();
			if (referenceId.toLowerCase().contains(planTableName)
					|| referenceId.toLowerCase().contains(bereichTableName))
				return planTableMetadataEntry.getKey();
		}
		return null;
	}

	private boolean isPlanTable(String tableName) {
		for (PlanTableMetadata planTableMetadata : PLAN_TABLE_METADATA.values()) {
			if (planTableMetadata.getPlanTableName().equalsIgnoreCase(tableName))
				return true;
		}
		return false;
	}

	private String findPlanReferenceColumnName(Connection conn, DatabaseMetaData metaData, String tableName)
			throws SQLException {
		String expectedColumnName = "xplan_gehoertzuplan";
		boolean hasColumnWithName = hasColumnWithName(conn, metaData, tableName, expectedColumnName);
		if (hasColumnWithName)
			return expectedColumnName;
		return null;
	}

	private List<String> findBereichReferenceColumnNames(Connection conn, DatabaseMetaData metaData, String tableName)
			throws SQLException {
		List<String> bereichReferenceColumnNames = new ArrayList<String>();
		for (String columnNameCandidate : BEREICH_COLUMN_NAMES) {
			boolean hasColumn = hasColumnWithName(conn, metaData, tableName, columnNameCandidate);
			if (hasColumn)
				bereichReferenceColumnNames.add(columnNameCandidate);
		}
		return bereichReferenceColumnNames;
	}

	private boolean hasColumnWithName(Connection conn, DatabaseMetaData metaData, String tableName, String columnName)
			throws SQLException {
		ResultSet columns = metaData.getColumns(conn.getCatalog(), "xplansyn", tableName, columnName);
		return columns.next();
	}

}
