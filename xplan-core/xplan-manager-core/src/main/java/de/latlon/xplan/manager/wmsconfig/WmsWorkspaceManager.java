/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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
package de.latlon.xplan.manager.wmsconfig;

import static de.latlon.xplan.manager.wmsconfig.ConfigWriterUtils.detectType;
import static de.latlon.xplan.manager.wmsconfig.ConfigWriterUtils.retrieveAllPlanTypes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.deegree.geometry.Envelope;
import org.deegree.geometry.primitive.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.manager.web.shared.PlanStatus;

/**
 * Creates/deletes configuration files in the xplansyn-wms-workspace or a passed
 * workspace.
 *
 * @author Florian Bingel
 * @version $Revision: $, $Date: $
 */
public class WmsWorkspaceManager {

	private static final Logger LOG = LoggerFactory.getLogger(WmsWorkspaceManager.class);

	private static final String DEFAULT_LOWER_CORNER = "-180 -90";

	private static final String DEFAULT_UPPER_CORNER = "180 90";

	private final String wsResource = "/wmsworkspace/";

	private final File wmsWorkspace;

	/**
	 * Instantiates a {@link WmsWorkspaceManager} with the wmsWorkspace directory
	 * @param wmsWorkspace directory the wms workspace is located, must exist and never
	 * <code>null</code>
	 * @throws IllegalArgumentException if the wmsWorkspace is <code>null</code> or does
	 * not exist
	 */
	public WmsWorkspaceManager(File wmsWorkspace) {
		if (wmsWorkspace == null || !wmsWorkspace.exists())
			throw new IllegalArgumentException();
		this.wmsWorkspace = wmsWorkspace;
	}

	/**
	 * Creates a WMS configuration in the workspace.
	 * @param archive to create a WMS configuration for, never <code>null</code>
	 * @param planId id of the plan, is used in the filenames of the configuration
	 * @param layerStoreIds list of tile ids, may be empty, but never <code>null</code>
	 * @param planStatus status of the plan the user selected, may be <code>null</code>
	 * @param bboxIn4326 bbox of the plan in EPSG:4326, may be <code>null</code>
	 * @param defaultBboxIn4326 default BBOX in EPSG:4326, may be <code>null</code>
	 * @throws Exception if an error occurred
	 */
	public void updateWmsWorkspace(XPlanArchive archive, int planId, List<String> layerStoreIds, PlanStatus planStatus,
			Envelope bboxIn4326, Envelope defaultBboxIn4326) throws Exception {
		String planType = detectType(archive.getType(), planStatus);
		String planIdAsString = Integer.toString(planId);

		createAndWriteWmsServiceConfiguration(planType, planIdAsString, layerStoreIds);
		createAndWriteThemesVectorLayerStoreId(planType, planIdAsString);
		createAndWriteLayersVectorConfiguration(planType, planIdAsString, bboxIn4326, defaultBboxIn4326);

		if (!layerStoreIds.isEmpty())
			createAndWriteThemesRasterLayerStoreId(planType, planIdAsString, layerStoreIds);
		// raster layers are created by XPlanRasterManager
		// createAndWriteLayersRasterConfiguration( planType, planIdAsString );

		LOG.info("WMS Konfiguration für Id " + planId + " nach " + wmsWorkspace.getPath() + " geschrieben.");
	}

	/**
	 * Deletes the configuration of the plan with the passed id from the workspace.
	 * @param planId id of the plan, is used in the filenames of the configuration
	 * @throws Exception if the expected workspace does not exist
	 */
	@Deprecated
	public void deleteWmsWorkspaceFilesForId(String planId) throws Exception {
		for (String planType : retrieveAllPlanTypes()) {
			deleteConfigFile(pathToLayersVectorConfig(planType, planId));
			deleteConfigFile(pathToLayersRasterConfig(planType, planId));
			deleteConfigFile(pathToThemesVectorConfig(planType, planId));
			deleteConfigFile(pathToThemesRasterConfig(planType, planId));
		}
		deleteConfigFile(pathToWmsServiceConfig(planId));
	}

