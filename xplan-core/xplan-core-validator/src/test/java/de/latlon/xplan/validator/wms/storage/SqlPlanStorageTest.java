/*-
 * #%L
 * xplan-core-validator - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.wms.storage;

import de.latlon.xplan.commons.XPlanSchemas;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.FeatureCollectionManipulator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import de.latlon.xplan.validator.wms.config.TestContext;
import de.latlon.xplan.validator.wms.config.ValidatorWmsSqlContext;
import org.deegree.feature.FeatureCollection;
import org.deegree.feature.types.AppSchema;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static de.latlon.xplan.commons.XPlanVersion.XPLAN_SYN;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ValidatorWmsSqlContext.class, TestContext.class })
@ActiveProfiles("validatorwmssql")
public class SqlPlanStorageTest {

	@Autowired
	private SqlPlanStorage planStorage;

	@Test
	public void test() throws Exception {
		FeatureCollection synFeatureCollection = createSynFeatureCollection();
		planStorage.storeSynFeatureCollection(1, synFeatureCollection);
	}

	private FeatureCollection createSynFeatureCollection() throws Exception {
		XPlanFeatureCollection featureCollection = parseFeatureCollection("xplan51/BP2070.zip");
		AppSchema synSchema = XPlanSchemas.getInstance().getAppSchema(XPLAN_SYN);
		SynRulesAccessor synRulesAccessor = new SynRulesAccessor();
		XPlanSynthesizer synthesizer = new XPlanSynthesizer(synRulesAccessor);

		FeatureCollection synFeatureCollection = synthesizer.synthesize(featureCollection);
		FeatureCollectionManipulator featureCollectionManipulator = new FeatureCollectionManipulator();
		featureCollectionManipulator.addPlanIdToFeatures(synFeatureCollection, synSchema, 1);
		return synFeatureCollection;
	}

	private XPlanFeatureCollection parseFeatureCollection(String name) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(name,
				getClass().getResourceAsStream("/testdata/" + name));
		return XPlanGmlParserBuilder.newBuilder().build().parseXPlanFeatureCollection(archive);
	}

}
