/*-
 * #%L
 * xplan-update-database-cli - update of database
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
package de.latlon.xplan.update.from_5_0_to_5_0_2;

import de.latlon.xplan.commons.util.FeatureCollectionUtils;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.update.AbstractUpdater;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

/**
 * Updates the data from version 5.0 to 5.0.2: Inserts data to xplanmgr.bereiche
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class UpdaterFrom5_0To5_0_2 extends AbstractUpdater {

	private final Logger LOG = LoggerFactory.getLogger(UpdaterFrom5_0To5_0_2.class);

	/**
	 * @param xplanDao allows access to the database, never <code>null</code>
	 */
	public UpdaterFrom5_0To5_0_2(XPlanDao xplanDao) {
		super(xplanDao);
	}

	@Override
	public void update(Connection conn) throws Exception {
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
