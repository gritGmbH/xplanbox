/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.cs.coordinatesystems.ICRS;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ArchiveMetadata {

	private final XPlanVersion version;

	private final XPlanType type;

	private final XPlanAde ade;

	private final ICRS crs;

	private final List<String> districts;

	private final boolean hasMultipleXPlanElements;

	public ArchiveMetadata(XPlanVersion version, XPlanType type, XPlanAde ade, ICRS crs, List<String> districts,
			boolean hasMultipleXPlanElements) {
		this.version = version;
		this.type = type;
		this.ade = ade;
		this.crs = crs;
		this.districts = districts;
		this.hasMultipleXPlanElements = hasMultipleXPlanElements;
	}

	public ICRS getCrs() {
		return crs;
	}

	public XPlanType getType() {
		return type;
	}

	public XPlanAde getAde() {
		return ade;
	}

	public List<String> getDistricts() {
		return districts;
	}

	public XPlanVersion getVersion() {
		return version;
	}

	public boolean hasMultipleXPlanElements() {
		return hasMultipleXPlanElements;
	}

}
