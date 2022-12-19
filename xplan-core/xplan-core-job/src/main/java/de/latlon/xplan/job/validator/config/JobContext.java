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

import de.latlon.xplan.job.validator.GmlDeleteJob;
import de.latlon.xplan.job.validator.GmlImportJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import static de.latlon.xplan.job.validator.GmlDeleteJob.DELETE_AFTER_KEY;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

/**
 * Spring configuration for Quartz scheduler and jobs.
 *
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Configuration
public class JobContext {

	private static final int IMPORT_INTERVAL_IN_SECONDS = 1;

	private static final int DELETE_INTERVAL_IN_SECONDS = 30;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public JobDetail importJob() {
		return JobBuilder.newJob().ofType(GmlImportJob.class).withIdentity("importJob", "xplan-validator-wms")
				.storeDurably().withDescription("Import GML files into FeatureStore ...").build();
	}

	@Bean
	public JobDetail deleteJob(@Value("${#{environment.DELETE_AFTER_MINUTES}:5}") int deleteAfterInMinutes) {
		return JobBuilder.newJob().ofType(GmlDeleteJob.class).withIdentity("deleteJob", "xplan-validator-wms")
				.storeDurably().withDescription("Delete GML files from FeatureStore ...")
				.usingJobData(DELETE_AFTER_KEY, deleteAfterInMinutes).build();
	}

	@Bean
	public Trigger importTrigger(JobDetail importJob) {
		return TriggerBuilder.newTrigger().forJob(importJob).withIdentity("importTrigger", "xplan-validator-wms")
				.withDescription("Import trigger")
				.withSchedule(simpleSchedule().withIntervalInSeconds(IMPORT_INTERVAL_IN_SECONDS).repeatForever())
				.startNow().build();
	}

	@Bean
	Trigger deleteTrigger(JobDetail deleteJob) {
		return TriggerBuilder.newTrigger().forJob(deleteJob).withIdentity("deleteTrigger", "xplan-validator-wms")
				.withDescription("Delete trigger")
				.withSchedule(simpleSchedule().withIntervalInSeconds(DELETE_INTERVAL_IN_SECONDS).repeatForever())
				.startNow().build();
	}

	@Bean
	public Scheduler scheduler(JobDetail importJob, Trigger importTrigger, JobDetail deleteJob, Trigger deleteTrigger,
			SchedulerFactoryBean schedulerFactory) throws SchedulerException {
		Scheduler scheduler = schedulerFactory.getScheduler();
		scheduler.scheduleJob(importJob, importTrigger);
		scheduler.scheduleJob(deleteJob, deleteTrigger);
		scheduler.start();
		return scheduler;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactory() {
		SchedulerJobFactory jobFactory = new SchedulerJobFactory();
		jobFactory.setApplicationContext(applicationContext);

		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));
		schedulerFactory.setJobFactory(jobFactory);

		return schedulerFactory;
	}

}
