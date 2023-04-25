/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.dictionary;

import de.latlon.xplan.commons.XPlanVersion;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_54;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_60;
import static java.nio.file.Files.copy;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanCodelistsTest {

	@Rule
	public TemporaryFolder synDirectory = new TemporaryFolder();

	@Test
	public void testGetCodelists_xml() throws XMLStreamException, IOException {
		copyCodelist(XPLAN_60, "exampleCodelist.xml");

		XPlanCodelists xPlanCodelists = new XPlanCodelists(synDirectory.getRoot().toPath());
		XPlanDictionaries codelists60 = xPlanCodelists.getCodelists(XPLAN_60);

		assertThat(codelists60.getDictionaries().size(), is(2));
	}

	@Test
	public void testGetCodelists_gml() throws XMLStreamException, IOException {
		copyCodelist(XPLAN_54, "exampleCodelist.gml");

		XPlanCodelists xPlanCodelists = new XPlanCodelists(synDirectory.getRoot().toPath());
		XPlanDictionaries codelists54 = xPlanCodelists.getCodelists(XPLAN_54);

		assertThat(codelists54.getDictionaries().size(), is(2));
	}

	private void copyCodelist(XPlanVersion xplan54, String targetName) throws IOException {
		InputStream resourceAsStream = XPlanCodelistsTest.class.getResourceAsStream("exampleCodelist.xml");
		File xplan54File = synDirectory.newFolder(xplan54.getVersionDir());
		copy(resourceAsStream, xplan54File.toPath().resolve(targetName));
		resourceAsStream.close();
	}

}
