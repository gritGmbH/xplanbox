package de.latlon.xplan.validator.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz</a>
 * @author last edited by: $Author: lyn $
 * @version $Revision: $, $Date: $
 */
public class ReportUtils {

    public static final String LABEL_INVALID = "nicht valide";

    public static final String LABEL_VALID = "valide";

    private ReportUtils() {
    }

    /**
     * @param isValid
     *            , true if valid, false otherwise
     * @return the string representation of the valid statue ('valid' if valid, 'nicht valide' if not)
     */
    public static String createValidLabel( boolean isValid ) {
        return isValid ? LABEL_VALID : LABEL_INVALID;
    }

    static void writeShapefilesToZipOS( File directory, ZipOutputStream out )
                    throws IOException {
        ZipEntry shapeDirEntry = new ZipEntry( "shapes/" );
        out.putNextEntry( shapeDirEntry );
        for ( File file : directory.listFiles() ) {
            if ( isShapefile( file ) ) {
                FileInputStream in = new FileInputStream( file );
                out.putNextEntry( new ZipEntry( "shapes/" + file.getName() ) );

                int len;
                byte[] buffer = new byte[1024];
                while ( ( len = in.read( buffer ) ) > 0 ) {
                    out.write( buffer, 0, len );
                }
                out.flush();
                out.closeEntry();
                in.close();
            }
        }
        out.closeEntry();
    }

    static void deleteShapefiles( File directory ) {
        for ( File file : directory.listFiles() ) {
            if ( isShapefile( file ) ) {
                file.delete();
            }
        }
    }

    public static boolean isShapefile( File file ) {
        return ( file.getName().endsWith( ".shp" ) || file.getName().endsWith( ".shx" )
                 || file.getName().endsWith( ".prj" ) || file.getName().endsWith( ".dbf" )
                 || file.getName().endsWith( ".fix" ) || file.getName().endsWith( ".atx" )
                 || file.getName().endsWith( ".sbx" ) || file.getName().endsWith( ".sbn" )
                 || file.getName().endsWith( ".aih" ) || file.getName().endsWith( ".ain" )
                 || file.getName().endsWith( ".shp.xml" ) || file.getName().endsWith( ".cpg" ) );
    }
}
