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
package de.latlon.xplan.manager.wmsconfig.raster.evaluation;

import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.manager.web.shared.RasterEvaluationResult;
import de.latlon.xplan.manager.workspace.WorkspaceException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.manager.wmsconfig.raster.RasterUtils.findRasterplanZipEntries;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanRasterEvaluator {

	private RasterEvaluation rasterEvaluation;

	/**
	 * @param rasterEvaluation used to evaluate the raster, never <code>null</code>
	 * @throws WorkspaceException
	 */
	public XPlanRasterEvaluator(RasterEvaluation rasterEvaluation) {
		this.rasterEvaluation = rasterEvaluation;
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
		List<String> scanFiles = collectRasterScanFiles(rasterPlanBaseAndUpdateScans);
		List<ArchiveEntry> rasterplanZipEntries = findRasterplanZipEntries(archive, scanFiles);
		return rasterEvaluation.evaluate(archive, rasterplanZipEntries);
	}

	private List<String> collectRasterScanFiles(List<ExternalReference> externalReferenceInfo) {
		List<String> scanFiles = new ArrayList<>();
		for (ExternalReference externalRef : externalReferenceInfo) {
			scanFiles.add(externalRef.getReferenzUrl());
		}
		return scanFiles;
	}

}
