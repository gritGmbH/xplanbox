/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
