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

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
@Entity
@Table(schema = "xplanmgr", name = "artefacts",
		uniqueConstraints = { @UniqueConstraint(columnNames = { "plan", "filename" }) })
public class Artefact {

	@EmbeddedId
	private @Valid ArtefactId id;

	@NotNull
	private @Valid byte[] data;

	@NotNull
	private @Valid Integer num;

	@NotNull
	private @Valid String mimetype;

	@NotNull
	private @Valid Long length;

	@Enumerated(EnumType.STRING)
	private @Valid ArtefactType artefacttype;

	public ArtefactId getId() {
		return id;
	}

	public void setId(ArtefactId id) {
		this.id = id;
	}

	public Artefact id(ArtefactId id) {
		this.id = id;
		return this;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Artefact data(byte[] data) {
		this.data = data;
		return this;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Artefact num(Integer num) {
		this.num = num;
		return this;
	}

	public String getMimetype() {
		return mimetype;
	}

	public void setMimetype(String mimetype) {
		this.mimetype = mimetype;
	}

	public Artefact mimetype(String mimetype) {
		this.mimetype = mimetype;
		return this;
	}

	public Long getLength() {
		return length;
	}

	public Artefact length(Long length) {
		this.length = length;
		return this;
	}

	public void setLength(Long length) {
		this.length = length;
	}

	public ArtefactType getArtefacttype() {
		return artefacttype;
	}

	public void setArtefacttype(ArtefactType artefacttype) {
		this.artefacttype = artefacttype;
	}

	public Artefact artefacttype(ArtefactType artefacttype) {
		this.artefacttype = artefacttype;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Artefact artefact = (Artefact) o;
		return Objects.equals(getId(), artefact.getId()) && Arrays.equals(getData(), artefact.getData())
				&& Objects.equals(getNum(), artefact.getNum()) && Objects.equals(getMimetype(), artefact.getMimetype())
				&& Objects.equals(getLength(), artefact.getLength()) && getArtefacttype() == artefact.getArtefacttype();
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(getId(), getNum(), getMimetype(), getLength(), getArtefacttype());
		result = 31 * result + Arrays.hashCode(getData());
		return result;
	}

	@Override
	public String toString() {
		return "Artefact{" + "id=" + id + ", data=" + Arrays.toString(data) + ", num=" + num + ", mimetype='" + mimetype
				+ ", length='" + length + '\'' + ", artefacttype=" + artefacttype + '}';
	}

}
