/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.database;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;

/**
 * Updates the district column in table xplanmgr.plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DistrictUpdater {

	private static final Logger LOG = LoggerFactory.getLogger(DistrictUpdater.class);

	private XPlanDao dao;

	/**
	 * @param dao used to access the database, never <code>null</code>
	 */
	public DistrictUpdater(XPlanDao dao) {
		this.dao = dao;
	}

	/**
	 * Retrieves all plans from the manager store, parses the district from the plan and
	 * updates the district column in the table xplanmgr.plans.
	 */
	public void updateDistricts() throws Exception {
		List<XPlan> plans = dao.getXPlanList(false);
		for (XPlan plan : plans) {
			LOG.debug("Update district of plan with id {}", plan.getId());
			FeatureCollection featureCollection = dao.retrieveFeatureCollection(plan);
			XPlanType planType = XPlanType.valueOf(plan.getType());
			XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());
			String district = retrieveDistrict(featureCollection, planType, version);
			dao.updateDistrict(plan, district);
		}
	}

}
