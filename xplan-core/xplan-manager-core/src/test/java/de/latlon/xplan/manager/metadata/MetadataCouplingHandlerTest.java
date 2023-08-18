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
import de.latlon.xplan.manager.database.XPlanManagerDao;
import de.latlon.xplan.manager.metadata.csw.CswClient;
import de.latlon.xplan.manager.metadata.csw.PlanRecordMetadata;
import de.latlon.xplan.manager.planwerkwms.PlanwerkServiceMetadata;
import org.apache.commons.io.IOUtils;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.SimpleGeometryFactory;
import org.junit.Test;
import org.xmlunit.matchers.HasXPathMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MetadataCouplingHandlerTest {

	@Test
	public void testProcessMetadataCoupling() throws Exception {
		PlanRecordMetadata planRecordMetadata = new PlanRecordMetadata("id", "http://test.de/id");
		String planName = "TestPlan1";
		CoupledResourceConfiguration config = createConfig();
		XPlanManagerDao xPlanDao = mock(XPlanManagerDao.class);
		CswClient cswClient = mockCswClient(planRecordMetadata, planName);
		MetadataCouplingHandler metadataCouplingHandler = new MetadataCouplingHandler(xPlanDao, config, cswClient);

		int planId = 1;
		metadataCouplingHandler.processMetadataCoupling(planId, planName, mockPlanwerkServiceMetadata(planName));

		Path directoryToStoreMetadata = config.getDirectoryToStoreMetadata();
		assertThat(numberOfCreatedRecords(directoryToStoreMetadata), is(1l));

		assertThat(theRecordIn(directoryToStoreMetadata),
				HasXPathMatcher.hasXPath("//gmd:MD_Metadata/gmd:dateStamp/gco:Date").withNamespaceContext(nsContext()));

		verify(xPlanDao, times(1)).insertOrReplacePlanWerkWmsMetadata(eq(planId), eq(planName), anyString(), isNull(),
				isNull());
	}

	@Test
	public void testProcessMetadataCoupling_UnavailableRecord() throws Exception {
		String planName = "TestPlan2";
		CoupledResourceConfiguration config = createConfig();
		XPlanManagerDao xPlanDao = mock(XPlanManagerDao.class);
		CswClient cswClient = mockCswClient(null, planName);
		MetadataCouplingHandler metadataCouplingHandler = new MetadataCouplingHandler(xPlanDao, config, cswClient);

		int planId = 1;
		metadataCouplingHandler.processMetadataCoupling(planId, planName, mockPlanwerkServiceMetadata(planName));

		Path directoryToStoreMetadata = config.getDirectoryToStoreMetadata();
		assertThat(numberOfCreatedRecords(directoryToStoreMetadata), is(0l));
		verify(xPlanDao, times(1)).insertOrReplacePlanWerkWmsMetadata(anyInt(), anyString(), isNull(), isNull(),
				isNull());
	}

	private Object numberOfCreatedRecords(Path directoryToStoreMetadata) throws IOException {
		return Files.list(directoryToStoreMetadata).count();
	}

	private String theRecordIn(Path createdMetadataRecords) throws IOException {
		Path metadataRecord = Files.list(createdMetadataRecords).findFirst().get();
		byte[] bytes = Files.readAllBytes(metadataRecord);
		return new String(bytes);
	}

	private PlanwerkServiceMetadata mockPlanwerkServiceMetadata(String planName) throws UnknownCRSException {
		PlanwerkServiceMetadata metadata = mock(PlanwerkServiceMetadata.class);
		when(metadata.getTitle()).thenReturn(planName);
		Envelope bbox = new SimpleGeometryFactory().createEnvelope(5, 45, 8, 46, CRSManager.lookup("EPSG:4326"));
		when(metadata.getEnvelope()).thenReturn(bbox);
		return metadata;
	}

	private CswClient mockCswClient(PlanRecordMetadata coupledResource, String planName)
			throws DataServiceCouplingException {
		CswClient cswClient = mock(CswClient.class);
		when(cswClient.requestMetadataRecord(planName)).thenReturn(coupledResource);
		return cswClient;
	}

	private CoupledResourceConfiguration createConfig() throws IOException {
		String cswUrlProvidingDatasetMetadata = "http://test.de";

		Path metadataConfigDirectory = createDirectoryWithTemplate();

		Path directoryToStoreMetadata = Files.createTempDirectory("directoryToStoreMetadataTest");

		String planWerkBaseUrl = "http://localhost:8080/xplan-planwerk-wms";
		return new CoupledResourceConfiguration(cswUrlProvidingDatasetMetadata, metadataConfigDirectory,
				directoryToStoreMetadata, planWerkBaseUrl, 750, 750);
	}

	private Path createDirectoryWithTemplate() throws IOException {
		InputStream resourceAsStream = MetadataCouplingHandlerTest.class
			.getResourceAsStream("iso-service-metadata-example-template.xml");
		Path metadataConfigDirectory = Files.createTempDirectory("metadataConfigDirectoryTest");
		Path target = Files.createFile(metadataConfigDirectory.resolve("service-iso-metadata-template.xml"));
		OutputStream output = Files.newOutputStream(target);
		IOUtils.copy(resourceAsStream, output);
		output.close();
		resourceAsStream.close();
		return metadataConfigDirectory;
	}

	private Map<String, String> nsContext() {
		Map<String, String> nsContext = new HashMap<>();
		nsContext.put("gmd", "http://www.isotc211.org/2005/gmd");
		nsContext.put("gco", "http://www.isotc211.org/2005/gco");
		return nsContext;
	}

}
