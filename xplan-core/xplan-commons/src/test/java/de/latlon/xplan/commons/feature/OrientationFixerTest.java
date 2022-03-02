package de.latlon.xplan.commons.feature;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.jts.JtsParser;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.xpath.TypedObjectNodeXPathEvaluator;
import org.deegree.filter.FilterEvaluationException;
import org.deegree.filter.IdFilter;
import org.deegree.geometry.primitive.Ring;
import org.deegree.geometry.standard.primitive.DefaultPolygon;
import org.junit.Test;
import org.locationtech.jts.geom.Coordinate;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class OrientationFixerTest {

	@Test
	public void testLaufrichtungCorrector_validLaufrichtung() throws Exception {
		XPlanGmlParser xPlanGmlParser = new XPlanGmlParser();
		XPlanArchive testArchive = getArchive("geometryOrientationValid.gml");
		XPlanFeatureCollection xPlanFeatureCollection = xPlanGmlParser.parseXPlanFeatureCollection(testArchive);
		Coordinate startPointOriginal = getSecondPoint(xPlanFeatureCollection);
		XPlanFeatureCollection xPlanFeatureCollectionRepairedLaufrichtung = xPlanGmlParser
				.parseXPlanFeatureCollection(testArchive, true);
		Coordinate startPointRepaired = getSecondPoint(xPlanFeatureCollectionRepairedLaufrichtung);
		assertThat(startPointRepaired.getX(), is(startPointOriginal.getX()));
		assertThat(startPointRepaired.getY(), is(startPointOriginal.getY()));
	}

	@Test
	public void testLaufrichtungCorrector_invalidLaufrichtung() throws Exception {
		XPlanGmlParser xPlanGmlParser = new XPlanGmlParser();
		XPlanArchive testArchive = getArchive("geometryOrientationInvalid.gml");
		XPlanFeatureCollection xPlanFeatureCollection = xPlanGmlParser.parseXPlanFeatureCollection(testArchive);
		Coordinate startPointOriginal = getSecondPoint(xPlanFeatureCollection);
		XPlanFeatureCollection xPlanFeatureCollectionRepairedLaufrichtung = xPlanGmlParser
				.parseXPlanFeatureCollection(testArchive, true);
		Coordinate startPointRepaired = getSecondPoint(xPlanFeatureCollectionRepairedLaufrichtung);
		assertThat(startPointRepaired.getX(), not(startPointOriginal.getX()));
		assertThat(startPointRepaired.getY(), not(startPointOriginal.getY()));
	}

	private Coordinate getSecondPoint(XPlanFeatureCollection xPlanFeatureCollectionRepairedLaufrichtung)
			throws FilterEvaluationException {
		Feature featureById = getFeatureById(xPlanFeatureCollectionRepairedLaufrichtung,
				"GML_88258139-e3ff-4388-9838-a30775e1f8bf");
		DefaultPolygon polygon = parseGeometry(featureById);
		Ring exteriorRing = polygon.getExteriorRing();
		return new JtsParser().getJTSRing(exteriorRing).getCoordinateN(1);
	}

	private Feature getFeatureById(XPlanFeatureCollection xPlanFeatureCollection, String id)
			throws FilterEvaluationException {
		FeatureCollection features = xPlanFeatureCollection.getFeatures();
		FeatureCollection members = features.getMembers(new IdFilter(id), new TypedObjectNodeXPathEvaluator());
		return members.stream().findFirst().get();
	}

	private DefaultPolygon parseGeometry(Feature featureById) {
		List<Property> properties = featureById.getProperties();
		for (Property property : properties) {
			if ("position".equals(property.getName().getLocalPart())) {
				return (DefaultPolygon) property.getValue();
			}
		}
		return null;
	}

	private XPlanArchive getArchive(String name) throws IOException {
		InputStream resourceAsStream = OrientationFixerTest.class.getResourceAsStream(name);
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		return archiveCreator.createXPlanArchiveFromGml("laufrichtung", resourceAsStream);
	}

}
