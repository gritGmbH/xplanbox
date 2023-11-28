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
