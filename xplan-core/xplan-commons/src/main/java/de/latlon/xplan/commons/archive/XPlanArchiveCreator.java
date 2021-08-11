/*-
 * #%L
 * xplan-commons - Commons Paket fuer XPlan Manager und XPlan Validator
 * %%
 * Copyright (C) 2008 - 2020 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 2.1 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */
package de.latlon.xplan.commons.archive;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLStreamException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.lang.String.format;

/**
 * Creator for {@link XPlanArchive}s.
 *
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @version $Revision: $, $Date: $
 */
public class XPlanArchiveCreator {

    public static final String MAIN_FILE = "xplan.gml";

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
     *                 XPlan archive (ZIP-file), must not be <code>null</code>
     * @throws IllegalArgumentException
     *                 if the file can not be read or is obviously invalid
     */
    public XPlanArchive createXPlanArchive( File file )
                    throws IOException {
        String fileName = file.getName();
        if ( fileName.toLowerCase().endsWith( ".zip" ) )
            return createXPlanArchiveFromZip( fileName, new FileInputStream( file ) );
        return createXPlanArchiveFromGml( fileName, new FileInputStream( file ) );
    }

    /**
     * Creates a new {@link XPlanArchive} instance from the given file.
     *
     * @param file
     *                         XPlan archive (ZIP-file), must not be <code>null</code>
     * @throws IllegalArgumentException
     *                         if the file can not be read or is obviously invalid
     */
    public XPlanArchive createXPlanArchiveFromZip( File file )
                            throws IOException {
        String fileName = file.getName();
        return createXPlanArchiveFromZip( fileName, new FileInputStream( file ) );
    }

    /**
     * @param name
     *                 never <code>null</code>
     * @param inputStream
     *                 never <code>null</code> and is closed on return
     * @throws IOException
     */
    public XPlanArchive createXPlanArchiveFromZip( String name, InputStream inputStream )
                    throws IOException {
        try {
            List<ZipEntryWithContent> zipEntries = new ArrayList<>();
            MainZipEntry mainEntry = readEntries( inputStream, zipEntries );
            String district = mapDistrict( mainEntry );
            return new XPlanArchive( zipEntries, name, mainEntry.getVersion(), mainEntry.getAde(), mainEntry.getType(),
                                     mainEntry.getCrs(), district, mainEntry.hasMultipleXPlanElements() );
        } catch ( XMLStreamException | FactoryConfigurationError e ) {
            String message = format( "Kann Archiv '%s' nicht lesen. Fehlermeldung: %s", name, e.getLocalizedMessage() );
            throw new IllegalArgumentException( message, e );
        } finally {
            inputStream.close();
        }
    }

    /**
     * @param name
     *                 never <code>null</code>
     * @param inputStream
     *                 never <code>null</code> and is closed on return
     * @throws IOException
     */
    public XPlanArchive createXPlanArchiveFromGml( String name, InputStream inputStream )
                            throws IOException {
        XPlanGmlReader xPlanGmlReader = new XPlanGmlReader();
        try {
            MainZipEntry mainEntry = xPlanGmlReader.createZipEntry( name, inputStream );
            String district = mapDistrict( mainEntry );
            return new XPlanArchive( mainEntry, name, mainEntry.getVersion(), mainEntry.getAde(), mainEntry.getType(),
                                     mainEntry.getCrs(), district, mainEntry.hasMultipleXPlanElements() );
        } catch ( XMLStreamException e ) {
            e.printStackTrace();
            String message = format( "Kann Archiv '%s' nicht lesen. Fehlermeldung: %s", name, e.getLocalizedMessage() );
            throw new IllegalArgumentException( message, e );
        } finally {
            inputStream.close();
        }
    }

    private String mapDistrict( MainZipEntry mainEntry ) {
        String district = mainEntry.getDistrict();
        if ( localCenterToDistrictMapper == null )
            return district;
        return localCenterToDistrictMapper.mapToDistrict( district );
    }

    private MainZipEntry readEntries( InputStream inputStream, List<ZipEntryWithContent> zipEntries )
                    throws IOException, XMLStreamException {

        MainZipEntry mainZipEntry = null;
        ZipInputStream zipInputStream = new ZipInputStream( inputStream, Charset.forName( "UTF-8" ) );
        ZipEntry entry;
        while ( ( entry = zipInputStream.getNextEntry() ) != null ) {
            ArtefactEntry zipEntry = readZipEntryFromStream( zipInputStream, entry );
            if ( MAIN_FILE.equals( entry.getName() ) ) {
                XPlanGmlReader xPlanGmlReader = new XPlanGmlReader();
                mainZipEntry = xPlanGmlReader.createZipEntry( zipEntry );
                zipEntries.add( mainZipEntry );
            } else {
                zipEntries.add( zipEntry );
            }
        }
        if ( mainZipEntry == null ) {
            throw new IllegalArgumentException(
                            "GML-Datei kann nicht eingelesen werden. Ist der Dateiname korrekt (xplan.gml)?" );
        }
        return mainZipEntry;
    }

    private ArtefactEntry readZipEntryFromStream( ZipInputStream zipInputStream, ZipEntry entry )
                    throws IOException {
        byte[] buffer = new byte[2048];
        try ( ByteArrayOutputStream output = new ByteArrayOutputStream() ) {
            int len;
            while ( ( len = zipInputStream.read( buffer ) ) > 0 ) {
                output.write( buffer, 0, len );
            }
            return new ArtefactEntry( entry, output.toByteArray() );
        }
    }

}
