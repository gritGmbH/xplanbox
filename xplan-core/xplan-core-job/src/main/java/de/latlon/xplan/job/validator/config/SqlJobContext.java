/*-
 * #%L
 * xplan-validator-wms - XPlanValidatorWMS
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
package de.latlon.xplan.job.validator.config;

import de.latlon.xplan.job.validator.sql.SqlDeleteJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import static de.latlon.xplan.job.validator.config.JobContext.DELETE_AFTER_KEY;
import static de.latlon.xplan.job.validator.config.JobContext.DELETE_INTERVAL_IN_SECONDS;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Spring configuration for Quartz scheduler and jobs for XPlanValidatorWMS based on
 * SQLFeatureStoreConfig.
 *
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Configuration
@Profile("validatorwmssql")
public class SqlJobContext {

	@Bean
	public JobDetail deleteJob(@Value("${#{environment.DELETE_AFTER_MINUTES}:5}") int deleteAfterInMinutes) {
		return JobBuilder.newJob().ofType(SqlDeleteJob.class).withIdentity("sqlDeleteJob", "xplan-validator-wms")
				.storeDurably().withDescription("Delete features from MemoryFeatureStore ...")
				.usingJobData(DELETE_AFTER_KEY, deleteAfterInMinutes).build();
	}

	@Bean
	Trigger deleteTrigger(JobDetail deleteJob) {
		return TriggerBuilder.newTrigger().forJob(deleteJob).withIdentity("sqlDeleteTrigger", "xplan-validator-wms")
				.withDescription("Delete trigger")
				.withSchedule(simpleSchedule().withIntervalInSeconds(DELETE_INTERVAL_IN_SECONDS).repeatForever())
				.startNow().build();
	}

	@Bean
	public Scheduler scheduler(JobDetail deleteJob, Trigger deleteTrigger, SchedulerFactoryBean schedulerFactory)
			throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.scheduleJob(deleteJob, deleteTrigger);
		scheduler.start();
		return scheduler;
	}

}
