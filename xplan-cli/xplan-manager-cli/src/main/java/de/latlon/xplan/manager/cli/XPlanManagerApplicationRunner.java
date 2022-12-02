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

import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.ConfigurationDirectoryPropertiesLoader;
import de.latlon.xplan.commons.configuration.PropertiesLoader;
import de.latlon.xplan.manager.CategoryMapper;
import de.latlon.xplan.manager.XPlanManager;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.database.ManagerWorkspaceWrapper;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.log.SystemLog;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.config.RasterStorageContext;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.wmsconfig.raster.config.RasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.RasterEvaluation;
import de.latlon.xplan.manager.wmsconfig.raster.evaluation.XPlanRasterEvaluator;
import de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorage;
import de.latlon.xplan.manager.workspace.WorkspaceReloader;
import de.latlon.xplan.manager.workspace.WorkspaceUtils;
import org.deegree.commons.config.DeegreeWorkspace;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static de.latlon.xplan.manager.cli.XPlanManagerCLI.printUsage;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
// TODO use Spring configuration for object creation
@Component
public class XPlanManagerApplicationRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) {
		disableDerbyLog();
		if (args.containsOption("v"))
			SystemLog.log();
		if (args.containsOption("import")) {
			importOption(args, instantiateManager(args));
		}
		else if (args.containsOption("export")) {
			exportOption(args, instantiateManager(args));
		}
		else if (args.containsOption("delete")) {
			deleteOption(args, instantiateManager(args));
		}
		else if (args.containsOption("list")) {
			listOption(instantiateManager(args));
		}
		else if (args.containsOption("createMetadata")) {
			createMetadataOption(args, instantiateServiceMetadataRecordCreator(args));
		}
		else {
			System.out.println(
					"Unbekannte Option. Eine der Optionen import, export, list, delete oder createMetadata muss gesetzt sein.");
			printUsage();
		}
	}

	private XPlanManager instantiateManager(ApplicationArguments args) {
		Path directoryContainingTheManagerConfig = parseDirectoryContainingManagerConfig(args);
		return createManager(directoryContainingTheManagerConfig);
	}

	private ServiceMetadataRecordCreator instantiateServiceMetadataRecordCreator(ApplicationArguments args) {
		Path directoryContainingTheManagerConfig = parseDirectoryContainingManagerConfig(args);
		return createServiceMetadataRecordCreator(directoryContainingTheManagerConfig);
	}

	private void listOption(XPlanManager manager) {
		System.out.println("---------------------------------------");
		try {
			List<XPlan> xPlanList = manager.list(true);
			printList(xPlanList);
		}
		catch (Exception e) {
			endWithFatalError("Auflisten der Plaene fehlgeschlagen. Fehlermeldung: " + e.getLocalizedMessage());
		}
	}

	private void deleteOption(ApplicationArguments args, XPlanManager manager) {
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
				manager.delete(planId);
				System.out.println("XPlan " + planId + " wurde geloescht.");
			}
			catch (Exception e) {
				endWithFatalError("Loeschen des Plans mit der id " + planId + " fehlgeschlagen. Fehlermeldung: "
						+ e.getLocalizedMessage());
			}
		}
	}

	private void exportOption(ApplicationArguments args, XPlanManager manager) {
		List<String> planIds = args.getNonOptionArgs();
		String targetDir = ".";
		if (args.containsOption("target") && !args.getOptionValues("target").isEmpty()) {
			targetDir = args.getOptionValues("target").get(0);
		}
		export(planIds, targetDir, manager);
	}

	private void importOption(ApplicationArguments args, XPlanManager manager) {
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
		importPlan(manager, force, plansToImport, defaultCRS);
	}

	private void createMetadataOption(ApplicationArguments args,
			ServiceMetadataRecordCreator serviceMetadataRecordCreator) {
		List<String> planIds = args.getNonOptionArgs();
		if (planIds == null || planIds.isEmpty()) {
			try {
				serviceMetadataRecordCreator.createServiceMetadataRecords();
			}
			catch (Exception e) {
				System.out.println(
						"Bei der Erstellung des Service Metadatensatz fuer alle Plaen ist ein Fehler aufgetreten: "
								+ e.getMessage());
			}
		}
		else {
			for (String planId : planIds) {
				try {
					int mgrIdInt = Integer.parseInt(planId);
					serviceMetadataRecordCreator.createServiceMetadataRecords(mgrIdInt);
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

	private void importPlan(XPlanManager manager, boolean force, List<String> plansToImport, ICRS defaultCRS) {
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
				List<RasterEvaluationResult> evaluateRasterdata = manager.evaluateRasterdata(planToImport);
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
				manager.importPlan(planToImport, defaultCRS, force, true, null);
			}
			catch (Exception e) {
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
			System.out.print(", Features: " + plan.getNumFeatures());
			System.out.print(", Raster: " + (plan.isRaster() ? "ja" : "nein"));
			System.out.print(", Veroeffentlichungsdatum: " + plan.getReleaseDate());
			System.out.println(", Importiert: " + plan.getImportDate());
		}
	}

	private void disableDerbyLog() {
		// avoid annoying creation of derby.log (because of internal CRS database)
		System.setProperty("derby.stream.error.method", "de.latlon.xplan.manager.XPlanManager.disableDerbyLogFile");
	}

	private Path parseDirectoryContainingManagerConfig(ApplicationArguments args) {
		Path directoryContainingTheManagerConfig = parseDirectoryFromArgs(args);
		System.out.println(
				"Die Konfigurationsdatei fuer den Manager (managerConfiguration.properties) wird im Verzeichnis "
						+ directoryContainingTheManagerConfig + " erwartet.");
		return directoryContainingTheManagerConfig;
	}

	private Path parseDirectoryFromArgs(ApplicationArguments args) {
		List<String> managerconfiguration = args.getOptionValues("managerconfiguration");
		if (managerconfiguration != null && !managerconfiguration.isEmpty()) {
			String pathToDirectory = managerconfiguration.get(0);
			Path managerConfigurationDirectory = Paths.get(pathToDirectory);
			if (Files.exists(managerConfigurationDirectory) && Files.isDirectory(managerConfigurationDirectory))
				return managerConfigurationDirectory;
		}
		String path = XPlanManagerCLI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		File jarLocation = new File(path);
		return Paths.get(jarLocation.getParentFile().getParent()).resolve("etc");
	}

	// TODO refactor code to use Spring configuration for creating XPlanManager object,
	// class is eligible for Spring DI
	private XPlanManager createManager(Path directoryContainingTheManagerConfig) {
		try {
			PropertiesLoader propertiesLoader = new ConfigurationDirectoryPropertiesLoader(
					directoryContainingTheManagerConfig, ManagerConfiguration.class);
			ManagerConfiguration managerConfiguration = new ManagerConfiguration(propertiesLoader);
			CategoryMapper categoryMapper = new CategoryMapper(managerConfiguration);
			XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator(categoryMapper);
			WorkspaceReloader workspaceReloader = new WorkspaceReloader(
					managerConfiguration.getWorkspaceReloaderConfiguration());
			DeegreeWorkspace managerWorkspace = WorkspaceUtils.instantiateManagerWorkspace(null);
			ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper(managerWorkspace,
					managerConfiguration);
			DeegreeWorkspace wmsWorkspace = WorkspaceUtils.instantiateWmsWorkspace(null);
			WmsWorkspaceWrapper wmsWorkspaceWrapper = new WmsWorkspaceWrapper(wmsWorkspace);
			XPlanDao xplanDao = new XPlanDao(managerWorkspaceWrapper, categoryMapper, managerConfiguration);
			RasterEvaluation rasterEvaluation = createRasterEvaluation(managerConfiguration);
			XPlanRasterEvaluator xPlanRasterEvaluator = new XPlanRasterEvaluator(rasterEvaluation);
			RasterStorage rasterStorage = createRasterStorage(managerConfiguration, wmsWorkspaceWrapper,
					rasterEvaluation);
			RasterConfigManager rasterManagerConfig = createRasterConfigManager(wmsWorkspaceWrapper,
					managerConfiguration);
			XPlanRasterManager xPlanRasterManager = new XPlanRasterManager(rasterStorage, rasterManagerConfig);
			return new XPlanManager(xplanDao, archiveCreator, managerWorkspaceWrapper, workspaceReloader, null,
					wmsWorkspaceWrapper, xPlanRasterEvaluator, xPlanRasterManager);
		}
		catch (Exception e) {
			endWithFatalError(e.getMessage());
		}
		return null;
	}

	private RasterConfigManager createRasterConfigManager(WmsWorkspaceWrapper wmsWorkspaceWrapper,
			ManagerConfiguration managerConfiguration) {
		// TODO turn into autowired field
		return new RasterStorageContext().rasterConfigManager(wmsWorkspaceWrapper, managerConfiguration);
	}

	private RasterStorage createRasterStorage(ManagerConfiguration managerConfiguration,
			WmsWorkspaceWrapper wmsWorkspaceWrapper, RasterEvaluation rasterEvaluation) {
		// TODO turn into autowired field
		return new RasterStorageContext().rasterStorage(managerConfiguration, wmsWorkspaceWrapper, rasterEvaluation);
	}

	private RasterEvaluation createRasterEvaluation(ManagerConfiguration managerConfiguration) {
		// TODO turn into autowired field
		return new RasterStorageContext().rasterEvaluation(managerConfiguration);
	}

	private ServiceMetadataRecordCreator createServiceMetadataRecordCreator(Path directoryContainingTheManagerConfig) {
		try {
			PropertiesLoader propertiesLoader = new ConfigurationDirectoryPropertiesLoader(
					directoryContainingTheManagerConfig, ManagerConfiguration.class);
			ManagerConfiguration managerConfiguration = new ManagerConfiguration(propertiesLoader);
			CategoryMapper categoryMapper = new CategoryMapper(managerConfiguration);
			DeegreeWorkspace managerWorkspace = WorkspaceUtils.instantiateManagerWorkspace(null);
			ManagerWorkspaceWrapper managerWorkspaceWrapper = new ManagerWorkspaceWrapper(managerWorkspace,
					managerConfiguration);
			XPlanDao xplanDao = new XPlanDao(managerWorkspaceWrapper, categoryMapper, managerConfiguration);
			return new ServiceMetadataRecordCreator(xplanDao, managerConfiguration);
		}
		catch (Exception e) {
			endWithFatalError(e.getMessage());
		}
		return null;
	}

	private void endWithFatalError(String msg) {
		System.out.println(msg);
		System.exit(0);
	}

}
