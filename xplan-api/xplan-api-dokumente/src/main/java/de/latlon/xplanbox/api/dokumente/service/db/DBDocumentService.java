package de.latlon.xplanbox.api.dokumente.service.db;

import de.latlon.xplan.core.manager.db.model.Artefact;
import de.latlon.xplan.core.manager.db.repository.ArtefactRepository;
import de.latlon.xplanbox.api.dokumente.exception.InvalidDocument;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeader;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeaderWithStream;
import de.latlon.xplanbox.api.dokumente.service.DocumentService;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.StreamingOutput;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

/**
 * Implementation of a {@link DocumentService} retrieving the documents from the database
 * (xplanmgr schema).
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
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
		return allArtefactsOfPlan.map(artefact -> new Document().fileName(artefact.getId().getFilename()))
				.collect(Collectors.toList());
	}

	@Override
	public DocumentHeader retrieveHeaderOfDocument(int planId, String fileName) throws InvalidDocument {
		Optional<Artefact> artefactCandidate = artefactRepository.findByPlanAndFilename(planId, fileName);
		if (!artefactCandidate.isPresent())
			throw new InvalidDocument(planId, fileName);
		Artefact artefact = artefactCandidate.get();
		return new DocumentHeader(artefact.getData().length, artefact.getMimetype());
	}

	@Override
	public DocumentHeaderWithStream retrieveDocumentAndHeader(int planId, String fileName) throws InvalidDocument {
		Optional<Artefact> artefactCandidate = artefactRepository.findByPlanAndFilename(planId, fileName);
		if (!artefactCandidate.isPresent())
			throw new InvalidDocument(planId, fileName);
		Artefact artefact = artefactCandidate.get();
		byte[] data = artefact.getData();
		try (ByteArrayInputStream bis = new ByteArrayInputStream(data);) {
			GZIPInputStream is = new GZIPInputStream(bis);
			StreamingOutput streamingOutput = outputStream -> IOUtils.copy(is, outputStream);
			return new DocumentHeaderWithStream(data.length, artefact.getMimetype(), streamingOutput);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
