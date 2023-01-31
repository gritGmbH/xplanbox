package de.latlon.xplan.commons.util;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;

/**
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @since 6.1
 */
public class ContentTypeCheckerTest {

	@Test
	public void checkContentTypesOfXPlanArchiveOrGml() throws Exception {
		Path path = new File(getClass().getResource("Blankenese29_Test_60.zip").getFile()).toPath();
		ContentTypeChecker.checkContentTypesOfXPlanArchiveOrGml(path);
	}

	@Test(expected = UnsupportedContentTypeException.class)
	public void checkContentTypesOfXPlanArchiveOrGml_ExpectException() throws Exception {
		Path path = new File(getClass().getResource("Blankenese29_Test_60_InvalidContent.zip").getFile()).toPath();
		ContentTypeChecker.checkContentTypesOfXPlanArchiveOrGml(path);
	}

	@Test
	public void checkContentTypeOfFileOfXPlanArchiveWithPng() throws Exception {
		Path path = new File(getClass().getResource("Blankenese29.png").getFile()).toPath();
		ContentTypeChecker.checkContentTypeOfFileOfXPlanArchive(path);
	}

	@Test
	public void checkContentTypeOfFileOfXPlanArchiveWithPgw() throws Exception {
		Path path = new File(getClass().getResource("Blankenese29.pgw").getFile()).toPath();
		ContentTypeChecker.checkContentTypeOfFileOfXPlanArchive(path);
	}

	@Test(expected = UnsupportedContentTypeException.class)
	public void checkContentTypeOfFileOfXPlanArchiveWithOdt_ExpectException() throws Exception {
		Path path = new File(getClass().getResource("test.odt").getFile()).toPath();
		ContentTypeChecker.checkContentTypeOfFileOfXPlanArchive(path);
	}

}