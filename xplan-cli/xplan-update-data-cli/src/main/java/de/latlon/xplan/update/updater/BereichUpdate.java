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

import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Updates the data from version 5.0 to 5.0.2: Inserts data to xplanmgr.bereiche
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class BereichUpdate {

	private final Logger LOG = LoggerFactory.getLogger(BereichUpdate.class);

	private final XPlanDao xplanDao;

	private ManagerWorkspaceWrapper managerWorkspaceWrapper;

	/**
	 * @param xplanDao allows access to the database, never <code>null</code>
	 */
	public BereichUpdate(XPlanDao xplanDao) {
		this.xplanDao = xplanDao;
	}

	/**
	 * Updates data. Schema must be up to date already.
	 * @throws Exception if an error occurred during update
	 */
	public void update() throws Exception {
		List<XPlan> plans = xplanDao.getXPlanList(false);
		for (XPlan plan : plans) {
			update(plan);
		}
	}

	private void update(XPlan plan) throws Exception {
		LOG.info("Update plan with id {}, version {}, type {}", plan.getId(), plan.getVersion(), plan.getType());
		FeatureCollection featureCollection = xplanDao.retrieveFeatureCollection(plan);
		if (featureCollection.isEmpty()) {
			LOG.warn("FeatureCollection is not available! Plan with id {} is skipped.", plan.getId());
			return;
		}
		List<Bereich> bereiche = FeatureCollectionUtils.retrieveBereiche(featureCollection);
		if (bereiche.isEmpty()) {
			LOG.info("Plan with id {} has no bereiche.", plan.getId());
		}
		xplanDao.updateBereiche(plan, bereiche);
	}

}
