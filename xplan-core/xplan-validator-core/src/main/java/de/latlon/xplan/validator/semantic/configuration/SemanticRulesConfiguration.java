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

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0.1
 */
public class SemanticRulesConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(SemanticRulesConfiguration.class);

	private final Path rulesPath;

	private List<String> resources;

	public SemanticRulesConfiguration() {
		this(null);
	}

	public SemanticRulesConfiguration(Path rulesPath) {
		this.rulesPath = rulesPath;
	}

	public Optional<Path> getRulesPath() {
		return Optional.ofNullable(rulesPath);
	}

	public Optional<String> getResource(String resourceName) {
		List<String> rulesList = getResources();
		return rulesList.stream().filter(resource -> resource.endsWith(resourceName)).findFirst();
	}

	public List<String> getResources(String fileSuffix) {
		List<String> resources = getResources();
		return resources.stream().filter(resource -> resource.endsWith(fileSuffix)).collect(Collectors.toList());
	}

	private List<String> getResources() {
		if (resources == null) {
			try (InputStream xqueryregeln = getClass().getResourceAsStream("/xqueryregeln.txt")) {
				if (xqueryregeln != null)
					resources = IOUtils.readLines(xqueryregeln, StandardCharsets.UTF_8);
				else
					resources = Collections.emptyList();
			}
			catch (IOException e) {
				LOG.error("xqueryregeln.txt could not be read");
			}
		}
		return resources;
	}

}
