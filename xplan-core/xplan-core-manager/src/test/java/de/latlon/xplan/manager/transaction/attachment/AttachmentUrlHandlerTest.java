/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.transaction.attachment;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.manager.edit.XPlanToEditFactory;
import de.latlon.xplan.manager.transaction.AttachmentUrlHandler;
import de.latlon.xplan.manager.web.shared.edit.RasterBasis;
import de.latlon.xplan.manager.web.shared.edit.RasterReference;
import de.latlon.xplan.manager.web.shared.edit.Reference;
import de.latlon.xplan.manager.web.shared.edit.XPlanToEdit;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AttachmentUrlHandlerTest {

	@Test
	public void testReplaceRelativeUrls_Xplan40() throws Exception {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		XPlanFeatureCollection xPlanFeatureCollection = getXPlanFeatureCollection("xplan40/BPlan004_4-0.zip");

		attachmentUrlHandler.replaceRelativeUrls(10, xPlanFeatureCollection);

		ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();
		List<ExternalReference> externalReferences = externalReferenceScanner.scan(xPlanFeatureCollection.getFeatures())
			.getAllReferences();

		assertTrue(externalReferences.size() == 2);
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_4-0.png"
				.equals(externalReference.getReferenzUrl())));
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_4-0.pgw"
				.equals(externalReference.getGeoRefUrl())));

		GenericXMLElement referenzURL = findExterneReferenzUrl_4(xPlanFeatureCollection);
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_4-0.pdf"
			.equals((referenzURL.getValue()).getAsText()));
	}

	@Test
	public void testReplaceRelativeUrls_Xplan41() throws Exception {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		XPlanFeatureCollection xPlanFeatureCollection = getXPlanFeatureCollection("xplan41/BPlan004_4-1.zip");

		attachmentUrlHandler.replaceRelativeUrls(10, xPlanFeatureCollection);

		ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();
		List<ExternalReference> externalReferences = externalReferenceScanner.scan(xPlanFeatureCollection.getFeatures())
			.getAllReferences();

		assertTrue(externalReferences.size() == 2);
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_4-1.png"
				.equals(externalReference.getReferenzUrl())));
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_4-1.pgw"
				.equals(externalReference.getGeoRefUrl())));

		GenericXMLElement referenzURL = findExterneReferenzUrl_4(xPlanFeatureCollection);
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_4-1.pdf"
			.equals((referenzURL.getValue()).getAsText()));
	}

	@Test
	public void testReplaceRelativeUrls_Xplan50() throws Exception {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		XPlanFeatureCollection xPlanFeatureCollection = getXPlanFeatureCollection("xplan50/BPlan004_5-0.zip");

		attachmentUrlHandler.replaceRelativeUrls(10, xPlanFeatureCollection);

		ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();
		List<ExternalReference> externalReferences = externalReferenceScanner.scan(xPlanFeatureCollection.getFeatures())
			.getAllReferences();

		assertTrue(externalReferences.size() == 2);
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-0.png"
				.equals(externalReference.getReferenzUrl())));
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-0.pgw"
				.equals(externalReference.getGeoRefUrl())));

		GenericXMLElement referenzURL = findExterneReferenzUrl(xPlanFeatureCollection);
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-0.pdf"
			.equals((referenzURL.getValue()).getAsText()));
	}

	@Test
	public void testReplaceRelativeUrls_Xplan51() throws Exception {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		XPlanFeatureCollection xPlanFeatureCollection = getXPlanFeatureCollection("xplan51/BPlan004_5-1.zip");

		attachmentUrlHandler.replaceRelativeUrls(10, xPlanFeatureCollection);

		ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();
		List<ExternalReference> externalReferences = externalReferenceScanner.scan(xPlanFeatureCollection.getFeatures())
			.getAllReferences();

		assertTrue(externalReferences.size() == 2);
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-1.png"
				.equals(externalReference.getReferenzUrl())));
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-1.pgw"
				.equals(externalReference.getGeoRefUrl())));

		GenericXMLElement referenzURL = findExterneReferenzUrl(xPlanFeatureCollection);
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-1.pdf"
			.equals((referenzURL.getValue()).getAsText()));
	}

	@Test
	public void testReplaceRelativeUrls_Xplan52() throws Exception {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		XPlanFeatureCollection xPlanFeatureCollection = getXPlanFeatureCollection("xplan52/BPlan004_5-2.zip");

		attachmentUrlHandler.replaceRelativeUrls(10, xPlanFeatureCollection);

		ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();
		List<ExternalReference> externalReferences = externalReferenceScanner.scan(xPlanFeatureCollection.getFeatures())
			.getAllReferences();

		assertTrue(externalReferences.size() == 2);
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-2.png"
				.equals(externalReference.getReferenzUrl())));
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-2.pgw"
				.equals(externalReference.getGeoRefUrl())));

		GenericXMLElement referenzURL = findExterneReferenzUrl(xPlanFeatureCollection);
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-2.pdf"
			.equals((referenzURL.getValue()).getAsText()));
	}

	@Test
	public void testReplaceRelativeUrls_Xplan60() throws Exception {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		XPlanFeatureCollection xPlanFeatureCollection = getXPlanFeatureCollection("xplan60/BPlan004_6-0.zip");

		attachmentUrlHandler.replaceRelativeUrls(10, xPlanFeatureCollection);

		ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();
		List<ExternalReference> externalReferences = externalReferenceScanner.scan(xPlanFeatureCollection.getFeatures())
			.getAllReferences();

		assertTrue(externalReferences.size() == 2);
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_6-0.png"
				.equals(externalReference.getReferenzUrl())));
		assertTrue(externalReferences.stream()
			.anyMatch(externalReference -> "http://test.de/xdokumente/api/v1/dokument/10/BPlan004_6-0.pgw"
				.equals(externalReference.getGeoRefUrl())));

		GenericXMLElement referenzURL = findExterneReferenzUrl(xPlanFeatureCollection);
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_6-0.pdf"
			.equals((referenzURL.getValue()).getAsText()));
	}

	@Test
	public void testReplaceRelativeUrls_XPlanToEdit() throws Exception {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		XPlanFeatureCollection xPlanFeatureCollection = getXPlanFeatureCollection("xplan60/BPlan004_6-0.zip");
		XPlanToEdit xPlanToEdit = new XPlanToEditFactory().createXPlanToEdit(xPlanFeatureCollection.getVersion(),
				xPlanFeatureCollection.getType(), xPlanFeatureCollection.getFeatures());
		attachmentUrlHandler.replaceRelativeUrls(10, xPlanToEdit);

		List<RasterBasis> rasterBasis = xPlanToEdit.getRasterBasis();
		assertTrue(rasterBasis.size() == 1);

		List<RasterReference> rasterReferences = rasterBasis.get(0).getRasterReferences();
		assertTrue(rasterReferences.size() == 1);
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_6-0.png"
			.equals(rasterReferences.get(0).getReference()));
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_6-0.pgw"
			.equals(rasterReferences.get(0).getGeoReference()));

		List<Reference> references = xPlanToEdit.getReferences();
		assertTrue(references.size() == 1);
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_6-0.pdf"
			.equals((references.get(0).getReference())));
	}

	@Test
	public void testReplaceRelativeUrl() {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		String replacedRelativeUrl = attachmentUrlHandler.replaceRelativeUrl("10", "test.pdf");
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/test.pdf".equals(replacedRelativeUrl));
	}

	@Test
	public void testIsSameReference() {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		boolean isSameReference = attachmentUrlHandler.isSameReference("10", "test.pdf",
				"http://test.de/xdokumente/api/v1/dokument/10/test.pdf");
		assertTrue(isSameReference);

		boolean isNotSameReference = attachmentUrlHandler.isSameReference("1", "test.pdf",
				"http://example.org/test/test.pdf");
		assertFalse(isNotSameReference);

	}

	private XPlanFeatureCollection getXPlanFeatureCollection(String resource)
			throws IOException, XMLStreamException, UnknownCRSException {
		InputStream inputStream = getClass().getResourceAsStream("/testdata/" + resource);
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(resource, inputStream);
		XPlanFeatureCollection xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(archive);
		return xPlanFeatureCollection;
	}

	private GenericXMLElement findExterneReferenzUrl(XPlanFeatureCollection xPlanFeatureCollection) {
		FeatureCollection featureCollection = xPlanFeatureCollection.getFeatures();
		Feature bpPlan = featureCollection.stream()
			.filter(feature -> "BP_Plan".equals(feature.getName().getLocalPart()))
			.findFirst()
			.get();
		Property externeReferenz = bpPlan.getProperties()
			.stream()
			.filter(property -> "externeReferenz".equals(property.getName().getLocalPart()))
			.findFirst()
			.get();
		GenericXMLElement xpSpezExterneReferenz = (GenericXMLElement) externeReferenz.getChildren().get(0);
		return (GenericXMLElement) xpSpezExterneReferenz.getChildren()
			.stream()
			.filter(property -> property instanceof GenericXMLElement
					&& "referenzURL".equals(((GenericXMLElement) property).getName().getLocalPart()))
			.findFirst()
			.get();
	}

	private GenericXMLElement findExterneReferenzUrl_4(XPlanFeatureCollection xPlanFeatureCollection) {
		FeatureCollection featureCollection = xPlanFeatureCollection.getFeatures();
		Feature bpPlan = featureCollection.stream()
			.filter(feature -> "BP_Plan".equals(feature.getName().getLocalPart()))
			.findFirst()
			.get();
		Property externeReferenz = bpPlan.getProperties()
			.stream()
			.filter(property -> "refBegruendung".equals(property.getName().getLocalPart()))
			.findFirst()
			.get();
		GenericXMLElement xpSpezExterneReferenz = (GenericXMLElement) externeReferenz.getChildren().get(0);
		return (GenericXMLElement) xpSpezExterneReferenz.getChildren()
			.stream()
			.filter(property -> property instanceof GenericXMLElement
					&& "referenzURL".equals(((GenericXMLElement) property).getName().getLocalPart()))
			.findFirst()
			.get();
	}

}
