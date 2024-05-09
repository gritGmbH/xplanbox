/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.semantic.configuration;

import de.latlon.xplan.validator.semantic.configuration.message.DefaultRulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.message.RulesMessagesAccessor;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersion;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersionParser;
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
public abstract class SemanticRulesConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(SemanticRulesConfiguration.class);

	private final Path rulesPath;

	private RulesMetadata rulesMetadata;

	private RulesMessagesAccessor messagesAccessor;

	private List<String> resources;

	/**
	 * Rules will be parsed from a textfile containing a list of resources.
	 */
	public SemanticRulesConfiguration() {
		this(null);
	}

	/**
	 * Rules will be parsed from passed directory.
	 * @param rulesPath the directory containing the rules, never <code>null</code>
	 */
	public SemanticRulesConfiguration(Path rulesPath) {
		this.rulesPath = rulesPath;
	}

	/**
	 * @return the rulesMetadata of the rules, never <code>null</code>
	 */
	public RulesMetadata getRulesMetadata() {
		if (rulesMetadata == null) {
			rulesMetadata = createRulesMetadata();
		}
		return rulesMetadata;
	}

	/**
	 * @return the {@link RulesMessagesAccessor}, never <code>null</code>
	 */
	public RulesMessagesAccessor getRulesMessageAccessor() {
		if (messagesAccessor == null) {
			messagesAccessor = createMessagesAccessor();
		}
		return messagesAccessor;
	}

	/**
	 * @return the directory containing the rules, if rules are configured in filesystem
	 */
	public Optional<Path> getRulesPath() {
		return Optional.ofNullable(rulesPath);
	}

	/**
	 * @param resourceName name of the resource to return
	 * @return the resource with the passed name, if exists
	 */
	public Optional<String> getResource(String resourceName) {
		List<String> rulesList = getResources();
		return rulesList.stream().filter(resource -> resource.endsWith(resourceName)).findFirst();
	}

	/**
	 * @param fileSuffix
	 * @return all resources with the passed suffix, may be <code>empty</code>, but never
	 * <code>null</code>
	 */
	public List<String> getResources(String fileSuffix) {
		List<String> resources = getResources();
		return resources.stream().filter(resource -> resource.endsWith(fileSuffix)).collect(Collectors.toList());
	}

	/**
	 * creates the {@link RulesMessagesAccessor}, defaults to
	 * {@link DefaultRulesMessagesAccessor}
	 * @return never <code>null</code>
	 */
	protected RulesMessagesAccessor createMessagesAccessor() {
		return new DefaultRulesMessagesAccessor();
	}

	/**
	 * creates the {@link RulesMetadata}, per default only the RulesVersion are set
	 * @return never <code>null</code>
	 */
	protected RulesMetadata createRulesMetadata() {
		RulesVersionParser rulesVersionParser = new RulesVersionParser(this);
		RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion();
		return new RulesMetadata(rulesVersion);
	}

	/**
	 * @return the resources file as stream containing the list of resources, never
	 * <code>null</code>
	 */
	protected abstract InputStream getResourcesFile();

	private List<String> getResources() {
		if (resources == null) {
			try (InputStream xqueryregeln = getResourcesFile()) {
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
