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
package de.latlon.xplan.commons.archive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.commons.util.MimeTypeDetector.getArtefactMimeType;

/**
 * Encapsulates an archive from a directory.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanPartArchive implements XPlanArchiveContentAccess {

	private static final Logger LOG = LoggerFactory.getLogger(XPlanPartArchive.class);

	private final Map<String, FileArchiveEntry> nameToFileEntry = new HashMap<String, FileArchiveEntry>();

	/**
	 * @param archiveFiles the archive files, never <code>null</code>
	 */
	public XPlanPartArchive(List<File> archiveFiles) {
		for (File file : archiveFiles) {
			nameToFileEntry.put(file.getName(), new FileArchiveEntry(file));
		}
	}

	@Override
	public List<? extends ArchiveEntry> getZipFileEntries() {
		List<ArchiveEntry> archiveEntiries = new ArrayList<ArchiveEntry>();
		for (FileArchiveEntry fileName : nameToFileEntry.values()) {
			archiveEntiries.add(fileName);
		}
		return archiveEntiries;
	}

	@Override
	public InputStream retrieveInputStreamFor(String name) {
		FileArchiveEntry entry = getEntry(name);
		if (entry != null)
			try {
				return new FileInputStream(entry.file);
			}
			catch (FileNotFoundException e) {
				LOG.warn("Could not find file with name '{}'", name);
			}
		return null;
	}

	@Override
	public FileArchiveEntry getEntry(String name) {
		return nameToFileEntry.get(name);
	}

	private class FileArchiveEntry implements ArchiveEntry {

		private File file;

		private FileArchiveEntry(File file) {
			this.file = file;
		}

		@Override
		public String getName() {
			return file.getName();
		}

		@Override
		public long getContentLength() {
			try {
				return Files.size(file.toPath());
			}
			catch (IOException e) {
				LOG.warn("Could not detect file size of file {}", file);
				return -1;
			}
		}

		@Override
		public String getContentType() {
			return getArtefactMimeType(getName());
		}

	}

}
