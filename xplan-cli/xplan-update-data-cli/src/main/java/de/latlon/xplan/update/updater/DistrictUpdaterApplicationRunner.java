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
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.update.config.ApplicationContext;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

import static de.latlon.xplan.commons.util.FeatureCollectionUtils.retrieveDistrict;

/**
 * Updates the district column in table xplanmgr.plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Import(ApplicationContext.class)
public class DistrictUpdaterApplicationRunner implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DistrictUpdaterApplicationRunner.class);

	@Autowired
	private XPlanDao dao;

	/**
	 * Retrieves all plans from the manager store, parses the district from the plan and
	 * updates the district column in the table xplanmgr.plans.
	 */
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void run(ApplicationArguments args) throws Exception {
		List<XPlan> plans = dao.getXPlanList();
		for (XPlan plan : plans) {
			LOG.info("Update district of plan with id {}", plan.getId());
			FeatureCollection featureCollection = dao.retrieveFeatureCollection(plan);
			XPlanType planType = XPlanType.valueOf(plan.getType());
			String district = retrieveDistrict(featureCollection, planType);
			dao.updateDistrict(plan, district);
		}
	}

}
