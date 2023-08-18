/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.transaction.AttachmentUrlHandler;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.api.manager.exception.DuplicateDokument;
import de.latlon.xplanbox.api.manager.exception.InvalidDokumentId;
import de.latlon.xplanbox.api.manager.v1.model.Dokument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles editing of Dokumente.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class EditDokumentHandler extends EditHandler {

	@Autowired
	private Optional<AttachmentUrlHandler> attachmentUrlHandler;

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @return all Dokumente of the plan. May be an empty list but not <code>null</code>
	 * @throws Exception
	 */
	public List<Dokument> retrieveDokumente(String planId) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		List<Reference> references = xPlanToEdit.getReferences();

		return references.stream().map(reference -> {
			String dokumentId = createDokumentId(planId, reference);
			return Dokument.fromReference(dokumentId, reference);
		}).collect(Collectors.toList());
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param dokumentId the id of the Dokument to retrieve, never <code>null</code>
	 * @return the Dokumente with the passed id of the plan. nerver <code>null</code>
	 * @throws Exception
	 */
	public Dokument retrieveDokument(String planId, String dokumentId) throws Exception {
		List<Dokument> dokumente = retrieveDokumente(planId);
		return getDokumentById(planId, dokumentId, dokumente);
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param dokumentModel the Dokument to add, never <code>null</code>
	 * @param file the file to add, may be <code>null</code>
	 * @return the added Dokument. nerver <code>null</code>
	 * @throws Exception
	 */
	public Dokument addDokument(String planId, Dokument dokumentModel, File file) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		Reference referenceToAdd = Dokument.toReference(dokumentModel);
		String newDokumentId = createDokumentId(planId, referenceToAdd);
		checkDokumentId(planId, dokumentModel, newDokumentId);

		List<Reference> references = xPlanToEdit.getReferences();
		references.add(referenceToAdd);
		List<File> uploadedArtefacts = file != null ? Collections.singletonList(file) : Collections.emptyList();
		manager.editPlan(plan, xPlanToEdit, true, uploadedArtefacts);
		return retrieveInsertedDokument(planId, newDokumentId);
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param dokumentId the id of the Dokument to replace, never <code>null</code>
	 * @param dokumentModel the Dokument to add, never <code>null</code>
	 * @param file the file to add, may be <code>null</code>
	 * @return the replaced Dokument. nerver <code>null</code>
	 * @throws Exception
	 */
	public Dokument replaceDokument(String planId, String dokumentId, Dokument dokumentModel, File file)
			throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		List<Reference> references = xPlanToEdit.getReferences();
		Reference referenceToReplace = getReferenceById(planId, dokumentId, references);
		Reference referenceToAdd = Dokument.toReference(dokumentModel);
		String newDokumentId = createDokumentId(planId, referenceToAdd);
		checkDokumentId(planId, dokumentId, dokumentModel, newDokumentId);

		references.remove(referenceToReplace);
		references.add(referenceToAdd);
		List<File> uploadedArtefacts = file != null ? Collections.singletonList(file) : Collections.emptyList();
		manager.editPlan(plan, xPlanToEdit, true, uploadedArtefacts);
		return retrieveInsertedDokument(planId, newDokumentId);
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param dokumentId the id of the Dokument to delete, never <code>null</code>
	 * @return the deleted Dokument. nerver <code>null</code>
	 * @throws Exception
	 */
	public Dokument deleteDokument(String planId, String dokumentId) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		List<Reference> references = xPlanToEdit.getReferences();
		Reference reference = getReferenceById(planId, dokumentId, references);
		references.remove(reference);
		manager.editPlan(plan, xPlanToEdit, true, Collections.emptyList());
		return Dokument.fromReference(dokumentId, reference);
	}

	private Reference getReferenceById(String planId, String dokumentId, List<Reference> references)
			throws InvalidDokumentId {
		List<Reference> referencesById = references.stream()
			.filter(reference -> dokumentId.equals(createDokumentId(planId, reference)))
			.collect(Collectors.toList());
		if (referencesById.size() != 1) {
			throw new InvalidDokumentId(planId, dokumentId);
		}
		return referencesById.get(0);
	}

	private Dokument getDokumentById(String planId, String dokumentId, List<Dokument> dokumente)
			throws InvalidDokumentId {
		List<Dokument> dokumenteWithId = dokumente.stream()
			.filter(dokument -> dokumentId.equals(dokument.getId()))
			.collect(Collectors.toList());
		if (dokumenteWithId.size() != 1) {
			throw new InvalidDokumentId(planId, dokumentId);
		}
		return dokumenteWithId.get(0);
	}

	private Dokument retrieveInsertedDokument(String planId, String newDokumentId) throws Exception {
		List<Reference> references = manager.getXPlanToEdit(findPlanById(planId)).getReferences();
		Reference insertedReference = getReferenceById(planId, newDokumentId, references);
		return Dokument.fromReference(newDokumentId, insertedReference);
	}

	private String createDokumentId(String planId, Reference reference) {
		StringBuilder id = new StringBuilder();
		if (reference.getReferenzName() != null)
			id.append(reference.getReferenzName());
		id.append('-');
		if (reference.getReference() != null) {
			if (attachmentUrlHandler.isPresent()) {
				String replacedReference = attachmentUrlHandler.get()
					.replaceRelativeUrl(planId, reference.getReference());
				id.append(replacedReference);
			}
			else {
				id.append(reference.getReference());
			}
		}
		return id.toString().replaceAll("[^a-zA-Z0-9\\-_]", "");
	}

	private void checkDokumentId(String planId, Dokument dokumentModel, String newDokumentId) throws Exception {
		List<Dokument> dokumente = retrieveDokumente(planId);
		boolean dokumentWithSameReferenceUrlExists = dokumente.stream()
			.anyMatch(dokument -> newDokumentId.equals(dokument.getId()));
		if (dokumentWithSameReferenceUrlExists) {
			throw new DuplicateDokument(planId, newDokumentId, dokumentModel.getReferenzName(),
					dokumentModel.getReferenzURL());
		}
	}

	private void checkDokumentId(String planId, String dokumentId, Dokument dokumentModel, String newDokumentId)
			throws Exception {
		if (dokumentId.equals(newDokumentId)) {
			return;
		}
		checkDokumentId(planId, dokumentModel, newDokumentId);
	}

}
