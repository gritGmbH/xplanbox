/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.manager.configuration.ConfigurationException;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import static de.latlon.xplan.manager.wmsconfig.ConfigWriterUtils.detectType;
import static de.latlon.xplan.manager.wmsconfig.raster.RasterConfigurationType.geotiff;
import static de.latlon.xplan.manager.wmsconfig.raster.RasterUtils.findRasterplanZipEntries;
import static de.latlon.xplan.manager.wmsconfig.raster.RasterUtils.unzipArchiveInTmpDirectory;

/**
 * An instance of XPlanRasterManager provides the service methods to manage raster files
 * part of XPlan.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanRasterManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanRasterManager.class);

	private final WmsWorkspaceWrapper wmsWorkspaceWrapper;

	private final GdalRasterAdapter rasterAdapter;

	private final ManagerConfiguration managerConfiguration;

	private final WorkspaceRasterThemeManager rasterThemeManager;

	private final WorkspaceRasterLayerManager rasterLayerManager;

	/**
	 * Instantiates a {@link XPlanRasterManager} with workspace and manager configuration.
	 * @param wmsWorkspaceWrapper the wms workspace, may be <code>null</code> (workspace
	 * with default name is used
	 * @param managerConfiguration configuration of the manager, should not be
	 * <code>null</code>
	 * @throws WorkspaceException
	 */
	public XPlanRasterManager(WmsWorkspaceWrapper wmsWorkspaceWrapper, GdalRasterAdapter rasterAdapter,
			ManagerConfiguration managerConfiguration) throws WorkspaceException {
		this.wmsWorkspaceWrapper = wmsWorkspaceWrapper;
		this.rasterAdapter = rasterAdapter;
		this.managerConfiguration = managerConfiguration;
		this.rasterThemeManager = new WorkspaceRasterThemeManager(wmsWorkspaceWrapper);
		this.rasterLayerManager = new WorkspaceRasterLayerManager(wmsWorkspaceWrapper.getLocation(),
				getRasterConfigurationTypeFromManagerConfig(), getRasterConfigurationCrsFromManagerConfig());
	}

	/**
	 * Removes the configuration of the plan with the given id.
	 * @param planId the id of the plan to remove, should not be <code>null</code>
	 */
	public void removeRasterLayers(String planId) {
		if (!isDeegreeRasterType())
			return;
		try {
			WorkspaceRasterLayerManager rasterLayerManager = new WorkspaceRasterLayerManager(
					wmsWorkspaceWrapper.getLocation());
			for (String type : WmsWorkspaceWrapper.supportedTypes) {
				rasterThemeManager.removeLayersForPlan(type, planId);
			}
			rasterLayerManager.deleteDataFilesAndRasterConfigurations(planId);
		}
		catch (Exception e) {
			LOG.trace("Configuration of the plan with id " + planId + " failed.!", e);
			LOG.error("Modifizierung der Themes-Datei fehlgeschlagen: {}", e.getMessage());
		}
	}

	/**
	 * Removes the configuration of the plan with the given id.
	 * @param planId the id of the plan to remove, should not be <code>null</code>
	 */
	public void removeRasterLayers(String planId, ExternalReferenceInfo externalReferencesToRemove) {
		if (!isDeegreeRasterType())
			return;
		try {
			List<ExternalReference> rasterPlanBaseAndUpdateScans = externalReferencesToRemove
					.getRasterPlanBaseAndUpdateScans();
			for (ExternalReference externalReferenceToRemove : rasterPlanBaseAndUpdateScans) {
				String referenzUrl = externalReferenceToRemove.getReferenzUrl();
				if (referenzUrl != null) {
					String rasterId = createRasterId(referenzUrl);
					WorkspaceRasterLayerManager rasterLayerManager = new WorkspaceRasterLayerManager(
							wmsWorkspaceWrapper.getLocation());
					for (String type : WmsWorkspaceWrapper.supportedTypes) {
						rasterThemeManager.removeLayersForPlan(type, planId, rasterId);
					}
					rasterLayerManager.deleteDataFilesAndRasterConfigurations(planId, rasterId);
				}
			}
		}
		catch (Exception e) {
			LOG.trace("Configuration of the plan with id " + planId + " failed.!", e);
			LOG.error("Modifizierung der Themes-Datei fehlgeschlagen: {}", e.getMessage());
		}
	}

	/**
	 * Creates one raster layer for each referenced raster. Sorts the raster layer after
	 * the plan with the moreRecentPlanId or at the end.
	 * @param archive containing the rasterdata to evaluate, never <code>null</code>
	 * @param planFeatureCollection featureCollection of the xplan, never
	 * <code>null</code>
	 * @param planId the id of the plan, never <code>null</code>
	 * @param moreRecentPlanId the id of the plan with release date immediate before the
	 * release date of the plan (if the plan has one), if <code>null</code> the plan will
	 * be inserted at last position
	 * @param type the type of the plan, never <code>null</code>
	 * @param planStatus the status of the plan, never <code>null</code>
	 * @param newPlanStatus status of the plan after update, may be <code>null</code> (if
	 * not updated)
	 * @param sortDate
	 * @return a list of ids of the inserted raster layers, may be empty if no raster
	 * layer was created, but never <code>null</code>
	 */
	public List<String> updateWmsWorkspaceWithRasterLayers(XPlanArchiveContentAccess archive,
			XPlanFeatureCollection planFeatureCollection, int planId, String moreRecentPlanId, XPlanType type,
			PlanStatus planStatus, PlanStatus newPlanStatus, Date sortDate) {
		long begin = System.currentTimeMillis();

		String sortDateAsString = "unbekannt";
		if (sortDate != null) {
			SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy");
			sortDateAsString = sd.format(sortDate);
		}
		LOG.info("- Erzeugen/Einsortieren der Rasterkonfigurationen (nach Datum: {} )...", sortDateAsString);
		try {
			List<String> scanFiles = collectRasterScanFiles(planFeatureCollection);
			logScanFiles(begin, scanFiles);
			List<ArchiveEntry> rasterplanEntries = findRasterplanZipEntries(archive, scanFiles,
					getRasterConfigurationTypeFromManagerConfig());

			List<String> rasterIds = copyRasterplanImageFilesToWmsWorkspace(wmsWorkspaceWrapper.getLocation(), archive,
					rasterplanEntries, planId);
			if (isDeegreeRasterType())
				insertRasterLayers(planId, moreRecentPlanId, type, planStatus, newPlanStatus, rasterIds, sortDate);
			return rasterIds;
		}
		catch (Exception e) {
			LOG.error("Rasterconfiguration could not be created!", e);
			LOG.trace("Rasterconfiguration could not be created: {} ", e.getMessage());
			throw new RuntimeException("Fehler beim Erzeugen der Rasterkonfigurationen: " + e.getLocalizedMessage());
		}
	}

	/**
	 * Updates the existing raster layer configuration, if the planStatus and
	 * newPlanStatus differs, the configuration is moved from one theme to another.
	 * @param planId the id of the plan, never <code>null</code>
	 * @param type the type of the plan, never <code>null</code>
	 * @param planStatus the status of the plan, never <code>null</code>
	 * @param newPlanStatus status of the plan after update, may be <code>null</code>
	 * @throws JAXBException
	 * @throws IOException
	 * @throws ConfigurationException
	 */
	public void updateRasterLayers(int planId, XPlanType type, PlanStatus planStatus, PlanStatus newPlanStatus)
			throws JAXBException, IOException, ConfigurationException {
		if (!isDeegreeRasterType())
			return;
		String statusType = detectType(type, planStatus);
		if (newPlanStatus != null) {
			String newStatusType = detectType(type, newPlanStatus);
			rasterThemeManager.moveLayers(statusType, newStatusType, Integer.toString(planId));
		}
	}

	/**
	 * Reorders the wms layer configuration by the passed sort dates.
	 * @param planId2sortDate plan ids mapped to the new sort date, never
	 * <code>null</code>
	 * @throws Exception
	 */
	public void reorderWmsLayers(Map<String, Date> planId2sortDate) throws Exception {
		if (!isDeegreeRasterType())
			return;
		String rasterConfigurationCrs = getRasterConfigurationCrsFromManagerConfig();
		rasterThemeManager.reorderWmsLayers(planId2sortDate, rasterConfigurationCrs);
	}

	private void insertRasterLayers(int planId, String moreRecentPlanId, XPlanType type, PlanStatus planStatus,
			PlanStatus newPlanStatus, List<String> rasterIds, Date sortDate)
			throws JAXBException, IOException, ConfigurationException {
		String statusType = detectType(type, planStatus);
		if (newPlanStatus != null) {
			String newStatusType = detectType(type, newPlanStatus);
			rasterThemeManager.moveLayers(statusType, newStatusType, Integer.toString(planId));
			statusType = newStatusType;
		}
		String rasterConfigurationCrs = getRasterConfigurationCrsFromManagerConfig();
		if (sortDate != null) {
			rasterThemeManager.insertLayersRightBefore(statusType, rasterConfigurationCrs, rasterIds, moreRecentPlanId);
		}
		else {
			rasterThemeManager.insertLayersAtBeginning(statusType, rasterConfigurationCrs, rasterIds);
		}
	}

	private List<String> copyRasterplanImageFilesToWmsWorkspace(File workspaceLocation,
			XPlanArchiveContentAccess archive, List<ArchiveEntry> rasterplanEntries, int planId)
			throws IOException, JAXBException {
		RasterConfigurationType rasterConfigurationType = getRasterConfigurationTypeFromManagerConfig();
		List<String> rasterIds = new ArrayList<>();
		switch (rasterConfigurationType) {
			case geotiff:
				createGeotiffConfig(workspaceLocation, archive, rasterplanEntries, planId, rasterIds);
				break;
			default:
				createGdalConfig(workspaceLocation, archive, rasterplanEntries, planId, rasterIds);
				break;
		}

		return rasterIds;
	}

	private void createGeotiffConfig(File workspaceLocation, XPlanArchiveContentAccess archive,
			List<ArchiveEntry> rasterplanEntries, int planId, List<String> rasterIds)
			throws IOException, JAXBException {
		for (ArchiveEntry entry : rasterplanEntries) {
			String entryName = entry.getName();
			LOG.debug("Raster data entry {} ", entryName);
			copyRasterFileAndCreateConfiguration(workspaceLocation, archive, planId, rasterIds, entryName);
		}
	}

	private void createGdalConfig(File workspaceLocation, XPlanArchiveContentAccess archive,
			List<ArchiveEntry> rasterplanEntries, int planId, List<String> rasterIds)
			throws IOException, JAXBException {
		File archiveDirectory = unzipArchiveInTmpDirectory(archive);
		for (ArchiveEntry entry : rasterplanEntries) {
			String entryName = entry.getName();
			LOG.debug("Raster data entry {} ", entryName);
			copyRasterFileAndCreateConfiguration(workspaceLocation, archive, planId, rasterIds, entryName);
			File mainRasterFile = new File(archiveDirectory, entryName);
			Vector<?> referencedFiles = rasterAdapter.getReferencedFiles(mainRasterFile);
			if (referencedFiles != null) {
				for (Object referencedFile : referencedFiles) {
					File file = new File(referencedFile.toString());
					if (!file.equals(mainRasterFile)) {
						String newFileName = createDataFileName(planId, file.getName());
						File target = createTargetFile(workspaceLocation, newFileName);
						FileUtils.copyFile(file, target);
					}
				}
			}
		}
	}

	private void copyRasterFileAndCreateConfiguration(File workspaceLocation, XPlanArchiveContentAccess archive,
			int planId, List<String> rasterIds, String entryName) throws IOException, JAXBException {
		String dataFileName = createDataFileName(planId, entryName);
		File target = createTargetFile(workspaceLocation, dataFileName);
		FileUtils.copyInputStreamToFile(archive.retrieveInputStreamFor(entryName), target);
		String rasterId = createRasterId(dataFileName);
		rasterIds.add(rasterId);
		if (isDeegreeRasterType())
			rasterLayerManager.createRasterConfigurations(rasterId, dataFileName,
					managerConfiguration.getRasterLayerMinScaleDenominator(),
					managerConfiguration.getRasterLayerMaxScaleDenominator());
	}

	private String createRasterId(String dataFileName) {
		return dataFileName.replaceAll(".tiff?", "");
	}

	private List<String> collectRasterScanFiles(XPlanFeatureCollection fc) {
		List<String> scanFiles = new ArrayList<>();
		for (ExternalReference externalRef : fc.getExternalReferenceInfo().getRasterPlanBaseScans()) {
			scanFiles.add(externalRef.getReferenzUrl());
		}
		return scanFiles;
	}

	private boolean isDeegreeRasterType() {
		return RasterConfigurationType.gdal.equals(getRasterConfigurationTypeFromManagerConfig())
				|| geotiff.equals(getRasterConfigurationTypeFromManagerConfig());
	}

	private RasterConfigurationType getRasterConfigurationTypeFromManagerConfig() {
		return managerConfiguration != null ? managerConfiguration.getRasterConfigurationType()
				: RasterConfigurationType.gdal;
	}

	private String getRasterConfigurationCrsFromManagerConfig() {
		return managerConfiguration != null ? managerConfiguration.getRasterConfigurationCrs() : null;
	}

	private String createDataFileName(int planId, String fileName) {
		return planId + "_" + fileName;
	}

	private File createTargetFile(File workspaceLocation, String newFileName) {
		return new File(workspaceLocation, "data/" + newFileName);
	}

	private void logScanFiles(long begin, List<String> scanFiles) {
		long elapsed = System.currentTimeMillis() - begin;
		LOG.info("OK [{} ms]", elapsed);
		if (!scanFiles.isEmpty()) {
			LOG.info("Rasterscans:");
			for (String scanFile : scanFiles) {
				LOG.info(" - {}", scanFile);
			}
		}
	}

}
