package de.latlon.xplan.commons.util;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ContentTypeChecker {

	private static final Logger LOG = LoggerFactory.getLogger(ContentTypeChecker.class);

	private static final List<String> ALLOWED_CONTENT_TYPES = List.of("application/zip", "application/xml", "image/png",
			"text/plain", "application/pdf");

	private ContentTypeChecker() {
	}

	/**
	 * Checks if file contains an allowed content type (either GML or ZIP archive). In
	 * addition, if file is a ZIP archive, all zip entries are checked as well. The file
	 * is deleted if content type is not allowed and an IOException is thrown.
	 * @param path to be checked, never <code>null</code>
	 * @throws IOException if mime type is not allowed
	 */
	public static void checkContentTypes(Path path) throws IOException {
		Tika tika = new Tika();
		LOG.debug("Detecting content type of file {}", path);
		String contentType = tika.detect(path);
		LOG.debug("Content type of file {} is {}", path, contentType);
		checkIfContentTypeAllowed(path, path.toString(), contentType, ALLOWED_CONTENT_TYPES);
		if ("application/zip".equals(contentType)) {
			checkContentTypesOfZipEntries(path, tika);
		}
	}

	private static void checkContentTypesOfZipEntries(Path path, Tika tika) throws IOException {
		ZipFile zipFile = new ZipFile(path.toString());
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(path.toFile()),
				Charset.forName("UTF-8"));
		ZipEntry entry;
		while ((entry = zipInputStream.getNextEntry()) != null) {
			String name = entry.getName();
			LOG.debug("Detecting content type of zip entry {}", name);
			String contentType = tika.detect(zipFile.getInputStream(entry));
			LOG.debug("Content type of zip entry {} is {}", name, contentType);
			checkIfContentTypeAllowed(path, name, contentType, ALLOWED_CONTENT_TYPES);
		}
	}

	private static void checkIfContentTypeAllowed(Path path, String fileName, String contentType,
			List<String> allowedContentTypes) throws IOException {
		if (!allowedContentTypes.contains(contentType)) {
			String message = "Content type of " + fileName + " is not allowed: " + contentType;
			LOG.warn(message);
			path.toFile().delete();
			LOG.debug("Deleted file {}", path);
			throw new IOException(message);
		}
	}

}
