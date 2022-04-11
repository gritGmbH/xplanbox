/*-
 * #%L
 * xplan-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.XPlanVersion;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class XPlanSynthesizerTest extends AbstractXplanSynthesizerTest {

	@Override
	XPlanVersion getXPlanVersion() {
		return XPLAN_41;
	}

	@Test
	public void testSynthesize_DefaultConfiguration() throws Exception {
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer();

		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		assertThat(xPlanSynthesizer.getRules().size(), is(7250));
	}

	@Test
	public void testSynthesize_ConfigDirectoryIsInvalid() throws Exception {
		Path notExist = Paths.get("/tmp/notExistDir");
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(notExist);

		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		assertThat(xPlanSynthesizer.getRules().size(), is(7250));
	}

	@Test
	public void testSynthesize_ConfigDirectoryIsFile() throws Exception {
		Path configFile = Files.createTempFile("synConfig", "");
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(configFile);

		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		assertThat(xPlanSynthesizer.getRules().size(), is(7250));
	}

	@Test
	public void testSynthesize_ConfigDirectoryDoesNotContainRules() throws Exception {
		Path configDirectory = createTmpDirectory();
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(configDirectory);

		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		assertThat(xPlanSynthesizer.getRules().size(), is(7250));
	}

	@Test
	public void testSynthesize_ConfigDirectoryWithRule() throws Exception {
		Path configDirectory = createTmpDirectoryAndCopyRuleFile("xplan41.syn",
				"XP_BesondereArtDerBaulNutzung-XPlan4.xml");
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(configDirectory);

		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		FeatureCollection synthesizedFeatures = xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		assertThat(xPlanSynthesizer.getRules().size(), is(7251));
		Iterator<Feature> it = synthesizedFeatures.iterator();
		while (it.hasNext()) {
			Feature feature = it.next();
			if ("BP_BaugebietsTeilFlaeche".equals(feature.getName().getLocalPart())) {
				List<Property> properties = feature
						.getProperties(new QName(feature.getName().getNamespaceURI(), "besondereArtDerBaulNutzung"));
				assertThat(properties.get(0).getValue().toString(), anyOf(is("Art2"), is("Art5")));
			}
		}
	}

	@Test
	public void testSynthesize_Synthesize_Enumeration_XP_BesondereArtDerBaulNutzung() throws Exception {
		Path configDirectory = createTmpDirectoryAndCopyRuleFile("xplan41_XP_BesondereArtDerBaulNutzung.syn",
				"XP_BesondereArtDerBaulNutzung-XPlan4.xml");
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(configDirectory);

		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		FeatureCollection synthesizedFeatures = xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		assertThat(xPlanSynthesizer.getRules().size(), is(7250));

		String firstPropertyValue = valueOfFirstProperty(synthesizedFeatures, "BP_BaugebietsTeilFlaeche",
				"besondereArtDerBaulNutzung");
		assertThat(firstPropertyValue, is("Art2"));
	}

	@Test
	public void testSynthesize_Synthesize_Codelist_BP_DetailArtDerBaulNutzung() throws Exception {
		Path configDirectory = createTmpDirectoryAndCopyRuleFile("xplan41_BP_DetailArtDerBaulNutzung.syn",
				"BP_DetailArtDerBaulNutzung.xml");
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(configDirectory);

		XPlanArchive archive = getTestArchive("xplan41/BP2070-detailierteArtDerBaulNutzung.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		FeatureCollection synthesizedFeatures = xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		assertThat(xPlanSynthesizer.getRules().size(), is(7250));

		String firstPropertyValue = valueOfFirstProperty(synthesizedFeatures, "BP_BaugebietsTeilFlaeche",
				"detaillierteArtDerBaulNutzung");
		assertThat(firstPropertyValue, is("Wohngebiet11"));
	}

	private XPlanFeatureCollection parseFeatureCollection(XPlanArchive archive) throws Exception {
		XPlanFeatureCollection xplanFc = readFeatures(archive);
		int id = 1;
		for (Feature feature : xplanFc.getFeatures()) {
			feature.setId("FEATURE_" + id++);
		}
		return xplanFc;
	}

	private Path createTmpDirectoryAndCopyRuleFile(String synFile, String codelistFile) throws IOException {
		Path tmpDirectory = createTmpDirectory();
		copyFile(tmpDirectory, synFile, "xplan41.syn");
		copyFile(tmpDirectory, codelistFile, codelistFile);
		return tmpDirectory;
	}

	private void copyFile(Path tmpDirectory, String fileName, String targetFileName) throws IOException {
		Path targetFile = tmpDirectory.resolve(targetFileName);
		InputStream resourceAsStream = XPlanSynthesizerTest.class.getResourceAsStream(fileName);
		try {
			Files.copy(resourceAsStream, targetFile);
		}
		finally {
			closeQuietly(resourceAsStream);
		}
	}

	private Path createTmpDirectory() throws IOException {
		return Files.createTempDirectory("configDirectory");
	}

	private String valueOfFirstProperty(FeatureCollection synthesizedFeatures, String featureType,
			String propertyName) {
		Iterator<Feature> it = synthesizedFeatures.iterator();
		while (it.hasNext()) {
			Feature feature = it.next();
			if (featureType.equals(feature.getName().getLocalPart())) {
				List<Property> properties = feature
						.getProperties(new QName(feature.getName().getNamespaceURI(), propertyName));

				return properties.get(0).getValue().toString();
			}

		}
		return null;
	}

}
