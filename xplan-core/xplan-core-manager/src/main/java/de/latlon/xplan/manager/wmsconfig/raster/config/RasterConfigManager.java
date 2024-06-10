/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster.config;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.manager.configuration.ConfigurationException;
import de.latlon.xplan.manager.web.shared.PlanStatus;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface RasterConfigManager {

	void insertRasterLayers(int planId, int moreRecentPlanId, XPlanType type, PlanStatus planStatus,
			PlanStatus newPlanStatus, List<String> rasterIds, Date sortDate)
			throws JAXBException, IOException, ConfigurationException;

	void removeRasterLayers(int planId);

	void removeRasterLayer(int planId, String rasterId) throws ConfigurationException, JAXBException, IOException;

	void updateRasterLayers(int planId, XPlanType type, PlanStatus planStatus, PlanStatus newPlanStatus)
			throws JAXBException, IOException, ConfigurationException;

	void reorderWmsLayers(Map<String, Date> planId2sortDate) throws Exception;

	void reorderWmsLayers(int planId, int moreRecentPlan, PlanStatus planStatus, XPlanType xPlanType)
			throws ConfigurationException, JAXBException, IOException;

	void createConfiguration(String rasterId, String rasterFileName) throws IOException, JAXBException;

}
