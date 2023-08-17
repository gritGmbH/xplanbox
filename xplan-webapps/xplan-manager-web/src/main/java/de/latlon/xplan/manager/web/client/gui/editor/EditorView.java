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
package de.latlon.xplan.manager.web.client.gui.editor;

import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

import java.util.List;

/**
 * Encapsulated the view of the editor.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class EditorView extends VerticalPanel {

	private HandlerManager eventBus;

	/**
	 * @param eventBus required to control overall view events, never <code>null</code>
	 */
	public EditorView(final HandlerManager eventBus) {
		this.eventBus = eventBus;
		setWidth("100%");
		setHorizontalAlignment(ALIGN_CENTER);
		setSpacing(15);
	}

	/**
	 * @param planId of the plan to edit
	 * @param bereiche of the plan to edit
	 * @param version of the xplan to edit. never <code>null</code>
	 * @param planType of the xplan to edit. never <code>null</code>
	 * @param xPlantoEdit the xplan to edit, never <code>null</code>
	 */
	public void setXPlanToEdit(String planId, List<Bereich> bereiche, EditVersion version, EditPlanType planType,
			XPlanToEdit xPlantoEdit) {
		clear();
		EditorPanel editorPanel = new EditorPanel(version, planType, bereiche, eventBus);
		add(editorPanel);
		editorPanel.setXPlanToEdit(planId, xPlantoEdit);
	}

}
