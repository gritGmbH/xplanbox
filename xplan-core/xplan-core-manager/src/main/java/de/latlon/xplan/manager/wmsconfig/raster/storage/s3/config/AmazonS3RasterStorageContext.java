/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.wmsconfig.raster.storage.s3.config;

import com.amazonaws.services.s3.AmazonS3;
import de.latlon.xplan.manager.storage.s3.config.AmazonS3Context;
import de.latlon.xplan.manager.wmsconfig.raster.storage.s3.S3RasterStorage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * Spring configuration for using AWS S3 as a storage for raster data. This requires
 * MapServer to be used as WMS service. Check the <code>RasterStorageContext</code>
 * configuration how to set MapServer as WMS.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 * @since 7.0
 * @see de.latlon.xplan.manager.wmsconfig.config.RasterStorageContext
 */
@Configuration
@Profile("s3img")
@Import({ AmazonS3Context.class })
public class AmazonS3RasterStorageContext {

	@Bean
	public S3RasterStorage rasterStorage(AmazonS3 s3Client,
			@Value("${s3.bucketName:#{environment.XPLAN_S3_BUCKET_NAME}}") String bucketName) {
		return new S3RasterStorage(s3Client, bucketName);
	}

}