	private void createAndWriteWmsServiceConfiguration(String planType, String planId, List<String> layerStoreIds)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {
		Document doc = createDocument(pathToWmsServiceTemplate());
		Node serviceConfiguration = doc.getElementsByTagName("wms:ServiceConfiguration").item(0);

		NodeList themeId = doc.getElementsByTagName("wms:ThemeId");
		themeId.item(0).setTextContent(planType + "_" + planId + "_vector");

		if (!layerStoreIds.isEmpty())
			themeId.item(1).setTextContent(planType + "_" + planId + "_raster");

		while (themeId.getLength() > 2) {
			Node node = themeId.item(2);
			serviceConfiguration.removeChild(node);
		}
		if (layerStoreIds.isEmpty()) {
			Node node = themeId.item(1);
			serviceConfiguration.removeChild(node);
		}

		writeToFile(doc, pathToWmsServiceConfig(planId));
	}

	private void createAndWriteLayersVectorConfiguration(String planType, String planId, Envelope bboxIn4326,
			Envelope defaultBboxIn4326)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {
		Document doc = createDocument(pathToLayersVectorTemplate(planType));
		writeLiteralsToDocument(planId, doc);
		writeLowerCornersToDocument(bboxIn4326, defaultBboxIn4326, doc);
		writeUpperCornersToDocument(bboxIn4326, defaultBboxIn4326, doc);
		writeToFile(doc, pathToLayersVectorConfig(planType, planId));
	}

	private void writeLiteralsToDocument(String planId, Document doc) {
		NodeList literals = doc.getElementsByTagName("Literal");
		for (int i = 0; i < literals.getLength(); i++) {
			Node node = literals.item(i);
			if ("PropertyIsEqualTo".equals(node.getParentNode().getNodeName())) {
				node.setTextContent(planId + "");
			}
		}
	}

	private void writeLowerCornersToDocument(Envelope bboxIn4326, Envelope defaultBboxIn4326, Document doc) {
		String lowerCorner = retrieveLowerCorner(bboxIn4326, defaultBboxIn4326);
		NodeList lowerCorners = doc.getElementsByTagName("s:LowerCorner");
		for (int i = 0; i < lowerCorners.getLength(); i++) {
			Node node = lowerCorners.item(i);
			if ("s:Envelope".equals(node.getParentNode().getNodeName())) {
				node.setTextContent(lowerCorner);
			}
		}
	}

	private void writeUpperCornersToDocument(Envelope bboxIn4326, Envelope defaultBboxIn4326, Document doc) {
		String upperCorner = retrieveUpperCorner(bboxIn4326, defaultBboxIn4326);
		NodeList upperCorners = doc.getElementsByTagName("s:UpperCorner");
		for (int i = 0; i < upperCorners.getLength(); i++) {
			Node node = upperCorners.item(i);
			if ("s:Envelope".equals(node.getParentNode().getNodeName())) {
				node.setTextContent(upperCorner);
			}
		}
	}

	private String retrieveLowerCorner(Envelope bboxIn4326, Envelope defaultBboxIn4326) {
		if (bboxIn4326 != null)
			return createCornerString(bboxIn4326.getMin());
		else if (defaultBboxIn4326 != null)
			return createCornerString(defaultBboxIn4326.getMin());
		else
			return DEFAULT_LOWER_CORNER;
	}

	private String retrieveUpperCorner(Envelope bboxIn4326, Envelope defaultBboxIn4326) {
		if (bboxIn4326 != null)
			return createCornerString(bboxIn4326.getMax());
		else if (defaultBboxIn4326 != null)
			return createCornerString(defaultBboxIn4326.getMax());
		else
			return DEFAULT_UPPER_CORNER;
	}

	private String createCornerString(Point corner) {
		double x = corner.get0();
		double y = corner.get1();
		return x + " " + y;
	}

	@SuppressWarnings("unused")
	private void createAndWriteLayersRasterConfiguration(String planType, String planId)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {
		Document doc = createDocument(pathToLayersRasterTemplate(planType));
		Node tileLayer = doc.getElementsByTagName("ns4:TileLayer").item(0);
		NodeList tileLayerChilds = tileLayer.getChildNodes();

		for (int i = 0; i < tileLayerChilds.getLength(); i++) {
			Node node = tileLayerChilds.item(i);

			if (node.getNodeName() != null) {
				switch (node.getNodeName()) {
				case "Name":
				case "ns2:Title":
					node.setTextContent(planType + "_" + planId + "_raster");
					break;
				case "ns4:TileDataSet":
					createAndAppendAttribute(node, "tileStoreId", planType + "_" + planId + "_raster");
					node.setTextContent(planType + "_" + planId + "_raster");
					break;
				}
			}
		}
		writeToFile(doc, pathToLayersRasterConfig(planType, planId));
	}

