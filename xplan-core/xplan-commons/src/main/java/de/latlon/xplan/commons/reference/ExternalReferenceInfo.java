/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.reference;

import java.util.ArrayList;
import java.util.List;

/**
 * Information on <code>XP_ExterneReferenz</code>/<code>XP_ExterneReferenzPlan</code>
 * objects as well as their usage inside
 * <code>XP_RasterplanBasis</code>/<code>XP_RasterplanAenderung</code> features.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class ExternalReferenceInfo {

	private final List<ExternalReference> nonRasterRefs;

	private final List<ExternalReference> rasterPlanBaseScans;

	/**
	 * Externe Referenzen von XP_RasterplanAenderung (<= XPlan 4.1)
	 */
	private final List<ExternalReference> rasterPlanUpdateScans;

	ExternalReferenceInfo(List<ExternalReference> nonRasterRefs, List<ExternalReference> rasterPlanBaseScans,
			List<ExternalReference> rasterPlanUpdateScans) {
		this.nonRasterRefs = nonRasterRefs;
		this.rasterPlanBaseScans = rasterPlanBaseScans;
		this.rasterPlanUpdateScans = rasterPlanUpdateScans;
	}

	public List<ExternalReference> getNonRasterRefs() {
		return nonRasterRefs;
	}

	public List<ExternalReference> getRasterPlanBaseScans() {
		return rasterPlanBaseScans;
	}

	public List<ExternalReference> getRasterPlanUpdateScans() {
		return rasterPlanUpdateScans;
	}

	public List<ExternalReference> getRasterPlanBaseAndUpdateScans() {
		List<ExternalReference> rasterPlanBaseAndUpdateScans = new ArrayList<>();
		rasterPlanBaseAndUpdateScans.addAll(rasterPlanBaseScans);
		rasterPlanBaseAndUpdateScans.addAll(rasterPlanUpdateScans);
		return rasterPlanBaseAndUpdateScans;
	}

	public List<ExternalReference> getAllReferences() {
		List<ExternalReference> allReferences = new ArrayList<>();
		allReferences.addAll(nonRasterRefs);
		allReferences.addAll(rasterPlanBaseScans);
		allReferences.addAll(rasterPlanUpdateScans);
		return allReferences;
	}

	@Override
	public String toString() {
		return "ExternalReferenceInfo {externalRefs=" + nonRasterRefs + ", rasterPlanBaseScans=" + rasterPlanBaseScans
				+ ", rasterPlanUpdateScans=" + rasterPlanUpdateScans + "}";
	}

}
