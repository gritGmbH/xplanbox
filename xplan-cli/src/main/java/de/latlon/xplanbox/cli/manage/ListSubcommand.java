/*-
 * #%L
 * xplan-cli - Kommandozeilenwerkzeuge fuer die xPlanBox
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
package de.latlon.xplanbox.cli.manage;

import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "list", description = "List all plans that are available in the data storage.",
		subcommands = { CommandLine.HelpCommand.class })
public class ListSubcommand extends ManagerSubcommand {

	private static final Logger LOG = LoggerFactory.getLogger(ListSubcommand.class);

	@Override
	public Integer callSubcommand(XPlanManager xPlanManager) {
		System.out.println("---------------------------------------");
		try {
			List<XPlan> xPlanList = xPlanManager.list();
			printList(xPlanList);
			return 0;
		}
		catch (Exception e) {
			LOG.debug("Auflisten der Plaene fehlgeschlagen.", e);
			LOG.error("Auflisten der Plaene fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage());
			return 1;
		}
	}

	private void printList(List<XPlan> xpList) {
		System.out.println("Anzahl Plaene: " + xpList.size());
		for (XPlan plan : xpList) {
			System.out.print("- Id: " + plan.getId());
			System.out.print(", Version: " + plan.getVersion());
			System.out.print(", Typ: " + plan.getType());
			System.out.print(", Name: " + plan.getName());
			System.out.print(", Nummer: " + plan.getNumber());
			if (plan.getGkz() != null) {
				System.out.print(", GKZ: " + plan.getGkz());
			}
			System.out.print(", Raster: " + (plan.isRaster() ? "ja" : "nein"));
			System.out.print(", Veroeffentlichungsdatum: " + plan.getReleaseDate());
			System.out.println(", Importiert: " + plan.getImportDate());
		}
	}

}
