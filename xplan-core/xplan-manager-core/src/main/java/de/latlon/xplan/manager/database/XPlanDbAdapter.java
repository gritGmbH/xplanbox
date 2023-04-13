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

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.ZipEntryWithContent;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.export.DatabaseXPlanArtefactIterator;
import de.latlon.xplan.manager.web.shared.AdditionalPlanData;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.AbstractReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;
import org.apache.commons.io.IOUtils;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.Geometry;
import org.deegree.geometry.io.WKTReader;
import org.deegree.geometry.io.WKTWriter;
import org.locationtech.jts.io.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static de.latlon.xplan.commons.archive.XPlanArchiveCreator.MAIN_FILE;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveAdditionalTypeWert;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;
import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveRechtsstandWert;
import static de.latlon.xplan.manager.database.ArtefactType.RASTERBASIS;
import static de.latlon.xplan.manager.database.ArtefactType.XPLANGML;
import static de.latlon.xplan.manager.database.DatabaseUtils.closeQuietly;
import static de.latlon.xplan.manager.web.shared.PlanStatus.FESTGESTELLT;
import static org.apache.commons.io.IOUtils.copyLarge;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanDbAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanDbAdapter.class);

	private final ManagerWorkspaceWrapper managerWorkspaceWrapper;

	private final CategoryMapper categoryMapper;

	public XPlanDbAdapter(ManagerWorkspaceWrapper managerWorkspaceWrapper, CategoryMapper categoryMapper) {
		this.managerWorkspaceWrapper = managerWorkspaceWrapper;
		this.categoryMapper = categoryMapper;
	}

	public int insert(XPlanArchive archive, XPlanFeatureCollection fc, FeatureCollection synFc, PlanStatus planStatus,
			Date beginValidity, Date endValidity, Date sortDate, String internalId, List<String> wfsFeatureIds)
			throws Exception {
		Connection conn = null;
		try {
			LOG.info("Insert XPlan in XPlanDB");
			conn = managerWorkspaceWrapper.openConnection();
			conn.setAutoCommit(false);
			int planId = insertMetadata(conn, archive, fc, synFc, wfsFeatureIds, planStatus, beginValidity, endValidity,
					sortDate, internalId);
			insertBereiche(conn, planId, synFc);
			conn.commit();
			return planId;
		}
		catch (AmbiguousBereichNummernException e) {
			throw e;
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
		finally {
			closeQuietly(conn);
		}
	}

	public void insertArtefacts(XPlanFeatureCollection xPlanFeatureCollection, XPlanArchive archive, int planId)
			throws Exception {
		Connection conn = null;
		try {
			LOG.info("Insert XPlan in XPlanDB");
			conn = managerWorkspaceWrapper.openConnection();
			conn.setAutoCommit(false);
			PreparedStatement stmt = null;
			List<ZipEntryWithContent> archiveEntries = xPlanFeatureCollection.getArchiveEntries(archive);
			int i = 0;
			for (ZipEntryWithContent archiveEntry : archiveEntries) {
				long begin = System.currentTimeMillis();
				String name = archiveEntry.getName();
				LOG.info(String.format("- Einfügen von XPlan-Artefakt '%s'...", name));
				try {
					InputStream is = archiveEntry.retrieveContentAsStream();
					String mimetype = getArtefactMimeType(name);
					String insertStatement = "INSERT INTO xplanmgr.artefacts (plan,filename,data,num,mimetype,artefacttype)"
							+ " VALUES (?,?,?,?,?,?::xplanmgr.artefacttype)";
					stmt = conn.prepareStatement(insertStatement);
					stmt.setInt(1, planId);
					stmt.setString(2, name);
					stmt.setBytes(3, createZipArtefact(is));
					stmt.setInt(4, i++);
					stmt.setString(5, mimetype);
					stmt.setString(6, detectArtefactType(xPlanFeatureCollection, archiveEntry));
					stmt.executeUpdate();
					stmt.close();
					is.close();
					long elapsed = System.currentTimeMillis() - begin;
					LOG.info("OK [" + elapsed + " ms]");
				}
				catch (IOException e) {
					throw new Exception("Fehler beim Lesen des Archiv-Eintrags: " + e.getLocalizedMessage(), e);
				}
				catch (SQLException e) {
					throw new Exception("Fehler beim Einfügen in DB: " + e.getLocalizedMessage(), e);
				}
				finally {
					closeQuietly(stmt);
				}
			}
			conn.commit();
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
		finally {
			closeQuietly(conn);
		}
	}

	public void insertOrReplacePlanWerkWmsMetadata(int planId, String title, String resourceIdentifier,
			String datasetMetadataUrl, String serviceMetadataUrl) throws Exception {
		Connection conn = null;
		try {
			LOG.info("Insert PlanWerkWmsMetadata");
			conn = managerWorkspaceWrapper.openConnection();
			conn.setAutoCommit(false);

			insertOrReplacePlanWerkWmsMetadata(conn, planId, title, resourceIdentifier, datasetMetadataUrl,
					serviceMetadataUrl);
			conn.commit();
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
		finally {
			closeQuietly(conn);
		}
	}

	public void deletePlan(int planId) throws Exception {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = managerWorkspaceWrapper.openConnection();
			conn.setAutoCommit(false);

			LOG.info("- Entferne XPlan " + planId + " aus der Manager-DB...");
			stmt = conn.prepareStatement("DELETE FROM xplanmgr.plans WHERE id=?");
			stmt.setInt(1, planId);
			stmt.executeUpdate();
			conn.commit();
			LOG.info("OK");
		}
		catch (Exception e) {
			throw new Exception("Fehler beim Löschen des Plans: " + e.getMessage() + ".", e);
		}
		finally {
			closeQuietly(conn, stmt, rs);
		}
	}

	/**
	 * @param oldXplan the {@link XPlan} describing the plan before update, never
	 * <code>null</code>
	 * @param newAdditionalPlanData of the {@link XPlan} with the updated values, never
	 * <code>null</code>
	 * @param fc the edited feature collection, never <code>null</code>
	 * @param synFc the edited feature collection with synthesized features, never
	 * <code>null</code>
	 * @param planArtefact the edited xplan gml, never <code>null</code>
	 * @param sortDate the date added to syn feature collection, may be <code>null</code>
	 * @param removedRefs
	 * @throws Exception
	 */
	public void update(XPlan oldXplan, AdditionalPlanData newAdditionalPlanData, XPlanFeatureCollection fc,
			FeatureCollection synFc, byte[] planArtefact, XPlanToEdit xPlanToEdit, Date sortDate,
			List<File> uploadedArtefacts, Set<String> removedRefs) throws Exception {
		Connection conn = null;
		try {
			LOG.info("Update XPlan");

			conn = managerWorkspaceWrapper.openConnection();
			conn.setAutoCommit(false);

			updatePlanMetadata(conn, oldXplan, newAdditionalPlanData, fc, synFc, sortDate);
			updatePlanArtefact(conn, oldXplan, planArtefact);
			updateArtefacts(conn, oldXplan, fc, xPlanToEdit, uploadedArtefacts, removedRefs);

			long begin = System.currentTimeMillis();
			LOG.info("- Persistierung...");
			conn.commit();
			long elapsed = System.currentTimeMillis() - begin;
			LOG.info("OK [" + elapsed + " ms].");
		}
		catch (Exception e) {
			if (conn != null)
				conn.rollback();
			throw new Exception("Fehler beim Einfügen: " + e.getMessage(), e);
		}
		finally {
			closeQuietly(conn);
		}
	}

	public void updateFids(int planId, List<String> fids) throws Exception {
		PreparedStatement stmt = null;
		try (Connection conn = managerWorkspaceWrapper.openConnection()) {
			conn.setAutoCommit(false);
			updateFeatureMetadata(conn, fids, planId);
			LOG.info("OK");
		}
		catch (Exception e) {
			LOG.error("Fehler beim Aktualiseren der Features. Ein Rollback wird durchgeführt.", e);
			throw new Exception("Fehler beim Aktualiseren des Plans: " + e.getMessage() + ".", e);
		}
		finally {
			closeQuietly(stmt);
		}
	}

	/**
	 * Updates the wmsSortDate column of all tables in the syn schema and in
	 * xplanmgr.plans table.
	 * @param sortDate the new sort date, may be <code>null</code>
	 * @param plan the plan to update, never <code>null</code>
	 * @throws Exception
	 */
	public void updateSortProperty(Date sortDate, XPlan plan) throws Exception {
		Connection conn = null;
		try {
			conn = managerWorkspaceWrapper.openConnection();
			updateSortPropertyInSynSchema(sortDate, plan, conn);
			updateSortPropertyInMgrSchema(sortDate, plan, conn);
		}
		catch (Exception e) {
			conn.rollback();
		}
		finally {
			closeQuietly(conn);
		}
	}

	/**
	 * Updates the district column of the table xplanmgr.plans.
	 * @param plan the plan to update, never <code>null</code>
	 * @param district the new district, may be <code>null</code>
	 * @throws Exception
	 */
	public void updateDistrict(XPlan plan, String district) throws Exception {
		Connection conn = null;
		try {
			conn = managerWorkspaceWrapper.openConnection();
			updateDistrictInMgrSchema(conn, plan, district);
		}
		catch (Exception e) {
			conn.rollback();
		}
		finally {
			closeQuietly(conn);
		}
	}

	/**
	 * Updates the district column of the table xplanmgr.plans.
	 * @param plan the plan to update, never <code>null</code>
	 * @param bereiche the bereiche, never <code>null</code>
	 * @throws Exception
	 */
	public void updateBereiche(XPlan plan, List<Bereich> bereiche) throws Exception {
		Connection conn = null;
		try {
			conn = managerWorkspaceWrapper.openConnection();
			updateBereichInMgrSchema(conn, plan, bereiche);
		}
		catch (Exception e) {
			conn.rollback();
		}
		finally {
			closeQuietly(conn);
		}
	}

	/**
	 * Updates the column artefacttype of the table xplanmgr.artefacts.
	 * @param planId of the plan to update, never <code>null</code>
	 * @param fileNames the fileNames to update, never <code>null</code>
	 * @param artefactType the artefactType to set, never <code>null</code>
	 * @throws SQLException
	 */
	public void updateArtefacttype(int planId, List<String> fileNames, ArtefactType artefactType) throws SQLException {
		Connection conn = null;
		try {
			conn = managerWorkspaceWrapper.openConnection();
			updateArtefacttype(conn, planId, fileNames, artefactType);
		}
		catch (Exception e) {
			LOG.error("Could not set artefacttype " + artefactType + " for plan with id " + planId + " and files "
					+ fileNames + ".", e);
			conn.rollback();
		}
		finally {
			closeQuietly(conn);
		}
	}

	/**
	 * @param planId of the plan to set the status
	 * @throws SQLException if the sql could not be executed
	 */
	public void setPlanWasInspirePublished(String planId) throws SQLException {
		Connection conn = null;
		try {
			conn = managerWorkspaceWrapper.openConnection();
			executeUpdateInspirePublishedStatus(conn, planId, true);
		}
		finally {
			closeQuietly(conn);
		}
	}

	public XPlanVersionAndPlanStatus selectXPlanMetadata(int planId) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try (Connection conn = managerWorkspaceWrapper.openConnection()) {
			stmt = conn.prepareStatement("SELECT xp_version, planstatus FROM xplanmgr.plans WHERE id=?");
			stmt.setInt(1, planId);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new PlanNotFoundException(planId);
			}
			XPlanVersion version = XPlanVersion.valueOf(rs.getString(1));
			PlanStatus planStatus = retrievePlanStatus(rs.getString(2));
			return new XPlanVersionAndPlanStatus(version, planStatus);
		}
		catch (PlanNotFoundException pe) {
			throw pe;
		}
		catch (Exception e) {
			throw new Exception("Interner-/Konfigurations-Fehler. Kann XPlan-Informationen nicht aus DB lesen: "
					+ e.getLocalizedMessage(), e);
		}
		finally {
			closeQuietly(stmt, rs);
		}
	}

	public Set<String> selectFids(int planId) throws SQLException {
		PreparedStatement stmt = null;
		try (Connection conn = managerWorkspaceWrapper.openConnection()) {
			stmt = conn.prepareStatement("SELECT fid FROM xplanmgr.features WHERE plan=?");
			stmt.setInt(1, planId);
			ResultSet rs = stmt.executeQuery();
			Set<String> ids = new HashSet<>();
			while (rs.next()) {
				ids.add(rs.getString(1));
			}
			return ids;
		}
		catch (SQLException e) {
			throw e;
		}
		finally {
			closeQuietly(stmt);
		}
	}

	/**
	 * Retrieve a list of all XPlans.
	 * @param includeNoOfFeature <code>true</code> if the number of features of each
	 * feature collection should be requested, <code>false</code> otherwise
	 * @param includeNoOfFeature
	 * @return list of XPlans
	 * @throws Exception
	 */
	public List<XPlan> selectAllXPlans(boolean includeNoOfFeature) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try (Connection mgrConn = managerWorkspaceWrapper.openConnection()) {
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append(
					"id, import_date, xp_version, xp_type, name, nummer, gkz, has_raster, release_date, ST_AsText(bbox), sonst_plan_art, planstatus, rechtsstand, district, gueltigkeitBeginn, gueltigkeitEnde, inspirepublished, internalid ");
			if (includeNoOfFeature)
				sql.append(", (SELECT count(fid) FROM xplanmgr.features WHERE id = plan) AS numfeatures ");
			sql.append("FROM xplanmgr.plans");
			stmt = mgrConn.prepareStatement(sql.toString());
			rs = stmt.executeQuery();
			List<XPlan> xplanList = new ArrayList<>();
			while (rs.next()) {
				XPlan xPlan = retrieveXPlan(rs, includeNoOfFeature);
				List<Bereich> bereiche = selectBereiche(mgrConn, getXPlanIdAsInt(xPlan.getId()));
				xPlan.setBereiche(bereiche);
				xplanList.add(xPlan);
			}
			return xplanList;
		}
		catch (Exception e) {
			throw new Exception("Interner-/Konfigurations-Fehler. Kann importierte Pläne nicht auflisten: "
					+ e.getLocalizedMessage(), e);
		}
		finally {
			closeQuietly(stmt, rs);
		}
	}

	/**
	 * Retrieve a single {@link XPlan} by id.
	 * @param planId id of a plan, must not be <code>null</code>
	 * @return a single plan
	 * @throws Exception
	 */
	public XPlan selectXPlanById(int planId) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try (Connection conn = managerWorkspaceWrapper.openConnection()) {
			stmt = conn.prepareStatement("SELECT id, import_date, xp_version, xp_type, name, "
					+ "nummer, gkz, has_raster, release_date, ST_AsText(bbox), "
					+ "sonst_plan_art, planstatus, rechtsstand, district, "
					+ "gueltigkeitBeginn, gueltigkeitEnde, inspirepublished, internalid FROM xplanmgr.plans WHERE id =?");
			stmt.setInt(1, planId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				List<Bereich> bereiche = selectBereiche(conn, planId);
				XPlan xPlan = retrieveXPlan(rs, false);
				xPlan.setBereiche(bereiche);
				return xPlan;
			}
		}
		catch (Exception e) {
			throw new Exception(
					"Interner-/Konfigurations-Fehler. Kann Plan nicht auflisten: " + e.getLocalizedMessage(), e);
		}
		finally {
			closeQuietly(stmt, rs);
		}
		return null;
	}

	public boolean selectPlanWithSameNameAndStatusExists(String planName, String status) {
		Connection conn = null;
		try {
			conn = managerWorkspaceWrapper.openConnection();
			return checkIfPlanWithSameNameAndStatusExists(conn, planName, status);
		}
		catch (Exception e) {
			LOG.warn("Es konnte nicht geprüft werden ob ein Plan mit dem Namen {} und Status {} bereits existiert.",
					planName, status);
		}
		finally {
			closeQuietly(conn);
		}
		return false;
	}

	public List<XPlan> getXPlanByName(String planName) throws Exception {
		String whereClause = "name = ?";
		return selectXPlansWithNameFilter(planName, whereClause);
	}

	public List<XPlan> getXPlansLikeName(String planName) throws Exception {
		String whereClause = "LOWER(name) LIKE ?";
		String planNameLike = "%" + planName.toLowerCase() + "%";
		return selectXPlansWithNameFilter(planNameLike, whereClause);
	}

	/**
	 * retrieves the id of the plan closest in future to the date passed
	 * @param releaseDate minimal release date
	 * @return id of plan with minimal release date
	 * @throws SQLException
	 */
	public String selectXPlanIdOfMoreRecentRasterPlan(Date releaseDate) throws SQLException {
		String planId = null;

		Connection mgrConn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			mgrConn = managerWorkspaceWrapper.openConnection();
			stmt = mgrConn.prepareStatement("SELECT id FROM xplanmgr.plans WHERE has_raster = true AND wmsSortDate=("
					+ "SELECT min(wmsSortDate) FROM xplanmgr.plans "
					+ "WHERE wmsSortDate IS NOT NULL AND wmsSortDate > ?)");
			stmt.setDate(1, new java.sql.Date(releaseDate.getTime()));
			rs = stmt.executeQuery();

			if (rs.next()) {
				planId = rs.getString(1);
			}
		}
		finally {
			closeQuietly(mgrConn, stmt, rs);
		}
		return planId;
	}

	/**
	 * exports a plan
	 * @param planId of plan to export
	 * @return
	 * @throws Exception
	 */
	public DatabaseXPlanArtefactIterator selectAllXPlanArtefacts(int planId) throws Exception {
		Connection conn = managerWorkspaceWrapper.openConnection();
		PreparedStatement stmt = conn
				.prepareStatement("SELECT filename,data FROM xplanmgr.artefacts WHERE plan=? ORDER BY num");
		stmt.setInt(1, planId);
		ResultSet rs = stmt.executeQuery();
		return new DatabaseXPlanArtefactIterator(conn, stmt, rs);
	}

	/**
	 * @param planId the id of the requested plan, <code>null</code>
	 * @return the original plan artefact, never <code>null</code>
	 * @throws Exception
	 */
	public InputStream selectXPlanArtefact(int planId) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try (Connection conn = managerWorkspaceWrapper.openConnection()) {
			stmt = conn
					.prepareStatement("SELECT data FROM xplanmgr.artefacts WHERE plan=? and artefacttype='XPLANGML'");
			stmt.setInt(1, planId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				return unzipArtefact(rs.getBinaryStream(1));
			}
		}
		catch (Exception e) {
			throw new Exception(
					"Fehler beim Rekonstruieren des XPlan-Artefakts '" + MAIN_FILE + "': " + e.getLocalizedMessage(),
					e);
		}
		finally {
			closeQuietly(stmt, rs);
		}
		return null;

	}

	/**
	 * Retrieve internalId by the manager id from xplansyn schema.
	 * @param planId the planId of the plan, never <code>null</code>
	 * @param type the type of the plan, never <code>null</code>
	 * @return the internal id of a plan (if available), <code>null</code> if an error
	 * occurred
	 */
	public String selectInternalId(int planId, XPlanType type) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try (Connection mgrConn = managerWorkspaceWrapper.openConnection()) {
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT xplan_internalid FROM ");
			switch (type) {
				case BP_Plan:
					sqlBuilder.append("xplansyn.xplan_bp_plan");
					break;
				case FP_Plan:
					sqlBuilder.append("xplansyn.xplan_fp_plan");
					break;
				case LP_Plan:
					sqlBuilder.append("xplansyn.xplan_lp_plan");
					break;
				case RP_Plan:
					sqlBuilder.append("xplansyn.xplan_rp_plan");
					break;
				default:
					LOG.warn("Unsupported xplan type " + type);
					return null;

			}
			sqlBuilder.append(" WHERE ");
			sqlBuilder.append(" xplan_mgr_planid = ?");

			LOG.trace("SQL Select to retrieve the internal id: " + sqlBuilder);

			stmt = mgrConn.prepareStatement(sqlBuilder.toString());
			stmt.setInt(1, planId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		}
		catch (Exception e) {
			LOG.warn("Die internalId des Plans mit der ID " + planId + " konnte nicht angefragt werden.");
		}
		finally {
			closeQuietly(stmt, rs);
		}
		return null;
	}

	private int insertMetadata(Connection mgrConn, XPlanArchive archive, XPlanFeatureCollection fc,
			FeatureCollection synFc, List<String> fids, PlanStatus planStatus, Date beginValidity, Date endValidity,
			Date sortDate, String internalId) throws SQLException {

		long begin = System.currentTimeMillis();
		LOG.info("- Einfügen der XPlan-Metadaten (XPLAN_MGR)...");

		int planId = insertPlanMetadata(mgrConn, archive, fc, synFc, planStatus, beginValidity, endValidity, sortDate,
				internalId);
		insertFeatureMetadata(mgrConn, fids, planId);

		long elapsed = System.currentTimeMillis() - begin;
		LOG.info("OK [" + elapsed + " ms].");
		return planId;
	}

	private int insertPlanMetadata(Connection mgrConn, XPlanArchive archive, XPlanFeatureCollection fc,
			FeatureCollection synFc, PlanStatus planStatus, Date beginValidity, Date endValidity, Date sortDate,
			String internalId) throws SQLException {
		String insertPlansSql = "INSERT INTO xplanmgr.plans "
				+ "(import_date, xp_version, xp_type, name, nummer, gkz, has_raster, rechtsstand, "
				+ "release_date, sonst_plan_art, planstatus, district, "
				+ "wmsSortDate, gueltigkeitBeginn, gueltigkeitEnde, internalid, bbox)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,ST_GeometryFromText(?, 4326))";
		PreparedStatement stmt = null;
		int planId;
		try {
			stmt = mgrConn.prepareStatement(insertPlansSql, PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			stmt.setString(2, archive.getVersion().name());
			stmt.setString(3, archive.getType().name());
			stmt.setString(4, fc.getPlanName());
			stmt.setString(5, fc.getPlanNummer());
			stmt.setString(6, fc.getPlanGkz());
			stmt.setBoolean(7, fc.getHasRaster());
			stmt.setString(8, retrieveRechtsstandWert(synFc, archive.getType()));
			stmt.setTimestamp(9, convertToSqlTimestamp(fc.getPlanReleaseDate()));
			stmt.setString(10, retrieveAdditionalTypeWert(synFc, archive.getType()));
			stmt.setString(11, retrievePlanStatusMessage(planStatus));
			stmt.setString(12, retrieveDistrict(fc.getFeatures(), archive.getType()));
			stmt.setTimestamp(13, convertToSqlTimestamp(sortDate));
			stmt.setTimestamp(14, convertToSqlTimestamp(beginValidity));
			stmt.setTimestamp(15, convertToSqlTimestamp(endValidity));
			stmt.setString(16, internalId);
			stmt.setString(17, createWktFromBboxIn4326(fc));

			stmt.executeUpdate();
			planId = detectPlanId(stmt);
		}
		finally {
			closeQuietly(stmt);
		}
		return planId;
	}

	private void insertFeatureMetadata(Connection conn, List<String> fids, int planId) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("INSERT INTO xplanmgr.features (plan,fid,num) VALUES (?,?,?)");
			stmt.setInt(1, planId);
			for (int i = 0; i < fids.size(); i++) {
				stmt.setString(2, fids.get(i));
				stmt.setInt(3, i);
				stmt.addBatch();
			}
			stmt.executeBatch();
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void insertBereiche(Connection conn, int planId, FeatureCollection fc)
			throws SQLException, AmbiguousBereichNummernException {
		PreparedStatement stmt = null;
		List<Bereich> bereiche = FeatureCollectionUtils.retrieveBereiche(fc);
		checkBereichNummern(bereiche);
		for (Bereich bereich : bereiche) {
			long begin = System.currentTimeMillis();
			String nummer = bereich.getNummer();
			LOG.info(String.format("- Einfügen von Bereich '%s'...", nummer));
			try {
				String insertStatement = "INSERT INTO xplanmgr.bereiche (plan,nummer,name) VALUES (?,?,?)";
				stmt = conn.prepareStatement(insertStatement);
				stmt.setInt(1, planId);
				stmt.setString(2, bereich.getNummer());
				stmt.setString(3, bereich.getName());
				stmt.executeUpdate();
				stmt.close();
				long elapsed = System.currentTimeMillis() - begin;
				LOG.info("OK [" + elapsed + " ms]");
			}
			finally {
				closeQuietly(stmt);
			}
		}
	}

	private void insertOrReplacePlanWerkWmsMetadata(Connection conn, int planId, String title,
			String resourceIdentifier, String datasetMetadataUrl, String serviceMetadataUrl) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("DELETE FROM xplanmgr.planwerkwmsmetadata WHERE plan = ?");
			stmt.setInt(1, planId);
			stmt.execute();

			stmt = conn.prepareStatement(
					"INSERT INTO xplanmgr.planwerkwmsmetadata (plan, title, resourceidentifier, datametadataurl, servicemetadataurl) VALUES (?,?,?,?,?)");
			stmt.setInt(1, planId);
			stmt.setString(2, title);
			stmt.setString(3, resourceIdentifier);
			stmt.setString(4, datasetMetadataUrl);
			stmt.setString(5, serviceMetadataUrl);
			stmt.execute();
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void updatePlanMetadata(Connection conn, XPlan xplan, AdditionalPlanData newXPlanMetadata,
			XPlanFeatureCollection fc, FeatureCollection synFc, Date sortDate) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE xplanmgr.plans SET ");
		sql.append("name = ?, ");
		sql.append("rechtsstand = ?, ");
		sql.append("sonst_plan_art = ?, ");
		sql.append("wmsSortDate = ?, ");
		sql.append("gueltigkeitbeginn = ?, ");
		sql.append("gueltigkeitende = ?, ");
		sql.append("planstatus = ? ");
		sql.append("WHERE id = ? ");
		String updateSql = sql.toString();
		PreparedStatement stmt = null;
		try {
			XPlanType type = XPlanType.valueOf(xplan.getType());
			stmt = conn.prepareStatement(updateSql);
			stmt.setString(1, fc.getPlanName());
			stmt.setString(2, retrieveRechtsstandWert(synFc, type));
			stmt.setString(3, retrieveAdditionalTypeWert(synFc, type));
			stmt.setTimestamp(4, convertToSqlTimestamp(sortDate));
			stmt.setTimestamp(5, convertToSqlTimestamp(newXPlanMetadata.getStartDateTime()));
			stmt.setTimestamp(6, convertToSqlTimestamp(newXPlanMetadata.getEndDateTime()));
			stmt.setString(7, retrievePlanStatusMessage(newXPlanMetadata.getPlanStatus()));
			stmt.setObject(8, Integer.parseInt(xplan.getId()));
			LOG.trace("SQL Update XPlanManager Metadata: {}", stmt);
			stmt.executeUpdate();
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void updatePlanArtefact(Connection conn, XPlan xplan, byte[] planArtefact) throws Exception {
		PreparedStatement stmt = null;
		long begin = System.currentTimeMillis();
		LOG.info(String.format("- Aktualisierung von XPlan-Artefakt 'xplan.gml'"));
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE xplanmgr.artefacts SET ");
			sql.append("data = ? ");
			sql.append("WHERE plan = ? AND artefacttype='XPLANGML'");
			String updateSql = sql.toString();
			stmt = conn.prepareStatement(updateSql);
			stmt.setBytes(1, createZipArtefact(new ByteArrayInputStream(planArtefact)));
			stmt.setInt(2, Integer.parseInt(xplan.getId()));
			LOG.trace("SQL Update XPlanManager Artefacts: {}", stmt);
			stmt.executeUpdate();
			stmt.close();
			long elapsed = System.currentTimeMillis() - begin;
			LOG.info("OK [" + elapsed + " ms]");
		}
		catch (SQLException e) {
			throw new Exception("Fehler beim Einfügen in DB: " + e.getLocalizedMessage(), e);
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void updateArtefacts(Connection conn, XPlan xPlan, XPlanFeatureCollection featureCollection,
			XPlanToEdit xPlanToEdit, List<File> uploadedArtefacts, Set<String> removedRefs) throws Exception {
		LOG.info("- Aktualisierung der XPlan-Artefakte von Plan mit ID '{}'", xPlan.getId());
		int planId = getXPlanIdAsInt(xPlan.getId());
		long begin = System.currentTimeMillis();
		try {
			List<String> referenceFileNames = retrieveReferenceFileNames(xPlanToEdit);
			List<String> artefactFileNames = selectArtefactFileNames(conn, planId);
			Map<String, File> artefactsToUpdate = new HashMap<>();
			Map<String, File> artefactsToInsert = new HashMap<>();
			for (String refFileName : referenceFileNames) {
				updateArtefactIfRequired(uploadedArtefacts, artefactFileNames, artefactsToUpdate, artefactsToInsert,
						refFileName);
			}
			executeUpdateArtefacts(conn, planId, artefactsToUpdate);
			executeInsertArtefacts(conn, planId, artefactsToInsert, featureCollection);
			executeDeleteArtefacts(conn, planId, removedRefs);

			long elapsed = System.currentTimeMillis() - begin;
			LOG.info("OK [" + elapsed + " ms]");
		}
		catch (SQLException e) {
			throw new Exception("Fehler beim Aktualisieren der XPlan-Artefakte in DB: " + e.getLocalizedMessage(), e);
		}
	}

	public void updateFeatureMetadata(List<String> fids, int planId) throws SQLException {
		try (Connection conn = managerWorkspaceWrapper.openConnection()) {
			updateFeatureMetadata(fids, planId);
		}
	}

	void updateFeatureMetadata(Connection conn, List<String> fids, int planId) throws SQLException {
		PreparedStatement stmt = null;
		try {
			LOG.info("- Aktualisiere Features von XPlan " + planId + " in der Manager-DB...");
			String sql = "DELETE FROM xplanmgr.features WHERE plan=?";
			LOG.trace("SQL Delete XPlanManager Features: " + sql);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, planId);
			stmt.executeUpdate();
			insertFeatureMetadata(conn, fids, planId);
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void updateArtefactIfRequired(List<File> uploadedArtefacts, List<String> artefactFileNames,
			Map<String, File> artefactsToUpdate, Map<String, File> artefactsToInsert, String refFileName)
			throws Exception {
		LOG.debug("Handle reference '{}'.", refFileName);
		if (refFileName.startsWith("http")) {
			LOG.debug("Found http reference, update of artefacts is not required.");
			return;
		}
		File uploadedFile = retrieveUploadedArtefact(refFileName, uploadedArtefacts);
		boolean isStoredInArtefactsTable = artefactFileNames.contains(refFileName);
		if (uploadedFile != null) {
			LOG.debug("Reference was uploaded, update in DB required.");
			if (isStoredInArtefactsTable) {
				artefactsToUpdate.put(refFileName, uploadedFile);
			}
			else {
				artefactsToInsert.put(refFileName, uploadedFile);
			}
		}
		else if (isStoredInArtefactsTable) {
			LOG.debug("Reference was not changed");
		}
		else {
			throw new Exception("Could not find referenced artefact with name " + refFileName);
		}
	}

	private void updateSortPropertyInSynSchema(Date sortDate, XPlan plan, Connection conn) throws Exception {
		String selectSchemaAndColumnsToModify = "SELECT column_name, table_schema, table_name "
				+ "FROM information_schema.columns " + "WHERE table_schema like 'xplansyn%' "
				+ "AND table_name like 'xplan_%' " + "AND column_name = 'xplan_wmssortdate';";
		PreparedStatement stmt = conn.prepareStatement(selectSchemaAndColumnsToModify);
		ResultSet schemaAndTablesToModify = stmt.executeQuery();

		while (schemaAndTablesToModify.next()) {
			String schemaname = schemaAndTablesToModify.getString("table_schema");
			String tablename = schemaAndTablesToModify.getString("table_name");

			StringBuilder updateSql = new StringBuilder();
			updateSql.append("UPDATE ").append(schemaname).append('.').append(tablename);
			updateSql.append(" SET xplan_wmssortdate = ? ");
			updateSql.append(" WHERE xplan_mgr_planid = ?");

			PreparedStatement updateStmt = conn.prepareStatement(updateSql.toString());
			updateStmt.setDate(1, convertToSqlDate(sortDate));
			updateStmt.setInt(2, getXPlanIdAsInt(plan.getId()));
			LOG.trace("SQL Update XPlan Syn sort date property: " + updateStmt);
			updateStmt.executeUpdate();
			closeQuietly(updateStmt);
		}
		closeQuietly(stmt);
	}

	private void updateSortPropertyInMgrSchema(Date sortDate, XPlan plan, Connection conn) throws Exception {
		StringBuilder updateSql = new StringBuilder();
		updateSql.append("UPDATE xplanmgr.plans");
		updateSql.append(" SET wmssortdate = ? ");
		updateSql.append(" WHERE id = ?");

		PreparedStatement updateStmt = null;
		try {
			updateStmt = conn.prepareStatement(updateSql.toString());
			updateStmt.setDate(1, convertToSqlDate(sortDate));
			updateStmt.setInt(2, getXPlanIdAsInt(plan.getId()));
			LOG.trace("SQL Update XPlan Manager sort date property: " + updateStmt);
			updateStmt.executeUpdate();
		}
		finally {
			closeQuietly(updateStmt);
		}
	}

	private void updateDistrictInMgrSchema(Connection conn, XPlan plan, String district) throws Exception {
		StringBuilder updateSql = new StringBuilder();
		updateSql.append("UPDATE xplanmgr.plans");
		updateSql.append(" SET district = ? ");
		updateSql.append(" WHERE id = ?");

		PreparedStatement updateStmt = null;
		try {
			updateStmt = conn.prepareStatement(updateSql.toString());
			updateStmt.setString(1, district);
			updateStmt.setInt(2, getXPlanIdAsInt(plan.getId()));
			LOG.trace("SQL Update XPlan Manager district column: " + updateStmt);
			updateStmt.executeUpdate();
		}
		finally {
			closeQuietly(updateStmt);
		}
	}

	private void updateBereichInMgrSchema(Connection conn, XPlan plan, List<Bereich> bereiche) throws Exception {
		StringBuilder updateSql = new StringBuilder();
		updateSql.append("INSERT INTO xplanmgr.bereiche");
		updateSql.append(" (plan, nummer, name)");
		updateSql.append(" VALUES (?,?,?)");
		updateSql.append(" ON CONFLICT");
		updateSql.append(" DO NOTHING");

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(updateSql.toString());
			for (Bereich bereich : bereiche) {
				stmt.setInt(1, getXPlanIdAsInt(plan.getId()));
				stmt.setString(2, bereich.getNummer());
				stmt.setString(3, bereich.getName());
				LOG.trace("SQL Update XPlan Manager bereich column: " + stmt);
				stmt.executeUpdate();
			}
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void updateArtefacttype(Connection conn, int planId, List<String> fileNames, ArtefactType artefactType)
			throws Exception {
		StringBuilder updateSql = new StringBuilder();
		updateSql.append("UPDATE xplanmgr.artefacts");
		updateSql.append(" SET artefacttype = ?::xplanmgr.artefacttype");
		updateSql.append(" WHERE");
		updateSql.append(" plan = ? AND");
		updateSql.append(" filename = ?");

		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(updateSql.toString());
			for (String rasterReference : fileNames) {
				stmt.setString(1, artefactType.name());
				stmt.setInt(2, planId);
				stmt.setString(3, rasterReference);
				LOG.trace("SQL Update xplanmgr.artefacts, column artefacttype: " + stmt);
				stmt.executeUpdate();
			}
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void executeUpdateArtefacts(Connection conn, int planId, Map<String, File> artefactsToUpdate)
			throws SQLException, IOException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE xplanmgr.artefacts SET ");
		sql.append("data = ? ");
		sql.append("WHERE plan = ? AND filename = ?");
		String updateSql = sql.toString();
		for (Map.Entry<String, File> artefactToUpdate : artefactsToUpdate.entrySet()) {
			try (FileInputStream fileInputStream = new FileInputStream(artefactToUpdate.getValue())) {
				try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
					stmt.setBytes(1, createZipArtefact(fileInputStream));
					stmt.setInt(2, planId);
					stmt.setString(3, artefactToUpdate.getKey());
					LOG.trace("SQL Update XPlanManager Artefacts: {}", stmt);
					stmt.executeUpdate();
				}
			}
		}
	}

	private void executeInsertArtefacts(Connection conn, int planId, Map<String, File> artefactsToInsert,
			XPlanFeatureCollection featureCollection) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO xplanmgr.artefacts ");
		sql.append("(plan,filename,data,num,mimetype,artefacttype) ");
		sql.append("VALUES (?,?,?,?,?,?::xplanmgr.artefacttype)");
		String insertSql = sql.toString();
		int num = selectNextArtefactNumber(conn, planId);
		for (Map.Entry<String, File> artefactToInsert : artefactsToInsert.entrySet()) {
			try (FileInputStream fileInputStream = new FileInputStream(artefactToInsert.getValue())) {
				String fileName = artefactToInsert.getKey();
				try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
					stmt.setInt(1, planId);
					stmt.setString(2, fileName);
					stmt.setBytes(3, createZipArtefact(fileInputStream));
					stmt.setInt(4, num++);
					stmt.setString(5, getArtefactMimeType(fileName));
					stmt.setString(6, detectNonXPlanGmlArtefactType(featureCollection, fileName));
					LOG.trace("SQL Insert XPlanManager Artefacts: {}", stmt);
					stmt.executeUpdate();
				}
			}
		}
	}

	private void executeDeleteArtefacts(Connection conn, int planId, Set<String> artefactsToDelete) throws Exception {
		LOG.debug("Artefacts to delete: {}", artefactsToDelete);
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM xplanmgr.artefacts ");
		sql.append("WHERE plan = ? AND filename = ? ");
		String deleteSql = sql.toString();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(deleteSql);
			stmt.setInt(1, planId);
			for (String artefactToDelete : artefactsToDelete) {
				stmt.setString(2, artefactToDelete);
				LOG.trace("SQL Delete XPlanManager Artefacts: {}", stmt);
				stmt.addBatch();
			}
			stmt.executeBatch();
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void executeUpdateInspirePublishedStatus(Connection conn, String xplanId, boolean isPiublished)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE xplanmgr.plans SET ");
		sql.append("inspirepublished = ? ");
		sql.append("WHERE id = ? ");
		String updateSql = sql.toString();
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(updateSql);
			stmt.setBoolean(1, isPiublished);
			stmt.setObject(2, Integer.parseInt(xplanId));
			LOG.trace("SQL Update XPlanManager INSPIRE Published status: {}", stmt);
			stmt.executeUpdate();
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private File retrieveUploadedArtefact(String refFileName, List<File> uploadedArtefacts) {
		if (uploadedArtefacts != null) {
			for (File uploadedArtefact : uploadedArtefacts) {
				if (refFileName.equals(uploadedArtefact.getName()))
					return uploadedArtefact;
			}
		}
		return null;
	}

	private List<String> retrieveReferenceFileNames(XPlanToEdit xPlanToEdit) {
		List<String> referenceFileNames = new ArrayList<String>();
		addReferences(referenceFileNames, xPlanToEdit.getTexts());
		addReferences(referenceFileNames, xPlanToEdit.getReferences());
		xPlanToEdit.getRasterBasis().forEach(rasterBasis -> {
			if (rasterBasis.getRasterReferences() != null)
				addReferences(referenceFileNames, rasterBasis.getRasterReferences());
		});
		return referenceFileNames;
	}

	private XPlan retrieveXPlan(ResultSet rs, boolean includeNoOfFeature) throws SQLException {
		int planId = rs.getInt(1);
		Date importDate = rs.getTimestamp(2);
		String xpVersion = rs.getString(3);
		String xpType = rs.getString(4);
		String name = rs.getString(5);
		String number = rs.getString(6);
		String gkz = rs.getString(7);
		Boolean isRaster = rs.getBoolean(8);
		Date releaseDate = convertToDate(rs.getDate(9));
		XPlanEnvelope bbox = createBboxFromWkt(rs.getString(10));
		String sonstPlanArt = rs.getString(11);
		String planStatus = rs.getString(12);
		String rechtsstand = rs.getString(13);
		String district = rs.getString(14);
		Timestamp startDateTime = rs.getTimestamp(15);
		Timestamp endDateTime = rs.getTimestamp(16);
		Boolean isInspirePublished = rs.getBoolean(17);
		String internalId = rs.getString(18);
		int numFeatures = -1;
		if (includeNoOfFeature)
			numFeatures = rs.getInt(19);

		XPlan xPlan = new XPlan((name != null ? name : "-"), Integer.toString(planId), xpType);
		xPlan.setVersion(xpVersion);
		xPlan.setNumber(number != null ? number : "-");
		xPlan.setGkz(gkz);
		xPlan.setNumFeatures(numFeatures);
		xPlan.setRaster(isRaster);
		xPlan.setAdditionalType(sonstPlanArt);
		xPlan.setLegislationStatus(rechtsstand);
		xPlan.setReleaseDate(releaseDate);
		xPlan.setImportDate(importDate);
		xPlan.setBbox(bbox);
		xPlan.setXplanMetadata(createXPlanMetadata(planStatus, startDateTime, endDateTime));
		xPlan.setDistrict(categoryMapper.mapToCategory(district));
		xPlan.setInspirePublished(isInspirePublished);
		xPlan.setInternalId(internalId);
		return xPlan;
	}

	public List<Bereich> selectBereiche(Connection conn, int planId) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			List<Bereich> bereiche = new ArrayList<>();
			stmt = conn.prepareStatement("SELECT nummer, name FROM xplanmgr.bereiche WHERE plan=?");
			stmt.setInt(1, planId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Bereich bereich = new Bereich();
				bereich.setNummer(rs.getString("nummer"));
				bereich.setName(rs.getString("name"));
				bereiche.add(bereich);
			}
			return bereiche;
		}
		finally {
			closeQuietly(stmt, rs);
		}
	}

	private boolean checkIfPlanWithSameNameAndStatusExists(Connection conn, String planName, String status)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(*) > 0 from xplanmgr.plans WHERE name = ? AND planstatus = ?");
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql.toString());
			stmt.setString(1, planName);
			stmt.setString(2, status);
			LOG.trace("SQL Check if plan with same name and status exists: {}", stmt);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next())
				return resultSet.getBoolean(1);
			return false;
		}
		finally {
			closeQuietly(stmt);
		}
	}

	private void addReferences(List<String> referenceFileNames, List<? extends AbstractReference> references) {
		for (AbstractReference ref : references) {
			String reference = ref.getReference();
			if (reference != null && !"".equals(reference))
				referenceFileNames.add(reference);
			String georeference = ref.getGeoReference();
			if (georeference != null && !"".equals(georeference))
				referenceFileNames.add(georeference);
		}
	}

	private int selectNextArtefactNumber(Connection conn, int planId) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT max(num) FROM xplanmgr.artefacts WHERE plan = ?");
			stmt.setInt(1, planId);
			LOG.trace("SQL Select artefacts max num value: {}", stmt);
			rs = stmt.executeQuery();
			if (rs.next()) {
				int maxNum = rs.getInt(1);
				return maxNum + 1;
			}
		}
		finally {
			closeQuietly(stmt, rs);
		}
		return 0;
	}

	private List<String> selectArtefactFileNames(Connection conn, int planId) throws Exception {
		List<String> artefactFileNames = new ArrayList<String>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement("SELECT filename FROM xplanmgr.artefacts WHERE plan=?");
			stmt.setInt(1, planId);
			LOG.trace("SQL Select artefacts filenames: {}", stmt);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String fileName = rs.getString(1);
				if (fileName != null && !"".equals(fileName))
					artefactFileNames.add(fileName);
			}
		}
		catch (Exception e) {
			throw new Exception("Interner-/Konfigurations-Fehler. Kann XPlan-Artefakte nicht aus DB lesen: "
					+ e.getLocalizedMessage(), e);
		}
		finally {
			closeQuietly(stmt, rs);
		}
		return artefactFileNames;
	}

	private List<XPlan> selectXPlansWithNameFilter(String planName, String whereClause) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try (Connection conn = managerWorkspaceWrapper.openConnection()) {
			stmt = conn.prepareStatement("SELECT id, import_date, xp_version, xp_type, name, "
					+ "nummer, gkz, has_raster, release_date, ST_AsText(bbox), "
					+ "sonst_plan_art, planstatus, rechtsstand, district, "
					+ "gueltigkeitBeginn, gueltigkeitEnde, inspirepublished, internalid FROM xplanmgr.plans WHERE "
					+ whereClause);
			stmt.setString(1, planName);
			rs = stmt.executeQuery();
			List<XPlan> xplanList = new ArrayList<>();
			while (rs.next()) {
				XPlan xPlan = retrieveXPlan(rs, false);
				List<Bereich> bereiche = selectBereiche(conn, getXPlanIdAsInt(xPlan.getId()));
				xPlan.setBereiche(bereiche);
				xplanList.add(xPlan);
			}
			return xplanList;
		}
		catch (Exception e) {
			throw new Exception(
					"Interner-/Konfigurations-Fehler. Kann Plan nicht auflisten: " + e.getLocalizedMessage(), e);
		}
		finally {
			closeQuietly(stmt, rs);
		}
	}

	private void checkBereichNummern(List<Bereich> bereiche) throws AmbiguousBereichNummernException {
		List<String> bereichNummern = new ArrayList<>();
		for (Bereich bereich : bereiche) {
			String nummer = bereich.getNummer();
			if (bereichNummern.contains(nummer))
				throw new AmbiguousBereichNummernException(nummer);
			bereichNummern.add(nummer);
		}
	}

	private String getArtefactMimeType(String fileName) {
		MimetypesFileTypeMap mimeMap = new MimetypesFileTypeMap();
		mimeMap.addMimeTypes("text/xml gml xml");
		mimeMap.addMimeTypes("application/pdf pdf");
		mimeMap.addMimeTypes("application/zip zip");
		mimeMap.addMimeTypes("image/jpeg jpg jpeg");
		mimeMap.addMimeTypes("image/png png");
		mimeMap.addMimeTypes("image/tiff tif tiff");
		mimeMap.addMimeTypes("image/ecw ecw");
		mimeMap.addMimeTypes("text/html html");
		mimeMap.addMimeTypes("text/plain txt text");
		return mimeMap.getContentType(fileName);
	}

	private byte[] createZipArtefact(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		GZIPOutputStream gos = new GZIPOutputStream(bos);
		copyLarge(is, gos);
		gos.close();
		return bos.toByteArray();
	}

	private String createWktFromBboxIn4326(XPlanFeatureCollection fc) {
		Envelope bboxIn4326 = fc.getBboxIn4326();
		if (bboxIn4326 != null)
			return WKTWriter.write(bboxIn4326);
		return null;
	}

	private XPlanEnvelope createBboxFromWkt(String bboxAsWkt) {
		if (bboxAsWkt != null && !bboxAsWkt.isEmpty()) {
			try {
				String crs = "epsg:4326";
				WKTReader reader = new WKTReader(CRSManager.lookup(crs));
				Geometry geometry = reader.read(bboxAsWkt);
				Envelope envelope = geometry.getEnvelope();
				return new XPlanEnvelope(envelope.getMin().get0(), envelope.getMin().get1(), envelope.getMax().get0(),
						envelope.getMax().get1(), crs);
			}
			catch (UnknownCRSException | ParseException e) {
				LOG.error("Could not create envelope from " + bboxAsWkt, e);
			}
		}
		return null;
	}

	private AdditionalPlanData createXPlanMetadata(String planStatus, Timestamp startDateTime, Timestamp endDateTime) {
		PlanStatus planStatusAsEnum = null;
		if (planStatus != null)
			planStatusAsEnum = PlanStatus.findByMessage(planStatus);
		return new AdditionalPlanData(planStatusAsEnum, startDateTime, endDateTime);
	}

	private String detectArtefactType(XPlanFeatureCollection xPlanFeatureCollection, ZipEntryWithContent archiveEntry) {
		if (archiveEntry.isXPlanGml()) {
			return XPLANGML.name();
		}
		return detectNonXPlanGmlArtefactType(xPlanFeatureCollection, archiveEntry.getName());
	}

	private int detectPlanId(PreparedStatement stmt) throws SQLException {
		ResultSet generatedKeys = stmt.getGeneratedKeys();
		try {
			if (generatedKeys.next()) {
				return generatedKeys.getInt(1);
			}
			else {
				LOG.error("Detecting the generated planId failed!");
				throw new SQLException("Detecting planId failed, no generated key obtained.");
			}
		}
		finally {
			generatedKeys.close();
		}
	}

	private String detectNonXPlanGmlArtefactType(XPlanFeatureCollection xPlanFeatureCollection, String name) {
		List<ExternalReference> rasterPlanBaseAndUpdateScans = xPlanFeatureCollection.getExternalReferenceInfo()
				.getRasterPlanBaseAndUpdateScans();
		boolean isRasterBasis = rasterPlanBaseAndUpdateScans.stream()
				.anyMatch(rasterPlanBaseAndUpdateScan -> name.equals(rasterPlanBaseAndUpdateScan.getReferenzUrl()));
		if (isRasterBasis)
			return RASTERBASIS.name();
		return null;
	}

	private PlanStatus retrievePlanStatus(String planStatusMessage) {
		if (planStatusMessage != null && planStatusMessage.length() > 0)
			return PlanStatus.findByMessage(planStatusMessage);
		return FESTGESTELLT;
	}

	private String retrievePlanStatusMessage(PlanStatus planStatus) {
		if (planStatus != null)
			return planStatus.getMessage();
		return FESTGESTELLT.getMessage();
	}

	private InputStream unzipArtefact(InputStream zippedStream) throws IOException {
		try (GZIPInputStream is = new GZIPInputStream(zippedStream);
				ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			IOUtils.copy(is, bos);
			return new ByteArrayInputStream(bos.toByteArray());
		}
	}

	private int getXPlanIdAsInt(String planId) throws Exception {
		try {
			return Integer.parseInt(planId);
		}
		catch (NumberFormatException e) {
			throw new Exception("Spezifizierter Wert '" + planId + "' ist keine gültige XPlan-Id (Ganzzahl).", e);
		}
	}

	private Date convertToDate(java.sql.Date dateToConvert) {
		return dateToConvert != null ? new Date(dateToConvert.getTime()) : null;
	}

	private Timestamp convertToSqlTimestamp(Date dateToConvert) {
		if (dateToConvert != null) {
			return new Timestamp(dateToConvert.getTime());
		}
		return null;
	}

	private java.sql.Date convertToSqlDate(Date dateToConvert) {
		if (dateToConvert != null) {
			return new java.sql.Date(dateToConvert.getTime());
		}
		return null;
	}

}