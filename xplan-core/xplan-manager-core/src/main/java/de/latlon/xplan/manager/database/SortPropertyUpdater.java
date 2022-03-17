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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;

/**
 * Updates the sort property.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SortPropertyUpdater {

	private static final Logger LOG = LoggerFactory.getLogger(SortPropertyUpdater.class);

	private final SortPropertyReader sortPropertyReader;

	private final XPlanDao dao;

	private final XPlanRasterManager xPlanRasterManager;

	/**
	 * @param sortPropertyReader used to read the sort property from a feature collection,
	 * never <code>null</code>
	 * @param dao used to access the database, never <code>null</code>
	 * @param xPlanRasterManager used to update the raster configuration, never
	 * <code>null</code>
	 */
	public SortPropertyUpdater(SortPropertyReader sortPropertyReader, XPlanDao dao,
			XPlanRasterManager xPlanRasterManager) {
		this.sortPropertyReader = sortPropertyReader;
		this.dao = dao;
		this.xPlanRasterManager = xPlanRasterManager;
	}

	/**
	 * Retrieves all plans from the manager store, parses the date from the plan with the
	 * help of the {@link SortPropertyReader} and updates the sort property in the syn
	 * schema data and reorders the wms rasterlayers.
	 */
	public void updateSortProperty() throws Exception {
		Map<String, Date> planId2sortDate = updateColumnsInDB();
		updateWmsRasterLayerOrder(planId2sortDate);
	}

	private Map<String, Date> updateColumnsInDB() throws Exception {
		Map<String, Date> planId2sortDate = new HashMap<String, Date>();
		List<XPlan> plans = dao.getXPlanList(false);
		for (XPlan plan : plans) {
			LOG.debug("Update sort column value for plan with id {}", plan.getId());
			FeatureCollection featureCollection = dao.retrieveFeatureCollection(plan);
			XPlanType planType = XPlanType.valueOf(plan.getType());
			XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());
			Date sortDate = sortPropertyReader.readSortDate(planType, version, featureCollection);
			planId2sortDate.put(plan.getId(), sortDate);
			dao.updateSortProperty(sortDate, plan);
		}
		return planId2sortDate;
	}

	private void updateWmsRasterLayerOrder(Map<String, Date> planId2sortDate) throws Exception {
		xPlanRasterManager.reorderWmsLayers(planId2sortDate);
	}

}
