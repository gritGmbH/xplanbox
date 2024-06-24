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
package de.latlon.xplan.manager.document;

import de.latlon.xplan.commons.archive.XPlanArchive;
import de.latlon.xplan.commons.archive.XPlanArchiveCreator;
import de.latlon.xplan.commons.feature.XPlanGmlParserBuilder;
import de.latlon.xplan.commons.reference.ExternalReferenceInfo;
import de.latlon.xplan.commons.reference.ExternalReferenceScanner;
import de.latlon.xplan.manager.edit.EditedArtefact;
import de.latlon.xplan.manager.edit.EditedArtefacts;
import de.latlon.xplan.manager.storage.StorageEvent;
import org.deegree.feature.FeatureCollection;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

import static de.latlon.xplan.manager.edit.ArtefactType.NONRASTER;
import static de.latlon.xplan.manager.edit.EditType.ADDED;
import static de.latlon.xplan.manager.edit.EditType.REMOVED;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class XPlanDocumentManagerTest {

	@Test
	public void testImportDocuments() throws Exception {
		DocumentStorage storage = mock(DocumentStorage.class);
		ApplicationEventPublisher applicationEventPublisher = mock(ApplicationEventPublisher.class);
		XPlanDocumentManager xPlanDocumentManager = new XPlanDocumentManager(storage, applicationEventPublisher);

		InputStream inputStream = getClass().getResourceAsStream("/testdata/xplan60/StErhVO_Hamm_60.zip");
		XPlanArchiveCreator archiveCreator = new XPlanArchiveCreator();
		XPlanArchive archive = archiveCreator.createXPlanArchiveFromZip("StErhVO_Hamm_60.zip", inputStream);
		FeatureCollection featureCollection = XPlanGmlParserBuilder.newBuilder()
			.build()
			.parseFeatureCollection(archive);
		ExternalReferenceScanner externalReferenceScanner = new ExternalReferenceScanner();
		ExternalReferenceInfo externalReferenceInfo = externalReferenceScanner.scan(featureCollection,
				archive.getVersion());
		xPlanDocumentManager.importDocuments(1, externalReferenceInfo, archive);

		verify(storage).importDocuments(eq(1), eq(archive), argThat(list -> list.contains("StErhVO_Hamm.pdf")),
				any(StorageEvent.class));
		verify(applicationEventPublisher).publishEvent(any(StorageEvent.class));
	}

	@Test
	public void testUpdateDocuments() throws Exception {
		DocumentStorage storage = mock(DocumentStorage.class);
		ApplicationEventPublisher applicationEventPublisher = mock(ApplicationEventPublisher.class);
		XPlanDocumentManager xPlanDocumentManager = new XPlanDocumentManager(storage, applicationEventPublisher);

		String referenceToAdd = "test.png";
		String referenceToRemove = "removed.png";
		Path uploadedArtefact = createMockedPath(referenceToAdd);
		EditedArtefact documentToAdd = new EditedArtefact(referenceToAdd, null, NONRASTER, ADDED);
		EditedArtefact documentToRemove = new EditedArtefact(referenceToRemove, null, NONRASTER, REMOVED);

		EditedArtefacts editedArtefacts = new EditedArtefacts(Arrays.asList(documentToAdd, documentToRemove));
		xPlanDocumentManager.updateDocuments(1, Collections.singletonList(uploadedArtefact), editedArtefacts);
		verify(storage).importDocument(eq(1), eq(referenceToAdd), eq(uploadedArtefact), any(StorageEvent.class));
		verify(storage).deleteDocument(eq(1), eq(referenceToRemove), any(StorageEvent.class));
		verify(applicationEventPublisher).publishEvent(any(StorageEvent.class));
	}

	private static Path createMockedPath(String referenceToAdd) {
		Path uploadedArtefact = mock(Path.class);
		when(uploadedArtefact.getFileName()).thenReturn(uploadedArtefact);
		when(uploadedArtefact.toString()).thenReturn(referenceToAdd);
		return uploadedArtefact;
	}

}
