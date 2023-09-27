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

	private ReferenceNameUtils() {
	}

	public static final String extractFilenameFromUrl(String url) {
		RegExp pattern = RegExp.compile("[^/]*\\\\?([^/]+)$");
		MatchResult result = pattern.exec(url);
		if (result != null) {
			// Extract the filename at position 0
			String[] parts = result.getGroup(0).split("\\.");
			return parts[0];
		}
		else {
			return url;
		}
	}

	public static String extractFilenameFromFile(String filename) {
		int indexOfSuffix = filename.lastIndexOf(".");
		if (indexOfSuffix > 0) {
			return filename.substring(0, indexOfSuffix);
		}
		return filename;
	}

}
