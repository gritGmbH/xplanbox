/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.report.reference;

import de.latlon.xplan.validator.report.ReportUtils.SkipCode;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ExternalReferenceReport {

	private SkipCode skipCode;

	private Map<String, ExternalReferenceStatus> referencesAndStatus;

	public ExternalReferenceReport(SkipCode skipCode) {
		this(skipCode, Collections.emptyMap());
	}

	public ExternalReferenceReport(Map<String, ExternalReferenceStatus> references) {
		this(null, references);
	}

	private ExternalReferenceReport(SkipCode skipCode, Map<String, ExternalReferenceStatus> references) {
		this.skipCode = skipCode;
		this.referencesAndStatus = references;
	}

	public SkipCode getSkipCode() {
		return skipCode;
	}

	public List<String> getReferences() {
		return referencesAndStatus.keySet().stream().collect(Collectors.toList());
	}

	public Map<String, ExternalReferenceStatus> getReferencesAndStatus() {
		return referencesAndStatus;
	}

}
