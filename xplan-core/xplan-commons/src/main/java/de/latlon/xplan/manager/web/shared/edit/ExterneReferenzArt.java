/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
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
