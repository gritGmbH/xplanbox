/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.archive;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MainZipEntry implements ZipEntryWithContent {

	private final String name;

	private final byte[] content;

	public MainZipEntry(byte[] content, String name) {
		this.content = content;
		this.name = name;
	}

	@Override
	public byte[] getContent() {
		return content;
	}

	@Override
	public InputStream retrieveContentAsStream() {
		return new ByteArrayInputStream(content);
	}

	@Override
	public String getName() {
		return name;
	}

}
