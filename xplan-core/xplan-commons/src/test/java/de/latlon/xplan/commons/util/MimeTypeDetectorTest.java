package de.latlon.xplan.commons.util;

import org.junit.Test;

import static de.latlon.xplan.commons.util.MimeTypeDetector.getArtefactMimeType;
import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MimeTypeDetectorTest {

	@Test
	public void testGetArtefactMimeType() {
		assertEquals("text/xml", getArtefactMimeType("test.xml"));
		assertEquals("text/xml", getArtefactMimeType("test.gml"));
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
