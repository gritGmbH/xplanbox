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
package de.latlon.xplan.validator.semantic.configuration.xquery;

import de.latlon.xplan.validator.semantic.SemanticValidatorRule;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersion;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesVersionParser;
import org.junit.Test;

import java.nio.file.Path;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_SO;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_XP;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.NONE;
import static java.nio.file.Paths.get;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests for <link>XQuerySemanticValidatorConfigurationRetriever</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorConfigurationRetrieverTest {

	@Test
	public void testRetrieveConfigurationShouldReturnCorrectNumberOfRules() throws Exception {
		Path rulesPath = get(XQuerySemanticValidatorConfigurationRetriever.class.getResource("rules").toURI());
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(rulesPath);
		XQuerySemanticValidatorConfigurationRetriever configurationRetriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration, rulesMatadata(rulesPath));
		SemanticValidatorConfiguration configuration = configurationRetriever.retrieveConfiguration();
		List<SemanticValidatorRule> rules = configuration.getAllRules();

		assertThat(rules.size(), is(11));
	}

	@Test
	public void testRetrieveConfigurationShouldRetrieveAllFilesRecursively() throws Exception {
		Path rulesPath = get(XQuerySemanticValidatorConfigurationRetriever.class.getResource("rules").toURI());
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(rulesPath);
		XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration, rulesMatadata(rulesPath));
		SemanticValidatorConfiguration configuration = retriever.retrieveConfiguration();

		assertThat(configuration.getRules(singletonList(IGNORE_XP)).size(), is(9));
		assertThat(configuration.getRules(singletonList(IGNORE_SO)).size(), is(10));
		assertThat(configuration.getRules(XPLAN_40, singletonList(NONE)).size(), is(8));
	}

	@Test
	public void testRetrieveConfigurationFromJar() throws Exception {
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration();
		XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration, new RulesMetadata(new RulesVersion()));
		SemanticValidatorConfiguration configuration = retriever.retrieveConfiguration();

		assertThat(configuration.getRules(XPLAN_40, singletonList(NONE)).size(), is(not(0)));
	}

	@Test
	public void testRetrieveConfigurationWithInvalidRulesPath() throws Exception {
		Path rulesPath = get("/does/not/exist");
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(rulesPath);
		XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration, rulesMatadata(rulesPath));
		SemanticValidatorConfiguration configuration = retriever.retrieveConfiguration();

		assertThat(configuration.getAllRules().size(), is(not(0)));
	}

	private RulesMetadata rulesMatadata(Path rulesPath) {
		RulesVersionParser rulesVersionParser = new RulesVersionParser();
		RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion(rulesPath);
		return new RulesMetadata(rulesVersion);

	}

}
