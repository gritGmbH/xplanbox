/*-
 * #%L
 * xplan-cli - Kommandozeilenwerkzeuge fuer die xPlanBox
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
package de.latlon.xplanbox.cli.validate;

import de.latlon.xplanbox.cli.validate.config.ValidateFromDatabaseContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import picocli.CommandLine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Entry point of the command line interface of the GMLLoader.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 5.0
 */
@Component
@CommandLine.Command(name = "db", description = "Validate XPlanGML in xPlanBox database.",
		subcommands = { CommandLine.HelpCommand.class })
public class ValidateFromDatabaseSubcommand implements Callable<Integer> {

	@CommandLine.Option(names = { "-h", "--host" },
			description = "hostname of the database server (default: ${DEFAULT-VALUE})", defaultValue = "localhost")
	private String host;

	@CommandLine.Option(names = { "-p", "--port" },
			description = "port of the database server (default: ${DEFAULT-VALUE})", defaultValue = "5432")
	private String port;

	@CommandLine.Option(names = { "-d", "--database" },
			description = "name of the xplanbox database (default: ${DEFAULT-VALUE})", defaultValue = "xplanbox")
	private String database;

	@CommandLine.Option(names = { "-u", "--user" }, description = "database user (default: ${DEFAULT-VALUE})",
			defaultValue = "postgres")
	private String user;

	@CommandLine.Option(names = { "-P", "--password" }, description = "database user password")
	private String password;

	@CommandLine.Option(names = { "-r", "--rules" }, description = "directory containing the rules")
	private File rules;

	@Override
	public Integer call() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
			JobParametersInvalidException, JobRestartException {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ValidateFromDatabaseContext.class);
		JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
		Job job = applicationContext.getBean(Job.class);
		Map<String, JobParameter<?>> jobParams = createJobParams();
		jobLauncher.run(job, new JobParameters(jobParams));
		return 0;
	}

	private Map<String, JobParameter<?>> createJobParams() {
		Map<String, JobParameter<?>> jobParams = new HashMap<>();
		jobParams.put("jdbcurl", new JobParameter<>(createJdbcUrl(), String.class));
		jobParams.put("user", new JobParameter<>(user, String.class));
		jobParams.put("password", new JobParameter<>(password, String.class));
		if (rules != null) {
			jobParams.put("rulesDirectory", new JobParameter<>(rules.getAbsolutePath(), String.class));
		}
		return jobParams;
	}

	private String createJdbcUrl() {
		return "jdbc:postgresql://" + host + ":" + port + "/" + database;
	}

}
