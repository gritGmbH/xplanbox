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
package de.latlon.xplan.manager.wmsconfig.raster.config;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.manager.web.shared.PlanStatus;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class NoConfigRasterConfigManager implements RasterConfigManager {

	@Override
	public void insertRasterLayers(int planId, String moreRecentPlanId, XPlanType type, PlanStatus planStatus,
			PlanStatus newPlanStatus, List<String> rasterIds, Date sortDate) {
	}

	@Override
	public void removeRasterLayers(int planId) {
	}

	@Override
	public void removeRasterLayer(int planId, String rasterId) {
	}

	@Override
	public void updateRasterLayers(int planId, XPlanType type, PlanStatus planStatus, PlanStatus newPlanStatus) {
	}

	@Override
	public void reorderWmsLayers(Map<String, Date> planId2sortDate) {
	}

	@Override
	public void createConfiguration(String rasterId, String rasterFileName) {
	}

}
