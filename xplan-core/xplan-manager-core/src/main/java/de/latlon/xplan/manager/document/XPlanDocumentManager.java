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
import de.latlon.xplan.manager.edit.EditedArtefacts;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.manager.edit.ArtefactType.NONRASTER;
import static de.latlon.xplan.manager.edit.EditType.ADDED;
import static de.latlon.xplan.manager.edit.EditType.REMOVED;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class XPlanDocumentManager {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanDocumentManager.class);

	private final DocumentStorage documentStorage;

	private final ApplicationEventPublisher applicationEventPublisher;

	public XPlanDocumentManager(DocumentStorage documentStorage, ApplicationEventPublisher applicationEventPublisher) {
		this.documentStorage = documentStorage;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	/**
	 * Imports all (non raster) documents from XPlanArchive.
	 * @param planId the id of the plan, never <code>null</code>
	 * @param externalReferenceInfo externalReferencesInfo containing references of the
	 * XPlanGML, never <code>null</code>
	 * @param xPlanArchive containing the documents, never <code>null</code>
	 * @return
	 * @throws StorageException if the documents could not be stored
	 */
	public void importDocuments(int planId, ExternalReferenceInfo externalReferenceInfo, XPlanArchive xPlanArchive)
			throws StorageException {
		List<String> referencesToAdd = collectNonHttpReferences(externalReferenceInfo.getNonRasterRefs());
		StorageEvent storageEvent = new StorageEvent();
		try {
			documentStorage.importDocuments(planId, xPlanArchive, referencesToAdd, storageEvent);
		}
		finally {
			applicationEventPublisher.publishEvent(storageEvent);
		}
	}

	/**
	 * Updates (removes outdated and adds new) documents.
	 * @param planId the id of the plan, never <code>null</code>
	 * @param uploadedArtefacts a list of all uploaded artefacts, may be empty but never
	 * <code>null</code>
	 * @param editedArtefacts describing the edited artefacts, never <code>null</code>
	 * @throws StorageException if the documents could not be updated
	 */
	public void updateDocuments(int planId, List<Path> uploadedArtefacts, EditedArtefacts editedArtefacts)
			throws StorageException {
		StorageEvent storageEvent = new StorageEvent();
		try {
			List<String> referencesToAdd = editedArtefacts.getFileNames(ADDED, NONRASTER);
			for (String referenceToAdd : referencesToAdd) {
				Path fileToAdd = getFileToAdd(referenceToAdd, uploadedArtefacts);
				if (fileToAdd != null) {
					documentStorage.importDocument(planId, referenceToAdd, fileToAdd, storageEvent);
				}
				else {
					LOG.warn("Could not find document with name {} to import in storage", referenceToAdd);
				}
			}

			List<String> referencesToRemove = editedArtefacts.getFileNames(REMOVED, NONRASTER);
			for (String referenceToRemove : referencesToRemove) {
				documentStorage.deleteDocument(planId, referenceToRemove, storageEvent);
			}
		}
		finally {
			applicationEventPublisher.publishEvent(storageEvent);
		}
	}

	private Path getFileToAdd(String referenceToAdd, List<Path> uploadedArtefacts) {
		for (Path uploadedArtefact : uploadedArtefacts) {
			if (referenceToAdd.equals(uploadedArtefact.getFileName().toString()))
				return uploadedArtefact;
		}
		return null;
	}

	private List<String> collectNonHttpReferences(List<ExternalReference> externalReferences) {
		List<String> referencesToAdd = new ArrayList<>();
		for (ExternalReference reference : externalReferences) {
			addReference(reference.getReferenzUrl(), referencesToAdd);
			addReference(reference.getGeoRefUrl(), referencesToAdd);
		}
		return referencesToAdd;
	}

	private void addReference(String reference, List<String> referencesToAdd) {
		String geoRef = reference;
		if (isNonHttpReference(geoRef)) {
			referencesToAdd.add(geoRef);
		}
	}

	private boolean isNonHttpReference(String ref) {
		return ref != null && !ref.startsWith("http");
	}

}
