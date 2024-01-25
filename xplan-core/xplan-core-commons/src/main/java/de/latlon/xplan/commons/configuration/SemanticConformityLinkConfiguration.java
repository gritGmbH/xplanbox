/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;

import de.latlon.xplan.commons.XPlanVersion;

/**
 * Contains the configuration of links to documents containing details about semantic
 * conformity.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 */
public class SemanticConformityLinkConfiguration {

	private final Map<XPlanVersion, String> versionToLink = new HashMap<>();

	/**
	 * Adds a new link to this {@link SemanticConformityLinkConfiguration}. If a
	 * configuration option with the same version was already added, the new one
	 * overwrites the existing.
	 * @param version the version of the plan, never <code>null</code>
	 * @param link the link to the document, may be <code>null</code> if not configured
	 * @throws NullPointerException if planType of version is <code>null</code>
	 */
	public void addLink(XPlanVersion version, String link) {
		if (version == null)
			throw new NullPointerException("version must never be null");
		versionToLink.put(version, link);
	}

	/**
	 * @param version the version of the plan, may be <code>null</code>
	 * @return
	 * @return the link assigned to the passed version, may be <code>null</code> if not
	 * configured
	 */
	public String retrieveLink(XPlanVersion version) {
		return versionToLink.get(version);
	}

	/**
	 * Logs the configuration on info level.
	 * @param log to log into, never <code>null</code>
	 */
	public void logConfiguration(Logger log) {
		log.info("  SemanticConformityLinkConfiguration");
		for (Entry<XPlanVersion, String> versionToLinkEntry : versionToLink.entrySet()) {
			XPlanVersion version = versionToLinkEntry.getKey();
			String link = versionToLinkEntry.getValue();
			log.info("   - {}: {}", version, link);
		}

	}

}
