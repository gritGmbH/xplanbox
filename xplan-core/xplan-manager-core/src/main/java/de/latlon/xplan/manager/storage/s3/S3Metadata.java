package de.latlon.xplan.manager.storage.s3;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class S3Metadata {

	private final String key;

	private final String contentType;

	private final long contentLength;

	public S3Metadata(String key, String contentType, long contentLength) {
		this.key = key;
		this.contentType = contentType;
		this.contentLength = contentLength;
	}

	public String getKey() {
		return key;
	}

	public String getContentType() {
		return contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

}
