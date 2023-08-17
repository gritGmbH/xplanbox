/*-
 * #%L
 * xplan-update-data-cli - update of database
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
package de.latlon.xplan.update.tool;

import de.latlon.xplan.update.updater.SortPropertyUpdaterApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point to update the sort date in databases.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
@SpringBootApplication
public final class SortDateUpdateTool {

	/**
	 * CLI entry method.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		if (args.length > 0 && ("--help".equals(args[0]) || "-help".equals(args[0]) || "-h".equals(args[0]))) {
			printUsage();
		}
		else {
			SpringApplication app = new SpringApplication(SortPropertyUpdaterApplicationRunner.class);
			app.setBannerMode(Banner.Mode.OFF);
			app.run(args).close();
		}
	}

	private static void printUsage() {
		System.out.println("Update sort date.");
		System.out.println();
		System.out.println("Usage: sortDateUpdate");
		System.out.println();
		System.out.println("Allgemeine Hinweise:");
		System.out.println(
				"      Das Verzeichnis in dem die Konfigurationsdatei managerConfiguration.properties liegt, muss durch Angabe des Verzeichnis in der Datei etc/application.properties oder durch Setzen der Umgebungsvariablen _XPLANBOX_CONFIG_ erfolgen. Andernfalls wird die Konfiguration aus etc/managerConfiguration.properties verwendet.");
		System.out.println(
				"     Der Workspace `xplan-manager-workspace` muss im Verzeichnis _.deegree/_ des Home-Verzeichnis des Nutzers liegen, der das Tool aufruft. Alternativ kann das Verzeichnis, in dem der Workspace liegt, durch Angabe der Umgebungsvariablen _DEEGREE_WORKSPACE_ROOT_ gesetzt werden.");
		System.exit(0);
	}

}
