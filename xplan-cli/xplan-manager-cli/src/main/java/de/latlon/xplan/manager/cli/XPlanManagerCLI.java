/*-
 * #%L
 * xplan-manager-cli - Kommandozeilentool des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
 * Kommandozeilen-Frontend zum Verwalten von XPlanArchiven.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
@SpringBootApplication
public class XPlanManagerCLI {

	/**
	 * @param args command line arguments
	 * @throws Exception in case of errors
	 */
	public static void main(String[] args) {
		if (args.length == 1
				|| (args.length > 1 && ("--help".equals(args[1]) || "-help".equals(args[1]) || "-h".equals(args[1])))) {
			printUsage();
		}
		else {
			SpringApplication app = new SpringApplication(XPlanManagerApplicationRunner.class);
			app.setBannerMode(Banner.Mode.OFF);
			app.run(args);
		}
	}

	static void printUsage() {
		System.out.println("Usage: XPlanManager <options>");
		System.out.println();
		System.out.println(" --help");
		System.out.println(" --list [--managerconfiguration=<PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]");
		System.out.println(
				" --import <xplanarchiv> [<xplanarchiv>..] [--force] [--crs=<CRS>] [--managerconfiguration=<PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]");
		System.out.println(
				" --export <planid> [<planid>..] [--target=<verzeichnis>] [--managerconfiguration=<PFAD/ZU/VERZEICHNIS/MIT/MANAGERCONFIGURATION>]");
		System.out.println(" --delete <planid> [<planid>..]");
		System.out.println();
		System.exit(0);
	}

}
