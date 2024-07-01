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
package de.latlon.xplanbox.cli.validate.config;

import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesMainConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import de.latlon.xplanbox.cli.validate.db.ValidationProcessor;
import de.latlon.xplanbox.cli.validate.db.domain.ValidationResultSummary;
import de.latlon.xplanbox.cli.validate.db.domain.XPlanWithFeatureCollection;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.batch.JobLauncherApplicationRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.WritableResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.JdbcTransactionManager;

import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Paths.get;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 5.0
 */
@Configuration
@EnableBatchProcessing
public class ValidateFromDatabaseContext {

	private static final Logger LOG = LoggerFactory.getLogger(ValidateFromDatabaseContext.class);

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JobExplorer jobExplorer;

	@Autowired
	private JobRepository jobRepository;

	@Bean
	public JobLauncherApplicationRunner runner() {
		return new JobLauncherApplicationRunner(jobLauncher, jobExplorer, jobRepository);
	}

	@Bean
	@StepScope
	@SuppressFBWarnings(value = "PATH_TRAVERSAL_IN")
	public SemanticValidator semanticValidator(@Value("#{jobParameters[rulesDirectory]}") String rulesDirectory)
			throws ConfigurationException, URISyntaxException {
		try {
			Path rulesPath = getSemanticValidator(rulesDirectory);
			SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesMainConfiguration(rulesPath);
			XQuerySemanticValidatorConfigurationRetriever semanticValidatorConfigurationRetriever = new XQuerySemanticValidatorConfigurationRetriever(
					semanticRulesConfiguration);
			return new XQuerySemanticValidator(semanticValidatorConfigurationRetriever);
		}
		catch (Exception e) {
			LOG.error("Rules could not be read", e);
			throw e;
		}
	}

	@StepScope
	@Bean
	public JdbcCursorItemReader<XPlanWithFeatureCollection> planFromDatabaseReader(
			@Value("#{jobParameters[jdbcurl]}") String jdbcurl, @Value("#{jobParameters[user]}") String user,
			@Value("#{jobParameters[password]}") String password) {
		LOG.info("JDBC connection:");
		LOG.info(" - jdbcurl: {}", jdbcurl);
		LOG.info(" - user: {}", user);
		LOG.info(" - password: {}", password);
		BasicDataSource dataSource = createDataSource(jdbcurl, user, password);
		JdbcCursorItemReader<XPlanWithFeatureCollection> xplanItemReader = new JdbcCursorItemReader<>();
		xplanItemReader
			.setSql("SELECT id, xp_version, name, filename, data FROM xplanmgr.plans p, xplanmgr.artefacts a "
					+ "WHERE p.id = a.plan  AND filename = 'xplan.gml'");
		xplanItemReader.setDataSource(dataSource);
		xplanItemReader.setRowMapper(new BeanPropertyRowMapper<>(XPlanWithFeatureCollection.class));
		return xplanItemReader;
	}

	@StepScope
	@Bean
	public ValidationProcessor validationProcessor(SemanticValidator semanticValidator) {
		return new ValidationProcessor(semanticValidator);
	}

	@Bean
	public ItemWriter<ValidationResultSummary> validationResultsWriter() throws IOException {
		File outputFile = Files.createTempFile("result", ".csv").toFile();
		LOG.info("Write result to file {}", outputFile);
		WritableResource outputResource = new FileSystemResource(outputFile);
		FlatFileItemWriter<ValidationResultSummary> writer = new FlatFileItemWriter<>();
		writer.setResource(outputResource);
		writer.setAppendAllowed(true);
		writer.setHeaderCallback(w -> w.append("id,version,name,result,failedRules"));
		writer.setLineAggregator(new DelimitedLineAggregator<>() {
			{
				setDelimiter(",");
				setFieldExtractor(new FieldExtractor<>() {
					@Override
					public Object[] extract(ValidationResultSummary validationResultSummary) {
						List<Object> fields = new ArrayList<>();
						fields.add(validationResultSummary.getId());
						fields.add(validationResultSummary.getVersion());
						fields.add(encapsulate(validationResultSummary.getName()));
						fields.add(validationResultSummary.getResult());
						fields.add(encapsulate(validationResultSummary.getFailedRules()));
						return fields.toArray();
					}

					private String encapsulate(String toEncapsulate) {
						if (toEncapsulate == null)
							return null;
						return "\"" + toEncapsulate.replace("\"", "\"\"") + "\"";
					}
				});
			}
		});
		return writer;
	}

	@Bean
	public Step step(JdbcCursorItemReader planFromDatabaseReader, ValidationProcessor validationProcessor,
			ItemWriter validationResultsWriter, JobRepository jobRepository,
			JdbcTransactionManager transactionManager) {
		StepBuilder stepBuilder = new StepBuilder("validateFromDatabaseStep", jobRepository);
		return new SimpleStepBuilder<XPlanWithFeatureCollection, ValidationResultSummary>(stepBuilder).chunk(1)
			.reader(planFromDatabaseReader)
			.processor(validationProcessor)
			.writer(validationResultsWriter)
			.transactionManager(transactionManager)
			.build();
	}

	@Bean
	public Job job(Step step, JobRepository jobRepository) {
		return new JobBuilder("validateFromDatabaseJob", jobRepository).incrementer(new RunIdIncrementer())
			.start(step)
			.build();
	}

	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.HSQL)
			.addScript("/org/springframework/batch/core/schema-hsqldb.sql")
			.build();
	}

	@Bean
	public JdbcTransactionManager transactionManager(DataSource dataSource) {
		return new JdbcTransactionManager(dataSource);
	}

	private Path getSemanticValidator(String rulesDirectory) throws URISyntaxException {
		if (rulesDirectory == null) {
			LOG.info("Rules from etc/rules are used");
			URL pathToRules = ValidateFileContext.class.getProtectionDomain().getCodeSource().getLocation();
			return get(pathToRules.toURI()).getParent().getParent().resolve("etc/rules");
		}
		LOG.info("Rules are read from: {}", rulesDirectory);
		return get(rulesDirectory);
	}

	private BasicDataSource createDataSource(String jdbcurl, String user, String password) {
		return DataSourceBuilder.create()
			.driverClassName("org.postgresql.Driver")
			.url(jdbcurl)
			.type(BasicDataSource.class)
			.username(user)
			.password(password)
			.build();
	}

}