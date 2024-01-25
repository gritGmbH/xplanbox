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
package de.latlon.xplan.manager.web.client.gui.editor.text;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TextHeader;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.comparator.AlphanumericComparator;
import de.latlon.xplan.manager.web.client.gui.editor.AbstractEditorSubPanelWithTable;
import de.latlon.xplan.manager.web.client.gui.editor.EditPlanType;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.client.gui.editor.dialog.SavedHandler;
import de.latlon.xplan.manager.web.shared.edit.Text;
import de.latlon.xplan.manager.web.shared.edit.TextRechtscharacterType;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_CENTER;
import static de.latlon.xplan.manager.web.client.gui.editor.EditVersion.XPLAN_41;

/**
 * Panel for texts.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class TextsPanel extends AbstractEditorSubPanelWithTable<Text> {

	/**
	 * @param version of the plan to edit, never <code>null</code>
	 * @param planType
	 */
	public TextsPanel(EditVersion version, EditPlanType planType) {
		super(version, planType, MESSAGES.editCaptionTexts());
		add(createGui());
	}

	@Override
	protected void initColumns(CellTable<Text> textsList) {
		addKeyColumn(textsList);
		addBasisColumn(textsList);
		addTextColumn(textsList);
		// #3305 - georeference is not needed.
		// addGeoReferenceColumn( textsList );
		if (!XPLAN_41.equals(version)) {
			addRechtscharakterColumn(textsList);
		}
		addReferenceColumn(textsList);

		TextHeader actionHeader = new TextHeader(MESSAGES.actions());
		addEditColumn(textsList, actionHeader);
	}

	@Override
	public void setValues(List<Text> values) {
		Collections.sort(values, new Comparator<Text>() {
			@Override
			public int compare(Text o1, Text o2) {
				AlphanumericComparator alphanumericComparator = new AlphanumericComparator();
				return alphanumericComparator.compare(o1.getKey(), o2.getKey());
			}
		});
		super.setValues(values);
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
		Button newButton = new Button(MESSAGES.editCaptionNewText(), new ClickHandler() {
			public void onClick(ClickEvent event) {
				final TextDialog textDialog = new TextDialog(version, planType);
				textDialog.addSaveHandler(new SavedHandler() {
					@Override
					public void changesSaved() {
						Text newText = textDialog.getEditedText();
						List<Text> texts = getValues();
						texts.add(newText);
						textDialog.hide();
					}
				});
				textDialog.center();
				textDialog.show();
			}
		});
		return newButton;
	}

	private void addKeyColumn(CellTable<Text> table) {
		TextColumn<Text> keyColumn = new TextColumn<Text>() {
			@Override
			public String getValue(Text textData) {
				return shortText(textData.getKey());
			}
		};
		keyColumn.setCellStyleNames("editTextsColumn keyColumn");
		table.addColumn(keyColumn, MESSAGES.editCaptionTextsKey());
	}

	private void addBasisColumn(CellTable<Text> table) {
		TextColumn<Text> basisColumn = new TextColumn<Text>() {
			@Override
			public String getValue(Text textData) {
				return shortText(textData.getBasis());
			}
		};
		basisColumn.setCellStyleNames("editTextsColumn basisColumn");
		table.addColumn(basisColumn, MESSAGES.editCaptionTextsBasis());
	}

	private void addTextColumn(CellTable<Text> table) {
		TextColumn<Text> textColumn = new TextColumn<Text>() {
			@Override
			public String getValue(Text textData) {
				return shortText(textData.getText());
			}
		};
		textColumn.setCellStyleNames("editTextsColumn textColumn");
		table.addColumn(textColumn, MESSAGES.editCaptionTextsText());
	}

	private void addReferenceColumn(CellTable<Text> table) {
		TextColumn<Text> referenceColumn = new TextColumn<Text>() {
			@Override
			public String getValue(Text textData) {
				return textData.getReference();
			}
		};
		referenceColumn.setCellStyleNames("editTextsColumn referenceColumn");
		table.addColumn(referenceColumn, MESSAGES.editCaptionTextsReference());
	}

	private void addRechtscharakterColumn(CellTable<Text> table) {
		TextColumn<Text> textColumn = new TextColumn<Text>() {
			@Override
			public String getValue(Text textData) {
				return TYPE_CODELIST_PROVIDER.translate(TextRechtscharacterType.class, textData.getRechtscharakter());
			}
		};
		textColumn.setCellStyleNames("editTextsColumn rechtscharakterColumn");
		table.addColumn(textColumn, MESSAGES.editCaptionReferencesType());
	}

	// #3305 - georeference is not needed.
	// private void addGeoReferenceColumn( CellTable<Text> table ) {
	// TextColumn<Text> geoReferenceColumn = new TextColumn<Text>() {
	// @Override
	// public String getValue( Text textData ) {
	// return textData.getGeoReference();
	// }
	// };
	// geoReferenceColumn.setCellStyleNames( "editTextsColumn geoReferenceColumn" );
	// table.addColumn( geoReferenceColumn, MESSAGES.editCaptionTextsGeoReference() );
	// }

	private void addEditColumn(final CellTable<Text> table, TextHeader columnHeader) {
		ButtonCell editButtonCell = new ButtonCell();
		final Column<Text, String> editButtonColumn = new Column<Text, String>(editButtonCell) {
			@Override
			public String getValue(Text object) {
				return "";
			}
		};
		editButtonColumn.setFieldUpdater(new FieldUpdater<Text, String>() {
			public void update(final int index, Text text, String value) {
				final TextDialog textDialog = new TextDialog(version, planType, text);
				textDialog.addSaveHandler(new SavedHandler() {
					@Override
					public void changesSaved() {
						Text editedtext = textDialog.getEditedText();
						List<Text> texts = getValues();
						texts.remove(index);
						texts.add(index, editedtext);
						textDialog.hide();
					}
				});
				textDialog.center();
				textDialog.show();
			}
		});
		editButtonColumn.setCellStyleNames("editTextsColumn editButtonColumn");
		table.addColumn(editButtonColumn, columnHeader);
	}

}
