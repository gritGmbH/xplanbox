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
package de.latlon.xplanbox.api.commons.handler;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplanbox.api.commons.v1.model.RulesMetadata;
import de.latlon.xplanbox.api.commons.v1.model.VersionEnum;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SystemConfigHandler {

	private XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever;

	private List<de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata> profileMetadata;

	public SystemConfigHandler(
			XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever,
			List<de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata> profileMetadata) {
		this.xQuerySemanticValidatorConfigurationRetriever = xQuerySemanticValidatorConfigurationRetriever;
		this.profileMetadata = profileMetadata;
	}

	public RulesMetadata getRulesMetadata() throws IOException {
		SemanticValidatorConfiguration semanticValidatorConfiguration = xQuerySemanticValidatorConfigurationRetriever
			.retrieveConfiguration();
		if (semanticValidatorConfiguration == null) {
			return new RulesMetadata();
		}
		de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata defaultRulesMetadata = semanticValidatorConfiguration
			.getRulesMetadata();
		return createRulesMetadata(defaultRulesMetadata);
	}

	public List<RulesMetadata> getProfiles() {
		return profileMetadata.stream()
			.map(profileMetadata -> createRulesMetadata(profileMetadata))
			.collect(Collectors.toList());
	}

	public List<VersionEnum> allSupportedVersions() {
		return Arrays.stream(XPlanVersion.values())
			.filter(xPlanVersion -> !XPlanVersion.XPLAN_SYN.equals(xPlanVersion))
			.map(xPlanVersion -> VersionEnum.fromValue(xPlanVersion.name()))
			.collect(Collectors.toList());
	}

	private RulesMetadata createRulesMetadata(
			de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata sourceRulesMetadata) {
		RulesMetadata rulesMetadata = new RulesMetadata();
		if (sourceRulesMetadata != null) {
			rulesMetadata.id(sourceRulesMetadata.getId())
				.name(sourceRulesMetadata.getName())
				.description(sourceRulesMetadata.getDescription())
				.source(sourceRulesMetadata.getSource())
				.version(sourceRulesMetadata.getVersion());
		}
		return rulesMetadata;
	}

}
