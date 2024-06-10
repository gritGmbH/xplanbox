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
package de.latlon.xplan.manager.web.shared;

import de.latlon.xplan.manager.web.client.gui.PlanListColumnType;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Encapsulates a manager web configuration
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class ManagerWebConfiguration implements Serializable {

	private static final long serialVersionUID = -6690846114049627139L;

	private boolean legislationStatusActivated;

	private boolean editorActivated;

	private boolean publishingInspirePluActivated;

	private String[] hiddenColumns;

	/**
	 * Instantiates a {@link ManagerWebConfiguration} with default values.
	 */
	public ManagerWebConfiguration() {
		this(false, false, false, new String[] {});
	}

	/**
	 * @param legislationStatusActivated <code>true</code> if the dialog to select the
	 * legislation status should be activated, <code>false</code> otherwise
	 * @param editorActivated <code>true</code> if editing of plans is activated,
	 * <code>false</code> otherwise <code>null</code>
	 */
	public ManagerWebConfiguration(boolean legislationStatusActivated, boolean editorActivated,
			boolean publishingInspirePluActivated, String[] hiddenColumns) {
		this.legislationStatusActivated = legislationStatusActivated;
		this.editorActivated = editorActivated;
		this.publishingInspirePluActivated = publishingInspirePluActivated;
		this.hiddenColumns = hiddenColumns;
	}

	/**
	 * @return <code>true</code> if the dialog to select the legislation status should be
	 * activated, <code>false</code> otherwise
	 */
	public boolean isLegislationStatusActivated() {
		return legislationStatusActivated;
	}

	/**
	 * @return <code>true</code> if editing of plans is activated, <code>false</code>
	 * otherwise
	 */
	public boolean isEditorActivated() {
		return editorActivated;
	}

	/**
	 * @return <code>true</code> if publishing of plans as INSPIRE PLU datasets is
	 * activated, <code>false</code> otherwise
	 */
	public boolean isPublishingInspirePluActivated() {
		return publishingInspirePluActivated;
	}

	/**
	 * @param planListColumnType
	 * @return return <code>true</code> if given column is visible, otherwise
	 * <code>false</code>.
	 */
	public boolean isColumnVisible(PlanListColumnType planListColumnType) {
		for (String hiddenColumn : hiddenColumns) {
			if (hiddenColumn != null && hiddenColumn.trim().equalsIgnoreCase(planListColumnType.name()))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(hiddenColumns);
		result = prime * result + (editorActivated ? 1231 : 1237);
		result = prime * result + (legislationStatusActivated ? 1231 : 1237);
		result = prime * result + (publishingInspirePluActivated ? 1231 : 1237);
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
		ManagerWebConfiguration other = (ManagerWebConfiguration) obj;
		if (!Arrays.equals(hiddenColumns, other.hiddenColumns))
			return false;
		if (editorActivated != other.editorActivated)
			return false;
		if (legislationStatusActivated != other.legislationStatusActivated)
			return false;
		if (publishingInspirePluActivated != other.publishingInspirePluActivated)
			return false;
		return true;
	}

}
