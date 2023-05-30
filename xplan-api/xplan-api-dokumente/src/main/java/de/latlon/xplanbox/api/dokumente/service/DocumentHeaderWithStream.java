package de.latlon.xplanbox.api.dokumente.service;

import javax.ws.rs.core.StreamingOutput;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
public class DocumentHeaderWithStream extends DocumentHeader {

	private StreamingOutput streamingOutput;

	public DocumentHeaderWithStream(long fileSize, String mediaType, StreamingOutput streamingOutput) {
		super(fileSize, mediaType);
		this.streamingOutput = streamingOutput;
	}

	public StreamingOutput getStreamingOutput() {
		return streamingOutput;
	}

}
