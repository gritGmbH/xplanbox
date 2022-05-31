/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.export;

import java.io.InputStream;

/**
 * Encapsulates a single artefact of a xplan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanArtefact {

	private final String fileName;

	private final InputStream content;

	/**
	 * @param fileName the name of the artefact, never <code>null</code>
	 * @param content the content of the artefact, never <code>null</code>
	 */
	public XPlanArtefact(String fileName, InputStream content) {
		this.fileName = fileName;
		this.content = content;
	}

	/**
	 * @return the name of the artefact, never <code>null</code>
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @return the content of the artefact, never <code>null</code>
	 */
	public InputStream getContent() {
		return content;
	}

}
