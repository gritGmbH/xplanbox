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
package de.latlon.xplan.manager.storage.s3;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import de.latlon.xplan.commons.archive.ArchiveEntry;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class S3Storage {

	private static final Logger LOG = LoggerFactory.getLogger(S3Storage.class);

	private final AmazonS3 client;

	private final String bucketName;

	public S3Storage(AmazonS3 client, String bucketName) {
		this.client = client;
		this.bucketName = bucketName;
	}

	/**
	 * @param key of the object to return
	 * @return the S3Object with the passed key, never <code>null</code>
	 * @throws StorageException if an error occurred requesting the object or an object
	 * with the passed key was not found
	 */
	public de.latlon.xplan.manager.storage.s3.S3Object getObject(String key) throws StorageException {
		S3Object object = null;
		try {
			LOG.info("Get object with key {} from bucket {}.", key, bucketName);
			object = client.getObject(bucketName, key);
			ObjectMetadata objectMetadata = object.getObjectMetadata();
			S3Metadata s3Metadata = new S3Metadata(object.getKey(), objectMetadata.getContentType(),
					objectMetadata.getContentLength());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			object.getObjectContent().transferTo(bos);
			return new de.latlon.xplan.manager.storage.s3.S3Object(s3Metadata, bos.toByteArray());
		}
		catch (AmazonServiceException | IOException e) {
			throw new StorageException("Could not get object with key " + key + " from bucket " + bucketName + ".", e);
		}
		finally {
			closeQuietly(object);
		}
	}

	/**
	 * @param key of the object metadata to return
	 * @return the S3Metadata of the object with the passed key, never <code>null</code>
	 * @throws StorageException if an error occurred requesting the object or an object
	 * with the passed key was not found
	 */
	public S3Metadata getObjectMetadata(String key) throws StorageException {
		try {
			LOG.info("Get object metadata with key {} from bucket {}.", key, bucketName);
			S3Object object = client.getObject(bucketName, key);
			ObjectMetadata objectMetadata = object.getObjectMetadata();
			return new S3Metadata(object.getKey(), objectMetadata.getContentType(), objectMetadata.getContentLength());
		}
		catch (AmazonServiceException e) {
			throw new StorageException("Could not get object with key " + key + " from bucket " + bucketName + ".", e);
		}
	}

	public List<S3ObjectSummary> listObjects(String prefix) {
		ObjectListing objectsToDelete = client.listObjects(bucketName, prefix);
		return objectsToDelete.getObjectSummaries();
	}

	protected String insertObject(int planId, String entryName, XPlanArchiveContentAccess archive)
			throws StorageException {
		String key = createKey(planId, entryName);
		try {
			LOG.info("Insert object with key {} in bucket {}.", key, bucketName);
			ArchiveEntry entry = archive.getEntry(entryName);
			String contentType = entry.getContentType();
			long contentLength = entry.getContentLength();
			InputStream content = archive.retrieveInputStreamFor(entryName);
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(contentLength);
			metadata.setContentType(contentType);
			client.putObject(bucketName, key, content, metadata);
			return key;
		}
		catch (AmazonServiceException e) {
			throw new StorageException("Could not insert object with key " + key + " in bucket " + bucketName + ".", e);
		}
	}

	protected void insertObject(String key, Path file) throws StorageException {
		try {
			LOG.info("Insert object with key {} in bucket {}.", key, bucketName);
			client.putObject(bucketName, key, file.toFile());
		}
		catch (AmazonServiceException e) {
			throw new StorageException("Could not insert object with key " + key + " in bucket " + bucketName + ".", e);
		}
	}

	public void insertObject(de.latlon.xplan.manager.storage.s3.S3Object object) throws StorageException {
		String key = object.getS3Metadata().getKey();
		try {
			LOG.info("Insert object with key {} in bucket {}.", key, bucketName);
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(object.getS3Metadata().getContentLength());
			metadata.setContentType(object.getS3Metadata().getContentType());
			ByteArrayInputStream bis = new ByteArrayInputStream(object.getContent());
			client.putObject(bucketName, key, bis, metadata);
		}
		catch (AmazonServiceException e) {
			throw new StorageException("Could not insert object with key " + key + " in bucket " + bucketName + ".", e);
		}
	}

	public void deleteObjects(String prefix) {
		List<S3ObjectSummary> objects = listObjects(prefix);
		for (S3ObjectSummary object : objects) {
			deleteObject(object);
		}
	}

	public void deleteObject(S3ObjectSummary object) {
		String key = object.getKey();
		LOG.info("Delete object with key {} from bucket {}.", key, bucketName);
		client.deleteObject(bucketName, key);
	}

	protected String createKey(int planId, String entry) {
		return planId + "_" + entry;
	}

	protected Bucket createBucketIfNotExists() throws StorageException {
		if (client.doesBucketExistV2(bucketName)) {
			LOG.info("Bucket {} already exists.", bucketName);
			return getBucket();
		}
		else {
			try {
				LOG.info("Create bucket with name {}.", bucketName);
				return client.createBucket(bucketName);
			}
			catch (AmazonS3Exception e) {
				throw new StorageException("Could not create bucket", e);
			}
		}
	}

	public Bucket getBucket() {
		List<Bucket> buckets = client.listBuckets();
		for (Bucket bucket : buckets) {
			if (bucket.getName().equals(bucketName)) {
				return bucket;
			}
		}
		return null;
	}

	private void closeQuietly(S3Object object) {
		if (object != null) {
			try {
				object.close();
			}
			catch (IOException e) {
				LOG.warn("Connection could not be closed: {}", e.getMessage());
				LOG.trace(e.getMessage(), e);
			}
		}
	}

}
