/*-
 * #%L
 * xplan-update-data-cli - update of database
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
package de.latlon.xplan.update.tool;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.commons.configuration.SortConfiguration;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.RasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.RasterConfigManagerFactory;
import de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorage;
import de.latlon.xplan.manager.workspace.WorkspaceUtils;
import de.latlon.xplan.update.updater.SortPropertyUpdater;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.tools.CommandUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

import static de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorageFactory.createRasterStorage;

/**
 * Main entry point to update the sort date in databases.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class SortDateUpdateTool {

	private static final Logger LOG = LoggerFactory.getLogger(SortDateUpdateTool.class);

	private static final String OPT_WORKSPACE_NAME = "workspaceName";

	private static final String OPT_CONFIG_DIR = "configurationDirectory";

	public static void main(String[] args) {
		if ((args.length > 0 && (args[0].contains("help") || args[0].contains("?")))) {
			printHelp(initOptions());
		}

		try {
			CommandLine cmdline = new PosixParser().parse(initOptions(), args);
			try {
				String workspaceName = cmdline.getOptionValue(OPT_WORKSPACE_NAME);
				if (workspaceName == null || workspaceName.isEmpty())
					workspaceName = "xplan-manager-workspace";
				String configurationDirectory = cmdline.getOptionValue(OPT_CONFIG_DIR);

				SortDateUpdateTool tool = new SortDateUpdateTool();
				tool.run(workspaceName, configurationDirectory);
				LOG.info("SortDateUpdateTool successfully executed!");
			}
			catch (Exception e) {
				LOG.error("SortDateUpdateTool could not be executed!", e);
			}
		}
		catch (ParseException exp) {
			System.err.println("Could nor parse command line");
			exp.printStackTrace();
		}

	}

	private static Options initOptions() {
		Options opts = new Options();

		Option opt = new Option("w", OPT_WORKSPACE_NAME, true,
				"Default: xplan-manager-workspace. Name of the manager workspace pointing to the database to update "
						+ "(must be located in the deegree workspace directory, usually .deegree)");
		opt.setRequired(false);
		opts.addOption(opt);

		opt = new Option("c", OPT_CONFIG_DIR, true, "the directory containing the manager configuration");
		opt.setRequired(false);
		opts.addOption(opt);

		CommandUtils.addDefaultOptions(opts);
		return opts;
	}

	private static void printHelp(Options options) {
		String help = "Update sort date.";
		CommandUtils.printHelp(options, SortDateUpdateTool.class.getSimpleName(), help, null);
	}

	private void run(String workspaceName, String configurationFilePathVariable) throws Exception {
		DeegreeWorkspace workspace = initWorkspace(workspaceName);
		ManagerConfiguration managerConfiguration = createManagerConfiguration(configurationFilePathVariable);
		ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper(workspace, managerConfiguration);

		XPlanDao xplanDao = createXplanDao(managerConfiguration, managerWorkspaceWrapper);
		SortPropertyReader sortPropertyReader = createSortPropertyReader(managerWorkspaceWrapper);
		XPlanRasterManager xPlanRasterManager = createxPlanRasterManager(managerConfiguration);

		SortPropertyUpdater sortPropertyUpdater = new SortPropertyUpdater(sortPropertyReader, xplanDao,
				xPlanRasterManager);
		sortPropertyUpdater.updateSortProperty();
	}

	private static XPlanRasterManager createxPlanRasterManager(ManagerConfiguration managerConfiguration)
			throws Exception {
		DeegreeWorkspace wmsWorkspace = WorkspaceUtils.instantiateWmsWorkspace(null);
		WmsWorkspaceWrapper wmsWorkspaceWrapper = new WmsWorkspaceWrapper(wmsWorkspace);
		Path dataDirectory = Paths.get(wmsWorkspaceWrapper.getLocation().toURI()).resolve("data");
		RasterStorage rasterStorage = createRasterStorage(managerConfiguration, dataDirectory);
		RasterConfigManager rasterConfigManager = RasterConfigManagerFactory
				.createRasterConfigManager(wmsWorkspaceWrapper, managerConfiguration);
		return new XPlanRasterManager(rasterStorage, rasterConfigManager);
	}

	private SortPropertyReader createSortPropertyReader(ManagerWorkspaceWrapper managerWorkspaceWrapper) {
		SortConfiguration sortConfiguration = createSortConfiguration(managerWorkspaceWrapper.getConfiguration());
		SortPropertyReader sortPropertyReader = new SortPropertyReader(sortConfiguration);
		return sortPropertyReader;
	}

	private static XPlanDao createXplanDao(ManagerConfiguration managerConfiguration,
			ManagerWorkspaceWrapper managerWorkspaceWrapper) {
		CategoryMapper categoryMapper = new CategoryMapper(managerConfiguration);
		return new XPlanDao(managerWorkspaceWrapper, categoryMapper, managerConfiguration);
	}

	private SortConfiguration createSortConfiguration(ManagerConfiguration managerConfiguration) {
		if (managerConfiguration != null)
			return managerConfiguration.getSortConfiguration();
		return new SortConfiguration();
	}

	private ManagerConfiguration createManagerConfiguration(String configurationFilePathVariable)
			throws ConfigurationException {
		Path directoryContainingTheManagerConfig = configurationFilePathVariable != null
				? Paths.get(configurationFilePathVariable) : null;
		PropertiesLoader propertiesLoader = new ConfigurationDirectoryPropertiesLoader(
				directoryContainingTheManagerConfig, ManagerConfiguration.class);
		return new ManagerConfiguration(propertiesLoader);
	}

	private static DeegreeWorkspace initWorkspace(String workspaceName) throws ResourceInitException {
		DeegreeWorkspace workspace = DeegreeWorkspace.getInstance(workspaceName);
		workspace.initAll();
		return workspace;
	}

}
