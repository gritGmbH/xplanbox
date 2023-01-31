/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.manager.configuration.ConfigurationException;
import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.wmsconfig.raster.config.RasterConfigManager;
import de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.manager.wmsconfig.raster.RasterUtils.findRasterplanZipEntries;

/**
 * An instance of XPlanRasterManager provides the service methods to manage raster files
 * part of XPlan.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanRasterManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanRasterManager.class);

	private final RasterStorage rasterStorage;

	private final RasterConfigManager rasterConfigManager;

	/**
	 * Instantiates a {@link XPlanRasterManager} with workspace and manager configuration.
	 * @param rasterStorage the RasterStorage used to write raster files, never
	 * <code>null</code>
	 * @param rasterConfigManager the RasterConfigManager used to write config files,
	 * never <code>null</code>
	 * @throws WorkspaceException
	 */
	public XPlanRasterManager(RasterStorage rasterStorage, RasterConfigManager rasterConfigManager)
			throws WorkspaceException {
		this.rasterStorage = rasterStorage;
		this.rasterConfigManager = rasterConfigManager;
	}

	/**
	 * Removes the configuration of the plan with the given id.
	 * @param planId the id of the plan to remove, should not be <code>null</code>
	 */
	public void removeRasterLayers(String planId) {
		try {
			rasterConfigManager.removeRasterLayers(planId);
			rasterStorage.deleteRasterFiles(planId);
		}
		catch (IOException e) {
			LOG.error("Löschen der Raster-Datei/Konfiguration fehlgeschlagen: {}", e.getMessage());
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
					rasterConfigManager.removeRasterLayer(planId, rasterId);
					rasterStorage.deleteRasterFiles(planId, rasterId);
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

			List<String> rasterIds = copyRasterfilesAndCreateConfig(archive, rasterplanEntries, planId);
			rasterConfigManager.insertRasterLayers(planId, moreRecentPlanId, type, planStatus, newPlanStatus, rasterIds,
					sortDate);
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
		rasterConfigManager.updateRasterLayers(planId, type, planStatus, newPlanStatus);
	}

	/**
	 * Reorders the wms layer configuration by the passed sort dates.
	 * @param planId2sortDate plan ids mapped to the new sort date, never
	 * <code>null</code>
	 * @throws Exception
	 */
	public void reorderWmsLayers(Map<String, Date> planId2sortDate) throws Exception {
		rasterConfigManager.reorderWmsLayers(planId2sortDate);
	}

	private List<String> copyRasterfilesAndCreateConfig(XPlanArchiveContentAccess archive,
			List<ArchiveEntry> rasterplanEntries, int planId) throws IOException, JAXBException, StorageException {
		List<String> rasterIds = new ArrayList<>();
		for (ArchiveEntry entry : rasterplanEntries) {
			String entryName = entry.getName();
			LOG.debug("Raster data entry {} ", entryName);
			String rasterFileName = rasterStorage.addRasterFile(planId, entryName, archive);
			String rasterId = createRasterId(rasterFileName);
			rasterIds.add(rasterId);
			rasterConfigManager.createConfiguration(rasterId, rasterFileName);
		}
		return rasterIds;
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
