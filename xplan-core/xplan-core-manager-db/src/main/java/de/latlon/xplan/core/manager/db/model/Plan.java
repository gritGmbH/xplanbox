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

import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Entity
@Table(schema = "xplanmgr", name = "plans")
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plans_id_seq")
	@SequenceGenerator(name = "plans_id_seq", schema = "xplanmgr", sequenceName = "plans_id_seq", allocationSize = 1)
	private @Valid Integer id;

	@NotNull
	@Column(name = "import_date")
	private @Valid Date importDate;

	@NotNull
	@Column(name = "xp_version")
	@Enumerated(EnumType.STRING)
	private @Valid XPlanVersion version;

	@NotNull
	@Column(name = "xp_type")
	@Enumerated(EnumType.STRING)
	private @Valid XPlanType type;

	private @Valid String name;

	private @Valid String nummer;

	private @Valid String internalid;

	private @Valid String gkz;

	@NotNull
	@Column(name = "has_raster")
	private @Valid Boolean hasRaster;

	private @Valid String rechtsstand;

	@Column(name = "release_date")
	private @Valid Date releaseDate;

	@Column(name = "sonst_plan_art")
	private @Valid String sonstPlanArt;

	private @Valid String planstatus;

	private @Valid String district;

	private @Valid Date wmssortdate;

	private @Valid Date gueltigkeitbeginn;

	private @Valid Date gueltigkeitende;

	private @Valid Boolean inspirepublished = false;

	private @Valid Geometry bbox;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(schema = "xplanmgr", name = "bereiche", joinColumns = @JoinColumn(name = "plan"),
			foreignKey = @ForeignKey(name = "bereiche_plan_fkey"))
	private @Valid Set<Bereich> bereiche;

	@OneToMany(mappedBy = "id.plan", cascade = ALL, orphanRemoval = true)
	private @Valid Set<Artefact> artefacts;

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(schema = "xplanmgr", name = "features", joinColumns = @JoinColumn(name = "plan"),
			foreignKey = @ForeignKey(name = "features_plan_fkey"))
	private @Valid Set<Feature> features;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	public Plan importDate(Date importDate) {
		this.importDate = importDate;
		return this;
	}

	public XPlanVersion getVersion() {
		return version;
	}

	public void setVersion(XPlanVersion version) {
		this.version = version;
	}

	public Plan version(XPlanVersion version) {
		this.version = version;
		return this;
	}

	public XPlanType getType() {
		return type;
	}

	public void setType(XPlanType type) {
		this.type = type;
	}

	public Plan type(XPlanType type) {
		this.type = type;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Plan name(String name) {
		this.name = name;
		return this;
	}

	public String getNummer() {
		return nummer;
	}

	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

	public Plan nummer(String nummer) {
		this.nummer = nummer;
		return this;
	}

	public String getInternalid() {
		return internalid;
	}

	public void setInternalid(String internalid) {
		this.internalid = internalid;
	}

	public Plan internalid(String internalid) {
		this.internalid = internalid;
		return this;
	}

	public String getGkz() {
		return gkz;
	}

	public void setGkz(String gkz) {
		this.gkz = gkz;
	}

	public Plan gkz(String gkz) {
		this.gkz = gkz;
		return this;
	}

	public Boolean getHasRaster() {
		return hasRaster;
	}

	public void setHasRaster(Boolean hasRaster) {
		this.hasRaster = hasRaster;
	}

	public Plan hasRaster(Boolean hasRaster) {
		this.hasRaster = hasRaster;
		return this;
	}

	public String getRechtsstand() {
		return rechtsstand;
	}

	public void setRechtsstand(String rechtsstand) {
		this.rechtsstand = rechtsstand;
	}

	public Plan rechtsstand(String rechtsstand) {
		this.rechtsstand = rechtsstand;
		return this;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Plan releaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
		return this;
	}

	public String getSonstPlanArt() {
		return sonstPlanArt;
	}

	public void setSonstPlanArt(String sonstPlanArt) {
		this.sonstPlanArt = sonstPlanArt;
	}

	public Plan sonstPlanArt(String sonstPlanArt) {
		this.sonstPlanArt = sonstPlanArt;
		return this;
	}

	public String getPlanstatus() {
		return planstatus;
	}

	public void setPlanstatus(String planstatus) {
		this.planstatus = planstatus;
	}

	public Plan planstatus(String planstatus) {
		this.planstatus = planstatus;
		return this;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Plan district(String district) {
		this.district = district;
		return this;
	}

	public Date getWmssortdate() {
		return wmssortdate;
	}

	public void setWmssortdate(Date wmssortdate) {
		this.wmssortdate = wmssortdate;
	}

	public Plan wmssortdate(Date wmssortdate) {
		this.wmssortdate = wmssortdate;
		return this;
	}

	/**
	 * @deprecated will be removed in a future version.
	 */
	@Deprecated
	public Date getGueltigkeitbeginn() {
		return gueltigkeitbeginn;
	}

	/**
	 * @deprecated will be removed in a future version.
	 */
	@Deprecated
	public void setGueltigkeitbeginn(Date gueltigkeitbeginn) {
		this.gueltigkeitbeginn = gueltigkeitbeginn;
	}

	/**
	 * @deprecated will be removed in a future version.
	 */
	@Deprecated
	public Plan gueltigkeitbeginn(Date gueltigkeitbeginn) {
		this.gueltigkeitbeginn = gueltigkeitbeginn;
		return this;
	}

	/**
	 * @deprecated will be removed in a future version.
	 */
	@Deprecated
	public Date getGueltigkeitende() {
		return gueltigkeitende;
	}

	/**
	 * @deprecated will be removed in a future version.
	 */
	@Deprecated
	public void setGueltigkeitende(Date gueltigkeitende) {
		this.gueltigkeitende = gueltigkeitende;
	}

	/**
	 * @deprecated will be removed in a future version.
	 */
	@Deprecated
	public Plan gueltigkeitende(Date gueltigkeitende) {
		this.gueltigkeitende = gueltigkeitende;
		return this;
	}

	public Boolean getInspirepublished() {
		return inspirepublished;
	}

	public void setInspirepublished(Boolean inspirepublished) {
		this.inspirepublished = inspirepublished;
	}

	public Plan inspirepublished(Boolean inspirepublished) {
		this.inspirepublished = inspirepublished;
		return this;
	}

	public Geometry getBbox() {
		return bbox;
	}

	public void setBbox(Geometry bbox) {
		this.bbox = bbox;
	}

	public Plan bbox(Geometry bbox) {
		this.bbox = bbox;
		return this;
	}

	public Set<Bereich> getBereiche() {
		return bereiche;
	}

	public void setBereiche(Set<Bereich> bereiche) {
		this.bereiche = bereiche;
	}

	public Plan bereiche(Set<Bereich> bereiche) {
		this.bereiche = bereiche;
		return this;
	}

	public Set<Artefact> getArtefacts() {
		return artefacts;
	}

	public void setArtefacts(Set<Artefact> artefacts) {
		this.artefacts = artefacts;
	}

	public Plan artefacts(Set<Artefact> artefacts) {
		this.artefacts = artefacts;
		return this;
	}

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	public Plan features(Set<Feature> features) {
		this.features = features;
		return this;
	}

	@Override
	public String toString() {
		return "Plan{" + "id=" + id + ", importDate=" + importDate + ", version=" + version + ", type=" + type
				+ ", name='" + name + '\'' + ", nummer='" + nummer + '\'' + ", internalid='" + internalid + '\''
				+ ", gkz='" + gkz + '\'' + ", hasRaster=" + hasRaster + ", rechtsstand='" + rechtsstand + '\''
				+ ", releaseDate=" + releaseDate + ", sonstPlanArt='" + sonstPlanArt + '\'' + ", planstatus='"
				+ planstatus + '\'' + ", district='" + district + '\'' + ", wmssortdate=" + wmssortdate
				+ ", gueltigkeitbeginn=" + gueltigkeitbeginn + ", gueltigkeitende=" + gueltigkeitende
				+ ", inspirepublished=" + inspirepublished + ", bereiche=" + bereiche + ", artefacts=" + artefacts
				+ ", features=" + features + '}';
	}

}
