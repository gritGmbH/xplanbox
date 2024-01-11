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
package de.latlon.xplan.manager.storage.s3;

import de.latlon.xplan.commons.archive.XPlanArchiveContentAccess;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.manager.storage.s3.config.AmazonS3TestContext;
import de.latlon.xplan.manager.storage.s3.config.S3StorageTestContext;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
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
 * and secretKey in the s3Mock.properties file.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AmazonS3TestContext.class, S3StorageTestContext.class })
@ActiveProfiles({ "s3img", "mock" })
@TestPropertySource("classpath:s3Mock.properties")
public class S3StorageTestManual {

	@Autowired
	private S3Storage s3Storage;

	@Test
	public void testInsertObjectAndGetObject() throws IOException, StorageException {
		InputStream inputStream = getClass().getResourceAsStream("/testdata/xplan60/Blankenese29_Test_60.zip");
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchiveContentAccess archive = archiveCreator.createXPlanArchiveFromZip("Blankenese29_Test_60.zip",
				inputStream);

		String key = s3Storage.insertObject(1, "Blankenese29.png", archive);
		assertThat(key, is("1_Blankenese29.png"));

		S3Metadata metadata = s3Storage.getObjectMetadata(key);
		assertThat(metadata.getContentLength(), is(2180090l));
		assertThat(metadata.getContentType(), is("image/png"));

		S3Object object = s3Storage.getObject(key);
		assertThat(object.getS3Metadata().getContentLength(), is(2180090l));
		assertThat(object.getS3Metadata().getContentType(), is("image/png"));
	}

	@Test(expected = StorageException.class)
	public void testGetObject_InvalidKey() throws StorageException {
		s3Storage.getObject("invalid");
	}

	@Test(expected = StorageException.class)
	public void testGetObjectMetadata_InvalidKey() throws StorageException {
		s3Storage.getObjectMetadata("invalid");
	}

}
