/*-
 * #%L
 * xplan-core-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.dictionary;

import de.latlon.xplan.commons.XPlanVersion;

import javax.xml.stream.XMLStreamException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static java.nio.file.Files.copy;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class XPlanCodelistsTest {

	@Test
	void testGetCodelists_xml(@TempDir Path synDirectory) throws Exception {
		copyCodelist(synDirectory, XPLAN_60, "exampleCodelist.xml");

		XPlanCodelists xPlanCodelists = new XPlanCodelists(synDirectory);
		XPlanDictionaries codelists60 = xPlanCodelists.getCodelists(XPLAN_60);

		assertEquals(2, codelists60.getDictionaries().size());
	}

	@Test
	void testGetCodelists_gml(@TempDir Path synDirectory) throws Exception {
		copyCodelist(synDirectory, XPLAN_54, "exampleCodelist.gml");

		XPlanCodelists xPlanCodelists = new XPlanCodelists(synDirectory);
		XPlanDictionaries codelists54 = xPlanCodelists.getCodelists(XPLAN_54);

		assertEquals(2, codelists54.getDictionaries().size());
	}

	private void copyCodelist(Path synDirectory, XPlanVersion xplan54, String targetName) throws IOException {
		InputStream resourceAsStream = XPlanCodelistsTest.class.getResourceAsStream("exampleCodelist.xml");
		Path xplan54File = Files.createDirectories(synDirectory.resolve(xplan54.getVersionDir()));
		copy(resourceAsStream, xplan54File.resolve(targetName));
		resourceAsStream.close();
	}

}
