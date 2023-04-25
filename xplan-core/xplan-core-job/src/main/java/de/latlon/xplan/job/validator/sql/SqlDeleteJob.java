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
package de.latlon.xplan.job.validator.sql;

import de.latlon.xplan.job.validator.workspace.ValidatorWorkspaceWrapper;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreException;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.filter.IdFilter;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static de.latlon.xplan.job.validator.config.JobContext.DEFAULT_DELETE_AFTER_IN_MINUTES;
import static java.util.Calendar.MINUTE;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SqlDeleteJob implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(SqlDeleteJob.class);

	public static final String DELETE_AFTER_KEY = "DELETE_AFTER_MINUTES";

	@Autowired
	private ValidatorWorkspaceWrapper validatorWorkspaceWrapper;

	@Override
	public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
		LOG.trace("Start SqlDeleteJob...");
		FeatureStore fs = validatorWorkspaceWrapper.lookupFeatureStore();
		Connection conn = null;
		PreparedStatement stmt = null;
		FeatureStoreTransaction transaction = null;
		try {
			transaction = fs.acquireTransaction();
			conn = validatorWorkspaceWrapper.openConnection();
			conn.setAutoCommit(false);
			Calendar deleteBeforeThis = getCalendareDeleteBefore(jobExecutionContext);
			stmt = conn.prepareStatement(
					"SELECT p.id, p.import_date, array_agg(f.fid) as fids FROM xplanvalidator.plans p  LEFT JOIN xplanvalidator.features f ON f.plan = p.id WHERE import_date < ? GROUP BY id");
			stmt.setTimestamp(1, new Timestamp(deleteBeforeThis.getTimeInMillis()));
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				Timestamp importDate = resultSet.getTimestamp(2);
				Array fids = resultSet.getArray(3);
				String[] fidsToDelete = (String[]) fids.getArray();
				deleteGml(transaction, Arrays.asList(fidsToDelete));
				deleteFromPlansAndFeatures(conn, id);
				LOG.info("Deleted features of plan with id {}, inserted at {}.", id, importDate);
			}
			conn.commit();
			transaction.commit();
		}
		catch (SQLException | FeatureStoreException e) {
			LOG.warn("Could not delete featureCollection", e);
			rollback(transaction, conn);
		}
		finally {
			close(stmt);
			close(conn);
		}
		LOG.trace("SqlDeleteJob done");
	}

	private void deleteGml(FeatureStoreTransaction transaction, List<String> fidsToDelete)
			throws FeatureStoreException {
		IdFilter idFilter = new IdFilter(fidsToDelete);
		transaction.performDelete(idFilter, null);
		LOG.info("Deleted {} features.", fidsToDelete.size());
	}

	private void deleteFromPlansAndFeatures(Connection conn, int id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("DELETE FROM xplanvalidator.plans WHERE id = ?");
		stmt.setInt(1, id);
		stmt.execute();
		close(stmt);
	}

	private Calendar getCalendareDeleteBefore(JobExecutionContext jobExecutionContext) {
		int deleteAfter = getDeleteAfter(jobExecutionContext);
		Calendar deleteBeforeThis = Calendar.getInstance();
		deleteBeforeThis.add(MINUTE, -deleteAfter);
		return deleteBeforeThis;
	}

	private int getDeleteAfter(JobExecutionContext jobExecutionContext) {
		JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
		if (jobDataMap.get(DELETE_AFTER_KEY) != null)
			return jobDataMap.getInt(DELETE_AFTER_KEY);
		return DEFAULT_DELETE_AFTER_IN_MINUTES;
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

}
