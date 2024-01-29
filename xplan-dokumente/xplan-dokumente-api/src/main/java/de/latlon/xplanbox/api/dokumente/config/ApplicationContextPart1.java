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
package de.latlon.xplanbox.api.dokumente.config;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLAN_MANAGER_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;

import org.deegree.commons.config.DeegreeWorkspace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.workspace.WorkspaceException;

/**
 * Simple workaround for circular dependencies in Spring beans.
 */
@Configuration
public class ApplicationContextPart1 {

	@Bean
	public ManagerWorkspaceWrapper managerWorkspaceWrapper() throws WorkspaceException {
		DeegreeWorkspace managerWorkspace = instantiateWorkspace(DEFAULT_XPLAN_MANAGER_WORKSPACE);
		return new ManagerWorkspaceWrapper(managerWorkspace);
	}

}
