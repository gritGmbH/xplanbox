/*-
 * #%L
 * xplan-manager-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.manager.validation;

import org.glassfish.jersey.media.multipart.FormDataBodyPart;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Payload;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Validates the model classes part of a multipart request.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, PARAMETER })
@Constraint(validatedBy = ModelValidator.Validator.class)
public @interface ModelValidator {

	String message() default "Model contains invalid content: ";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?> value();

	class Validator implements ConstraintValidator<ModelValidator, FormDataBodyPart> {

		private Class<?> classToValidate;

		private String message;

		@Override
		public void initialize(final ModelValidator modelValidator) {
			this.classToValidate = modelValidator.value();
			this.message = modelValidator.message();
		}

		@Override
		public boolean isValid(FormDataBodyPart formDataBodyPart, ConstraintValidatorContext context) {
			Object model = formDataBodyPart.getValueAs(classToValidate);
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			javax.validation.Validator validator = factory.getValidator();
			Set<ConstraintViolation<Object>> validate = validator.validate(model);
			if (!validate.isEmpty()) {
				context.disableDefaultConstraintViolation();
				String details = validate.stream()
					.map(val -> val.getPropertyPath() + " " + val.getMessage())
					.collect(Collectors.joining(","));
				context.buildConstraintViolationWithTemplate(message + details).addConstraintViolation();
				return false;
			}
			return true;
		}

	}

}
