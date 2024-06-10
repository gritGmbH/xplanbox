/*-
 * #%L
 * xplan-services-wms - deegree XPlan WebMapService
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
package de.latlon.xplan.planwerkwms;

import de.latlon.xplan.planwerkwms.jaxb.Planwerk;

import org.deegree.commons.tom.ows.Version;
import org.deegree.services.OWS;
import org.deegree.services.OWSProvider;
import org.deegree.services.controller.ImplementationMetadata;
import org.deegree.services.jaxb.wms.DeegreeWMS;
import org.deegree.services.wms.controller.WmsMetadata;
import org.deegree.workspace.ResourceLocation;
import org.deegree.workspace.Workspace;
import org.deegree.workspace.standard.DefaultWorkspace;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.xml.sax.SAXException;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static de.latlon.xplan.planwerkwms.PlanwerkMetadata.CONFIG_JAXB_PACKAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
public class PlanwerkMetadataTest {

	private final String schemaLocation = "/META-INF/schemas/services/planwerkwms/1.0/planwerk_configuration.xsd";

	@Test
	public void verifyThatUnmarshallingWorksForConfigFile() throws JAXBException, IOException, SAXException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(CONFIG_JAXB_PACKAGE);
		final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		final Schema configSchema = loadSchema(schemaLocation);
		unmarshaller.setSchema(configSchema);

		URL planwerkConfigFile = PlanwerkMetadata.class.getResource("/planwerkwms.xml");
		Planwerk planwerkConfig = (Planwerk) unmarshaller.unmarshal(planwerkConfigFile.openStream());

		MatcherAssert.assertThat(planwerkConfig.getName(), equalTo("Bergedorf1101Aend"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void verifyThatUnmarshallingWorksWithResourceBuilder() throws IOException {
		final List<String> versionsAsList = Arrays.asList("1.3.0", "1.1.1");
		final DeegreeWMS.SupportedVersions supportedVersions = Mockito.mock(DeegreeWMS.SupportedVersions.class);
		when(supportedVersions.getVersion()).thenReturn(versionsAsList);
		final DeegreeWMS planwerkWms = Mockito.mock(DeegreeWMS.class);
		when(planwerkWms.getSupportedVersions()).thenReturn(supportedVersions);
		final WmsMetadata planwerkWmsMD = Mockito.mock(WmsMetadata.class);
		when(planwerkWmsMD.getCfg()).thenReturn(planwerkWms);
		final Workspace workspace = Mockito.mock(DefaultWorkspace.class);
		when(workspace.getModuleClassLoader()).thenReturn(PlanwerkMetadata.class.getClassLoader());
		when(workspace.getResourceMetadata(ArgumentMatchers.<Class<OWSProvider>>any(), anyString()))
			.thenReturn(planwerkWmsMD);
		final ResourceLocation<OWS> location = Mockito.mock(PlanwerkResourceLocation.class);
		when(location.getAsStream()).thenReturn(PlanwerkMetadata.class.getResource("/planwerkwms.xml").openStream());
		final OWSProvider provider = Mockito.mock(PlanwerkProvider.class);
		when(provider.getSchema()).thenReturn(PlanwerkMetadataTest.class.getResource(schemaLocation));
		when(provider.getImplementationMetadata())
			.thenReturn((ImplementationMetadata) PlanwerkProvider.IMPLEMENTATION_METADATA);
		when(planwerkWmsMD.getProvider()).thenReturn(provider);
		final PlanwerkMetadata resource = new PlanwerkMetadata(workspace, location, provider);

		PlanwerkBuilder planwerkBuilder = (PlanwerkBuilder) resource.prepare();

		MatcherAssert.assertThat(planwerkBuilder, is(notNullValue()));
		MatcherAssert.assertThat(planwerkBuilder.build(), is(notNullValue()));
	}

	private Schema loadSchema(String schemaFile) throws IOException, SAXException {
		final SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		final URL schemaFileUrl = PlanwerkMetadataTest.class.getResource(schemaFile);
		final StreamSource streamSource = new StreamSource(schemaFileUrl.openStream());
		return sf.newSchema(streamSource);
	}

}
