package de.latlon.xplan.manager.storage.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class S3StorageCleanUpManagerTest {

	private static final String BUCKET_NAME = "xplanbox";

	@Test
	public void testDeleteRasterFiles() {
		AmazonS3 client = spy(AmazonS3.class);
		ObjectListing objectListing = mock(ObjectListing.class);
		when(client.listObjects(eq(BUCKET_NAME), eq("1_"))).thenReturn(objectListing);

		S3ObjectSummary objectToDelete = mock(S3ObjectSummary.class);
		when(objectToDelete.getKey()).thenReturn("1_test");
		List<S3ObjectSummary> objectSummaries = Collections.singletonList(objectToDelete);
		when(objectListing.getObjectSummaries()).thenReturn(objectSummaries);
		S3StorageCleanUpManager s3RasterStorage = new S3StorageCleanUpManager(client, BUCKET_NAME);

		s3RasterStorage.deleteAll("1");

		verify(client).listObjects(BUCKET_NAME, "1_");
		verify(client).deleteObject(BUCKET_NAME, "1_test");
	}

}
