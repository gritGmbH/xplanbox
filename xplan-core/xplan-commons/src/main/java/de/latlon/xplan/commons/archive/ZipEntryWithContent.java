package de.latlon.xplan.commons.archive;

import java.io.InputStream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 */
public interface ZipEntryWithContent extends ArchiveEntry {

    byte[] getContent();

    /**
     * @return of the entry, never <code>null</code>
     */
    InputStream retrieveContentAsStream();

}
