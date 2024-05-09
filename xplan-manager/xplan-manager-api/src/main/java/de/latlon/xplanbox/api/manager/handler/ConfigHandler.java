/*-
 * #%L
 * xplan-manager-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationType;
import de.latlon.xplanbox.api.commons.handler.SystemConfigHandler;
import de.latlon.xplanbox.api.manager.config.DefaultValidationConfiguration;
import de.latlon.xplanbox.api.manager.config.ManagerApiConfiguration;
import de.latlon.xplanbox.api.manager.v1.model.ManagerSystemConfig;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class ConfigHandler {

	private static final Logger LOG = getLogger(ConfigHandler.class);

	@Autowired
	private SystemConfigHandler systemConfigHandler;

	@Autowired
	private ManagerConfiguration managerConfiguration;

	@Autowired
	private ManagerApiConfiguration managerApiConfiguration;

	public ManagerSystemConfig describeManagerSystem() throws IOException {
		LOG.debug("Generating manager config information");
		String rasterCrs = managerConfiguration.getRasterConfigurationCrs();
		RasterConfigurationType rasterType = managerConfiguration.getRasterConfigurationType();
		DefaultValidationConfiguration defaultValidationConfiguration = managerApiConfiguration
			.getDefaultValidationConfiguration();

		return (ManagerSystemConfig) new ManagerSystemConfig().rasterCrs(rasterCrs)
			.rasterType(rasterType.name())
			.skipSemantisch(defaultValidationConfiguration.isSkipSemantisch())
			.skipGeometrisch(defaultValidationConfiguration.isSkipGeometrisch())
			.skipFlaechenschluss(defaultValidationConfiguration.isSkipFlaechenschluss())
			.skipGeltungsbereich(defaultValidationConfiguration.isSkipGeltungsbereich())
			.skipLaufrichtung(defaultValidationConfiguration.isSkipLaufrichtung())
			.documentUrl(managerConfiguration.getEnvironmentVariableValue("XPLAN_DOCUMENT_URL_PUBLIC"))
			.rulesMetadata(systemConfigHandler.getRulesMetadata())
			.profiles(systemConfigHandler.getProfiles())
			.supportedXPlanGmlVersions(systemConfigHandler.allSupportedVersions())
			.version(parseVersion());
	}

	public String parseVersion() {
		Package thisPackage = getClass().getPackage();
		return thisPackage.getImplementationVersion();
	}

}
