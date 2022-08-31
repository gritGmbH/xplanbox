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

import static de.latlon.xplan.manager.wmsconfig.ConfigWriterUtils.detectType;
import static de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType.geotiff;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.DEFAULT_XPLANSYN_WMS_WORKSPACE;
import static de.latlon.xplan.manager.workspace.WorkspaceUtils.instantiateWorkspace;
import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.deegree.commons.config.DeegreeWorkspace;
import org.gdal.gdal.Dataset;
import org.gdal.gdal.gdal;
import org.gdal.osr.SpatialReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.manager.configuration.ConfigurationException;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.wmsconfig.WmsWorkspaceWrapper;
import de.latlon.xplan.manager.wmsconfig.raster.WorkspaceRasterLayerManager.RasterConfigurationType;
import de.latlon.xplan.manager.workspace.WorkspaceException;

/**
 * An instance of XPlanRasterManager provides the service methods to manage raster files
 * part of XPlan. Supports XPlan version 2, 3, 4.1, and 4.2.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanRasterManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanRasterManager.class);

	private static boolean gdalSuccessfullInitialized = false;

	private final WmsWorkspaceWrapper wmsWorkspaceWrapper;

	// TODO: must never null!
	private final ManagerConfiguration managerConfiguration;

	private final WorkspaceRasterThemeManager rasterThemeManager;

	private final WorkspaceRasterLayerManager rasterLayerManager;

	static {
		try {
			gdal.AllRegister();
			LOG.info("Installed GDAL JNI Version : " + gdal.VersionInfo());
			gdalSuccessfullInitialized = true;
		}
		catch (Throwable e) {
			LOG.warn("Registration of GDAL JNI adapter failed: " + e.getMessage(), e);
			LOG.warn("GDAL raster configurations are not supported. "
					+ "In the managerConfiguration.properties file the rasterConfigurationType must not be gdal!");
		}
	}

	/**
	 * Instantiates a {@link XPlanRasterManager} expecting a workspace with name
	 * 'xplansyn-wms-workspace'
	 * @throws WorkspaceException
	 */
	public XPlanRasterManager() throws WorkspaceException {
		this(instantiateDefaultWorkspaceWrapper(), null);
	}

	/**
	 * Instantiates a {@link XPlanRasterManager} with workspace and manager configuration.
	 * @param wmsWorkspaceWrapper the wms workspace, may be <code>null</code> (workspace
	 * with default name is used
	 * @param managerConfiguration configuration of the manager, should not be
	 * <code>null</code>
	 * @throws WorkspaceException
	 */
	public XPlanRasterManager(WmsWorkspaceWrapper wmsWorkspaceWrapper, ManagerConfiguration managerConfiguration)
			throws WorkspaceException {
		this.wmsWorkspaceWrapper = wmsWorkspaceWrapper;
		this.managerConfiguration = managerConfiguration;
		this.rasterThemeManager = new WorkspaceRasterThemeManager(wmsWorkspaceWrapper);
		this.rasterLayerManager = new WorkspaceRasterLayerManager(wmsWorkspaceWrapper.getLocation());
	}

	/**
	 * @return <code>true</code> if gdal was correctly initialized, <code>false</code>
	 * otherwise
	 */
	public static boolean isGdalSuccessfullInitialized() {
		return gdalSuccessfullInitialized;
	}

	/**
	 * Adds the specified layer to a user-defined category using a custom name and title.
	 * @param type must be one of bplan, lplan, rplan or fplan, never <code>null</code>
	 * @param layerId id of the layer, must not be <code>null</code> (and already exist in
	 * themes file)
	 * @param tiffid
	 * @param layerName name of the layer in the category, must not be <code>null</code>
	 * @param layerTitle title of the layer in the category, must not be <code>null</code>
	 * @param categoryName category to place the new layer, must not be <code>null</code>
	 * (and already exist)
	 */
	public void addLayer(String type, String layerId, String tiffid, String layerName, String layerTitle,
			String categoryName) {
		try {
			rasterThemeManager.addUserLayer(type, layerId, layerName, layerTitle, categoryName);
		}
		catch (Exception e) {
			LOG.trace("Layer could not be created!", e);
			LOG.error("Benutzerdefinierte Ebene kann nicht angelegt werden: {}", e.getLocalizedMessage());
		}
	}

	/**
	 * Removes the layer with the passed name.
	 * @param type must be one of bplan, lplan, rplan or fplan, never <code>null</code>
	 * @param layerName name of the layer to remove, should not be <code>null</code>
	 */
	public void removeLayer(String type, String layerName) {
		try {
			rasterThemeManager.removeUserLayer(type, layerName);
		}
		catch (Exception e) {
			LOG.trace("Layer " + layerName + " could not be removed!", e);
			LOG.error("Benutzerdefinierte Ebene kann nicht gelöscht werden: {}", e.getLocalizedMessage());
		}
	}

	/**
	 * Creates a new category with the passed name and title. If the name of the
	 * uppercategory is not <code>null</code> the new category will be created as
	 * subcategory of this.
	 * @param type must be one of bplan, lplan, rplan or fplan, never <code>null</code>
	 * @param name of the category to add, never <code>null</code>
	 * @param title of the category to add, never <code>null</code>
	 * @param uppercategory name of an existing category, may be <code>null</code> if the
	 * new category has no upper category
	 */
	public void addCategory(String type, String name, String title, String uppercategory) {
		try {
			rasterThemeManager.addCategory(type, name, title, uppercategory);
		}
		catch (Exception e) {
			LOG.trace("Category " + name + " could not be added!", e);
			LOG.error("Kategorie kann nicht angelegt werden: {} ", e.getLocalizedMessage());
		}
	}

	/**
	 * Removes the category with the passed name.
	 * @param type must be one of bplan, lplan, rplan or fplan, never <code>null</code>
	 * @param categoryName to remove, should not be <code>null</code>
	 */
	public void removeCategory(String type, String categoryName) {
		try {
			rasterThemeManager.removeCategory(type, categoryName);
		}
		catch (Exception e) {
			LOG.trace("Category " + categoryName + " could not be removed!", e);
			LOG.error("Kategorie kann nicht gelöscht werden: {}", e.getLocalizedMessage());
		}
	}

	/**
	 * Moves the layer with the passed id to the category with the passed name.
	 * @param type must be one of bplan, lplan, rplan or fplan, never <code>null</code>
	 * @param layer name of the layer to move, should not be <code>null</code>
	 * @param category name of the category the layer should be moved to, should not be
	 * <code>null</code>
	 */
	public void moveLayer(String type, String layer, String category) {
		try {
			rasterThemeManager.moveLayer(type, layer, category);
		}
		catch (Exception e) {
			LOG.trace("Layer " + layer + " could not be moved to " + category + "!", e);
			LOG.error("Ebene kann nicht verschoben werden: {}", e.getLocalizedMessage());
		}
	}

	/**
	 * Removes the configuration of the plan with the given id.
	 * @param planId the id of the plan to remove, should not be <code>null</code>
	 */
	public void removeRasterLayers(String planId) {
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
			List<ArchiveEntry> rasterplanEntries = findRasterplanZipEntries(archive, scanFiles);

			List<String> rasterIds = copyRasterplanImageFilesToWmsWorkspace(wmsWorkspaceWrapper.getLocation(), archive,
					rasterplanEntries, planId);

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
		String statusType = detectType(type, planStatus);
		if (newPlanStatus != null) {
			String newStatusType = detectType(type, newPlanStatus);
			rasterThemeManager.moveLayers(statusType, newStatusType, Integer.toString(planId));
			statusType = newStatusType;
		}
	}

	/**
	 * Evaluates rasterdata referenced by the plan.
	 * @param archive containing the rasterdata to evaluate, never <code>null</code>
	 * @param planFeatureCollection featureCollection of the xplan, never
	 * <code>null</code>
	 * @return a list of evaluation results, one per raster, may be empty if plan does not
	 * contain rasterdata but never <code>null</code>
	 * @throws IOException if access to the archive fails
	 */
	public List<RasterEvaluationResult> evaluateRasterdata(XPlanArchiveContentAccess archive,
			XPlanFeatureCollection planFeatureCollection) throws IOException {
		ExternalReferenceInfo externalReferenceInfo = planFeatureCollection.getExternalReferenceInfo();
		return evaluateRasterdata(archive, externalReferenceInfo);
	}

	/**
	 * Evaluates rasterdata referenced by the plan.
	 * @param archive containing the rasterdata to evaluate, never <code>null</code>
	 * @param externalReferencesToEvaluate {@link ExternalReferenceInfo} to evaluate,
	 * never <code>null</code>
	 * @return a list of evaluation results, one per raster, may be empty if plan does not
	 * contain rasterdata but never <code>null</code>
	 * @throws IOException if access to the archive fails
	 */
	public List<RasterEvaluationResult> evaluateRasterdata(XPlanArchiveContentAccess archive,
			ExternalReferenceInfo externalReferencesToEvaluate) throws IOException {
		List<ExternalReference> rasterPlanBaseAndUpdateScans = externalReferencesToEvaluate
				.getRasterPlanBaseAndUpdateScans();
		switch (getRasterConfigurationTypeFromManagerConfig()) {
		case geotiff:
			return evaluateRasterdataGeotiffConfig(archive, rasterPlanBaseAndUpdateScans);
		default:
			return evaluateRasterGdalConfig(archive, rasterPlanBaseAndUpdateScans);
		}
	}

	/**
	 * Reorders the wms layer configuraton by the passed sort dates.
	 * @param planId2sortDate plan ids mapped to the new sort date, never
	 * <code>null</code>
	 * @throws Exception
	 */
	public void reorderWmsLayers(Map<String, Date> planId2sortDate) throws Exception {
		String rasterConfigurationCrs = getRasterConfigurationCrsFromManagerConfig();
		rasterThemeManager.reorderWmsLayers(planId2sortDate, rasterConfigurationCrs);
	}

	private static WmsWorkspaceWrapper instantiateDefaultWorkspaceWrapper() throws WorkspaceException {
		DeegreeWorkspace workspace = instantiateWorkspace(DEFAULT_XPLANSYN_WMS_WORKSPACE);
		return new WmsWorkspaceWrapper(workspace);
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

	private List<RasterEvaluationResult> evaluateRasterdataGeotiffConfig(XPlanArchiveContentAccess archive,
			List<ExternalReference> externalReferencesToEvaluate) {
		List<RasterEvaluationResult> results = new ArrayList<RasterEvaluationResult>();
		List<String> scanFiles = collectRasterScanFiles(externalReferencesToEvaluate);
		List<ArchiveEntry> findRasterplanZipEntries = findRasterplanZipEntries(archive, scanFiles);
		for (ArchiveEntry entry : findRasterplanZipEntries) {
			String entryName = entry.getName();
			String lowerCaseEntryName = entryName.toLowerCase();
			boolean supportedImageFormat = false;
			if (lowerCaseEntryName.endsWith("tif") || lowerCaseEntryName.endsWith("tiff"))
				supportedImageFormat = true;
			results.add(new RasterEvaluationResult(entryName, null, getRasterConfigurationCrsFromManagerConfig(), false,
					true, supportedImageFormat));
		}
		return results;
	}

	private List<ArchiveEntry> findRasterplanZipEntries(XPlanArchiveContentAccess archive, List<String> scanFiles) {
		List<ArchiveEntry> entries = new ArrayList<ArchiveEntry>();
		for (String scanFile : scanFiles) {
			if (scanFile != null) {
				ArchiveEntry entry = archive.getEntry(scanFile);
				if (entry == null) {
					throw new RuntimeException("Rasterscan-Datei:" + scanFile + " ist nicht im Archiv vorhanden.");
				}
				if (geotiff.equals(getRasterConfigurationTypeFromManagerConfig())) {
					String name = entry.getName().toLowerCase();
					if (!name.endsWith("tif") && !name.endsWith("tiff")) {
						LOG.info("Ignoriere Datei '{}'. Keine TIFF-Datei.", entry.getName());
					}
				}
				entries.add(entry);
			}
		}
		return entries;
	}

	private List<RasterEvaluationResult> evaluateRasterGdalConfig(XPlanArchiveContentAccess archive,
			List<ExternalReference> externalReferencesToEvaluate) throws IOException {
		List<RasterEvaluationResult> results = new ArrayList<RasterEvaluationResult>();
		List<String> scanFiles = collectRasterScanFiles(externalReferencesToEvaluate);
		List<ArchiveEntry> rasterplanZipEntries = findRasterplanZipEntries(archive, scanFiles);
		if (!rasterplanZipEntries.isEmpty()) {
			File archiveDirectory = unzipArchiveInTmpDirectory(archive);
			for (ArchiveEntry zipEntry : rasterplanZipEntries) {
				RasterEvaluationResult result = evaluateRaster(archive, zipEntry, archiveDirectory);
				results.add(result);
			}
			archiveDirectory.delete();
		}
		return results;
	}

	private RasterEvaluationResult evaluateRaster(XPlanArchiveContentAccess archive, ArchiveEntry zipEntry,
			File archiveDirectory) throws IOException {
		String entryName = zipEntry.getName();
		LOG.info("Rasterdatei mit Namen {} gefunden.", entryName);
		File mainRasterFile = new File(archiveDirectory, entryName);
		LOG.trace("Raster was copied to {}.", mainRasterFile);
		String configuredCrs = getRasterConfigurationCrsFromManagerConfig();
		Dataset dataset = gdal.OpenShared(mainRasterFile.getAbsolutePath());
		if (dataset != null) {
			String rasterCrs = dataset.GetProjectionRef();
			LOG.info("Koordinatensystem des Rasters: {}", rasterCrs);
			boolean isCrsSet = rasterCrs != null && !rasterCrs.isEmpty();
			boolean isConfiguredCrs = compareConfiguredCrsWithRasterCrs(rasterCrs, configuredCrs);
			mainRasterFile.delete();
			return new RasterEvaluationResult(entryName, retrieveAuthorityIfAvailable(rasterCrs), configuredCrs,
					isCrsSet, isConfiguredCrs, true);
		}
		return new RasterEvaluationResult(entryName, null, configuredCrs, false, false, false);
	}

	private List<String> copyRasterplanImageFilesToWmsWorkspace(File workspaceLocation,
			XPlanArchiveContentAccess archive, List<ArchiveEntry> rasterplanEntries, int planId)
			throws IOException, JAXBException {
		RasterConfigurationType rasterConfigurationType = getRasterConfigurationTypeFromManagerConfig();
		String rasterConfigurationCrs = getRasterConfigurationCrsFromManagerConfig();
		WorkspaceRasterLayerManager layerMgr = new WorkspaceRasterLayerManager(workspaceLocation,
				rasterConfigurationType, rasterConfigurationCrs);
		List<String> rasterIds = new ArrayList<String>();

		switch (rasterConfigurationType) {
		case geotiff:
			createGeotiffConfig(workspaceLocation, archive, rasterplanEntries, planId, layerMgr, rasterIds);
			break;
		default:
			createGdalConfig(workspaceLocation, archive, rasterplanEntries, planId, layerMgr, rasterIds);
			break;
		}

		return rasterIds;
	}

	private void createGeotiffConfig(File workspaceLocation, XPlanArchiveContentAccess archive,
			List<ArchiveEntry> rasterplanEntries, int planId, WorkspaceRasterLayerManager layerMgr,
			List<String> rasterIds) throws IOException, JAXBException {
		for (ArchiveEntry entry : rasterplanEntries) {
			String entryName = entry.getName();
			LOG.debug("Raster data entry {} ", entryName);
			copyRasterFileAndCreateConfiguration(workspaceLocation, archive, planId, layerMgr, rasterIds, entryName);
		}
	}

	private void createGdalConfig(File workspaceLocation, XPlanArchiveContentAccess archive,
			List<ArchiveEntry> rasterplanEntries, int planId, WorkspaceRasterLayerManager layerMgr,
			List<String> rasterIds) throws IOException, JAXBException {
		File archiveDirectory = unzipArchiveInTmpDirectory(archive);
		for (ArchiveEntry entry : rasterplanEntries) {
			String entryName = entry.getName();
			LOG.debug("Raster data entry {} ", entryName);
			copyRasterFileAndCreateConfiguration(workspaceLocation, archive, planId, layerMgr, rasterIds, entryName);
			File mainRasterFile = new File(archiveDirectory, entryName);
			Dataset dataset = gdal.OpenShared(mainRasterFile.getAbsolutePath());
			if (dataset != null) {
				Vector<?> referencedFiles = dataset.GetFileList();
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
			int planId, WorkspaceRasterLayerManager layerMgr, List<String> rasterIds, String entryName)
			throws IOException, JAXBException {
		String dataFileName = createDataFileName(planId, entryName);
		File target = createTargetFile(workspaceLocation, dataFileName);
		FileUtils.copyInputStreamToFile(archive.retrieveInputStreamFor(entryName), target);
		String rasterId = createRasterId(dataFileName);
		rasterIds.add(rasterId);
		layerMgr.createRasterConfigurations(rasterId, dataFileName,
				managerConfiguration.getRasterLayerMinScaleDenominator(),
				managerConfiguration.getRasterLayerMaxScaleDenominator());
	}

	private String createRasterId(String dataFileName) {
		return dataFileName.replaceAll(".tiff?", "");
	}

	private List<String> collectRasterScanFiles(List<ExternalReference> externalReferenceInfo) {
		List<String> scanFiles = new ArrayList<String>();
		for (ExternalReference externalRef : externalReferenceInfo) {
			scanFiles.add(externalRef.getReferenzUrl());
		}
		return scanFiles;
	}

	private List<String> collectRasterScanFiles(XPlanFeatureCollection fc) {
		List<String> scanFiles = new ArrayList<String>();
		for (ExternalReference externalRef : fc.getExternalReferenceInfo().getRasterPlanBaseScans()) {
			scanFiles.add(externalRef.getReferenzUrl());
		}
		for (ExternalReference externalRef : fc.getExternalReferenceInfo().getRasterPlanUpdateScans()) {
			scanFiles.add(externalRef.getReferenzUrl());
		}
		return scanFiles;
	}

	private File unzipArchiveInTmpDirectory(XPlanArchiveContentAccess archive) throws IOException {
		List<? extends ArchiveEntry> archiveEntries = archive.getZipFileEntries();
		File archiveDirectory = Files.createTempDirectory("xplanbox-archive").toFile();
		for (ArchiveEntry zipEntry : archiveEntries) {
			copyToTempFile(archive, zipEntry.getName(), archiveDirectory);
		}
		return archiveDirectory;
	}

	private void copyToTempFile(XPlanArchiveContentAccess archive, String entryName, File archiveDirectory)
			throws IOException {
		InputStream content = archive.retrieveInputStreamFor(entryName);
		File writeRasterIn = new File(archiveDirectory, entryName);
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(writeRasterIn);
			IOUtils.copy(content, outputStream);
		}
		finally {
			closeQuietly(content);
			closeQuietly(outputStream);
		}
	}

	private boolean compareConfiguredCrsWithRasterCrs(String rasterCrs, String configuredCrs) {
		SpatialReference rasterReference = new SpatialReference(rasterCrs);

		SpatialReference configuredReference = new SpatialReference();
		configuredReference.ImportFromEPSG(asEpsgCode(configuredCrs));

		int isSame = rasterReference.IsSame(configuredReference);
		return isSame == 1;
	}

	private String retrieveAuthorityIfAvailable(String rasterCrs) {
		SpatialReference rasterReference = new SpatialReference(rasterCrs);
		String key;
		if (rasterReference.IsGeographic() == 1)
			key = "GEOGCS";
		else if (rasterReference.IsProjected() == 1)
			key = "PROJCS";
		else
			return rasterCrs;
		return rasterReference.GetAuthorityName(key) + ":" + rasterReference.GetAuthorityCode(key);
	}

	private int asEpsgCode(String rasterCrs) {
		String epsgCode = rasterCrs.substring(rasterCrs.indexOf(":") + 1);
		return Integer.parseInt(epsgCode);
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
