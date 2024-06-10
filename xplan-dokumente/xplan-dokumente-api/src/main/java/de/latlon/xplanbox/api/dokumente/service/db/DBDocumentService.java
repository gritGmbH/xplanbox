/*-
 * #%L
 * xplan-dokumente-api - Software zur Verwaltung von XPlanGML Daten
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
package de.latlon.xplanbox.api.dokumente.service.db;

import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.repository.ArtefactRepository;
import de.latlon.xplanbox.api.dokumente.exception.InvalidDocument;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeader;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeaderWithStream;
import de.latlon.xplanbox.api.dokumente.service.DocumentService;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.ws.rs.core.StreamingOutput;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import static de.latlon.xplan.core.manager.db.model.ArtefactType.XPLANGML;

/**
 * Implementation of a {@link DocumentService} retrieving the documents from the database
 * (xplanmgr schema).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Service
@Profile("!s3img & !s3doc")
public class DBDocumentService implements DocumentService {

	@Autowired
	private ArtefactRepository artefactRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Document> listDocuments(int planId) {
		Stream<Artefact> allArtefactsOfPlan = artefactRepository.findAllByPlanId(planId);
		return allArtefactsOfPlan.filter(artefact -> XPLANGML != artefact.getArtefacttype())
			.map(artefact -> new Document().fileName(artefact.getId().getFilename()))
			.collect(Collectors.toList());
	}

	@Override
	public DocumentHeader retrieveHeader(int planId, String fileName) throws InvalidDocument {
		Optional<Artefact> artefactCandidate = artefactRepository.findByPlanAndFilename(planId, fileName);
		if (!artefactCandidate.isPresent())
			throw new InvalidDocument(planId, fileName);
		Artefact artefact = artefactCandidate.get();
		return new DocumentHeader(artefact.getLength(), artefact.getMimetype());
	}

	@Override
	public DocumentHeaderWithStream retrieveDocumentAndHeader(int planId, String fileName) throws InvalidDocument {
		Optional<Artefact> artefactCandidate = artefactRepository.findByPlanAndFilename(planId, fileName);
		if (!artefactCandidate.isPresent())
			throw new InvalidDocument(planId, fileName);
		Artefact artefact = artefactCandidate.get();
		byte[] data = artefact.getData();
		try (ByteArrayInputStream bis = new ByteArrayInputStream(data)) {
			GZIPInputStream is = new GZIPInputStream(bis);
			StreamingOutput streamingOutput = outputStream -> is.transferTo(outputStream);
			return new DocumentHeaderWithStream(artefact.getLength(), artefact.getMimetype(), streamingOutput);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
