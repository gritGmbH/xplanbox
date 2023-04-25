/*-
 * #%L
 * xplan-evaluation-schema-synchronize-cli - Datenbankschema für die Auswertung der XPlanGML-Daten
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
package de.latlon.xplan.db;

import de.latlon.xplan.commons.cli.DatabaseUtils;
import de.latlon.xplan.commons.cli.SynchronizeAllExecutor;
import de.latlon.xplan.commons.cli.SynchronizeExecutor;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * Tool to synchronize the evaluation xplansyn schema with the xPlanBox synschema.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SynchronizeEvaluationSchemaTool {

	private static final Logger LOG = LogManager.getLogger(SynchronizeEvaluationSchemaTool.class);

	private enum SYNCTYPE {

		ALL, SYNC

	}

	private static final String LOG_TABLE_NAME = "xplanevaluation.planTableLog";

	private static final String OPT_HOST = "host";

	private static final String OPT_PORT = "port";

	private static final String OPT_DATABSE = "database";

	private static final String OPT_USER = "user";

	private static final String OPT_PASSWORD = "password";

	public static final String OPT_TYPE = "type";

	/**
	 * @param args may be null
	 */
	public static void main(String[] args) {
		if (args == null || args.length == 0
				|| (args.length > 0 && (args[0].contains("help") || args[0].contains("?")))) {
			printHelp(initOptions());
		}
		try {
			CommandLine cmdline = new PosixParser().parse(initOptions(), args);
			try {
				String host = cmdline.getOptionValue(OPT_HOST);
				String port = cmdline.getOptionValue(OPT_PORT);
				String database = cmdline.getOptionValue(OPT_DATABSE);
				String user = cmdline.getOptionValue(OPT_USER);
				String password = cmdline.getOptionValue(OPT_PASSWORD);
				SYNCTYPE synctype = determineSyncType(cmdline);

				SynchronizeEvaluationSchemaTool tool = new SynchronizeEvaluationSchemaTool();
				tool.run(synctype, host, port, database, user, password);
			}
			catch (Exception e) {
				LOG.error("Could not execute tool command line", e);
			}
		}
		catch (ParseException exp) {
			LOG.error("Could not parse command line", exp);
		}
	}

	private static SYNCTYPE determineSyncType(CommandLine cmdline) {
		String syncType = cmdline.getOptionValue(OPT_TYPE);

		if (syncType == null)
			return SYNCTYPE.SYNC;
		return SYNCTYPE.valueOf(syncType.toUpperCase());
	}

	private void run(SYNCTYPE synctype, String host, String port, String database, String user, String password) {
		switch (synctype) {
			case SYNC:
				sync(host, port, database, user, password, (conn) -> {
					EvaluationSchemaSynchronizer synchronizer = new EvaluationSchemaSynchronizer();
					SynchronizeExecutor executer = new SynchronizeExecutor(LOG_TABLE_NAME, synchronizer);
					executer.synchronize(conn);
				});
				break;
			case ALL:
				sync(host, port, database, user, password, (conn) -> {
					EvaluationSchemaSynchronizer synchronizer = new EvaluationSchemaSynchronizer();
					SynchronizeAllExecutor executor = new SynchronizeAllExecutor(LOG_TABLE_NAME, synchronizer);
					executor.synchronizeAll(conn);
				});
				break;
			default:
				LOG.warn("Unsupported sync type: {}", synctype);
		}
	}

	private void sync(String host, String port, String database, String user, String password,
			Consumer<Connection> connectionConsumer) {
		String jdbcUrl = DatabaseUtils.createJdbcUrl(host, port, database);
		try (Connection conn = DriverManager.getConnection(jdbcUrl, user, password)) {
			conn.setAutoCommit(false);
			connectionConsumer.accept(conn);
		}
		catch (SQLException e) {
			LOG.error("Could not open connection: {}", e.getMessage(), e);
		}
	}

	private static Options initOptions() {
		Options opts = new Options();

		Option opt = new Option("t", OPT_TYPE, true,
				"one of 'ALL' or 'SYNC'; 'SYNC' synchronizes plans logged in xplanevaluation.planTableLog (default if missing), 'ALL' synchronizes all available plans");
		opt.setRequired(false);
		opts.addOption(opt);

		opt = new Option("h", OPT_HOST, true, "hostname of the database server");
		opt.setRequired(true);
		opts.addOption(opt);

		opt = new Option("p", OPT_PORT, true, "optional port of the database server");
		opt.setRequired(false);
		opts.addOption(opt);

		opt = new Option("d", OPT_DATABSE, true, "name of the xplanbox database");
		opt.setRequired(true);
		opts.addOption(opt);

		opt = new Option("u", OPT_USER, true, "database user");
		opt.setRequired(true);
		opts.addOption(opt);

		opt = new Option("w", OPT_PASSWORD, true, "database user password");
		opt.setRequired(true);
		opts.addOption(opt);

		opts.addOption("?", "help", false, "print (this) usage information");
		return opts;
	}

	private static void printHelp(Options options) {
		String helpMsg = "Update xplanevaluation syn schema.";
		String toolName = SynchronizeEvaluationSchemaTool.class.getSimpleName();
		HelpFormatter formatter = new HelpFormatter();
		StringWriter helpWriter = new StringWriter();
		StringBuffer helpBuffer = helpWriter.getBuffer();
		PrintWriter helpPrintWriter = new PrintWriter(helpWriter);
		helpPrintWriter.println();
		if (helpMsg != null && helpMsg.length() != 0) {
			formatter.printWrapped(helpPrintWriter, 100, helpMsg);
			helpPrintWriter.println();
		}
		formatter.printUsage(helpPrintWriter, 100, toolName, options);
		helpBuffer.append("\n");
		formatter.printOptions(helpPrintWriter, 100, options, 3, 5);
		System.err.print(helpBuffer.toString());
		System.exit(1);
	}

}
