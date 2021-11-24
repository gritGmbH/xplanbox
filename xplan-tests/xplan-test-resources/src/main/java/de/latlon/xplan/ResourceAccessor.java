/*-
 * #%L
 * xplan-test-resources - Ressourcen fuer JUnit- und Integrationstests
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
package de.latlon.xplan;

import java.io.InputStream;

/**
 * Convenient access to resources in this module
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class ResourceAccessor {

	private ResourceAccessor() {
	}

	/**
	 * Retrieves the resource identified by name
	 * @param name the file name of the resource
	 * @return an <link>InputStream</link> for the contents of the file
	 */
	public static InputStream readResourceStream(String name) {
		return ResourceAccessor.class.getResourceAsStream(name);
	}

}
