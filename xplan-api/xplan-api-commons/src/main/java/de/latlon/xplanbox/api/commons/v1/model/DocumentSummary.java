package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import java.util.Objects;

/**
 * Contains a summary of a validated plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.1
 */
public class DocumentSummary {

	private @Valid String name;

	private @Valid String type;

	public DocumentSummary name(String name) {
		this.name = name;
		return this;
	}

	@Schema(example = "Othmarschen3")
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DocumentSummary type(String type) {
		this.type = type;
		return this;
	}

	@Schema(example = "BP_Plan")
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof DocumentSummary))
			return false;
		DocumentSummary that = (DocumentSummary) o;
		return Objects.equals(name, that.name) && Objects.equals(type, that.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type);
	}

	@Override
	public String toString() {
		return "BasicPlanInfo{" + "name='" + name + '\'' + ", type='" + type + '\'' + '}';
	}

}