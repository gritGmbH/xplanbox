/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
/*----------------------------------------------------------------------------
 This file is part of deegree, http://deegree.org/
 Copyright (C) 2001-2014 by:
 - Department of Geography, University of Bonn -
 and
 - lat/lon GmbH -

 This library is free software; you can redistribute it and/or modify it under
 the terms of the GNU Lesser General Public License as published by the Free
 Software Foundation; either version 2.1 of the License, or (at your option)
 any later version.
 This library is distributed in the hope that it will be useful, but WITHOUT
 ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 details.
 You should have received a copy of the GNU Lesser General Public License
 along with this library; if not, write to the Free Software Foundation, Inc.,
 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

 Contact information:

 lat/lon GmbH
 Aennchenstr. 19, 53177 Bonn
 Germany
 http://lat-lon.de/

 Department of Geography, University of Bonn
 Prof. Dr. Klaus Greve
 Postfach 1147, 53001 Bonn
 Germany
 http://www.geographie.uni-bonn.de/deegree/

 e-mail: info@deegree.org
----------------------------------------------------------------------------*/
package de.latlon.xplan.commons.archive;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	}

}
