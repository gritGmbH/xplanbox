package de.latlon.xplan.commons.archive;

import de.latlon.xplan.commons.XPlanAde;
import de.latlon.xplan.commons.XPlanType;
import de.latlon.xplan.commons.XPlanVersion;
import org.deegree.cs.coordinatesystems.ICRS;
import org.deegree.cs.persistence.CRSManager;

import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.Location;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static de.latlon.xplan.commons.XPlanType.valueOfDefaultNull;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_3;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_40;
import static de.latlon.xplan.commons.XPlanVersion.XPLAN_41;
import static de.latlon.xplan.commons.util.XPlanVersionUtils.determineBaseVersion;
import static java.lang.String.format;
import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static org.deegree.commons.xml.stax.XMLStreamUtils.skipStartDocument;

/**
 * Creator for {@link XPlanArchive}s.
 * 
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * 
 * @version $Revision: $, $Date: $
 */
public class XPlanArchiveCreator {

    public static final String MAIN_FILE = "xplan.gml";

    private static final QName V3_ORTSTEIL_NAME = new QName( XPLAN_3.getNamespace(), "ortsteil" );

    private static final QName V40_GEMEINDE = new QName( XPLAN_40.getNamespace(), "gemeinde" );

    private static final QName V41_GEMEINDE = new QName( XPLAN_41.getNamespace(), "gemeinde" );

    private final LocalCenterToDistrictMapper localCenterToDistrictMapper;

    public XPlanArchiveCreator() {
        this( null );
    }

    public XPlanArchiveCreator( LocalCenterToDistrictMapper localCenterToDistrictMapper ) {
        this.localCenterToDistrictMapper = localCenterToDistrictMapper;
    }

    /**
     * Creates a new {@link XPlanArchive} instance from the given file.
     * 
     * @param file
     *            XPlan archive (ZIP-file), must not be <code>null</code>
     * @throws IllegalArgumentException
     *             if the file can not be read or is obviously invalid
     */
    public XPlanArchive createXPlanArchive( File file )
                    throws IOException {
        return createXPlanArchive( file.getName(), new FileInputStream( file ) );
    }

    /**
     * @param name
     *            never <code>null</code>
     * @param inputStream
     *            never <code>null</code> and is closed on return
     * @throws IOException
     */
    public XPlanArchive createXPlanArchive( String name, InputStream inputStream )
                    throws IOException {
        try {
            ZipEntryWithContent mainZipEntry = null;
            List<ZipEntryWithContent> zipEntries = new ArrayList<>();
            ZipEntryWithContent mainEntry = readEntries( inputStream, mainZipEntry, zipEntries );
            return parseXPlanArchive( name, mainEntry, zipEntries );
        } catch ( XMLStreamException | FactoryConfigurationError e ) {
            String message = format( "Kann Archiv '%s' nicht lesen. Fehlermeldung: %s", name, e.getLocalizedMessage() );
            throw new IllegalArgumentException( message, e );
        } finally {
            inputStream.close();
        }
    }

    private XPlanArchive parseXPlanArchive( String name, ZipEntryWithContent mainEntry,
                                            List<ZipEntryWithContent> zipEntries )
                    throws XMLStreamException, FactoryConfigurationError {
        XMLStreamReader xmlReader = createReader( mainEntry );
        try {
            Location crsLocation = null;
            ICRS crs = null;
            Location typeLocation = null;
            XPlanType type = null;
            XPlanAde ade = null;
            String district = null;
            XPlanVersion version = determineBaseVersion( xmlReader.getName() );
            do {
                if ( xmlReader.isStartElement() ) {
                    // ade
                    if ( ade == null && version == XPlanVersion.XPLAN_41 ) {
                        ade = determineAde( xmlReader.getNamespaceURI() );
                    }
                    // crs
                    String srsName = xmlReader.getAttributeValue( null, "srsName" );
                    if ( srsName != null ) {
                        if ( crs == null ) {
                            crs = CRSManager.getCRSRef( srsName );
                            crsLocation = xmlReader.getLocation();
                        } else if ( !crs.getName().equals( srsName ) ) {
                            throwCrsInConflictException( xmlReader, crsLocation, crs, srsName );
                        }
                    }
                    // type
                    XPlanType typeFromElement = valueOfDefaultNull( xmlReader.getLocalName() );
                    if ( typeFromElement != null ) {
                        if ( type == null ) {
                            type = typeFromElement;
                            typeLocation = xmlReader.getLocation();
                        } else {
                            throwDuplicatedTypeException( xmlReader, typeLocation, type, typeFromElement );
                        }
                    }
                    // district
                    if ( district == null )
                        district = parseDistrict( xmlReader, version );
                }
            } while ( xmlReader.hasNext() && xmlReader.next() != END_DOCUMENT );
            checkType( type );
            return new XPlanArchive( zipEntries, name, version, ade, type, crs, district );
        } finally {
            closeQuietly( xmlReader );
        }
    }

    private XPlanAde determineAde( String namespaceUri ) {
        try {
            return XPlanAde.valueOfNamespace( namespaceUri );
        } catch ( IllegalArgumentException e ) {
        }
        return null;
    }

    private String parseDistrict( XMLStreamReader xmlReader, XPlanVersion version )
                    throws XMLStreamException {
        QName name = xmlReader.getName();
        switch ( version ) {
        case XPLAN_3:
            if ( V3_ORTSTEIL_NAME.equals( name ) ) {
                return mapToDistrict( xmlReader );
            }
            break;
        case XPLAN_40:
            if ( V40_GEMEINDE.equals( name ) ) {
                xmlReader.next();
                return parseDistrictFromXPlan4( XPLAN_40.getNamespace(), xmlReader );
            }
        case XPLAN_41:
            if ( V41_GEMEINDE.equals( name ) ) {
                xmlReader.next();
                return parseDistrictFromXPlan4( XPLAN_41.getNamespace(), xmlReader );
            }
        default:
            break;
        }
        return null;
    }

