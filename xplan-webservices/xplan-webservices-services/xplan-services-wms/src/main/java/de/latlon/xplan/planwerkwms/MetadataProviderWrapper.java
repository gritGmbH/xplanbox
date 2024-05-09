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
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.util.AXIOMUtil;
import org.apache.axiom.om.util.XMLStreamWriterFilterBase;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.deegree.commons.ows.metadata.DatasetMetadata;
import org.deegree.commons.ows.metadata.MetadataUrl;
import org.deegree.commons.ows.metadata.ServiceIdentification;
import org.deegree.commons.ows.metadata.ServiceProvider;
import org.deegree.commons.ows.metadata.layer.ExternalIdentifier;
import org.deegree.commons.tom.ows.LanguageString;
import org.deegree.services.metadata.OWSMetadataProvider;
import org.deegree.workspace.Resource;
import org.deegree.workspace.ResourceMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;
import static org.deegree.commons.xml.stax.XMLStreamUtils.closeQuietly;
import static org.deegree.commons.xml.stax.XMLStreamUtils.copy;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class MetadataProviderWrapper implements OWSMetadataProvider {

	private static final Logger LOG = LoggerFactory.getLogger(MetadataProviderWrapper.class);

	private static final QName EXTENDED_CAPABILITIES = new QName("http://inspire.ec.europa.eu/schemas/inspire_vs/1.0",
			"ExtendedCapabilities");

	private final OWSMetadataProvider wrappedProvider;

	private final Planwerk planwerk;

	public MetadataProviderWrapper(OWSMetadataProvider wrappedProvider, Planwerk planwerk) {
		this.wrappedProvider = wrappedProvider;
		this.planwerk = planwerk;
	}

	@Override
	public ServiceIdentification getServiceIdentification() {
		ServiceIdentification serviceIdentification = wrappedProvider.getServiceIdentification();
		String name = getTitle(planwerk);
		if (serviceIdentification == null)
			return new ServiceIdentification(name, null, null, null, null, null, null, null, null);
		List<LanguageString> titles = singletonList(new LanguageString(name, null));
		return new ServiceIdentification(name, titles, serviceIdentification.getAbstracts(),
				serviceIdentification.getKeywords(), serviceIdentification.getServiceType(),
				serviceIdentification.getServiceTypeVersion(), serviceIdentification.getProfiles(),
				serviceIdentification.getFees(), serviceIdentification.getAccessConstraints());
	}

	@Override
	public ServiceProvider getServiceProvider() {
		return wrappedProvider.getServiceProvider();
	}

	@Override
	public Map<String, List<OMElement>> getExtendedCapabilities() {
		Map<String, List<OMElement>> extendedCapabilities = wrappedProvider.getExtendedCapabilities();
		List<String> serviceMetadataUrls = planwerk.getServiceMetadataUrl();
		if (extendedCapabilities != null && !serviceMetadataUrls.isEmpty()) {
			if (serviceMetadataUrls.size() > 2)
				LOG.warn("Currently only one service metadata is allowd");
			return createCopyOfExtendedCapabilities(extendedCapabilities, serviceMetadataUrls);
		}
		return extendedCapabilities;
	}

	@Override
	public List<DatasetMetadata> getDatasetMetadata() {
		return wrappedProvider.getDatasetMetadata();
	}

	@Override
	public Map<String, String> getExternalMetadataAuthorities() {
		return wrappedProvider.getExternalMetadataAuthorities();
	}

	@Override
	public DatasetMetadata getDatasetMetadata(QName name) {
		return wrappedProvider.getDatasetMetadata(name);
	}

	@Override
	public List<DatasetMetadata> getAllDatasetMetadata(QName name) {
		List<ExternalIdentifier> externalIds = new ArrayList<>();
		for (String resourceIdentifier : planwerk.getResourceIdentifier()) {
			externalIds.add(new ExternalIdentifier(resourceIdentifier, null));
		}
		List<MetadataUrl> metadataUrls = new ArrayList<>();
		for (String dataMetadatUrl : planwerk.getDataMetadataUrl()) {
			metadataUrls.add(new MetadataUrl(dataMetadatUrl, null, null));
		}
		List<DatasetMetadata> datasetMetadatas = new ArrayList<>();
		DatasetMetadata datasetMetadata = new DatasetMetadata(name, null, null, null, metadataUrls, externalIds, null,
				null, null, null);
		datasetMetadatas.add(datasetMetadata);
		return datasetMetadatas;
	}

	@Override
	public ResourceMetadata<? extends Resource> getMetadata() {
		return wrappedProvider.getMetadata();
	}

	@Override
	public void init() {
		wrappedProvider.init();
	}

	@Override
	public void destroy() {
		wrappedProvider.destroy();
	}

	private String getTitle(Planwerk planwerk) {
		List<String> wmsTitle = planwerk.getWmsTitle();
		if (wmsTitle == null || wmsTitle.isEmpty())
			return planwerk.getName();
		return wmsTitle.stream().collect(Collectors.joining(", "));
	}

	private Map<String, List<OMElement>> createCopyOfExtendedCapabilities(
			Map<String, List<OMElement>> extendedCapabilities, List<String> serviceMetadataUrls) {
		Map<String, List<OMElement>> extendedCapabilitiesToReturn = new HashMap<>();
		for (Map.Entry<String, List<OMElement>> extendedCapabilitiesEntry : extendedCapabilities.entrySet()) {
			List<OMElement> copyOfElements = new ArrayList<>();
			for (OMElement xml : extendedCapabilitiesEntry.getValue()) {
				if (EXTENDED_CAPABILITIES.equals(xml.getQName())) {
					OMElement copyWithNewServiceMetadataUrl = createCopy(xml, serviceMetadataUrls.get(0));
					copyOfElements.add(copyWithNewServiceMetadataUrl);
				}
				else {
					copyOfElements.add(xml);
				}
			}
			extendedCapabilitiesToReturn.put(extendedCapabilitiesEntry.getKey(), copyOfElements);
		}
		return extendedCapabilitiesToReturn;
	}

	private OMElement createCopy(OMElement xml, String serviceMetadataUrl) {
		XMLStreamReader xmlStreamReader = null;
		XMLStreamWriter writer = null;
		try {
			xmlStreamReader = xml.getXMLStreamReader();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			writer = XMLOutputFactory.newFactory().createXMLStreamWriter(bos);
			XMLStreamWriterFilterBase filter = new ServiceMetadataUrlFilter(serviceMetadataUrl);
			filter.setDelegate(writer);
			copy(filter, xmlStreamReader);
			writer.close();

			String xmlFragment = new String(bos.toByteArray());
			return AXIOMUtil.stringToOM(xmlFragment);
		}
		catch (XMLStreamException e) {
			LOG.warn("Could not pass service metadata url to extended capabilities");
			return xml;
		}
		finally {
			closeQuietly(xmlStreamReader);
			closeQietly(writer);
		}
	}

	private void closeQietly(XMLStreamWriter writer) {
		if (writer != null) {
			try {
				writer.close();
			}
			catch (XMLStreamException e) {
			}
		}
	}

	private class ServiceMetadataUrlFilter extends XMLStreamWriterFilterBase {

		private final String serviceMetadataUrl;

		private String path = "";

		ServiceMetadataUrlFilter(String serviceMetadataUrl) {
			this.serviceMetadataUrl = serviceMetadataUrl;
		}

		@Override
		public void writeStartElement(String prefix, String localName, String namespaceURI) throws XMLStreamException {
			path += '/' + localName;
			super.writeStartElement(prefix, localName, namespaceURI);
		}

		@Override
		public void writeStartElement(String namespaceURI, String localName) throws XMLStreamException {
			path += '/' + localName;
			super.writeStartElement(namespaceURI, localName);
		}

		@Override
		public void writeStartElement(String localName) throws XMLStreamException {
			path += '/' + localName;
			super.writeStartElement(localName);
		}

		@Override
		public void writeEndElement() throws XMLStreamException {
			path = path.substring(0, path.lastIndexOf("/"));
			super.writeEndElement();
		}

		@Override
		protected String xmlData(String s) {
			if (path.endsWith("MetadataUrl/URL"))
				return serviceMetadataUrl;
			return s;
		}

	}

}
