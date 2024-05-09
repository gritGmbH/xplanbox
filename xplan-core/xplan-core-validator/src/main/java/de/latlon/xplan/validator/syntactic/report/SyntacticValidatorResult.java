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
package de.latlon.xplan.validator.syntactic.report;

import de.latlon.xplan.validator.report.ValidatorDetail;
import de.latlon.xplan.validator.report.ValidatorResult;

import java.util.List;

import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;

/**
 * contains the validator result of the syntactic validator
 *
 * @author Florian Bingel
 */
public class SyntacticValidatorResult extends ValidatorResult {

	private static final String VALIDATION_TYPE_NAME = getMessage("validationType_syntactic");

	private final List<String> messages;

	/**
	 * @param messages list of messages describing validation errors, may be empty but
	 * never <code>null</code>
	 * @param isValid <code>true</code> if the xplan is syntactical valid,
	 * <code>false</code> otherwise
	 * @param validatorDetails details about the validation, mey be <code>null</code>
	 */
	public SyntacticValidatorResult(List<String> messages, boolean isValid, ValidatorDetail validatorDetails) {
		super(isValid, validatorDetails);
		this.messages = messages;
	}

	/**
	 * @return list of messages describing validation errors, may be empty but never
	 * <code>null</code>
	 */
	public List<String> getMessages() {
		return messages;
	}

	@Override
	public String getType() {
		return VALIDATION_TYPE_NAME;
	}

	@Override
	public String toString() {
		return "SyntacticValidatorResult{" + "messages=" + messages + '}';
	}

}