    private String parseDistrictFromXPlan4( String namespace, XMLStreamReader xmlReader )
                    throws XMLStreamException {
        QName xpgemeindeName = new QName( namespace, "XP_Gemeinde" );
        boolean isXpGemeinde = skip( xmlReader, xpgemeindeName );
        if ( isXpGemeinde ) {
            QName ortsteilName = new QName( namespace, "ortsteilName" );
            boolean isOrtsteil = skip( xmlReader, ortsteilName );
            if ( isOrtsteil ) {
                return mapToDistrict( xmlReader );
            }
        }
        return null;
    }

    private String mapToDistrict( XMLStreamReader xmlReader )
                    throws XMLStreamException {
        String district = xmlReader.getElementText();
        if ( localCenterToDistrictMapper == null )
            return district;
        return localCenterToDistrictMapper.mapToDistrict( district );
    }

    private void checkType( XPlanType type ) {
        if ( type == null ) {
            String msg = format(
                            "Fehler: Datei '%s' ist ungültig. Datei enthält kein unterstützes XP_Plan-Element (%s).",
                            MAIN_FILE, Arrays.asList( XPlanType.values() ).stream().map(
                                            xPlanType -> xPlanType.toString() ).collect( Collectors.joining(",") ) );
            throw new IllegalArgumentException( msg );
        }
    }

    private void throwCrsInConflictException( XMLStreamReader xmlReader, Location crsLocation, ICRS crs,
                                               String secondCrsName ) {
        Location secondLocation = xmlReader.getLocation();
        String msg = format( "Fehler: Dokument enthält widersprüchliche CRS-Informationen "
                                             + "(srsName-Attribute): '%s' (Zeile: %d, Spalte: %d) und '%s' (Zeile: %d, Spalte: %d)",
                             crs.getName(), crsLocation.getLineNumber(), crsLocation.getColumnNumber(), secondCrsName,
                             secondLocation.getLineNumber(), secondLocation.getColumnNumber() );
        throw new IllegalArgumentException( msg );
    }

    private void throwDuplicatedTypeException( XMLStreamReader xmlReader, Location typeLocation, XPlanType type,
                                              XPlanType secondType ) {
        Location secondLocation = xmlReader.getLocation();
        String msg = format( "Fehler: Dokument enthält mehr als ein XP_Plan-Element: '%s' (Zeile: %d, Spalte: %d) "
                                             + "und '%s' (Zeile: %d, Spalte: %d)", type, typeLocation.getLineNumber(),
                             typeLocation.getColumnNumber(), secondType, secondLocation.getLineNumber(),
                             secondLocation.getColumnNumber() );
        throw new IllegalArgumentException( msg );
    }

    private boolean skip( XMLStreamReader xmlReader, QName elementToStop )
                    throws XMLStreamException {
        int openElements = 1;
        do {
            if ( xmlReader.isStartElement() ) {
                openElements++;
                if ( elementToStop != null && elementToStop.equals( xmlReader.getName() ) ) {
                    return true;
                }
                if ( elementToStop != null && openElements == 0 ) {
                    return false;
                }
            } else if ( xmlReader.isEndElement() ) {
                openElements--;
            }
        } while ( xmlReader.next() != END_DOCUMENT );
        return false;
    }

    private ZipEntryWithContent readEntries( InputStream inputStream, ZipEntryWithContent mainZipEntry,
                                             List<ZipEntryWithContent> zipEntries )
                    throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream( inputStream, Charset.forName( "UTF-8" ) );
        ZipEntry entry;
        while ( ( entry = zipInputStream.getNextEntry() ) != null ) {
            ZipEntryWithContent zipEntry = readZipEntryFromStream( zipInputStream, entry );
            zipEntries.add( zipEntry );
            if ( MAIN_FILE.equals( zipEntry.getName() ) ) {
                mainZipEntry = zipEntry;
            }
        }
        if ( mainZipEntry == null ) {
            throw new IllegalArgumentException(
                            "GML-Datei kann nicht eingelesen werden. Ist der Dateiname korrekt (xplan.gml)?" );
        }
        return mainZipEntry;
    }

    private ZipEntryWithContent readZipEntryFromStream( ZipInputStream zipInputStream, ZipEntry entry )
                    throws IOException {
        byte[] buffer = new byte[2048];
        try ( ByteArrayOutputStream output = new ByteArrayOutputStream() ) {
            int len;
            while ( ( len = zipInputStream.read( buffer ) ) > 0 ) {
                output.write( buffer, 0, len );
            }
            return new ZipEntryWithContent( entry, output.toByteArray() );
        }
    }

    private XMLStreamReader createReader( ZipEntryWithContent entry )
                    throws XMLStreamException, FactoryConfigurationError {
        XMLStreamReader xmlReader = XMLInputFactory.newInstance().createXMLStreamReader( entry.retrieveContentAsStream() );
        skipStartDocument( xmlReader );
        return xmlReader;
    }

    private void closeQuietly( XMLStreamReader xmlReader ) {
        if ( xmlReader != null ) {
            try {
                xmlReader.close();
            } catch ( XMLStreamException e ) {
                // nothing to do
            }
        }
    }

}