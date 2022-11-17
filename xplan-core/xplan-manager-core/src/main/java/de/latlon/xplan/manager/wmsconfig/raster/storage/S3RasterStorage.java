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
package de.latlon.xplan.manager.wmsconfig.raster.storage;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

/**
 * {@link RasterStorage} implementation storing and deleting raster files in a S3 bucket.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class S3RasterStorage implements RasterStorage {

	private static final Logger LOG = LoggerFactory.getLogger(S3RasterStorage.class);

	static final String BUCKET_NAME = "xplanbox";

	private final GdalRasterAdapter rasterAdapter;

	private final AmazonS3Factory amazonS3Factory;

	public S3RasterStorage(GdalRasterAdapter rasterAdapter, AmazonS3Factory amazonS3Factory) {
		this.rasterAdapter = rasterAdapter;
		this.amazonS3Factory = amazonS3Factory;
	}

	@Override
	public String addRasterFile(int planId, String entryName, XPlanArchiveContentAccess archive)
			throws IOException, StorageException {
		AmazonS3 client = amazonS3Factory.createClient();
		try {
			createBucketIfNotExists(client);
			String objectKey = insertObject(planId, entryName, archive, client);
			Vector<?> referencedFiles = rasterAdapter.getReferencedFiles(archive, entryName);
			if (referencedFiles != null) {
				for (Object referencedFile : referencedFiles) {
					Path file = Paths.get(referencedFile.toString());
					String newObjectKey = createKey(planId, file.getFileName().toString());
					if (!newObjectKey.equals(objectKey)) {
						insertObject(newObjectKey, file, client);
					}
				}
			}
			return objectKey;
		}
		finally {
			client.shutdown();
		}
	}

	@Override
	public void deleteRasterFiles(String planId) {
		String prefix = planId + "_";
		deleteObject(prefix);
	}

	@Override
	public void deleteRasterFiles(String planId, String rasterId) {
		String prefix = planId + "_" + rasterId;
		deleteObject(prefix);

	}

	private String insertObject(int planId, String entryName, XPlanArchiveContentAccess archive, AmazonS3 client)
			throws StorageException {
		String key = createKey(planId, entryName);
		try {
			LOG.info("Insert object with key {} in bucket {}.", key, BUCKET_NAME);
			InputStream entry = archive.retrieveInputStreamFor(entryName);
			ObjectMetadata metadata = new ObjectMetadata();
			client.putObject(BUCKET_NAME, key, entry, metadata);
			return key;
		}
		catch (AmazonServiceException e) {
			throw new StorageException("Could not insert object with key " + key + " in bucket " + BUCKET_NAME + ".",
					e);
		}
	}

	private void insertObject(String key, Path file, AmazonS3 client) throws StorageException {
		try {
			LOG.info("Insert object with key {} in bucket {}.", key, BUCKET_NAME);
			client.putObject(BUCKET_NAME, key, file.toFile());
		}
		catch (AmazonServiceException e) {
			throw new StorageException("Could not insert object with key " + key + " in bucket " + BUCKET_NAME + ".",
					e);
		}
	}

	private void deleteObject(String prefix) {
		AmazonS3 client = amazonS3Factory.createClient();
		try {
			ObjectListing objectsToDelete = client.listObjects(BUCKET_NAME, prefix);
			List<S3ObjectSummary> objects = objectsToDelete.getObjectSummaries();
			for (S3ObjectSummary object : objects) {
				String key = object.getKey();
				LOG.info("Delete object with key {} from bucket {}.", key, BUCKET_NAME);
				client.deleteObject(BUCKET_NAME, key);
			}
		}
		finally {
			client.shutdown();
		}
	}

	private String createKey(int planId, String entry) {
		return planId + "_" + entry;
	}

	private Bucket createBucketIfNotExists(AmazonS3 client) throws StorageException {
		if (client.doesBucketExistV2(BUCKET_NAME)) {
			LOG.info("Bucket {} already exists.", BUCKET_NAME);
			return getBucket(client);
		}
		else {
			try {
				LOG.info("Create bucket with name {}.", BUCKET_NAME);
				return client.createBucket(BUCKET_NAME);
			}
			catch (AmazonS3Exception e) {
				throw new StorageException("Could not create bucket", e);
			}
		}
	}

	public static Bucket getBucket(AmazonS3 client) {
		List<Bucket> buckets = client.listBuckets();
		for (Bucket bucket : buckets) {
			if (bucket.getName().equals(BUCKET_NAME)) {
				return bucket;
			}
		}
		return null;
	}

}
