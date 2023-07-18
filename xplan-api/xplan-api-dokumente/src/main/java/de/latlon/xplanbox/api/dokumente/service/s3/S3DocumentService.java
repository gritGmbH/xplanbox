package de.latlon.xplanbox.api.dokumente.service.s3;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import de.latlon.xplan.manager.storage.s3.S3Metadata;
import de.latlon.xplan.manager.storage.s3.S3Object;
import de.latlon.xplan.manager.storage.s3.S3Storage;
import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import de.latlon.xplanbox.api.dokumente.exception.InvalidDocument;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeader;
import de.latlon.xplanbox.api.dokumente.service.DocumentHeaderWithStream;
import de.latlon.xplanbox.api.dokumente.service.DocumentService;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.StreamingOutput;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * Implementation of a {@link DocumentService} retrieving the documents from S3.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
@Service
@Profile("s3img & s3doc")
public class S3DocumentService implements DocumentService {

	@Autowired
	private S3Storage documentStorage;

	@Override
	public List<Document> listDocuments(int planId) {
		String prefix = planId + "_";
		List<S3ObjectSummary> s3ObjectSummaries = documentStorage.listObjects(prefix);
		return s3ObjectSummaries.stream()
				.map(s3ObjectSummary -> new Document().fileName(s3ObjectSummary.getKey().replaceFirst(prefix, "")))
				.collect(Collectors.toList());
	}

	@Override
	public DocumentHeader retrieveHeader(int planId, String fileName) throws InvalidDocument, StorageException {
		String key = planId + "_" + fileName;
		try {
			S3Metadata objectMetadata = documentStorage.getObjectMetadata(key);
			return new DocumentHeader(objectMetadata.getContentLength(), objectMetadata.getContentType());
		}
		catch (StorageException e) {
			if (e.getStatusCode() == NOT_FOUND.getStatusCode())
				throw new InvalidDocument(planId, fileName);
			throw e;
		}
	}

	@Override
	public DocumentHeaderWithStream retrieveDocumentAndHeader(int planId, String fileName)
			throws InvalidDocument, StorageException {
		String key = planId + "_" + fileName;
		try {
			S3Object object = documentStorage.getObject(key);
			S3Metadata objectMetadata = object.getS3Metadata();
			StreamingOutput streamingOutput = outputStream -> new ByteArrayInputStream(object.getContent())
					.transferTo(outputStream);
			return new DocumentHeaderWithStream(objectMetadata.getContentLength(), objectMetadata.getContentType(),
					streamingOutput);
		}
		catch (StorageException e) {
			if (e.getStatusCode() == NOT_FOUND.getStatusCode())
				throw new InvalidDocument(planId, fileName);
			throw e;
		}
	}

}