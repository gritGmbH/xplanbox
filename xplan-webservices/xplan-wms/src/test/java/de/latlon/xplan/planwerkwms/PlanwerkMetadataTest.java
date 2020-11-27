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
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

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

        assertThat(planwerkConfig.getName(), equalTo("Bergedorf1101Aend"));
    }

    @Test
    public void verifyThatUnmarshallingWorksWithResourceBuilder() throws IOException {
        final List<String> versionsAsList = Arrays.asList(new String[]{"1.3.0", "1.1.1"});
        final DeegreeWMS.SupportedVersions supportedVersions = Mockito.mock(DeegreeWMS.SupportedVersions.class);
        when(supportedVersions.getVersion()).thenReturn(versionsAsList);
        final DeegreeWMS planwerkWms = Mockito.mock(DeegreeWMS.class);
        when(planwerkWms.getConfigVersion()).thenReturn("3.4.0");
        when(planwerkWms.getSupportedVersions()).thenReturn(supportedVersions);
        final WmsMetadata planwerkWmsMD = Mockito.mock(WmsMetadata.class);
        when(planwerkWmsMD.getCfg()).thenReturn(planwerkWms);
        final Workspace workspace = Mockito.mock(DefaultWorkspace.class);
        when(workspace.getModuleClassLoader()).thenReturn(PlanwerkMetadata.class.getClassLoader());
        when(workspace.getResourceMetadata(Matchers.<Class<OWSProvider>>any(), anyString())).thenReturn(planwerkWmsMD);
        final ResourceLocation<OWS> location = Mockito.mock(PlanwerkResourceLocation.class);
        when(location.getAsStream()).thenReturn(PlanwerkMetadata.class.getResource("/planwerkwms.xml").openStream());
        final OWSProvider provider = Mockito.mock(PlanwerkProvider.class);
        when(provider.getSchema()).thenReturn( PlanwerkMetadataTest.class.getResource(schemaLocation) );
        when(provider.getImplementationMetadata()).thenReturn((ImplementationMetadata)PlanwerkProvider.IMPLEMENTATION_METADATA);
        when(planwerkWmsMD.getProvider()).thenReturn(provider);
        final PlanwerkMetadata resource = new PlanwerkMetadata(workspace, location, provider );

        PlanwerkBuilder planwerkBuilder = (PlanwerkBuilder) resource.prepare();

        assertThat(planwerkBuilder, is(notNullValue()));
        assertThat(planwerkBuilder.build(), is(notNullValue()));
    }

    private Schema loadSchema(String schemaFile) throws IOException, SAXException {
        final SchemaFactory sf = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        final URL schemaFileUrl = PlanwerkMetadataTest.class.getResource(schemaFile);
        final StreamSource streamSource = new StreamSource(schemaFileUrl.openStream());
        return sf.newSchema(streamSource);
    }
}