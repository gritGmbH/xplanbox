/*-
 * #%L
 * xplan-update-data-cli - update of database
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
package de.latlon.xplan.update.config;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SystemPropertyPropertiesLoader;
import de.latlon.xplan.core.manager.db.config.JpaContext;
import de.latlon.xplan.core.manager.db.repository.ArtefactRepository;
import de.latlon.xplan.core.manager.db.repository.PlanRepository;
import de.latlon.xplan.core.manager.db.repository.PlanwerkWmsMetadataRepository;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDbAdapter;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import org.deegree.commons.config.DeegreeWorkspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLAN_MANAGER_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;

/**
 * Spring Application Context for initialising xplan-update-data-cli components.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Configuration
@Import(JpaContext.class)
public class CommonContext {

	private static final Logger LOG = LoggerFactory.getLogger(CommonContext.class);

	@Autowired
	@Lazy
	private PlanRepository planRepository;

	@Autowired
	@Lazy
	private PlanwerkWmsMetadataRepository planwerkWmsMetadataRepository;

	@Autowired
	@Lazy
	private ArtefactRepository artefactRepository;

	@Bean
	public ManagerConfiguration managerConfiguration(PropertiesLoader managerPropertiesLoader)
			throws ConfigurationException {
		return new ManagerConfiguration(managerPropertiesLoader);
	}

	@Bean
	public CategoryMapper categoryMapper(ManagerConfiguration managerConfiguration) {
		return new CategoryMapper(managerConfiguration);
	}

	@Bean
	public XPlanDbAdapter xPlanDbAdapter(CategoryMapper categoryMapper) {
		return new XPlanDbAdapter(categoryMapper, planRepository, planwerkWmsMetadataRepository, artefactRepository);
	}

	@Bean
	public PropertiesLoader managerPropertiesLoader(
			@Value("${xplanbox.config:#{environment.XPLANBOX_CONFIG}}") String configFilePath) {
		if (configFilePath != null && !configFilePath.isEmpty()) {
			LOG.info("Using {}/managerConfiguration.properties", configFilePath);
			return new SystemPropertyPropertiesLoader(configFilePath);
		}
		LOG.info("Using etc/managerConfiguration.properties");
		return new ConfigurationDirectoryPropertiesLoader(etcDirectory());
	}

	@Bean
	public ManagerWorkspaceWrapper managerWorkspaceWrapper() throws WorkspaceException {
		DeegreeWorkspace managerWorkspace = instantiateWorkspace(DEFAULT_XPLAN_MANAGER_WORKSPACE);
		return new ManagerWorkspaceWrapper(managerWorkspace);
	}

	private Path etcDirectory() {
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		File jarLocation = new File(path);
		return Paths.get(jarLocation.getParentFile().getParent()).resolve("etc");
	}

}
