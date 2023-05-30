package de.latlon.xplanbox.api.dokumente.service;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class DocumentHeader {

	private final long fileSize;

	private final String mediaType;

	public DocumentHeader(long fileSize, String mediaType) {
		this.fileSize = fileSize;
		this.mediaType = mediaType;
	}

	public long getFileSize() {
		return fileSize;
	}

	public String getMediaType() {
		return mediaType;
	}

}
