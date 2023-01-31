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
package de.latlon.xplan.manager.planwerkwms;

import org.deegree.geometry.Envelope;

/**
 * Encapsulates metadata describing the Planwerk WMS of a plan.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkServiceMetadata {

	private String title;

	private String description;

	private Envelope envelope;

	private String planwerkWmsGetCapabilitiesUrl;

	private String planwerkWmsGetMapUrl;

	PlanwerkServiceMetadata(String title, String description, Envelope envelope, String planwerkWmsGetCapabilitiesUrl,
			String planwerkWmsGetMapUrl) {
		this.title = title;
		this.description = description;
		this.envelope = envelope;
		this.planwerkWmsGetCapabilitiesUrl = planwerkWmsGetCapabilitiesUrl;
		this.planwerkWmsGetMapUrl = planwerkWmsGetMapUrl;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Envelope getEnvelope() {
		return envelope;
	}

	public String getPlanwerkWmsGetCapabilitiesUrl() {
		return planwerkWmsGetCapabilitiesUrl;
	}

	public String getPlanwerkWmsGetMapUrl() {
		return planwerkWmsGetMapUrl;
	}

	@Override
	public String toString() {
		return "PlanwerkServiceMetadata{" + "title='" + title + '\'' + ", description='" + description + '\''
				+ ", envelope=" + envelope + ", planwerkWmsGetCapabilitiesUrl='" + planwerkWmsGetCapabilitiesUrl + '\''
				+ ", planwerkWmsGetMapUrl='" + planwerkWmsGetMapUrl + '\'' + '}';
	}

}
