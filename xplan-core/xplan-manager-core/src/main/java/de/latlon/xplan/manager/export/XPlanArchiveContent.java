/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.export;

import org.deegree.feature.FeatureCollection;

import de.latlon.xplan.commons.XPlanVersion;

/**
 * Encapsulates the contents of a xplan archive to export.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanArchiveContent {

	private final XPlanArtefactIterator artefacts;

	private final FeatureCollection restoredFeatureCollection;

	private final XPlanVersion version;

	/**
	 * @param restoredFeatureCollection the imported features, never <code>null</code>
	 * @param artefacts {@link XPlanArtefactIterator} about the artefacts of the plans,
	 * never <code>null</code>
	 * @param version of the plan, never <code>null</code>
	 */
	public XPlanArchiveContent(FeatureCollection restoredFeatureCollection, XPlanArtefactIterator artefacts,
			XPlanVersion version) {
		this.restoredFeatureCollection = restoredFeatureCollection;
		this.artefacts = artefacts;
		this.version = version;
	}

	/**
	 * @return the imported features, never <code>null</code>
	 */
	public FeatureCollection getRestoredFeatureCollection() {
		return restoredFeatureCollection;
	}

	/**
	 * @return {@link XPlanArtefactIterator} about the artefacts of the plans, never
	 * <code>null</code>
	 */
	public XPlanArtefactIterator getArtefacts() {
		return artefacts;
	}

	/**
	 * @return of the plan, never <code>null</code>
	 */
	public XPlanVersion getVersion() {
		return version;
	}

}
