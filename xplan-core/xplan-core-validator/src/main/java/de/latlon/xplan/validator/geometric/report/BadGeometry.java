/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.geometric.report;

import org.deegree.geometry.Geometry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * contains a defect geometry and its error-Strings
 *
 * @author Florian Bingel
 */

public class BadGeometry {

	private Geometry originalGeometry;

	private final List<String> errors = new ArrayList<>();

	private final Map<String, Geometry> markerGeometries = new HashMap<>();

	public BadGeometry() {
	}

	public BadGeometry(Geometry originalGeometry, String error) {
		this.originalGeometry = originalGeometry;
		addError(error);
	}

	public void setOriginalGeometry(Geometry originalGeometry) {
		this.originalGeometry = originalGeometry;
	}

	public Geometry getOriginalGeometry() {
		return originalGeometry;
	}

	public List<String> getErrors() {
		return errors;
	}

	public String getErrorsSingleString() {
		StringBuilder allErrors = new StringBuilder();
		for (String error : errors) {
			allErrors.append(error);
			allErrors.append("; ");
		}
		return allErrors.toString();
	}

	public void addError(String err) {
		errors.add(err);
	}

	public Map<String, Geometry> getMarkerGeometries() {
		return markerGeometries;
	}

	public void addMarkerGeometry(String error, Geometry markerGeometry) {
		markerGeometries.put(error, markerGeometry);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BadGeometry that = (BadGeometry) o;
		return Objects.equals(getOriginalGeometry(), that.getOriginalGeometry())
				&& Objects.equals(getErrors(), that.getErrors())
				&& Objects.equals(getMarkerGeometries(), that.getMarkerGeometries());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getOriginalGeometry(), getErrors(), getMarkerGeometries());
	}

}
