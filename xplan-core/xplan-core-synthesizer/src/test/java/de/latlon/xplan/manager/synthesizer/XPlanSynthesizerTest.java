/*-
 * #%L
 * xplan-core-synthesizer - XPlan Manager Synthesizer Komponente
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
package de.latlon.xplan.manager.synthesizer;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import org.deegree.commons.tom.gml.property.Property;
import org.deegree.feature.Feature;
import org.deegree.feature.FeatureCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
class XPlanSynthesizerTest extends AbstractXplanSynthesizerTest {

	private static Path configDirectoryWithRule;

	private static Path configDirectoryWithEnum;

	private static Path configDirectoryWithCodelist;

	@BeforeAll
	static void copyRules(@TempDir Path tmpDir) throws IOException {
		configDirectoryWithRule = Files.createDirectory(tmpDir.resolve("configDirectoryWithRule"));
		createTmpDirectoryAndCopyRuleFile(configDirectoryWithRule, "xplan41.syn",
				"XP_BesondereArtDerBaulNutzung-XPlan4.xml");

		configDirectoryWithEnum = Files.createDirectory(tmpDir.resolve("configDirectoryWithEnum"));
		createTmpDirectoryAndCopyRuleFile(configDirectoryWithEnum, "xplan41_XP_BesondereArtDerBaulNutzung.syn",
				"XP_BesondereArtDerBaulNutzung-XPlan4.xml");

		configDirectoryWithCodelist = Files.createDirectory(tmpDir.resolve("configDirectoryWithCodelist"));
		createTmpDirectoryAndCopyRuleFile(configDirectoryWithCodelist, "xplan41_BP_DetailArtDerBaulNutzung.syn",
				"BP_DetailArtDerBaulNutzung.xml");
	}

	@Test
	void testSynthesize_ConfigDirectoryWithRule() throws Exception {
		SynRulesAccessor synRulesAccessor = new SynRulesAccessor(configDirectoryWithRule);
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(synRulesAccessor);

		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		FeatureCollection synthesizedFeatures = xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		Iterator<Feature> it = synthesizedFeatures.iterator();
		while (it.hasNext()) {
			Feature feature = it.next();
			if ("BP_BaugebietsTeilFlaeche".equals(feature.getName().getLocalPart())) {
				List<Property> properties = feature
					.getProperties(new QName(feature.getName().getNamespaceURI(), "besondereArtDerBaulNutzung"));

				assertThat(properties.get(0).getValue().toString()).isIn("Art2", "Art5");
			}
		}
	}

	@Test
	void testSynthesize_Synthesize_Enumeration_XP_BesondereArtDerBaulNutzung() throws Exception {
		SynRulesAccessor synRulesAccessor = new SynRulesAccessor(configDirectoryWithEnum);
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(synRulesAccessor);

		XPlanArchive archive = getTestArchive("xplan41/LA22.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		FeatureCollection synthesizedFeatures = xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		String firstPropertyValue = valueOfFirstProperty(synthesizedFeatures, "BP_BaugebietsTeilFlaeche",
				"besondereArtDerBaulNutzung");
		assertEquals("Art2", firstPropertyValue);
	}

	@Test
	void testSynthesize_Synthesize_Codelist_BP_DetailArtDerBaulNutzung() throws Exception {
		SynRulesAccessor synRulesAccessor = new SynRulesAccessor(configDirectoryWithCodelist);
		XPlanSynthesizer xPlanSynthesizer = new XPlanSynthesizer(synRulesAccessor);

		XPlanArchive archive = getTestArchive("xplan41/BP2070-detailierteArtDerBaulNutzung.zip");
		XPlanFeatureCollection xplanFc = parseFeatureCollection(archive);
		FeatureCollection synthesizedFeatures = xPlanSynthesizer.synthesize(archive.getVersion(), xplanFc);

		String firstPropertyValue = valueOfFirstProperty(synthesizedFeatures, "BP_BaugebietsTeilFlaeche",
				"detaillierteArtDerBaulNutzung");
		assertEquals("Wohngebiet11", firstPropertyValue);
	}

	private XPlanFeatureCollection parseFeatureCollection(XPlanArchive archive) throws Exception {
		XPlanFeatureCollection xplanFc = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseXPlanFeatureCollection(archive);
		int id = 1;
		for (Feature feature : xplanFc.getFeatures()) {
			feature.setId("FEATURE_" + id++);
		}
		return xplanFc;
	}

	private static void createTmpDirectoryAndCopyRuleFile(Path targetDir, String synFile, String codelistFile)
			throws IOException {
		copyFile(targetDir, synFile, "xplan41.syn");
		copyFile(targetDir, codelistFile, codelistFile);
	}

	private static void copyFile(Path targetDir, String fileName, String targetFileName) throws IOException {
		Path targetFile = targetDir.resolve(targetFileName);
		try (InputStream resourceAsStream = XPlanSynthesizerTest.class.getResourceAsStream(fileName)) {
			Files.copy(resourceAsStream, targetFile);
		}
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
