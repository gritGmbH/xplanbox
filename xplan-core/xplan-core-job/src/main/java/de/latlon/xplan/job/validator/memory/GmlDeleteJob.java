/*-
 * #%L
 * xplan-core-job - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplan.job.validator.memory;

import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.feature.persistence.FeatureStore;
import org.deegree.feature.persistence.FeatureStoreException;
import org.deegree.feature.persistence.FeatureStoreProvider;
import org.deegree.feature.persistence.FeatureStoreTransaction;
import org.deegree.filter.IdFilter;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.job.validator.memory.InsertedFids.INSERTED_FIDS_KEY;
import static de.latlon.xplan.job.validator.config.JobContext.DEFAULT_DELETE_AFTER_IN_MINUTES;
import static de.latlon.xplan.job.validator.config.JobContext.DELETE_AFTER_KEY;
import static java.util.Calendar.MINUTE;

/**
 * Job to delete GML files.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GmlDeleteJob implements Job {

	private static final Logger LOG = LoggerFactory.getLogger(GmlDeleteJob.class);

	private static final String MEMORY_FEATURESTORE = "xplansyn";

	@Autowired
	private DeegreeWorkspace workspace;

	@Override
	public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
		int deleteAfter = getDeleteAfter(jobExecutionContext);

		try {
			SchedulerContext context = jobExecutionContext.getScheduler().getContext();
			@SuppressWarnings("unchecked")
			List<InsertedFids> insertedFids = (List<InsertedFids>) context.get(INSERTED_FIDS_KEY);
			Calendar deleteBeforeThis = Calendar.getInstance();
			deleteBeforeThis.add(MINUTE, -deleteAfter);
			if (insertedFids != null) {
				List<InsertedFids> deleteCandidates = insertedFids.stream()
					.filter(f -> f.getInsertTime().before(deleteBeforeThis))
					.collect(Collectors.toList());

				deleteCandidates.forEach(f -> deleteGml(f.getFids(), workspace));
				insertedFids.removeAll(deleteCandidates);
				context.put(INSERTED_FIDS_KEY, insertedFids);
			}
		}
		catch (SchedulerException e) {
			LOG.warn("Could not retrieve scheduler context", e);
		}
		LOG.trace("GmlDeleteJob done");
	}

	private void deleteGml(List<String> fidsToDelete, DeegreeWorkspace workspace) {
		LOG.info("Delete {}", fidsToDelete);
		FeatureStore fs = workspace.getNewWorkspace().getResource(FeatureStoreProvider.class, MEMORY_FEATURESTORE);
		FeatureStoreTransaction ta = null;
		try {
			ta = fs.acquireTransaction();
			IdFilter idFilter = new IdFilter(fidsToDelete);
			ta.performDelete(idFilter, null);
			LOG.info("Deleted " + fidsToDelete.size() + " features in memory.");
			ta.commit();
		}
		catch (Exception e) {
			LOG.warn("Could not delete featureCollection", e);
			rollbackQuietly(ta);
		}
	}

	private int getDeleteAfter(JobExecutionContext jobExecutionContext) {
		JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
		if (jobDataMap.get(DELETE_AFTER_KEY) != null)
			return jobDataMap.getInt(DELETE_AFTER_KEY);
		return DEFAULT_DELETE_AFTER_IN_MINUTES;
	}

	private void rollbackQuietly(FeatureStoreTransaction ta) {
		if (ta != null) {
			try {
				ta.rollback();
			}
			catch (FeatureStoreException ex) {
			}
		}
	}

}
