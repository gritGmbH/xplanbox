package de.latlon.xplan.manager.web.client.utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ReferenceNameUtilsTest {

	@Test
	public void testThatParseFilenameFromUrlWithQueryParameter() {
		String url = "https://example.com/path/to/file.txt?abc=1&xyz=abc";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("file"));
	}

	@Test
	public void testThatParseFilenameFromUrlWithQueryParameterWithoutFile() {
		String url = "https://example.com/path/to/?abc=1&xyz=abc";
		String filename = ReferenceNameUtils.extractFilenameFromUrl(url);
		assertThat(filename, is("abc=1&xyz=abc"));
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
		assertThat(filename, is("UNBEKANNT"));
	}

	@Test
	public void testThatParseFilenameFromFileWithExtension() {
		String file = "file.txt";
		String filename = ReferenceNameUtils.extractFilenameFromFile(file);
		assertThat(filename, is("file"));
	}

}