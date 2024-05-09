/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.hale;

import java.util.HashMap;
import java.util.Map;

/**
 * Encapsulates an ImportReader or ImportWriter with settings.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class HaleIOProvider {

	private final String name;

	private final Map<String, String> settings = new HashMap<>();

	/**
	 * @param name the name of the ImportReader or ImportWriter (ID-of-target-writer),
	 * never <code>null</code>
	 */
	public HaleIOProvider(String name) {
		this.name = name;
	}

	/**
	 * Adds a new setting
	 * @param key of the setting, never <code>null</code>
	 * @param value of the setting, never <code>null</code>
	 */
	public void addSetting(String key, String value) {
		settings.put(key, value);
	}

	/**
	 * @return the name of the ImportReader or ImportWriter (ID-of-target-writer), never
	 * <code>null</code>
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return additional settings, may be empty but never <code>null</code>
	 */
	public Map<String, String> getSettings() {
		return settings;
	}

}
