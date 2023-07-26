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
package de.latlon.xplan.manager.edit;

import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceInfoBuilder;
import de.latlon.xplan.manager.transaction.AttachmentUrlHandler;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import net.sf.saxon.functions.Empty;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static de.latlon.xplan.manager.web.shared.edit.RasterReferenceType.SCAN;

/**
 * Creates/Extracts {@link ExternalReferenceInfo} which are newly or not longer
 * referenced.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ExternalReferenceUtils {

	/**
	 * Detects all external references (rasterPlanBase and rasterPlanChanges) not longer
	 * referenced by the plan.
	 * @param externalReferencesModified the {@link ExternalReferenceInfo} from the
	 * modified plan (after update), never <code>null</code>
	 * @param uploadedArtefacts the uploaded artifacts, may be {@link Empty} but never
	 * <code>null</code>
	 * @param externalReferencesOriginal the {@link ExternalReferenceInfo} from the
	 * original plan (before update), never <code>null</code>
	 * @return the {@link ExternalReferenceInfo} with all references not longer referenced
	 * by the plan, never <code>null</code>
	 */
	public static ExternalReferenceInfo createExternalRefRemovedOrUpdated(
			ExternalReferenceInfo externalReferencesModified, List<File> uploadedArtefacts,
			ExternalReferenceInfo externalReferencesOriginal) {
		ExternalReferenceInfoBuilder externalReferenceInfoBuilder = new ExternalReferenceInfoBuilder();

		List<ExternalReference> nonRastersOriginal = externalReferencesOriginal.getNonRasterRefs();
		List<ExternalReference> nonRastersModified = externalReferencesModified.getNonRasterRefs();
		for (ExternalReference nonRasterOriginal : nonRastersOriginal) {
			if (wasRemovedOrRequiresUpdate(nonRasterOriginal, nonRastersModified, uploadedArtefacts))
				externalReferenceInfoBuilder.addNonRasterReference(nonRasterOriginal);
		}
		List<ExternalReference> baseScansOriginal = externalReferencesOriginal.getRasterPlanBaseScans();
		List<ExternalReference> baseScansModified = externalReferencesModified.getRasterPlanBaseScans();
		for (ExternalReference baseScanOriginal : baseScansOriginal) {
			if (wasRemovedOrRequiresUpdate(baseScanOriginal, baseScansModified, uploadedArtefacts))
				externalReferenceInfoBuilder.addRasterPlanBaseScan(baseScanOriginal);
		}
		List<ExternalReference> updateScansOriginal = externalReferencesOriginal.getRasterPlanUpdateScans();
		List<ExternalReference> updateScansModified = externalReferencesModified.getRasterPlanUpdateScans();
		for (ExternalReference updateScanOriginal : updateScansOriginal) {
			if (wasRemovedOrRequiresUpdate(updateScanOriginal, updateScansModified, uploadedArtefacts))
				externalReferenceInfoBuilder.addRasterPlanBaseScan(updateScanOriginal);
		}
		return externalReferenceInfoBuilder.build();
	}

	/**
	 * Detects all external references (rasterPlanBase and rasterPlanChanges) referenced
	 * by the plan after update (but not before).
	 * @param externalReferences containing the actual referenced raster, never
	 * <code>null</code>
	 * @param uploadedArtefacts the uploaded artifacts, may be {@link Empty} but never
	 * <code>null</code>
	 * @return
	 * @return the {@link ExternalReferenceInfo} with all references referenced by the
	 * plan after the update (but not before), never <code>null</code>
	 */
	public static ExternalReferenceInfo createExternalRefAddedOrUpdated(ExternalReferenceInfo externalReferences,
			List<File> uploadedArtefacts) {
		ExternalReferenceInfoBuilder externalReferenceInfoBuilder = new ExternalReferenceInfoBuilder();
		for (ExternalReference nonRasterRef : externalReferences.getNonRasterRefs()) {
			if (wasUploaded(nonRasterRef, uploadedArtefacts))
				externalReferenceInfoBuilder.addNonRasterReference(nonRasterRef);
		}
		for (ExternalReference baseScan : externalReferences.getRasterPlanBaseScans()) {
			if (wasUploaded(baseScan, uploadedArtefacts))
				externalReferenceInfoBuilder.addRasterPlanBaseScan(baseScan);
		}
		for (ExternalReference rasterChange : externalReferences.getRasterPlanUpdateScans()) {
			if (wasUploaded(rasterChange, uploadedArtefacts))
				externalReferenceInfoBuilder.addRasterPlanUpdateScan(rasterChange);
		}
		return externalReferenceInfoBuilder.build();
	}

	/**
	 * Detects all external references (rasterPlanBase and rasterPlanChanges) referenced
	 * by the plan after update (but not before).
	 * @param planToEdit containing the actual referenced raster, never <code>null</code>
	 * @param uploadedArtefacts the uploaded artifacts, may be {@link Empty} but never
	 * <code>null</code>
	 * @return
	 * @return the {@link ExternalReferenceInfo} with all references referenced by the
	 * plan after the update (but not before), never <code>null</code>
	 */
	public static ExternalReferenceInfo createExternalRefAddedOrUpdated(XPlanToEdit planToEdit,
			List<File> uploadedArtefacts) {
		ExternalReferenceInfoBuilder externalReferenceInfoBuilder = new ExternalReferenceInfoBuilder();
		planToEdit.getRasterBasis().forEach(rasterBasis -> {
			for (RasterReference rasterReference : rasterBasis.getRasterReferences()) {
				String referenceUrl = rasterReference.getReference();
				if (SCAN.equals(rasterReference.getType()) && wasUploaded(referenceUrl, uploadedArtefacts)) {
					externalReferenceInfoBuilder.addRasterPlanBaseScan(new ExternalReference(referenceUrl));
				}
			}
		});
		return externalReferenceInfoBuilder.build();
	}

	/**
	 * Detects all external references not longer referenced by the plan.
	 * @param attachmentUrlHandler used to get original filenames, may be
	 * <code>null</code>
	 * @param planId required to get original filenames if the attachmentUrlHandler is
	 * passed, may be <code>null</code>
	 * @param externalReferencesModified the {@link ExternalReferenceInfo} from the
	 * modified plan (after update), never <code>null</code>
	 * @param externalReferencesOriginal the {@link ExternalReferenceInfo} from the
	 * original plan (before update), never <code>null</code>
	 * @param originalFileNames
	 * @return the {@link ExternalReferenceInfo} with all references not longer referenced
	 * by the plan, never <code>null</code>
	 */
	public static Set<String> collectRemovedRefFileNames(AttachmentUrlHandler attachmentUrlHandler, String planId,
			ExternalReferenceInfo externalReferencesModified, ExternalReferenceInfo externalReferencesOriginal,
			List<String> originalFileNames) {
		Set<String> removedRefs = new HashSet<>();

		List<ExternalReference> externalRefsOriginal = externalReferencesOriginal.getAllReferences();
		List<ExternalReference> externalRefModified = externalReferencesModified.getAllReferences();
		for (ExternalReference externalRefOriginal : externalRefsOriginal) {
			String ref = externalRefOriginal.getReferenzUrl();
			if (ref != null && !isReferenced(ref, externalRefModified))
				removedRefs.add(findOriginalFileName(attachmentUrlHandler, planId, originalFileNames, ref));
			String georef = externalRefOriginal.getGeoRefUrl();
			if (georef != null && !isReferenced(georef, externalRefModified))
				removedRefs.add(findOriginalFileName(attachmentUrlHandler, planId, originalFileNames, georef));
		}

		return removedRefs;
	}

	public static Map<String, String> collectAddedRefFileNames(AttachmentUrlHandler attachmentUrlHandler, String planId,
			ExternalReferenceInfo externalReferencesModified, ExternalReferenceInfo externalReferencesOriginal,
			List<String> uploadedFileNames) {
		Map<String, String> addedRefs = new HashMap<>();

		List<ExternalReference> externalRefsOriginal = externalReferencesOriginal.getAllReferences();
		List<ExternalReference> externalRefsModified = externalReferencesModified.getAllReferences();
		for (ExternalReference externalRefModified : externalRefsModified) {
			String ref = externalRefModified.getReferenzUrl();
			String fileNameRef = findOriginalFileName(attachmentUrlHandler, planId, uploadedFileNames, ref);
			if (ref != null && !isReferenced(ref, externalRefsOriginal)
					&& wasUploadedFileName(fileNameRef, uploadedFileNames)) {
				addedRefs.put(ref, fileNameRef);
			}
			String georef = externalRefModified.getGeoRefUrl();
			String fileNameGeoRef = findOriginalFileName(attachmentUrlHandler, planId, uploadedFileNames, georef);
			if (georef != null && !isReferenced(georef, externalRefsOriginal)
					&& wasUploadedFileName(fileNameGeoRef, uploadedFileNames))
				addedRefs.put(georef, fileNameGeoRef);
		}
		return addedRefs;
	}

	private static String findOriginalFileName(AttachmentUrlHandler attachmentUrlHandler, String planId,
			List<String> originalFileNames, String ref) {
		if (attachmentUrlHandler != null) {
			for (String originalFileName : originalFileNames) {
				if (attachmentUrlHandler.isSameReference(planId, originalFileName, ref))
					return originalFileName;
			}
		}
		return ref;
	}

	private static boolean wasUploaded(ExternalReference externalRef, List<File> uploadedArtefacts) {
		if (externalRef != null) {
			String referenzUrl = externalRef.getReferenzUrl();
			return wasUploaded(referenzUrl, uploadedArtefacts);
		}
		return false;
	}

	private static boolean wasUploaded(String referenzUrl, List<File> uploadedArtefacts) {
		if (referenzUrl != null) {
			for (File file : uploadedArtefacts) {
				if (referenzUrl.equals(file.getName()))
					return true;
			}
		}
		return false;
	}

	private static boolean wasUploadedFileName(String referenzUrl, List<String> uploadedFileNames) {
		if (referenzUrl != null) {
			for (String fileName : uploadedFileNames) {
				if (referenzUrl.equals(fileName))
					return true;
			}
		}
		return false;
	}

	private static boolean wasRemovedOrRequiresUpdate(ExternalReference originalReference,
			List<ExternalReference> modifiedReferences, List<File> uploadedArtefacts) {
		if (originalReference != null && originalReference.getReferenzUrl() != null) {
			ExternalReference externalRefModifiedWithSameUrl = findExternalReferenceByReferenzUrl(originalReference,
					modifiedReferences);
			if (externalRefModifiedWithSameUrl == null)
				return true;
			else if (wasUploaded(externalRefModifiedWithSameUrl, uploadedArtefacts))
				return true;
		}
		return false;
	}

	private static ExternalReference findExternalReferenceByReferenzUrl(ExternalReference referenceToFind,
			List<ExternalReference> referencesToSearch) {
		for (ExternalReference referenceToSearch : referencesToSearch) {
			String referenzUrl = referenceToSearch.getReferenzUrl();
			if (referenzUrl != null && referenzUrl.equals(referenceToFind.getReferenzUrl())) {
				return referenceToSearch;
			}
		}
		return null;
	}

	private static boolean isReferenced(String url, List<ExternalReference> externalRefsModified) {
		for (ExternalReference externalRefModified : externalRefsModified) {
			String referenceUrl = externalRefModified.getReferenzUrl();
			if (referenceUrl != null && referenceUrl.equals(url))
				return true;
			String geoReferenceUrl = externalRefModified.getGeoRefUrl();
			if (geoReferenceUrl != null && geoReferenceUrl.equals(url))
				return true;
		}
		return false;
	}

}
