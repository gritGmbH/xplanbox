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
package de.latlon.xplan.manager.document;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.deegree.feature.FeatureCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanDocumentManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanDocumentManager.class);

	private final DocumentStorage documentStorage;

	public XPlanDocumentManager(DocumentStorage documentStorage) {
		this.documentStorage = documentStorage;
	}

	/**
	 * Imports all (non raster) documents from XPlanArchive.
	 * @param planId the id of the plan, never <code>null</code>
	 * @param featureCollection the parsed feature collection, never <code>null</code>
	 * @param xPlanArchive containing the documents, never <code>null</code>
	 * @throws StorageException if the documents could not be stored
	 */
	public void importDocuments(int planId, FeatureCollection featureCollection, XPlanArchive xPlanArchive)
			throws StorageException {
		ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();
		ExternalReferenceInfo externalReferenceInfo = externalReferenceScanner.scan(featureCollection,
				xPlanArchive.getVersion());
		List<String> referencesToAdd = collectReferencesToAdd(externalReferenceInfo.getNonRasterRefs());
		documentStorage.importDocuments(planId, xPlanArchive, referencesToAdd);
	}

	/**
	 * Updates (removes outdated and adds new) documents.
	 * @param planId the id of the plan, never <code>null</code>
	 * @param uploadedArtefacts a list of all uploaded artefacts, may be empty but never
	 * <code>null</code>
	 * @param documentsToAdd a list of documents added, may be empty but never *
	 * <code>null</code>
	 * @param documentsToRemove a list of documents removed, may be empty but never * *
	 * <code>null</code>
	 * @throws StorageException if the documents could not be updated
	 */
	public void updateDocuments(int planId, List<Path> uploadedArtefacts, List<ExternalReference> documentsToAdd,
			List<ExternalReference> documentsToRemove) throws StorageException {
		for (String referenceToAdd : collectReferencesToAdd(documentsToAdd)) {
			Path fileToAdd = getFileToAdd(referenceToAdd, uploadedArtefacts);
			if (fileToAdd != null) {
				documentStorage.importDocument(planId, referenceToAdd, fileToAdd);
			}
			else {
				LOG.warn("Could not find document with name {} to import in storage", referenceToAdd);
			}
		}

		for (ExternalReference referenceToRemove : documentsToRemove) {
			documentStorage.deleteDocument(planId, referenceToRemove.getReferenzUrl());
			documentStorage.deleteDocument(planId, referenceToRemove.getGeoRefUrl());
		}
	}

	private Path getFileToAdd(String referenceToAdd, List<Path> uploadedArtefacts) {
		for (Path uploadedArtefact : uploadedArtefacts) {
			if (referenceToAdd.equals(uploadedArtefact.getFileName().toString()))
				return uploadedArtefact;
		}
		return null;
	}

	private List<String> collectReferencesToAdd(List<ExternalReference> externalReferences) {
		List<String> referencesToAdd = new ArrayList<>();
		for (ExternalReference reference : externalReferences) {
			addReference(reference.getReferenzUrl(), referencesToAdd);
			addReference(reference.getGeoRefUrl(), referencesToAdd);
		}
		return referencesToAdd;
	}

	private static void addReference(String reference, List<String> referencesToAdd) {
		String geoRef = reference;
		if (geoRef != null && !geoRef.startsWith("http")) {
			referencesToAdd.add(geoRef);
		}
	}

}
