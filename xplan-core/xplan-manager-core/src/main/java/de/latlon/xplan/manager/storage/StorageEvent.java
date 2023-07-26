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
package de.latlon.xplan.manager.storage;

import de.latlon.xplan.manager.storage.s3.S3Object;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class StorageEvent {

	private final List<String> insertedKeys = new ArrayList<>();

	private final List<S3Object> deletedKeysToObjects = new ArrayList<>();

	private final List<Path> storedPaths = new ArrayList<>();

	private final Map<Path, byte[]> deletedPathsToObjects = new HashMap<>();

	public void addInsertedKey(String key) {
		insertedKeys.add(key);
	}

	public List<String> getInsertedKeys() {
		return insertedKeys;
	}

	public void addDeletedKey(S3Object s3Object) {
		deletedKeysToObjects.add(s3Object);
	}

	public List<S3Object> getDeletedS3Objects() {
		return deletedKeysToObjects;
	}

	public void addStoredPath(Path path) {
		storedPaths.add(path);
	}

	public List<Path> getInsertedPaths() {
		return storedPaths;
	}

	public void addDeletedPath(Path path, byte[] objectContent) {
		deletedPathsToObjects.put(path, objectContent);
	}

	public Map<Path, byte[]> getDeletedPathsToObjects() {
		return deletedPathsToObjects;
	}

}
