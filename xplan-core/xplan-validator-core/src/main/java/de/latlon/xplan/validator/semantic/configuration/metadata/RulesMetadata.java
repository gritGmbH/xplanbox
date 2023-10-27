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

import org.apache.commons.lang.StringUtils;

import java.util.Objects;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class RulesMetadata {

	private static final String UNKNOWN = "unbekannt";

	private final String id;

	private final String name;

	private final String description;

	private final String version;

	private final String source;

	/**
	 * @param rulesVersion containing the version and source, never <code>null</code>
	 */
	public RulesMetadata(RulesVersion rulesVersion) {
		this(null, null, null, rulesVersion.getVersion(), rulesVersion.getSource());
	}

	/**
	 * @param id id of the rules (the profile), may be <code>null</code>
	 * @param name name of the rules (the profile), may be <code>null</code>
	 * @param description description of the rules (e.g. the profile), may be
	 * <code>null</code>
	 * @param rulesVersion containing the version and source, never <code>null</code>
	 */
	public RulesMetadata(String id, String name, String description, RulesVersion rulesVersion) {
		this(id, name, description, rulesVersion.getVersion(), rulesVersion.getSource());
	}

	/**
	 * @param id id of the rules (the profile), may be <code>null</code>
	 * @param name name of the rules (the profile), may be <code>null</code>
	 * @param description description of the rules (e.g. the profile), may be
	 * <code>null</code>
	 * @param version the version of the rules, may be <code>null</code> if not known
	 * @param source the source of the rules, may be <code>null</code> if not known
	 */
	public RulesMetadata(String id, String name, String description, String version, String source) {
		this.name = name;
		this.description = description;
		this.version = StringUtils.isEmpty(version) ? UNKNOWN : version;
		this.source = StringUtils.isEmpty(source) ? UNKNOWN : source;
		if (id == null)
			this.id = createIdFromHashCode();
		else {
			this.id = id;
		}
	}

	/**
	 * @return the id of the rules, may be <code>null</code> if the rules metadata has no
	 * name
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the name of the rules, may be <code>null</code>
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the description of the rules, may be <code>null</code>
	 */
	public String getDescription() {
		return description;
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

	private String createIdFromHashCode() {
		return Integer.toString(Objects.hash(getName(), getDescription()));
	}

}
