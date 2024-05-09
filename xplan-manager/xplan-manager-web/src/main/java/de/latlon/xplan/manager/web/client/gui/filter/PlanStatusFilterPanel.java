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

import static com.google.gwt.user.client.ui.HasHorizontalAlignment.ALIGN_LEFT;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import de.latlon.xplan.manager.web.client.filter.PlanFilter;
import de.latlon.xplan.manager.web.client.filter.PlanStatusFilter;
import de.latlon.xplan.manager.web.client.i18n.XPlanWebMessages;
import de.latlon.xplan.manager.web.shared.PlanStatus;

/**
 * GUI component containing the plan status filter of the plan list.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class PlanStatusFilterPanel extends AbstractFilterPanel implements ResetableFilterPanel {

	private final XPlanWebMessages messages = GWT.create(XPlanWebMessages.class);

	private final ListBox planStatusListBox;

	public PlanStatusFilterPanel(FilterExecutor filterExecutor) {
		super(filterExecutor);
		planStatusListBox = createPlanStatusListBox();
		createUi();
	}

	@Override
	public void reset() {
		planStatusListBox.setSelectedIndex(0);
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
		layout.setWidget(1, 1, createPlanStatusLabel());
		layout.setWidget(2, 1, planStatusListBox);
		return layout;
	}

	private Widget createPlanStatusLabel() {
		return new Label(messages.filterPlanStatusLabel());
	}

	private ListBox createPlanStatusListBox() {
		final ListBox planStatusListBox = new ListBox();
		planStatusListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				PlanFilter filter = createFilter(planStatusListBox);
				updateAndExecuteFilter(filter);
			}

			private PlanStatusFilter createFilter(final ListBox planStatusListBox) {
				int selectedIndex = planStatusListBox.getSelectedIndex();
				if (selectedIndex <= 0)
					return new PlanStatusFilter(null);
				return new PlanStatusFilter(planStatusListBox.getValue(selectedIndex));
			}
		});
		planStatusListBox.setTitle(messages.filterPlanStatusTooltip());
		addItems(planStatusListBox);
		return planStatusListBox;
	}

	private void addItems(ListBox planStatusListBox) {
		planStatusListBox.addItem(messages.filterPlanStatusSelectionAll());
		for (PlanStatus planStatus : PlanStatus.values()) {
			planStatusListBox.addItem(planStatus.getMessage());
		}
	}

}
