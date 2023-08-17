/*-
 * #%L
 * xplan-api-validator - Modul zur Gruppierung der REST-API
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
package de.latlon.xplanbox.api.validator.config;

import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.profile.SemanticProfileValidator;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import de.latlon.xplanbox.api.validator.v1.DefaultApi;
import de.latlon.xplanbox.api.validator.v1.InfoApi;
import de.latlon.xplanbox.api.validator.v1.ValidateApi;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * Indented to register the JAX-RS resources within Spring Application Context. TODO
 * Resources not configured automatically. Using JerseyTest instead.
 *
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Configuration
public class TestContext {

	private static final Logger LOG = getLogger(TestContext.class);

	@Bean
	@Profile("jaxrs")
	ResourceConfig resourceConfig() {
		ResourceConfig jerseyConfig = new ResourceConfig();
		jerseyConfig.register(ValidateApi.class);
		jerseyConfig.register(InfoApi.class);
		jerseyConfig.register(DefaultApi.class);
		return jerseyConfig;
	}

	@PostConstruct
	void initLoggingAdapter() {
		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		LOG.trace("JUL logging enabled");
	}

	@Primary
	@Bean
	public List<RulesMetadata> profileMetadata() {
		RulesMetadata profile1 = new RulesMetadata("id1", "test1", "description1", "0.1", "unbekannt");
		RulesMetadata profile2 = new RulesMetadata("id2", "test2", "description2", "0.2", "lokal");
		return Arrays.asList(profile1, profile2);
	}

	@Primary
	@Bean
	public List<SemanticProfileValidator> profileValidators(List<RulesMetadata> profileMetadata) {
		return profileMetadata.stream().map(profile -> {
			SemanticProfileValidator semanticProfileValidator = mock(SemanticProfileValidator.class);
			when(semanticProfileValidator.getId()).thenReturn(profile.getId());
			SemanticValidatorResult result = mock(SemanticValidatorResult.class);
			when(result.getRulesMetadata()).thenReturn(profile);
			when(semanticProfileValidator.validateSemantic(any(SemanticValidableXPlanArchive.class), anyList()))
				.thenReturn(result);
			return semanticProfileValidator;
		}).collect(Collectors.toList());
	}

}
