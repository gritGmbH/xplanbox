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
package de.latlon.xplan.manager.cli;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Kommandozeilenwerkzeug (command line interface) zum Verwalten von XPlanArchiven.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
@SpringBootApplication
public class XPlanManagerCLI {

	/**
	 * CLI entry method.
	 * @param args command line arguments
	 * @throws Exception in case of errors
	 */
	public static void main(String[] args) {
		if (args.length < 1 || ("--help".equals(args[0]) || "-help".equals(args[0]) || "-h".equals(args[0]))) {
			printUsage();
		}
		else {
			SpringApplication app = new SpringApplication(XPlanManagerApplicationRunner.class);
			app.setBannerMode(Banner.Mode.OFF);
			app.run(args).close();
		}
	}

	static void printUsage() {
		System.out.println("Usage: XPlanManager <options>");
		System.out.println();
		System.out.println(" --help");
		System.out.println(" --list");
		System.out.println(" --import <xplanarchiv> [<xplanarchiv>..] [--force] [--crs=<CRS>]");
		System.out.println(
				"     <xplanarchiv> Die absolute oder relative Referenz auf den Plan, der importiert werden soll (verpflichtend). Mehrere Plaene koennen durch ein Leerzeichen getrennt angegeben werden.");
		System.out.println(
				"     --force Erzwingen des Imports eines Plans mit Geomtriefehlern oder Validierungsfehlern (optional). EMPFOHLEN ist die Behebung der Fehler!");
		System.out.println("     --crs Angabe des Koordinatenreferenzsystems in dem die Daten vorliegen (optional).");
		System.out.println(" --export <planid> [<planid>..] [--target=<verzeichnis>]");
		System.out.println(
				"     <planid> Die ID des Plans der exportiert werden soll (verpflichtend). Mehrere IDs koennen durch ein Leerzeichen getrennt angegeben werden.");
		System.out.println(
				"     --target Angabe des Verzeichnis in dem die exportierten XPlanArchive abgelegt werden sollen (optional).");
		System.out.println(" --delete <planid> [<planid>..]");
		System.out.println(
				"     <planid> Die ID des Plans der geloescht werden soll (verpflichtend). Mehrere IDs koennen durch ein Leerzeichen getrennt angegeben werden.");
		System.out.println(" --createMetadata <planid> [<planid>..]");
		System.out.println(
				"     <planid> Die ID des Plans zu dem der Service-Metadatensatz generiert werden soll (optional). Mehrere IDs koennen durch ein Leerzeichen getrennt angegeben werden. Wenn keine ID angegeben ist, werden fuer alle Plaene Metadatensaetze erstellt.");
		System.out.println();
		System.out.println("Allgemeine Parameter:");
		System.out.println("     --v Ausgabe der Systemeigenschaften");
		System.out.println();
		System.out.println("Allgemeine Hinweise:");
		System.out.println(
				"      Das Verzeichnis in dem die Konfigurationsdatei managerConfiguration.properties liegt, muss durch Angabe des Verzeichnis in der Datei etc/application.properties oder durch Setzen der Umgebungsvariablen _XPLANBOX_CONFIG_ erfolgen. Andernfalls wird die Konfiguration aus etc/managerConfiguration.properties verwendet.");
		System.out.println(
				"     Der Workspace `xplan-manager-workspace` muss im Verzeichnis _.deegree/_ des Home-Verzeichnis des Nutzers liegen, der das XPlanManagerCLI aufruft. Alternativ kann das Verzeichnis, in dem der Workspace liegt, durch Angabe der Umgebungsvariablen _DEEGREE_WORKSPACE_ROOT_ gesetzt werden.");
		System.out.println();
		System.exit(0);
	}

}
