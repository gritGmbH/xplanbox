/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ExternalReferenceReport {

	private SkipCode skipCode;

	private List<String> references;

	public ExternalReferenceReport(SkipCode skipCode) {
		this(skipCode, Collections.emptyList());
	}

	public ExternalReferenceReport(List<String> references) {
		this(null, references);
	}

	private ExternalReferenceReport(SkipCode skipCode, List<String> references) {
		this.skipCode = skipCode;
		this.references = references;
	}

	public SkipCode getSkipCode() {
		return skipCode;
	}

	public List<String> getReferences() {
		return references;
	}

}
