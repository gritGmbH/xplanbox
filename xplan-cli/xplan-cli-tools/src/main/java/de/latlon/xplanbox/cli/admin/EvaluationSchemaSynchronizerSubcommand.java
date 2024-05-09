/*-
 * #%L
 * xplan-cli-tools - Kommandozeilenwerkzeuge fuer die xPlanBox
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplanbox.cli.admin;

import de.latlon.xplan.commons.cli.DatabaseUtils;
import de.latlon.xplan.commons.cli.SynchronizeAllExecutor;
import de.latlon.xplan.commons.cli.SynchronizeExecutor;
import de.latlon.xplanbox.cli.admin.evaluation.EvaluationSchemaSynchronizer;
import de.latlon.xplanbox.cli.admin.evaluation.SyncType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "evaluation-db-update", description = "EvaluationSchemaSynchronizer",
		subcommands = { CommandLine.HelpCommand.class })
public class EvaluationSchemaSynchronizerSubcommand implements Callable<Integer> {

	private static final Logger LOG = LoggerFactory.getLogger(EvaluationSchemaSynchronizerSubcommand.class);

	private static final String LOG_TABLE_NAME = "xplanevaluation.planTableLog";

	@CommandLine.Option(names = { "-h", "--host" },
			description = "hostname of the database server (default: ${DEFAULT-VALUE})", defaultValue = "localhost")
	private String host;

	@CommandLine.Option(names = { "-p", "--port" },
			description = "port of the database server (default: ${DEFAULT-VALUE})", defaultValue = "5432")
	private String port;

	@CommandLine.Option(names = { "-d", "--database" },
			description = "name of the xplanbox database (default: ${DEFAULT-VALUE})", defaultValue = "xplanbox")
	private String database;

	@CommandLine.Option(names = { "-u", "--user" }, description = "database user",
			defaultValue = "postgres (default: ${DEFAULT-VALUE})")
	private String user;

	@CommandLine.Option(names = { "-P", "--password" }, description = "database user password")
	private String password;

	@CommandLine.Option(names = { "-t", "--type" },
			description = "one of 'ALL' or 'SYNC' (default: ${DEFAULT-VALUE}); 'SYNC' synchronizes plans logged in xplanevaluation.planTableLog, 'ALL' synchronizes all available plans.",
			defaultValue = "SYNC")
	private SyncType type;

	@Override
	public Integer call() {
		try {
			switch (type) {
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
					LOG.warn("Unsupported sync type: {}", type);
			}
			return 0;
		}
		catch (Exception e) {
			LOG.error("Could not execute tool command line", e);
			return 1;
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

}
