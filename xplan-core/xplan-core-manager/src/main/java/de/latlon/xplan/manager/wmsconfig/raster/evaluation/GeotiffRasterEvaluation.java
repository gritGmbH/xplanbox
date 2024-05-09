/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster.evaluation;

import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeotiffRasterEvaluation implements RasterEvaluation {

	private static final Logger LOG = LoggerFactory.getLogger(GeotiffRasterEvaluation.class);

	private final String configuredRasterCrs;

	public GeotiffRasterEvaluation(String configuredRasterCrs) {
		this.configuredRasterCrs = configuredRasterCrs;
	}

	@Override
	public List<RasterEvaluationResult> evaluate(XPlanArchiveContentAccess archive,
			List<ArchiveEntry> rasterplanZipEntries) throws IOException {
		List<RasterEvaluationResult> results = new ArrayList<>();
		for (ArchiveEntry entry : rasterplanZipEntries) {
			String entryName = entry.getName();
			String lowerCaseEntryName = entryName.toLowerCase();
			boolean supportedImageFormat = false;
			if (lowerCaseEntryName.endsWith("tif") || lowerCaseEntryName.endsWith("tiff"))
				supportedImageFormat = true;
			results.add(new RasterEvaluationResult(entryName, null, configuredRasterCrs, false, true,
					supportedImageFormat));
		}
		return results;
	}

	@Override
	public boolean isSupportedFile(String fileName) {
		String name = fileName.toLowerCase();
		if (!name.endsWith("tif") && !name.endsWith("tiff")) {
			LOG.info("Ignoriere Datei '{}'. Keine TIFF-Datei.", fileName);
			return false;
		}
		return true;
	}

}
