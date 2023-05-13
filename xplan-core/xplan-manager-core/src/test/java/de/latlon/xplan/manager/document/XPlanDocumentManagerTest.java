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
package de.latlon.xplan.manager.document;

import de.latlon.xplan.ResourceAccessor;
import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.commons.reference.ExternalReference;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class XPlanDocumentManagerTest {

	@Test
	public void testImportDocuments() throws Exception {
		DocumentStorage storage = mock(DocumentStorage.class);
		ApplicationEventPublisher applicationEventPublisher = mock(ApplicationEventPublisher.class);
		XPlanDocumentManager xPlanDocumentManager = new XPlanDocumentManager(storage, applicationEventPublisher);

		InputStream inputStream = ResourceAccessor.readResourceStream("xplan60/StErhVO_Hamm_60.zip");
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip("StErhVO_Hamm_60.zip", inputStream);
		FeatureCollection featureCollection = XPlanGmlParserBuilder.newBuilder().build()
				.parseFeatureCollection(archive);

		xPlanDocumentManager.importDocuments(1, featureCollection, archive);

		DocumentStorageEvent documentStorageEvent = mock(DocumentStorageEvent.class);
		verify(storage).importDocuments(eq(1), eq(archive), argThat(list -> list.contains("StErhVO_Hamm.pdf")),
				documentStorageEvent);
	}

	@Test
	public void testUpdateDocuments() throws Exception {
		DocumentStorage storage = mock(DocumentStorage.class);
		ApplicationEventPublisher applicationEventPublisher = mock(ApplicationEventPublisher.class);
		DocumentStorageEvent documentStorageEvent = mock(DocumentStorageEvent.class);
		XPlanDocumentManager xPlanDocumentManager = new XPlanDocumentManager(storage, applicationEventPublisher);

		String referenceToAdd = "test.png";
		String referenceToRemove = "removed.png";
		Path uploadedArtefact = createMockedPath(referenceToAdd);
		List<ExternalReference> documentsToAdd = Collections.singletonList(new ExternalReference(referenceToAdd));
		List<ExternalReference> documentsToRemove = Collections.singletonList(new ExternalReference(referenceToRemove));
		xPlanDocumentManager.updateDocuments(1, Collections.singletonList(uploadedArtefact), documentsToAdd,
				documentsToRemove);
		verify(storage).importDocument(eq(1), eq(referenceToAdd), eq(uploadedArtefact), documentStorageEvent);
		verify(storage).deleteDocument(eq(1), eq(referenceToRemove), documentStorageEvent);
	}

	private static Path createMockedPath(String referenceToAdd) {
		Path uploadedArtefact = mock(Path.class);
		when(uploadedArtefact.getFileName()).thenReturn(uploadedArtefact);
		when(uploadedArtefact.toString()).thenReturn(referenceToAdd);
		return uploadedArtefact;
	}

}
