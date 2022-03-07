package de.latlon.xplan.validatedb.cli.config;

import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validatedb.cli.domain.ValidationResultSummary;
import de.latlon.xplan.validatedb.cli.domain.XPlanWithFeatureCollection;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.xquery.XQuerySemanticValidator;
import org.apache.commons.dbcp.BasicDataSource;
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
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
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

import static java.nio.file.Paths.get;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Configuration
@EnableBatchProcessing
public class ValidateFromDatabaseConfiguration {

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
			throws ValidatorException, URISyntaxException {
		try {
			System.out.println("Rules are read from: " + rulesDirectory);
			Path rulesPath = get(rulesDirectory);
			return new XQuerySemanticValidator(new XQuerySemanticValidatorConfigurationRetriever(rulesPath));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@StepScope
	@Bean
	public JdbcCursorItemReader planFromDatabaseReader(@Value("#{jobParameters[jdbcurl]}") String jdbcurl,
			@Value("#{jobParameters[user]}") String user, @Value("#{jobParameters[password]}") String password) {
		System.out.println("JDBC connection:");
		System.out.println(" - jdbcurl: " + jdbcurl);
		System.out.println(" - user: " + user);
		System.out.println(" - password: " + password);
		BasicDataSource dataSource = createDataSource(jdbcurl, user, password);
		JdbcCursorItemReader xplanItemReader = new JdbcCursorItemReader<>();
		xplanItemReader.setSql(
				"SELECT id, xp_version, name, district, filename, data FROM xplanmgr.plans p, xplanmgr.artefacts a "
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
	public ItemWriter validationResultsWriter() throws IOException {
		File outputFile = Files.createTempFile("result", ".csv").toFile();
		System.out.println("Write result to file " + outputFile);
		Resource outputResource = new FileSystemResource(outputFile);
		FlatFileItemWriter<ValidationResultSummary> writer = new FlatFileItemWriter<>();
		writer.setResource(outputResource);
		writer.setAppendAllowed(true);
		writer.setHeaderCallback(w -> w.append("id,version,name,district,result,failedRules"));
		writer.setLineAggregator(new DelimitedLineAggregator<ValidationResultSummary>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<ValidationResultSummary>() {
					{
						setNames(new String[] { "id", "version", "name", "district", "result", "failedRules" });
					}
				});

			}
		});
		return writer;
	}

	@Bean
	public Step step(JdbcCursorItemReader planFromDatabaseReader, ValidationProcessor validationProcessor,
			ItemWriter validationResultsWriter) {
		return stepBuilderFactory.get("validateFromDatabaseStep")
				.<XPlanWithFeatureCollection, ValidationResultSummary>chunk(1).reader(planFromDatabaseReader)
				.processor(validationProcessor).writer(validationResultsWriter).build();
	}

	@Bean
	public Job job(Step step) {
		return jobBuilderFactory.get("validateFromDatabaseJob").incrementer(new RunIdIncrementer()).start(step).build();
	}

	private BasicDataSource createDataSource(String jdbcurl, String user, String password) {
		return DataSourceBuilder.create().driverClassName("org.postgresql.Driver").url(jdbcurl)
				.type(BasicDataSource.class).username(user).password(password).build();
	}

}