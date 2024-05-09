/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.gui.editor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RowCountChangeEvent;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.CodelistProvider;
import de.latlon.xplan.manager.web.client.gui.editor.codelist.TypeCodelistProvider;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;

import java.util.Collections;
import java.util.List;

/**
 * Base class for all sub panels with a table in the {@link EditorPanel}.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public abstract class AbstractEditorSubPanelWithTable<T> extends CaptionPanel {

	private static final int TEXT_LIMIT = 500;

	protected static final XPlanWebMessages MESSAGES = GWT.create(XPlanWebMessages.class);

	protected static final CodelistProvider CODELIST_PROVIDER = new CodelistProvider();

	protected static final TypeCodelistProvider TYPE_CODELIST_PROVIDER = new TypeCodelistProvider();

	protected final EditVersion version;

	protected final EditPlanType planType;

	private final ListDataProvider<T> provider = new ListDataProvider<T>();

	private final CellTable<T> table;

	/**
	 * @param version of the plan to edit, never <code>null</code>
	 * @param planType type of the plan to edit, never <code>null</code>
	 * @param captionText the text of the caption, never <code>null</code>
	 */
	public AbstractEditorSubPanelWithTable(EditVersion version, EditPlanType planType, String captionText) {
		this.version = version;
		this.planType = planType;
		setCaptionText(captionText);
		this.table = createTable();
	}

	/**
	 * @return the table, never <code>null</code>
	 */
	public CellTable<T> getTable() {
		return table;
	}

	/**
	 * @return the values displayed in the table, may be empty but never <code>null</code>
	 */
	public List<T> getValues() {
		return provider.getList();
	}

	/**
	 * Adds the passed values to the table.
	 * @param values to add, may be <code>null</code> or empty
	 */
	public void setValues(List<T> values) {
		if (values == null)
			values = Collections.emptyList();
		table.setRowCount(values.size(), true);
		List<T> providedChanges = getValues();
		providedChanges.clear();
		providedChanges.addAll(values);
	}

	/**
	 * Adds the columns displayed by the table.
	 * @param table to add the columns to, never <code>null</code>
	 */
	protected abstract void initColumns(CellTable<T> table);

	protected String shortText(String textToShort) {
		if (textToShort != null && textToShort.length() > TEXT_LIMIT + 5) {
			String shortendText = textToShort.substring(0, TEXT_LIMIT);
			return shortendText + "...";
		}
		return textToShort;

	}

	private CellTable<T> createTable() {
		final CellTable<T> table = new CellTable<T>();
		table.addRowCountChangeHandler(new RowCountChangeEvent.Handler() {
			@Override
			public void onRowCountChange(RowCountChangeEvent event) {
				table.setVisibleRange(new Range(0, event.getNewRowCount()));
			}
		});
		initColumns(table);
		provider.addDataDisplay(table);
		return table;
	}

}
