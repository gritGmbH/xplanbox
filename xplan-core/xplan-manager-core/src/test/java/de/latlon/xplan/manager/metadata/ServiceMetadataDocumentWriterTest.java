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

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.xmlunit.matchers.EvaluateXPathMatcher.hasXPath;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ServiceMetadataDocumentWriterTest {

	private static final String DATE = "2017-01-03";

	private static final String TYPE = "service";

	private static final String TITLE = "Alsterdorf20";

	@Test
	public void testWriteServiceMetadataDocument() throws Exception {
		byte[] template = IOUtils.toByteArray(ServiceMetadataDocumentWriterTest.class
			.getResourceAsStream("iso-service-metadata-example-template.xml"));

		ByteArrayOutputStream serviceMetadataInstance = new ByteArrayOutputStream();

		ServiceMetadataDocumentWriter serviceMetadataDocumentWriter = new ServiceMetadataDocumentWriter(template);
		serviceMetadataDocumentWriter.writeServiceMetadataDocument(properties(), serviceMetadataInstance);

		assertThat(serviceMetadataInstance.toString(),
				hasXPath("//gmd:MD_Metadata/gmd:dateStamp/gco:Date", is(DATE)).withNamespaceContext(nsContext()));

		assertThat(serviceMetadataInstance.toString(),
				hasXPath("//gmd:MD_Metadata/gmd:hierarchyLevel/gmd:MD_ScopeCode", is(TYPE))
					.withNamespaceContext(nsContext()));

		assertThat(serviceMetadataInstance.toString(),
				hasXPath("//gmd:MD_Metadata/gmd:hierarchyLevel/gmd:MD_ScopeCode/@codeListValue", is(TYPE))
					.withNamespaceContext(nsContext()));

		assertThat(serviceMetadataInstance.toString(),
				hasXPath("//gmd:MD_Metadata/gmd:hierarchyLevelName/gco:CharacterString", is(TYPE))
					.withNamespaceContext(nsContext()));

		assertThat(serviceMetadataInstance.toString(),
				hasXPath("//gmd:MD_Metadata/gmd:metadataStandardName/gco:CharacterString", is("NOVALUE"))
					.withNamespaceContext(nsContext()));

		assertThat(serviceMetadataInstance.toString(), hasXPath(
				"//gmd:identificationInfo/srv:SV_ServiceIdentification/gmd:citation/gmd:CI_Citation/gmd:title/gco:CharacterString",
				is("WMS Bebauungsplan " + TITLE))
			.withNamespaceContext(nsContext()));
	}

	private Properties properties() {
		Properties properties = new Properties();
		properties.setProperty("CURRENT_DATE", DATE);
		properties.setProperty("MD_SCOPE_CODE", TYPE);
		properties.setProperty("TITLE", TITLE);
		return properties;
	}

	private Map<String, String> nsContext() {
		Map<String, String> nsContext = new HashMap<>();
		nsContext.put("gmd", "http://www.isotc211.org/2005/gmd");
		nsContext.put("gco", "http://www.isotc211.org/2005/gco");
		nsContext.put("srv", "http://www.isotc211.org/2005/srv");
		return nsContext;
	}

}
