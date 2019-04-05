package de.latlon.xplan.commons.archive;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;

/**
 * ZipEntry implementation where each entry allows access to the content.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ArtefactEntry extends ArchiveZipEntry implements ZipEntryWithContent {

    private final byte[] content;

    /**
     * Create entry with content.
     *
     * @param entry
     *                 the encapsulated entry, never <code>null</code>
     * @param content
     *                 of the entry, never <code>null</code>
     */
    public ArtefactEntry( ZipEntry entry, byte[] content ) {
        super( entry );
        this.content = content;
    }

    /**
     * @return of the entry, never <code>null</code>
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * @return of the entry, never <code>null</code>
     */
    public InputStream retrieveContentAsStream() {
        return new ByteArrayInputStream( content );
    }

}