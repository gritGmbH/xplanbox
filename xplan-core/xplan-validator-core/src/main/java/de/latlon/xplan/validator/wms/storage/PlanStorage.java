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
package de.latlon.xplan.validator.wms.storage;

import de.latlon.xplan.validator.wms.MapPreviewCreationException;
import org.deegree.feature.FeatureCollection;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface PlanStorage {

	/**
	 * @return a unique ID for the plan to store
	 */
	int retrieveUniquePlanid() throws MapPreviewCreationException;

	/**
	 * @param planId
	 * @param synFeatureCollection the feature collection to store, never
	 * <code>null</code>
	 * @throws MapPreviewCreationException if the feature collection could not be stored
	 */
	void storeSynFeatureCollection(int planId, FeatureCollection synFeatureCollection)
			throws MapPreviewCreationException;

}
