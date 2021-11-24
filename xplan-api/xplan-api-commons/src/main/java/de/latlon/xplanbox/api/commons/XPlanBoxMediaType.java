/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplanbox.api.commons;

import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanBoxMediaType {

	public static final String APPLICATION_ZIP = "application/zip";

	public static final MediaType APPLICATION_ZIP_TYPE = new MediaType("application", "zip");

	public static final String APPLICATION_X_ZIP = "application/x-zip";

	public static final MediaType APPLICATION_X_ZIP_TYPE = new MediaType("application", "x-zip");

	public static final String APPLICATION_X_ZIP_COMPRESSED = "application/x-zip-compressed";

	public static final MediaType APPLICATION_X_ZIP_COMPRESSED_TYPE = new MediaType("application", "x-zip-compressed");

	public static final String APPLICATION_PDF = "application/pdf";

	public static final MediaType APPLICATION_PDF_TYPE = new MediaType("application", "pdf");

}
