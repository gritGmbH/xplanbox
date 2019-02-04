package de.latlon.xplan.commons.archive;

import static de.latlon.xplan.commons.archive.XPlanArchiveCreator.MAIN_FILE;
import static java.lang.String.format;
import static org.deegree.commons.xml.stax.XMLStreamUtils.skipStartDocument;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.deegree.cs.coordinatesystems.ICRS;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;

/**
 * Provides easy access to the metadata and contents of an XPlan archive.
 * <p>
 * An XPlan archive is a ZIP file with a defined structure:
 * <ul>
 * <li><code>xplan.gml</code> (mandatory, main XPlan feature collection)</li>
 * <li><code>*</code> (optional, other artifacts referenced by the main file)</li>
 * </ul>
 * </p>
 * 
 * @author <a href="mailto:schneider@occamlabs.de">Markus Schneider</a>
 * @since 1.0
 */
public class XPlanArchive implements XPlanArchiveContentAccess, SemanticValidableXPlanArchive {

    private final List<ZipEntryWithContent> zipFileEntries;

    private final String name;

    private final XPlanVersion version;

    private final XPlanAde ade;

    private final XPlanType type;

    private final ICRS crs;

    private final String district;

    XPlanArchive( List<ZipEntryWithContent> zipEntries, String name, XPlanVersion version, XPlanAde ade, XPlanType type,
                  ICRS crs, String district ) {
        this.zipFileEntries = zipEntries;
        this.name = name;
        this.version = version;
        this.ade = ade;
        this.type = type;
        this.crs = crs;
        this.district = district;
    }

    /**
     * Returns the XPlan name.
     * 
     * @return name, never <code>null</code>
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the XPlan version.
     * 
     * @return version, never <code>null</code>
     */
    @Override
    public XPlanVersion getVersion() {
        return version;
    }

    /**
     * Returns the XPlan ADE.
     * 
     * @return ade, may be <code>null</code>
     */
    public XPlanAde getAde() {
        return ade;
    }

    /**
     * Returns the XPlan type.
     * 
     * @return type, never <code>null</code>
     */
    public XPlanType getType() {
        return type;
    }

    /**
     * Returns the CRS.
     * 
     * @return crs, can be <code>null</code> (unspecified)
     */
    public ICRS getCrs() {
        return crs;
    }

    /**
     * Returns the district
     * 
     * @return district, can be <code>null</code>
     */
    public String getDistrict() {
        return district;
    }

    @Override
    public List<? extends ArchiveEntry> getZipFileEntries() {
        return zipFileEntries;
    }

    /**
     * Retrieve a <link>InputStream</link> returning the main file of this archive
     * 
     * @return the main file as <link>InputStream</link>
     */
    public InputStream getMainFileInputStream() {
        return new ByteArrayInputStream( getMainFile().getContent() );
    }

    /**
     * Returns a reader for the XML of the main file. Start document is skipped.
     * 
     * @return reader, never <code>null</code>
     */
    @Override
    public XMLStreamReader getMainFileXmlReader() {
        try {
            XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader( getMainFileInputStream() );
            skipStartDocument( xmlReader );
            return xmlReader;
        } catch ( Exception e ) {
            String message = format( "Kann Datei '%s' aus '%s' nicht lesen. Fehlermeldung: %s", MAIN_FILE, this.name,
                                     e.getLocalizedMessage() );
            throw new IllegalArgumentException( message, e );
        }
    }

    @Override
    public ArchiveEntry getEntry( String name ) {
        checkNameParameter( name );
        for ( ZipEntryWithContent zipEntry : zipFileEntries ) {
            if ( name.equals( zipEntry.getName() ) )
                return zipEntry;
        }
        return null;
    }

    @Override
    public InputStream retrieveInputStreamFor( String name ) {
        ArchiveEntry entry = getEntry( name );
        if ( entry != null )
            return ( (ZipEntryWithContent) entry ).retrieveContentAsStream();
        String message = format( "Zip entry with the name%scould not be found in archive%s", name, this.name );
        throw new IllegalArgumentException( message );
    }

    @Override
    public String toString() {
        return format( "[%s, %s, %s]", version, type, crs != null ? crs.getName() : "undefiniertes Bezugssystem" );
    }

    private ZipEntryWithContent getMainFile() {
        for ( ZipEntryWithContent zipEntry : zipFileEntries ) {
            if ( MAIN_FILE.equals( zipEntry.getName() ) )
                return zipEntry;
        }
        String msg = format( "%s ist kein gültiges XPlan-Archiv (enthält keine Datei mit Namen '%s').", this.name,
                             MAIN_FILE );
        throw new IllegalArgumentException( msg );
    }

    private void checkNameParameter( String name ) {
        if ( name == null )
            throw new IllegalArgumentException( "Name to detect the zip entry must not be null." );
    }

}