/*-
 * #%L
 * xplan-core-job - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplan.job.validator.config;

import de.latlon.xplan.job.validator.sql.SqlDeleteJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOG = LoggerFactory.getLogger(SqlJobContext.class);

	@Bean
	public JobDetail deleteJob(@Value("#{environment.DELETE_AFTER_MINUTES ?: 5}") int deleteAfterInMinutes) {
		LOG.info("Delete validated plans after {} minutes.", deleteAfterInMinutes);
		return JobBuilder.newJob()
			.ofType(SqlDeleteJob.class)
			.withIdentity("sqlDeleteJob", "xplan-validator-wms")
			.storeDurably()
			.withDescription("Delete features from SQLFeatureStore ...")
			.usingJobData(DELETE_AFTER_KEY, deleteAfterInMinutes)
			.build();
	}

	@Bean
	Trigger deleteTrigger(JobDetail deleteJob) {
		return TriggerBuilder.newTrigger()
			.forJob(deleteJob)
			.withIdentity("sqlDeleteTrigger", "xplan-validator-wms")
			.withDescription("Delete trigger")
			.withSchedule(simpleSchedule().withIntervalInSeconds(DELETE_INTERVAL_IN_SECONDS).repeatForever())
			.startNow()
			.build();
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
