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
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "export", description = "Export a single or multiple plan(s).",
		subcommands = { CommandLine.HelpCommand.class })
public class ExportSubcommand extends ManagerSubcommand {

	private static final Logger LOG = LoggerFactory.getLogger(ExportSubcommand.class);

	@CommandLine.Option(names = { "-i", "--id" }, split = ",",
			description = "Die ID des Plans der exportiert werden soll.", required = true)
	private String[] ids;

	@CommandLine.Option(names = { "-t", "--target" },
			description = "Angabe des Verzeichnis in dem die exportierten XPlanArchive abgelegt werden sollen.")
	private Optional<File> target;

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
			File outputFile = createOutputFile(planId);
			if (outputFile.exists()) {
				LOG.error("Kann Datei '" + outputFile.getName() + "' nicht erzeugen. Datei existiert bereits.");
				return 1;
			}
			try {
				outputFile.createNewFile();
				OutputStream outputStream = new FileOutputStream(outputFile);
				xPlanManager.export(planId, outputStream);
			}
			catch (IOException e) {
				LOG.error("Kann FileOutputStream nicht erzeugen: " + e.getMessage());
				return 1;
			}
			catch (Exception e) {
				LOG.debug("Export des Plans fehlgeschlagen.", e);
				LOG.error("Export des Plans fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage());
				return 1;
			}
			System.out.print("XPlan " + planId + " wurde nach '" + outputFile.getName() + "' exportiert.");
		}
		return 0;
	}

	private File createOutputFile(String planId) {
		File parent = target.isPresent() ? target.get() : new File(".");
		return new File(parent, FilenameUtils.getName("xplan-exported-" + planId + ".zip"));
	}

}
