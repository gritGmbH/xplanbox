/*-
 * #%L
 * xplan-api-commons - xplan-api-commons
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

	public SystemConfigHandler(
			XQuerySemanticValidatorConfigurationRetriever xQuerySemanticValidatorConfigurationRetriever) {
		this.xQuerySemanticValidatorConfigurationRetriever = xQuerySemanticValidatorConfigurationRetriever;
	}

	public RulesMetadata getRulesMetadata() throws IOException {
		SemanticValidatorConfiguration semanticValidatorConfiguration = xQuerySemanticValidatorConfigurationRetriever
				.retrieveConfiguration();
		RulesMetadata rulesMetadata = new RulesMetadata();
		if (semanticValidatorConfiguration != null && semanticValidatorConfiguration.getRulesMetadata() != null)
			rulesMetadata.source(semanticValidatorConfiguration.getRulesMetadata().getSource())
					.version(semanticValidatorConfiguration.getRulesMetadata().getVersion());
		return rulesMetadata;
	}

	public List<VersionEnum> allSupportedVersions() {
		return Arrays.stream(XPlanVersion.values()).filter(xPlanVersion -> !XPlanVersion.XPLAN_SYN.equals(xPlanVersion))
				.map(xPlanVersion -> VersionEnum.fromValue(xPlanVersion.name())).collect(Collectors.toList());
	}

}
