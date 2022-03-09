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
package de.latlon.xplan.manager.web.shared.edit;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulate a raster basis.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class RasterBasis {

	private String featureId;

	private List<RasterReference> rasterReferences;

	public RasterBasis() {
	}

	public RasterBasis(String featureId) {
		this(featureId, null);
	}

	public RasterBasis(String featureId, List<RasterReference> rasterReferences) {
		this.featureId = featureId;
		this.rasterReferences = rasterReferences;
	}

	/**
	 * @return the id of the feature containing the references, may be <code>null</code>
	 */
	public String getFeatureId() {
		return featureId;
	}

	/**
	 * @param featureId the id of the feature containing the references, may be
	 * <code>null</code>
	 */
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}

	/**
	 * @return the raster references, never <code>null</code>
	 */
	public List<RasterReference> getRasterReferences() {
		if (rasterReferences == null)
			rasterReferences = new ArrayList<RasterReference>();
		return rasterReferences;
	}

	/**
	 * @param rasterReference to add, may be <code>null</code> (nothing is added)
	 */
	public void addRasterReference(RasterReference rasterReference) {
		if (rasterReference != null)
			getRasterReferences().add(rasterReference);
	}

	/**
	 * @param rasterReferences the raster references to set, may be <code>null</code>
	 */
	public void setRasterReferences(List<RasterReference> rasterReferences) {
		this.rasterReferences = rasterReferences;
	}

	@Override
	public String toString() {
		return "RasterWithReferences {featureId=" + featureId + ", rasterReferences=" + rasterReferences + "}";
	}

}
