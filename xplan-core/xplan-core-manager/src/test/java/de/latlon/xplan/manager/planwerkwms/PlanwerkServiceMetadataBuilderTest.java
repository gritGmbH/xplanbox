/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.planwerkwms;

import de.latlon.xplan.manager.configuration.CoupledResourceConfiguration;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.exceptions.UnknownCRSException;
import org.deegree.cs.persistence.CRSManager;
import org.deegree.geometry.Envelope;
import org.deegree.geometry.SimpleGeometryFactory;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static de.latlon.xplan.commons.XPlanType.BP_Plan;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class PlanwerkServiceMetadataBuilderTest {

	private static final SimpleGeometryFactory GEOMETRY_FACTORY = new SimpleGeometryFactory();

	private static ICRS EPSG25832;

	private static ICRS EPSG4326;

	private final String planName = "test mit leer";

	private final String description = "test descr";

	private final String planWerkBaseUrl = "http://localhost:8080/xplan-planwerk-wms";

	private final String layer = "bp_objekte";

	private final String style = "";

	@BeforeClass
	public static void initCrs() throws UnknownCRSException {
		EPSG25832 = CRSManager.lookup("EPSG:25832");
		EPSG4326 = CRSManager.lookup("EPSG:4326");
	}

	@Test
	public void testBuild() throws Exception {
		Envelope envelope = GEOMETRY_FACTORY.createEnvelope(10.0, 53.5, 10.5, 54.0, EPSG4326);

		CoupledResourceConfiguration configuration = createConfig();
		PlanwerkServiceMetadataBuilder planwerkServiceMetadataBuilder = new PlanwerkServiceMetadataBuilder(BP_Plan,
				planName, description, envelope, configuration);
		PlanwerkServiceMetadata planwerkServiceMetadata = planwerkServiceMetadataBuilder.build(EPSG25832);

		assertThat(planwerkServiceMetadata.getTitle(), is(planName));
		assertThat(planwerkServiceMetadata.getDescription(), is(description));
		assertThat(planwerkServiceMetadata.getEnvelope(), is(envelope));
		assertThat(planwerkServiceMetadata.getPlanwerkWmsGetCapabilitiesUrl(),
				is(planWerkBaseUrl + "/services/planwerkwms/planname/"
						+ URLEncoder.encode(planName, StandardCharsets.UTF_8.toString())
						+ "?SERVICE=WMS&VERSION=1.3.0&REQUEST=GetCapabilities"));

		String getMapUrl = planwerkServiceMetadata.getPlanwerkWmsGetMapUrl();
		assertThat(getMapUrl, startsWith(planWerkBaseUrl + "/services/planwerkwms/planname/"
				+ URLEncoder.encode(planName, StandardCharsets.UTF_8.toString()) + "?"));
		assertThat(getMapUrl, containsString("LAYERS=" + layer));
		assertThat(getMapUrl, containsString("STYLES=" + style));
		assertThat(getMapUrl, containsString("WIDTH=" + configuration.getPlanWerkWmsGetMapWidth()));
		assertThat(getMapUrl, containsString("HEIGHT=" + configuration.getPlanWerkWmsGetMapHeight()));
	}

	@Test
	public void testBuild_GetMap_FittingBbox() throws Exception {
		Envelope envelope = GEOMETRY_FACTORY.createEnvelope(10.0, 53.5, 10.5, 54.0, EPSG4326);

		CoupledResourceConfiguration configuration = createConfig();
		PlanwerkServiceMetadataBuilder planwerkServiceMetadataBuilder = new PlanwerkServiceMetadataBuilder(BP_Plan,
				planName, description, envelope, configuration);

		PlanwerkServiceMetadata planwerkServiceMetadata = planwerkServiceMetadataBuilder.build(EPSG4326);
		String getMapUrl = planwerkServiceMetadata.getPlanwerkWmsGetMapUrl();
		assertThat(getMapUrl, containsString("BBOX=" + asString(envelope)));
	}

	@Test
	public void testBuild_GetMap_BboxToHeight() throws Exception {
		Envelope envelope = GEOMETRY_FACTORY.createEnvelope(10.0, 53.0, 10.5, 54.0, EPSG4326);

		CoupledResourceConfiguration configuration = createConfig();
		PlanwerkServiceMetadataBuilder planwerkServiceMetadataBuilder = new PlanwerkServiceMetadataBuilder(BP_Plan,
				planName, description, envelope, configuration);
		PlanwerkServiceMetadata planwerkServiceMetadata = planwerkServiceMetadataBuilder.build(EPSG4326);

		Envelope expectedBBox = GEOMETRY_FACTORY.createEnvelope(9.75, 53.0, 10.75, 54.0, EPSG4326);
		String getMapUrl = planwerkServiceMetadata.getPlanwerkWmsGetMapUrl();

		assertThat(getMapUrl, containsString("BBOX=" + asString(expectedBBox)));
	}

	@Test
	public void testBuild_GetMap_BboxToWidth() throws Exception {
		Envelope envelope = GEOMETRY_FACTORY.createEnvelope(10.0, 53.5, 11, 54.0, EPSG4326);

		CoupledResourceConfiguration configuration = createConfig();
		PlanwerkServiceMetadataBuilder planwerkServiceMetadataBuilder = new PlanwerkServiceMetadataBuilder(BP_Plan,
				planName, description, envelope, configuration);
		PlanwerkServiceMetadata planwerkServiceMetadata = planwerkServiceMetadataBuilder.build(EPSG4326);

		Envelope expectedBBox = GEOMETRY_FACTORY.createEnvelope(10, 53.25, 11, 54.25, EPSG4326);
		String getMapUrl = planwerkServiceMetadata.getPlanwerkWmsGetMapUrl();

		assertThat(getMapUrl, containsString("BBOX=" + asString(expectedBBox)));
	}

	private String asString(Envelope envelope) {
		return envelope.getMin().get0() + "," + envelope.getMin().get1() + "," + envelope.getMax().get0() + ","
				+ envelope.getMax().get1();
	}

	private CoupledResourceConfiguration createConfig() throws IOException {
		String cswUrlProvidingDatasetMetadata = "http://test.de";
		Path metadataConfigDirectory = Files.createTempDirectory("metadataConfigDirectory");
		Path directoryToStoreMetadata = Files.createTempDirectory("directoryToStoreMetadata");
		CoupledResourceConfiguration configuration = new CoupledResourceConfiguration(cswUrlProvidingDatasetMetadata,
				metadataConfigDirectory, directoryToStoreMetadata, planWerkBaseUrl, 750, 750);
		configuration.addPlanWerkWmsGetMapLayer(BP_Plan, layer);
		configuration.addPlanWerkWmsGetMapStyle(BP_Plan, style);
		return configuration;
	}

}
