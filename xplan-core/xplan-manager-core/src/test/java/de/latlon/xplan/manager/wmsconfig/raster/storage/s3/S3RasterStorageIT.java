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
package de.latlon.xplan.manager.wmsconfig.raster.storage.s3;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import de.latlon.xplan.manager.wmsconfig.raster.storage.s3.config.AmazonS3RasterStorageContext;
import de.latlon.xplan.manager.wmsconfig.raster.storage.s3.config.AmazonS3TestContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * ATTENTION: Executing this test class can run up the bill for the AWS account
 * configured! Ensure that the S3 client is using a free account or is substituted by a
 * mock.
 *
 * To run the IT against a AWS S3 account disable the profile "mock" and set accessKeyId
 * and secretKey in the s3.properties file.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AmazonS3RasterStorageContext.class, AmazonS3TestContext.class })
@ActiveProfiles({ "s3", "mock" })
@TestPropertySource("classpath:s3Mock.properties")
public class S3RasterStorageIT {

	@Autowired
	private S3RasterStorage s3RasterStorage;

	@Test
	public void testAddRasterFile() throws IOException, StorageException {
		InputStream inputStream = ResourceAccessor.readResourceStream("xplan60/Blankenese29_Test_60.zip");
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchiveContentAccess archive = archiveCreator.createXPlanArchiveFromZip("Blankenese29_Test_60.zip",
				inputStream);

		String key = s3RasterStorage.addRasterFile(1, "Blankenese29.png", archive);

		assertThat(key, is("1_Blankenese29.png"));
	}

	@Test
	public void testDeleteRasterFiles() {
		s3RasterStorage.deleteRasterFiles("1");
	}

	@Test
	public void testDeleteRasterFile() {
		s3RasterStorage.deleteRasterFiles("1", "Blankenese29");
	}

}
