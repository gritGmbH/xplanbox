/*-
 * #%L
 * xplan-manager-web - Webanwendung XPlanManagerWeb
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
package de.latlon.xplan.manager.web.client.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReferenceNameUtilsTest {

	@Test
	public void testThatParseFilenameFromUrlWithQueryParameter() {
		String url = "https://example.com/path/to/file.txt?abc=1&xyz=abc.pdf";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("file"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithQueryParameterWithoutFile() {
		String url = "https://example.com/path/to?abc=1&xyz=abc.pdf";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("abc=1&xyz=abc.pdf"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithQueryParameterWithoutFile1() {
		String url = "https://example.com/path/to?abc=1&xyz=abc";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("abc=1&xyz=abc"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithQueryParameterWithTrailingSlash() {
		String url = "https://example.com/path/to/?abc=1&xyz=abc.pdf";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("abc=1&xyz=abc.pdf"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithTrailingQuestionmark() {
		String url = "https://example.com/path/to/file.txt?";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("file"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithoutExtensionWithTrailingQuestionmark() {
		String url = "https://example.com/path/to/file?";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("file"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithoutExtension() {
		String url = "https://example.com/path/to/file";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("file"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithExtension() {
		String url = "https://example.com/path/to/file.txt";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("file"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithTrailingSlash() {
		String url = "https://example.com/path/to/file.txt/";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("file"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithoutPathAndFile() {
		String url = "https://example.com/";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("example"));

	}

	@Test
	public void testThatParseFilenameFromUrlWithCredentials() {
		String url = "https://foo@bar:example.com/path/to/file.txt";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("file"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithEmptyString() {
		String url = "";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, nullValue());
	}

	@Test
	public void testThatParseFilenameFromFileWithExtension() {
		String file = "file.txt";
		String filename = ReferenceNameUtils.extractFilenameFromFile(file);
		assertThat(filename, is("file"));
	}

}
