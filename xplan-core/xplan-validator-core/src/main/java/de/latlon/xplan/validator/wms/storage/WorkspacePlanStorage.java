/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.wms.storage;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.validator.wms.MapPreviewCreationException;
import org.apache.commons.io.IOUtils;
import org.deegree.commons.xml.stax.IndentingXMLStreamWriter;
import org.deegree.cs.exceptions.TransformationException;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.deegree.gml.GMLOutputFactory;
import org.deegree.gml.GMLStreamWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;
import static org.deegree.gml.GMLVersion.GML_32;

/**
 * Implementation of a {@link PlanStorage}, writing the feature collection to the data
 * directory in the workspace directory.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class WorkspacePlanStorage implements PlanStorage {

	private static final Logger LOG = LoggerFactory.getLogger(WorkspacePlanStorage.class);

	private static final String RELATIVE_PATH_TO_DATA_DIR = "data";

	private static int PLANID = 1;

	private final Path pathToDataDirectory;

	/**
	 * @param workspaceLocation the path to the workspace, never <code>null</code>
	 * @throws IOException if the directory data could not be created
	 * @throws IllegalArgumentException if the workspace location does not exist
	 */
	public WorkspacePlanStorage(Path workspaceLocation) throws IOException {
		if (workspaceLocation == null || !Files.exists(workspaceLocation))
			throw new IllegalArgumentException("Workspace does not exist");
		this.pathToDataDirectory = workspaceLocation.resolve(RELATIVE_PATH_TO_DATA_DIR);
		if (!Files.exists(pathToDataDirectory))
			Files.createDirectory(pathToDataDirectory);
	}

	@Override
	public int retrieveUniquePlanid() {
		return PLANID++;
	}

	@Override
	public void storeSynFeatureCollection(int planId, FeatureCollection synFeatureCollection)
			throws MapPreviewCreationException {
		AppSchema synSchema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
		String fileName = UUID.randomUUID() + ".gml";
		Path pathToFile = pathToDataDirectory.resolve(fileName);
		LOG.info("Write to file {}", pathToFile);
		OutputStream output = null;
		GMLStreamWriter gmlWriter = null;
		try {
			output = Files.newOutputStream(pathToFile);
			gmlWriter = createGmlWriter(output);
			Map<String, String> nsBindings = synSchema.getNamespaceBindings();
			nsBindings.put("gml", GML_32.getNamespace());
			gmlWriter.setNamespaceBindings(nsBindings);
			gmlWriter.write(synFeatureCollection);
		}
		catch (IOException | UnknownCRSException | XMLStreamException | TransformationException e) {
			LOG.warn("Could not write featureCollection as GML to file", e);
			throw new MapPreviewCreationException(e);
		}
		finally {
			closeQuietly(gmlWriter);
			IOUtils.closeQuietly(output, null);
		}
	}

	private GMLStreamWriter createGmlWriter(OutputStream output) throws XMLStreamException {
		XMLStreamWriter xmlWriter = XMLOutputFactory.newInstance().createXMLStreamWriter(output);
		xmlWriter = new IndentingXMLStreamWriter(xmlWriter);
		return GMLOutputFactory.createGMLStreamWriter(GML_32, xmlWriter);
	}

	private void closeQuietly(GMLStreamWriter gmlWriter) {
		try {
			if (gmlWriter != null)
				gmlWriter.close();
		}
		catch (XMLStreamException e) {
		}
	}

}
