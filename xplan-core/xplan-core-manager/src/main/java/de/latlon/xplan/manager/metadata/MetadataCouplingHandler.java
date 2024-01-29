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
package de.latlon.xplan.manager.metadata;

import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import de.latlon.xplan.manager.database.XPlanDao;
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.metadata.csw.CswClient;
import de.latlon.xplan.manager.metadata.csw.PlanRecordMetadata;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadata;
import org.deegree.geometry.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Properties;
import java.util.UUID;

import static java.nio.file.Files.readAllBytes;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MetadataCouplingHandler {

	private static final Logger LOG = LoggerFactory.getLogger(MetadataCouplingHandler.class);

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	private static final DateTimeFormatter DATE_TIME_FILE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");

	private static final DecimalFormat COORD_FORMAT;

	public static final String TEMPLATE_NAME = "service-iso-metadata-template.xml";

	private final CswClient cswClient;

	private final ServiceMetadataDocumentWriter serviceMetadataDocumentWriter;

	private final Path directoryToStoreMetadata;

	private final XPlanDao xPlanDao;

	static {
		COORD_FORMAT = (DecimalFormat) NumberFormat.getNumberInstance(Locale.ENGLISH);
		COORD_FORMAT.applyPattern("0.000000");
	}

	/**
	 * @param config never <code>null</code>
	 * @throws DataServiceCouplingException
	 */
	public MetadataCouplingHandler(XPlanManagerDao xPlanDao, CoupledResourceConfiguration config)
			throws DataServiceCouplingException {
		this(xPlanDao, config, new CswClient(config.getCswUrlProvidingDatasetMetadata()));
	}

	MetadataCouplingHandler(XPlanManagerDao xPlanDao, CoupledResourceConfiguration config, CswClient cswClient)
			throws DataServiceCouplingException {
		this.xPlanDao = xPlanDao;
		this.cswClient = cswClient;
		Path configurationDirectory = config.getMetadataConfigDirectory();
		byte[] template = readTemplate(configurationDirectory);
		this.serviceMetadataDocumentWriter = new ServiceMetadataDocumentWriter(template);
		this.directoryToStoreMetadata = config.getDirectoryToStoreMetadata();
	}

	/**
	 * Processes the metadata coupling:
	 * <ol>
	 * <li>Requests the metadata record describing the passed plan</li>
	 * <li>Creates a new service metadata record describing the Planwerk WMS of the passed
	 * plan</li>
	 * </ol>
	 * @param planName
	 * @param planwerkServiceMetadata describing the plan, never <code>null</code>
	 * @throws DataServiceCouplingException
	 */
	public void processMetadataCoupling(int planId, String planName, PlanwerkServiceMetadata planwerkServiceMetadata)
			throws DataServiceCouplingException {
		String serviceRecordId = UUID.randomUUID().toString();
		LocalDateTime now = LocalDateTime.now();
		PlanRecordMetadata planRecordMetadata = this.cswClient.requestMetadataRecord(planName);
		if (planRecordMetadata != null) {
			Properties properties = createProperties(planwerkServiceMetadata, planRecordMetadata, now, serviceRecordId);
			Path serviceMetadataDocument = createServiceMetadataDocument(planId, planwerkServiceMetadata.getTitle(),
					now, properties);
			LOG.info("Service metadata document was filed to {}", serviceMetadataDocument);
		}
		else {
			LOG.info("Dataset metadata document for plan with id {} and name {} is not available", planId, planName);
		}
		writePlanwerkCapabilitiesInfo(planId, serviceRecordId, planwerkServiceMetadata, planRecordMetadata);

	}

	@Transactional(rollbackOn = Exception.class)
	private void writePlanwerkCapabilitiesInfo(int planId, String serviceRecordId,
			PlanwerkServiceMetadata planwerkServiceMetadata, PlanRecordMetadata planRecordMetadata)
			throws DataServiceCouplingException {
		String title = planwerkServiceMetadata.getTitle();
		String resourceIdentifier = null;
		String datasetMetadataUrl = null;
		String serviceMetadataUrl = null;
		if (planRecordMetadata != null) {
			resourceIdentifier = planRecordMetadata.getResourceIdentifier();
			datasetMetadataUrl = cswClient.createGetRecordByIdRequest(planRecordMetadata.getRecordId());
			serviceMetadataUrl = cswClient.createGetRecordByIdRequest(serviceRecordId);
		}

		try {
			xPlanDao.insertOrReplacePlanWerkWmsMetadata(planId, title, resourceIdentifier, datasetMetadataUrl,
					serviceMetadataUrl);
		}
		catch (Exception e) {
			new DataServiceCouplingException("Could not insert in table planwerkwmsmetadata ", e);
		}
	}

	private Path createServiceMetadataDocument(int planId, String planName, LocalDateTime now, Properties properties)
			throws DataServiceCouplingException {
		OutputStream outputStream = null;
		try {
			String fileName = createFileName(planId, planName, now);
			Path target = directoryToStoreMetadata.resolve(fileName);
			LOG.info("Write Planwerk WMS service document to {}", target);
			outputStream = Files.newOutputStream(target);
			serviceMetadataDocumentWriter.writeServiceMetadataDocument(properties, outputStream);
			return target;
		}
		catch (IOException e) {
			LOG.error("Could not create output file", e);
			throw new DataServiceCouplingException(e);
		}
		catch (XMLStreamException e) {
			LOG.error("Could not create service metadata document", e);
			throw new DataServiceCouplingException(e);
		}
		finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				}
				catch (IOException e) {
				}
			}
		}
	}

	private String createFileName(int planId, String planName, LocalDateTime now) {
		String normalizedPlanName = planName.replaceAll("[^a-zA-Z0-9\\-_]", "");
		return planId + "_" + normalizedPlanName + "_" + now.format(DATE_TIME_FILE_FORMAT) + ".xml";
	}

	private Properties createProperties(PlanwerkServiceMetadata planwerkServiceMetadata,
			PlanRecordMetadata planRecordMetadata, LocalDateTime now, String recordId) {
		Properties properties = new Properties();
		properties.setProperty("METADATA_ID", recordId);
		properties.setProperty("CURRENT_DATE", now.format(DATE_FORMAT));
		properties.setProperty("TITLE", planwerkServiceMetadata.getTitle());
		properties.setProperty("CURRENT_DATE_TIME", now.format(DATE_TIME_FORMAT));
		setProperty(properties, "ABSTRACT", planwerkServiceMetadata.getDescription());
		setProperty(properties, "PLANWERKWMS_OVERVIEW", planwerkServiceMetadata.getPlanwerkWmsGetMapUrl());
		Envelope envelope = planwerkServiceMetadata.getEnvelope();
		if (envelope != null) {
			properties.setProperty("WEST_BOUND_LONG", COORD_FORMAT.format(envelope.getMin().get0()));
			properties.setProperty("EAST_BOUND_LONG", COORD_FORMAT.format(envelope.getMax().get0()));
			properties.setProperty("SOUTH_BOUND_LAT", COORD_FORMAT.format(envelope.getMin().get1()));
			properties.setProperty("NORTH_BOUND_LAT", COORD_FORMAT.format(envelope.getMax().get1()));
		}
		properties.setProperty("DATA_METADATA_RESOURCEIDENTIFIER", planRecordMetadata.getResourceIdentifier());
		properties.setProperty("DATA_METADATA_FILEIDENTIFIER", planRecordMetadata.getRecordId());
		setProperty(properties, "PLANWERKWMS_CAPABILITIES", planwerkServiceMetadata.getPlanwerkWmsGetCapabilitiesUrl());
		return properties;
	}

	private void setProperty(Properties properties, String key, String value) {
		properties.setProperty(key, value != null ? value : "");
	}

	private byte[] readTemplate(Path configurationDirectory) throws DataServiceCouplingException {
		Path serviceTemplateDocument = configurationDirectory.resolve(TEMPLATE_NAME);
		if (!Files.exists(serviceTemplateDocument))
			throw new DataServiceCouplingException(
					"Template for service metadata does not exist. Expected: " + serviceTemplateDocument);
		try {
			return readAllBytes(serviceTemplateDocument);
		}
		catch (IOException e) {
			throw new DataServiceCouplingException("Template for service metadata could not be read.", e);
		}
	}

}
