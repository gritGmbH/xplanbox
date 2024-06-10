/*-
 * #%L
 * xplan-manager-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.manager.handler;

import de.latlon.xplan.manager.transaction.AttachmentUrlHandler;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.api.manager.exception.DuplicateRasterbasis;
import de.latlon.xplanbox.api.manager.exception.InvalidPlanToEdit;
import de.latlon.xplanbox.api.manager.exception.InvalidRasterbasisId;
import de.latlon.xplanbox.api.manager.exception.MissingBereichNummer;
import de.latlon.xplanbox.api.manager.exception.UnexpectedError;
import de.latlon.xplanbox.api.manager.v1.model.Rasterbasis;
import org.deegree.commons.utils.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.inject.Singleton;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles editing of Rasterbasis.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class EditRasterbasisHandler extends EditHandler {

	@Autowired
	private Optional<AttachmentUrlHandler> attachmentUrlHandler;

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @return all Rasterbasis of the plan. May be an empty list but not <code>null</code>
	 * @throws Exception
	 */
	public List<Rasterbasis> retrieveRasterbasis(String planId) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		return xPlanToEdit.getRasterBasis()
			.stream()
			.flatMap(rb -> rb.getRasterReferences().stream())
			.map(rasterReference -> {
				String rasterbasisId = createRasterbasisId(planId, rasterReference);
				return Rasterbasis.fromRasterReference(rasterbasisId, rasterReference);
			})
			.collect(Collectors.toList());
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param rasterbasisId the id of the Rasterbasis to retrieve, never <code>null</code>
	 * @return the Rasterbasis with the passed id of the plan, never <code>null</code>
	 * @throws Exception
	 */
	public Rasterbasis retrieveRasterbasis(String planId, String rasterbasisId) throws Exception {
		List<Rasterbasis> rasterbasis = retrieveRasterbasis(planId);
		return getRasterbasisById(planId, rasterbasisId, rasterbasis);
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param rasterbasisModel the Rasterbasis to add, never <code>null</code>
	 * @param referenz the referenz to add, may be <code>null</code>
	 * @param geoReferenz the geoReferenz to add, may be <code>null</code>
	 * @return the added Rasterbasis, never <code>null</code>
	 * @throws Exception
	 */
	public Rasterbasis addRasterbasis(String planId, Rasterbasis rasterbasisModel, File referenz, File geoReferenz)
			throws Exception {
		XPlan plan = findPlanById(planId);
		checkBereichNummer(planId, rasterbasisModel);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		String bereichNummer = rasterbasisModel.getBereichNummer();
		RasterBasis rasterBasis = getRasterBasisByBereichNummer(bereichNummer, xPlanToEdit);
		RasterReference rasterReferenceToAdd = rasterbasisModel.toRasterReference();
		String newRasterbasisId = createRasterbasisId(planId, rasterReferenceToAdd);
		checkRasterbasisId(planId, rasterbasisModel, newRasterbasisId);

		rasterBasis.getRasterReferences().add(rasterReferenceToAdd);
		List<File> uploadedArtefacts = createUploadedArtefactsList(referenz, geoReferenz);
		manager.editPlan(plan, xPlanToEdit, true, uploadedArtefacts);
		return retrieveInsertedRasterbasis(planId, rasterbasisModel, newRasterbasisId);
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param rasterbasisId the id of the Rasterbasis to replace, never <code>null</code>
	 * @param rasterbasisModel the Rasterbasis to add, never <code>null</code>
	 * @param referenz the referenz to add, may be <code>null</code>
	 * @param geoReferenz the geoReferenz to add, may be <code>null</code>
	 * @return the replaced Rasterbasis, never <code>null</code>
	 * @throws Exception
	 */
	public Rasterbasis replaceRasterbasis(String planId, String rasterbasisId, Rasterbasis rasterbasisModel,
			File referenz, File geoReferenz) throws Exception {
		XPlan plan = findPlanById(planId);
		checkBereichNummer(planId, rasterbasisModel);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		String bereichNummer = rasterbasisModel.getBereichNummer();
		RasterBasis rasterBasis = getRasterBasisByBereichNummer(bereichNummer, xPlanToEdit);
		if (rasterBasis == null)
			throw new UnexpectedError("Could not find a RasterBasis assigned to Bereich with nummer "
					+ rasterbasisModel.getBereichNummer());
		RasterReference rasterReferenceToReplace = getRasterReferenceById(planId, rasterbasisId, rasterBasis);
		RasterReference rasterReferenceToAdd = rasterbasisModel.toRasterReference();
		String newRasterbasisId = createRasterbasisId(planId, rasterReferenceToAdd);
		checkRasterbasisId(planId, rasterbasisId, rasterbasisModel, newRasterbasisId);

		rasterBasis.getRasterReferences().remove(rasterReferenceToReplace);
		rasterBasis.getRasterReferences().add(rasterReferenceToAdd);
		List<File> uploadedArtefacts = createUploadedArtefactsList(referenz, geoReferenz);
		manager.editPlan(plan, xPlanToEdit, true, uploadedArtefacts);

		return retrieveInsertedRasterbasis(planId, rasterbasisModel, newRasterbasisId);
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param rasterbasisId the id of the Rasterbasis to delete, never <code>null</code>
	 * @return the deleted Rasterbasis, never <code>null</code>
	 * @throws Exception
	 */
	public Rasterbasis deleteRasterbasis(String planId, String rasterbasisId) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		Pair<RasterBasis, RasterReference> rasterBasisAndReference = getRasterReferenceById(planId, rasterbasisId,
				xPlanToEdit);
		RasterBasis rasterBasis = rasterBasisAndReference.first;
		RasterReference rasterReferenceToDelete = rasterBasisAndReference.second;
		rasterBasis.getRasterReferences().remove(rasterReferenceToDelete);
		if (rasterBasis.getRasterReferences().isEmpty())
			xPlanToEdit.addRasterBasis(null);
		manager.editPlan(plan, xPlanToEdit, true, Collections.emptyList());
		return Rasterbasis.fromRasterReference(rasterbasisId, rasterReferenceToDelete);
	}

	private RasterBasis getRasterBasisByBereichNummer(String bereichNummer, XPlanToEdit xPlanToEdit)
			throws InvalidPlanToEdit {
		Optional<RasterBasis> rasterBasisCandidate = xPlanToEdit.getRasterBasis()
			.stream()
			.filter(rb -> rb.getBereichNummer().equals(bereichNummer))
			.findFirst();
		if (rasterBasisCandidate.isPresent())
			return rasterBasisCandidate.get();
		throw new InvalidPlanToEdit("Could not find bereich with nummer " + bereichNummer);
	}

	private Pair<RasterBasis, RasterReference> getRasterReferenceById(String planId, String rasterbasisId,
			XPlanToEdit xPlanToEdit) throws InvalidRasterbasisId {
		for (RasterBasis rasterBasis : xPlanToEdit.getRasterBasis()) {
			List<RasterReference> rasterReferencesById = rasterBasis.getRasterReferences()
				.stream()
				.filter(rasterReference -> rasterbasisId.equals(createRasterbasisId(planId, rasterReference)))
				.collect(Collectors.toList());
			return new Pair<>(rasterBasis, rasterReferencesById.get(0));
		}
		throw new InvalidRasterbasisId(planId, rasterbasisId);
	}

	private RasterReference getRasterReferenceById(String planId, String rasterbasisId, RasterBasis rasterBasis)
			throws InvalidRasterbasisId {
		List<RasterReference> rasterReferences = rasterBasis.getRasterReferences();
		if (rasterReferences == null) {
			throw new InvalidRasterbasisId(planId, rasterbasisId);
		}
		List<RasterReference> rasterReferencesById = rasterReferences.stream()
			.filter(rasterReference -> rasterbasisId.equals(createRasterbasisId(planId, rasterReference)))
			.collect(Collectors.toList());
		if (rasterReferencesById.size() != 1) {
			throw new InvalidRasterbasisId(planId, rasterbasisId);
		}
		return rasterReferencesById.get(0);
	}

	private Rasterbasis getRasterbasisById(String planId, String rasterbasisId, List<Rasterbasis> rasterbasis)
			throws InvalidRasterbasisId {
		List<Rasterbasis> rasterbasisWithId = rasterbasis.stream()
			.filter(rb -> rasterbasisId.equals(rb.getId()))
			.collect(Collectors.toList());
		if (rasterbasisWithId.size() != 1) {
			throw new InvalidRasterbasisId(planId, rasterbasisId);
		}
		return rasterbasisWithId.get(0);
	}

	private Rasterbasis retrieveInsertedRasterbasis(String planId, Rasterbasis rasterbasisModel,
			String newRasterbasisId) throws Exception {
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(findPlanById(planId));
		RasterBasis rasterBasisByBereichNummer = getRasterBasisByBereichNummer(rasterbasisModel.getBereichNummer(),
				xPlanToEdit);
		RasterReference insertedReference = getRasterReferenceById(planId, newRasterbasisId,
				rasterBasisByBereichNummer);
		return Rasterbasis.fromRasterReference(newRasterbasisId, insertedReference);
	}

	private List<File> createUploadedArtefactsList(File referenz, File geoReferenz) {
		List<File> uploadedArtefacts = new ArrayList<>();
		if (referenz != null)
			uploadedArtefacts.add(referenz);
		if (geoReferenz != null)
			uploadedArtefacts.add(geoReferenz);
		return uploadedArtefacts;
	}

	private String createRasterbasisId(String planId, RasterReference rasterReference) {
		StringBuilder id = new StringBuilder();
		if (rasterReference.getReferenzName() != null)
			id.append(rasterReference.getReferenzName());
		id.append('-');
		if (rasterReference.getReference() != null) {
			if (attachmentUrlHandler.isPresent()) {
				String replacedReference = attachmentUrlHandler.get()
					.replaceRelativeUrl(planId, rasterReference.getReference());
				id.append(replacedReference);
			}
			else {
				id.append(rasterReference.getReference());
			}
		}

		return id.toString().replaceAll("[^a-zA-Z0-9\\-_]", "");
	}

	private void checkRasterbasisId(String planId, Rasterbasis rasterbasisModel, String newRasterbasisId)
			throws Exception {
		List<Rasterbasis> rasterbasis = retrieveRasterbasis(planId);
		boolean rasterbasisWithSameReferenceUrlExists = rasterbasis.stream()
			.anyMatch(rb -> newRasterbasisId.equals(rb.getId()));
		if (rasterbasisWithSameReferenceUrlExists) {
			throw new DuplicateRasterbasis(planId, newRasterbasisId, rasterbasisModel.getReferenzName(),
					rasterbasisModel.getReferenzURL());
		}
	}

	private void checkRasterbasisId(String planId, String rasterbasisId, Rasterbasis rasterbasisModel,
			String newRasterbasisId) throws Exception {
		if (rasterbasisId.equals(newRasterbasisId)) {
			return;
		}
		checkRasterbasisId(planId, rasterbasisModel, newRasterbasisId);
	}

	private void checkBereichNummer(String planId, Rasterbasis rasterbasisModel) throws MissingBereichNummer {
		if (rasterbasisModel.getBereichNummer() == null) {
			throw new MissingBereichNummer(planId, rasterbasisModel.getId());
		}
	}

}
