/*-
 * #%L
 * xplan-update-database-cli - update of database
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
package de.latlon.xplan.update;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.update.DatabaseDataUpdater.UPDATE_VERSION;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.tools.CommandUtils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.update.DatabaseDataUpdater.UPDATE_VERSION.FROM_1_0_to_1_3_1;
import static de.latlon.xplan.update.DatabaseDataUpdater.UPDATE_VERSION.FROM_PRE1_0_to_1_0;
import static java.util.Arrays.asList;

/**
 * Main entry point to update xplan data in databases. Schema must be updated already.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class DatabaseUpdateTool {

	private static final String OPT_WORKSPACE_NAME = "workspaceName";

	private static final String OPT_CONFIG_DIR = "configurationDirectory";

	private static final String OPT_VERSION = "updateVersion";

	public static void main(String[] args) throws ConfigurationException {
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

				List<UPDATE_VERSION> version = parseUpdateVersion(cmdline);

				DatabaseUpdateTool tool = new DatabaseUpdateTool();
				tool.run(workspaceName, configurationDirectory, Collections.emptyList());
			}
			catch (Exception e) {
				e.printStackTrace();
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

		opt = new Option("u", OPT_VERSION, false,
				"update version (CURRENTLY THIS IS IGNORED): only the update from 5.0 to 5.0.2 is supported.");
		opt.setRequired(false);
		opts.addOption(opt);

		CommandUtils.addDefaultOptions(opts);
		return opts;
	}

	private static void printHelp(Options options) {
		String help = "Update database.";
		CommandUtils.printHelp(options, DatabaseUpdateTool.class.getSimpleName(), help, null);
	}

	private void run(String workspaceName, String configurationFilePathVariable, List<UPDATE_VERSION> version)
			throws Exception {
		DeegreeWorkspace workspace = initWorkspace(workspaceName);
		ManagerConfiguration managerConfiguration = createManagerConfiguration(configurationFilePathVariable);
		ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper(workspace, managerConfiguration);
		XPlanDao xplanDao = createXplanDao(managerConfiguration, managerWorkspaceWrapper);
		DatabaseDataUpdater dataUpdater = new DatabaseDataUpdater(xplanDao, managerWorkspaceWrapper);
		dataUpdater.updateData(version);
	}

	private static XPlanDao createXplanDao(ManagerConfiguration managerConfiguration,
			ManagerWorkspaceWrapper managerWorkspaceWrapper) {
		CategoryMapper categoryMapper = new CategoryMapper(managerConfiguration);
		return new XPlanDao(managerWorkspaceWrapper, categoryMapper, managerConfiguration);
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

	private static List<UPDATE_VERSION> parseUpdateVersion(CommandLine cmdline) {
		String updateVersion = cmdline.getOptionValue(OPT_VERSION);
		if (updateVersion != null && !updateVersion.isEmpty()) {
			List<UPDATE_VERSION> updateVersions = new ArrayList<DatabaseDataUpdater.UPDATE_VERSION>();
			String[] versions = updateVersion.split(",");
			for (String version : versions) {
				if ("1".equals(version))
					updateVersions.add(FROM_PRE1_0_to_1_0);
				if ("2".equals(version))
					updateVersions.add(FROM_1_0_to_1_3_1);
			}
			return updateVersions;
		}
		return asList(UPDATE_VERSION.values());

	}

}
