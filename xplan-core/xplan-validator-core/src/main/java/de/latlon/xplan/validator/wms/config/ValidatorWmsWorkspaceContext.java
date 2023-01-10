/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.wms.config;

import de.latlon.xplan.validator.wms.storage.PlanStorage;
import de.latlon.xplan.validator.wms.storage.WorkspacePlanStorage;
import org.deegree.commons.config.DeegreeWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Configuration
public class ValidatorWmsWorkspaceContext {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorWmsWorkspaceContext.class);

	private static final String XPLAN_GML_WMS_WORKSPACE = "xplan-validator-wms-memory-workspace";

	@Bean
	public PlanStorage planStorage() {
		try {
			Path workspaceLocation = Paths.get(DeegreeWorkspace.getWorkspaceRoot()).resolve(XPLAN_GML_WMS_WORKSPACE);
			return new WorkspacePlanStorage(workspaceLocation);
		}
		catch (Exception e) {
			LOG.error("Could not initialise WorkspacePlanStorage. Reason: {}. MapPreview will not be available.",
					e.getMessage(), e);
		}
		return null;
	}

}
