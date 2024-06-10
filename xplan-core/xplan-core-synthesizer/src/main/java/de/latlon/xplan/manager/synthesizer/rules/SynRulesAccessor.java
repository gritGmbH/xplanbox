/*-
 * #%L
 * xplan-core-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer.rules;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.manager.dictionary.XPlanCodelists;
import de.latlon.xplan.manager.synthesizer.RuleParser;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SynRulesAccessor {

	private static final Logger LOG = LoggerFactory.getLogger(SynRulesAccessor.class);

	private final Path rulesDirectory;

	private final RuleParser ruleParser;

	private final XPlanCodelists externalCodelists;

	private final Map<XPlanVersion, Map<String, Expression>> synRulesPerVersion = new HashMap<>();

	public SynRulesAccessor() {
		this(null);
	}

	/**
	 * @param rulesDirectory the directory containing additional rules overwriting the
	 * internal rules, may be <code>null</code>
	 */
	public SynRulesAccessor(Path rulesDirectory) {
		this.rulesDirectory = rulesDirectory;
		this.externalCodelists = parseExternalCodelists(rulesDirectory);
		this.ruleParser = new RuleParser(this);
	}

	public Path getExternalConfigurationFile() {
		return rulesDirectory;
	}

	public XPlanCodelists getExternalCodelists() {
		return externalCodelists;
	}

	public Expression getExpression(XPlanVersion xPlanVersion, String propertyPath) {
		Map<String, Expression> expressions = parseRules(xPlanVersion);
		return expressions.get(propertyPath);
	}

	private XPlanCodelists parseExternalCodelists(Path rulesDirectory) {
		try {
			return new XPlanCodelists(rulesDirectory);
		}
		catch (IOException | XMLStreamException e) {
			throw new RuntimeException("Die extern konfigurierten Codelisten konnten nicht geparst werden", e);
		}
	}

	private Map<String, Expression> parseRules(XPlanVersion xPlanVersion) {
		if (!synRulesPerVersion.containsKey(xPlanVersion)) {
			parseAndAddSynRules(xPlanVersion);
		}
		return synRulesPerVersion.get(xPlanVersion);
	}

	private synchronized void parseAndAddSynRules(XPlanVersion xPlanVersion) {
		Map<String, Expression> synRules = new HashMap<>();
		String rulesFileName = detectRulesFileName(xPlanVersion);
		InputStream rulesFromClasspath = retrieveRulesFileFromClasspath(rulesFileName);
		parseRules(synRules, rulesFromClasspath);
		InputStream rulesFromFileSystem = retrieveRulesFileFromFileSystem(rulesFileName);
		if (rulesFromFileSystem != null)
			parseRules(synRules, rulesFromFileSystem);
		synRulesPerVersion.put(xPlanVersion, synRules);
	}

	private void parseRules(Map<String, Expression> synRules, InputStream is) {
		try {
			for (String line : IOUtils.readLines(is, Charset.defaultCharset())) {
				if (!line.startsWith("#") && !"".equals(line.trim())) {
					int firstEquals = line.indexOf("=");
					String propertyPath = line.substring(0, firstEquals);
					Expression expression = ruleParser.parse(line.substring(firstEquals + 1));
					synRules.put(propertyPath, expression);
				}
			}
		}
		finally {
			closeQuietly(is, null);
		}
	}

	private InputStream retrieveRulesFileFromFileSystem(String rulesFileName) {
		if (rulesDirectory != null) {
			Path rulesFile = rulesDirectory.resolve(rulesFileName);
			LOG.info("Read additional/overwriting rules from directory: {}", rulesFile);
			if (Files.exists(rulesFile)) {
				try {
					return Files.newInputStream(rulesFile);
				}
				catch (IOException e) {
					LOG.info("Could not read rules in configuration directory.");
				}
			}
			LOG.info("Could not find rules in configuration directory.");
		}
		return null;
	}

	private InputStream retrieveRulesFileFromClasspath(String rulesFileName) {
		String rulesResource = "/rules/" + rulesFileName;
		LOG.info("Read rules from internal directory: {}", rulesResource);
		return XPlanSynthesizer.class.getResourceAsStream(rulesResource);
	}

	private String detectRulesFileName(XPlanVersion version) {
		String synRulesFileName = version.getSynRulesFileName();
		if (synRulesFileName == null) {
			throw new IllegalArgumentException("Could not find rules file for XPlan version " + version);
		}
		return synRulesFileName;
	}

}
