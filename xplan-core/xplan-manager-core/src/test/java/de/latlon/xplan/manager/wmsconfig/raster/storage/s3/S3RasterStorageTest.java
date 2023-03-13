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
package de.latlon.xplan.manager.wmsconfig.raster.storage.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

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

	private static final String BUCKET_NAME = "xplanbox";

	@Test
	public void testAddRasterFile() throws IOException, StorageException {
		AmazonS3 client = spy(AmazonS3.class);
		S3RasterStorage s3RasterStorage = createS2RasterStorage(client);
		XPlanArchiveContentAccess archive = mockArchive();

		String key = s3RasterStorage.addRasterFile(1, "test", archive);

		assertThat(key, is("1_test"));
		verify(client).doesBucketExistV2(eq(BUCKET_NAME));
		verify(client).putObject(eq(BUCKET_NAME), eq("1_test"), nullable(InputStream.class), any(ObjectMetadata.class));
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
	}

	private S3RasterStorage createS2RasterStorage(AmazonS3 client) {
		GdalRasterAdapter rasterAdapter = mockGdalRasterAdapter();
		return new S3RasterStorage(rasterAdapter, client, BUCKET_NAME);
	}

	private GdalRasterAdapter mockGdalRasterAdapter() {
		return mock(GdalRasterAdapter.class);
	}

	private XPlanArchiveContentAccess mockArchive() {
		return mock(XPlanArchiveContentAccess.class);
	}

}
