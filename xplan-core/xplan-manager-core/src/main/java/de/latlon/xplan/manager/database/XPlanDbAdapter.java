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

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.ZipEntryWithContent;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.io.WKTWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.MimetypesFileTypeMap;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

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

	public XPlanDbAdapter(ManagerWorkspaceWrapper managerWorkspaceWrapper) {
		this.managerWorkspaceWrapper = managerWorkspaceWrapper;
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

	public XPlanVersionAndPlanStatus selectXPlanMetadata(int id) throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try (Connection conn = managerWorkspaceWrapper.openConnection()) {
			stmt = conn.prepareStatement("SELECT xp_version, planstatus FROM xplanmgr.plans WHERE id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (!rs.next()) {
				throw new PlanNotFoundException(id);
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