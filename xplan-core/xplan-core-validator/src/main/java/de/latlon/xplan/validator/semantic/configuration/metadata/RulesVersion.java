/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.semantic.configuration.metadata;

import org.apache.commons.lang3.StringUtils;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RulesVersion {

	private static final String UNKNOWN = "unbekannt";

	private final String version;

	private final String source;

	public RulesVersion() {
		this(null, null);
	}

	/**
	 * @param version the version of the rules, may be <code>null</code> if not known
	 * @param source the source of the rules, may be <code>null</code> if not known
	 */
	public RulesVersion(String version, String source) {
		this.version = StringUtils.isEmpty(version) ? UNKNOWN : version;
		this.source = StringUtils.isEmpty(source) ? UNKNOWN : source;
	}

	/**
	 * @return the version of the rules, 'unbekannt' if not known, never <code>null</code>
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @return the source of the rules, 'unbekannt' if not known, never <code>null</code>
	 */
	public String getSource() {
		return source;
	}

	@Override
	public String toString() {
		return "RulesVersion{" + "version='" + version + '\'' + ", source='" + source + '\'' + '}';
	}

}
