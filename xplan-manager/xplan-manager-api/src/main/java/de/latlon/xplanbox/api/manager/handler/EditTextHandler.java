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

import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import de.latlon.xplanbox.api.manager.exception.InvalidTextId;
import de.latlon.xplanbox.api.manager.v1.model.Text;
import org.springframework.stereotype.Component;

import jakarta.inject.Singleton;
import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Handles editing of Text.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Component
@Singleton
public class EditTextHandler extends EditHandler {

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @return all Text of the plan. May be an empty list but not <code>null</code>
	 * @throws Exception
	 */
	public List<Text> retrieveTexte(String planId) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		List<de.latlon.xplan.manager.web.shared.edit.Text> texts = xPlanToEdit.getTexts();

		return texts.stream().map(text -> {
			String textId = createTextId(text);
			return Text.fromText(textId, text);
		}).collect(Collectors.toList());
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param textId the id of the Text to retrieve, never <code>null</code>
	 * @return the Text with the passed id of the plan, never <code>null</code>
	 * @throws Exception
	 */
	public Text retrieveText(String planId, String textId) throws Exception {
		List<Text> texte = retrieveTexte(planId);
		return getTextById(planId, textId, texte);
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param textModel the Text to add, never <code>null</code>
	 * @param file the file to add, may be <code>null</code>
	 * @return the added Text, never <code>null</code>
	 * @throws Exception
	 */
	public Text addText(String planId, Text textModel, File file) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		List<de.latlon.xplan.manager.web.shared.edit.Text> texts = xPlanToEdit.getTexts();
		List<String> textIdsBeforeInsert = texts.stream()
			.filter(t -> t.getFeatureId() != null)
			.map(t -> t.getFeatureId())
			.collect(Collectors.toList());
		de.latlon.xplan.manager.web.shared.edit.Text textToAdd = textModel.toText(plan.getVersion(), plan.getType());
		texts.add(textToAdd);

		List<File> uploadedArtefacts = file != null ? Collections.singletonList(file) : Collections.emptyList();
		manager.editPlan(plan, xPlanToEdit, true, uploadedArtefacts);
		Optional<de.latlon.xplan.manager.web.shared.edit.Text> insertedText = manager
			.getXPlanToEdit(findPlanById(planId))
			.getTexts()
			.stream()
			.filter(t -> !textIdsBeforeInsert.contains(t.getFeatureId()))
			.findFirst();
		return Text.fromText(insertedText.get());
	}

	/**
	 * @param planId the ID of the plan, never <code>null</code>
	 * @param textId the id of the Text to replace, never <code>null</code>
	 * @param textModel the Text to add, never <code>null</code>
	 * @param file the file to add, may be <code>null</code>
	 * @return the replaced Text, never <code>null</code>
	 * @throws Exception
	 */
	public Text replaceText(String planId, String textId, Text textModel, File file) throws Exception {
		XPlan plan = findPlanById(planId);
		XPlanToEdit xPlanToEdit = manager.getXPlanToEdit(plan);
		List<de.latlon.xplan.manager.web.shared.edit.Text> texts = xPlanToEdit.getTexts();
		de.latlon.xplan.manager.web.shared.edit.Text textToReplace = getOldTextById(planId, textId, texts);
		texts.remove(textToReplace);
		de.latlon.xplan.manager.web.shared.edit.Text textToAdd = textModel.toText(plan.getVersion(), plan.getType());
		textToAdd.setFeatureId(textId);
		texts.add(textToAdd);
		List<File> uploadedArtefacts = file != null ? Collections.singletonList(file) : Collections.emptyList();
		manager.editPlan(plan, xPlanToEdit, true, uploadedArtefacts);
		Optional<de.latlon.xplan.manager.web.shared.edit.Text> insertedText = manager
			.getXPlanToEdit(findPlanById(planId))
			.getTexts()
			.stream()
			.filter(t -> textId.equals(t.getFeatureId()))
			.findFirst();
		return Text.fromText(insertedText.get());
	}

	private de.latlon.xplan.manager.web.shared.edit.Text getOldTextById(String planId, String textId,
			List<de.latlon.xplan.manager.web.shared.edit.Text> texts) throws InvalidTextId {
		List<de.latlon.xplan.manager.web.shared.edit.Text> textsById = texts.stream()
			.filter(text -> textId.equals(createTextId(text)))
			.collect(Collectors.toList());
		if (textsById.size() != 1) {
			throw new InvalidTextId(planId, textId);
		}
		return textsById.get(0);
	}

	private Text getTextById(String planId, String textId, List<Text> texte) throws InvalidTextId {
		List<Text> texteWithId = texte.stream()
			.filter(text -> textId.equals(text.getId()))
			.collect(Collectors.toList());
		if (texteWithId.size() != 1) {
			throw new InvalidTextId(planId, textId);
		}
		return texteWithId.get(0);
	}

	private static String createTextId(de.latlon.xplan.manager.web.shared.edit.Text text) {
		return text.getFeatureId();
	}

}
