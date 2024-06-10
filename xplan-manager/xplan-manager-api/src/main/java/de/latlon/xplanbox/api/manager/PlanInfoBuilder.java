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
package de.latlon.xplanbox.api.manager;

import de.latlon.xplan.manager.web.shared.PlanStatus;
import de.latlon.xplan.manager.web.shared.XPlan;
import de.latlon.xplan.validator.web.shared.XPlanEnvelope;
import de.latlon.xplanbox.api.commons.v1.model.PlanInfoBbox;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;
import de.latlon.xplanbox.api.manager.config.JerseyConfig;
import de.latlon.xplanbox.api.manager.config.ManagerApiConfiguration;
import de.latlon.xplanbox.api.manager.v1.model.Bereich;
import de.latlon.xplanbox.api.manager.v1.model.Link;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfo;
import de.latlon.xplanbox.api.manager.v1.model.PlanInfoXplanModelData;
import de.latlon.xplanbox.api.manager.v1.model.PlanStatusEnum;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplanbox.api.manager.v1.model.Link.RelEnum.ALTERNATE;
import static de.latlon.xplanbox.api.manager.v1.model.Link.RelEnum.PLANWERKWMS;
import static de.latlon.xplanbox.api.manager.v1.model.Link.RelEnum.SELF;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanInfoBuilder {

	private static final Logger LOG = getLogger(PlanInfoBuilder.class);

	private final XPlan xPlan;

	private final List<Bereich> bereiche;

	private final ManagerApiConfiguration managerApiConfiguration;

	private final List<String> alternateMediaTypes = new ArrayList<>();

	private String selfMediaType;

	public PlanInfoBuilder(XPlan xPlan, List<Bereich> bereiche, ManagerApiConfiguration managerApiConfiguration) {
		this.xPlan = xPlan;
		this.bereiche = bereiche;
		this.managerApiConfiguration = managerApiConfiguration;
	}

	public PlanInfoBuilder selfMediaType(String selfMediaType) {
		this.selfMediaType = selfMediaType;
		return this;
	}

	public PlanInfoBuilder alternateMediaType(List<String> alternateMediaTypes) {
		if (alternateMediaTypes != null)
			this.alternateMediaTypes.addAll(alternateMediaTypes);
		return this;
	}

	public PlanInfo build() {
		return new PlanInfo().id(Integer.parseInt(xPlan.getId()))
			.importDate(xPlan.getImportDate())
			.inspirePublished(xPlan.isInspirePublished())
			.raster(xPlan.isRaster())
			.version(version())
			.planStatus(planStatus())
			.bbox(bbox())
			.links(links())
			.type(xPlan.getType())
			.xplanModelData(xPlanModelData());
	}

	private PlanInfoXplanModelData xPlanModelData() {
		return new PlanInfoXplanModelData().name(xPlan.getName())
			.nummer(xPlan.getNumber())
			.internalId(xPlan.getInternalId())
			.inkrafttretensDatum(xPlan.getReleaseDate())
			.rechtsstand(xPlan.getLegislationStatus())
			.ags(xPlan.getGkz())
			.bereiche(bereiche);
	}

	private PlanStatusEnum planStatus() {
		if (xPlan.getPlanStatus() != null) {
			PlanStatus planStatus = xPlan.getPlanStatus();
			return PlanStatusEnum.valueOf(planStatus.name());
		}
		return null;
	}

	private VersionEnum version() {
		return VersionEnum.fromValue(xPlan.getVersion());
	}

	private List<Link> links() {
		List<Link> links = new ArrayList<>();
		URI selfRef = createSelfRef();
		if (selfRef != null) {
			Link selfLink = new Link().href(selfRef).rel(SELF).type(selfMediaType).title(xPlan.getName());
			links.add(selfLink);

			alternateMediaTypes.forEach(mediaType -> {
				Link alternateLink = new Link().href(selfRef).rel(ALTERNATE).type(mediaType).title(xPlan.getName());
				links.add(alternateLink);
			});
		}

		if (managerApiConfiguration.getWmsUrl() != null) {
			Link planwerkWmsLink = createWmsEndpointUrl();
			if (planwerkWmsLink != null)
				links.add(planwerkWmsLink);
		}
		return links;
	}

	private URI createSelfRef() {
		URI apiUrl = managerApiConfiguration.getApiUrl();
		URIBuilder uriBuilder = new URIBuilder(apiUrl);

		List<String> pathSegments = new ArrayList<>();
		if (apiUrl.getPath() != null && !apiUrl.getPath().isEmpty())
			pathSegments.addAll(Arrays.asList(apiUrl.getPath().split("/")));
		pathSegments.addAll(Arrays.asList(JerseyConfig.APP_PATH.split("/")));
		pathSegments.add("plan");
		pathSegments.add(xPlan.getId());
		uriBuilder.setPathSegments(pathSegments.stream()
			.filter(pathSegment -> pathSegment != null && !pathSegment.isEmpty())
			.collect(Collectors.toList()));
		try {
			return uriBuilder.build();
		}
		catch (URISyntaxException e) {
			LOG.warn("Could not create self reference: " + e.getMessage(), e);
		}
		return null;
	}

	private Link createWmsEndpointUrl() {
		try {
			URIBuilder uriBuilder = new URIBuilder(managerApiConfiguration.getWmsUrl());
			List<String> pathSegments = new ArrayList<>();
			pathSegments.addAll(uriBuilder.getPathSegments());
			pathSegments.add("services");
			pathSegments.add(detectService());
			pathSegments.add("planname");
			pathSegments.add(xPlan.getName().replace("/", ""));
			uriBuilder.setPathSegments(pathSegments);
			URI planwerkWmsRef = uriBuilder.build();
			Link planwerkWmsLink = new Link().href(planwerkWmsRef).rel(PLANWERKWMS).title(xPlan.getName());
			return planwerkWmsLink;
		}
		catch (URISyntaxException e) {
			LOG.warn("Could not build XPlanwerkWMS url: " + e.getMessage(), e);
		}
		return null;
	}

	private PlanInfoBbox bbox() {
		XPlanEnvelope bbox = xPlan.getBbox();
		if (bbox != null)
			return new PlanInfoBbox().crs(bbox.getCrs())
				.minX(bbox.getMinX())
				.minY(bbox.getMinY())
				.maxX(bbox.getMaxX())
				.maxY(bbox.getMaxY());
		return null;
	}

	private String detectService() {
		if (xPlan.getPlanStatus() != null)
			switch (xPlan.getPlanStatus()) {
				case ARCHIVIERT:
					return "planwerkwmsarchive";
				case IN_AUFSTELLUNG:
					return "planwerkwmspre";
			}
		return "planwerkwms";
	}

}
