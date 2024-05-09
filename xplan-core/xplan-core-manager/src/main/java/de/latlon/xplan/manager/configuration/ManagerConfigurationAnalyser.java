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
package de.latlon.xplan.manager.configuration;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;

/**
 * Checks the configuration of XPlanManager.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerConfigurationAnalyser {

	private final ManagerConfiguration managerConfiguration;

	private final WmsWorkspaceWrapper wmsWorkspace;

	/**
	 * @param managerConfiguration configuration of the manager, never <code>null</code>
	 * @param wmsWorkspace wms workspace never <code>null</code>
	 */
	public ManagerConfigurationAnalyser(ManagerConfiguration managerConfiguration, WmsWorkspaceWrapper wmsWorkspace) {
		this.managerConfiguration = managerConfiguration;
		this.wmsWorkspace = wmsWorkspace;
	}

	/**
	 * Checks the configuration, throws an {@link ConfigurationException} if the
	 * configuration is broken
	 * @throws ConfigurationException if the configuration is broken
	 */
	public void checkConfiguration() throws ConfigurationException {
		String rasterConfigurationCrsFromManagerConfig = getRasterConfigurationCrsFromManagerConfig();
		try {
			wmsWorkspace.checkThemes(rasterConfigurationCrsFromManagerConfig);
		}
		catch (JAXBException | IOException e) {
			throw new ConfigurationException(e);
		}
	}

	private String getRasterConfigurationCrsFromManagerConfig() {
		return managerConfiguration != null ? managerConfiguration.getRasterConfigurationCrs() : null;
	}

}
