package de.latlon.xplan.manager.wmsconfig.raster.storage;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import static de.latlon.xplan.manager.wmsconfig.raster.storage.S3RasterStorage.BUCKET_NAME;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class S3RasterStorageTest {

	@Test
	public void testAddRasterFile() throws IOException, StorageException {
		AmazonS3 client = spy(AmazonS3.class);
		S3RasterStorage s3RasterStorage = createS2RasterStorage(client);
		XPlanArchiveContentAccess archive = mockArchive();

		String key = s3RasterStorage.addRasterFile(1, "test", archive);

		assertThat(key, is("1_test"));
		verify(client).doesBucketExistV2(eq(BUCKET_NAME));
		verify(client).putObject(eq(BUCKET_NAME), eq("1_test"), nullable(InputStream.class), any(ObjectMetadata.class));
		verify(client).shutdown();
	}

	@Test
	public void testDeleteRasterFiles() {
		AmazonS3 client = spy(AmazonS3.class);
		ObjectListing objectListing = mock(ObjectListing.class);
		when(client.listObjects(eq(BUCKET_NAME), eq("1_"))).thenReturn(objectListing);

		S3ObjectSummary objectToDelete = mock(S3ObjectSummary.class);
		when(objectToDelete.getKey()).thenReturn("1_test");
		List<S3ObjectSummary> objectSummaries = Collections.singletonList(objectToDelete);
		when(objectListing.getObjectSummaries()).thenReturn(objectSummaries);
		S3RasterStorage s3RasterStorage = createS2RasterStorage(client);

		s3RasterStorage.deleteRasterFiles("1");

		verify(client).listObjects(BUCKET_NAME, "1_");
		verify(client).deleteObject(BUCKET_NAME, "1_test");
		verify(client).shutdown();
	}

	@Test
	public void testDeleteRasterFile() {
		AmazonS3 client = spy(AmazonS3.class);
		ObjectListing objectListing = mock(ObjectListing.class);
		when(client.listObjects(eq(BUCKET_NAME), eq("1_test"))).thenReturn(objectListing);

		S3ObjectSummary objectToDelete = mock(S3ObjectSummary.class);
		when(objectToDelete.getKey()).thenReturn("1_test");
		List<S3ObjectSummary> objectSummaries = Collections.singletonList(objectToDelete);
		when(objectListing.getObjectSummaries()).thenReturn(objectSummaries);
		S3RasterStorage s3RasterStorage = createS2RasterStorage(client);

		s3RasterStorage.deleteRasterFiles("1", "test");

		verify(client).deleteObject(BUCKET_NAME, "1_test");
		verify(client).shutdown();
	}

	private S3RasterStorage createS2RasterStorage(AmazonS3 client) {
		GdalRasterAdapter rasterAdapter = mockGdalRasterAdapter();
		AmazonS3Factory amazonS3Factory = mockAmazonS3Factory(client);
		S3RasterStorage s3RasterStorage = new S3RasterStorage(rasterAdapter, amazonS3Factory);
		return s3RasterStorage;
	}

	private GdalRasterAdapter mockGdalRasterAdapter() {
		return mock(GdalRasterAdapter.class);
	}

	private AmazonS3Factory mockAmazonS3Factory(AmazonS3 client) {
		AmazonS3Factory mock = mock(AmazonS3Factory.class);
		when(mock.createClient()).thenReturn(client);
		return mock;
	}

	private XPlanArchiveContentAccess mockArchive() {
		return mock(XPlanArchiveContentAccess.class);
	}

}
