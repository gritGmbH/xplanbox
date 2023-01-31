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
package de.latlon.xplan.manager.web.shared.edit;

/**
 * Used to discriminate XP_MimeTypes.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @version $Revision: $, $Date: $
 */
public enum MimeTypes {

	APPLICATION_MSEXCEL("application/msexcel"), APPLICATION_MSWORD("application/msword"),
	APPLICATION_ODT("application/odt"), APPLICATION_PDF("application/pdf"),
	APPLICATION_VND_OGC_GML("application/vnd.ogc.gml"), APPLICATION_VND_OGC_SLD_XML("application/vnd.ogc.sld+xml"),
	APPLICATION_VND_OGC_WMS_XML("application/vnd.ogc.wms_xml"), APPLICATION_XML("application/xml"),
	APPLICATION_ZIP("application/zip"), IMAGE_ECW("image/ecw"), IMAGE_JPG("image/jpg"), IMAGE_PNG("image/png"),
	IMAGE_SVG_XML("image/svg+xml"), IMAGE_TIFF("image/tiff"), TEXT_HTML("text/html"), TEXT_PLAIN("text/plain");

	private String code;

	MimeTypes(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static MimeTypes getByCode(String code) {
		if (code == null)
			return null;
		for (MimeTypes value : values()) {
			if (value.code.equals(code.trim()))
				return value;
		}
		return null;
	}

}
