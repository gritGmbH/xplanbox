/*-
 * #%L
 * xplan-core-manager - XPlan Manager Core Komponente
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
package de.latlon.xplan.manager.document.s3;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.manager.document.s3.config.AmazonS3DocumentStorageContext;
import de.latlon.xplan.manager.storage.StorageEvent;
import de.latlon.xplan.manager.storage.s3.config.AmazonS3TestContext;
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
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * ATTENTION: Executing this test class can run up the bill for the AWS account
 * configured! Ensure that the S3 client is using a free account or is substituted by a
 * mock.
 *
 * To run the IT against a AWS S3 account disable the profile "mock" and set accessKeyId
 * and secretKey in the s3Mock.properties file.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @author <a href="mailto:friebe@lat-lon.de">Torsten Friebe</a>
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { AmazonS3DocumentStorageContext.class, AmazonS3TestContext.class })
@ActiveProfiles({ "s3doc", "mock" })
@TestPropertySource("classpath:s3Mock.properties")
public class S3DocumentStorageIT {

	@Autowired
	private S3DocumentStorage s3DocumentStorage;

	@Test
	public void testImportDocuments() throws IOException, StorageException {
		InputStream inputStream = getClass().getResourceAsStream("/testdata/xplan60/StErhVO_Hamm_60.zip");
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip("StErhVO_Hamm_60.zip", inputStream);

		StorageEvent storageEvent = mock(StorageEvent.class);
		List<String> keys = s3DocumentStorage.importDocuments(1, archive, Collections.singletonList("StErhVO_Hamm.pdf"),
				storageEvent);

		assertThat(keys.size(), is(1));
		assertThat(keys.get(0), is("1_StErhVO_Hamm.pdf"));
		verify(storageEvent).addInsertedKey("1_StErhVO_Hamm.pdf");
	}

}
