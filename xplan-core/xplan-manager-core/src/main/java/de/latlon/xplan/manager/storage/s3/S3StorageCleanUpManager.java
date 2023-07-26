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

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import de.latlon.xplan.manager.storage.StorageCleanUpManager;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class S3StorageCleanUpManager extends S3Storage implements StorageCleanUpManager {

	public S3StorageCleanUpManager(AmazonS3 client, String bucketName) {
		super(client, bucketName);
	}

	@Override
	public void deleteAll(String id, StorageEvent storageEvent) throws StorageException {
		List<S3ObjectSummary> s3ObjectSummaries = listObjects(id + "_");
		for (S3ObjectSummary objectSummary : s3ObjectSummaries) {
			String key = objectSummary.getKey();
			S3Object object = getObject(key);
			if (object != null)
				storageEvent.addDeletedKey(object);
			deleteObject(objectSummary);
		}
	}

}
