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
package de.latlon.xplanbox.api.manager.v1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.Objects;

/**
 * Datatype for Link. A Link to a resource related to the resource such as XPlanWerkWMS or
 * the resource itself.
 *
 * @since 4.0
 */
@Schema(description = "Link to a resource related to the resource such as XPlanWerkWMS or the resource itself")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen",
		date = "2020-08-28T13:42:47.160+02:00[Europe/Berlin]")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Link {

	private @Valid URI href;

	public enum RelEnum {

		SELF(String.valueOf("self")), ALTERNATE(String.valueOf("alternate")),
		PLANWERKWMS(String.valueOf("planwerkwms"));

		private String value;

		RelEnum(String v) {
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
		public static RelEnum fromValue(String value) {
			for (RelEnum b : RelEnum.values()) {
				if (b.value.equals(value)) {
					return b;
				}
			}
			throw new IllegalArgumentException("Unexpected value '" + value + "'");
		}

	}

	private @Valid RelEnum rel;

	private @Valid String type;

	private @Valid String hreflang;

	private @Valid String title;

	private @Valid Integer length;

	/**
	 *
	 **/
	public Link href(URI href) {
		this.href = href;
		return this;
	}

	@Schema(example = "https://xplanbox.lat-lon.de/xmanager/api/v1/plan/123", required = true)
	@JsonProperty("href")
	@NotNull
	public URI getHref() {
		return href;
	}

	public void setHref(URI href) {
		this.href = href;
	}

	/**
	 *
	 **/
	public Link rel(RelEnum rel) {
		this.rel = rel;
		return this;
	}

	@Schema(example = "self")
	@JsonProperty("rel")
	public RelEnum getRel() {
		return rel;
	}

	public void setRel(RelEnum rel) {
		this.rel = rel;
	}

	/**
	 *
	 **/
	public Link type(String type) {
		this.type = type;
		return this;
	}

	@Schema(example = "application/json")
	@JsonProperty("type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 *
	 **/
	public Link hreflang(String hreflang) {
		this.hreflang = hreflang;
		return this;
	}

	@Schema(example = "en")
	@JsonProperty("hreflang")
	public String getHreflang() {
		return hreflang;
	}

	public void setHreflang(String hreflang) {
		this.hreflang = hreflang;
	}

	/**
	 *
	 **/
	public Link title(String title) {
		this.title = title;
		return this;
	}

	@Schema(example = "Othmarschen 3, Hamburg")
	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 *
	 **/
	public Link length(Integer length) {
		this.length = length;
		return this;
	}

	@Schema
	@JsonProperty("length")
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Link link = (Link) o;
		return Objects.equals(this.href, link.href) && Objects.equals(this.rel, link.rel)
				&& Objects.equals(this.type, link.type) && Objects.equals(this.hreflang, link.hreflang)
				&& Objects.equals(this.title, link.title) && Objects.equals(this.length, link.length);
	}

	@Override
	public int hashCode() {
		return Objects.hash(href, rel, type, hreflang, title, length);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Link {\n");

		sb.append("    href: ").append(toIndentedString(href)).append("\n");
		sb.append("    rel: ").append(toIndentedString(rel)).append("\n");
		sb.append("    type: ").append(toIndentedString(type)).append("\n");
		sb.append("    hreflang: ").append(toIndentedString(hreflang)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    length: ").append(toIndentedString(length)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the
	 * first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}
