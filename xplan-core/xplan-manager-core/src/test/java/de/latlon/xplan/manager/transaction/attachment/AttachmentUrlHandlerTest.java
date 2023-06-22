package de.latlon.xplan.manager.transaction.attachment;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.commons.reference.ExternalReference;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.manager.transaction.AttachmentUrlHandler;
import org.deegree.commons.tom.genericxml.GenericXMLElement;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.commons.tom.primitive.PrimitiveValue;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.property.GenericProperty;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class AttachmentUrlHandlerTest {

	@Test
	public void testReplaceRelativeUrls() throws Exception {
		AttachmentUrlHandler attachmentUrlHandler = new AttachmentUrlHandler(
				"http://test.de/xdokumente/api/v1/dokument/{planId}/{fileName}");
		XPlanFeatureCollection xPlanFeatureCollection = getXPlanFeatureCollection();

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

		GenericProperty referenzURL = findExterneReferenzUrl(xPlanFeatureCollection);
		assertTrue("http://test.de/xdokumente/api/v1/dokument/10/BPlan004_5-2.pdf"
				.equals(((PrimitiveValue) referenzURL.getValue()).getAsText()));
	}

	private static XPlanFeatureCollection getXPlanFeatureCollection()
			throws IOException, XMLStreamException, UnknownCRSException {
		InputStream inputStream = ResourceAccessor.readResourceStream("xplan52/BPlan004_5-2.zip");
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip("BPlan004_5-2.zip", inputStream);
		XPlanFeatureCollection xPlanFeatureCollection = XPlanGmlParserBuilder.newBuilder().build()
				.parseXPlanFeatureCollection(archive);
		return xPlanFeatureCollection;
	}

	private static GenericProperty findExterneReferenzUrl(XPlanFeatureCollection xPlanFeatureCollection) {
		FeatureCollection featureCollection = xPlanFeatureCollection.getFeatures();
		Feature bpPlan = featureCollection.stream()
				.filter(feature -> "BP_Plan".equals(feature.getName().getLocalPart())).findFirst().get();
		Property externeReferenz = bpPlan.getProperties().stream()
				.filter(property -> "externeReferenz".equals(property.getName().getLocalPart())).findFirst().get();
		GenericXMLElement xpSpezExterneReferenz = (GenericXMLElement) externeReferenz.getChildren().get(0);
		return (GenericProperty) xpSpezExterneReferenz.getChildren().stream()
				.filter(property -> property instanceof GenericProperty
						&& "referenzURL".equals(((GenericProperty) property).getName().getLocalPart()))
				.findFirst().get();
	}

}
