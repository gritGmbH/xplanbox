/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.utils;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

/**
 * Helper class to extract file names from URLs using GWT RegEx API.
 *
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 * @since 7.0
 */
public final class ReferenceNameUtils {

	public static final String UNKNOWN = "Unbekannt";

	private ReferenceNameUtils() {
	}

	public static String extractFilenameFromUrl(String url) {
		if (url == null || url.isEmpty()) {
			return null;
		}
		url = removeTrailingQuestionmark(url);
		if (url.contains("?")) {
			String[] pathAndQueryString = url.split("\\?");
			String lastPathSegment = extractLastPathSegment(pathAndQueryString[0]);
			if (lastPathSegment != null && lastPathSegment.contains(".")) {
				return extractFilenameFromFile(lastPathSegment);
			}
			else {
				return pathAndQueryString[1];
			}
		}
		String lastPathSegment = extractLastPathSegment(url);
		if (lastPathSegment != null) {
			return extractFilenameFromFile(lastPathSegment);
		}
		return UNKNOWN;
	}

	private static String removeTrailingQuestionmark(String url) {
		return url.replaceAll("\\?$", "");
	}

	private static String extractLastPathSegment(String pathString) {
		RegExp pattern = RegExp.compile("[^/]*\\\\?([^/]+)$");
		MatchResult result = pattern.exec(pathString.replaceAll("/$", ""));
		if (result != null) {
			return result.getGroup(0);
		}
		return null;
	}

	public static String extractFilenameFromFile(String filename) {
		int indexOfSuffix = filename.lastIndexOf(".");
		if (indexOfSuffix > 0) {
			return filename.substring(0, indexOfSuffix);
		}
		return filename;
	}

}
