package de.latlon.xplan.commons.util;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ContentTypeCheckerTest {

	@Test
	public void checkContentTypes() throws IOException {
		Path path = new File(getClass().getResource("Blankenese29_Test_60.zip").getFile()).toPath();
		ContentTypeChecker.checkContentTypes(path);
	}

	@Test(expected = IOException.class)
	public void checkContentTypes_ExpectException() throws IOException {
		Path path = new File(getClass().getResource("Blankenese29_Test_60_InvalidContent.zip").getFile()).toPath();
		ContentTypeChecker.checkContentTypes(path);
	}

}