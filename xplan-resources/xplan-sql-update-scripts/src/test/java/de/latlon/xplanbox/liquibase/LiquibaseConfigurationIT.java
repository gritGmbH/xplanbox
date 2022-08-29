package de.latlon.xplanbox.liquibase;

import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.changelog.RanChangeSet;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.*;

public class LiquibaseConfigurationIT {

	@Test
	public void testLiquibaseConfiguration() throws Exception {
		Map<String, Object> config = new HashMap<>();
		config.put("liquibase.verbose", "true");
		config.put("liquibase.logLevel", "info");
		config.put("liquibase.sql.logLevel", "info");

		Scope.child(config, () -> {
			Connection connection = openConnection();

			Database database = DatabaseFactory.getInstance()
					.findCorrectDatabaseImplementation(new JdbcConnection(connection));
			Liquibase liquibase = new liquibase.Liquibase("changelog_v60.yaml", new ClassLoaderResourceAccessor(),
					database);
			liquibase.validate();
			ValidationErrors errors = database.validate();
			assertFalse(errors.hasErrors());
			RanChangeSet changeSet60 = database.getRanChangeSetList().stream()
					.filter(changeSet -> changeSet.getId().equalsIgnoreCase("1660760759850-6")).findAny().orElse(null);
			assertNotNull(changeSet60);
			assertTrue(database.doesTagExist("v_6.0"));
			connection.close();
		});
	}

	private Connection openConnection() throws ClassNotFoundException, IOException, SQLException {
		Class.forName("org.postgresql.Driver");
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try (InputStream stream = loader.getResourceAsStream("liquibase-target.properties")) {
			prop.load(stream);
		}
		String url = prop.getProperty("url");
		String user = prop.getProperty("username");
		String password = prop.getProperty("password");
		Connection con = DriverManager.getConnection(url, user, password);
		return con;
	}

}
