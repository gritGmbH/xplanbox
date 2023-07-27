package de.latlon.xplan.manager.storage.s3;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
public class S3Object {

	private final S3Metadata s3Metadata;

	private final byte[] content;

	public S3Object(S3Metadata s3Metadata, byte[] content) {
		this.s3Metadata = s3Metadata;
		this.content = content;
	}

	public S3Metadata getS3Metadata() {
		return s3Metadata;
	}

	public byte[] getContent() {
		return content;
	}

}
