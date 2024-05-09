/*-
 * #%L
 * xplan-core-api - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplanbox.api.commons.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ExternalReferenceResult {

	private @Valid String name;

	private @Valid ExternalReferenceStatusEnum status;

	public void setName(String name) {
		this.name = name;
	}

	public ExternalReferenceResult name(String name) {
		this.name = name;
		return this;
	}

	@Schema(example = "stelling.png")
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	public void setStatus(ExternalReferenceStatusEnum status) {
		this.status = status;
	}

	public ExternalReferenceResult status(ExternalReferenceStatusEnum externalReferenceStatusEnum) {
		this.status = externalReferenceStatusEnum;
		return this;
	}

	@Schema(example = "AVAILABLE")
	@JsonProperty("status")
	public ExternalReferenceStatusEnum getStatus() {
		return status;
	}

}
