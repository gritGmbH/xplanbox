/*-
 * #%L
 * xplan-wms - deegree XPlan WebMapService
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
package de.latlon.xplan.planwerkwms;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class Plan {

	private final String name;

	private final List<Integer> managerIds;

	private final String bbox;

	private final List<String> wmsTitles;

	private final List<String> resourceidentifiers;

	private final List<String> dataMetadataUrls;

	private final List<String> serviceMetadataUrls;

	public Plan(String name, List<Integer> managerIds, String bbox, List<String> wmsTitles,
			List<String> resourceidentifiers, List<String> dataMetadataUrls, List<String> serviceMetadataUrls) {
		this.name = name;
		this.managerIds = managerIds;
		this.bbox = bbox;
		this.wmsTitles = wmsTitles;
		this.resourceidentifiers = resourceidentifiers;
		this.dataMetadataUrls = dataMetadataUrls;
		this.serviceMetadataUrls = serviceMetadataUrls;
	}

	public String getName() {
		return name;
	}

	public List<Integer> getManagerIds() {
		return managerIds;
	}

	public String getBbox() {
		return bbox;
	}

	public List<String> getWmsTitles() {
		return wmsTitles;
	}

	public List<String> getResourceidentifiers() {
		return resourceidentifiers;
	}

	public List<String> getDataMetadataUrls() {
		return dataMetadataUrls;
	}

	public List<String> getServiceMetadataUrls() {
		return serviceMetadataUrls;
	}

}
