/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster.storage.s3.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import de.latlon.xplan.manager.wmsconfig.raster.storage.s3.S3RasterStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PreDestroy;

/**
 * Spring configuration for using AWS S3 as a storage for raster data. This requires
 * MapServer to be used as WMS service. Check the <code>RasterStorageContext</code>
 * configuration how to set MapServer as WMS.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 * @since 6.1
 * @see de.latlon.xplan.manager.wmsconfig.config.RasterStorageContext
 */
@Configuration
@Profile("s3")
@PropertySource(value = "classpath:s3.properties", ignoreResourceNotFound = true)
public class AmazonS3Context {

	@Autowired
	private AmazonS3 s3Client;

	@Bean
	public S3RasterStorage rasterStorage(GdalRasterAdapter rasterAdapter, AmazonS3 s3Client,
			@Value("${s3.bucketName:#{environment.MAPSERVER_S3_BUCKET_NAME}}") String bucketName) {
		return new S3RasterStorage(rasterAdapter, s3Client, bucketName);
	}

	@Bean
	public GdalRasterAdapter rasterAdapter() {
		return new GdalRasterAdapter();
	}

	@Bean
	public AmazonS3 s3Client(AWSCredentials credentials, Regions regions) {
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(regions).build();
	}

	@Bean
	public Regions regions(@Value("${s3.region:#{environment.MAPSERVER_S3_REGION}}") String regions) {
		Regions defaultRegion = Regions.fromName(regions);
		return defaultRegion;
	}

	@Bean
	public AWSCredentials credentials(
			@Value("${s3.accessKeyId:#{environment.MAPSERVER_S3_ACCESS_KEY}}") String accessKeyId,
			@Value("${s3.secretKey:#{environment.MAPSERVER_S3_SECRET_ACCESS_KEY}}") String secretKey) {
		return new BasicAWSCredentials(accessKeyId, secretKey);
	}

	@PreDestroy
	public void shutdown() {
		s3Client.shutdown();
	}

}
