package de.latlon.xplan.manager.storage.s3.config;

import com.amazonaws.services.s3.AmazonS3;
import de.latlon.xplan.manager.storage.s3.S3Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@Configuration
public class S3StorageTestContext {

	@Bean
	public S3Storage rollbackStorage(AmazonS3 s3Client,
			@Value("${s3.bucketName:#{environment.XPLAN_S3_BUCKET_NAME}}") String bucketName) {
		return new S3Storage(s3Client, bucketName);
	}

}
