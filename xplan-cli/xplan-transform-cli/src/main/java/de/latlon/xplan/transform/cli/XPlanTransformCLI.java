/*-
 * #%L
 * xplan-manager-cli - Kommandozeilentool des XPlan Managers
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

import de.latlon.xplan.transform.cli.config.ApplicationContext;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static de.latlon.xplan.transform.cli.TransformApplicationRunner.LOG_TABLE_NAME;

/**
 * Kommandozeilenwerkzeug (command line interface) zum Verwalten von XPlanArchiven.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
@SpringBootApplication
public class XPlanTransformCLI {

	/**
	 * CLI entry method.
	 * @param args command line arguments
	 * @throws Exception in case of errors
	 */
	public static void main(String[] args) {
		if (args.length > 0 && ("--help".equals(args[0]) || "-help".equals(args[0]) || "-h".equals(args[0]))) {
			printUsage();
		}
		else {
			SpringApplication app = new SpringApplication(TransformApplicationRunner.class);
			app.setBannerMode(Banner.Mode.OFF);
			app.run(args).close();
		}
	}

	static void printUsage() {
		System.out.println(
				"Reads all Plans from XPlanWFS 4.1 datastore, transforms them to XPlanGML 5.1 and inserts them in XPlanWFS 5.1 datastore.");
		System.out.println();
		System.out.println("Usage: XPlanTransformCLI <options>");
		System.out.println();
		System.out.println("Options:");
		System.out.println("     --type one of 'VALIDATE' (default if missing), 'ALL', 'SYNC':\n"
				+ "         * 'VALIDATE': validates all available XPlanGML 4.1 plans and writes the results\n"
				+ "         * 'ALL' transforms all available XPlanGML 4.1 plans and inserts the valid plans in XPlanWFS 5.1 datastore, plans already available in 5.1 will be removed first\n"
				+ "         * 'SYNC' transforms the XPlanGML 4.1 plans logged in the table " + LOG_TABLE_NAME
				+ " and inserts the valid plans in XPlanWFS 5.1 datastore");
		System.out.println(
				"     --output directory to write the validation results into, directory will be created if it not exists (if missing a new tmp directory is created");
		System.out.println("Allgemeine Hinweise:");
		System.out.println(
				"      Das Verzeichnis in dem die Konfigurationsdatei managerConfiguration.properties liegt, muss durch Angabe des Verzeichnis in der Datei etc/application.properties oder durch Setzen der Umgebungsvariablen _XPLANBOX_CONFIG_ erfolgen. Andernfalls wird die Konfiguration aus etc/managerConfiguration.properties verwendet.");
		System.out.println(
				"     Der Workspace `xplan-manager-workspace` muss im Verzeichnis _.deegree/_ des Home-Verzeichnis des Nutzers liegen, der das Tool aufruft. Alternativ kann das Verzeichnis, in dem der Workspace liegt, durch Angabe der Umgebungsvariablen _DEEGREE_WORKSPACE_ROOT_ gesetzt werden.");
		System.exit(0);
	}

}
