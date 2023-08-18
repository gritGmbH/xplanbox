/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
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
package de.latlon.xplan.manager.web.client.gui.editor.reference;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.gui.editor.AbstractEditorSubPanelWithTable;
import de.latlon.xplan.manager.web.client.gui.editor.EditPlanType;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.SavedHandler;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;

import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;

/**
 * Panel for references.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class ReferencesPanel extends AbstractEditorSubPanelWithTable<Reference> {

	/**
	 * @param version of the plan to edit, never <code>null</code>
	 */
	public ReferencesPanel(EditVersion version, EditPlanType planType) {
		super(version, planType, MESSAGES.editCaptionReferences());
		add(createGui());
	}

	@Override
	protected void initColumns(CellTable<Reference> referencesList) {
		addReferenceColumn(referencesList);
		// #3305 - georeference is not needed.
		// addGeoReferenceColumn( referencesList );
		addTypeColumn(referencesList);

		TextHeader actionHeader = new TextHeader(MESSAGES.actions());
		addRemoveColumn(referencesList, actionHeader);
	}

	private Widget createGui() {
		VerticalPanel panel = new VerticalPanel();
		panel.setSpacing(5);
		panel.setHorizontalAlignment(ALIGN_CENTER);
		panel.add(getTable());
		panel.add(createNewButton());
		return panel;
	}

	private Button createNewButton() {
		Button newButton = new Button(MESSAGES.editCaptionNewReference(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				final ReferenceDialog referenceDialog = new ReferenceDialog(version);
				referenceDialog.addSaveHandler(new SavedHandler() {
					@Override
					public void changesSaved() {
						Reference newReference = referenceDialog.getReference();
						List<Reference> references = getValues();
						if (validateNewReferenceInContextOfAll(newReference, references)) {
							references.add(newReference);
							referenceDialog.hide();
						}
					}
				});
				referenceDialog.center();
				referenceDialog.show();
			}
		});
		return newButton;
	}

	private void addReferenceColumn(CellTable<Reference> table) {
		TextColumn<Reference> referenceColumn = new TextColumn<Reference>() {
			@Override
			public String getValue(Reference referenceData) {
				return referenceData.getReference();
			}
		};
		referenceColumn.setCellStyleNames("editReferencesColumn referenceColumn");
		table.addColumn(referenceColumn, MESSAGES.editCaptionReferencesReference());
	}

	// #3305 - georeference is not needed.
	// private void addGeoReferenceColumn( CellTable<Reference> table ) {
	// TextColumn<Reference> geoReferenceColumn = new TextColumn<Reference>() {
	// @Override
	// public String getValue( Reference referenceData ) {
	// return referenceData.getGeoReference();
	// }
	// };
	// geoReferenceColumn.setCellStyleNames( "editReferencesColumn geoReferenceColumn" );
	// table.addColumn( geoReferenceColumn, MESSAGES.editCaptionReferencesGeoReference()
	// );
	// }

	private void addTypeColumn(CellTable<Reference> table) {
		TextColumn<Reference> typeColumn = new TextColumn<Reference>() {
			@Override
			public String getValue(Reference referenceData) {
				return TYPE_CODELIST_PROVIDER.translate(ReferenceType.class, referenceData.getType());
			}
		};
		typeColumn.setCellStyleNames("editReferencesColumn typeColumn");
		table.addColumn(typeColumn, MESSAGES.editCaptionReferencesType());
	}

	private void addRemoveColumn(final CellTable<Reference> table, TextHeader columnHeader) {
		ButtonCell downloadButtonCell = new ButtonCell();
		final Column<Reference, String> removeButtonColumn = new Column<Reference, String>(downloadButtonCell) {
			@Override
			public String getValue(Reference object) {
				return "";
			}
		};
		removeButtonColumn.setFieldUpdater(new FieldUpdater<Reference, String>() {
			public void update(int index, Reference object, String value) {
				List<Reference> references = getValues();
				references.remove(index);
			}
		});
		removeButtonColumn.setCellStyleNames("editReferencesColumn removeButtonColumn");
		table.addColumn(removeButtonColumn, columnHeader);
	}

	private boolean validateNewReferenceInContextOfAll(Reference newReference, List<Reference> references) {
		if (GRUENORDNUNGSPLAN.equals(newReference.getType()) && alreadyExists(references, GRUENORDNUNGSPLAN)) {
			Window.alert(MESSAGES.editCaptionReferencesGreenStructursReferenceAlreadyExists());
			return false;
		}
		return true;
	}

	private boolean alreadyExists(List<Reference> references, ReferenceType type) {
		for (Reference reference : references) {
			if (type.equals(reference.getType()))
				return true;
		}
		return false;
	}

}
