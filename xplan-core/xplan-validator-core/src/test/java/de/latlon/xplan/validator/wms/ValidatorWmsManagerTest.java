/*-
 * #%L
 * xplan-validator-core - XPlan Validator Core Komponente
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
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

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanFeatureCollection;
import de.latlon.xplan.commons.feature.XPlanGmlParser;
import de.latlon.xplan.manager.synthesizer.XPlanSynthesizer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
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

	private static Path workspaceLocation;

	@BeforeClass
	public static void initWorkspace() throws IOException {
		workspaceLocation = Files.createTempDirectory("ValidatorWmsManagerTest");
	}

	@Test
	public void testInsert() throws Exception {
		XPlanSynthesizer synthesizer = new XPlanSynthesizer();
		ValidatorWmsManager validatorWmsManager = new ValidatorWmsManager(synthesizer, workspaceLocation);

		XPlanFeatureCollection featureCollection = parseFeatureCollection("xplan51/BP2070.zip");
		validatorWmsManager.insert(featureCollection);

		List<Path> dataFiles = Files.list(workspaceLocation.resolve("data")).collect(Collectors.toList());
		assertThat(dataFiles.size(), is(1));
	}

	private XPlanFeatureCollection parseFeatureCollection(String name) throws Exception {
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip(name,
				ResourceAccessor.readResourceStream(name));
		return new XPlanGmlParser().parseXPlanFeatureCollection(archive);

	}

}
