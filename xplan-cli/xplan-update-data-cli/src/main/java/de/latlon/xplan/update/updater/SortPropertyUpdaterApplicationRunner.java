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
package de.latlon.xplan.update.updater;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.update.config.SortPropertyUpdaterApplicationContext;
import de.latlon.xplan.update.db.SortPropertyDbUpdater;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Updates the sort property.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Component
@Import(SortPropertyUpdaterApplicationContext.class)
@ComponentScan("de.latlon.xplan.update.db")
public class SortPropertyUpdaterApplicationRunner implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(SortPropertyUpdaterApplicationRunner.class);

	@Autowired
	private SortPropertyReader sortPropertyReader;

	@Autowired
	private XPlanDao dao;

	@Autowired
	private SortPropertyDbUpdater sortPropertyDbUpdater;

	@Autowired
	private XPlanRasterManager xPlanRasterManager;

	/**
	 * Retrieves all plans from the manager store, parses the date from the plan with the
	 * help of the {@link SortPropertyReader} and updates the sort property in the syn
	 * schema data and reorders the wms rasterlayers.
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void run(ApplicationArguments args) throws Exception {
		Map<String, Date> planId2sortDate = updateColumnsInDB();
		updateWmsRasterLayerOrder(planId2sortDate);
		LOG.info("SortDateUpdateTool successfully executed!");
	}

	private Map<String, Date> updateColumnsInDB() throws Exception {
		Map<String, Date> planId2sortDate = new HashMap<String, Date>();
		List<XPlan> plans = dao.getXPlanList();
		for (XPlan plan : plans) {
			LOG.debug("Update sort column value for plan with id {}", plan.getId());
			FeatureCollection featureCollection = dao.retrieveFeatureCollection(plan);
			XPlanType planType = XPlanType.valueOf(plan.getType());
			XPlanVersion version = XPlanVersion.valueOf(plan.getVersion());
			Date sortDate = sortPropertyReader.readSortDate(planType, version, featureCollection);
			planId2sortDate.put(plan.getId(), sortDate);
			sortPropertyDbUpdater.updateSortProperty(sortDate, plan);
		}
		return planId2sortDate;
	}

	private void updateWmsRasterLayerOrder(Map<String, Date> planId2sortDate) throws Exception {
		xPlanRasterManager.reorderWmsLayers(planId2sortDate);
	}

}
