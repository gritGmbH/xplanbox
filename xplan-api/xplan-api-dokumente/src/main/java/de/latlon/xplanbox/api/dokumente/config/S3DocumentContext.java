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
