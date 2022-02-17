/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.metadata.csw;

/**
 * Encapsulates data from metadata record
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanRecordMetadata {

	private final String recordId;

	private final String resourceIdentifier;

	/**
	 * @param recordId id of the record, never <code>null</code>
	 * @param resourceIdentifier resource identifier of the record, may be
	 * <code>null</code>
	 */
	public PlanRecordMetadata(String recordId, String resourceIdentifier) {
		this.recordId = recordId;
		this.resourceIdentifier = resourceIdentifier;
	}

	/**
	 * @return id of the record, never <code>null</code>
	 */
	public String getRecordId() {
		return recordId;
	}

	/**
	 * @return resource identifier of the record, may be <code>null</code>
	 */
	public String getResourceIdentifier() {
		return resourceIdentifier;
	}

}
