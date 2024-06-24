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
package de.latlon.xplan.manager.edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Access to {@link EditedArtefact} with filters
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class EditedArtefacts {

	private final List<EditedArtefact> editedArtefacts;

	public EditedArtefacts() {
		this(new ArrayList<>());
	}

	public EditedArtefacts(List<EditedArtefact> editedArtefacts) {
		this.editedArtefacts = editedArtefacts;
	}

	/**
	 * @param editedArtefact to add, never <code>null</code>
	 */
	public void add(EditedArtefact editedArtefact) {
		this.editedArtefacts.add(editedArtefact);
	}

	/**
	 * @param editType used for filtering, never <code>null</code>
	 * @return all fileNames of artefacts with the passed editType, may be empty but never
	 * <code>null</code>
	 */
	public List<String> getFileNames(EditType editType) {
		return editedArtefacts.stream()
			.filter(editedArtefact -> editedArtefact.getEditType() == editType)
			.map(editedArtefact -> editedArtefact.getFileName())
			.collect(Collectors.toList());
	}

	/**
	 * @param editType used for filtering, never <code>null</code>
	 * @return all ${link EditedArtefact}s with the passed editType, may be empty but
	 * never <code>null</code>
	 */
	public List<EditedArtefact> getEditedArtefacts(EditType editType) {
		return editedArtefacts.stream()
			.filter(editedArtefact -> editedArtefact.getEditType() == editType)
			.collect(Collectors.toList());
	}

	/**
	 * @param editType used for filtering, never <code>null</code>
	 * @@param artefactType used for filtering, never <code>null</code>
	 * @return all fileNames of artefacts with the passed editType and editType, may be
	 * empty but never <code>null</code>
	 */
	public List<String> getFileNames(EditType editType, ArtefactType... artefactType) {
		return editedArtefacts.stream()
			.filter(editedArtefact -> Arrays.stream(artefactType).anyMatch(at -> editedArtefact.getArtefactType() == at)
					&& editedArtefact.getEditType() == editType)
			.flatMap(editedArtefact -> Stream.of(editedArtefact.getFileName(), editedArtefact.getGeorefFileName()))
			.filter(fileName -> fileName != null)
			.collect(Collectors.toList());
	}

}
