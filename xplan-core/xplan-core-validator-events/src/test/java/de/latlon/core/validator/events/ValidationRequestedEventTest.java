/*-
 * #%L
 * xplan-core-validator-events - Modul zur Gruppierung der Kernmodule
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
package de.latlon.core.validator.events;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;

class ValidationRequestedEventTest {

	@Test
	void jsonSerializeAndDeserialize() throws Exception {
		List<ValidationOption> extendedOptions = Arrays.asList(new ValidationOption("foo"),
				new ValidationOption("foo2", "arg1"));
		List<ValidationType> validationTypes = Arrays.asList(ValidationType.GEOMETRIC, ValidationType.SYNTACTIC,
				ValidationType.SEMANTIC);
		ValidationSettings settings = new ValidationSettings("validationName", validationTypes, extendedOptions);
		ValidationRequestedEvent event = new ValidationRequestedEvent(settings);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(event);

		ValidationRequestedEvent valueFromJson = mapper.readValue(json, ValidationRequestedEvent.class);

		assertThat(valueFromJson).usingRecursiveComparison().isEqualTo(event);

	}

}
