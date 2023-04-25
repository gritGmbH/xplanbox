/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster.config;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.manager.web.shared.PlanStatus;

import java.util.HashMap;
import java.util.Map;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static de.latlon.xplan.commons.XPlanType.FP_Plan;
import static de.latlon.xplan.commons.XPlanType.LP_Plan;
import static de.latlon.xplan.commons.XPlanType.RP_Plan;
import static de.latlon.xplan.commons.XPlanType.SO_Plan;
import static de.latlon.xplan.manager.web.shared.PlanStatus.ARCHIVIERT;
import static de.latlon.xplan.manager.web.shared.PlanStatus.IN_AUFSTELLUNG;

/**
 * Contains convenience methods to write the wms configuration for a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ConfigWriterUtils {

	private static final Map<XPlanType, String> typeToExtension = new HashMap<XPlanType, String>();

	static {
		typeToExtension.put(BP_Plan, "bplan");
		typeToExtension.put(FP_Plan, "fplan");
		typeToExtension.put(LP_Plan, "lplan");
		typeToExtension.put(RP_Plan, "rplan");
		typeToExtension.put(SO_Plan, "soplan");
	}

	private ConfigWriterUtils() {
	}

	/**
	 * Detects the type of the archive
	 * @param type never <code>null</code>
	 * @param planStatus may be <code>null</code> (same as FESTGESTELLT)
	 * @return the type, never <code>null</code>
	 * @throws IllegalArgumentException if the archive type is not supported
	 */
	public static String detectType(XPlanType type, PlanStatus planStatus) {
		if (typeToExtension.containsKey(type)) {
			String extension = typeToExtension.get(type);
			if (IN_AUFSTELLUNG.equals(planStatus))
				return extension + "pre";
			if (ARCHIVIERT.equals(planStatus))
				return extension + "archive";
			return extension;
		}
		String msg = "Plan with type " + type + " is not supported for wms configuration creation!";
		throw new IllegalArgumentException(msg);
	}

}
