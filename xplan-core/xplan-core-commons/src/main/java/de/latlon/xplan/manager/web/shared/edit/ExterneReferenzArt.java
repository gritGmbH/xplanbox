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
package de.latlon.xplan.manager.web.shared.edit;

/**
 * Used to discriminate XP_ExterneReferenzArt.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public enum ExterneReferenzArt {

	DOKUMENT("Dokument"), PLANMITGEOREFERENZ("PlanMitGeoreferenz");

	private String code;

	ExterneReferenzArt(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static ExterneReferenzArt getByCode(String code) {
		if (code == null)
			return null;
		for (ExterneReferenzArt value : values()) {
			if (value.code.equals(code.trim()))
				return value;
		}
		return null;
	}

}
