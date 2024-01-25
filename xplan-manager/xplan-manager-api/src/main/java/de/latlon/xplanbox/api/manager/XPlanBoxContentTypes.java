/*-
 * #%L
 * xplan-api-manager - xplan-api-manager
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
package de.latlon.xplanbox.api.manager;

import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 5.0
 */
public class XPlanBoxContentTypes {

	private XPlanBoxContentTypes() {
	}

	public static final String XPLANBOX_NO_VERSION_JSON = "application/vnd.xplanbox.api+json";

	public static final MediaType XPLANBOX_NO_VERSION_JSON_TYPE = new MediaType("application", "vnd.xplanbox.api+json");

	public static final String XPLANBOX_V1_JSON = "application/vnd.xplanbox.api.v1+json";

	public static final MediaType XPLANBOX_V1_JSON_TYPE = new MediaType("application", "vnd.xplanbox.api.v1+json");

	public static final String XPLANBOX_V2_JSON = "application/vnd.xplanbox.api.v2+json";

	public static final MediaType XPLANBOX_V2_JSON_TYPE = new MediaType("application", "vnd.xplanbox.api.v2+json");

}
