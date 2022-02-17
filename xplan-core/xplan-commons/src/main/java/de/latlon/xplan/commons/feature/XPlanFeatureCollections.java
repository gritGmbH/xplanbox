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
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;

import java.util.List;

/**
 * Provides convenient access to the information contained in the main document of an
 * {@link XPlanArchive}.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanFeatureCollections {

	private final XPlanVersion version;

	private final XPlanAde ade;

	private final List<XPlanFeatureCollection> xPlanGmlInstances;

	XPlanFeatureCollections(XPlanVersion version, XPlanType type, XPlanAde ade,
			List<XPlanFeatureCollection> xPlanGmlInstances) {
		this.version = version;
		this.ade = ade;
		this.xPlanGmlInstances = xPlanGmlInstances;
	}

	public XPlanVersion getVersion() {
		return version;
	}

	public XPlanAde getAde() {
		return ade;
	}

	public List<XPlanFeatureCollection> getxPlanGmlInstances() {
		return xPlanGmlInstances;
	}

}