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
package de.latlon.xplan.manager.wmsconfig.raster.storage.s3;

import com.amazonaws.services.s3.AmazonS3;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.storage.s3.S3Object;
import de.latlon.xplan.manager.storage.s3.S3Storage;
import de.latlon.xplan.manager.wmsconfig.raster.access.GdalRasterAdapter;
import de.latlon.xplan.manager.wmsconfig.raster.storage.RasterStorage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * {@link RasterStorage} implementation storing and deleting raster files in a S3 bucket.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class S3RasterStorage extends S3Storage implements RasterStorage {

	private final GdalRasterAdapter rasterAdapter;

	public S3RasterStorage(GdalRasterAdapter rasterAdapter, AmazonS3 client, String bucketName) {
		super(client, bucketName);
		this.rasterAdapter = rasterAdapter;
	}

	@Override
	@SuppressFBWarnings(value = "PATH_TRAVERSAL_IN")
	public String addRasterFile(int planId, String entryName, XPlanArchiveContentAccess archive,
			StorageEvent storageEvent) throws IOException, StorageException {
		createBucketIfNotExists();
		String objectKey = insertObject(planId, entryName, archive);
		storageEvent.addInsertedKey(objectKey);
		Vector<?> referencedFiles = rasterAdapter.getReferencedFiles(archive, entryName);
		if (referencedFiles != null) {
			for (Object referencedFile : referencedFiles) {
				Path file = Paths.get(referencedFile.toString());
				String newObjectKey = createKey(planId, file.getFileName().toString());
				if (!newObjectKey.equals(objectKey)) {
					insertObject(newObjectKey, file);
					storageEvent.addInsertedKey(newObjectKey);
				}
			}
		}
		return objectKey;
	}

	@Override
	public void deleteRasterFile(int planId, String fileName, StorageEvent storageEvent) throws StorageException {
		String key = planId + "_" + fileName;
		S3Object object = getObject(key);
		if (object != null)
			storageEvent.addDeletedKey(object);
		deleteObjects(key);
	}

}
