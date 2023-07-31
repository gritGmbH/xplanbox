package de.latlon.xplan.manager.storage.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
	public void testDeleteRasterFiles() throws StorageException {
		AmazonS3 client = spy(AmazonS3.class);
		ObjectListing objectListing = mock(ObjectListing.class);
		S3Object object = mock(S3Object.class);
		when(object.getObjectContent()).thenReturn(mock(S3ObjectInputStream.class));
		ObjectMetadata objectMetadata = mock(ObjectMetadata.class);
		when(objectMetadata.getContentType()).thenReturn("image/png");
		when(objectMetadata.getContentLength()).thenReturn(90l);
		when(object.getObjectMetadata()).thenReturn(objectMetadata);
		when(object.getKey()).thenReturn("1_test.png");
		when(client.listObjects(eq(BUCKET_NAME), eq("1_"))).thenReturn(objectListing);
		when(client.getObject(eq(BUCKET_NAME), eq("1_test.png"))).thenReturn(object);

		S3ObjectSummary objectToDelete = mock(S3ObjectSummary.class);
		when(objectToDelete.getKey()).thenReturn("1_test.png");
		List<S3ObjectSummary> objectSummaries = Collections.singletonList(objectToDelete);
		when(objectListing.getObjectSummaries()).thenReturn(objectSummaries);
		S3StorageCleanUpManager s3RasterStorage = new S3StorageCleanUpManager(client, BUCKET_NAME);

		StorageEvent storageEvent = mock(StorageEvent.class);
		s3RasterStorage.deleteAll("1", storageEvent);

		verify(client).listObjects(BUCKET_NAME, "1_");
		verify(client).deleteObject(BUCKET_NAME, "1_test.png");
		ArgumentCaptor<de.latlon.xplan.manager.storage.s3.S3Object> argument = ArgumentCaptor
			.forClass(de.latlon.xplan.manager.storage.s3.S3Object.class);
		verify(storageEvent).addDeletedKey(argument.capture());
		assertThat(argument.getValue().getS3Metadata().getKey(), is("1_test.png"));
	}

}
