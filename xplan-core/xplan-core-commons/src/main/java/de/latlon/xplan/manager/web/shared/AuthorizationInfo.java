/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.manager.web.shared;

import java.io.Serializable;

/**
 * Holds information about the authorization.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author <a href="mailto:wanhoff@lat-lon.de">Jeronimo Wanhoff</a>
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public class AuthorizationInfo implements Serializable {

	private static final long serialVersionUID = -8979723934544833665L;

	private boolean superUser;

	private boolean editor;

	/**
	 * An empty constructor is mandatory for GWT applications.
	 */
	public AuthorizationInfo() {
	}

	/**
	 * Instantiates a new {@link AuthorizationInfo} without editor permissions (if not
	 * super user).
	 * @param isSuperUser true if user is a super user, false otherwise
	 */
	public AuthorizationInfo(boolean isSuperUser) {
		this(isSuperUser, false);
	}

	/**
	 * @param isSuperUser true if user is a super user, false otherwise
	 * @param isEditor true if user is editor, false otherwise
	 */
	public AuthorizationInfo(boolean isSuperUser, boolean isEditor) {
		this.superUser = isSuperUser;
		this.editor = isEditor;
	}

	/**
	 * @return true if super user, false otherwise
	 */
	public boolean isSuperUser() {
		return superUser;
	}

	/**
	 * Setter is mandatory for GWT applications.
	 * @param isSuperUser is super user
	 */
	public void setSuperUser(boolean isSuperUser) {
		this.superUser = isSuperUser;
	}

	/**
	 * @return true if editor, false otherwise
	 */
	public boolean isEditor() {
		return editor;
	}

	/**
	 * Setter is mandatory for GWT applications.
	 * @param isEditor is editor
	 */
	public void setEditor(boolean isEditor) {
		this.editor = isEditor;
	}

}
