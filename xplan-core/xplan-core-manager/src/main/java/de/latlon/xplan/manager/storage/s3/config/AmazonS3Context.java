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
package de.latlon.xplan.manager.storage.s3.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import de.latlon.xplan.manager.storage.StorageCleanUpManager;
import de.latlon.xplan.manager.storage.s3.S3Storage;
import de.latlon.xplan.manager.storage.s3.S3StorageCleanUpManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Spring configuration for using AWS S3 as a storage.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 * @since 7.0
 */
@Configuration
@Profile("s3img | s3doc")
@ComponentScan("de.latlon.xplan.manager.storage.s3.listener")
public class AmazonS3Context {

	@Bean(destroyMethod = "shutdown")
	public AmazonS3 s3Client(AWSCredentials credentials,
			@Value("${s3.region:#{environment.XPLAN_S3_REGION}}") String signingRegion,
			@Value("${s3.endpoint.url:#{environment.XPLAN_S3_ENDPOINT}}") String endpointUrl) {
		AmazonS3 client;
		// TODO refactoring if/else to @ConditionalOnExpression with SpringBoot into 2
		// SpringBeans
		if (endpointUrl == null || endpointUrl.isEmpty()) {
			client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(signingRegion)
				.build();
		}
		else {
			AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(endpointUrl,
					signingRegion);
			client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withEndpointConfiguration(endpoint)
				.build();
		}
		return client;
	}

	@Bean
	public AWSCredentials credentials(@Value("${s3.accessKeyId:#{environment.XPLAN_S3_ACCESS_KEY}}") String accessKeyId,
			@Value("${s3.secretKey:#{environment.XPLAN_S3_SECRET_ACCESS_KEY}}") String secretKey) {
		return new BasicAWSCredentials(accessKeyId, secretKey);
	}

	@Bean
	public StorageCleanUpManager storageCleanUpManager(AmazonS3 s3Client,
			@Value("${s3.bucketName:#{environment.XPLAN_S3_BUCKET_NAME}}") String bucketName) {
		return new S3StorageCleanUpManager(s3Client, bucketName);
	}

	@Bean
	public S3Storage rollbackStorage(AmazonS3 s3Client,
			@Value("${s3.bucketName:#{environment.XPLAN_S3_BUCKET_NAME}}") String bucketName) {
		return new S3Storage(s3Client, bucketName);
	}

}
