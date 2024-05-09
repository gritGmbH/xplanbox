/*-
 * #%L
 * xplan-cli-tools - Kommandozeilenwerkzeuge fuer die xPlanBox
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "delete", description = "Delete a single or multiple plan(s).",
		subcommands = { CommandLine.HelpCommand.class })
public class DeleteSubcommand extends ManagerSubcommand {

	private static final Logger LOG = LoggerFactory.getLogger(DeleteSubcommand.class);

	@CommandLine.Option(names = { "-i", "--id" }, split = ",",
			description = "Die ID des Plans der geloescht werden soll.", required = true)
	private String[] ids;

	@Override
	public Integer callSubcommand(XPlanManager xPlanManager) {
		List<String> planIds = Arrays.asList(ids);
		if (planIds.isEmpty()) {
			System.out.println("---------------------------------------");
			System.out.println("Keine planIds angegeben.");
			return 0;
		}
		for (String planId : planIds) {
			System.out.println("---------------------------------------");
			try {
				xPlanManager.delete(planId);
				System.out.println("XPlan " + planId + " wurde geloescht.");
			}
			catch (Exception e) {
				LOG.debug("Loeschen des Plans mit der id " + planId + " fehlgeschlagen.", e);
				LOG.error("Loeschen des Plans mit der id " + planId + " fehlgeschlagen. Fehlermeldung: "
						+ e.getLocalizedMessage());
				return 1;
			}
		}
		return 0;
	}

}
