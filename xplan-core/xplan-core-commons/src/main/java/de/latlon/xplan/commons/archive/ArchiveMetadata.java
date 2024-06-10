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

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.cs.coordinatesystems.ICRS;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ArchiveMetadata {

	private final XPlanVersion version;

	private final XPlanType type;

	private final ICRS crs;

	private boolean hasVerbundenerPlanBereich;

	private final boolean hasMultipleXPlanElements;

	public ArchiveMetadata(XPlanVersion version, XPlanType type, ICRS crs, boolean hasVerbundenerPlanBereich,
			boolean hasMultipleXPlanElements) {
		this.version = version;
		this.type = type;
		this.crs = crs;
		this.hasVerbundenerPlanBereich = hasVerbundenerPlanBereich;
		this.hasMultipleXPlanElements = hasMultipleXPlanElements;
	}

	public ICRS getCrs() {
		return crs;
	}

	public XPlanType getType() {
		return type;
	}

	public XPlanVersion getVersion() {
		return version;
	}

	public boolean hasVerbundenerPlanBereich() {
		return hasVerbundenerPlanBereich;
	}

	public boolean hasMultipleXPlanElements() {
		return hasMultipleXPlanElements;
	}

}
