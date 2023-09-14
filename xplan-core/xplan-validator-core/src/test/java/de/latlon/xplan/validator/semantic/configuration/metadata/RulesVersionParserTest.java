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

import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import org.junit.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
public class RulesVersionParserTest {

	@Test
	public void verifyThatParseVersionFromRulesDirectoryReturnsValue() throws URISyntaxException {
		Path rulesPath = Path
			.of(RulesVersionParserTest.class.getResource("/de/latlon/xplan/validator/configuration/rules").toURI());
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(rulesPath);
		RulesVersionParser rulesVersionParser = new RulesVersionParser(semanticRulesConfiguration);
		RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion();
		assertThat(rulesVersion.getVersion(), is("0.0.1"));
		assertThat(rulesVersion.getSource(), containsString("xplan-validator-core"));
	}

	@Test
	public void verifyThatParseVersionFromInternalRulesReturnsValue() {
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration();
		RulesVersionParser rulesVersionParser = new RulesVersionParser(semanticRulesConfiguration);
		RulesVersion rulesVersion = rulesVersionParser.parserRulesVersion();
		assertThat(rulesVersion.getVersion(), notNullValue());
		assertThat(rulesVersion.getSource(),
				containsString("https://gitlab.opencode.de/xleitstelle/xplanung/validierungsregeln/standard"));
	}

}
