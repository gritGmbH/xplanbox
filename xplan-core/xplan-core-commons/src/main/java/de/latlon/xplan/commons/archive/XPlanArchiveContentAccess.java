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

import java.io.InputStream;
import java.util.List;

/**
 * Provides access to the content of the XPlanArchive.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public interface XPlanArchiveContentAccess {

	/**
	 * @return a list of {@link ArchiveEntry}s encapsulated in this archive
	 */
	List<? extends ArchiveEntry> getZipFileEntries();

	/**
	 * Retrieve the content of the entry specified with its name as {@link InputStream}.
	 * @param name the name of the entry, never <code>null</code>
	 * @return an {@link InputStream} for the given zip entry
	 * @throws IllegalArgumentException if name is <code>null</code> or no entry with this
	 * name exists
	 */
	InputStream retrieveInputStreamFor(String name);

	/**
	 * Retrieve the content of the entry as {@link ArchiveEntry}.
	 * @param name the name of the entry, never <code>null</code>
	 * @return the {@link ArchiveEntry} with the specified name, <code>null</code> if no
	 * entry exists.
	 * @throws IllegalArgumentException if name is <code>null</code>
	 */
	ArchiveEntry getEntry(String name);

}
