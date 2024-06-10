/*-
 * #%L
 * xplan-core-gwt - Modul zur Gruppierung von GWT Komponenten
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
package de.latlon.xplanbox.core.gwt.commons.server.service;

import de.latlon.xplanbox.core.gwt.commons.shared.InvalidParameterException;
import de.latlon.xplan.validator.web.shared.ValidationSettings;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class ValidationUtils {

	private ValidationUtils() {
	}

	/**
	 * @param validationSettings to validate, never <code>null</code>
	 * @throws InvalidParameterException if the passed validationSettings are invalid
	 */
	public static void validate(ValidationSettings validationSettings) throws InvalidParameterException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<ValidationSettings>> validate = validator.validate(validationSettings);
		if (!validate.isEmpty()) {
			String messages = validate.stream()
				.map(violation -> violation.getMessage())
				.collect(Collectors.joining(", "));
			throw new InvalidParameterException(messages);
		}
	}

}