	private void createAndWriteThemesVectorLayerStoreId(String planType, String planId)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {
		Document doc = createDocument(pathToThemesVectorTemplate(planType));
		NodeList layerStoreId = doc.getElementsByTagName("LayerStoreId");

		layerStoreId.item(0).setTextContent(planType + "_" + planId + "_vector");
		writeToFile(doc, pathToThemesVectorConfig(planType, planId));
	}

	private void createAndWriteThemesRasterLayerStoreId(String planType, String planId, List<String> layerStoreIds)
			throws ParserConfigurationException, IOException, SAXException, TransformerException {
		Document doc = createDocument(pathToThemesRasterTemplate(planType));
		NodeList layerStoreIdNodeList = doc.getElementsByTagName("LayerStoreId");
		layerStoreIdNodeList.item(0).setTextContent(planType + "_" + planId + "_raster");

		Node theme = doc.getElementsByTagName("Layer").item(0).getParentNode();
		NodeList themeChilds = theme.getChildNodes();
		int noOfThemes = themeChilds.getLength();
		for (int i = 0; i < noOfThemes; i++) {
			Node node = themeChilds.item(i);
			if (node.getNodeName() != null) {
				switch (node.getNodeName()) {
				case "Identifier":
					node.setTextContent(planType + "_" + planId + "_raster_sortiert");
					break;
				case "ns2:Title":
					node.setTextContent(planType + " Raster Theme " + planId);
					break;
				case "Layer":
					for (String layerStoreId : layerStoreIds) {
						Node clonedNode = node.cloneNode(true);
						createAndAppendAttribute(clonedNode, "layerStore", layerStoreId);
						clonedNode.setTextContent(layerStoreId);
						theme.appendChild(clonedNode);
						i++;
					}
					theme.removeChild(node);
					break;
				}
			}
		}
		writeToFile(doc, pathToThemesRasterConfig(planType, planId));
	}

	private void deleteConfigFile(String fileToDelete) {
		File f = new File(wmsWorkspace, fileToDelete);
		if (f.exists() && f.delete()) {
			LOG.info("Lösche " + fileToDelete);
		}
	}

	private void createAndAppendAttribute(Node node, String attName, String val) {
		NamedNodeMap attributes = node.getAttributes();
		Node attNode = node.getOwnerDocument().createAttribute(attName);
		attNode.setNodeValue(val);
		attributes.setNamedItem(attNode);
	}

	private Document createDocument(String resource) throws ParserConfigurationException, IOException, SAXException {
		InputStream input = getClass().getResourceAsStream(resource);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(input);
	}

	private String pathToWmsServiceTemplate() {
		return wsResource + "services/wms_$ID.xml";
	}

	private String pathToLayersVectorTemplate(String planType) {
		return wsResource + "layers/" + planType + "_$ID_vector.xml";
	}

	private String pathToLayersRasterTemplate(String planType) {
		return wsResource + "layers/" + planType + "_$ID_raster.xml";
	}

	private String pathToThemesVectorTemplate(String planType) {
		return wsResource + "themes/" + planType + "_$ID_vector.xml";
	}

	private String pathToThemesRasterTemplate(String planType) {
		return wsResource + "themes/" + planType + "_$ID_raster.xml";
	}

	private String pathToWmsServiceConfig(String planId) {
		return "services/wms_" + planId + ".xml";
	}

	private String pathToLayersVectorConfig(String planType, String planId) {
		return "layers/" + planType + "_" + planId + "_vector.xml";
	}

	private String pathToLayersRasterConfig(String planType, String planId) {
		return "layers/" + planType + "_" + planId + "_raster.xml";
	}

	private String pathToThemesVectorConfig(String planType, String planId) {
		return "themes/" + planType + "_" + planId + "_vector.xml";
	}

	private String pathToThemesRasterConfig(String planType, String planId) {
		return "themes/" + planType + "_" + planId + "_raster.xml";
	}

	private void writeToFile(Document doc, String configFileToWrite) throws TransformerException {
		File config = new File(wmsWorkspace, configFileToWrite);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(config);
		transformer.transform(source, result);
	}

}
