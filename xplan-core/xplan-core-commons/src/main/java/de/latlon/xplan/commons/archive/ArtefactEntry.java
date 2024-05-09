/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.archive;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;

import static de.latlon.xplan.commons.util.MimeTypeDetector.getArtefactMimeType;

/**
 * ZipEntry implementation where each entry allows access to the content.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ArtefactEntry extends ArchiveZipEntry implements ZipEntryWithContent {

	private final byte[] content;

	/**
	 * Create entry with content.
	 * @param entry the encapsulated entry, never <code>null</code>
	 * @param content of the entry, never <code>null</code>
	 */
	public ArtefactEntry(ZipEntry entry, byte[] content) {
		super(entry);
		this.content = content;
	}

	@Override
	/**
	 * @return of the entry, never <code>null</code>
	 */
	public byte[] getContent() {
		return content;
	}

	@Override
	/**
	 * @return of the entry, never <code>null</code>
	 */
	public InputStream retrieveContentAsStream() {
		return new ByteArrayInputStream(content);
	}

	@Override
	public boolean isXPlanGml() {
		return false;
	}

	@Override
	public long getContentLength() {
		return content.length;
	}

	@Override
	public String getContentType() {
		return getArtefactMimeType(getName());
	}

}
