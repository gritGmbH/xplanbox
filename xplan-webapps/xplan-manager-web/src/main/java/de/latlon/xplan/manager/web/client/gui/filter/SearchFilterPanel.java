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
package de.latlon.xplan.manager.web.client.gui.filter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.filter.FreeTextFilter;
import de.latlon.xplan.manager.web.client.filter.PlanFilter;
import de.latlon.xplan.manager.web.client.gui.PlanListColumnType;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;

import java.util.HashMap;
import java.util.Map;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.ADDITIONALTYPE;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.ID;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.IMPORTDATE;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.LEGISLATIONSTATUS;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.NAME;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.NUMBER;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.RELEASEDATE;
import static de.latlon.xplan.manager.web.client.gui.PlanListColumnType.TYPE;

/**
 * GUI component containing the search filter of the plan list.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class SearchFilterPanel extends AbstractFilterPanel implements ResetableFilterPanel {

	private static final XPlanWebMessages messages = GWT.create(XPlanWebMessages.class);

	private final Map<String, PlanListColumnType> labelsToColumns;

	private final ListBox listBox;

	private final TextBox textBox;

	public SearchFilterPanel(FilterExecutor filterExecutor, ManagerWebConfiguration configuration) {
		super(filterExecutor);
		labelsToColumns = initColumns(configuration);
		this.listBox = createListBox();
		this.textBox = createTextBox();
		createUi();
	}

	@Override
	public void reset() {
		listBox.setSelectedIndex(0);
		textBox.setValue(null);
		updateFilter(null);
	}

	private void createUi() {
		Widget layout = createLayout();
		this.setWidget(layout);
	}

	private Widget createLayout() {
		FlexTable layout = new FlexTable();
		FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setHorizontalAlignment(1, 1, ALIGN_LEFT);
		layout.setCellSpacing(5);
		layout.setWidget(1, 1, new Label(messages.searchLabel()));
		layout.setWidget(2, 1, listBox);
		layout.setWidget(2, 2, textBox);
		layout.setWidget(2, 3, createButton());
		return layout;
	}

	private ListBox createListBox() {
		ListBox listBox = new ListBox();
		listBox.addItem(messages.searchOnAllColumns());
		for (String column : labelsToColumns.keySet()) {
			listBox.addItem(column);
		}
		return listBox;
	}

	private TextBox createTextBox() {
		TextBox textBox = new TextBox();
		textBox.setTitle(messages.filterFreeTextTooltip());
		textBox.addKeyDownHandler(createKeyDownHandler());
		return textBox;
	}

	private KeyDownHandler createKeyDownHandler() {
		return new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
					performSearch();
			}
		};
	}

	private Button createButton() {
		Button button = new Button(messages.searchButton());
		button.addClickHandler(createClickHandler());
		return button;
	}

	private ClickHandler createClickHandler() {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				performSearch();
			}
		};
	}

	private void performSearch() {
		String searchString = textBox.getValue();
		PlanListColumnType searchColumn = detectSearchColumn(listBox);
		PlanFilter freeTextFilter = new FreeTextFilter(searchColumn, searchString);
		updateAndExecuteFilter(freeTextFilter);
	}

	private PlanListColumnType detectSearchColumn(final ListBox listBox) {
		int selectedIndex = listBox.getSelectedIndex();
		if (selectedIndex > 0) {
			String selectedValue = listBox.getValue(selectedIndex);
			return labelsToColumns.get(selectedValue);
		}
		return null;
	}

	private Map<String, PlanListColumnType> initColumns(ManagerWebConfiguration configuration) {
		Map<String, PlanListColumnType> labelsToColumns = new HashMap<String, PlanListColumnType>();
		addColumn(labelsToColumns, messages.idColumn(), ID, configuration);
		addColumn(labelsToColumns, messages.numberColumn(), NUMBER, configuration);
		addColumn(labelsToColumns, messages.nameColumn(), NAME, configuration);
		addColumn(labelsToColumns, messages.planArt(), TYPE, configuration);
		addColumn(labelsToColumns, messages.sonstPlanArt(), ADDITIONALTYPE, configuration);
		addColumn(labelsToColumns, messages.legislationStatus(), LEGISLATIONSTATUS, configuration);
		addColumn(labelsToColumns, messages.releaseDate(), RELEASEDATE, configuration);
		addColumn(labelsToColumns, messages.importDate(), IMPORTDATE, configuration);
		return labelsToColumns;
	}

	private void addColumn(Map<String, PlanListColumnType> labelsToColumns, String label, PlanListColumnType columnType,
			ManagerWebConfiguration configuration) {
		if (configuration.isColumnVisible(columnType))
			labelsToColumns.put(label, columnType);
	}

}
