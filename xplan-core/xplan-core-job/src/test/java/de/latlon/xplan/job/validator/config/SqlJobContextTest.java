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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { JobContext.class, SqlJobContext.class, MemoryJobContext.class })
@ActiveProfiles("validatorwmssql")
public class SqlJobContextTest {

	@Autowired
	private Scheduler scheduler;

	@Test
	public void checkScheduler() throws SchedulerException {
		boolean sqlDeleteJobExists = scheduler.checkExists(new JobKey("sqlDeleteJob", "xplan-validator-wms"));
		boolean gmlDeleteJobExists = scheduler.checkExists(new JobKey("gmlDeleteJob", "xplan-validator-wms"));
		boolean gmlImportJobExists = scheduler.checkExists(new JobKey("gmlImportJob", "xplan-validator-wms"));
		assertThat(sqlDeleteJobExists, is(true));
		assertThat(gmlDeleteJobExists, is(false));
		assertThat(gmlImportJobExists, is(false));
	}

}
