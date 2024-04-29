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
