/*-
 * #%L
 * xplan-database-scripts - Liquibase Changelogs zum Aufsetzen/Aktualisieren der Datenhaltung.
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
package de.latlon.xplanbox.liquibase;

import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.changelog.RanChangeSet;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Ignore
public class LiquibaseConfigurationIT {

	/**
	 * Prerequisites:
	 *
	 * <ol>
	 * <li>create the database with pgAdmin or psql `CREATE DATABASE <DATABASE_NAME>`</li>
	 * <li>apply changelog to database (see README.md)</li>
	 * <li>configure the JDBC connection in the `liquibase-target.properties`</li>
	 * </ol>
	 * @throws Exception
	 */
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
			RanChangeSet changeSet60 = database.getRanChangeSetList()
				.stream()
				.filter(changeSet -> changeSet.getId().equalsIgnoreCase("1663512741090-1"))
				.findAny()
				.orElse(null);
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
		return DriverManager.getConnection(url, user, password);
	}

}
