/*-
 * #%L
 * xplan-validator-wms - XPlanValidatorWMS
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
package de.latlon.xplan.validator.wms.storage;

import de.latlon.xplan.job.validator.workspace.ValidatorWorkspaceWrapper;
import de.latlon.xplan.validator.wms.MapPreviewCreationException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreException;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.protocol.wfs.transaction.action.IDGenMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

/**
 * Implementation of a {@link PlanStorage}, writing the feature collection to a database
 * using the deegree SQLFeatureStore in xplan-valdiator-workspace.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SqlPlanStorage implements PlanStorage {

	private static final Logger LOG = LoggerFactory.getLogger(SqlPlanStorage.class);

	@Autowired
	private ValidatorWorkspaceWrapper validatorWorkspaceWrapper;

	@Override
	public int retrieveUniquePlanid() throws MapPreviewCreationException {
		Connection conn = null;
		try {
			conn = validatorWorkspaceWrapper.openConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT nextval('xplanvalidator.plans_id_seq')");
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		}
		catch (SQLException e) {
			throw new MapPreviewCreationException("Could not select plan id.", e);
		}
		finally {
			close(conn);
		}
		return 0;
	}

	@Override
	public void storeSynFeatureCollection(int planId, FeatureCollection synFeatureCollection)
			throws MapPreviewCreationException {
		FeatureStore featureStore = validatorWorkspaceWrapper.lookupFeatureStore();
		FeatureStoreTransaction transaction = null;
		Connection conn = null;
		try {
			transaction = featureStore.acquireTransaction();
			List<String> insertedIds = transaction.performInsert(synFeatureCollection, IDGenMode.GENERATE_NEW);
			LOG.info("Inserted {} features.", insertedIds.size());
			conn = validatorWorkspaceWrapper.openConnection();
			conn.setAutoCommit(false);
			insertIds(conn, planId, insertedIds);
			commit(transaction, conn);
		}
		catch (FeatureStoreException | SQLException e) {
			rollback(transaction, conn);
			throw new MapPreviewCreationException("Insert of feature collection in XPlanValidatorWMS failed.", e);
		}
		finally {
			close(conn);
		}
	}

	private PreparedStatement insertIds(Connection conn, int planId, List<String> insertedIds) throws SQLException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement("INSERT INTO xplanvalidator.plans (id,import_date) VALUES (?,?)");
			stmt.setInt(1, planId);
			long now = new java.util.Date().getTime();
			stmt.setTimestamp(2, new Timestamp(now));
			stmt.execute();

			stmt = conn.prepareStatement("INSERT INTO xplanvalidator.features (plan,fid) VALUES (?,?)");
			stmt.setInt(1, planId);
			for (String insertedId : insertedIds) {
				stmt.setString(2, insertedId);
				stmt.addBatch();
			}
			stmt.executeBatch();
			return stmt;
		}
		finally {
			close(stmt);
		}
	}

	private void commit(FeatureStoreTransaction transaction, Connection conn) {
		try {
			transaction.commit();
			conn.commit();
		}
		catch (FeatureStoreException | SQLException ex) {
		}
	}

	private void rollback(FeatureStoreTransaction transaction, Connection conn) {
		try {
			if (transaction != null)
				transaction.rollback();
			if (conn != null)
				conn.rollback();
		}
		catch (FeatureStoreException | SQLException ex) {
		}
	}

	private void close(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		}
		catch (SQLException e) {
		}
	}

	private void close(Connection conn) {
		try {
			if (conn != null)
				conn.close();
		}
		catch (SQLException e) {
			LOG.warn("DB connection could not be closed: {}", e.getMessage());
		}
	}

}
