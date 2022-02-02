package de.latlon.xplan.commons.feature;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanGmlParserTest {

	@Test
	public void testParseFeatureCollection() throws Exception {
		XPlanGmlParser xPlanGmlParser = new XPlanGmlParser();
		XPlanArchive testArchive = getTestArchive("xplan41/Eidelstedt_4_V4.zip");
		XPlanFeatureCollection xPlanFeatureCollection = xPlanGmlParser.parseXPlanFeatureCollection(testArchive);
		assertThat(xPlanFeatureCollection.getFeatures().size(), is(56));
	}

	@Test
	public void testParseFeatureCollectionMultipleInstance() throws Exception {
		XPlanGmlParser xPlanGmlParser = new XPlanGmlParser();
		XPlanArchive testArchive = getArchive("xplan-multipleInstances.gml");
		XPlanFeatureCollections xPlanFeatureCollection = xPlanGmlParser
				.parseXPlanFeatureCollectionAllowMultipleInstances(testArchive, null);
		assertThat(xPlanFeatureCollection.getxPlanGmlInstances().size(), is(3));
	}

	@Test(expected = FeatureCollectionParseException.class)
	public void testParseFeatureCollectionMultipleInstanceWithUnreferencedFeature() throws Exception {
		XPlanGmlParser xPlanGmlParser = new XPlanGmlParser();
		XPlanArchive testArchive = getArchive("xplan-multipleInstances-withUnreferenced.gml");
		xPlanGmlParser.parseXPlanFeatureCollectionAllowMultipleInstances(testArchive, null);
	}

	private XPlanArchive getTestArchive(String name) throws IOException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromZip(name, ResourceAccessor.readResourceStream(name));
	}

	private XPlanArchive getArchive(String name) throws IOException {
		InputStream resourceAsStream = XPlanGmlParserTest.class.getResourceAsStream(name);
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromGml("multipleInstances", resourceAsStream);
	}

}