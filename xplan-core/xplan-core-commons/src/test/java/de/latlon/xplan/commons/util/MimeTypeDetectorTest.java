/*-
 * #%L
 * xplan-core-commons - Commons Paket fuer XPlan Manager und XPlan Validator
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
package de.latlon.xplan.commons.util;

import org.junit.jupiter.api.Test;

import static de.latlon.xplan.commons.util.MimeTypeDetector.getArtefactMimeType;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class MimeTypeDetectorTest {

	@Test
	void testGetArtefactMimeType() {
		assertEquals("application/xml", getArtefactMimeType("test.xml"));
		assertEquals("application/xml", getArtefactMimeType("test.gml"));
		assertEquals("application/pdf", getArtefactMimeType("test.pdf"));
		assertEquals("application/zip", getArtefactMimeType("test.zip"));
		assertEquals("image/jpeg", getArtefactMimeType("test.jpg"));
		assertEquals("image/jpeg", getArtefactMimeType("test.jpeg"));
		assertEquals("image/png", getArtefactMimeType("test.png"));
		assertEquals("image/tiff", getArtefactMimeType("test.tif"));
		assertEquals("image/tiff", getArtefactMimeType("test.tiff"));
		assertEquals("image/ecw", getArtefactMimeType("test.ecw"));
		assertEquals("text/html", getArtefactMimeType("test.html"));
		assertEquals("text/plain", getArtefactMimeType("test.txt"));
		assertEquals("text/plain", getArtefactMimeType("test.text"));
		assertEquals("application/octet-stream", getArtefactMimeType("test.unsupported"));
	}

}
