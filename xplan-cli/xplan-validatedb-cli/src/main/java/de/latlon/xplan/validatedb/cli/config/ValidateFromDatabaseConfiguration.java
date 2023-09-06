/*-
 * #%L
 * xplan-evaluation-schema-synchronize-cli - Datenbankschema für die Auswertung der XPlanGML-Daten
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
package de.latlon.xplan.validatedb.cli.config;

import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.validatedb.cli.domain.ValidationResultSummary;
import de.latlon.xplan.validatedb.cli.domain.XPlanWithFeatureCollection;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersion;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersionParser;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
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
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
public class ValidateFromDatabaseConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(ValidateFromDatabaseConfiguration.class);

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

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	@StepScope
	public SemanticValidator semanticValidator(@Value("#{jobParameters[rulesDirectory]}") String rulesDirectory)
			throws ConfigurationException, URISyntaxException {
		try {
			LOG.info("Rules are read from: {}", rulesDirectory);
			Path rulesPath = get(rulesDirectory);
			RulesVersionParser rulesVersionParser = new RulesVersionParser();
			RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion(rulesPath);
			RulesMetadata rulesMetadata = new RulesMetadata(rulesVersion);
			return new XQuerySemanticValidator(
					new XQuerySemanticValidatorConfigurationRetriever(rulesPath, rulesMetadata));
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
			.setSql("SELECT id, xp_version, name, district, filename, data FROM xplanmgr.plans p, xplanmgr.artefacts a "
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
		Resource outputResource = new FileSystemResource(outputFile);
		FlatFileItemWriter<ValidationResultSummary> writer = new FlatFileItemWriter<>();
		writer.setResource(outputResource);
		writer.setAppendAllowed(true);
		writer.setHeaderCallback(w -> w.append("id,version,name,district,result,failedRules"));
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
						fields.add(encapsulate(validationResultSummary.getDistrict()));
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Step step(JdbcCursorItemReader planFromDatabaseReader, ValidationProcessor validationProcessor,
			ItemWriter validationResultsWriter) {
		return stepBuilderFactory.get("validateFromDatabaseStep")
			.<XPlanWithFeatureCollection, ValidationResultSummary>chunk(1)
			.reader(planFromDatabaseReader)
			.processor(validationProcessor)
			.writer(validationResultsWriter)
			.build();
	}

	@Bean
	public Job job(Step step) {
		return jobBuilderFactory.get("validateFromDatabaseJob").incrementer(new RunIdIncrementer()).start(step).build();
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
