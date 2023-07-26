package de.latlon.xplan.commons.util;

import org.apache.tika.Tika;

/**
 * Detect mime types using apache tika.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public final class MimeTypeDetector {

	private MimeTypeDetector() {
	}

	public static String getArtefactMimeType(String fileName) {
		Tika tika = new Tika();
		return tika.detect(fileName);
	}

}
