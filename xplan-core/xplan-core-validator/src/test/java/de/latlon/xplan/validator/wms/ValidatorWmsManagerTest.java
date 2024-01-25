/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
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
package de.latlon.xplan.validator.wms;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import de.latlon.xplan.manager.synthesizer.rules.SynRulesAccessor;
import de.latlon.xplan.validator.wms.storage.PlanStorage;
import de.latlon.xplan.validator.wms.storage.WorkspacePlanStorage;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public class ValidatorWmsManagerTest {

	@Rule
	public TemporaryFolder tempFolder = new TemporaryFolder();

	@Test
	public void testInsert() throws Exception {
		Path workspaceLocation = tempFolder.getRoot().toPath();
		SynRulesAccessor synRulesAccessor = new SynRulesAccessor();
		XPlanSynthesizer synthesizer = new XPlanSynthesizer(synRulesAccessor);
		PlanStorage planStorage = new WorkspacePlanStorage(workspaceLocation);
		ValidatorWmsManager validatorWmsManager = new ValidatorWmsManager(synthesizer, planStorage);

		XPlanFeatureCollection featureCollection = parseFeatureCollection("xplan51/BP2070.zip");
		validatorWmsManager.insert(featureCollection);

		List<Path> dataFiles = Files.list(workspaceLocation.resolve("data")).collect(Collectors.toList());
		assertThat(dataFiles.size(), is(1));
	}

	private XPlanFeatureCollection parseFeatureCollection(String name) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(name,
				getClass().getResourceAsStream("/testdata/" + name));
		return XPlanGmlParserBuilder.newBuilder().build().parseXPlanFeatureCollection(archive);
	}

}
