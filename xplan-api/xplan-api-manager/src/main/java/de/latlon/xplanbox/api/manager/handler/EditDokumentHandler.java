package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.api.manager.exception.DuplicateDokument;
import de.latlon.xplanbox.api.manager.exception.InvalidDokumentId;
import de.latlon.xplanbox.api.manager.v1.model.Dokument;
import de.latlon.xplanbox.api.manager.v1.model.Referenz;
import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles editing of Dokumente.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class EditDokumentHandler extends EditHandler {

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
			String dokumentId = createDokumentId(reference);
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
		String newDokumentId = createDokumentId(referenceToAdd);
		checkDokumentId(planId, dokumentModel, newDokumentId);

		List<Reference> references = xPlanToEdit.getReferences();
		references.add(referenceToAdd);
		List<File> uploadedArtefacts = file != null ? Collections.singletonList(file) : Collections.emptyList();
		manager.editPlan(plan, xPlanToEdit, false, uploadedArtefacts);
		return dokumentModel.id(newDokumentId);
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
		String newDokumentId = createDokumentId(referenceToAdd);
		checkDokumentId(planId, dokumentId, dokumentModel, newDokumentId);

		references.remove(referenceToReplace);
		references.add(referenceToAdd);
		List<File> uploadedArtefacts = file != null ? Collections.singletonList(file) : Collections.emptyList();
		manager.editPlan(plan, xPlanToEdit, false, uploadedArtefacts);
		return dokumentModel.id(newDokumentId);
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
		manager.editPlan(plan, xPlanToEdit, false, Collections.emptyList());
		return Dokument.fromReference(dokumentId, reference);
	}

	private Reference getReferenceById(String planId, String dokumentId, List<Reference> references)
			throws InvalidDokumentId {
		List<Reference> referencesById = references.stream()
				.filter(reference -> dokumentId.equals(createDokumentId(reference))).collect(Collectors.toList());
		if (referencesById.size() != 1) {
			throw new InvalidDokumentId(planId, dokumentId);
		}
		return referencesById.get(0);
	}

	private Dokument getDokumentById(String planId, String dokumentId, List<Dokument> dokumente)
			throws InvalidDokumentId {
		List<Dokument> dokumenteWithId = dokumente.stream().filter(dokument -> dokumentId.equals(dokument.getId()))
				.collect(Collectors.toList());
		if (dokumenteWithId.size() != 1) {
			throw new InvalidDokumentId(planId, dokumentId);
		}
		return dokumenteWithId.get(0);
	}

	private static String createDokumentId(Reference reference) {
		StringBuilder id = new StringBuilder();
		if (reference.getReferenzName() != null)
			id.append(reference.getReferenzName());
		id.append('-');
		if (reference.getReference() != null)
			id.append(reference.getReference());
		return id.toString().replaceAll("[^a-zA-Z0-9\\-_]", "");
	}

	private void checkDokumentId(String planId, Dokument dokumentModel, String newDokumentId) throws Exception {
		List<Dokument> dokumente = retrieveDokumente(planId);
		long noOfDokumenteWithNewId = dokumente.stream().filter(dokument -> newDokumentId.equals(dokument.getId()))
				.count();
		if (noOfDokumenteWithNewId > 0) {
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