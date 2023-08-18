/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumentenAPI
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
package de.latlon.xplanbox.api.dokumente.handler;

import de.latlon.xplanbox.api.dokumente.v1.model.SystemConfig;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Component
@Singleton
public class ConfigHandler {

	private static final Logger LOG = getLogger(ConfigHandler.class);

	public SystemConfig describeSystem() {
		LOG.debug("Generating XPlanDokumenteAPI config information");
		return new SystemConfig().version(parseVersion());
	}

	public String parseVersion() {
		Package thisPackage = getClass().getPackage();
		return thisPackage.getImplementationVersion();
	}

}
