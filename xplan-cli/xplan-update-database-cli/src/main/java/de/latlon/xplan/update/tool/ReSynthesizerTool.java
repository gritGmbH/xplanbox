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
package de.latlon.xplan.update.tool;

import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.commons.feature.SortPropertyReader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.ReSynthesizer;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
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

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Tool to re-synthesize all or single plans.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ReSynthesizerTool {

	private static final Logger LOG = LoggerFactory.getLogger(ReSynthesizerTool.class);

	private static final String OPT_WORKSPACE_NAME = "workspaceName";

	private static final String OPT_CONFIG_DIR = "configurationDirectory";

	private static final String OPT_MGR_ID = "mgrId";

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
				String mgrId = cmdline.getOptionValue(OPT_MGR_ID);

				ReSynthesizerTool tool = new ReSynthesizerTool();
				tool.run(workspaceName, configurationDirectory, mgrId);
			}
			catch (Exception e) {
				LOG.error("ReSynthesizerTool could not be executed!", e);
			}
		}
		catch (ParseException exp) {
			System.err.println("Could not parse command line");
			exp.printStackTrace();
		}

	}

	private void run(String workspaceName, String configurationDirectory, String mgrId) throws Exception {
		ReSynthesizer reSynthesizer = createReSynthesizer(workspaceName, configurationDirectory);
		if (mgrId == null) {
			reSynthesizer.reSynthesize();
		}
		else {
			try {
				int mgrIdInt = Integer.parseInt(mgrId);
				reSynthesizer.reSynthesize(mgrIdInt);
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid manager id (parameter -i), '" + mgrId + "' is not an integer value");
			}
		}
	}

	private ReSynthesizer createReSynthesizer(String workspaceName, String configurationDirectory)
			throws ResourceInitException, ConfigurationException {
		DeegreeWorkspace workspace = initWorkspace(workspaceName);
		ManagerConfiguration managerConfiguration = createManagerConfiguration(configurationDirectory);
		XPlanDao xplanDao = createXplanDao(workspace, managerConfiguration);
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(
				managerConfiguration.getSynthesizerConfigurationDirectory());
		SortPropertyReader sortPropertyReader = new SortPropertyReader(managerConfiguration.getSortConfiguration());
		return new ReSynthesizer(xplanDao, xPlanSynthesizer, sortPropertyReader);
	}

	private static DeegreeWorkspace initWorkspace(String workspaceName) throws ResourceInitException {
		DeegreeWorkspace workspace = DeegreeWorkspace.getInstance(workspaceName);
		File location = workspace.getLocation();
		LOG.info("Initialise Workspace " + location);
		workspace.initAll();
		return workspace;
	}

	private static XPlanDao createXplanDao(DeegreeWorkspace workspace, ManagerConfiguration managerConfiguration) {
		CategoryMapper categoryMapper = new CategoryMapper(managerConfiguration);
		ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper(workspace, managerConfiguration);
		return new XPlanDao(managerWorkspaceWrapper, categoryMapper, managerConfiguration);
	}

	private static ManagerConfiguration createManagerConfiguration(String configurationFilePathVariable)
			throws ConfigurationException {
		Path file = configurationFilePathVariable != null ? Paths.get(configurationFilePathVariable) : null;
		ConfigurationDirectoryPropertiesLoader loader = new ConfigurationDirectoryPropertiesLoader(file);
		return new ManagerConfiguration(loader);
	}

	private static Options initOptions() {
		Options opts = new Options();

		Option opt = new Option("w", OPT_WORKSPACE_NAME, true,
				"Default: xplan-manager-workspace. Name of the manager workspace pointing to the database to update "
						+ "(Must be located in the deegree workspace directory, usually .deegree in the user home directory. If the workspaces is not located there, the system property DEEGREE_WORKSPACE_ROOT=/path/to/directory/containg/workspace must be set.)");
		opt.setRequired(false);
		opts.addOption(opt);

		opt = new Option("c", OPT_CONFIG_DIR, true,
				"The directory containing the manager configuration, e.g. /path/to/directory/xplan-manager-config");
		opt.setRequired(true);
		opts.addOption(opt);

		opt = new Option("i", OPT_MGR_ID, true,
				"The ID of a plan in the XPlanManager (manager-ID) of the plan to re-synthesize. If missing all plans are re-synthesized");
		opt.setRequired(false);
		opts.addOption(opt);

		CommandUtils.addDefaultOptions(opts);
		return opts;
	}

	private static void printHelp(Options options) {
		String help = "Reads the XPlanGML and stores the re-synthesized in the xplansyn schema.";
		CommandUtils.printHelp(options, "reSynthesizer", help, null);
	}

}