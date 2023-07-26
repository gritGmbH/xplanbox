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
package de.latlon.xplan.validator.reference;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.validator.report.reference.ExternalReferenceReport;
import de.latlon.xplan.validator.report.reference.ExternalReferenceStatus;
import org.deegree.feature.FeatureCollection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.latlon.xplan.validator.report.ReportUtils.SkipCode.INTERNAL_ERRORS;
import static de.latlon.xplan.validator.report.reference.ExternalReferenceStatus.AVAILABLE;
import static de.latlon.xplan.validator.report.reference.ExternalReferenceStatus.MISSING;
import static de.latlon.xplan.validator.report.reference.ExternalReferenceStatus.UNCHECKED;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class ExternalReferenceEvaluator {

	/**
	 * Parses the external refernces
	 * @param features of the archive, never <code>null</code>
	 * @param archive never <code>null</code>
	 * @return the {@link ExternalReferenceReport}, never <code>null</code>
	 */
	public ExternalReferenceReport parseAndAddExternalReferences(XPlanFeatureCollection features,
			XPlanArchive archive) {
		if (features != null)
			return parseAndAddExternalReferences(features.getFeatures(), archive);
		return new ExternalReferenceReport(INTERNAL_ERRORS);
	}

	private ExternalReferenceReport parseAndAddExternalReferences(FeatureCollection fc, XPlanArchive archive) {
		ExternalReferenceScanner scanner = new ExternalReferenceScanner();
		ExternalReferenceInfo externalReferenceInfo = scanner.scan(fc);
		List<ExternalReference> allExternalReferences = externalReferenceInfo.getAllReferences();
		Map<String, ExternalReferenceStatus> references = new HashMap<>();
		for (ExternalReference ref : allExternalReferences) {
			addUrl(archive, references, ref.getReferenzUrl());
			addUrl(archive, references, ref.getGeoRefUrl());
		}
		return new ExternalReferenceReport(references);
	}

	private static void addUrl(XPlanArchive archive, Map<String, ExternalReferenceStatus> references,
			String referenzUrl) {
		if (referenzUrl != null) {
			ExternalReferenceStatus status = detectExternalReferenceStatus(archive, referenzUrl);
			references.put(referenzUrl, status);
		}
	}

	private static ExternalReferenceStatus detectExternalReferenceStatus(XPlanArchive archive, String referenzUrl) {
		if (referenzUrl.startsWith("http"))
			return UNCHECKED;
		if (archive.hasEntry(referenzUrl))
			return AVAILABLE;
		return MISSING;
	}

}