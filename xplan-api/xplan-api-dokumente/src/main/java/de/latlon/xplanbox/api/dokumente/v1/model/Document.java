package de.latlon.xplanbox.api.dokumente.v1.model;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import static de.latlon.xplan.commons.util.TextPatternConstants.NAME_PATTERN;
import static de.latlon.xplan.commons.util.TextPatternConstants.S_LENGTH;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 6.1
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Document {

	@Size(max = S_LENGTH)
	@Pattern(regexp = NAME_PATTERN)
	private @Valid String fileName;

	public String getFileName() {
		return fileName;
	}

	public Document fileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
