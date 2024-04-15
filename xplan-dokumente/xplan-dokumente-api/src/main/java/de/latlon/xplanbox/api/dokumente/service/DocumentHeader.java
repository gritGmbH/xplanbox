/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumenteAPI
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
package de.latlon.xplanbox.api.dokumente.service;

/**
 * Encapsulated the header information of a document.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class DocumentHeader {

	private final long fileSize;

	private final String mediaType;

	public DocumentHeader(long fileSize, String mediaType) {
		this.fileSize = fileSize;
		this.mediaType = mediaType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getMediaType() {
		return mediaType;
	}

}
