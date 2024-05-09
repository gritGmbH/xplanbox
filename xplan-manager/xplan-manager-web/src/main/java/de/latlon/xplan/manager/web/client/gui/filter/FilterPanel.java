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
package de.latlon.xplan.manager.web.client.gui.filter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.filter.PlanFilter;
import de.latlon.xplan.manager.web.client.gui.PlanListPanel;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.ManagerWebConfiguration;

/**
 * GUI component containing the filter tab panel of the plan list.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class FilterPanel extends HorizontalPanel implements FilterExecutor {

	private final XPlanWebMessages messages = GWT.create(XPlanWebMessages.class);

	private final PlanListPanel planListPanel;

	private final List<FilterProvider> filterProviders = new ArrayList<FilterProvider>();

	private final List<ResetableFilterPanel> resetableFilterPanels = new ArrayList<ResetableFilterPanel>();

	public FilterPanel(PlanListPanel planListPanel, ManagerWebConfiguration configuration) {
		this.planListPanel = planListPanel;
		createUi(configuration);
	}

	/**
	 * Resets and updates all filter panels.
	 */
	public void resetAndFilterPlanList() {
		for (ResetableFilterPanel resetableFilterPanel : resetableFilterPanels) {
			resetableFilterPanel.reset();
		}
		doFilter();
	}

	@Override
	public void addFilterProvider(FilterProvider filterProvider) {
		filterProviders.add(filterProvider);
	}

	@Override
	public void doFilter() {
		List<PlanFilter> filters = retrievePlanFilters();
		planListPanel.applyFilter(filters);
	}

	private void addResetableFilterPanel(ResetableFilterPanel resetableFilterPanel) {
		resetableFilterPanels.add(resetableFilterPanel);
	}

	private void createUi(ManagerWebConfiguration configuration) {
		setWidth("100%");
		setVerticalAlignment(ALIGN_BOTTOM);

		CategoryFilterPanel categoryFilterPanel = new CategoryFilterPanel(this, configuration);
		addResetableFilterPanel(categoryFilterPanel);
		PlanStatusFilterPanel planStatusFilterPanel = new PlanStatusFilterPanel(this);
		addResetableFilterPanel(planStatusFilterPanel);
		SearchFilterPanel searchFilterPanel = new SearchFilterPanel(this, configuration);
		addResetableFilterPanel(searchFilterPanel);

		Widget resetButton = createResetButtonWidget();
		add(categoryFilterPanel);
		add(planStatusFilterPanel);
		add(searchFilterPanel);
		add(resetButton);
		getElement().setId("filter-panel");
	}

	private Widget createResetButtonWidget() {
		Button button = new Button(messages.filterReset());
		button.addClickHandler(createClickHandler());
		return createResetButtonLayout(button);
	}

	private Widget createResetButtonLayout(Button button) {
		FlexTable layout = new FlexTable();
		FlexTable.FlexCellFormatter formatter = layout.getFlexCellFormatter();
		formatter.setHorizontalAlignment(1, 1, ALIGN_LEFT);
		layout.setCellSpacing(5);
		layout.setWidget(2, 1, button);
		return layout;
	}

	private ClickHandler createClickHandler() {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				resetAndFilterPlanList();
			}
		};
	}

	private List<PlanFilter> retrievePlanFilters() {
		ArrayList<PlanFilter> planFilters = new ArrayList<PlanFilter>();
		for (FilterProvider filterProvider : filterProviders) {
			PlanFilter planFilter = filterProvider.provideFilter();
			if (planFilter != null)
				planFilters.add(planFilter);
		}
		return planFilters;
	}

}
