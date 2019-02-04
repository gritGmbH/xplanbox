package de.latlon.xplan.commons.archive;

import de.latlon.xplan.commons.XPlanVersion;

import javax.xml.stream.XMLStreamReader;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface SemanticValidableXPlanArchive {

    XPlanVersion getVersion();

    XMLStreamReader getMainFileXmlReader();
}
