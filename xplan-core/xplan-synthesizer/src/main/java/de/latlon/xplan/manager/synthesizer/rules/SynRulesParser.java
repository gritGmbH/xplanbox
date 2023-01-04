/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
import de.latlon.xplan.manager.synthesizer.RuleParser;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.synthesizer.expression.Expression;
import org.apache.commons.collections4.map.MultiKeyMap;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.apache.commons.io.IOUtils.closeQuietly;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class SynRulesParser {

	private static final Logger LOG = LoggerFactory.getLogger(SynRulesParser.class);

	private final Path rulesDirectory;

	private final RuleParser ruleParser;

	public SynRulesParser() {
		this(null);
	}

	/**
	 * @param rulesDirectory the directory containing additional rules overwriting the
	 * internal rules, may be <code>null</code>
	 */
	public SynRulesParser(Path rulesDirectory) {
		this.rulesDirectory = rulesDirectory;
		this.ruleParser = new RuleParser(rulesDirectory);
	}

	public MultiKeyMap parseRules() {
		MultiKeyMap synRules = new MultiKeyMap();
		Arrays.stream(XPlanVersion.values()).filter(xPlanVersion -> xPlanVersion.getSynRulesFileName() != null)
				.forEach(xPlanVersion -> {
					String rulesFileName = xPlanVersion.getSynRulesFileName();
					InputStream rulesFromClasspath = retrieveRulesFileFromClasspath(rulesFileName);
					parseRules(synRules, xPlanVersion, rulesFromClasspath);
					InputStream rulesFromFileSystem = retrieveRulesFileFromFileSystem(rulesFileName);
					if (rulesFromFileSystem != null)
						parseRules(synRules, xPlanVersion, rulesFromFileSystem);
				});
		return synRules;
	}

	private void parseRules(MultiKeyMap synRules, XPlanVersion xPlanVersion, InputStream is) {
		try {
			for (String line : IOUtils.readLines(is)) {
				if (!line.startsWith("#") && !"".equals(line.trim())) {
					int firstEquals = line.indexOf("=");
					String propertyPath = line.substring(0, firstEquals);
					Expression expression = ruleParser.parse(line.substring(firstEquals + 1));
					synRules.put(xPlanVersion, propertyPath, expression);
				}
			}
		}
		catch (IOException e) {
			throw new RuntimeException("Error while reading the rules file ", e);
		}
		finally {
			closeQuietly(is);
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

}
