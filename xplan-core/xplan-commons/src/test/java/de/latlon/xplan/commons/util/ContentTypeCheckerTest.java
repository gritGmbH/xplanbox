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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.nio.file.Path;

/**
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @since 7.0
 */
class ContentTypeCheckerTest {

	@Test
	void checkContentTypesOfXPlanArchiveOrGml() throws Exception {
		Path path = new File(getClass().getResource("Blankenese29_Test_60.zip").getFile()).toPath();
		ContentTypeChecker.checkContentTypesOfXPlanArchiveOrGml(path);
	}

	@Test
	void checkContentTypesOfXPlanArchiveOrGml_ExpectException() throws Exception {
		Path path = new File(getClass().getResource("Blankenese29_Test_60_InvalidContent.zip").getFile()).toPath();
		assertThrows(UnsupportedContentTypeException.class,
				() -> ContentTypeChecker.checkContentTypesOfXPlanArchiveOrGml(path));
	}

	@Test
	void checkContentTypeOfFileOfXPlanArchiveWithPng() throws Exception {
		Path path = new File(getClass().getResource("Blankenese29.png").getFile()).toPath();
		ContentTypeChecker.checkContentTypeOfFileOfXPlanArchive(path);
	}

	@Test
	void checkContentTypeOfFileOfXPlanArchiveWithPgw() throws Exception {
		Path path = new File(getClass().getResource("Blankenese29.pgw").getFile()).toPath();
		ContentTypeChecker.checkContentTypeOfFileOfXPlanArchive(path);
	}

	@Test
	void checkContentTypeOfFileOfXPlanArchiveWithOdt_ExpectException() throws Exception {
		Path path = new File(getClass().getResource("test.odt").getFile()).toPath();
		assertThrows(UnsupportedContentTypeException.class,
				() -> ContentTypeChecker.checkContentTypeOfFileOfXPlanArchive(path));
	}

}
