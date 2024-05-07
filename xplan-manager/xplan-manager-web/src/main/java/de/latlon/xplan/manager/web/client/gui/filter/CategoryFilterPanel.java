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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import de.latlon.xplan.manager.web.client.filter.CategoryFilter;
import de.latlon.xplan.manager.web.client.filter.PlanFilter;
import de.latlon.xplan.manager.web.client.i18n.DynamicXPlanWebMessages;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;
import static java.util.Arrays.asList;

/**
 * GUI component containing the category filter of the plan list.
 *
 * @deprecated This class be removed in a future version.
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
@Deprecated
public class CategoryFilterPanel extends AbstractFilterPanel implements ResetableFilterPanel {

	private final DynamicXPlanWebMessages dynamicMmessages = GWT.create(DynamicXPlanWebMessages.class);

	private final ListBox categoryListBox;

	public CategoryFilterPanel(FilterExecutor filterExecutor, ManagerWebConfiguration configuration) {
		super(filterExecutor);
		categoryListBox = createCategoryListBox(configuration);
		createUi();
	}

	@Override
	public void reset() {
		categoryListBox.setSelectedIndex(0);
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
		layout.setWidget(1, 1, createCategoryLabel());
		layout.setWidget(2, 1, categoryListBox);
		return layout;
	}

	private Widget createCategoryLabel() {
		return new Label(dynamicMmessages.filterCommunityLabel());
	}

	private ListBox createCategoryListBox(final ManagerWebConfiguration configuration) {
		final ListBox categoryListBox = new ListBox();
		categoryListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				PlanFilter categoryFilter = createCategoryFilter(categoryListBox);
				updateAndExecuteFilter(categoryFilter);
			}

			private CategoryFilter createCategoryFilter(final ListBox categoryListBox) {
				int selectedIndex = categoryListBox.getSelectedIndex();
				if (selectedIndex <= 0)
					return new CategoryFilter(null);
				else if (selectedIndex == categoryListBox.getItemCount() - 1) {
					return new CategoryFilter(asList(configuration.getCategoryFilterValues()), true);
				}
				return new CategoryFilter(categoryListBox.getValue(selectedIndex));
			}
		});
		categoryListBox.setTitle(dynamicMmessages.filterCommunityTooltip());
		addItems(categoryListBox, configuration);
		return categoryListBox;
	}

	private void addItems(ListBox categoryListBox, ManagerWebConfiguration configuration) {
		categoryListBox.addItem(dynamicMmessages.filterCommunitySelectionAll());
		for (String category : configuration.getCategoryFilterValues()) {
			categoryListBox.addItem(category);
		}
		categoryListBox.addItem(dynamicMmessages.filterCommunitySelectionOther());
	}

}
