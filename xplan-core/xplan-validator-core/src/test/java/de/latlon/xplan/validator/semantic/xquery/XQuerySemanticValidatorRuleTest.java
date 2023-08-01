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
import de.latlon.xplan.validator.semantic.report.InvalidFeaturesResult;
import net.sf.saxon.trans.XPathException;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_SO;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.IGNORE_XP;
import static de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions.NONE;
import static de.latlon.xplan.validator.semantic.report.ValidationResultType.ERROR;
import static de.latlon.xplan.validator.semantic.report.ValidationResultType.WARNING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests for <link>XQuerySemanticValidatorRule</link>
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidatorRuleTest {

	@Test
	public void testValidRuleShouldReturnTrue() throws Exception {
		ByteArrayInputStream byteArrayInputStream = getStream();
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(byteArrayInputStream, "name",
				XPLAN_41, NONE, "message");
		List<InvalidFeaturesResult> invalidFeatures = validatorRule.validate(retrieveArchive("xplan41/BP2070.zip"));
		assertThat(invalidFeatures.size(), is(0));
	}

	@Test
	public void testValidRuleSelectingMultipleGmlIdsShouldReturnTrue() throws Exception {
		InputStream xqery = XQuerySemanticValidatorRuleTest.class
			.getResourceAsStream("../configuration/xquery/rules/gmlIds.xq");
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(xqery, "name", XPLAN_41, NONE,
				"message");
		List<InvalidFeaturesResult> invalidFeatures = validatorRule.validate(retrieveArchive("xplan41/BP2070.zip"));
		assertThat(invalidFeatures.size(), is(1));
	}

	@Test
	public void testValidRuleSelectingOneGmlIdShouldReturnTrue() throws Exception {
		InputStream xqery = XQuerySemanticValidatorRuleTest.class
			.getResourceAsStream("../configuration/xquery/rules/gmlId.xq");
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(xqery, "name", XPLAN_41, NONE,
				"message");
		List<InvalidFeaturesResult> invalidFeatures = validatorRule.validate(retrieveArchive("xplan41/BP2070.zip"));
		assertThat(invalidFeatures.size(), is(1));
	}

	@Test
	public void testValidRuleSelectingWarningsAndErrors() throws Exception {
		InputStream xqery = XQuerySemanticValidatorRuleTest.class
			.getResourceAsStream("../configuration/xquery/rules/gmlId-warningsAndErrors.xq");
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(xqery, "name", XPLAN_41, NONE,
				"message");
		List<InvalidFeaturesResult> invalidFeatures = validatorRule.validate(retrieveArchive("xplan41/BP2070.zip"));
		assertThat(invalidFeatures.size(), is(2));
		assertThat(invalidFeatures.stream().filter(invalidFeature -> invalidFeature.getResultType() == WARNING).count(),
				is(1l));
		assertThat(invalidFeatures.stream().filter(invalidFeature -> invalidFeature.getResultType() == ERROR).count(),
				is(1l));
	}

	@Test
	public void testNonMatchingRuleShouldReturnFalse() throws Exception {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("exists(/notThere)".getBytes());
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(byteArrayInputStream, "name",
				XPLAN_41, NONE, "message");
		List<InvalidFeaturesResult> invalidFeatures = validatorRule.validate(retrieveArchive("xplan41/BP2070.zip"));
		assertThat(invalidFeatures.size(), is(1));
	}

	@Test(expected = XPathException.class)
	public void testDefectedRuleShouldThrowException() throws Exception {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("<<".getBytes());
		(new XQuerySemanticValidatorRule(byteArrayInputStream, "name", XPLAN_41, NONE, "message"))
			.validate(retrieveArchive("xplan41/BP2070.zip"));
	}

	@Test
	public void testIsIgnoredByOptionShouldReturnTrue() throws Exception {
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(getStream(), "name", XPLAN_41,
				IGNORE_SO, "message");
		boolean ignoredByOption = validatorRule.isIgnoredByOption(IGNORE_SO);
		assertThat(ignoredByOption, is(true));
	}

	@Test
	public void testIsIgnoredByOptionShouldReturnFalse() throws Exception {
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(getStream(), "name", XPLAN_41,
				IGNORE_XP, "message");
		boolean ignoredByOption = validatorRule.isIgnoredByOption(IGNORE_SO);
		assertThat(ignoredByOption, is(false));
	}

	@Test
	public void testIsIgnoredByOptionNoneShouldReturnFalse() throws Exception {
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(getStream(), "name", XPLAN_41, NONE,
				"message");
		boolean ignoredByOption = validatorRule.isIgnoredByOption(IGNORE_SO);
		assertThat(ignoredByOption, is(false));
	}

	@Test
	public void testIsIgnoredByOptionWithNoneShouldReturnFalse() throws Exception {
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(getStream(), "name", XPLAN_41,
				IGNORE_SO, "message");
		boolean ignoredByOption = validatorRule.isIgnoredByOption(NONE);
		assertThat(ignoredByOption, is(false));
	}

	@Test
	public void testIsIgnoredByOptionWithNullShouldReturnFalse() throws Exception {
		XQuerySemanticValidatorRule validatorRule = new XQuerySemanticValidatorRule(getStream(), "name", XPLAN_41,
				IGNORE_SO, "message");
		boolean ignoredByOption = validatorRule.isIgnoredByOption(null);
		assertThat(ignoredByOption, is(false));
	}

	private ByteArrayInputStream getStream() {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("exists(/)".getBytes());
		return byteArrayInputStream;
	}

	private XPlanArchive retrieveArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

}
