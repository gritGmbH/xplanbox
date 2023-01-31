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
package de.latlon.xplan.validator.web.shared;

import java.io.Serializable;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MapPreviewMetadata implements Serializable {

	private String configFileName;

	private XPlanEnvelope bbox;

	private String validationName;

	public MapPreviewMetadata() {
	}

	public MapPreviewMetadata(String configFileName, String validationName, XPlanEnvelope bbox) {
		this.configFileName = configFileName;
		this.validationName = validationName;
		this.bbox = bbox;
	}

	public String getConfigFileName() {
		return configFileName;
	}

	public String getValidationName() {
		return validationName;
	}

	public XPlanEnvelope getBbox() {
		return bbox;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	public void setValidationName(String validationName) {
		this.validationName = validationName;
	}

	public void setBbox(XPlanEnvelope bbox) {
		this.bbox = bbox;
	}

}
