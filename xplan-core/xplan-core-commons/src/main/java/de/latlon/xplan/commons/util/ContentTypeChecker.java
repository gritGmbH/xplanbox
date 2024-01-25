/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.util;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static java.util.Arrays.asList;

/**
 * The class <code>ContentTypeChecker</code> provides methods to check for specific file
 * and content types.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @since 7.0
 */
public final class ContentTypeChecker {

	private static final Logger LOG = LoggerFactory.getLogger(ContentTypeChecker.class);

	public static final String CONTENT_TYPE_CHECKER_PROPERTIES = "contentTypeChecker.properties";

	private static final List<String> ALLOWED_CONTENT_TYPES_XPLANARCHIVE_AND_GML = createListOfAllowedContentTypes(
			"allowedContentTypesXPlanArchiveAndGml");

	private static final List<String> ALLOWED_CONTENT_TYPES_FILES_OF_XPLANARCHIVE = createListOfAllowedContentTypes(
			"allowedContentTypesFilesOfXPlanArchive");

	private ContentTypeChecker() {
	}

	/**
	 * Checks if file contains an allowed content type (either GML or ZIP archive). In
	 * addition, if file is a ZIP archive, all zip entries are checked as well. The file
	 * is deleted if content type is not allowed and an IOException is thrown.
	 * @param path to be checked, never <code>null</code>
	 * @throws java.io.IOException if mime type is not allowed
	 */
	public static void checkContentTypesOfXPlanArchiveOrGml(Path path)
			throws IOException, UnsupportedContentTypeException {
		String contentType = checkContentType(path, ALLOWED_CONTENT_TYPES_XPLANARCHIVE_AND_GML);
		if ("application/zip".equals(contentType))
			checkContentTypesOfZipEntries(path);
	}

	public static void checkContentTypeOfFileOfXPlanArchive(Path path)
			throws IOException, UnsupportedContentTypeException {
		checkContentType(path, ALLOWED_CONTENT_TYPES_FILES_OF_XPLANARCHIVE);
	}

	private static String checkContentType(Path path, List<String> allowedContentTypes)
			throws IOException, UnsupportedContentTypeException {
		LOG.debug("Detecting content type of file {}", path);
		String contentType = new Tika().detect(path);
		LOG.debug("Content type of file {} is {}", path, contentType);
		checkIfContentTypeAllowed(path, contentType, allowedContentTypes);
		return contentType;
	}

	private static void checkContentTypesOfZipEntries(Path path) throws IOException, UnsupportedContentTypeException {
		ZipFile zipFile = new ZipFile(path.toString());
		try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(path.toFile()),
				Charset.forName("UTF-8"))) {
			ZipEntry entry;
			while ((entry = zipInputStream.getNextEntry()) != null) {
				String name = entry.getName();
				LOG.debug("Detecting content type of zip entry {}", name);
				String contentType = new Tika().detect(zipFile.getInputStream(entry));
				LOG.debug("Content type of zip entry {} is {}", name, contentType);
				checkIfContentTypeAllowed(path, contentType, ALLOWED_CONTENT_TYPES_FILES_OF_XPLANARCHIVE);
			}
		}
	}

	private static void checkIfContentTypeAllowed(Path path, String contentType, List<String> allowedContentTypes)
			throws UnsupportedContentTypeException {
		if (!allowedContentTypes.contains(contentType)) {
			String message = "Content type of " + path + " is not allowed: " + contentType;
			LOG.warn(message);
			path.toFile().delete();
			LOG.debug("Deleted file {}", path);
			throw new UnsupportedContentTypeException(message);
		}
	}

	private static List<String> createListOfAllowedContentTypes(String property) {
		String allowedContentTypes = loadAllowedContentTypesProperty(property);
		return asList(allowedContentTypes.split(","));
	}

	private static String loadAllowedContentTypesProperty(String property) {
		try (InputStream stream = ContentTypeChecker.class.getResourceAsStream(CONTENT_TYPE_CHECKER_PROPERTIES)) {
			Properties properties = new Properties();
			properties.load(stream);
			return properties.getProperty(property);
		}
		catch (IOException e) {
			LOG.error(property + " of " + CONTENT_TYPE_CHECKER_PROPERTIES
					+ " could not be loaded! An empty string is used instead. Message: " + e.getMessage(), e);
			return "";
		}
	}

}
