package de.latlon.xplan.manager.web.shared.edit;

/**
 * Used to discriminate XP_MimeTypes.
 *
 * @author <a href="mailto:stenger@lat-lon.de">Dirk Stenger</a>
 * @author last edited by: $Author: stenger $
 * @version $Revision: $, $Date: $
 */
public enum MimeTypes {

    APPLICATION_MSEXCEL( "application/msexcel" ), APPLICATION_MSWORD( "application/msword" ), APPLICATION_ODT(
                    "application/odt" ), APPLICATION_PDF( "application/pdf" ), APPLICATION_VND_OGC_GML(
                    "application/vnd.ogc.gml" ), APPLICATION_VND_OGC_SLD_XML(
                    "application/vnd.ogc.sld+xml" ), APPLICATION_VND_OGC_WMS_XML(
                    "application/vnd.ogc.wms_xml" ), APPLICATION_XML( "application/xml" ), APPLICATION_ZIP(
                    "application/zip" ), IMAGE_ECW( "image/ecw" ), IMAGE_JPG( "image/jpg" ), IMAGE_PNG(
                    "image/png" ), IMAGE_SVG_XML( "image/svg+xml" ), IMAGE_TIFF( "image/tiff" ), TEXT_HTML(
                    "text/html" ), TEXT_PLAIN( "text/plain" );

    private String code;

    MimeTypes( String code ) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MimeTypes getByCode( String code ) {
        if ( code == null )
            return null;
        for ( MimeTypes value : values() ) {
            if ( value.code.equals( code.trim() ) )
                return value;
        }
        return null;
    }

}