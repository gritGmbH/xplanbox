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
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.manager.wmsconfig.raster.config.ConfigWriterUtils.detectType;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class DeegreeRasterConfigManager implements RasterConfigManager {

	private static final Logger LOG = LoggerFactory.getLogger(DeegreeRasterConfigManager.class);

	private final ManagerConfiguration managerConfiguration;

	private final WorkspaceRasterThemeManager rasterThemeManager;

	private final WorkspaceRasterLayerManager rasterLayerManager;

	public DeegreeRasterConfigManager(WmsWorkspaceWrapper wmsWorkspaceWrapper,
			ManagerConfiguration managerConfiguration) {
		this.rasterThemeManager = new WorkspaceRasterThemeManager(wmsWorkspaceWrapper);
		this.managerConfiguration = managerConfiguration;
		this.rasterLayerManager = new WorkspaceRasterLayerManager(wmsWorkspaceWrapper.getLocation(),
				managerConfiguration.getRasterConfigurationType(), managerConfiguration.getRasterConfigurationCrs());
	}

	@Override
	public void insertRasterLayers(int planId, int moreRecentPlanId, XPlanType type, PlanStatus planStatus,
			PlanStatus newPlanStatus, List<String> rasterIds, Date sortDate)
			throws JAXBException, IOException, ConfigurationException {
		String statusType = detectType(type, planStatus);
		if (newPlanStatus != null) {
			String newStatusType = detectType(type, newPlanStatus);
			rasterThemeManager.moveLayers(statusType, newStatusType, planId);
			statusType = newStatusType;
		}
		if (sortDate != null) {
			rasterThemeManager.insertLayersRightBefore(statusType, managerConfiguration.getRasterConfigurationCrs(),
					rasterIds, moreRecentPlanId);
		}
		else {
			rasterThemeManager.insertLayersAtBeginning(statusType, managerConfiguration.getRasterConfigurationCrs(),
					rasterIds);
		}
	}

	/**
	 * Removes the configuration of the plan with the given id.
	 * @param planId the id of the plan to remove, should not be <code>null</code>
	 */
	@Override
	public void removeRasterLayers(int planId) {
		try {
			for (String type : WmsWorkspaceWrapper.supportedTypes) {
				rasterThemeManager.removeLayersForPlan(type, planId);
			}
			rasterLayerManager.deleteDataFilesAndRasterConfigurations(planId);
		}
		catch (Exception e) {
			LOG.trace("Configuration of the plan with id " + planId + " failed.!", e);
			LOG.error("Modifizierung der Themes-Datei fehlgeschlagen: {}", e.getMessage());
		}
	}

	/**
	 * Removes the configuration of the plan with the given id.
	 * @param planId the id of the plan to remove, should not be <code>null</code>
	 */
	@Override
	public void removeRasterLayer(int planId, String rasterId)
			throws ConfigurationException, JAXBException, IOException {
		for (String type : WmsWorkspaceWrapper.supportedTypes) {
			rasterThemeManager.removeLayersForPlan(type, planId, rasterId);
		}
		rasterLayerManager.deleteDataFilesAndRasterConfigurations(planId, rasterId);
	}

	/**
	 * Updates the existing raster layer configuration, if the planStatus and
	 * newPlanStatus differs, the configuration is moved from one theme to another.
	 * @param planId the id of the plan, never <code>null</code>
	 * @param type the type of the plan, never <code>null</code>
	 * @param planStatus the status of the plan, never <code>null</code>
	 * @param newPlanStatus status of the plan after update, may be <code>null</code>
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	@Override
	public void updateRasterLayers(int planId, XPlanType type, PlanStatus planStatus, PlanStatus newPlanStatus)
			throws JAXBException, IOException, ConfigurationException {
		String statusType = detectType(type, planStatus);
		if (newPlanStatus != null) {
			String newStatusType = detectType(type, newPlanStatus);
			rasterThemeManager.moveLayers(statusType, newStatusType, planId);
		}
	}

	/**
	 * Reorders the wms layer configuration by the passed sort dates.
	 * @param planId2sortDate plan ids mapped to the new sort date, never
	 * <code>null</code>
	 * @throws Exception
	 */
	@Override
	public void reorderWmsLayers(Map<String, Date> planId2sortDate) throws Exception {
		rasterThemeManager.reorderWmsLayers(planId2sortDate, managerConfiguration.getRasterConfigurationCrs());
	}

	/**
	 * Reorders the wms layer configuration of the raster configurations of plan with the
	 * passed id planId.
	 * @param planId of the plan to reorder
	 * @param moreRecentPlan the id of the more recent plan
	 * @param planStatus
	 * @param xPlanType
	 * @throws Exception
	 */
	@Override
	public void reorderWmsLayers(int planId, int moreRecentPlan, PlanStatus planStatus, XPlanType xPlanType)
			throws ConfigurationException, JAXBException, IOException {
		String statusType = detectType(xPlanType, planStatus);
		rasterThemeManager.reorderWmsLayers(planId, moreRecentPlan, statusType);
	}

	@Override
	public void createConfiguration(String rasterId, String rasterFileName) throws IOException, JAXBException {
		rasterLayerManager.createRasterConfigurations(rasterId, rasterFileName,
				managerConfiguration.getRasterLayerMinScaleDenominator(),
				managerConfiguration.getRasterLayerMaxScaleDenominator());
	}

}
