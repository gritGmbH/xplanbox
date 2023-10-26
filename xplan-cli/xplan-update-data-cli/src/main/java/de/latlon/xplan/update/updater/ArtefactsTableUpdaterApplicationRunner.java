/*-
 * #%L
 * xplan-update-data-cli - update of database
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
package de.latlon.xplan.update.updater;

import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.update.artefacts.ArtefactsTableUpdater;
import de.latlon.xplan.update.config.ArtefactsTableUpdaterApplicationApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Updates the data from version 6.0 to 7.0: Inserts data to
 * xplanmgr.artefacts.artefacttype
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Import(ArtefactsTableUpdaterApplicationApplicationContext.class)
public class ArtefactsTableUpdaterApplicationRunner implements ApplicationRunner {

	private final Logger LOG = LoggerFactory.getLogger(ArtefactsTableUpdaterApplicationRunner.class);

	@Autowired
	private XPlanDao xplanDao;

	@Autowired
	private ArtefactsTableUpdater artefactsTableUpdater;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		int noOfSuccessfullyUpdatedPlanIds = 0;
		Map<String, String> failedPlans = new HashMap<>();

		List<XPlan> plans = xplanDao.getXPlanList();
		for (XPlan plan : plans) {
			String planId = plan.getId();
			try {
				artefactsTableUpdater.update(plan);
				noOfSuccessfullyUpdatedPlanIds++;
			}
			catch (Exception e) {
				failedPlans.put(planId, e.getMessage());
				LOG.error("Plan with id " + planId + " could not be updated!", e);
			}
		}
		logResult(plans.size(), noOfSuccessfullyUpdatedPlanIds, failedPlans);
	}

	private void logResult(int noOfExecutedPlans, int noOfSuccessfullyUpdatedPlanIds, Map<String, String> failedPlans) {
		LOG.info("ArtefactsTableUpdateTool completely executed!");
		LOG.info("Updated {} of {} plans successfully.", noOfSuccessfullyUpdatedPlanIds, noOfExecutedPlans);
		if (!failedPlans.isEmpty()) {
			LOG.warn("{} of {} plans could not be updated:", failedPlans.size(), noOfExecutedPlans);
			failedPlans.forEach((id, msg) -> LOG.warn("  - ID: {}, failure: {}", id, msg));
		}
	}

}
