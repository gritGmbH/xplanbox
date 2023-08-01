/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumentenAPI
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

import javax.ws.rs.core.StreamingOutput;

/**
 * Encapsulated the header information of a document and the document itself.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class DocumentHeaderWithStream extends DocumentHeader {

	private StreamingOutput streamingOutput;

	public DocumentHeaderWithStream(long fileSize, String mediaType, StreamingOutput streamingOutput) {
		super(fileSize, mediaType);
		this.streamingOutput = streamingOutput;
	}

	public StreamingOutput getStreamingOutput() {
		return streamingOutput;
	}

}
