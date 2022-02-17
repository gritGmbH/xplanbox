/*-
 * #%L
 * xplan-manager-web - Webanwendung des XPlan Managers
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
 ----------------------------------------------------------------------------*/
package de.latlon.xplan.manager.web.client.gui.editor.reference;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_3;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.BEGRUENDUNG;
import static de.latlon.xplan.manager.web.shared.edit.ReferenceType.GRUENORDNUNGSPLAN;

import java.util.List;

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
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.SavedHandler;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.ReferenceType;

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
	public ReferencesPanel(EditVersion version) {
		super(version, MESSAGES.editCaptionReferences());
		add(createGui());
	}

	@Override
	protected void initColumns(CellTable<Reference> referencesList) {
		addReferenceColumn(referencesList);
		// #3305 - georeference is not needed.
		// if ( !XPLAN_3.equals( version ) )
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
		if (XPLAN_3.equals(version) && BEGRUENDUNG.equals(newReference.getType())
				&& alreadyExists(references, BEGRUENDUNG)) {
			Window.alert(MESSAGES.editCaptionReferencesReasonReferenceAlreadyExists());
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
