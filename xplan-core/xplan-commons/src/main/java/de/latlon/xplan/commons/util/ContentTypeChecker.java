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

	private static final List<String> ALLOWED_CONTENT_TYPES_UPLOADED_FILE = List.of("application/zip",
			"application/xml");

	private static final List<String> ALLOWED_CONTENT_TYPES_ZIP_ENTRY = List.of("application/xml", "image/png",
			"text/plain");

	private ContentTypeChecker() {
	}

	/**
	 * Checks if file contains an allowed content type (either GML or ZIP archive). In
	 * addition, if file is a ZIP archive, all zip entries are checked as well. The file
	 * is deleted if content type is not allowed and an IOException is thrown.
	 * @param file to be checked, never <code>null</code>
	 * @throws IOException if mime type is not allowed
	 */
	public static void checkContentTypes(Path file) throws IOException {
		Tika tika = new Tika();
		LOG.debug("Detecting content type of file {}", file);
		String contentType = tika.detect(file);
		LOG.debug("Content type of file {} is {}", file, contentType);
		checkIfContentTypeAllowed(file, file.toString(), contentType, ALLOWED_CONTENT_TYPES_UPLOADED_FILE);
		if ("application/zip".equals(contentType)) {
			checkContentTypesOfZipEntries(file, tika);
		}
	}

	private static void checkContentTypesOfZipEntries(Path file, Tika tika) throws IOException {
		ZipFile zipFile = new ZipFile(file.toString());
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file.toFile()),
				Charset.forName("UTF-8"));
		ZipEntry entry;
		while ((entry = zipInputStream.getNextEntry()) != null) {
			String name = entry.getName();
			LOG.debug("Detecting content type of zip entry {}", name);
			String contentType = tika.detect(zipFile.getInputStream(entry));
			LOG.debug("Content type of zip entry {} is {}", name, contentType);
			checkIfContentTypeAllowed(file, name, contentType, ALLOWED_CONTENT_TYPES_ZIP_ENTRY);
		}
	}

	private static void checkIfContentTypeAllowed(Path file, String fileName, String contentType,
			List<String> allowedContentTypes) throws IOException {
		if (!allowedContentTypes.contains(contentType)) {
			String message = "Content type of " + fileName + " is not allowed: " + contentType;
			LOG.warn(message);
			file.toFile().delete();
			LOG.debug("Deleted file {}", file);
			throw new IOException(message);
		}
	}

}
