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
package de.latlon.xplan.manager.web.client.gui.editor.codelist;

import de.latlon.xplan.manager.web.client.gui.editor.EditPlanType;
import de.latlon.xplan.manager.web.client.gui.editor.EditVersion;

/**
 * Represents a key for a codelist, combined out of the version and codelistType.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
class CodelistKey {

	private final EditVersion version;

	private final EditPlanType planType;

	private final CodelistType codelistType;

	/**
	 * Instantiates a {@link CodelistKey} out of the version and codelistType.
	 * @param version may be <code>null</code>
	 * @param codelistType may be <code>null</code>
	 */
	CodelistKey(EditVersion version, EditPlanType planType, CodelistType codelistType) {
		this.version = version;
		this.planType = planType;
		this.codelistType = codelistType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codelistType == null) ? 0 : codelistType.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((planType == null) ? 0 : planType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CodelistKey other = (CodelistKey) obj;
		if (codelistType != other.codelistType)
			return false;
		if (version != other.version)
			return false;
		if (planType != other.planType)
			return false;
		return true;
	}

}
