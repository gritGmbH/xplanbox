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
package de.latlon.xplan.manager.inspireplu;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.inspire.plu.transformation.InspirePluTransformator;
import de.latlon.xplan.manager.database.XPlanDao;
import org.apache.commons.io.IOUtils;
import org.deegree.feature.FeatureCollection;
import org.deegree.gml.GMLInputFactory;
import org.deegree.gml.GMLStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.deegree.gml.GMLVersion.GML_32;

/**
 * Retrieves the XPlan GML from the database, transforms the plan to INSPIRE PLU and
 * inserts the transformed plan to the INSPIRE download service for PLU.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class InspirePluPublisher {

	private static final Logger LOG = LoggerFactory.getLogger(InspirePluPublisher.class);

	private XPlanDao xPlanDao;

	private InspirePluTransformator transformator;

	/**
	 * Instantiates the {@link InspirePluPublisher}
	 * @param xPlanDao used to retrieve the XPlan GML insert the INSPIRE PLU, never
	 * <code>null</code>
	 * @param transformator the {@link InspirePluTransformator} to transform the XPlan GML
	 * to INSPIRE PLU, never <code>nulll</code>
	 */
	public InspirePluPublisher(XPlanDao xPlanDao, InspirePluTransformator transformator) {
		this.xPlanDao = xPlanDao;
		this.transformator = transformator;
	}

	/**
	 * Retrieves the XPlan GML from the database, transforms the plan to INSPIRE PLU and
	 * inserts the transformed plan to the INSPIRE download service for PLU.
	 * @param planId id of the plan to transform and publish, never <code>null</code>
	 * @param xPlanVersion the version of the xplan, never <code>null</code>
	 * @throws Exception if an exception occurs
	 */
	@Transactional(rollbackOn = Exception.class)
	public void transformAndPublish(String planId, XPlanVersion xPlanVersion) throws Exception {
		Path xPlanGml = retrieveXPlan(planId);
		Path inspirePlu = transformator.transformToPlu(xPlanGml, xPlanVersion);
		FeatureCollection featureCollection = parseFeatureCollection(inspirePlu);
		xPlanDao.insertInspirePlu(featureCollection);
		xPlanDao.setPlanWasInspirePublished(planId);
	}

	private FeatureCollection parseFeatureCollection(Path inspirePlu) throws Exception {
		XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
		xmlInputFactory.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
		xmlInputFactory.setProperty(XMLInputFactory.SUPPORT_DTD, false);
		try (FileInputStream stream = new FileInputStream(inspirePlu.toFile())) {
			XMLStreamReader xmlStream = xmlInputFactory.createXMLStreamReader(stream);
			GMLStreamReader gmlReader = GMLInputFactory.createGMLStreamReader(GML_32, xmlStream);
			return gmlReader.readFeatureCollection();
		}
		catch (Exception e) {
			LOG.error("Could not parse INSPIRE PLU dataset as feature collection", e);
			throw new Exception("Could not parse INSPIRE PLU dataset as feature collection");
		}
	}

	private Path retrieveXPlan(String planId) throws Exception {
		try {
			InputStream sourceStream = xPlanDao.retrieveXPlanArtefact(planId);
			Path sourceFile = Files.createTempFile("xplanGmlSource", ".xml");
			FileOutputStream sourceFileStream = new FileOutputStream(sourceFile.toFile());
			IOUtils.copy(sourceStream, sourceFileStream);
			sourceStream.close();
			sourceFileStream.close();
			return sourceFile;
		}
		catch (Exception e) {
			LOG.error("Could not retrieve XPlan GML from database", e);
			throw new Exception("Could not retrieve XPlan GML from database");
		}
	}

}
