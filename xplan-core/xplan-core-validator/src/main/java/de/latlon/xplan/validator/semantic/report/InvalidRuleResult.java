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
package de.latlon.xplan.validator.semantic.report;

import static de.latlon.xplan.validator.semantic.report.ValidationResultType.ERROR;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InvalidRuleResult {

	private static final String UNKNOWN_GML_ID = "unbekannt";

	private final String gmlId;

	private final ValidationResultType resultType;

	private final String message;

	public InvalidRuleResult(String message) {
		this(UNKNOWN_GML_ID, message);
	}

	public InvalidRuleResult(String gmlId, String message) {
		this(gmlId, ERROR, message);
	}

	public InvalidRuleResult(String gmlId, ValidationResultType resultType, String message) {
		this.gmlId = gmlId;
		this.resultType = resultType;
		this.message = message;
	}

	public String getGmlId() {
		return gmlId;
	}

	public ValidationResultType getResultType() {
		return resultType;
	}

	public String getMessage() {
		return message;
	}

}
