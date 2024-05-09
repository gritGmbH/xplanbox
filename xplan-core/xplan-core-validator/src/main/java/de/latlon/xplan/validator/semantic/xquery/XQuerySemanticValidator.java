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
package de.latlon.xplan.validator.semantic.xquery;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.SemanticValidableXPlanArchive;
import de.latlon.xplan.commons.configuration.SemanticConformityLinkConfiguration;
import de.latlon.xplan.manager.web.shared.ConfigurationException;
import de.latlon.xplan.validator.ValidatorException;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.SemanticValidator;
import de.latlon.xplan.validator.semantic.SemanticValidatorRule;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidationOptions;
import de.latlon.xplan.validator.semantic.configuration.SemanticValidatorConfiguration;
import de.latlon.xplan.validator.semantic.configuration.xquery.XQuerySemanticValidatorConfigurationRetriever;
import de.latlon.xplan.validator.semantic.report.InvalidFeaturesResult;
import de.latlon.xplan.validator.semantic.report.SemanticValidatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;
import static java.lang.String.format;

/**
 * Validates <link>XPlanArchives</link> semantically using XQuery The file path must be
 * set and the validator be built before usage
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class XQuerySemanticValidator implements SemanticValidator {

	private static final Logger LOG = LoggerFactory.getLogger(XQuerySemanticValidator.class);

	private final SemanticValidatorConfiguration semanticValidatorConfiguration;

	private final SemanticConformityLinkConfiguration semanticConformityLinkConfiguration;

	/**
	 * Builds the validator.
	 * @param retriever retrieves the configuration for this validator
	 * @throws ValidatorException
	 */
	public XQuerySemanticValidator(XQuerySemanticValidatorConfigurationRetriever retriever)
			throws ConfigurationException {
		this(retriever, null);
	}

	/**
	 * Builds the validator.
	 * @param retriever retrieves the configuration for this validator
	 * @param semanticConformityLinkConfiguration, encapsulates the configuration of the
	 * semantic validator links, may be <code>null</code>
	 * @throws ValidatorException
	 */
	public XQuerySemanticValidator(XQuerySemanticValidatorConfigurationRetriever retriever,
			SemanticConformityLinkConfiguration semanticConformityLinkConfiguration) throws ConfigurationException {
		this.semanticConformityLinkConfiguration = semanticConformityLinkConfiguration;
		try {
			semanticValidatorConfiguration = retriever.retrieveConfiguration();
		}
		catch (IOException e) {
			LOG.error("Could not instantiate semantic validator. Reason: {}", e.getMessage());
			throw new ConfigurationException("Could not create validator.", e);
		}
	}

	@Override
	public ValidatorResult validateSemantic(SemanticValidableXPlanArchive archive,
			List<SemanticValidationOptions> semanticValidationOptions) {
		checkParameters(archive, semanticValidationOptions);
		ValidatorDetail detail = createDetail(archive);
		SemanticValidatorResult validatorResult = new SemanticValidatorResult(detail);
		validatorResult.setRulesMetadata(semanticValidatorConfiguration.getRulesMetadata());
		boolean isArchiveValid = true;
		List<SemanticValidatorRule> rulesToApply = retrieveRulesToApply(archive, semanticValidationOptions);
		LOG.info("Number of rules to apply: {}", rulesToApply.size());
		for (SemanticValidatorRule semanticValidatorRule : rulesToApply) {
			boolean isThisRuleValid = validateRule(archive, validatorResult, semanticValidatorRule);
			isArchiveValid = isArchiveValid && isThisRuleValid;
			LOG.debug(format("Rule %s is %s valid", semanticValidatorRule.getName(), !isThisRuleValid ? "not" : ""));
		}
		validatorResult.setValid(isArchiveValid);
		return validatorResult;
	}

	private List<SemanticValidatorRule> retrieveRulesToApply(SemanticValidableXPlanArchive archive,
			List<SemanticValidationOptions> semanticValidationOptions) {
		XPlanVersion version = archive.getVersion();
		LOG.debug("Find all rules for version {} and options {}.", version, semanticValidationOptions);
		return semanticValidatorConfiguration.getRules(version, semanticValidationOptions);
	}

	private boolean validateRule(SemanticValidableXPlanArchive archive, SemanticValidatorResult result,
			SemanticValidatorRule semanticValidatorRule) {
		String name = semanticValidatorRule.getName();
		try {
			List<InvalidFeaturesResult> invalidFeatures = semanticValidatorRule.validate(archive);
			String message = semanticValidatorRule.getMessage();
			return result.addRule(name, message, invalidFeatures);
		}
		catch (ValidatorException e) {
			LOG.error("Error while semantically validating validation rule " + name, e);
		}
		return false;
	}

	private ValidatorDetail createDetail(SemanticValidableXPlanArchive archive) {
		if (semanticConformityLinkConfiguration != null) {
			String link = semanticConformityLinkConfiguration.retrieveLink(archive.getVersion());
			if (link != null && !"".equals(link)) {
				return new ValidatorDetail(getMessage("XQuerySemanticValidator_linkKonformitaetsbedingungen"), link);
			}
		}
		return null;
	}

	private void checkParameters(SemanticValidableXPlanArchive archive,
			List<SemanticValidationOptions> semanticValidationOptions) {
		if (archive == null)
			throw new IllegalArgumentException("archive must not be null");
		if (semanticValidationOptions == null)
			throw new IllegalArgumentException("options must not be null");
	}

}
