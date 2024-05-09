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
package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.ZipEntryWithContent;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import org.deegree.feature.FeatureCollection;
import org.deegree.geometry.Envelope;

import java.util.Date;
import java.util.List;

/**
 * Provides convenient access to the information contained in the main document of an
 * {@link XPlanArchive}.
 *
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanSingleInstanceFeatureCollection extends XPlanFeatureCollection {

	XPlanSingleInstanceFeatureCollection(FeatureCollection fc, XPlanType type, String name, String nummer, String gkz,
			Date planReleaseDate, ExternalReferenceInfo externalRefInfo, Envelope bboxIn4326, XPlanVersion version) {
		super(fc, type, name, nummer, gkz, planReleaseDate, externalRefInfo, bboxIn4326, version);
	}

	@Override
	public List<ZipEntryWithContent> getArchiveEntries(XPlanArchive xPlanArchive) {
		if (xPlanArchive == null)
			return null;
		return xPlanArchive.getZipFileEntries();
	}

}
