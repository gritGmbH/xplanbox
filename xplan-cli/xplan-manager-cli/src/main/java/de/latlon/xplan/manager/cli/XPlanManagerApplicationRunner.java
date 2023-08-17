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

import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.cli.config.ApplicationContext;
import de.latlon.xplan.manager.log.SystemLog;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.XPlan;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

import static de.latlon.xplan.manager.cli.XPlanManagerCLI.printUsage;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Import(ApplicationContext.class)
public class XPlanManagerApplicationRunner implements ApplicationRunner {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanManagerApplicationRunner.class);

	@Autowired
	private XPlanManager xPlanManager;

	@Autowired
	private Optional<ServiceMetadataRecordCreator> serviceMetadataRecordCreator;

	@Override
	public void run(ApplicationArguments args) {
		disableDerbyLog();
		if (args.containsOption("v"))
			SystemLog.log();
		if (args.containsOption("import")) {
			runImport(args);
		}
		else if (args.containsOption("export")) {
			runExport(args);
		}
		else if (args.containsOption("delete")) {
			runDelete(args);
		}
		else if (args.containsOption("list")) {
			runList();
		}
		else if (args.containsOption("createMetadata")) {
			createMetadataOption(args);
		}
		else {
			System.out.println(
					"Unbekannte Option. Eine der Optionen import, export, list, delete oder createMetadata muss gesetzt sein.");
			printUsage();
		}
	}

	private void runList() {
		System.out.println("---------------------------------------");
		try {
			List<XPlan> xPlanList = xPlanManager.list();
			printList(xPlanList);
		}
		catch (Exception e) {
			LOG.debug("Auflisten der Plaene fehlgeschlagen.", e);
			endWithFatalError("Auflisten der Plaene fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage());
		}
	}

	private void runDelete(ApplicationArguments args) {
		List<String> planIds = args.getNonOptionArgs();
		if (planIds.isEmpty()) {
			System.out.println("---------------------------------------");
			System.out.println("Keine planIds angegeben.");
			printUsage();
			return;
		}
		for (String planId : planIds) {
			System.out.println("---------------------------------------");
			try {
				xPlanManager.delete(planId);
				System.out.println("XPlan " + planId + " wurde geloescht.");
			}
			catch (Exception e) {
				LOG.debug("Loeschen des Plans mit der id " + planId + " fehlgeschlagen.", e);
				endWithFatalError("Loeschen des Plans mit der id " + planId + " fehlgeschlagen. Fehlermeldung: "
						+ e.getLocalizedMessage());
			}
		}
	}

	private void runExport(ApplicationArguments args) {
		List<String> planIds = args.getNonOptionArgs();
		String targetDir = ".";
		if (args.containsOption("target") && !args.getOptionValues("target").isEmpty()) {
			targetDir = args.getOptionValues("target").get(0);
		}
		export(planIds, targetDir, xPlanManager);
	}

	private void runImport(ApplicationArguments args) {
		boolean force = false;
		if (args.containsOption("force")) {
			force = true;
		}
		List<String> plansToImport = args.getNonOptionArgs();
		if (plansToImport == null || plansToImport.isEmpty()) {
			System.out.println("Es wurde kein Plan zum importieren angegeben.");
			printUsage();
		}
		ICRS defaultCRS = null;
		if (args.containsOption("crs") && !args.getOptionValues("crs").isEmpty()) {
			String crs = args.getOptionValues("crs").get(0);
			try {
				CRSManager.lookup(crs);
				defaultCRS = CRSManager.getCRSRef(crs);
			}
			catch (UnknownCRSException e) {
				endWithFatalError(
						"Das angegebene CRS '" + crs + "' ist unbekannt. Fehlermeldung: " + e.getLocalizedMessage());
			}
		}
		importPlan(force, plansToImport, defaultCRS);
	}

	private void createMetadataOption(ApplicationArguments args) {
		if (!serviceMetadataRecordCreator.isPresent())
			endWithFatalError("ServiceMetadataRecordCreator is not available Check configuration.");
		List<String> planIds = args.getNonOptionArgs();
		if (planIds == null || planIds.isEmpty()) {
			try {
				serviceMetadataRecordCreator.get().createServiceMetadataRecords();
			}
			catch (Exception e) {
				System.out
					.println("Bei der Erstellung des Service Metadatensatz fuer alle Plaen ist ein Fehler aufgetreten: "
							+ e.getMessage());
			}
		}
		else {
			for (String planId : planIds) {
				try {
					int mgrIdInt = Integer.parseInt(planId);
					serviceMetadataRecordCreator.get().createServiceMetadataRecords(mgrIdInt);
				}
				catch (NumberFormatException e) {
					System.out.println("Ungueltige Plan id (" + planId + "', es handelt sich nicht um eine Ganzzahl.");
				}
				catch (Exception e) {
					System.out.println("Bei der Erstellung des Service Metadatensatz fuer den Plan mit der ID " + planId
							+ " ist ein Fehler aufgetreten: " + e.getMessage());
				}
			}
		}
	}

	private void importPlan(boolean force, List<String> plansToImport, ICRS defaultCRS) {
		if (plansToImport.isEmpty()) {
			System.out.println("---------------------------------------");
			System.out.println("Keine Plaene zum importieren angegeben.");
			printUsage();
			return;
		}
		for (String planToImport : plansToImport) {
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
				xPlanManager.importPlan(planToImport, defaultCRS, force, true, null);
			}
			catch (Exception e) {
				LOG.debug("Import des XPlanArchivs fehlgeschlagen.", e);
				endWithFatalError("Import des XPlanArchivs fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage());
			}
		}
	}

	private void export(List<String> planIds, String targetDir, XPlanManager manager) {
		if (planIds.isEmpty()) {
			System.out.println("---------------------------------------");
			System.out.println("Keine planIds angegeben.");
			printUsage();
			return;
		}
		for (String planId : planIds) {
			System.out.println("---------------------------------------");
			File outputFile = new File(targetDir, "xplan-exported-" + planId + ".zip");
			if (outputFile.exists()) {
				endWithFatalError("Kann Datei '" + outputFile.getName() + "' nicht erzeugen. Datei existiert bereits.");
			}
			try {
				outputFile.createNewFile();
				OutputStream outputStream = new FileOutputStream(outputFile);
				manager.export(planId, outputStream);
			}
			catch (IOException e) {
				endWithFatalError("Kann FileOutputStream nicht erzeugen: " + e.getMessage());
			}
			catch (Exception e) {
				LOG.debug("Export des Plans fehlgeschlagen.", e);
				endWithFatalError("Export des Plans fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage());
			}
			System.out.print("XPlan " + planId + " wurde nach '" + outputFile.getName() + "' exportiert.");
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

	private void disableDerbyLog() {
		// avoid annoying creation of derby.log (because of internal CRS database)
		System.setProperty("derby.stream.error.method", "de.latlon.xplan.manager.XPlanManager.disableDerbyLogFile");
	}

	private void endWithFatalError(String msg) {
		System.out.println(msg);
		System.exit(0);
	}

}
