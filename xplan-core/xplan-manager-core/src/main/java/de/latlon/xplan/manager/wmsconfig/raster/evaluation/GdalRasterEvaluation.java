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
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import org.gdal.osr.SpatialReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.manager.wmsconfig.raster.RasterUtils.unzipArchiveInTmpDirectory;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GdalRasterEvaluation implements RasterEvaluation {

	private static final Logger LOG = LoggerFactory.getLogger(GdalRasterEvaluation.class);

	private final GdalRasterAdapter rasterAdapter;

	private final String configuredRasterCrs;

	public GdalRasterEvaluation(String configuredRasterCrs) {
		this.rasterAdapter = new GdalRasterAdapter();
		this.configuredRasterCrs = configuredRasterCrs;
	}

	@Override
	public List<RasterEvaluationResult> evaluate(XPlanArchiveContentAccess archive,
			List<ArchiveEntry> rasterplanZipEntries) throws IOException {

		List<RasterEvaluationResult> results = new ArrayList<>();
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

	@Override
	public boolean isSupportedFile(String fileName) {
		return true;
	}

	private RasterEvaluationResult evaluateRaster(ArchiveEntry zipEntry, File archiveDirectory) {
		String entryName = zipEntry.getName();
		LOG.info("Rasterdatei mit Namen {} gefunden.", entryName);
		File mainRasterFile = new File(archiveDirectory, entryName);
		LOG.trace("Raster was copied to {}.", mainRasterFile);
		String rasterCrs = rasterAdapter.getRasterCrs(mainRasterFile);
		if (rasterCrs != null) {
			LOG.info("Koordinatensystem des Rasters: {}", rasterCrs);
			boolean isCrsSet = rasterCrs != null && !rasterCrs.isEmpty();
			boolean isConfiguredCrs = compareConfiguredCrsWithRasterCrs(rasterCrs, configuredRasterCrs);
			mainRasterFile.delete();
			return new RasterEvaluationResult(entryName, retrieveAuthorityIfAvailable(rasterCrs), configuredRasterCrs,
					isCrsSet, isConfiguredCrs, true);
		}
		return new RasterEvaluationResult(entryName, null, configuredRasterCrs, false, false, false);
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

}
