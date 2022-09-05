package de.latlon.xplanbox.api.manager;

import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import liquibase.changelog.RanChangeSet;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Verifies if the current running software matches to the current configured application
 * database using liquibase API.
 *
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Component
public class DatabaseVerifier {

	private static final Logger LOG = getLogger(DatabaseVerifier.class);

	private static final String DB_VERSION = "v_6.0";

	private ManagerWorkspaceWrapper managerWorkspaceWrapper;

	@Autowired
	public DatabaseVerifier(ManagerWorkspaceWrapper managerWorkspaceWrapper) {
		this.managerWorkspaceWrapper = managerWorkspaceWrapper;
	}

	@PostConstruct
	public void verify() {
		LOG.debug("Verifying application database XPlanDB for matching version");

		try (Connection connection = managerWorkspaceWrapper.openConnection()) {
			Database database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(new JdbcConnection(connection));
			if (database.getDatabaseChangeLogTableName().isEmpty()) {
				LOG.error("Liquibase tables are missing, unable to verify version of application database XPlanDB."
						+ " Verify your installation and the database setup.");
				return;
			}
			if (database.doesTagExist(DB_VERSION)) {
				LOG.info("Application database XPlanDB version: " + DB_VERSION);
				LOG.info("Last changeset id: "
						+ retrieveChangeSet(database).stream().reduce((first, last) -> last).orElse(null));
				LOG.debug("Ran changesets: " + retrieveChangeSet(database));
			}
			else {
				LOG.warn("Application database version tag {} is missing."
						+ " Database version is not matching application version."
						+ " Verify your installation and the database setup.", DB_VERSION);
			}
		}
		catch (DatabaseException | SQLException e) {
			LOG.error("Connection to XPlanDB failed or liquibase can not be initialised."
					+ " Verify your installation and the database setup. Nested exception: " + e.getMessage());
			LOG.debug("Nested exception:", e);
		}
	}

	private List<RanChangeSet> retrieveChangeSet(Database database) throws DatabaseException {
		return database.getRanChangeSetList();
	}

}
