/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.inspector.geltungsbereich;

import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_53;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class GeltungsbereichFeatureAnalyser {

	private static final List<QName> OBJECTS_ALLOWED_OUTSIDE = new ArrayList<>();

	static {
		addForAllXPlanVersions("XP_FPO");
		addForAllXPlanVersions("XP_LPO");
		addForAllXPlanVersions("XP_LTO");
		addForAllXPlanVersions("XP_PPO");
		addForAllXPlanVersions("XP_PTO");
		addForAllXPlanVersions("XP_TPO");
		OBJECTS_ALLOWED_OUTSIDE.add(new QName(XPLAN_53.getNamespace(), "BP_HoehenMass"));
		OBJECTS_ALLOWED_OUTSIDE.add(new QName(XPLAN_53.getNamespace(), "BP_AbstandsMass"));
		OBJECTS_ALLOWED_OUTSIDE.add(new QName(XPLAN_54.getNamespace(), "BP_HoehenMass"));
		OBJECTS_ALLOWED_OUTSIDE.add(new QName(XPLAN_54.getNamespace(), "BP_AbstandsMass"));
		OBJECTS_ALLOWED_OUTSIDE.add(new QName(XPLAN_60.getNamespace(), "BP_HoehenMass"));
		OBJECTS_ALLOWED_OUTSIDE.add(new QName(XPLAN_60.getNamespace(), "BP_AbstandsMass"));
	}

	private static void addForAllXPlanVersions(String localName) {
		for (XPlanVersion version : XPlanVersion.values()) {
			if (!XPLAN_SYN.equals(version)) {
				OBJECTS_ALLOWED_OUTSIDE.add(new QName(version.getNamespace(), localName));
			}
		}
	}

	/**
	 * @param feature never <code>null</code>
	 * @return <code>true</code> if the feature is allowed to be outside of the
	 * geltungsbereich, <code>false</code> otherwise
	 */
	boolean isAllowedToBeOutside(Feature feature) {
		return OBJECTS_ALLOWED_OUTSIDE.contains(feature.getName());
	}

	/**
	 * @param feature never <code>null</code>
	 * @return <code>true</code> if the feature is a feature collection,
	 * <code>false</code> otherwise
	 */
	public boolean isFeatureCollection(Feature feature) {
		return feature instanceof FeatureCollection;
	}

}
