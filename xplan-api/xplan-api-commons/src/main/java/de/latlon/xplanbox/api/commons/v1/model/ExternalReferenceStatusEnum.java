/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
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
package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public enum ExternalReferenceStatusEnum {

	AVAILABLE("AVAILABLE"),

	MISSING("MISSING"),

	UNCHECKED("UNCHECKED");

	private String value;

	ExternalReferenceStatusEnum(String v) {
		value = v;
	}

	public String value() {
		return value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static ExternalReferenceStatusEnum fromValue(String value) {
		for (ExternalReferenceStatusEnum b : ExternalReferenceStatusEnum.values()) {
			if (b.value.equals(value)) {
				return b;
			}
		}
		throw new IllegalArgumentException("Unexpected value '" + value + "'");
	}

	/**
	 * @param externalReferenceStatus may be <code>null</code>
	 * @return the ExternalReferenceStatus, <code>null</code> if externalReferenceStatus
	 * was null
	 * @throws IllegalArgumentException if the passed externalReferenceStatus could not be
	 * converted to a ExternalReferenceStatus
	 */
	public static ExternalReferenceStatusEnum fromExternalReferenceStatus(
			de.latlon.xplan.validator.report.reference.ExternalReferenceStatus externalReferenceStatus) {
		if (externalReferenceStatus == null)
			return null;
		for (ExternalReferenceStatusEnum b : ExternalReferenceStatusEnum.values()) {
			if (b.value.equals(externalReferenceStatus.name())) {
				return b;
			}
		}
		throw new IllegalArgumentException("Unexpected ExternalReferenceStatus '" + externalReferenceStatus + "'");
	}

}
