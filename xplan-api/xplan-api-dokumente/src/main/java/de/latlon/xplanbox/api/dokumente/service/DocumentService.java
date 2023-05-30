package de.latlon.xplanbox.api.dokumente.service;

import de.latlon.xplanbox.api.dokumente.exception.InvalidDocument;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;

import java.util.List;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public interface DocumentService {

	List<Document> listDocuments(int planId);

	DocumentHeader retrieveHeaderOfArtefact(int planIdAsInt, String fileName) throws InvalidDocument;

	DocumentHeaderWithStream writeArtefactToStream(int planId, String fileName) throws InvalidDocument;

}
