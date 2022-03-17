/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

	private final List<ExternalReference> externalRefs;

	private final List<ExternalReference> rasterPlanBaseScans;

	/**
	 * Externe Referenzen von XP_RasterplanAenderung (<= XPlan 4.1)
	 */
	private final List<ExternalReference> rasterPlanUpdateScans;

	public ExternalReferenceInfo() {
		this(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	}

	public ExternalReferenceInfo(List<ExternalReference> externalRefs, List<ExternalReference> rasterPlanBaseScans,
			List<ExternalReference> rasterPlanUpdateScans) {
		this.externalRefs = externalRefs;
		this.rasterPlanBaseScans = rasterPlanBaseScans;
		this.rasterPlanUpdateScans = rasterPlanUpdateScans;
	}

	public List<ExternalReference> getExternalRefs() {
		return externalRefs;
	}

	public void addExternalRefs(List<ExternalReference> externalRefs) {
		this.externalRefs.addAll(externalRefs);
	}

	public List<ExternalReference> getRasterPlanBaseScans() {
		return rasterPlanBaseScans;
	}

	public void addRasterPlanBaseScan(ExternalReference rasterPlanBaseScan) {
		this.rasterPlanBaseScans.add(rasterPlanBaseScan);
	}

	public void addRasterPlanBaseScans(List<ExternalReference> rasterPlanBaseScans) {
		this.rasterPlanBaseScans.addAll(rasterPlanBaseScans);
	}

	public List<ExternalReference> getRasterPlanUpdateScans() {
		return rasterPlanUpdateScans;
	}

	public void addRasterPlanUpdateScan(ExternalReference rasterPlanUpdateScan) {
		this.rasterPlanUpdateScans.add(rasterPlanUpdateScan);
	}

	public void addRasterPlanUpdateScans(List<ExternalReference> rasterPlanUpdateScans) {
		this.rasterPlanUpdateScans.addAll(rasterPlanUpdateScans);
	}

	public List<ExternalReference> getRasterPlanBaseAndUpdateScans() {
		List<ExternalReference> rasterPlanBaseAndUpdateScans = new ArrayList<>();
		rasterPlanBaseAndUpdateScans.addAll(rasterPlanBaseScans);
		rasterPlanBaseAndUpdateScans.addAll(rasterPlanUpdateScans);
		return rasterPlanBaseAndUpdateScans;
	}

	public List<ExternalReference> getAllReferences() {
		List<ExternalReference> allReferences = new ArrayList<>();
		allReferences.addAll(externalRefs);
		allReferences.addAll(rasterPlanBaseScans);
		allReferences.addAll(rasterPlanUpdateScans);
		return allReferences;
	}

	@Override
	public String toString() {
		return "ExternalReferenceInfo {externalRefs=" + externalRefs + ", rasterPlanBaseScans=" + rasterPlanBaseScans
				+ ", rasterPlanUpdateScans=" + rasterPlanUpdateScans + "}";
	}

}
