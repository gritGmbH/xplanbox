/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.wms.config;

import de.latlon.xplan.job.validator.config.JobContext;
import de.latlon.xplan.job.validator.config.SqlJobContext;
import de.latlon.xplan.job.validator.exception.JobConfigException;
import de.latlon.xplan.job.validator.workspace.ValidatorWorkspaceWrapper;
import de.latlon.xplan.validator.wms.storage.PlanStorage;
import de.latlon.xplan.validator.wms.storage.SqlPlanStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Configuration
@Profile("validatorwmssql")
@Import({ JobContext.class, SqlJobContext.class })
public class ValidatorWmsSqlContext {

	private static final Logger LOG = LoggerFactory.getLogger(ValidatorWmsSqlContext.class);

	@Bean
	public ValidatorWorkspaceWrapper validatorWorkspaceWrapper() throws JobConfigException {
		return new ValidatorWorkspaceWrapper();
	}

	@Bean
	public PlanStorage planStorage() {
		try {
			return new SqlPlanStorage();
		}
		catch (Exception e) {
			LOG.error("Could not initialise WorkspacePlanStorage. Reason: {}. MapPreview will not be available.",
					e.getMessage(), e);
		}
		return null;
	}

}
