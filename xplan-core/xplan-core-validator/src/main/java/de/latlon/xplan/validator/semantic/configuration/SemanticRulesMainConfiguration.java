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
package de.latlon.xplan.validator.semantic.configuration;

import java.io.InputStream;
import java.nio.file.Path;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0.1
 */
public class SemanticRulesMainConfiguration extends SemanticRulesConfiguration {

	/**
	 * Default rules will be parsed from /xqueryregeln.txt.
	 */
	public SemanticRulesMainConfiguration() {
		super();
	}

	/**
	 * Default rules will be parsed from passed directory.
	 * @param rulesPath the directory containing the rules, never <code>null</code>
	 */
	public SemanticRulesMainConfiguration(Path rulesPath) {
		super(rulesPath);
	}

	@Override
	protected InputStream getResourcesFile() {
		return getClass().getResourceAsStream("/xqueryregeln.txt");
	}

}
