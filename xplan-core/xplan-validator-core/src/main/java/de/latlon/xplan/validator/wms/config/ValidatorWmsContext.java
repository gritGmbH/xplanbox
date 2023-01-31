/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.wms.config;

import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import de.latlon.xplan.validator.configuration.ValidatorConfiguration;
import de.latlon.xplan.validator.wms.ValidatorWmsManager;
import de.latlon.xplan.validator.wms.storage.PlanStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Configuration
@Import({ ValidatorWmsWorkspaceContext.class, ValidatorWmsSqlContext.class })
public class ValidatorWmsContext {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorWmsContext.class);

	@Autowired(required = false)
	private PlanStorage planStorage;

	@Autowired
	private ValidatorConfiguration validatorConfiguration;

	@Bean
	public ValidatorWmsManager validatorWmsManager(XPlanSynthesizer synthesizer) {
		if (planStorage == null) {
			LOG.warn("PlanStorage is not available. Map preview will not be available.");
			return null;
		}
		LOG.trace("Using validatorConfiguration: " + validatorConfiguration);
		String validatorWmsEndpoint = validatorConfiguration.getValidatorWmsEndpoint();
		if (validatorWmsEndpoint == null) {
			LOG.warn("XPlanValidatorWMS endpoint URL is not configured. Map preview will not be available.");
			return null;
		}
		try {
			return new ValidatorWmsManager(synthesizer, planStorage);
		}
		catch (IllegalArgumentException e) {
			LOG.error("Could not initialise ValidatorWmsManager. WMS resources cannot be created. Reason: {}",
					e.getMessage(), e);
		}
		return null;
	}

	@Bean
	public XPlanSynthesizer xPlanSynthesizer(SynRulesAccessor synRulesAccessor) {
		return new XPlanSynthesizer(synRulesAccessor);
	}

	@Bean
	public SynRulesAccessor synRulesAccessor() {
		return new SynRulesAccessor();
	}

}
