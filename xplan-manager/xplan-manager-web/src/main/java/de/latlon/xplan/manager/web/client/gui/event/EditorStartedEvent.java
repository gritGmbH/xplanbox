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
package de.latlon.xplan.manager.web.client.gui.event;

import com.google.gwt.event.shared.GwtEvent;
import de.latlon.xplan.manager.web.client.gui.editor.EditPlanType;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;
import de.latlon.xplan.manager.web.shared.Bereich;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;

import java.util.List;

/**
 * Indicates that the editing was started by the user.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class EditorStartedEvent extends GwtEvent<EditorStartedEventHandler> {

	public static Type<EditorStartedEventHandler> TYPE = new Type<EditorStartedEventHandler>();

	private final String planId;

	private List<Bereich> bereiche;

	private final EditVersion version;

	private final EditPlanType planType;

	private final XPlanToEdit xPlantoEdit;

	/**
	 * @param planId of the plan to edit
	 * @param bereiche of the plan to edit
	 * @param version of the plan to edit, never <code>null</code>
	 * @param planType of the plan to edit, never <code>null</code>
	 * @param xPlantoEdit never <code>null</code>
	 */
	public EditorStartedEvent(String planId, List<Bereich> bereiche, EditVersion version, EditPlanType planType,
			XPlanToEdit xPlantoEdit) {
		this.planId = planId;
		this.bereiche = bereiche;
		this.version = version;
		this.planType = planType;
		this.xPlantoEdit = xPlantoEdit;
	}

	@Override
	public Type<EditorStartedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditorStartedEventHandler handler) {
		handler.onEditorStarted(this);
	}

	/**
	 * @return the id of the plan to edit
	 */
	public String getPlanId() {
		return planId;
	}

	/**
	 * @return the version of the plan to edit, never <code>null</code>
	 */
	public EditVersion getVersion() {
		return version;
	}

	/**
	 * @return the type of the plan to edit, never <code>null</code>
	 */
	public EditPlanType getPlanType() {
		return planType;
	}

	/**
	 * @return the plan to edit, never <code>null</code>
	 */
	public XPlanToEdit getxPlantoEdit() {
		return xPlantoEdit;
	}

	/**
	 * @return the bereiche of the plan to edit
	 */
	public List<Bereich> getBereiche() {
		return bereiche;
	}

}
