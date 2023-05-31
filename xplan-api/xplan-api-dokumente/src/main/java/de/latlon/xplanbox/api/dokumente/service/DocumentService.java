package de.latlon.xplanbox.api.dokumente.service;

import de.latlon.xplan.manager.wmsconfig.raster.storage.StorageException;
import de.latlon.xplanbox.api.dokumente.exception.InvalidDocument;
import de.latlon.xplanbox.api.dokumente.v1.model.Document;

import java.util.List;

/**
 * Interface to access documents.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public interface DocumentService {

	/**
	 * @param planId id of the plan
	 * @return the list of all documents of the plan with the passed planId
	 */
	List<Document> listDocuments(int planId);

	/**
	 * @param planId id of the plan
	 * @param fileName, name of the file to return header information, never
	 * <code>null</code>
	 * @return the header information of the document
	 * @throws InvalidDocument if a document with the passed fileName for the plan with
	 * the passed planId does not exist
	 * @throws StorageException if the document is not available
	 */
	DocumentHeader retrieveHeaderOfDocument(int planId, String fileName) throws InvalidDocument, StorageException;

	/**
	 * @param planId id of the plan
	 * @param fileName, name of the file to return header information, never
	 * <code>null</code>
	 * @return the document and header information of the document
	 * @throws InvalidDocument if a document with the passed fileName for the plan with
	 * the passed planId does not exist
	 * @throws StorageException if the document is not available
	 */
	DocumentHeaderWithStream retrieveDocumentAndHeader(int planId, String fileName)
			throws InvalidDocument, StorageException;

}
