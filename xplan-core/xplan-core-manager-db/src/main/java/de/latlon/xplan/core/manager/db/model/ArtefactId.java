/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.core.manager.db.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
@Embeddable
public class ArtefactId implements Serializable {

	@OneToOne(optional = false)
	@JoinColumn(referencedColumnName = "id", name = "plan", nullable = false,
			foreignKey = @ForeignKey(name = "artefacts_plan_fkey"))
	private @Valid Plan plan;

	@Column(nullable = false)
	private @Valid String filename;

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ArtefactId filename(String filename) {
		this.filename = filename;
		return this;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public ArtefactId plan(Plan plan) {
		this.plan = plan;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ArtefactId that = (ArtefactId) o;
		return Objects.equals(getPlan(), that.getPlan()) && Objects.equals(getFilename(), that.getFilename());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPlan(), getFilename());
	}

	@Override
	public String toString() {
		return "ArtefactId{" + "plan=" + plan + ", filename='" + filename + '\'' + '}';
	}

}
