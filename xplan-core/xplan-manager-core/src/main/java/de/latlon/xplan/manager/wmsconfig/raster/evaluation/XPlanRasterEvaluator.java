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
package de.latlon.xplan.manager.wmsconfig.raster.evaluation;

import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.manager.configuration.ManagerConfiguration;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.wmsconfig.raster.XPlanRasterManager;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import de.latlon.xplan.manager.workspace.WorkspaceException;
import org.gdal.osr.SpatialReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.manager.wmsconfig.raster.RasterUtils.findRasterplanZipEntries;
import static de.latlon.xplan.manager.wmsconfig.raster.RasterUtils.unzipArchiveInTmpDirectory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanRasterEvaluator {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanRasterEvaluator.class);

	private final GdalRasterAdapter rasterAdapter;

	private final ManagerConfiguration managerConfiguration;

	/**
	 * Instantiates a {@link XPlanRasterManager} with workspace and manager configuration.
	 * @param managerConfiguration configuration of the manager, should not be
	 * <code>null</code>
	 * @throws WorkspaceException
	 */
	public XPlanRasterEvaluator(GdalRasterAdapter rasterAdapter, ManagerConfiguration managerConfiguration) {
		this.rasterAdapter = rasterAdapter;
		this.managerConfiguration = managerConfiguration;
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
		switch (managerConfiguration.getRasterConfigurationType()) {
			case geotiff:
				return evaluateRasterdataGeotiffConfig(archive, rasterPlanBaseAndUpdateScans);
			default:
				return evaluateRasterGdalConfig(archive, rasterPlanBaseAndUpdateScans);
		}
	}

	private List<RasterEvaluationResult> evaluateRasterdataGeotiffConfig(XPlanArchiveContentAccess archive,
			List<ExternalReference> externalReferencesToEvaluate) {
		List<RasterEvaluationResult> results = new ArrayList<>();
		List<String> scanFiles = collectRasterScanFiles(externalReferencesToEvaluate);
		List<ArchiveEntry> findRasterplanZipEntries = findRasterplanZipEntries(archive, scanFiles,
				managerConfiguration.getRasterConfigurationType());
		for (ArchiveEntry entry : findRasterplanZipEntries) {
			String entryName = entry.getName();
			String lowerCaseEntryName = entryName.toLowerCase();
			boolean supportedImageFormat = false;
			if (lowerCaseEntryName.endsWith("tif") || lowerCaseEntryName.endsWith("tiff"))
				supportedImageFormat = true;
			results.add(new RasterEvaluationResult(entryName, null, managerConfiguration.getRasterConfigurationCrs(),
					false, true, supportedImageFormat));
		}
		return results;
	}

	private List<RasterEvaluationResult> evaluateRasterGdalConfig(XPlanArchiveContentAccess archive,
			List<ExternalReference> externalReferencesToEvaluate) throws IOException {
		List<RasterEvaluationResult> results = new ArrayList<>();
		List<String> scanFiles = collectRasterScanFiles(externalReferencesToEvaluate);
		List<ArchiveEntry> rasterplanZipEntries = findRasterplanZipEntries(archive, scanFiles,
				managerConfiguration.getRasterConfigurationType());
		if (!rasterplanZipEntries.isEmpty()) {
			File archiveDirectory = unzipArchiveInTmpDirectory(archive);
			for (ArchiveEntry zipEntry : rasterplanZipEntries) {
				RasterEvaluationResult result = evaluateRaster(zipEntry, archiveDirectory);
				results.add(result);
			}
			archiveDirectory.delete();
		}
		return results;
	}

	private RasterEvaluationResult evaluateRaster(ArchiveEntry zipEntry, File archiveDirectory) {
		String entryName = zipEntry.getName();
		LOG.info("Rasterdatei mit Namen {} gefunden.", entryName);
		File mainRasterFile = new File(archiveDirectory, entryName);
		LOG.trace("Raster was copied to {}.", mainRasterFile);
		String configuredCrs = managerConfiguration.getRasterConfigurationCrs();
		String rasterCrs = rasterAdapter.getRasterCrs(mainRasterFile);
		if (rasterCrs != null) {
			LOG.info("Koordinatensystem des Rasters: {}", rasterCrs);
			boolean isCrsSet = rasterCrs != null && !rasterCrs.isEmpty();
			boolean isConfiguredCrs = compareConfiguredCrsWithRasterCrs(rasterCrs, configuredCrs);
			mainRasterFile.delete();
			return new RasterEvaluationResult(entryName, retrieveAuthorityIfAvailable(rasterCrs), configuredCrs,
					isCrsSet, isConfiguredCrs, true);
		}
		return new RasterEvaluationResult(entryName, null, configuredCrs, false, false, false);
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

	private List<String> collectRasterScanFiles(List<ExternalReference> externalReferenceInfo) {
		List<String> scanFiles = new ArrayList<>();
		for (ExternalReference externalRef : externalReferenceInfo) {
			scanFiles.add(externalRef.getReferenzUrl());
		}
		return scanFiles;
	}

}
