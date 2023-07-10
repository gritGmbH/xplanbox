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

import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.core.manager.db.repository.ArtefactRepository;
import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.core.manager.db.repository.PlanwerkWmsMetadataRepository;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.database.XPlanDbAdapter;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import org.deegree.commons.config.DeegreeWorkspace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLAN_MANAGER_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
@Configuration
@ComponentScan(basePackages = { "de.latlon.xplanbox.api.dokumente.handler", "de.latlon.xplanbox.api.dokumente.v1",
		"de.latlon.xplanbox.api.dokumente.service" })
@Import({ JpaContext.class, S3DocumentContext.class })
public class ApplicationContext {

	@Autowired
	private PlanRepository planRepository;

	@Autowired
	private PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository;

	@Autowired
	private ArtefactRepository artefactRepository;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Bean
	public ManagerWorkspaceWrapper managerWorkspaceWrapper(ManagerConfiguration managerConfiguration)
			throws WorkspaceException {
		DeegreeWorkspace managerWorkspace = instantiateWorkspace(DEFAULT_XPLAN_MANAGER_WORKSPACE);
		return new ManagerWorkspaceWrapper(managerWorkspace, managerConfiguration);
	}

	@Bean
	public XPlanDbAdapter xPlanDbAdapter(CategoryMapper categoryMapper) {
		return new XPlanDbAdapter(categoryMapper, planRepository, planwerkWmsMetadataRepository, artefactRepository);
	}

	@Bean
	public XPlanDao xPlanDao(ManagerWorkspaceWrapper managerWorkspaceWrapper, XPlanDbAdapter xPlanDbAdapter) {
		return new XPlanDao(managerWorkspaceWrapper, xPlanDbAdapter, applicationEventPublisher);
	}

	@Bean
	public CategoryMapper categoryMapper(ManagerConfiguration managerConfiguration) {
		return new CategoryMapper(managerConfiguration);
	}

	@Bean
	public ManagerConfiguration managerConfiguration(PropertiesLoader managerPropertiesLoader)
			throws ConfigurationException {
		return new ManagerConfiguration(managerPropertiesLoader);
	}

	@Bean
	public PropertiesLoader propertiesLoader() {
		return new SystemPropertyPropertiesLoader();
	}

	@Bean
	public DokumentenApiConfiguration dokumentenApiConfiguration(PropertiesLoader propertiesLoader)
			throws ConfigurationException {
		return new DokumentenApiConfiguration(propertiesLoader);
	}

}
