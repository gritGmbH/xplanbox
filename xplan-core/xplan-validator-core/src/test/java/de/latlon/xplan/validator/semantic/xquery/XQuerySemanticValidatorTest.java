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
package de.latlon.xplan.validator.semantic.xquery;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.configuration.SemanticConformityLinkConfiguration;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.configuration.SemanticRulesConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for <link>XQuerySemanticValidator</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
@RunWith(JUnitParamsRunner.class)
public class XQuerySemanticValidatorTest {

	@Parameters({ "xplan41/BP2070.zip,10 ", "xplan50/BP2070.zip,7", "xplan51/BP2070.zip,7" })
	@Test
	public void testValidateSemantic(String testResource, int expectedNumberOfRules) throws Exception {
		Path xqueryFilePath = pathToSampleRules();
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(xqueryFilePath);
		XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration);
		SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(retriever);
		ValidatorResult result = xQuerySemanticValidator.validateSemantic(getTestArchive(testResource),
				Collections.emptyList());
		SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

		assertThat(semanticValidatorResult.getRules().size(), is(expectedNumberOfRules));
	}

	@Test
	public void testValidatorResultContainsDetailsHint() throws Exception {
		Path xqueryFilePath = pathToSampleRules();
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(xqueryFilePath);
		XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration);
		SemanticConformityLinkConfiguration linkConfig = new SemanticConformityLinkConfiguration();
		linkConfig.addLink(XPLAN_41, "link");
		SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(retriever, linkConfig);
		ValidatorResult result = xQuerySemanticValidator.validateSemantic(getTestArchive("xplan41/BP2070.zip"),
				Collections.emptyList());
		SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

		assertThat(semanticValidatorResult.getValidatorDetail().getDetailsString(), is(notNullValue()));
		assertThat(semanticValidatorResult.getValidatorDetail().getLink(), is("link"));
	}

	@Test
	public void testValidatorResultWithoutLinkNotContainsDetailsHint() throws Exception {
		Path xqueryFilePath = pathToSampleRules();
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(xqueryFilePath);
		XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration);
		SemanticConformityLinkConfiguration linkConfig = new SemanticConformityLinkConfiguration();
		linkConfig.addLink(XPLAN_40, "link");
		SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(retriever, linkConfig);
		ValidatorResult result = xQuerySemanticValidator.validateSemantic(getTestArchive("xplan41/BP2070.zip"),
				Collections.emptyList());
		SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

		assertThat(semanticValidatorResult.getValidatorDetail(), is(nullValue()));
	}

	@Test
	public void testValidatorResultWithoutLinkConfigNotContainsDetailsHint() throws Exception {
		Path xqueryFilePath = pathToSampleRules();
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(xqueryFilePath);
		XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration);
		SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(retriever);
		ValidatorResult result = xQuerySemanticValidator.validateSemantic(getTestArchive("xplan41/BP2070.zip"),
				Collections.emptyList());
		SemanticValidatorResult semanticValidatorResult = (SemanticValidatorResult) result;

		assertThat(semanticValidatorResult.getValidatorDetail(), is(nullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateSemanticWithNullArchiveShouldFail() throws Exception {
		Path xqueryFilePath = pathToSampleRules();
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(xqueryFilePath);
		XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration);
		SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(retriever);
		xQuerySemanticValidator.validateSemantic(null, Collections.emptyList());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateSemanticWithOptionsArchiveShouldFail() throws Exception {
		Path xqueryFilePath = pathToSampleRules();
		SemanticRulesConfiguration semanticRulesConfiguration = new SemanticRulesConfiguration(xqueryFilePath);
		XQuerySemanticValidatorConfigurationRetriever retriever = new XQuerySemanticValidatorConfigurationRetriever(
				semanticRulesConfiguration);
		SemanticValidator xQuerySemanticValidator = new XQuerySemanticValidator(retriever);
		xQuerySemanticValidator.validateSemantic(getTestArchive("xplan41/BP2070.zip"), null);
	}

	private XPlanArchive getTestArchive(String name) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

	private Path pathToSampleRules() throws URISyntaxException {
		return Paths.get(XQuerySemanticValidatorTest.class.getResource("../configuration/xquery/rules").toURI());
	}

}
