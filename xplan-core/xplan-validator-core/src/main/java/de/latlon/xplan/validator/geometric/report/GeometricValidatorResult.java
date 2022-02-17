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
package de.latlon.xplan.validator.geometric.report;

import java.util.Collections;
import java.util.List;

import org.deegree.cs.coordinatesystems.ICRS;

import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.report.ReportUtils.SkipCode;

/**
 * contains the validator result of the geometric validator
 *
 * @author Florian Bingel
 */

public class GeometricValidatorResult extends ValidatorResult {

	private static final String VALIDATION_TYPE_NAME = "Geometrische Validierung";

	private final List<String> warnings;

	private final List<String> errors;

	private final List<BadGeometry> badGeometries;

	private final ICRS crs;

	public GeometricValidatorResult(SkipCode skipCode) {
		super(skipCode);
		this.warnings = Collections.emptyList();
		this.badGeometries = Collections.emptyList();
		this.crs = null;
		this.errors = Collections.emptyList();
	}

	public GeometricValidatorResult(List<String> warnings, List<String> errors, List<BadGeometry> badGeometries,
			ICRS crs, boolean isValid) {
		super(isValid);
		this.warnings = warnings;
		this.badGeometries = badGeometries;
		this.crs = crs;
		this.errors = errors;
	}

	public String getType() {
		return VALIDATION_TYPE_NAME;
	}

	public List<String> getWarnings() {
		return warnings;
	}

	public List<String> getErrors() {
		return errors;
	}

	public List<BadGeometry> getBadGeometries() {
		return badGeometries;
	}

	public ICRS getCrs() {
		return crs;
	}

	@Override
	public String toString() {
		return "GeometricValidatorResult{" + "warnings=" + warnings + ", errors=" + errors + '}';
	}

}
