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
package de.latlon.xplan.manager.web.client.gui.editor.change;

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
import de.latlon.xplan.manager.web.shared.edit.Change;
import de.latlon.xplan.manager.web.shared.edit.ChangeType;

import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistType.XP_RechtscharakterPlanaenderung;

/**
 * Subpanel to edit changes of XPlan version 4.1
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ChangesXplanPanel extends AbstractEditorSubPanelWithTable<Change> {

	/**
	 * Instantiates a panel to edit changes of XPlan version 4.1
	 */
	public ChangesXplanPanel(EditVersion version, EditPlanType planType) {
		super(version, planType, MESSAGES.editCaptionChanges());
		add(createGui());
	}

	@Override
	protected void initColumns(CellTable<Change> changesList) {
		addPlannameColumn(changesList);
		addLegalNatureColumn(changesList);
		addNumberColumn(changesList);
		addTypeColumn(changesList);

		TextHeader actionHeader = new TextHeader(MESSAGES.actions());
		addEditColumn(changesList, actionHeader);
		addRemoveColumn(changesList, actionHeader);
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
		Button newButton = new Button(MESSAGES.editCaptionNewChange(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				final ChangesXplanDialog changesDialog = new ChangesXplanDialog(version, planType);
				changesDialog.addSaveHandler(new SavedHandler() {
					@Override
					public void changesSaved() {
						if (changesDialog.isValid()) {
							Change newChange = changesDialog.getChange();
							List<Change> changes = getValues();
							changes.add(newChange);
							changesDialog.hide();
						}
						else {
							Window.alert(MESSAGES.editInvalidInput());
						}
					}
				});
				changesDialog.center();
				changesDialog.show();
			}
		});
		return newButton;
	}

	private void addPlannameColumn(CellTable<Change> table) {
		TextColumn<Change> planNameColumn = new TextColumn<Change>() {
			@Override
			public String getValue(Change changeData) {
				return shortText(changeData.getPlanName());
			}
		};
		planNameColumn.setCellStyleNames("editChangesColumn planNameColumn");
		table.addColumn(planNameColumn, MESSAGES.editCaptionChangesPlanName());
	}

	private void addLegalNatureColumn(CellTable<Change> table) {
		TextColumn<Change> legalNatureColumn = new TextColumn<Change>() {
			@Override
			public String getValue(Change changeData) {
				if (changeData.getLegalNatureCode() > 0)
					return CODELIST_PROVIDER.translate(version, planType, XP_RechtscharakterPlanaenderung,
							changeData.getLegalNatureCode());
				return "";
			}
		};
		legalNatureColumn.setCellStyleNames("editChangesColumn legalNatureColumn");
		table.addColumn(legalNatureColumn, MESSAGES.editCaptionChangesLegalNature());
	}

	private void addNumberColumn(CellTable<Change> table) {
		TextColumn<Change> numberColumn = new TextColumn<Change>() {
			@Override
			public String getValue(Change changeData) {
				return shortText(changeData.getNumber());
			}
		};
		numberColumn.setCellStyleNames("editChangesColumn numberColumn");
		table.addColumn(numberColumn, MESSAGES.editCaptionChangesNumber());
	}

	private void addTypeColumn(CellTable<Change> table) {
		TextColumn<Change> typeColumn = new TextColumn<Change>() {
			@Override
			public String getValue(Change changeData) {
				return TYPE_CODELIST_PROVIDER.translate(ChangeType.class, changeData.getType());
			}
		};
		typeColumn.setCellStyleNames("editChangesColumn typeColumn");
		table.addColumn(typeColumn, MESSAGES.editCaptionChangesType());
	}

	private void addEditColumn(final CellTable<Change> table, TextHeader columnHeader) {
		ButtonCell editButtonCell = new ButtonCell();
		final Column<Change, String> editButtonColumn = new Column<Change, String>(editButtonCell) {
			@Override
			public String getValue(Change object) {
				return "";
			}
		};
		final EditVersion version = this.version;
		editButtonColumn.setFieldUpdater(new FieldUpdater<Change, String>() {
			public void update(final int index, Change change, String value) {
				final ChangesXplanDialog changesDialog = new ChangesXplanDialog(version, planType, change);
				changesDialog.addSaveHandler(new SavedHandler() {
					@Override
					public void changesSaved() {
						if (changesDialog.isValid()) {
							Change updatedChange = changesDialog.getChange();
							List<Change> changes = getValues();
							changes.remove(index);
							changes.add(index, updatedChange);
							changesDialog.hide();
						}
						else {
							Window.alert(MESSAGES.editInvalidInput());
						}
					}
				});
				changesDialog.center();
				changesDialog.show();
			}
		});
		editButtonColumn.setCellStyleNames("editChangesColumn editButtonColumn");
		table.addColumn(editButtonColumn, columnHeader);
	}

	private void addRemoveColumn(final CellTable<Change> table, TextHeader columnHeader) {
		ButtonCell downloadButtonCell = new ButtonCell();
		final Column<Change, String> removeButtonColumn = new Column<Change, String>(downloadButtonCell) {
			@Override
			public String getValue(Change object) {
				return "";
			}
		};
		removeButtonColumn.setFieldUpdater(new FieldUpdater<Change, String>() {
			public void update(int index, Change object, String value) {
				List<Change> changes = getValues();
				changes.remove(index);
			}
		});
		removeButtonColumn.setCellStyleNames("editChangesColumn removeButtonColumn");
		table.addColumn(removeButtonColumn, columnHeader);
	}

}
