/*-
 * #%L
 * xplan-validator-wms - XPlanValidatorWMS
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
package de.latlon.xplan.job.validator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Spring configuration for Quartz scheduler and jobs.
 *
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Configuration
@Profile("validatorwmssql | validatorwmsmemory")
public class JobContext {

	public static final String DELETE_AFTER_KEY = "DELETE_AFTER_MINUTES";

	public static final int DEFAULT_DELETE_AFTER_IN_MINUTES = 5;

	static final int IMPORT_INTERVAL_IN_SECONDS = 1;

	static final int DELETE_INTERVAL_IN_SECONDS = 30;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public SchedulerFactoryBean schedulerFactory() {
		SchedulerJobFactory jobFactory = new SchedulerJobFactory();
		jobFactory.setApplicationContext(applicationContext);

		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
		schedulerFactory.setJobFactory(jobFactory);
		schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);

		return schedulerFactory;
	}

}
