/*-
 * #%L
 * xplan-transform-cli - Kommandozeilentool fuer die Transformation zwischen XPlanGML Versionen
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
package de.latlon.xplan.transform.cli;

import de.latlon.xplan.commons.cli.SynchronizeExecutor;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.transform.cli.config.ApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

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
 * @deprecated will be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Import(ApplicationContext.class)
public class TransformApplicationRunner implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(TransformApplicationRunner.class);

	static final String LOG_TABLE_NAME = "xplanmgr.transformToolPlanTableLog";

	private enum TYPE {

		VALIDATE, SYNC, ALL

	}

	private static final String OPT_TYPE = "type";

	private static final String OPT_OUT_DIR = "output";

	@Autowired
	private ManagerWorkspaceWrapper managerWorkspaceWrapper;

	@Autowired
	private TransformingValidator transformingValidator;

	@Autowired
	private XPlanDao xPlanDao;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		TYPE type = determineType(args);
		Path outDirectory = createOutDirectory(args);
		run(type, outDirectory);
	}

	private void run(TYPE type, Path outDirectory) throws Exception {
		switch (type) {
			case ALL:
				sync(managerWorkspaceWrapper, (conn) -> {
					TransformationSynchronizer synchronizer = new TransformationSynchronizer(xPlanDao,
							transformingValidator, outDirectory);
					TransformAllExecutor allExecuter = new TransformAllExecutor(LOG_TABLE_NAME, synchronizer);
					allExecuter.transformAll(conn);
				});
				break;
			case SYNC:
				sync(managerWorkspaceWrapper, (conn) -> {
					TransformationSynchronizer synchronizer = new TransformationSynchronizer(xPlanDao,
							transformingValidator, outDirectory);
					SynchronizeExecutor executer = new SynchronizeExecutor(LOG_TABLE_NAME, synchronizer);
					executer.synchronize(conn);
				});
				break;
			case VALIDATE:
			default:
				ValidateExecutor validateExecutor = new ValidateExecutor(xPlanDao, transformingValidator);
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

	private static Path createOutDirectory(ApplicationArguments args) throws IOException {
		if (!args.containsOption(OPT_OUT_DIR))
			return createTempDirectory("validationresults");

		String outDirectory = args.getOptionValues(OPT_OUT_DIR).get(0);
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

	private static TYPE determineType(ApplicationArguments args) {
		if (!args.containsOption(OPT_TYPE))
			return TYPE.VALIDATE;
		String type = args.getOptionValues(OPT_TYPE).get(0);
		return TYPE.valueOf(type.toUpperCase());
	}

}
