/*-
 * #%L
 * xplan-core-api - Modul zur Gruppierung der Kernmodule
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
package de.latlon.xplanbox.api.commons;

import de.latlon.xplan.validator.web.shared.ValidationOption;
import de.latlon.xplan.validator.web.shared.ValidationSettings;
import de.latlon.xplan.validator.web.shared.ValidationType;
import de.latlon.xplanbox.api.commons.exception.UnsupportedHeaderValue;
import de.latlon.xplanbox.api.commons.exception.UnsupportedParameterValue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.latlon.xplan.commons.util.TextPatternConstants.SIMPLE_NAME_PATTERN;
import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_FLAECHENSCHLUSS;
import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_GELTUNGSBEREICH;
import static de.latlon.xplan.validator.geometric.GeometricValidatorImpl.SKIP_LAUFRICHTUNG;
import static de.latlon.xplan.validator.web.shared.ValidationType.GEOMETRIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SEMANTIC;
import static de.latlon.xplan.validator.web.shared.ValidationType.SYNTACTIC;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public final class ValidatorConverter {

	private ValidatorConverter() {
	}

	public static String detectOrCreateValidationName(String xFilename)
			throws UnsupportedParameterValue, UnsupportedHeaderValue {
		return detectOrCreateValidationName(xFilename, null);
	}

	public static String detectOrCreateValidationName(String xFilenameHeader, String nameParameter)
			throws UnsupportedParameterValue, UnsupportedHeaderValue {
		if (nameParameter != null) {
			if (!matchesNamePattern(nameParameter)) {
				throw new UnsupportedParameterValue("name", nameParameter);
			}
			return nameParameter;
		}
		if (xFilenameHeader != null) {
			if (!matchesNamePattern(xFilenameHeader)) {
				throw new UnsupportedHeaderValue("X-Filename", xFilenameHeader);
			}
			int suffixStart = xFilenameHeader.lastIndexOf(".");
			if (suffixStart < 0)
				return xFilenameHeader;
			return xFilenameHeader.substring(0, suffixStart);
		}
		return UUID.randomUUID().toString();
	}

	public static ValidationSettings createValidationSettings(String validationName, boolean skipGeometrisch,
			boolean skipSemantisch, boolean skipFlaechenschluss, boolean skipGeltungsbereich, boolean skipLaufrichtung,
			List<String> profiles) {
		ValidationSettings settings = new ValidationSettings();
		settings.setValidationName(validationName);
		settings.setValidationTypes(asValidationTypes(skipGeometrisch, skipSemantisch));
		settings.setExtendedOptions(asValidationOptions(skipFlaechenschluss, skipGeltungsbereich, skipLaufrichtung));
		settings.setProfiles(profiles);
		return settings;
	}

	private static List<ValidationType> asValidationTypes(boolean skipGeometrisch, boolean skipSemantisch) {
		List<ValidationType> validationTypes = new ArrayList<>();
		validationTypes.add(SYNTACTIC);
		if (!skipSemantisch)
			validationTypes.add(SEMANTIC);
		if (!skipGeometrisch)
			validationTypes.add(GEOMETRIC);
		return validationTypes;
	}

	private static List<ValidationOption> asValidationOptions(boolean skipFlaechenschluss, boolean skipGeltungsbereich,
			boolean skipLaufrichtung) {
		List<ValidationOption> validationOptions = new ArrayList<>();
		if (skipFlaechenschluss)
			validationOptions.add(SKIP_FLAECHENSCHLUSS);
		if (skipGeltungsbereich)
			validationOptions.add(SKIP_GELTUNGSBEREICH);
		if (skipLaufrichtung)
			validationOptions.add(SKIP_LAUFRICHTUNG);
		return validationOptions;
	}

	private static boolean matchesNamePattern(String nameToCheck) {
		Pattern pattern = Pattern.compile(SIMPLE_NAME_PATTERN);
		Matcher matcher = pattern.matcher(nameToCheck);
		return matcher.matches();
	}

}
