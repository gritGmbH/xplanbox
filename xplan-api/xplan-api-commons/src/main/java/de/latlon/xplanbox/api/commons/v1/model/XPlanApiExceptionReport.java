package de.latlon.xplanbox.api.commons.v1.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
@XmlRootElement(name = "Exception")
@XmlAccessorType(XmlAccessType.FIELD)
public class XPlanApiExceptionReport {

    @XmlAttribute
    private String version = "1.0.0";

    @XmlElement(name = "exceptionText")
    public String exceptionText;

    public XPlanApiExceptionReport() {
    }

    public XPlanApiExceptionReport( String exceptionText ) {
        this.exceptionText = exceptionText;
    }

    public String getExceptionText() {
        return exceptionText;
    }

    public void setExceptionText( String exceptionText ) {
        this.exceptionText = exceptionText;
    }

}