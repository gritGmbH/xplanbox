/*-
 * #%L
 * xplan-transform-cli - Kommandozeilentool fuer die Transformation zwischen XPlanGML Versionen
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.transform.cli;

import de.latlon.xplan.commons.cli.SynchronizeExecutor;
import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.transformation.HaleXplan41ToXplan51Transformer;
import de.latlon.xplan.manager.transformation.XPlanGmlTransformer;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.commons.config.ResourceInitException;
import org.deegree.commons.tools.CommandUtils;
import org.deegree.workspace.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

import static java.nio.file.Files.createDirectory;
import static java.nio.file.Files.createTempDirectory;
import static java.nio.file.Files.exists;
import static java.nio.file.Files.isDirectory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class TransformTool {

	private static final Logger LOG = LoggerFactory.getLogger(TransformTool.class);

	static final String LOG_TABLE_NAME = "xplanmgr.transformToolPlanTableLog";

	private enum TYPE {

		VALIDATE, SYNC, ALL

	}

	private static final String OPT_WORKSPACE_NAME = "workspaceName";

	private static final String OPT_CONFIG_DIR = "configurationDirectory";

	private static final String OPT_TYPE = "type";

	private static final String OPT_OUT_DIR = "output";

	public static void main(String[] args) {
		if (args.length == 0 || (args.length > 0 && (args[0].contains("help") || args[0].contains("?")))) {
			printHelp(initOptions());
		}

		try {
			CommandLine cmdline = new PosixParser().parse(initOptions(), args);
			try {
				String workspaceName = cmdline.getOptionValue(OPT_WORKSPACE_NAME);
				if (workspaceName == null || workspaceName.isEmpty())
					workspaceName = "xplan-manager-workspace";
				String configurationDirectory = cmdline.getOptionValue(OPT_CONFIG_DIR);
				TYPE type = determineType(cmdline);
				Path outDirectory = createOutDirectory(cmdline);

				TransformTool tool = new TransformTool();
				tool.run(workspaceName, configurationDirectory, type, outDirectory);
			}
			catch (Exception e) {
				LOG.error("TransformTool could not be executed!", e);
			}
		}
		catch (ParseException exp) {
			System.err.println("Could not parse command line");
			exp.printStackTrace();
			printHelp(initOptions());
		}
	}

	private void run(String workspaceName, String configurationDirectory, TYPE type, Path outDirectory)
			throws Exception {
		ManagerConfiguration managerConfiguration = createManagerConfiguration(configurationDirectory);
		XPlanDao xPlanDao = createXplanDao(workspaceName, managerConfiguration);
		HaleXplan41ToXplan51Transformer transformer = new HaleXplan41ToXplan51Transformer(
				managerConfiguration.getPathToHaleCli(), managerConfiguration.getPathToHaleProjectDirectory());
		XPlanGmlTransformer xPlanGmlTransformer = new XPlanGmlTransformer(transformer);
		TransformingValidator validator = new TransformingValidator(xPlanDao, xPlanGmlTransformer);
		ManagerWorkspaceWrapper managerWorkspaceWrapper = createManagerWorkspaceWrapper(workspaceName,
				configurationDirectory);
		switch (type) {
		case ALL:
			sync(managerWorkspaceWrapper, (conn) -> {
				TransformationSynchronizer synchronizer = new TransformationSynchronizer(xPlanDao, validator,
						outDirectory);
				TransformAllExecutor allExecuter = new TransformAllExecutor(LOG_TABLE_NAME, synchronizer);
				allExecuter.transformAll(conn);
			});
			break;
		case SYNC:
			sync(managerWorkspaceWrapper, (conn) -> {
				TransformationSynchronizer synchronizer = new TransformationSynchronizer(xPlanDao, validator,
						outDirectory);
				SynchronizeExecutor executer = new SynchronizeExecutor(LOG_TABLE_NAME, synchronizer);
				executer.synchronize(conn);
			});
			break;
		case VALIDATE:
		default:
			ValidateExecutor validateExecutor = new ValidateExecutor(xPlanDao, validator);
			validateExecutor.validateAll(outDirectory);
		}
		LOG.info("Results was written to {}", outDirectory);
	}

	private void sync(ManagerWorkspaceWrapper managerWorkspaceWrapper, Consumer<Connection> connectionConsumer) {
		try (Connection conn = managerWorkspaceWrapper.openConnection()) {
			conn.setAutoCommit(false);
			connectionConsumer.accept(conn);
		}
		catch (SQLException e) {
			LOG.error("Could not open connection: {}", e.getMessage(), e);
		}
	}

	private static Path createOutDirectory(CommandLine cmdline) throws IOException {
		String outDirectory = cmdline.getOptionValue(OPT_OUT_DIR);
		if (outDirectory != null) {
			Path path = Paths.get(outDirectory);
			if (!exists(path)) {
				return createDirectory(path);
			}
			if (isDirectory(path)) {
				return path;
			}
			else {
				throw new IllegalArgumentException("Passed output directory exists but is not a directory");
			}
		}

		return createTempDirectory("validationresults");
	}

	private static TYPE determineType(CommandLine cmdline) {
		String type = cmdline.getOptionValue(OPT_TYPE);
		if (type == null)
			return TYPE.VALIDATE;
		return TYPE.valueOf(type.toUpperCase());
	}

	private static DeegreeWorkspace initWorkspace(String workspaceName) throws ResourceInitException {
		DeegreeWorkspace workspace = DeegreeWorkspace.getInstance(workspaceName);
		File location = workspace.getLocation();
		LOG.info("Initialise Workspace " + location);
		workspace.initAll();
		return workspace;
	}

	private static XPlanDao createXplanDao(String workspaceName, ManagerConfiguration managerConfiguration)
			throws ResourceInitException {
		CategoryMapper categoryMapper = new CategoryMapper(managerConfiguration);
		ManagerWorkspaceWrapper managerWorkspaceWrapper = createManagerWorkspaceWrapper(workspaceName,
				managerConfiguration);
		return new XPlanDao(managerWorkspaceWrapper, categoryMapper, managerConfiguration);
	}

	private static ManagerWorkspaceWrapper createManagerWorkspaceWrapper(String workspaceName,
			String configurationDirectory) throws ResourceInitException, ConfigurationException {
		ManagerConfiguration managerConfiguration = createManagerConfiguration(configurationDirectory);
		DeegreeWorkspace workspace = initWorkspace(workspaceName);
		return new ManagerWorkspaceWrapper(workspace, managerConfiguration);
	}

	private static ManagerWorkspaceWrapper createManagerWorkspaceWrapper(String workspaceName,
			ManagerConfiguration managerConfiguration) throws ResourceInitException {
		DeegreeWorkspace workspace = initWorkspace(workspaceName);
		return new ManagerWorkspaceWrapper(workspace, managerConfiguration);
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
						+ "(must be located in the deegree workspace directory, usually .deegree)");
		opt.setRequired(false);
		opts.addOption(opt);

		opt = new Option("c", OPT_CONFIG_DIR, true, "the directory containing the manager configuration");
		opt.setRequired(true);
		opts.addOption(opt);

		opt = new Option("t", OPT_TYPE, true, "one of 'VALIDATE' (default if missing), 'ALL', 'SYNC':\n"
				+ "   * 'VALIDATE': validates all available XPlanGML 4.1 plans and writes the results\n"
				+ "   * 'ALL' transforms all available XPlanGML 4.1 plans and inserts the valid plans in XPlanWFS 5.1 datastore, plans already available in 5.1 will be removed first\n"
				+ "   * 'SYNC' transforms the XPlanGML 4.1 plans logged in the table " + LOG_TABLE_NAME
				+ " and inserts the valid plans in XPlanWFS 5.1 datastore");
		opt.setRequired(false);
		opts.addOption(opt);

		opt = new Option("f", OPT_OUT_DIR, true,
				"directory to write the validation results into, directory will be created if it not exists (if missing a new tmp directory is created)");
		opt.setRequired(false);
		opts.addOption(opt);

		CommandUtils.addDefaultOptions(opts);
		return opts;
	}

	private static void printHelp(Options options) {
		String help = "Reads all Plans from XPlanWFS 4.1 datastore, transforms them to XPlanGML 5.1 and inserts them in XPlanWFS 5.1 datastore.";
		CommandUtils.printHelp(options, "XPlanTransformCLI", help, null);
	}

}
