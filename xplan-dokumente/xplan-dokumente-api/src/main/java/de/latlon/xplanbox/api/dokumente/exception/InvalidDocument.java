/*-
 * #%L
 * xplan-dokumente-api - Software zur Verwaltung von XPlanGML Daten
 * %%
 * Copyright (C) 2008 - 2024 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
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
package de.latlon.xplanbox.api.dokumente.exception;

import de.latlon.xplanbox.api.commons.exception.XPlanApiException;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * Indicates that a document is not available.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class InvalidDocument extends XPlanApiException {

	private static final String EXCEPTION_MESSAGE = "Document with filename %s of Plan with ID %s does not exist!";

	public InvalidDocument(int planId, String fileName) {
		super(String.format(EXCEPTION_MESSAGE, fileName, planId));
	}

	@Override
	public int getStatusCode() {
		return NOT_FOUND.getStatusCode();
	}

}
