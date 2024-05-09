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
package de.latlon.xplan.validator.semantic.report;

import de.latlon.xplan.validator.report.ReportUtils.SkipCode;
import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorResult;
import de.latlon.xplan.validator.semantic.configuration.metadata.RulesMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;

/**
 * contains the validator result of the semantic validator
 *
 * @author Florian Bingel
 */
public class SemanticValidatorResult extends ValidatorResult {

	private static final String VALIDATION_TYPE_NAME = getMessage("validationType_semantic");

	private final List<RuleResult> rules = new ArrayList<>();

	private RulesMetadata rulesMetadata;

	public SemanticValidatorResult(SkipCode skipCode) {
		super(skipCode);
	}

	/**
	 * Instantiates a new {@link SemanticValidatorResult} without detailsHint.
	 */
	public SemanticValidatorResult() {
		this((ValidatorDetail) null);
	}

	/**
	 * @param detail some details about the validation, may be <code>null</code>
	 */
	public SemanticValidatorResult(ValidatorDetail detail) {
		super(detail);
	}

	@Override
	public String getType() {
		return VALIDATION_TYPE_NAME;
	}

	/**
	 * Creates a new {@link RuleResult} from the passed values and added them to the list
	 * of rules.
	 * @param name the name of the rule, should not be <code>null</code>
	 * @param defaultMessage defaultMessage of the rule, if missing in
	 * InvalidFeatureResult
	 * @param invalidFeaturesResults list of features with errors or warnings,
	 * @return
	 */
	public boolean addRule(String name, String defaultMessage, List<InvalidFeaturesResult> invalidFeaturesResults) {
		RuleResult ruleResult = new RuleResult(name, defaultMessage, invalidFeaturesResults);
		rules.add(ruleResult);
		return ruleResult.isValid();
	}

	/**
	 * @return all {@link RuleResult}s.
	 */
	public List<RuleResult> getRules() {
		return rules.stream().sorted().collect(Collectors.toList());
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

	@Override
	public String toString() {
		return "SemanticValidatorResult{" + "rules=" + rules + '}';
	}

}
