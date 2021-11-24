package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SemanticInvalidRuleResult {

	private @Valid String message;

	private @Valid List<String> invalidGmlIds = new ArrayList<String>();

	public SemanticInvalidRuleResult message(String message) {
		this.message = message;
		return this;
	}

	@Schema
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SemanticInvalidRuleResult invalidGmlIds(List<String> invalidGmlIds) {
		this.invalidGmlIds = invalidGmlIds;
		return this;
	}

	@ArraySchema(schema = @Schema(description = "GML-ID", example = "GML_b4e47d29-d21c-42ab-85b7-b12ea57e89f2"))
	@JsonProperty("invalidGmlIds")
	public List<String> getInvalidGmlIds() {
		return invalidGmlIds;
	}

	public void setInvalidGmlIds(List<String> invalidFeatures) {
		this.invalidGmlIds = invalidFeatures;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SemanticInvalidRuleResult that = (SemanticInvalidRuleResult) o;
		return Objects.equals(message, that.message) && Objects.equals(invalidGmlIds, that.invalidGmlIds);
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, invalidGmlIds);
	}

	@Override
	public String toString() {
		return "SemanticInvalidRuleResult{" + "message='" + message + '\'' + ", invalidGmlIds=" + invalidGmlIds + '}';
	}

}
