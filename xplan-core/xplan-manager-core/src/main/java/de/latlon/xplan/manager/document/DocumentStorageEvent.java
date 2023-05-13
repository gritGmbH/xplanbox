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
package de.latlon.xplan.manager.document;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class DocumentStorageEvent {

	private final List<String> insertedKeys = new ArrayList<>();

	private final Map<String, InputStream> deletedKeysToObjects = new HashMap<>();

	public void addInsertedKey(String key) {
		insertedKeys.add(key);
	}

	public List<String> getInsertedKeys() {
		return insertedKeys;
	}

	public void addRemovedDocument(String key, InputStream objectContent) {
		deletedKeysToObjects.put(key, objectContent);
	}

	public Map<String, InputStream> getDeletedKeysToObjects() {
		return deletedKeysToObjects;
	}

}
