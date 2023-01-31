/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster.storage.s3.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import io.findify.s3mock.S3Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.annotation.PreDestroy;

import static org.mockito.Mockito.mock;

/**
 * Spring Configuration to enable usage of mock objects for integration tests.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@Configuration
public class AmazonS3TestContext {

	@Autowired(required = false)
	private S3Mock s3Mock;

	@Autowired
	private AmazonS3 s3TestClient;

	@Bean
	@Primary
	public GdalRasterAdapter rasterAdapter() {
		return mock(GdalRasterAdapter.class);
	}

	@Bean
	@Profile("mock")
	public S3Mock s3Mock(@Value("${s3.endpoint.port}") int port) {
		S3Mock s3Mock = new S3Mock.Builder().withPort(port).withInMemoryBackend().build();
		s3Mock.start();
		return s3Mock;
	}

	@Bean
	@Primary
	@Profile("mock")
	public AmazonS3 s3TestClient(@Value("${s3.region}") String signingRegion,
			@Value("${s3.bucketName}") String bucketName, @Value("${s3.endpoint.url}") String url,
			@Value("${s3.endpoint.port}") String port) {
		AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(url + ":" + port,
				signingRegion);
		AmazonS3 client = AmazonS3ClientBuilder.standard().withPathStyleAccessEnabled(true)
				.withEndpointConfiguration(endpoint)
				.withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials())).build();
		client.createBucket(bucketName);
		return client;
	}

	@PreDestroy
	public void shutdown() {
		s3TestClient.shutdown();
		if (s3Mock != null) {
			s3Mock.stop();
			s3Mock.shutdown();
		}
	}

}
