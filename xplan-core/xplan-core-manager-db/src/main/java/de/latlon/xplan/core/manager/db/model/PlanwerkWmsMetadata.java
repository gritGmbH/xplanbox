/*-
 * #%L
 * xplan-core-manager-db - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplan.core.manager.db.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import java.util.Objects;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Entity
@Table(schema = "xplanmgr", name = "planwerkwmsmetadata")
public class PlanwerkWmsMetadata {

	@Id
	@Column(name = "plan")
	private Integer planId;

	private @Valid String title;

	private @Valid String resourceidentifier;

	private @Valid String datametadataurl;

	private @Valid String servicemetadataurl;

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public PlanwerkWmsMetadata plan(Integer planId) {
		this.planId = planId;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PlanwerkWmsMetadata title(String title) {
		this.title = title;
		return this;
	}

	public String getResourceidentifier() {
		return resourceidentifier;
	}

	public void setResourceidentifier(String resourceidentifier) {
		this.resourceidentifier = resourceidentifier;
	}

	public PlanwerkWmsMetadata resourceidentifier(String resourceidentifier) {
		this.resourceidentifier = resourceidentifier;
		return this;
	}

	public String getDatametadataurl() {
		return datametadataurl;
	}

	public void setDatametadataurl(String datametadataurl) {
		this.datametadataurl = datametadataurl;
	}

	public PlanwerkWmsMetadata datametadataurl(String datametadataurl) {
		this.datametadataurl = datametadataurl;
		return this;
	}

	public String getServicemetadataurl() {
		return servicemetadataurl;
	}

	public void setServicemetadataurl(String servicemetadataurl) {
		this.servicemetadataurl = servicemetadataurl;
	}

	public PlanwerkWmsMetadata servicemetadataurl(String servicemetadataurl) {
		this.servicemetadataurl = servicemetadataurl;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PlanwerkWmsMetadata that = (PlanwerkWmsMetadata) o;
		return Objects.equals(getPlanId(), that.getPlanId()) && Objects.equals(getTitle(), that.getTitle())
				&& Objects.equals(getResourceidentifier(), that.getResourceidentifier())
				&& Objects.equals(getDatametadataurl(), that.getDatametadataurl())
				&& Objects.equals(getServicemetadataurl(), that.getServicemetadataurl());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPlanId(), getTitle(), getResourceidentifier(), getDatametadataurl(),
				getServicemetadataurl());
	}

	@Override
	public String toString() {
		return "PlanwerkWmsMetadata{" + "planId=" + planId + ", title='" + title + '\'' + ", resourceidentifier='"
				+ resourceidentifier + '\'' + ", datametadataurl='" + datametadataurl + '\'' + ", servicemetadataurl='"
				+ servicemetadataurl + '\'' + '}';
	}

}
