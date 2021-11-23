/*-
 * #%L
 * xplan-api-validator - Modul zur Gruppierung der REST-API
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplanbox.api.validator.handler;

import de.latlon.xplanbox.api.commons.handler.SystemConfigHandler;
import de.latlon.xplanbox.api.commons.v1.model.SystemConfig;
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

	public SystemConfig describeSystem() throws IOException {
		LOG.debug("Generating validator config information");
		return new SystemConfig().version(parseVersion()).rulesMetadata(systemConfigHandler.getRulesMetadata())
				.supportedXPlanGmlVersions(systemConfigHandler.allSupportedVersions());
	}

	public String parseVersion() {
		Package thisPackage = getClass().getPackage();
		return thisPackage.getImplementationVersion();
	}

}
