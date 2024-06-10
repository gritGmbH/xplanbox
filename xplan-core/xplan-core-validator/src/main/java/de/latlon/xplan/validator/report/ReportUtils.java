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
package de.latlon.xplan.validator.report;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.validator.i18n.ValidationMessages;

import static de.latlon.xplan.validator.i18n.ValidationMessages.getMessage;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class ReportUtils {

	public static final String LABEL_INVALID = getMessage("report_invalid");

	public static final String LABEL_VALID = getMessage("report_valid");

	public enum SkipCode {

		SYNTAX_ERRORS(ValidationMessages.getMessage("report_syntaxErrors")),

		INTERNAL_ERRORS(ValidationMessages.getMessage("report_internalErrors"));

		private String message;

		SkipCode(String message) {
			this.message = message;
		}

		/**
		 * @return the message of the failure
		 */
		public String getMessage() {
			return message;
		}

	}

	private ReportUtils() {
	}

	/**
	 * @param isValid true if valid, false otherwise
	 * @return the string representation of the valid statue ('valid' if valid, 'nicht
	 * valide' if not)
	 */
	public static String createValidLabel(boolean isValid) {
		return isValid ? LABEL_VALID : LABEL_INVALID;
	}

	public static String asLabel(XPlanVersion version) {
		if (version == null)
			return "unbekannt";
		switch (version) {
			case XPLAN_40:
				return "4.0";
			case XPLAN_41:
				return "4.1";
			case XPLAN_50:
				return "5.0";
			case XPLAN_51:
				return "5.1";
			case XPLAN_52:
				return "5.2";
			case XPLAN_53:
				return "5.3";
			case XPLAN_54:
				return "5.4";
			case XPLAN_60:
				return "6.0";
			case XPLAN_SYN:
				return "1.0";
			default:
				return null;
		}
	}

}
