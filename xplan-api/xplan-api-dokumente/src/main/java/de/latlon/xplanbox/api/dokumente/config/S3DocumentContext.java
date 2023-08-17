/*-
 * #%L
 * xplan-api-dokumente - XPlanDokumentenAPI
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
package de.latlon.xplanbox.api.dokumente.config;

import com.amazonaws.services.s3.AmazonS3;
import de.latlon.xplan.manager.storage.s3.S3Storage;
import de.latlon.xplan.manager.storage.s3.config.AmazonS3Context;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Configuration
@Profile("s3img & s3doc")
@Import(AmazonS3Context.class)
public class S3DocumentContext {

	private static final Logger LOG = getLogger(S3DocumentContext.class);

	@Bean
	public S3Storage documentStorage(AmazonS3 s3Client,
			@Value("${s3.bucketName:#{environment.XPLAN_S3_BUCKET_NAME}}") String bucketName) {
		LOG.info("Instantiate S3Storage to manage documents");
		return new S3Storage(s3Client, bucketName);
	}

}
