/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.feature.FeatureInspector;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Convenient access to resources in this module
 *
 * @author <a href="mailto:erben@lat-lon.de">Alexander Erben</a>
 * @version $Revision: $, $Date: $
 */
public class FeatureParserUtils {

	private FeatureParserUtils() {
	}

	/**
	 * Retrieves the FeatureCollection passed as XPlanArchive
	 * @param archive the archive to read
	 * @param inspectors to add
	 * @return the parsed <link>FeatureCollection</link>
	 */
	public static FeatureCollection readFeatures(XPlanArchive archive, FeatureInspector... inspectors)
			throws XMLStreamException, UnknownCRSException {
		return XPlanGmlParserBuilder.newBuilder()
			.withFeatureInspector(inspectors)
			.build()
			.parseFeatureCollection(archive);
	}

	/**
	 * Retrieves the resource (ZIP) absolute or relative to the class
	 * <link>ResourceAccessor</link>
	 * @param name the file name of the resource, absolute or relative to the class
	 * <link>ResourceAccessor</link>
	 * @param inspectors to add
	 * @return an <link>FeatureCollection</link> for the contents of the file
	 */
	public static FeatureCollection readFeaturesFromZip(String name, FeatureInspector... inspectors)
			throws IOException, XMLStreamException, UnknownCRSException {
		InputStream inputStream = FeatureParserUtils.class.getResourceAsStream("/testdata/" + name);
		return readFeaturesFromZip(name, inputStream, inspectors);
	}

	/**
	 * Retrieves the resource (ZIP) absolute or relative to the passed class
	 * @param name the file name of the resource, absolute or relative to the passed class
	 * @param baseClass the class the name is relative to
	 * @param inspectors to add
	 * @return an <link>FeatureCollection</link> for the contents of the file
	 */
	public static FeatureCollection readFeaturesFromZip(String name, Class baseClass, FeatureInspector... inspectors)
			throws IOException, XMLStreamException, UnknownCRSException {
		InputStream inputStream = baseClass.getResourceAsStream(name);
		return readFeaturesFromZip(name, inputStream, inspectors);
	}

	/**
	 * Retrieves the resource (GML) absolute or relative to the class
	 * <link>ResourceAccessor</link>
	 * @param name the file name of the resource, absolute or relative to the class
	 * <link>ResourceAccessor</link>
	 * @param inspectors to add
	 * @return an <link>FeatureCollection</link> for the contents of the file
	 */
	public static FeatureCollection readFeaturesFromGml(String name, FeatureInspector... inspectors)
			throws IOException, XMLStreamException, UnknownCRSException {
		InputStream inputStream = FeatureParserUtils.class.getResourceAsStream("/testdata/" + name);
		return readFeaturesFromGml(name, inputStream, inspectors);
	}

	/**
	 * Retrieves the resource (GML) absolute or relative to the passed class
	 * @param name the file name of the resource, absolute or relative to the passed class
	 * @param baseClass the class the name is relative to
	 * @param inspectors to add
	 * @return an <link>FeatureCollection</link> for the contents of the file
	 */
	public static FeatureCollection readFeaturesFromGml(String name, Class baseClass, FeatureInspector... inspectors)
			throws IOException, XMLStreamException, UnknownCRSException {
		InputStream resourceAsStream = baseClass.getResourceAsStream(name);
		return readFeaturesFromGml(name, resourceAsStream, inspectors);
	}

	private static FeatureCollection readFeaturesFromZip(String name, InputStream resourceAsStream,
			FeatureInspector[] inspectors) throws IOException, XMLStreamException, UnknownCRSException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive xPlanArchive = archiveCreator.createXPlanArchiveFromZip(name, resourceAsStream);
		return readFeatures(xPlanArchive, inspectors);
	}

	private static FeatureCollection readFeaturesFromGml(String name, InputStream inputStream,
			FeatureInspector[] inspectors) throws IOException, XMLStreamException, UnknownCRSException {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive xPlanArchive = archiveCreator.createXPlanArchiveFromGml(name, inputStream);
		return readFeatures(xPlanArchive, inspectors);
	}

}
