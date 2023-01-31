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
package de.latlon.xplan.validatedb.cli;

import de.latlon.xplan.validatedb.cli.config.ValidateFromDatabaseConfiguration;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of the command line interface of the GMLLoader.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 5.0
 */
@SpringBootApplication
public class ValidateFromDatabaseApp {

	public static void main(String[] args) {
		if (args.length == 1
				|| (args.length > 1 && ("--help".equals(args[1]) || "-help".equals(args[1]) || "-h".equals(args[1])))) {
			printUsage();
		}
		else if (args.length != 4) {
			printUsage();
		}
		else {
			SpringApplication app = new SpringApplication(ValidateFromDatabaseConfiguration.class);
			app.setBannerMode(Banner.Mode.OFF);
			app.run(args);
		}
	}

	private static void printUsage() {
		System.out.println(
				"Usage: ./XPlanValidateDB -jdbcurl=jdbc:postgresql://localhost:5432/xplanbox -user=postgres -password=postgres -rulesDirectory=/PATH_TO_CLI/etc/rules");
		System.out.println();
		System.out.println("Options:");
		System.out.println(" -jdbcurl=jdbc:postgresql://localhost:5432/xplanbox");
		System.out.println(" -user=postgres");
		System.out.println(" -password=postgres");
		System.out.println(" -rulesDirectory=/PATH_TO_CLI/etc/rules");
	}

}
