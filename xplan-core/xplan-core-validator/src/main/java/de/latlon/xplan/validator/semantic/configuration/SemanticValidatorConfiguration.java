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

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.semantic.SemanticValidatorRule;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;
import static java.util.Collections.unmodifiableList;

/**
 * Encapsulates a configuration for the semantic validator
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class SemanticValidatorConfiguration {

	private final List<SemanticValidatorRule> rules = new ArrayList<>();

	private RulesMetadata rulesMetadata;

	/**
	 * Retrieve all rules as unmodifiable <link>List</link>
	 * @return an unmodifiable <link>List</link> of <link>SemanticValidatorRule</link>
	 * encapsulated in this configuration
	 */
	public List<SemanticValidatorRule> getAllRules() {
		return unmodifiableList(rules);
	}

	/**
	 * Retrieve all rules matching the passed filter parameters in a unmodifiable
	 * <link>List</link>
	 * @param version the version the rules should be applied to, never <code>null</code>
	 * @param validationOptionsToIgnore the validation options to ignore, may be empty if
	 * all rules should be applied, but never <code>null</code>
	 * @return an unmodifiable <link>List</link> of <link>SemanticValidatorRule</link>
	 * matching the filter parameters
	 * @throws IllegalArgumentException if one of the parameters is <code>null</code>
	 */
	public List<SemanticValidatorRule> getRules(XPlanVersion version,
			List<SemanticValidationOptions> validationOptionsToIgnore) {
		checkParameters(version, validationOptionsToIgnore);
		List<SemanticValidatorRule> matchingRules = new ArrayList<>();
		for (SemanticValidatorRule rule : rules) {
			if (isCorrectVersion(version, rule) && isNotIgnored(rule, validationOptionsToIgnore)) {
				matchingRules.add(rule);
			}
		}
		return unmodifiableList(matchingRules);
	}

	/**
	 * Retrieve all rules matching the passed filter parameter in a unmodifiable
	 * <link>List</link>
	 * @param validationOptionsToIgnore the validation options to ignore, may be empty if
	 * all rules should be applied, but never <code>null</code>
	 * @return an unmodifiable <link>List</link> of <link>SemanticValidatorRule</link>
	 * matching the filter parameters
	 */
	public List<SemanticValidatorRule> getRules(List<SemanticValidationOptions> validationOptionsToIgnore) {
		List<SemanticValidatorRule> matchingRules = new ArrayList<>();
		for (SemanticValidatorRule rule : rules) {
			if (isNotIgnored(rule, validationOptionsToIgnore)) {
				matchingRules.add(rule);
			}
		}
		return unmodifiableList(matchingRules);
	}

	/**
	 * add a <link>SemanticValidatorRule</link>
	 * @param rule never <code>null</code>
	 */
	public void addRule(SemanticValidatorRule rule) {
		rules.add(rule);
	}

	/**
	 * add multiple <link>SemanticValidatorRule</link>
	 * @param rules none <code>null</code>
	 */
	public void addRules(SemanticValidatorRule[] rules) {
		addAll(this.rules, rules);
	}

	/**
	 * @param rulesMetadata may be <code>null</code>
	 */
	public void setRulesMetadata(RulesMetadata rulesMetadata) {
		this.rulesMetadata = rulesMetadata;
	}

	/**
	 * @return the {@link RulesMetadata}, may be <code>null</code>
	 */
	public RulesMetadata getRulesMetadata() {
		return rulesMetadata;
	}

	private boolean isCorrectVersion(XPlanVersion version, SemanticValidatorRule rule) {
		XPlanVersion ruleVersion = rule.getXPlanVersion();
		return ruleVersion == null || version.equals(ruleVersion);
	}

	private boolean isNotIgnored(SemanticValidatorRule rule,
			List<SemanticValidationOptions> validationOptionsToIgnore) {
		for (SemanticValidationOptions validationOption : validationOptionsToIgnore) {
			if (rule.isIgnoredByOption(validationOption)) {
				return false;
			}
		}
		return true;
	}

	private void checkParameters(XPlanVersion version, List<SemanticValidationOptions> validationOptionsToIgnore) {
		if (version == null)
			throw new IllegalArgumentException("XPlanVersion must not be null");
		if (validationOptionsToIgnore == null)
			throw new IllegalArgumentException("ValidationOptions must not be null");
	}

}
