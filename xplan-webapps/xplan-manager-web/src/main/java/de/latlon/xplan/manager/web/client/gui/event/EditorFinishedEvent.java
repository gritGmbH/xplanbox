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
package de.latlon.xplan.manager.web.client.gui.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Indicates that the editing was finished by the user.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class EditorFinishedEvent extends GwtEvent<EditorFinishedEventHandler> {

	public static Type<EditorFinishedEventHandler> TYPE = new Type<EditorFinishedEventHandler>();

	@Override
	public Type<EditorFinishedEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(EditorFinishedEventHandler handler) {
		handler.onEditorFinished(this);
	}

}
