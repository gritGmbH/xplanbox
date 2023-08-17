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
 * Used to create instances of {@link ExternalReferenceInfo}.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class ExternalReferenceInfoBuilder {

	private final List<ExternalReference> nonRasterRefs = new ArrayList<>();

	private final List<ExternalReference> rasterPlanBaseScans = new ArrayList<>();

	private final List<ExternalReference> rasterPlanUpdateScans = new ArrayList<>();

	/**
	 * @param nonRasterRef references which is not a raster reference, add to the
	 * {@link ExternalReferenceInfo}. May be <code>null</code>
	 * @return this instance of the {@link ExternalReferenceInfoBuilder}
	 */
	public ExternalReferenceInfoBuilder addNonRasterReference(ExternalReference nonRasterRef) {
		if (nonRasterRef != null)
			this.nonRasterRefs.add(nonRasterRef);
		return this;
	}

	/**
	 * @param nonRasterRefs list of references which are not a raster references, added to
	 * the {@link ExternalReferenceInfo}. May be empty but never <code>null</code>
	 * @return this instance of the {@link ExternalReferenceInfoBuilder}
	 */
	public ExternalReferenceInfoBuilder withNonRasterReferences(List<ExternalReference> nonRasterRefs) {
		this.nonRasterRefs.addAll(nonRasterRefs);
		return this;
	}

	/**
	 * @param rasterPlanBaseScan base scan to add to the {@link ExternalReferenceInfo}.
	 * May be <code>null</code>
	 * @return this instance of the {@link ExternalReferenceInfoBuilder}
	 */
	public ExternalReferenceInfoBuilder addRasterPlanBaseScan(ExternalReference rasterPlanBaseScan) {
		if (rasterPlanBaseScan != null)
			this.rasterPlanBaseScans.add(rasterPlanBaseScan);
		return this;
	}

	/**
	 * @param rasterPlanBaseScans list of base scans to add to the
	 * {@link ExternalReferenceInfo}. May be empty but never <code>null</code>
	 * @return this instance of the {@link ExternalReferenceInfoBuilder}
	 */
	public ExternalReferenceInfoBuilder addRasterPlanBaseScans(List<ExternalReference> rasterPlanBaseScans) {
		this.rasterPlanBaseScans.addAll(rasterPlanBaseScans);
		return this;
	}

	/**
	 * @param rasterPlanUpdateScan update scan to add to the
	 * {@link ExternalReferenceInfo}. May be <code>null</code>
	 * @return this instance of the {@link ExternalReferenceInfoBuilder}
	 */
	public ExternalReferenceInfoBuilder addRasterPlanUpdateScan(ExternalReference rasterPlanUpdateScan) {
		if (rasterPlanUpdateScan != null)
			this.rasterPlanUpdateScans.add(rasterPlanUpdateScan);
		return this;
	}

	/**
	 * @param rasterPlanUpdateScans list of update scans to add to the
	 * {@link ExternalReferenceInfo}. May be empty but never <code>null</code>
	 * @return this instance of the {@link ExternalReferenceInfoBuilder}
	 */
	public ExternalReferenceInfoBuilder addRasterPlanUpdateScans(List<ExternalReference> rasterPlanUpdateScans) {
		this.rasterPlanUpdateScans.addAll(rasterPlanUpdateScans);
		return this;
	}

	public ExternalReferenceInfo build() {
		return new ExternalReferenceInfo(nonRasterRefs, rasterPlanBaseScans, rasterPlanUpdateScans);
	}

}
