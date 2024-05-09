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
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplanbox.cli.manage.converter.CrsConverter;
import org.deegree.cs.coordinatesystems.ICRS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@CommandLine.Command(name = "import", description = "Import a single or multiple XPlanArchive(s) or XPlanGML file(s).",
		subcommands = { CommandLine.HelpCommand.class })
public class ImportSubcommand extends ManagerSubcommand {

	private static final Logger LOG = LoggerFactory.getLogger(ImportSubcommand.class);

	@CommandLine.Option(names = { "-f", "--file" }, split = ", ", required = true, description = "File(s) to import.")
	private File[] files;

	@CommandLine.Option(names = { "-o", "--force" }, defaultValue = "false",
			description = "Force import, ignores invalid or unknown raster format or CRS (default: ${DEFAULT-VALUE}).")
	private boolean force;

	@CommandLine.Option(names = { "-c", "--crs" }, converter = CrsConverter.class)
	private ICRS crs;

	@Override
	public Integer callSubcommand(XPlanManager xPlanManager) {
		if (files.length < 1) {
			System.out.println("---------------------------------------");
			System.out.println("Keine Plaene zum importieren angegeben.");
			return 0;
		}
		for (File file : files) {
			String planToImport = file.getAbsolutePath();
			System.out.println("---------------------------------------");
			System.out.println("Import des Plans " + planToImport);
			try {
				List<RasterEvaluationResult> evaluateRasterdata = xPlanManager.evaluateRasterdata(planToImport);
				System.out.println("Evaluationsergebnis der referenzierten Rasterdaten: ");
				boolean areAllValid = true;
				for (RasterEvaluationResult result : evaluateRasterdata) {
					boolean configuredCrs = result.isConfiguredCrs();
					boolean supportedImageFormat = result.isSupportedImageFormat();
					System.out.println("  - Name: " + result.getRasterName() + " Unterstuetztes CRS: "
							+ (configuredCrs ? "Ja" : "Nein") + " Unterstuetztes Bildformat: "
							+ (supportedImageFormat ? "Ja" : "Nein"));
					if (!configuredCrs || !supportedImageFormat)
						areAllValid = false;
				}
				if (areAllValid) {
					System.out.println("Die Rasterdaten des Plans sind valide");
				}
				else {
					if (!force) {
						System.out.println(
								"Aufgrund invalider Rasterdaten wird der Import abgebrochen. Sie koennen den Import "
										+ "ohne die Erzeugung von Rasterkonfigurationen erzwingen, indem Sie die Option --force angeben.");
						System.exit(0);
					}
					System.out.println("Es existieren invaliden Rasterdaten. Da die Option --force gesetzt ist, "
							+ "wird der Plan importiert, es werden jedoch keine Konfigurationen fuer "
							+ "Rasterdaten geschrieben.");
				}
				xPlanManager.importPlan(planToImport, crs, force, true, null);
			}
			catch (Exception e) {
				LOG.debug("Import des XPlanArchivs fehlgeschlagen.", e);
				LOG.error("Import des XPlanArchivs fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage());
				return 1;
			}
		}
		return 0;
	}

}
